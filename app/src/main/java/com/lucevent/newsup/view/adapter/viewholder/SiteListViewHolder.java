package com.lucevent.newsup.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.io.LogoManager;

public class SiteListViewHolder extends RecyclerView.ViewHolder {

    private ImageView icon;
    private TextView name;

    public SiteListViewHolder(View v)
    {
        super(v);
        icon = (ImageView) v.findViewById(R.id.icon);
        name = (TextView) v.findViewById(R.id.name);
    }

    public void bind(Site site)
    {
        icon.setImageDrawable(LogoManager.getLogo(site.code, LogoManager.Size.ACTION_BAR));
        name.setText(site.name);
        itemView.setTag(site.code);
    }

}
