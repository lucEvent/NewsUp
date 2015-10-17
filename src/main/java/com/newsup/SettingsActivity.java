package com.newsup;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.newsup.dialog.DialogState;
import com.newsup.dialog.SiteConfiguration;
import com.newsup.dialog.SitePicker;
import com.newsup.kernel.NewsDataCenter;
import com.newsup.kernel.Site;
import com.newsup.kernel.list.SiteList;
import com.newsup.lister.SiteLister;
import com.newsup.settings.AppSettings;

import java.util.Comparator;
import java.util.TreeSet;

public final class SettingsActivity extends ListActivity implements DialogState {

    private NewsDataCenter data;
    private SiteList sites;

    private SettingsTabManager tabManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_settings);

        data = new NewsDataCenter(this);

        sites = filterSites();

        tabManager = new SettingsTabManager();

        onConfigureMyFeed(null);
    }

    private SiteList filterSites() {
        TreeSet<Site> orderedList = new TreeSet<Site>(new Comparator<Site>() {
            @Override
            public int compare(Site s1, Site s2) {
                return s1.name.compareTo(s2.name);
            }
        });
        for (Site site : data.getSites()) {
            if (site.code >= 0) {
                orderedList.add(site);
            }
        }
        return new SiteList(orderedList);
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
        new SitePicker(SettingsActivity.this, data);
    }

    public void onConfigureSaveTime(View view) {
        //TODO new TimePicker(SettingsActivity.this, XXXtimeXXX, handler).show();
    }

    private class SettingsMyFeedManager {

        private View content;
        private TextView lab_sitesTitle, lab_sitesSubtitle, lab_saveTime;

        SettingsMyFeedManager() {
            content = findViewById(R.id.content_myfeed);
            lab_sitesTitle = (TextView) content.findViewById(R.id.conf_label_sites_title);//Sites to show: 1 Site
            lab_sitesSubtitle = (TextView) content.findViewById(R.id.conf_label_sites_subtitle);//El Pais, As,...
            lab_saveTime = (TextView) content.findViewById(R.id.conf_label_save_time);//Never
            setUp();
        }

        public void setUp() {
            AppSettings settings = data.getSettings();
            lab_sitesTitle.setText("Sites to show: " + settings.main_sites_i.length + " Sites");

            SiteList sites = data.getSites();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < settings.main_sites_i.length; ++i) {
                if (i != 0) sb.append(", ");
                sb.append(sites.get(settings.main_sites_i[i]).name);
            }
            lab_sitesSubtitle.setText(sb.toString());
            //lab_saveTime TODO
        }


        public void setVisibility(int visibility) {
            content.setVisibility(visibility);
        }
    }

    private class SettingsSitesManager {

        private View content;

        SettingsSitesManager() {
            content = getListView();
            setListAdapter(new SiteLister(SettingsActivity.this, sites));
        }

        public void setVisibility(int visibility) {
            content.setVisibility(visibility);
        }

    }

    private SiteConfiguration siteConfigurationManager;

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Site site = sites.get(position);
        debug("Se ha seleccionado: " + site.name);
        if (siteConfigurationManager == null) {
            siteConfigurationManager = new SiteConfiguration(this, data);
        }
        siteConfigurationManager.set(site);
    }


    private void debug(String text) {
        Log.d("##SettingsActivity##", text);
    }

}