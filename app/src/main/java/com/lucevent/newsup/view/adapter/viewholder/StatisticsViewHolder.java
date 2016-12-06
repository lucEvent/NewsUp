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
    private TextView name, nAccesses, readings, last, version;

    public StatisticsViewHolder(View v)
    {
        super(v);

        icon = (ImageView) v.findViewById(R.id.icon);
        name = (TextView) v.findViewById(R.id.name);
        nAccesses = (TextView) v.findViewById(R.id.nAccesses);
        readings = (TextView) v.findViewById(R.id.readings);
        last = (TextView) v.findViewById(R.id.last);
        version = (TextView) v.findViewById(R.id.version);
    }

    @SuppressLint("SetTextI18n")
    public static void populateViewHolder(StatisticsViewHolder holder, SiteStat siteStat)
    {
        holder.icon.setBackground(LogoManager.getLogo(siteStat.siteCode, LogoManager.Size.I_ITEM));
        holder.name.setText(siteStat.siteName);
        holder.nAccesses.setText("#" + siteStat.nAccesses);
        holder.readings.setText("[" + siteStat.readings + "]");
        holder.last.setText(Date.getAge(siteStat.lastAccess));
        holder.version.setText(siteStat.version);
    }

}
