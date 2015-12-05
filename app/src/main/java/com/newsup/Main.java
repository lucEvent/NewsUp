package com.newsup;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.newsup.control.SectionPickerManager;
import com.newsup.dialog.SiteConfiguration;
import com.newsup.kernel.NewsDataCenter;
import com.newsup.kernel.basic.News;
import com.newsup.kernel.basic.Site;
import com.newsup.kernel.set.NewsMap;
import com.newsup.kernel.util.Typefaces;
import com.newsup.lister.NewsLister;
import com.newsup.lister.SiteLister;
import com.newsup.task.Socket;
import com.newsup.widget.NewsNotFoundDialog;
import com.newsup.widget.NewsView;

import java.lang.reflect.Field;

public class Main extends ListActivity implements Socket {

    /**
     * display layer
     **/
    private static ListView drawerSiteList;
    private static NewsLister newslister;
    private static NewsView newsView;
    private static View newsListView;

    /**
     * kernel layer
     **/
    private static NewsDataCenter datamanager;


    /**
     * controllers
     **/
    private static DrawerLayout mDrawerLayout;
    private static ActionBarDrawerToggle mDrawerToggle;
    private static SectionPickerManager sectionManager;
    private static ActionBarManager actionBar;
    private static Menu appmenu;

    /**
     * class variables
     */
    private static Site currentSite;
    private static boolean displayingNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);

        newslister = new NewsLister(this);
        newslister.setNotifyOnChange(true);
        setListAdapter(newslister);

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        datamanager = new NewsDataCenter(this, cm, handler);

        newsView = new NewsView(this, handler);
        newsListView = findViewById(R.id.list_content);
        actionBar = new ActionBarManager();

        //TODO que se muestre la ultima que estaba viendo no?
        displaySiteNews(null, null);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawerSiteList = (ListView) findViewById(R.id.site_drawer);
        drawerSiteList.setAdapter(new SiteLister(this, datamanager, true));
        drawerSiteList.setOnItemClickListener(null);

        mDrawerToggle = new ActionBarDrawerToggle(Main.this, mDrawerLayout, R.drawable.ic_drawer, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        sectionManager = new SectionPickerManager(this, this);
        if (currentSite != null) {
            sectionManager.setSections(currentSite.getSections());
        }

        /**
         * Setting app font
         */
        try {
            Typeface customFontTypeface = Typefaces.get(this, "fonts/customfont.woff");
            Field defaultFontTypefaceField = Typeface.class.getDeclaredField("SERIF");
            defaultFontTypefaceField.setAccessible(true);
            defaultFontTypefaceField.set(null, customFontTypeface);
        } catch (Exception e) {
            debug("Can not set custom font");
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        appmenu = menu;
        actionBar.update();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                datamanager.toggleFavorite(currentSite);
                actionBar.update();
                ((SiteLister) drawerSiteList.getAdapter()).resetMain();
                return true;
            case R.id.action_settings:
                new SiteConfiguration(Main.this, datamanager).set(currentSite);
                return true;
        }
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void displaySiteNews(Site site, int[] sections) {
        currentSite = site;
        closeNews();
        newslister.clear();

        if (site == null) {
            datamanager.load_Mainpage_News();
            setTitle(Site.MAIN_NAME);
            newsListView.setBackgroundColor(Site.MAIN_COLOR);
            newslister.showSiteLogo(true);
        } else {
            datamanager.load_News_from(site, sections);
            setTitle(site.name);
            newsListView.setBackgroundColor(site.color);
            newslister.showSiteLogo(false);
        }
        actionBar.update();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(drawerSiteList)) {
            mDrawerLayout.closeDrawer(drawerSiteList);
        } else if (displayingNews) {
            closeNews();
        } else {
            finish();
        }
    }

    public void onMyCustomFeedAction(View view) {
        if (currentSite != null) {
            displaySiteNews(null, null);
        }
        mDrawerLayout.closeDrawer(drawerSiteList);
    }

    public void onSavedForLaterAction(View view) {
        startActivity(new Intent(this, BookmarksActivity.class));
        mDrawerLayout.closeDrawer(drawerSiteList);
    }

    public void onHistoryAction(View view) {
        startActivity(new Intent(this, HistoryActivity.class));
        mDrawerLayout.closeDrawer(drawerSiteList);
    }

    public void onConfigurationAction(View view) {
        startActivityForResult(new Intent(this, SettingsActivity.class), 0);
        mDrawerLayout.closeDrawer(drawerSiteList);
    }

    public void onSiteSelectedAction(View view) {
        Site site = (Site) view.getTag();
        if (site != currentSite) {
            displaySiteNews(site, null);

            sectionManager.setSections(site.getSections());
        }
        mDrawerLayout.closeDrawer(drawerSiteList);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        News news = newslister.getItem(position);
        if (news.link != null) {

            debug(news.link);

            datamanager.getNewsContent(news);

            displayNews(news);
        }
    }

    private void displayNews(News news) {
        if (newsView.displayNews(news)) {
            datamanager.addToHistory(news);
            actionBar.hide();
            displayingNews = true;
        } else {
            new NewsNotFoundDialog(this, handler, news).show();
        }
    }

    public void showSections(View view) {
        sectionManager.show();
    }

    private final Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case NEWS_READ:
                    newslister.add((News) msg.obj);
                    break;
                case NEWS_READ_HISTORY:
                    newslister.addAll((NewsMap) msg.obj);
                    break;
                case NO_INTERNET:
                    //TODO
                    debug("[NO INTERNET] Falta por hacer cosas");
                    break;
                case OPEN_NEWS:
                    News news = (News) msg.obj;
                    if (newsView.displayNews(news)) {
                        actionBar.hide();

                        displayingNews = true;
                    } else {
                        new NewsNotFoundDialog(Main.this, handler, news).show();
                    }
                    break;
                case ERROR:
                    debug("Error recibido por el Handler");
                    break;
                default:
                    debug("[][#] OPCION NO CONTEMPLADA: " + msg.what);
            }
        }

    };

    @Override
    public void message(int taskMessage, Object data) {
        switch (taskMessage) {
            case SECTION_SELECTED:
                displaySiteNews(currentSite, new int[]{(Integer) data});
                break;
        }
    }

    public void closeNews() {
        actionBar.show();
        displayingNews = false;
        newsView.close();
    }

    private static void debug(String text) {
        android.util.Log.d("##MAIN##", "[Main] " + text);
    }

    class ActionBarManager {

        private android.app.ActionBar actionBar;
        private ImageButton show_sections;

        ActionBarManager() {

            actionBar = getActionBar();
            show_sections = (ImageButton) findViewById(R.id.button_sections);

            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);

        }

        void update() {
            if (appmenu == null) return;

            Site site = currentSite;
            if (site == null) {
                actionBar.setBackgroundDrawable(new ColorDrawable(Site.MAIN_COLOR));
                actionBar.setIcon(R.mipmap.ic_app);
                appmenu.getItem(0).setVisible(false);
                appmenu.getItem(1).setVisible(false);
                setBarTitle(getString(R.string.mycustomfeed), Color.BLACK);
                show_sections.setVisibility(ImageButton.GONE);

            } else {
                actionBar.setBackgroundDrawable(new ColorDrawable(site.color));
                MenuItem fav = appmenu.getItem(0).setVisible(true);
                MenuItem set = appmenu.getItem(1).setVisible(true);

                boolean isfav = datamanager.isFavorite(site);
                boolean isDark = (Color.red(site.color) < 0x7F) && (Color.green(site.color) < 0x7F) && (Color.blue(site.color) < 0x7F);

                if (isDark) {
                    set.setIcon(R.drawable.ic_settings_white);
                    fav.setIcon(isfav ? R.drawable.ic_star_white : R.drawable.ic_star_border_white);
                    setBarTitle(site.name, Color.WHITE);
                    show_sections.setImageResource(R.drawable.ic_more);
                } else {
                    set.setIcon(R.drawable.ic_settings);
                    fav.setIcon(isfav ? R.drawable.ic_star : R.drawable.ic_star_border);
                    setBarTitle(site.name, Color.BLACK);
                    show_sections.setImageResource(R.drawable.ic_more_black);
                }
                actionBar.setIcon(site.icon);

                show_sections.setVisibility(ImageButton.VISIBLE);
                show_sections.getBackground().setColorFilter(site.color, PorterDuff.Mode.MULTIPLY);

            }
        }

        private void setBarTitle(String title, int color) {
            Spannable sp_title = new SpannableString(title);
            sp_title.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            sp_title.setSpan(new ForegroundColorSpan(color), 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            actionBar.setTitle(sp_title);
        }

        public void show() {
            actionBar.show();
        }

        public void hide() {
            actionBar.hide();
        }

    }

}