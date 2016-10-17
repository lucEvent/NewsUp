package com.lucevent.newsup;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.io.LogoManager;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.kernel.NewsManager;
import com.lucevent.newsup.kernel.ScheduleManager;
import com.lucevent.newsup.net.MainChangeListener;
import com.lucevent.newsup.permission.StoragePermissionHandler;
import com.lucevent.newsup.services.ScheduledDownloadReceiver;
import com.lucevent.newsup.view.activity.ContactActivity;
import com.lucevent.newsup.view.activity.SelectSitesActivity;
import com.lucevent.newsup.view.fragment.AboutFragment;
import com.lucevent.newsup.view.fragment.AppSettingsFragment;
import com.lucevent.newsup.view.fragment.BookmarksFragment;
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
    private NewsManager dataManager;
    private StoragePermissionHandler permissionHandler;

    public DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        AppSettings.initialize(this, this);
        dataManager = new NewsManager(this);
        permissionHandler = new StoragePermissionHandler(this);

        if (AppSettings.firstStart()) {
            Intent intent = new Intent(this, SelectSitesActivity.class);
            intent.putExtra(AppCode.SEND_PURPOSE, SelectSitesActivity.For.APP_FIRST_START);
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
        assert navigationView != null : "Navigation view is null";
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = new FragmentManager(this, navigationView, R.id.main_content);

        updateDrawer(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(AppCode.SEND_NEWS_IDS)) {
            int[] news_codes = extras.getIntArray(AppCode.SEND_NEWS_IDS);
            newsFragment = NewsListFragment.instanceForNotification(news_codes);
        } else
            newsFragment = NewsListFragment.instanceFor(-1);

        if (extras != null && extras.containsKey(AppCode.RESTART)) {
            ScheduledDownloadReceiver.scheduleDownloads(this,
                    new ScheduleManager(this).getDownloadSchedules());
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
            if (f instanceof NewsListFragment)
                setUpColors(((NewsListFragment) f).currentSiteCode);

        } else super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppCode.REQUEST_ADD_CONTENT && resultCode > 0) {
            navigateTo(resultCode);
        }
    }

    private void updateDrawer(boolean updateFavorites)
    {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null : "Navigation view is null";

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            navigationView.setItemIconTintList(null);

        MenuItem stats = navigationView.getMenu().findItem(R.id.nav_stats);
        if (ProSettings.checkEnabled(ProSettings.STATISTICS_KEY))
            stats.setVisible(true);
        else
            stats.setVisible(false);

        MenuItem notes = navigationView.getMenu().findItem(R.id.nav_notes);
        if (ProSettings.checkEnabled(ProSettings.NOTES_KEY))
            notes.setVisible(true);
        else
            notes.setVisible(false);

        if (updateFavorites) {
            SubMenu fab_menu = navigationView.getMenu().findItem(R.id.nav_header_favorites).getSubMenu();
            fab_menu.clear();

            Sites favorites = dataManager.getFavoritesSites();
            for (Site site : favorites) {
                MenuItem mi = fab_menu.add(2, site.code, Menu.NONE, site.name);
                configureMenuItem(mi, site);
            }
        }
        navigationView.invalidate();
    }

    private void configureMenuItem(MenuItem mi, Site site)
    {
        Drawable icon = LogoManager.getLogo(site.code, LogoManager.Size.DRAWER);
        assert icon != null : "Icon not found for code " + site.code;
        icon.setColorFilter(0x000000, android.graphics.PorterDuff.Mode.ADD);
        mi.setIcon(icon);
        mi.setCheckable(true);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item)
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
            case R.id.nav_saved_news:
                if (permissionHandler.checkAndAsk(this)) {
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
            case R.id.nav_add_content:
                Intent intent = new Intent(this, SelectSitesActivity.class);
                intent.putExtra(AppCode.SEND_PURPOSE, SelectSitesActivity.For.ADD_CONTENT);
                startActivityForResult(intent, AppCode.REQUEST_ADD_CONTENT);
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

            if (!(fragmentManager.currentFragment instanceof NewsListFragment))
                fragmentManager.popToFirst();

            else newsFragment.setUp();

            fragmentManager.setNavigationItemId(0, where);

        } else
            fragmentManager.replaceFragment(fragment, where,
                    fragmentManager.currentFragment instanceof NewsListFragment);

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
            textColor = site.hasDarkColor() ? Color.WHITE : Color.BLACK;
            statusBarColor = barColor == 0xffffffff ? 0xffcccccc : barColor;
            navBlack = !site.hasDarkColor();
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
        updateDrawer(true);
    }

    @Override
    public void onReplaceFragment(Fragment f, int navigationViewIndex, boolean addToStack)
    {
        fragmentManager.replaceFragment(f, navigationViewIndex, addToStack);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
    {
        if (permissionHandler.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            navigateTo(R.id.nav_saved_news);
            ((NavigationView) findViewById(R.id.nav_view)).setCheckedItem(R.id.nav_saved_news);
        }
    }

}
