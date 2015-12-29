package com.newsup;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.newsup.dialog.SiteConfiguration;
import com.newsup.dialog.SitePicker;
import com.newsup.kernel.NewsDataCenter;
import com.newsup.kernel.basic.Site;
import com.newsup.kernel.set.SiteList;
import com.newsup.lister.SiteLister;
import com.newsup.settings.AppSettings;
import com.newsup.settings.DownloadScheduleSetting;
import com.newsup.task.Socket;
import com.newsup.task.SocketMessage;

import java.text.DecimalFormat;

public final class SettingsActivity extends ListActivity implements Socket {

    private NewsDataCenter data;

    private SettingsTabManager tabManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_settings);

        data = new NewsDataCenter(this);
        tabManager = new SettingsTabManager();

        DownloadScheduleSetting.s_days = new String[]{getString(R.string.mon),
                getString(R.string.tue), getString(R.string.wed), getString(R.string.thu),
                getString(R.string.fri), getString(R.string.sat), getString(R.string.sun)};

        onConfigureMyFeed(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tabManager.setUp();
    }

    public void onConfigureMyFeed(View view) {
        tabManager.onSelectConfigureMyFeed();
    }

    public void onConfigureSites(View view) {
        tabManager.onSelectConfigureSites();
    }

    private class SettingsTabManager {

        private Button myfeed, confsites;
        SettingsMyFeedManager myFeedManager;
        SettingsSitesManager sitesManager;

        SettingsTabManager() {
            myfeed = (Button) findViewById(R.id.label_myfeed);
            confsites = (Button) findViewById(R.id.label_confsites);
            myFeedManager = new SettingsMyFeedManager();
            sitesManager = new SettingsSitesManager();
        }

        public void setUp() {
            myFeedManager.setUp();
//           sitesManager.setUp();
        }

        public void onSelectConfigureMyFeed() {
            myfeed.setSelected(true);
            confsites.setSelected(false);

            myFeedManager.setVisibility(View.VISIBLE);
            sitesManager.setVisibility(View.GONE);
        }

        public void onSelectConfigureSites() {
            myfeed.setSelected(false);
            confsites.setSelected(true);

            myFeedManager.setVisibility(View.GONE);
            sitesManager.setVisibility(View.VISIBLE);
        }

    }

    public void onConfigureSitesToShow(View view) {
        new SitePicker(SettingsActivity.this, AppSettings.MAIN_CODES, this);
    }

    public void onConfigureSaveTime(View view) {
        startActivity(new Intent(this, ScheduleDownloadActivity.class));
    }

    public void onConfigureCleanCache(View v) {
        data.cleanCache();
        tabManager.myFeedManager.setUpCacheSize();
    }

    private class SettingsMyFeedManager {

        private View content;
        private TextView lab_sitesTitle, lab_sitesSubtitle, lab_saveTime, lab_cleancache;

        SettingsMyFeedManager() {
            content = findViewById(R.id.content_myfeed);
            lab_sitesTitle = (TextView) content.findViewById(R.id.conf_label_sites_title);//Sites to show: 1 Site
            lab_sitesSubtitle = (TextView) content.findViewById(R.id.conf_label_sites_subtitle);//El Pais, As,...
            lab_saveTime = (TextView) content.findViewById(R.id.conf_label_save_time);//Never
            lab_cleancache = (TextView) content.findViewById(R.id.conf_label_cleancache);//10 MB
        }

        void setUp() {
            setUpMainSites();
            setUpSaveTime();
            setUpCacheSize();
        }

        void setUpMainSites() {
            lab_sitesTitle.setText(getString(R.string.mainsites) + AppSettings.MAIN_CODES.length);

            SiteList sites = data.getSites();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < AppSettings.MAIN_CODES.length; ++i) {
                if (i != 0) sb.append(", ");
                sb.append(sites.getSiteByCode(AppSettings.MAIN_CODES[i]).name);
            }
            lab_sitesSubtitle.setText(sb.toString());
        }

        void setUpSaveTime() {
            if (AppSettings.DL_SCHEDULES.isEmpty()) {
                lab_saveTime.setText(R.string.never);
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < AppSettings.DL_SCHEDULES.size(); ++i) {
                    if (i != 0) sb.append("\n");
                    sb.append(AppSettings.DL_SCHEDULES.get(i).toShortString());
                }
                lab_saveTime.setText(sb.toString());
            }
        }

        void setUpCacheSize() {
            double mbsize = data.getCacheSize() / 1048576.0;
            lab_cleancache.setText(new DecimalFormat("#0.00").format(mbsize) + " MB");
        }

        void setVisibility(int visibility) {
            content.setVisibility(visibility);
        }

    }

    private class SettingsSitesManager {

        private View content;

        SettingsSitesManager() {
            content = getListView();
            setListAdapter(new SiteLister(SettingsActivity.this, NewsDataCenter.getAppSites()));
        }

        void setVisibility(int visibility) {
            content.setVisibility(visibility);
        }

    }

    private SiteConfiguration siteConfigurationManager;

    public void onSiteSelectedAction(View view) {
        if (siteConfigurationManager == null) {
            siteConfigurationManager = new SiteConfiguration(this, data);
        }
        siteConfigurationManager.set((Site) view.getTag());
    }

    @Override
    public void message(int message, Object dataAttached) {
        if (message == SocketMessage.SELECTED_SITE_CODES) {
            data.setSetting(AppSettings.SET_MAIN_CODES, dataAttached);
            tabManager.myFeedManager.setUpMainSites();
        }
    }

    private void debug(String text) {
        Log.d("##SettingsActivity##", text);
    }

}