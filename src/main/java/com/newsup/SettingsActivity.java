package com.newsup;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.newsup.dialog.DialogState;
import com.newsup.dialog.SectionPicker;
import com.newsup.dialog.SitePicker;
import com.newsup.kernel.NewsDataCenter;
import com.newsup.kernel.Site;
import com.newsup.kernel.list.SiteList;
import com.newsup.settings.AppSettings;
import com.newsup.settings.SiteSettings;

import java.io.IOException;
import java.util.Arrays;

public final class SettingsActivity extends Activity implements DialogState {

    private NewsDataCenter data;
    private SiteList sites;
    private FrameLayout content;
    private TextView tabtitle;
    private ImageButton[] tabs;

    private int currentTab = -999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_settings);

        data = new NewsDataCenter(this, null);
        sites = data.getSites();
        content = (FrameLayout) findViewById(R.id.content);
        tabtitle = (TextView) findViewById(R.id.tabtitle);

        setSitesTabBar();

        displayTab(-1);
    }

    private void setSitesTabBar() {
        tabs = new ImageButton[sites.size() + 1];
        tabs[0] = (ImageButton) findViewById(R.id.icon_main);
        tabs[1] = (ImageButton) findViewById(R.id.icon_elpais);
        tabs[2] = (ImageButton) findViewById(R.id.icon_as);
        tabs[3] = (ImageButton) findViewById(R.id.icon_20m);
        tabs[4] = (ImageButton) findViewById(R.id.icon_sport);
        tabs[5] = (ImageButton) findViewById(R.id.icon_hs);
        tabs[6] = (ImageButton) findViewById(R.id.icon_svd);
        tabs[7] = (ImageButton) findViewById(R.id.icon_em);
        tabs[8] = (ImageButton) findViewById(R.id.icon_hp);
        tabs[9] = (ImageButton) findViewById(R.id.icon_il);
        tabs[10] = (ImageButton) findViewById(R.id.icon_dt);
        tabs[11] = (ImageButton) findViewById(R.id.icon_tl);
        tabs[12] = (ImageButton) findViewById(R.id.icon_lh);
        tabs[13] = (ImageButton) findViewById(R.id.icon_x);
        tabs[14] = (ImageButton) findViewById(R.id.icon_eal);
        tabs[15] = (ImageButton) findViewById(R.id.icon_ht);
        tabs[16] = (ImageButton) findViewById(R.id.icon_ted);
        tabs[17] = (ImageButton) findViewById(R.id.icon_cnn);
        tabs[18] = (ImageButton) findViewById(R.id.icon_bcc);

        try {
            tabs[0].setImageDrawable(Drawable.createFromStream(getAssets().open("home.png"), null));
            for (int i = 0; i < sites.size(); ++i) {
                tabs[i + 1].setImageDrawable(Drawable.createFromStream(getAssets().open(sites.get(i).name + ".png"), null));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayTab(View v) {
        displayTab(Integer.parseInt((String) v.getTag()));
    }

    private void displayTab(int tab) {
        if (tab == currentTab) {
            return;
        }
        for (ImageButton icon : tabs) {
            icon.setSelected(false);
        }
        tabs[tab + 1].setSelected(true);

        View view;
        if (tab == -1) {
            view = getLayoutInflater().inflate(R.layout.f_set_home, content, false);

            setMainSiteLogo(view, AppSettings.main_site_i);
            tabtitle.setText("Configuration Main page");
        } else {
            view = getLayoutInflater().inflate(R.layout.f_set_i, content, false);
            tabtitle.setText("Configuration " + sites.get(tab).name);
        }

        currentTab = tab;
        content.removeAllViews();
        content.addView(view);
    }

    public void selectMainSite(View view) {
        new SitePicker(this, sites, handler).show();
    }

    private boolean selectSectionsOnMainPageWaiter;

    public void selectSectionsOnMainPage(View view) {
        SiteSettings ssettings = data.getSettingsOf(sites.get(currentTab));
        Boolean[] bsections = Arrays.copyOfRange(ssettings.sectionsOnMain, 0, ssettings.sectionsOnMain.length);
        new SectionPicker(this, sites.get(currentTab).getSections(), bsections, handler).show();
        selectSectionsOnMainPageWaiter = true;
    }

    public void selectSectionsToSave(View view) {
        SiteSettings ssettings = data.getSettingsOf(sites.get(currentTab));
        Boolean[] bsections = Arrays.copyOfRange(ssettings.sectionsToSave, 0, ssettings.sectionsToSave.length);
        new SectionPicker(this, sites.get(currentTab).getSections(), bsections, handler).show();
        selectSectionsOnMainPageWaiter = false;
    }

    private final Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SITE_PICKED:
                    setMainSiteLogo(content, (Integer) msg.obj);
                    data.setSettingsWith(AppSettings.MAIN_SITE_I, (Integer) msg.obj);
                    break;
                case SECTIONS_PICKED:
                    Site site = sites.get(currentTab);
                    Boolean[] schosen = (Boolean[]) msg.obj;
                    if (selectSectionsOnMainPageWaiter) {
                        site.settings.sectionsOnMain = schosen;
                    } else {
                        site.settings.sectionsToSave = schosen;
                    }
                    data.setSettingsOf(site);
                    break;
                default:
                    debug("[][#] OPCION NO CONTEMPLADA");
            }
        }

    };

    private void setMainSiteLogo(View view, int sitei) {
        try {
            Drawable logo = Drawable.createFromStream(getAssets().open(sites.get(sitei).name + ".png"), null);
            ((Button) view.findViewById(R.id.main_site_logo)).setCompoundDrawablesWithIntrinsicBounds(null, null, logo, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void debug(String text) {
        Log.d("##SettingsActivity##", text);
    }


}