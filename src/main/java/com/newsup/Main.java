package com.newsup;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.newsup.kernel.MainPageCenter;
import com.newsup.kernel.News;
import com.newsup.kernel.NewsDataCenter;
import com.newsup.kernel.Site;
import com.newsup.kernel.list.NewsMap;
import com.newsup.kernel.list.SectionList;
import com.newsup.kernel.list.SiteList;
import com.newsup.lister.NewsLister;
import com.newsup.lister.SectionLister;
import com.newsup.lister.SiteLister;
import com.newsup.task.TaskMessage;
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
    private static MainPageCenter mainpagecenter;
    private static SiteList sites;
    private static int currentSite;
    private static boolean displayingNews;

    // *****///
    private static DrawerLayout mDrawerLayout;
    private static ListView drawerSiteList;
    private static ListView drawerSectionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);

        newslister = new NewsLister(this);
        newslister.setNotifyOnChange(true);
        setListAdapter(newslister);

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        datamanager = new NewsDataCenter(this, cm, handler);
        sites = datamanager.getSites();
        mainpagecenter = new MainPageCenter(this.datamanager, cm, handler);


        newsView = new NewsView(this, datamanager, handler);

        //TODO que se muestre la ultima que estaba viendo no?
        displaySiteNews(0, null);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawerSiteList = (ListView) findViewById(R.id.site_drawer);
        drawerSiteList.setAdapter(new SiteLister(this, sites));
        drawerSiteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                displaySiteNews(position, null);

                drawerSiteList.setItemChecked(position, true);
                mDrawerLayout.closeDrawer(drawerSiteList);

                SectionLister sectionlister = (SectionLister) drawerSectionList.getAdapter();
                sectionlister.clear();

                if (position != 0) {
                    sectionlister.addAll(sites.get(currentSite).getSections());
                }
            }

        });

        drawerSectionList = (ListView) findViewById(R.id.section_drawer);
        drawerSectionList.setAdapter(new SectionLister(this, new SectionList()));
        if (currentSite != 0) {
            ((SectionLister) drawerSectionList.getAdapter()).addAll(sites.get(currentSite).getSections());
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

    private void displaySiteNews(int siteposition, int[] sections) {
        currentSite = siteposition;
        closeNews();
        newslister.clear();

        Site csite = sites.get(currentSite);
        if (siteposition == 0) {
            mainpagecenter.loadNews();
        } else {
            datamanager.getNews(csite, sections);
        }
        setTitle(csite.name);

        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(csite.color);
        if (siteposition == 0) {
            actionBar.setIcon(R.mipmap.ic_launcher);
        } else {
            actionBar.setIcon(csite.icon);
        }
    }

    @Override
    public void onBackPressed() {
        if (displayingNews) {
            closeNews();
        } else {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                startActivityForResult(new Intent(this, SettingsActivity.class), 0);
                break;
            case R.id.action_bookmarks:
                startActivity(new Intent(this, BookmarksActivity.class));
                break;
/*            case R.id.action_debug:
                Intent i = new Intent(this, DebugActivity.class);
                StringBuilder data = new StringBuilder();

                i.putExtra("debug", data.toString());
                startActivity(i);
                break;
  */
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
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
            getActionBar().hide();

            displayingNews = true;
        }
    }

    private static final Handler handler = new Handler() {

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
                case ERROR:
                    debug("Error recibido por el Handler");
                    break;
                default:
                    debug("[][#] OPCION NO CONTEMPLADA");
            }
        }

    };

    public void closeNews() {
        getActionBar().show();
        displayingNews = false;
        newsView.close();
    }

    private static void debug(String text) {
        Log.d("##MAIN##", "[Main] " + text);
    }

}