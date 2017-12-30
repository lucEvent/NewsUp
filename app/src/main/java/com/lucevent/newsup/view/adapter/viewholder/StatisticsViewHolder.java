package com.lucevent.newsup.view.adapter.viewholder;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.Date;
import com.lucevent.newsup.io.LogoManager;
import com.lucevent.newsup.kernel.stats.SiteStat;

public class StatisticsViewHolder extends RecyclerView.ViewHolder {

    private ImageView icon;
    private TextView name, requests, readings, last, version;

    public StatisticsViewHolder(View v)
    {
        super(v);

        icon = (ImageView) v.findViewById(R.id.icon);
        name = (TextView) v.findViewById(R.id.name);
        requests = (TextView) v.findViewById(R.id.requests);
        readings = (TextView) v.findViewById(R.id.readings);
        last = (TextView) v.findViewById(R.id.last);
        version = (TextView) v.findViewById(R.id.version);
    }

    @SuppressLint("SetTextI18n")
    public void bind(SiteStat siteStat)
    {
        icon.setBackground(LogoManager.getLogo(siteStat.siteCode, LogoManager.Size.I_ITEM));
        name.setText(siteStat.siteName);
        requests.setText(siteStat.totalRequests + "/" + siteStat.totalRequests);
        readings.setText(Integer.toString(siteStat.readings));
        last.setText(Date.getAge(siteStat.lastRequest));
        version.setText(siteStat.version);
    }

}
