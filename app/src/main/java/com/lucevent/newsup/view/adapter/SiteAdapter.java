package com.lucevent.newsup.view.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

public class SiteAdapter implements View.OnTouchListener {

    public enum Order {
        BY_NAME, BY_COUNTRY, BY_LANGUAGE, BY_CATEGORY
    }

    private HashMap<Integer, View> viewMap;

    private String[] titles_languages, titles_countries, titles_types;

    public SiteAdapter(Context context)
    {
        titles_languages = context.getResources().getStringArray(R.array.site_languages);
        titles_countries = context.getResources().getStringArray(R.array.site_countries);
        titles_types = context.getResources().getStringArray(R.array.site_categories);
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
                titles = titles_languages;
                shift = SiteLanguage.shift;
                siteMap = new SitesMap(AppData.sites, SitesMap.SITE_COMPARATOR_BY_LANGUAGE, filter);
                break;
            case BY_COUNTRY:
                titles = titles_countries;
                shift = SiteCountry.shift;
                siteMap = new SitesMap(AppData.sites, SitesMap.SITE_COMPARATOR_BY_COUNTRY, filter);
                break;
            case BY_CATEGORY:
                titles = titles_types;
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
                v.setOnTouchListener(this);
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

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                showSiteDataDialog(v.getContext(), (Integer) v.getTag());

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:

                dismissDialog();

                break;
        }

        return false;
    }

    private View dialogView;
    private AlertDialog dialog;

    private void showSiteDataDialog(Context context, int site_code)
    {
        if (dialogView == null)
            dialogView = LayoutInflater.from(context).inflate(R.layout.d_site_info, null);
        else ((ViewGroup) dialogView.getParent()).removeAllViews();

        Site site = AppData.getSiteByCode(site_code);

        int barColor = site.color == 0xffffffff ? 0xffcccccc : site.color;
        dialogView.findViewById(R.id.color_top_bar).setBackgroundColor(barColor);
        ((ImageView) dialogView.findViewById(R.id.site_logo)).setImageDrawable(LogoManager.getLogo(site_code, LogoManager.Size.SELECT_SCREEN));

        int icountry = site.getCountry() - 1;
        ((TextView) dialogView.findViewById(R.id.country)).setText(context.getString(R.string.x_country, icountry >= 0 ? titles_countries[icountry] : ""));
        ((TextView) dialogView.findViewById(R.id.language)).setText(context.getString(R.string.x_language, titles_languages[site.getLanguage() - 1]));
        ((TextView) dialogView.findViewById(R.id.type)).setText(context.getString(R.string.x_category, titles_types[site.getCategory() - 1]));

        dialog = new AlertDialog.Builder(context)
                .setView(dialogView)
                .create();
        dialog.show();
    }

    private void dismissDialog()
    {
        dialog.dismiss();
    }

}
