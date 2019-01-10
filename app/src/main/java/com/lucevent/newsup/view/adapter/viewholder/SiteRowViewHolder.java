package com.lucevent.newsup.view.adapter.viewholder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.io.LogoManager;

public class SiteRowViewHolder extends CategorizedSiteRowViewHolder {

	private TextView mLabel;
	private ImageView mLogoView;

	public SiteRowViewHolder(View v)
	{
		super(v);
		mLabel = v.findViewById(R.id.site_name);
		mLogoView = v.findViewById(R.id.site_icon);
	}

	public void bind(@NonNull Site site, boolean isSelected)
	{
		mLabel.setText(site.name);
		mLogoView.setImageDrawable(LogoManager.getLogo(site.code, LogoManager.Size.SELECT_SCREEN));

		itemView.setSelected(isSelected);
		itemView.setTag(site.code);
	}

}
