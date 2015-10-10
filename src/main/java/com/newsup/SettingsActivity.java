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

    private int siteSelectedpos = -9999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_settings);

        data = new NewsDataCenter(this, null);
        sites = data.getSites();
        content = (FrameLayout) findViewById(R.id.content);
        tabtitle = (TextView) findViewById(R.id.tabtitle);

        setSitesTabBar();

        displayTab(0, -1);
    }

    private void setSitesTabBar() {
        debug("Num sites:" + sites.getNumSites());
        tabs = new ImageButton[sites.getNumSites() + 1];
        tabs[0] = (ImageButton) findViewById(R.id.icon0);

        tabs[1] = (ImageButton) findViewById(R.id.icon1);
        tabs[2] = (ImageButton) findViewById(R.id.icon2);
        tabs[3] = (ImageButton) findViewById(R.id.icon3);
        tabs[4] = (ImageButton) findViewById(R.id.icon4);
        tabs[5] = (ImageButton) findViewById(R.id.icon5);

        tabs[6] = (ImageButton) findViewById(R.id.icon6);

        tabs[7] = (ImageButton) findViewById(R.id.icon7);
        tabs[8] = (ImageButton) findViewById(R.id.icon8);
        tabs[9] = (ImageButton) findViewById(R.id.icon9);

        tabs[10] = (ImageButton) findViewById(R.id.icon10);
        tabs[11] = (ImageButton) findViewById(R.id.icon11);
        tabs[12] = (ImageButton) findViewById(R.id.icon12);
        tabs[13] = (ImageButton) findViewById(R.id.icon13);

        tabs[14] = (ImageButton) findViewById(R.id.icon14);
        tabs[15] = (ImageButton) findViewById(R.id.icon15);
        tabs[16] = (ImageButton) findViewById(R.id.icon16);
        tabs[17] = (ImageButton) findViewById(R.id.icon17);
        tabs[18] = (ImageButton) findViewById(R.id.icon18);
        tabs[19] = (ImageButton) findViewById(R.id.icon19);
        tabs[20] = (ImageButton) findViewById(R.id.icon20);
        tabs[21] = (ImageButton) findViewById(R.id.icon21);
        tabs[22] = (ImageButton) findViewById(R.id.icon22);
        tabs[23] = (ImageButton) findViewById(R.id.icon23);
        tabs[24] = (ImageButton) findViewById(R.id.icon24);
        tabs[25] = (ImageButton) findViewById(R.id.icon25);
 /*       tabs[26] = (ImageButton) findViewById(R.id.icon26);
        tabs[27] = (ImageButton) findViewById(R.id.icon27);
        tabs[28] = (ImageButton) findViewById(R.id.icon28);
        tabs[29] = (ImageButton) findViewById(R.id.icon29);
*/
        try {
            tabs[0].setImageDrawable(Drawable.createFromStream(getAssets().open("home.png"), null));
            tabs[0].setId(0);
            tabs[0].setTag(0);
            int tab = 1;
            for (int sitepos = 0; sitepos < sites.size(); ++sitepos) {
                Site site = sites.get(sitepos);
                if (site.code != -1) {
                    debug(tab + " Settings up " + site.name);
                    tabs[tab].setImageDrawable(Drawable.createFromStream(getAssets().open(site.name + ".png"), null));
                    tabs[tab].setId(tab);
                    tabs[tab].setTag(sitepos);
                    tab++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayTab(View v) {
        displayTab(v.getId(), (Integer) v.getTag());
    }

    private void displayTab(int tab, int siteSelectedpos) {
        if (this.siteSelectedpos == siteSelectedpos) {
            return;
        }
        for (ImageButton icon : tabs) {
            icon.setSelected(false);
        }
        tabs[tab].setSelected(true);

        View view;
        if (tab == 0) {
            view = getLayoutInflater().inflate(R.layout.f_set_home, content, false);

            setMainSiteLogo(view, AppSettings.main_site_i);
            tabtitle.setText("Configuration Main page");
        } else {
            view = getLayoutInflater().inflate(R.layout.f_set_i, content, false);
            tabtitle.setText("Configuration " + sites.get(siteSelectedpos).name);
        }

        this.siteSelectedpos = siteSelectedpos;
        content.removeAllViews();
        content.addView(view);
    }

    public void selectMainSite(View view) {
        new SitePicker(this, sites, handler).show();
    }

    private boolean selectSectionsOnMainPageWaiter;

    public void selectSectionsOnMainPage(View view) {
        SiteSettings ssettings = data.getSettingsOf(sites.get(siteSelectedpos));
        Boolean[] bsections = Arrays.copyOfRange(ssettings.sectionsOnMain, 0, ssettings.sectionsOnMain.length);
        new SectionPicker(this, sites.get(siteSelectedpos).getSections(), bsections, handler).show();
        selectSectionsOnMainPageWaiter = true;
    }

    public void selectSectionsToSave(View view) {
        SiteSettings ssettings = data.getSettingsOf(sites.get(siteSelectedpos));
        Boolean[] bsections = Arrays.copyOfRange(ssettings.sectionsToSave, 0, ssettings.sectionsToSave.length);
        new SectionPicker(this, sites.get(siteSelectedpos).getSections(), bsections, handler).show();
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
                    Site site = sites.get(siteSelectedpos);
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