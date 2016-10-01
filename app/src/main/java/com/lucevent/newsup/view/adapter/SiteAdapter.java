package com.lucevent.newsup.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.SitesMap;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.data.util.SiteCategory;
import com.lucevent.newsup.data.util.SiteCountry;
import com.lucevent.newsup.data.util.SiteLanguage;
import com.lucevent.newsup.io.LogoManager;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.view.util.SiteScatterMap;

import java.util.HashMap;

public class SiteAdapter {

    public enum Order {
        BY_NAME, BY_COUNTRY, BY_LANGUAGE, BY_CATEGORY
    }

    private HashMap<Integer, View> viewMap;

    private String[] titles_languages, titles_countries, titles_types;
    private final int[] language_weights, country_weights;

    public SiteAdapter(Context context)
    {
        titles_languages = context.getResources().getStringArray(R.array.site_languages);
        titles_countries = context.getResources().getStringArray(R.array.site_countries);
        titles_types = context.getResources().getStringArray(R.array.site_categories);
        language_weights = context.getResources().getIntArray(R.array.site_language_weights);
        country_weights = context.getResources().getIntArray(R.array.site_country_weights);
    }

    public HashMap<Integer, View> createView(Context context, ViewGroup parent, int rowSize, Order order, String filter)
    {
        String[] titles;
        int shift;
        SiteScatterMap siteScatterMap;

        switch (order) {
            case BY_NAME:
                titles = new String[]{""};
                shift = 24;
                siteScatterMap = new SiteScatterMap(AppData.sites, filter) {
                    @Override
                    public Integer comparableValueOf(Site s)
                    {
                        return 0;
                    }
                };
                break;
            default:
            case BY_LANGUAGE:
                titles = titles_languages;
                shift = SiteLanguage.shift;
                siteScatterMap = new SiteScatterMap(AppData.sites, filter) {
                    @Override
                    public Integer comparableValueOf(Site s)
                    {
                        return language_weights[s.getLanguage() - 1];
                    }
                };
                break;
            case BY_COUNTRY:
                titles = titles_countries;
                shift = SiteCountry.shift;
                siteScatterMap = new SiteScatterMap(AppData.sites, filter) {
                    @Override
                    public Integer comparableValueOf(Site s)
                    {
                        return country_weights[s.getCountry() - 1];
                    }
                };
                break;
            case BY_CATEGORY:
                titles = titles_types;
                shift = SiteCategory.shift;
                siteScatterMap = new SiteScatterMap(AppData.sites, filter) {
                    @Override
                    public Integer comparableValueOf(Site s)
                    {
                        return s.getCategory();
                    }
                };
                break;
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
        number = 0;

        for (Integer value : siteScatterMap.keySet()) {

            SitesMap sites = siteScatterMap.get(value);

            int type = ((sites.first().info >> shift) & 0xFF);

            View site_group = inflater.inflate(R.layout.i_site_group, parent, false);
            ((TextView) site_group.findViewById(R.id.group_title)).setText(titles[type - 1]);
            TableLayout table = (TableLayout) site_group.findViewById(R.id.site_table);
            parent.addView(site_group);

            for (Site s : sites) {

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
        }
        return viewMap;
    }

}
