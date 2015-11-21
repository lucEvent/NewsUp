package com.newsup;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.newsup.dialog.SiteConfiguration;
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
    private static SiteList sites;
    private static Site currentSite;
    private static boolean displayingNews;

    // *****///
    private static DrawerLayout mDrawerLayout;
    private static ListView drawerSiteList;
    private static ListView drawerSectionList;
    private static ActionBarManager actionBar;

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
        Log.d("##MAIN##", "[Main] " + text);
    }

    class ActionBarManager implements View.OnClickListener, View.OnLongClickListener {

        private android.app.ActionBar actionBar;

        private View bar;
        private TextView title;
        private ImageButton fav, set;

        ActionBarManager() {
            actionBar = getActionBar();
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);

            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.f_main_actionbar, null);

            bar = view.findViewById(R.id.action_bar);
            title = (TextView) view.findViewById(R.id.title);
            fav = (ImageButton) view.findViewById(R.id.action_fav);
            set = (ImageButton) view.findViewById(R.id.action_settings);

            fav.setOnClickListener(this);
            fav.setOnLongClickListener(this);

            set.setOnClickListener(this);
            set.setOnLongClickListener(this);

            actionBar.setCustomView(view);
        }

        void update() {
            Site site = currentSite;
            if (site == null) {
                title.setText(R.string.mycustomfeed);
                fav.setVisibility(ImageView.INVISIBLE);
                set.setVisibility(ImageView.INVISIBLE);

                bar.setBackgroundColor(Site.MAIN_COLOR);
            } else {
                title.setText(site.name);
                fav.setVisibility(ImageView.VISIBLE);
                set.setVisibility(ImageView.VISIBLE);

                bar.setBackgroundColor(site.color);

                boolean isfav = datamanager.isFavorite(site);
                boolean isDark = (Color.red(site.color) < 0x7F) && (Color.green(site.color) < 0x7F) && (Color.blue(site.color) < 0x7F);

                if (isDark) {
                    set.setImageResource(R.drawable.ic_settings_white);
                    fav.setImageResource(isfav ? R.drawable.ic_star_white : R.drawable.ic_star_border_white);
                    title.setTextColor(Color.WHITE);
                } else {
                    set.setImageResource(R.drawable.ic_settings);
                    fav.setImageResource(isfav ? R.drawable.ic_star : R.drawable.ic_star_border);
                    title.setTextColor(Color.BLACK);
                }
//                actionBar.setIcon(site.icon);
            }
        }

        public void show() {
            actionBar.show();
        }

        public void hide() {
            actionBar.hide();
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.action_fav) {
                datamanager.toggleFavorite(currentSite);
                update();
                ((SiteLister) drawerSiteList.getAdapter()).resetMain();
            } else if (v.getId() == R.id.action_settings) {
                new SiteConfiguration(Main.this, datamanager).set(currentSite);
            }
        }

        @Override
        public boolean onLongClick(View v) {
            int message = 0;
            if (v.getId() == R.id.action_fav) message = R.string.favorite;
            else if (v.getId() == R.id.action_settings) message = R.string.settings;

            Toast.makeText(Main.this, message, Toast.LENGTH_SHORT).show();
            return true;
        }
    }

}