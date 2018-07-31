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

	private ImageView mIcon;
	private TextView mName, mRequests, mReadings, mLast, mVersion;

	public StatisticsViewHolder(View v)
	{
		super(v);

		mIcon = (ImageView) v.findViewById(R.id.icon);
		mName = (TextView) v.findViewById(R.id.name);
		mRequests = (TextView) v.findViewById(R.id.requests);
		mReadings = (TextView) v.findViewById(R.id.readings);
		mLast = (TextView) v.findViewById(R.id.last);
		mVersion = (TextView) v.findViewById(R.id.version);
	}

	@SuppressLint("SetTextI18n")
	public void bind(SiteStat siteStat)
	{
		mIcon.setBackground(LogoManager.getLogo(siteStat.siteCode, LogoManager.Size.I_ITEM));
		mName.setText(siteStat.siteName);
		mRequests.setText(siteStat.monthRequests + "/" + siteStat.totalRequests);
		mReadings.setText(Integer.toString(siteStat.readings));
		mLast.setText(Date.getAge(siteStat.lastRequest));
		mVersion.setText(siteStat.version);
	}

}
