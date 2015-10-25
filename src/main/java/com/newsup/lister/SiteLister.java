package com.newsup.lister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.newsup.R;
import com.newsup.kernel.Site;
import com.newsup.kernel.list.SiteList;

public class SiteLister extends ArrayAdapter<Site> {

    private View[] views;

    private LayoutInflater inflater;

    public SiteLister(Context context, SiteList values) {
        super(context, -1, values);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.views = new View[values.size()];
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (views[position] == null) {
            Site site = getItem(position);
            if (site.code == -1) {
                view = inflater.inflate(R.layout.i_site_header, parent, false);
                ((TextView) view.findViewById(R.id.name)).setText(site.name);
                view.setOnClickListener(null);
            } else {
                view = inflater.inflate(R.layout.i_site, parent, false);

                ((TextView) view.findViewById(R.id.name)).setText(site.name);

                view.findViewById(R.id.logo).setBackgroundDrawable(site.icon.getConstantState().newDrawable());
            }
            view.findViewById(R.id.idcolor).setBackgroundDrawable(site.theme);
            views[position] = view;
        }
        return views[position];
    }

    private void debug(String text) {
        android.util.Log.d("##SiteLister##", text);
    }

}
