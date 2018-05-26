package com.lucevent.newsup;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.io.LogoManager;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.kernel.KernelManager;
import com.lucevent.newsup.kernel.ScheduleManager;
import com.lucevent.newsup.net.MainChangeListener;
import com.lucevent.newsup.permission.PermissionHandler;
import com.lucevent.newsup.services.ScheduledDownloadReceiver;
import com.lucevent.newsup.view.activity.ContactActivity;
import com.lucevent.newsup.view.activity.NewSiteRequestActivity;
import com.lucevent.newsup.view.activity.SelectSitesActivity;
import com.lucevent.newsup.view.fragment.AboutFragment;
import com.lucevent.newsup.view.fragment.AppSettingsFragment;
import com.lucevent.newsup.view.fragment.BookmarksFragment;
import com.lucevent.newsup.view.fragment.EventsFragment;
import com.lucevent.newsup.view.fragment.FragmentManager;
import com.lucevent.newsup.view.fragment.HistorialFragment;
import com.lucevent.newsup.view.fragment.NewsListFragment;
import com.lucevent.newsup.view.fragment.NotesFragment;
import com.lucevent.newsup.view.fragment.StatisticsFragment;
import com.lucevent.newsup.view.util.OnBackPressedListener;

public class Main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        MainChangeListener {

    private NewsListFragment newsFragment;

    private FragmentManager fragmentManager;
    private KernelManager dataManager;
    private PermissionHandler permissionHandler;

    public DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        AppCompatDelegate.setDefaultNightMode(
                PreferenceManager.getDefaultSharedPreferences(this).getBoolean(getString(R.string.pref_night_mode_key), false) ?
                        AppCompatDelegate.MODE_NIGHT_YES :
                        AppCompatDelegate.MODE_NIGHT_NO
        );
        super.onCreate(savedInstanceState);

        AppSettings.initialize(this, this);
        dataManager = new KernelManager(this);
        permissionHandler = new PermissionHandler(this, AppData.STORAGE_PERMISSIONS);

        if (AppSettings.firstStart()) {
            Intent intent = new Intent(this, SelectSitesActivity.class);
            intent.putExtra(AppCode.PURPOSE, SelectSitesActivity.For.APP_FIRST_START);
            startActivity(intent);
            finish();
            return;
        }

        setContentView(R.layout.a_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = new FragmentManager(this, navigationView, R.id.main_content);

        setUpDrawer();
        updateFavorites();

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(AppCode.SOURCES)) {
            newsFragment = NewsListFragment.instanceFor(extras);
        } else
            newsFragment = NewsListFragment.instanceFor(-1);

        if (extras != null && extras.containsKey(AppCode.RESTART)) {
            ScheduledDownloadReceiver.scheduleDownloads(this,
                    new ScheduleManager(this).getSchedule());
        }

        newsFragment.setRetainInstance(true);

        fragmentManager.addFragment(newsFragment, R.id.nav_my_news);

    }

    @Override
    public void onBackPressed()
    {
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else if (fragmentManager.currentFragment instanceof OnBackPressedListener
                && ((OnBackPressedListener) fragmentManager.currentFragment).onBackPressed()) {
            // do nothing
        } else if (fragmentManager.getBackStackEntryCount() > 0) {

            Fragment f = fragmentManager.popFragment();
            if (f instanceof NewsListFragment) {
                setUpColors(((NewsListFragment) f).currentSiteCode);
                lastItemSelected = R.id.nav_my_news;
            }

        } else super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppCode.REQUEST_ADD_CONTENT && resultCode > 0) {
            navigateTo(resultCode);
        } else if (requestCode == AppCode.REQUEST_SITE && resultCode != RESULT_CANCELED) {
            navigateTo(resultCode);
        }
    }

    private void setUpDrawer()
    {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            navigationView.setItemIconTintList(null);

        View header = navigationView.getHeaderView(0);

        ImageButton stats = (ImageButton) header.findViewById(R.id.nav_stats);
        if (ProSettings.checkEnabled(ProSettings.STATISTICS_KEY))
            stats.setVisibility(View.VISIBLE);
        else
            stats.setVisibility(View.GONE);

        lastItemSelected = R.id.nav_my_news;
        header.findViewById(R.id.nav_my_news).setSelected(true);

        header.findViewById(R.id.nav_my_news).setOnLongClickListener(onDrawerActionBarButtonLongClick);
        header.findViewById(R.id.nav_events).setOnLongClickListener(onDrawerActionBarButtonLongClick);
        header.findViewById(R.id.nav_saved_news).setOnLongClickListener(onDrawerActionBarButtonLongClick);
        header.findViewById(R.id.nav_read_news).setOnLongClickListener(onDrawerActionBarButtonLongClick);
        header.findViewById(R.id.nav_stats).setOnLongClickListener(onDrawerActionBarButtonLongClick);
        header.findViewById(R.id.nav_more_publications).setOnLongClickListener(onDrawerActionBarButtonLongClick);

        MenuItem notes = navigationView.getMenu().findItem(R.id.nav_notes);
        if (ProSettings.checkEnabled(ProSettings.NOTES_KEY))
            notes.setVisible(true);
        else
            notes.setVisible(false);
    }

    private View.OnLongClickListener onDrawerActionBarButtonLongClick = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v)
        {
            int msg = -1;
            switch (v.getId()) {
                case R.id.nav_my_news:
                    msg = R.string.my_news;
                    break;
                case R.id.nav_events:
                    msg = R.string.happening_now;
                    break;
                case R.id.nav_saved_news:
                    msg = R.string.bookmarks;
                    break;
                case R.id.nav_read_news:
                    msg = R.string.read_news;
                    break;
                case R.id.nav_stats:
                    msg = R.string.statistics;
                    break;
                case R.id.nav_more_publications:
                    msg = R.string.more_publications;
            }

            int x = v.getLeft();
            int y = v.getTop() + 2 * v.getHeight();
            Toast toast = Toast.makeText(v.getContext(), msg, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.START, x, y);
            toast.show();

            return true;
        }
    };

    private void updateFavorites()
    {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        Menu menu = navigationView.getMenu();

        menu.removeGroup(R.id.group_favorites);

        Sites favorites = dataManager.getFavoriteSites();
        for (Site site : favorites) {
            int order = Math.max(0, 0xffff - site.getNumReadings() - 1);
            MenuItem mi = menu.add(R.id.group_favorites, site.code, order, site.name + " (" + site.getNumReadings() + ")");
            configureMenuItem(mi, site);
        }

        navigationView.invalidate();
    }

    private void configureMenuItem(MenuItem mi, Site site)
    {
        mi.setCheckable(true);

        Drawable icon = LogoManager.getLogo(site.code, LogoManager.Size.DRAWER);
        if (icon == null)
            return;
        icon.setColorFilter(0x000000, android.graphics.PorterDuff.Mode.ADD);
        mi.setIcon(icon);
    }

    private int lastItemSelected;

    public void onDrawerActionBarItemSelected(View v)
    {
        if (navigateTo(v.getId()) && v.getId() != R.id.nav_more_publications) {
            lastItemSelected = v.getId();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        return navigateTo(item.getItemId());
    }

    private boolean navigateTo(int where)
    {
        Fragment fragment = null;
        String title;
        int colorCode = -1;
        boolean isNewsFragment = false;

        switch (where) {
            case R.id.nav_my_news:
                newsFragment.setSite(-1);
                title = getString(R.string.my_news);
                isNewsFragment = true;
                break;
            case R.id.nav_events:
                fragment = new EventsFragment();
                title = getString(R.string.happening_now);
                break;
            case R.id.nav_saved_news:
                if (permissionHandler.checkAndAsk(this, AppCode.REQUEST_PERMISSION_BOOKMARK)) {
                    fragment = new BookmarksFragment();
                    title = getString(R.string.bookmarks);
                    break;
                }
                return false;
            case R.id.nav_read_news:
                fragment = new HistorialFragment();
                title = getString(R.string.read_news);
                break;
            case R.id.nav_stats:
                fragment = new StatisticsFragment();
                title = getString(R.string.statistics);
                break;
            case R.id.nav_more_publications:
                Intent intent = new Intent(this, SelectSitesActivity.class);
                intent.putExtra(AppCode.PURPOSE, SelectSitesActivity.For.SELECT_ONE);
                startActivityForResult(intent, AppCode.REQUEST_ADD_CONTENT);
                return true;
            case R.id.nav_new_site_request:
                startActivityForResult(new Intent(this, NewSiteRequestActivity.class), AppCode.REQUEST_SITE);
                return true;
            case R.id.nav_notes:
                fragment = new NotesFragment();
                title = getString(R.string.notes);
                break;
            case R.id.nav_settings:
                fragment = new AppSettingsFragment();
                title = getString(R.string.settings);
                break;
            case R.id.nav_contact:
                startActivity(new Intent(this, ContactActivity.class));
                return false;
            case R.id.nav_about:
                fragment = new AboutFragment();
                title = getString(R.string.about);
                break;
            default:
                colorCode = where;
                newsFragment.setSite(where);
                title = AppData.getSiteByCode(where).name;
                isNewsFragment = true;
        }
        drawer.closeDrawer(GravityCompat.START);

        if (isNewsFragment) {

            fragmentManager.setNavigationItemId(0, where);

            if (!(fragmentManager.currentFragment instanceof NewsListFragment))
                fragmentManager.popToFirst();

            else {
                newsFragment.setUp();
                fragmentManager.updateCheckedItem(where, lastItemSelected);
            }
        } else {
            fragmentManager.replaceFragment(fragment, where,
                    fragmentManager.currentFragment instanceof NewsListFragment);

            fragmentManager.updateCheckedItem(where, lastItemSelected);
        }

        setTitle(title);
        setUpColors(colorCode);
        return true;
    }

    private void setUpColors(int colorCode)
    {
        int barColor, textColor, statusBarColor;
        boolean navBlack = false;

        Toolbar ab = (Toolbar) findViewById(R.id.toolbar);
        if (colorCode < 0) {
            barColor = ContextCompat.getColor(this, R.color.colorPrimary);
            textColor = Color.WHITE;
            statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark);
        } else {
            Site site = AppData.getSiteByCode(colorCode);

            barColor = site.color;
            textColor = site.needsBrightColors() ? Color.WHITE : Color.BLACK;
            statusBarColor = barColor == 0xffffffff ? 0xffcccccc : barColor;
            navBlack = !site.needsBrightColors();
        }
        //noinspection ConstantConditions
        ab.setBackgroundColor(barColor);
        ab.setTitleTextColor(textColor);

        if (!navBlack)
            //noinspection ConstantConditions
            ab.getNavigationIcon().clearColorFilter();
        else
            //noinspection ConstantConditions
            ab.getNavigationIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (statusBarColor == 0xffcccccc) {
                drawer.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                statusBarColor = barColor;
            } else
                drawer.setSystemUiVisibility(0);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(statusBarColor);
        }
    }

    @Override
    public void onMainistsChange()
    {
        if (newsFragment.lastLoadedSiteCode == -1)
            newsFragment.lastLoadedSiteCode = -9;
    }

    @Override
    public void onFavoritesChange()
    {
        updateFavorites();
    }

    @Override
    public void onReplaceFragment(Fragment f, int navigationViewIndex, boolean addToStack)
    {
        fragmentManager.replaceFragment(f, navigationViewIndex, addToStack);
    }

    @Override
    public void onLoadImagesPreferenceChanged()
    {
        newsFragment.onLoadImagesPreferenceChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults)
    {
        if (requestCode == AppCode.REQUEST_PERMISSION_BOOKMARK)
            if (permissionHandler.isPermissionGranted(permissions, grantResults)) {
                navigateTo(R.id.nav_saved_news);
                lastItemSelected = R.id.nav_saved_news;
            } else
                Toast.makeText(this, R.string.msg_permission_bookmark_denied, Toast.LENGTH_LONG).show();
    }

}
