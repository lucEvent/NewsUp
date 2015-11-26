package com.newsup;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.newsup.dialog.SiteConfiguration;
import com.newsup.kernel.News;
import com.newsup.kernel.NewsDataCenter;
import com.newsup.kernel.Site;
import com.newsup.kernel.list.NewsMap;
import com.newsup.kernel.list.SectionList;
import com.newsup.lister.NewsLister;
import com.newsup.lister.SectionLister;
import com.newsup.lister.SiteLister;
import com.newsup.task.TaskMessage;
import com.newsup.widget.NewsNotFoundDialog;
import com.newsup.widget.NewsView;

public class Main extends ListActivity implements TaskMessage {

    /**
     * Display layer
     **/
    private static NewsLister newslister;
    private static NewsView newsView;

    /**
     * Kernel layer
     **/
    private static NewsDataCenter datamanager;
    private static Site currentSite;
    private static boolean displayingNews;

    // *****///
    private static DrawerLayout mDrawerLayout;
    private static ListView drawerSiteList;
    private static ListView drawerSectionList;
    private static ActionBarManager actionBar;
    private static Menu appmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);

        newslister = new NewsLister(this);
        newslister.setNotifyOnChange(true);
        setListAdapter(newslister);

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        datamanager = new NewsDataCenter(this, cm, handler);

        newsView = new NewsView(this, datamanager, handler);
        actionBar = new ActionBarManager();

        //TODO que se muestre la ultima que estaba viendo no?
        displaySiteNews(null, null);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawerSiteList = (ListView) findViewById(R.id.site_drawer);
        drawerSiteList.setAdapter(new SiteLister(this, datamanager, true));
        drawerSiteList.setOnItemClickListener(null);

        drawerSectionList = (ListView) findViewById(R.id.section_drawer);
        drawerSectionList.setAdapter(new SectionLister(this, new SectionList()));
        if (currentSite != null) {
            ((SectionLister) drawerSectionList.getAdapter()).addAll(currentSite.getSections());
        }
        drawerSectionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                displaySiteNews(currentSite, new int[]{position});

                drawerSectionList.setItemChecked(position, true);

                mDrawerLayout.closeDrawer(drawerSectionList);
            }

        });
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
        return super.onOptionsItemSelected(item);
    }

    private void displaySiteNews(Site site, int[] sections) {
        currentSite = site;
        closeNews();
        newslister.clear();

        if (site == null) {
            datamanager.load_Mainpage_News();
            setTitle(Site.MAIN_NAME);

            getListView().setBackgroundColor(Site.MAIN_COLOR);
        } else {
            datamanager.load_News_from(site, sections);
            setTitle(site.name);
            getListView().setBackgroundColor(site.color);
        }
        actionBar.update();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(drawerSiteList)) {
            mDrawerLayout.closeDrawer(drawerSiteList);
        } else if (mDrawerLayout.isDrawerOpen(drawerSectionList)) {
            mDrawerLayout.closeDrawer(drawerSectionList);
        } else if (displayingNews) {
            closeNews();
        } else {
            finish();
        }
    }

    public void onMyCustomFeedAction(View view) {
        if (currentSite != null) {
            displaySiteNews(null, null);

            SectionLister sectionlister = (SectionLister) drawerSectionList.getAdapter();
            sectionlister.clear();
        }
        mDrawerLayout.closeDrawer(drawerSiteList);
    }

    public void onSavedForLaterAction(View view) {
        startActivity(new Intent(this, BookmarksActivity.class));
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

            SectionLister sectionlister = (SectionLister) drawerSectionList.getAdapter();
            sectionlister.clear();
            sectionlister.addAll(site.getSections());
            drawerSectionList.setSelection(0);
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
            actionBar.hide();
            displayingNews = true;
        } else {
            new NewsNotFoundDialog(this, handler, news).show();
        }
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

        ActionBarManager() {

            actionBar = getActionBar();
/*            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
*/
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
                } else {
                    set.setIcon(R.drawable.ic_settings);
                    fav.setIcon(isfav ? R.drawable.ic_star : R.drawable.ic_star_border);
                    setBarTitle(site.name, Color.BLACK);
                }
                actionBar.setIcon(site.icon);
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