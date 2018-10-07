package com.lucevent.newsup.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.view.adapter.viewholder.SiteListViewHolder;

public class SiteListAdapter extends RecyclerView.Adapter<SiteListViewHolder> {

	private Sites mSites;
	private View.OnClickListener mOnItemClickListener;

	public SiteListAdapter(Sites sites, View.OnClickListener onItemClickListener)
	{
		mSites = sites;
		mOnItemClickListener = onItemClickListener;
	}

	@NonNull
	@Override
	public SiteListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_site_list, parent, false);
		v.setOnClickListener(mOnItemClickListener);
		return new SiteListViewHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull SiteListViewHolder holder, int position)
	{
		holder.bind(mSites.get(position));
	}

	@Override
	public int getItemCount()
	{
		return mSites.size();
	}

}
