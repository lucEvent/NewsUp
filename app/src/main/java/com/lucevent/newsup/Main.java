package com.lucevent.newsup;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
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

import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.io.LogoManager;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.kernel.NewsManager;
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
        setContentView(R.layout.a_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        handler = new Handler(this);
        AppSettings.initialize(this, handler);
        dataManager = new NewsManager(this);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null : "Navigation view is null";
        navigationView.setNavigationItemSelectedListener(this);

        updateDrawer(true, true);

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(AppCode.SEND_NEWS_IDS)) {
            int[] news_codes = extras.getIntArray(AppCode.SEND_NEWS_IDS);
            currentFragment = NewsListFragment.instanceForNotification(news_codes);
        } else
            currentFragment = NewsListFragment.instanceFor(-1);

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


    private void updateDrawer(boolean updateFavorites, boolean updateSites)
    {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null : "Navigation view is null";

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            navigationView.setItemIconTintList(null);

        MenuItem stats = navigationView.getMenu().findItem(R.id.nav_stats);
        if (AppSettings.isDevModeActivated())
            stats.setVisible(true);
        else
            stats.setVisible(false);
        
        if (updateFavorites) {
            SubMenu fab_menu = navigationView.getMenu().findItem(R.id.nav_header_favorites).getSubMenu();
            fab_menu.clear();

            Sites favorites = dataManager.getFavoritesSites();
            for (Site site : favorites) {
                MenuItem mi = fab_menu.add(2, site.code, Menu.NONE, site.name);
                configureMenuItem(mi, site);
            }
        }

        if (updateSites) {
            SubMenu sites_menu = navigationView.getMenu().findItem(R.id.nav_header_sites).getSubMenu();
            sites_menu.clear();

            for (Site site : AppData.sites) {
                MenuItem mi = sites_menu.add(3, site.code, Menu.NONE, site.name);
                configureMenuItem(mi, site);
            }
        }
        navigationView.invalidate();
    }

    private void configureMenuItem(MenuItem mi, Site site)
    {
        if (site.code == -1) {
            mi.setEnabled(false);
            mi.setIcon(R.drawable.ic_label);
        } else {
            Drawable icon = LogoManager.getLogo(site.code, LogoManager.Size.DRAWER);
            assert icon != null : "Icon not found for code " + site.code;
            icon.setColorFilter(0x000000, android.graphics.PorterDuff.Mode.ADD);
            mi.setIcon(icon);
            mi.setCheckable(true);
        }
    }

    private Fragment currentFragment, previousFragment;

    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        Fragment fragment;
        String title;
        int colorCode = -1;

        switch (item.getItemId()) {
            case R.id.nav_my_news:
                fragment = NewsListFragment.instanceFor(-1);
                title = getString(R.string.my_news);
                break;
            case R.id.nav_saved_news:
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
            default:
                colorCode = item.getItemId();
                fragment = NewsListFragment.instanceFor(colorCode);
                title = AppData.getSiteByCode(colorCode).name;
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
        return true;
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
                    service.updateDrawer(true, false);
                    break;
                case AppCode.ACTION_UPDATE_PRO:
                    service.updateDrawer(false, false);
                    break;
                default:
                    AppSettings.printerror("[MAIN] OPTION UNKNOWN: " + msg.what);
            }
        }

    }

}
