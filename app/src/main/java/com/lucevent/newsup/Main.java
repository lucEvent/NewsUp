package com.lucevent.newsup;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
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
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.io.LogoManager;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.kernel.NewsManager;
import com.lucevent.newsup.kernel.ScheduleManager;
import com.lucevent.newsup.services.ScheduledDownloadReceiver;
import com.lucevent.newsup.view.activity.SelectSitesActivity;
import com.lucevent.newsup.view.fragment.AboutFragment;
import com.lucevent.newsup.view.fragment.AppSettingsFragment;
import com.lucevent.newsup.view.fragment.BookmarksFragment;
import com.lucevent.newsup.view.fragment.HistorialFragment;
import com.lucevent.newsup.view.fragment.NewsListFragment;
import com.lucevent.newsup.view.fragment.StatisticsFragment;

import java.lang.ref.WeakReference;

public class Main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NewsManager dataManager;
    public Handler handler;

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        handler = new Handler(this);
        AppSettings.initialize(this, handler);
        dataManager = new NewsManager(this);

        if (AppSettings.firstStart()) {
            Intent intent = new Intent(this, SelectSitesActivity.class);
            intent.putExtra(AppCode.SEND_PURPOSE, SelectSitesActivity.FOR.APP_FIRST_START);
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

        updateDrawer(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(AppCode.SEND_NEWS_IDS)) {
            int[] news_codes = extras.getIntArray(AppCode.SEND_NEWS_IDS);
            currentFragment = NewsListFragment.instanceForNotification(news_codes);
        } else
            currentFragment = NewsListFragment.instanceFor(-1);

        if (extras != null && extras.containsKey(AppCode.RESTART)) {
            ScheduledDownloadReceiver.scheduleDownloads(this,
                    new ScheduleManager(this).getDownloadSchedules());
        }

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_content, currentFragment)
                .commit();
    }

    @Override
    public void onBackPressed()
    {
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else if (getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();
        else super.onBackPressed();
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
        if (ProSettings.areStatisticsEnabled())
            stats.setVisible(true);
        else
            stats.setVisible(false);

        MenuItem sports = navigationView.getMenu().findItem(R.id.nav_sports);
        if (ProSettings.areSportsEnabled())
            sports.setVisible(true);
        else
            sports.setVisible(false);

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

    private Fragment currentFragment, previousFragment;

    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        navigateTo(item.getItemId());
        return true;
    }

    private void navigateTo(int where)
    {
        Fragment fragment;
        String title;
        int colorCode = -1;

        switch (where) {
            case R.id.nav_my_news:
                fragment = NewsListFragment.instanceFor(-1);
                title = getString(R.string.my_news);
                break;
            case R.id.nav_saved_news:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !diskPermissionGranted()) {
                    requestPermissions(
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                            AppCode.REQUEST_PERMISSION_WRITE_IN_STORAGE);
                    return;
                }
                fragment = new BookmarksFragment();
                title = getString(R.string.saved_for_later);
                break;
            case R.id.nav_read_news:
                fragment = new HistorialFragment();
                title = getString(R.string.read_news);
                break;
            case R.id.nav_settings:
                fragment = new AppSettingsFragment();
                title = getString(R.string.settings);
                break;
            case R.id.nav_stats:
                fragment = new StatisticsFragment();
                title = getString(R.string.statistics);
                break;
            case R.id.nav_about:
                fragment = new AboutFragment();
                title = getString(R.string.about);
                break;
            case R.id.nav_sports:
                startActivity(new Intent(this, SportsMain.class));
                return;
            case R.id.nav_add_content:
                Intent intent = new Intent(this, SelectSitesActivity.class);
                intent.putExtra(AppCode.SEND_PURPOSE, SelectSitesActivity.FOR.ADD_CONTENT);
                startActivityForResult(intent, AppCode.REQUEST_ADD_CONTENT);
                return;
            default:
                colorCode = where;
                fragment = NewsListFragment.instanceFor(where);
                title = AppData.getSiteByCode(where).name;
        }
        drawer.closeDrawer(GravityCompat.START);

        previousFragment = currentFragment;
        currentFragment = fragment;

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_content, fragment)
                .commit();

        setTitle(title);
        setUpColors(colorCode);
    }

    private void setUpColors(int colorCode)
    {
        int barColor, textColor, statusBarColor;
        boolean navBlack = false;

        Toolbar ab = (Toolbar) findViewById(R.id.toolbar);
        if (colorCode == -1) {
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


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(statusBarColor);
        }
    }

    static class Handler extends android.os.Handler {

        private final WeakReference<Main> context;

        Handler(Main context)
        {
            this.context = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg)
        {
            Main service = context.get();
            switch (msg.what) {
                case AppCode.ACTION_UPDATE_FAVORITES:
                    service.updateDrawer(true);
                    break;
                default:
                    AppSettings.printerror("[MAIN] OPTION UNKNOWN: " + msg.what);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
    {
        switch (requestCode) {
            case AppCode.REQUEST_PERMISSION_WRITE_IN_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the contacts-related task you need to do.
                    onNavigationItemSelected(((NavigationView) findViewById(R.id.nav_view)).getMenu().findItem(R.id.nav_saved_news));

                } else {

                    // permission denied, boo!
                    Toast.makeText(this, R.string.msg_disk_permission_denied, Toast.LENGTH_SHORT).show();

                }
                break;
            }
        }
    }

    private boolean diskPermissionGranted()
    {
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

}
