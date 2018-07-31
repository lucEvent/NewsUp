package com.lucevent.newsup.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.io.LogoManager;

public class SiteListViewHolder extends RecyclerView.ViewHolder {

	private ImageView mIcon;
	private TextView mName;

	public SiteListViewHolder(View v)
	{
		super(v);
		mIcon = (ImageView) v.findViewById(R.id.icon);
		mName = (TextView) v.findViewById(R.id.name);
	}

	public void bind(Site site)
	{
		mIcon.setImageDrawable(LogoManager.getLogo(site.code, LogoManager.Size.ACTION_BAR));
		mName.setText(site.name);
		itemView.setTag(site.code);
	}

}
