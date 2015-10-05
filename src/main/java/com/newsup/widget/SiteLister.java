package com.newsup.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.newsup.R;
import com.newsup.kernel.Site;
import com.newsup.kernel.list.SiteList;

import java.io.IOException;

public class SiteLister extends ArrayAdapter<Site> {

    private LayoutInflater inflater;

    public SiteLister(Context context, SiteList values) {
        super(context, R.layout.i_news, values);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = inflater.inflate(R.layout.i_site, parent, false);
        }

        try {
            Site site = getItem(position);
            Drawable logo = Drawable.createFromStream(getContext().getAssets().open(site.name + ".png"), null);
            ((ImageView) view).setBackgroundDrawable(logo);

        } catch (IOException e) {
            debug("Error en SiteLister con posicion " + position);
            ((ImageView) view).setBackgroundResource(R.mipmap.ic_launcher);
        }

        return view;
    }

    private void debug(String text) {
        android.util.Log.d("##SiteLister##", text);
    }

}
