package com.newsup.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.newsup.R;
import com.newsup.kernel.News;
import com.newsup.kernel.Site;
import com.newsup.kernel.list.SiteList;

import java.util.List;

public class SitePickerLister extends ArrayAdapter<Site> {

    private LayoutInflater inflater;

    public SitePickerLister(Context context, SiteList values) {
        super(context, R.layout.i_picker_site, values);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = inflater.inflate(R.layout.i_picker_site, parent, false);
        }
        TextView tview = (TextView) view;

        Site site = getItem(position);
        tview.setText(site.name);
        try {
            Drawable logo = Drawable.createFromStream(getContext().getAssets().open(site.name + ".png"), null);
            tview.setCompoundDrawablesWithIntrinsicBounds(logo, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tview;
    }
}