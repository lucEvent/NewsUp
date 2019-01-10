package com.lucevent.newsup.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lucevent.newsup.R;

public abstract class CategorizedSiteRowViewHolder extends RecyclerView.ViewHolder {

	private TextView mTitle;

	public CategorizedSiteRowViewHolder(View v)
	{
		super(v);
		mTitle = v.findViewById(R.id.group_title);
	}

	public void bind(String title)
	{
		mTitle.setText(title);
	}

}
