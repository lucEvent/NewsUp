package com.lucevent.newsup.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.Date;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.io.LogoManager;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    private View container, logo;
    private TextView title, description, date;

    public NewsViewHolder(View v)
    {
        super(v);

        title = (TextView) v.findViewById(R.id.title);
        description = (TextView) v.findViewById(R.id.description);
        date = (TextView) v.findViewById(R.id.date);

        logo = v.findViewById(R.id.logo);

        container = v;
    }

    public static void populateViewHolder(NewsViewHolder holder, News news, boolean showSiteLogo)
    {
        if (showSiteLogo) {
            holder.logo.setVisibility(View.VISIBLE);
            holder.logo.setBackground(LogoManager.getLogo(news.site_code, LogoManager.Size.I_ITEM));
        } else
            holder.logo.setVisibility(View.GONE);

        holder.title.setText(news.title);
        holder.description.setText(news.description);
        holder.date.setText(Date.getAge(news.date));

        holder.container.setTag(news);
    }

}
