package com.lucevent.newsup.view.adapter.viewholder;

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
    private TextView name, nAccesses, last, ip;

    public StatisticsViewHolder(View v)
    {
        super(v);

        icon = (ImageView) v.findViewById(R.id.icon);
        name = (TextView) v.findViewById(R.id.name);
        nAccesses = (TextView) v.findViewById(R.id.nAccesses);
        last = (TextView) v.findViewById(R.id.last);
        ip = (TextView) v.findViewById(R.id.ip);
    }

    public static void populateViewHolder(StatisticsViewHolder holder, SiteStat siteStat)
    {
        holder.icon.setBackground(LogoManager.getLogo(siteStat.siteCode, LogoManager.Size.I_ITEM));
        holder.name.setText(siteStat.siteName);
        holder.nAccesses.setText("#" + siteStat.nAccesses);
        holder.last.setText(Date.getAge(siteStat.lastAccess));
        holder.ip.setText(siteStat.lastIp);
    }

}
