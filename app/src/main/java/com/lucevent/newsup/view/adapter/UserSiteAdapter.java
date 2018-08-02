package com.lucevent.newsup.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.UserSite;
import com.lucevent.newsup.view.adapter.viewholder.UserSiteViewHolder;

import java.util.ArrayList;
import java.util.Collection;

public class UserSiteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private final View.OnClickListener mOnClick;

	private final ArrayList<UserSite> mDataSet;

	public UserSiteAdapter(View.OnClickListener onClick)
	{
		mOnClick = onClick;
		mDataSet = new ArrayList<>(1);
	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_user_site, parent, false);
		v.setOnClickListener(mOnClick);
		return new UserSiteViewHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
	{
		((UserSiteViewHolder) holder).bind(mDataSet.get(position));
	}

	@Override
	public int getItemCount()
	{
		return mDataSet.size();
	}

	public void setNewDataSet(Collection<UserSite> newDataSet)
	{
		mDataSet.clear();
		mDataSet.addAll(newDataSet);
		notifyDataSetChanged();
	}

	public void clear()
	{
		mDataSet.clear();
		notifyDataSetChanged();
	}

}
