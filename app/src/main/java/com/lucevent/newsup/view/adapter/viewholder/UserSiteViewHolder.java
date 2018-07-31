package com.lucevent.newsup.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.UserSite;

public class UserSiteViewHolder extends RecyclerView.ViewHolder {

	private static final RequestOptions OPTIONS = new RequestOptions().fitCenter();

	private ImageView mImage;
	private TextView mName, mUrl;

	public UserSiteViewHolder(View v)
	{
		super(v);
		mImage = (ImageView) v.findViewById(R.id.icon);
		mName = (TextView) v.findViewById(R.id.name);
		mUrl = (TextView) v.findViewById(R.id.url);
	}

	public void bind(UserSite site)
	{
		mImage.setImageResource(R.mipmap.ic_launcher);
		if (site.icon != null && !site.icon.isEmpty()) {
			Glide.with(mImage.getContext())
					.applyDefaultRequestOptions(OPTIONS)
					.load(site.icon)
					.into(mImage);
		}

		mName.setText(site.name);
		mUrl.setText(site.url);

		itemView.setTag(site);
	}

}
