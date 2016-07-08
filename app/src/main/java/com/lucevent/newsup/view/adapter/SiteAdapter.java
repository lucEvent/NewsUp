package com.lucevent.newsup.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.SitesMap;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.data.util.SiteCategory;
import com.lucevent.newsup.data.util.SiteCountry;
import com.lucevent.newsup.data.util.SiteLanguage;
import com.lucevent.newsup.io.LogoManager;
import com.lucevent.newsup.kernel.AppData;

import java.util.HashMap;

public class SiteAdapter {

    public enum Order {
        BY_NAME, BY_COUNTRY, BY_LANGUAGE, BY_CATEGORY
    }

    private HashMap<Integer, View> viewMap;

    public SiteAdapter()
    {
    }

    public HashMap<Integer, View> createView(Context context, ViewGroup parent, int rowSize, Order order, String filter)
    {
        String[] titles;
        int shift;
        SitesMap siteMap;

        switch (order) {
            case BY_NAME:
                titles = new String[]{""};
                shift = 24;
                siteMap = new SitesMap(AppData.sites, SitesMap.SITE_COMPARATOR_BY_NAME, filter);
                break;
            case BY_LANGUAGE:
                titles = context.getResources().getStringArray(R.array.site_languages);
                shift = SiteLanguage.shift;
                siteMap = new SitesMap(AppData.sites, SitesMap.SITE_COMPARATOR_BY_LANGUAGE, filter);
                break;
            case BY_COUNTRY:
                titles = context.getResources().getStringArray(R.array.site_countries);
                shift = SiteCountry.shift;
                siteMap = new SitesMap(AppData.sites, SitesMap.SITE_COMPARATOR_BY_COUNTRY, filter);
                break;
            case BY_CATEGORY:
                titles = context.getResources().getStringArray(R.array.site_categories);
                shift = SiteCategory.shift;
                siteMap = new SitesMap(AppData.sites, SitesMap.SITE_COMPARATOR_BY_CATEGORY, filter);
                break;
            default:
                AppSettings.printerror("Wrong order", null);
                return null;
        }

        LayoutInflater inflater = LayoutInflater.from(context);

        TableRow row = null;
        int number = 0;
        if (viewMap == null) {
            viewMap = new HashMap<>(AppData.sites.size());
            for (Site s : AppData.sites) {

                if (number % rowSize == 0) {
                    row = new TableRow(context);

                }

                View v = inflater.inflate(R.layout.i_site, row, false);
                v.setTag(s.code);
                ((TextView) v.findViewById(R.id.site_name)).setText(s.name);
                ((ImageView) v.findViewById(R.id.site_icon)).setImageDrawable(LogoManager.getLogo(s.code, LogoManager.Size.SELECT_SCREEN));

                viewMap.put(s.code, v);
            }
        }

        parent.removeAllViews();
        TableLayout table = null;
        number = 0;
        int lasttype = -1;

        for (Site s : siteMap) {
            int type = ((s.info >> shift) & 0xFF);
            if (type == 0) continue;
            if (lasttype != type) {  //First so init title and new table
                lasttype = type;

                View site_group = inflater.inflate(R.layout.i_site_group, parent, false);
                ((TextView) site_group.findViewById(R.id.group_title)).setText(titles[lasttype - 1]);
                table = (TableLayout) site_group.findViewById(R.id.site_table);
                parent.addView(site_group);

                while (number % rowSize != 0) {
                    View v = inflater.inflate(R.layout.i_site, row, false);
                    v.setClickable(false);
                    row.addView(v);
                    number++;
                }

            }

            if (number % rowSize == 0) {
                row = new TableRow(context);
                table.addView(row);
            }
            View v = viewMap.get(s.code);
            if (v.getParent() != null) {
                ((TableRow) v.getParent()).removeView(v);
            }
            row.addView(v);
            number++;

        }

        while (number % rowSize != 0) {
            View v = inflater.inflate(R.layout.i_site, row, false);
            v.setClickable(false);
            row.addView(v);
            number++;
        }
        return viewMap;
    }

}
