package com.newsup.lister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.newsup.R;
import com.newsup.kernel.NewsDataCenter;
import com.newsup.kernel.basic.Site;
import com.newsup.kernel.set.SiteList;

public class SiteLister extends ArrayAdapter<Site> {
    private View[] views;

    private final boolean showMenu;
    private SiteList sites;
    private NewsDataCenter dataCenter;

    private LayoutInflater inflater;

    public SiteLister(Context context, NewsDataCenter dataCenter, boolean showMenu) {
        super(context, -1, new SiteList());
        this.showMenu = showMenu;
        this.sites = dataCenter.getSites();
        this.dataCenter = dataCenter;
        this.views = new View[showMenu ? sites.size() + 1 : sites.size()];
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public SiteLister(Context context, SiteList values) {
        super(context, -1, values);
        this.showMenu = false;
        this.sites = values;
        this.views = new View[sites.size()];
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (views[position] != null) return views[position];

        if (showMenu) {
            if (position == 0) {
                views[position] = getMenuView(parent);
                return views[position];
            }
        }

        Site site = sites.get(showMenu ? position - 1 : position);
        if (site.code == -1) {
            view = inflater.inflate(R.layout.i_site_header, parent, false);
            ((TextView) view.findViewById(R.id.name)).setText(site.name);
            view.setOnClickListener(null);
        } else {
            view = inflater.inflate(R.layout.i_site, parent, false);
            ((TextView) view.findViewById(R.id.name)).setText(site.name);
            view.findViewById(R.id.logo).setBackgroundDrawable(site.icon.getConstantState().newDrawable());
            view.setTag(site);
        }

        views[position] = view;
        return views[position];
    }

    private View getMenuView(ViewGroup parent) {
        View view = inflater.inflate(R.layout.i_drawer_main, parent, false);
        view.setOnClickListener(null);

        LinearLayout favscontent = (LinearLayout) view.findViewById(R.id.content_favs);
        SiteList favs = dataCenter.getFavoritesSites();
        if (!favs.isEmpty()) {
            for (Site fav : favs) {
                View favview = inflater.inflate(R.layout.i_site, parent, false);
                ((TextView) favview.findViewById(R.id.name)).setText(fav.name);
                favview.findViewById(R.id.logo).setBackgroundDrawable(fav.icon.getConstantState().newDrawable());
                favview.setTag(fav);
                favscontent.addView(favview);
            }
        } else {
            favscontent.setVisibility(LinearLayout.GONE);
        }
        return view;
    }

    @Override
    public int getCount() {
        return showMenu ? sites.size() + 1 : sites.size();
    }

    public void resetMain() {
        views[0] = null;
        notifyDataSetChanged();
    }

    private void debug(String text) {
        android.util.Log.d("##SiteLister##", text);
    }

}
