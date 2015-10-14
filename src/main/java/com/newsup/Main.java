package com.newsup;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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

import com.newsup.kernel.News;
import com.newsup.kernel.NewsDataCenter;
import com.newsup.kernel.Site;
import com.newsup.kernel.list.NewsList;
import com.newsup.kernel.list.NewsMap;
import com.newsup.kernel.list.SectionList;
import com.newsup.kernel.list.SiteList;
import com.newsup.net.State;
import com.newsup.widget.NewsLister;
import com.newsup.widget.NewsView;
import com.newsup.widget.SectionLister;
import com.newsup.widget.SiteLister;

import java.io.IOException;

public class Main extends ListActivity implements State {

    /**
     * Display layer
     **/
    private static NewsLister newslister;
    private static NewsView newsView;

    /**
     * Kernel layer
     **/
    private static NewsDataCenter datamanager;
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

        newslister = new NewsLister(this, new NewsList());
        newslister.setNotifyOnChange(true);
        setListAdapter(newslister);

        datamanager = new NewsDataCenter(this, handler);
        sites = datamanager.getSites();

        newsView = new NewsView(this, datamanager, handler);

        displaySiteNews(datamanager.getSettings().main_site_i, null);

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
                sectionlister.addAll(sites.get(currentSite).getSections());
            }

        });

        drawerSectionList = (ListView) findViewById(R.id.section_drawer);
        drawerSectionList.setAdapter(new SectionLister(this, new SectionList()));
        ((SectionLister) drawerSectionList.getAdapter()).addAll(sites.get(currentSite).getSections());
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
        closeNews();
        currentSite = siteposition;
        newslister.clear();

        Site csite = sites.get(currentSite);
        datamanager.getNews(csite, sections);
        setTitle(csite.name);
        try {
            ActionBar actionBar = getActionBar();
            actionBar.setBackgroundDrawable(csite.color);
            actionBar.setIcon(Drawable.createFromStream(getAssets().open(csite.name + ".png"), null));
        } catch (IOException e) {
            debug("Error setting Site Icon in the Bar");
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
  */          default:
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

    private final Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case NEWS_READ:
                    newslister.add((News) msg.obj);
                    break;
                case SECTION_BEGIN:
                    newslister.add(new News((String) msg.obj, null, null, null, null));
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

    private void debug(String text) {
        Log.d("##MAIN##", "[" + this.toString() + "] " + text);
    }

}