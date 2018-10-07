package com.lucevent.newsup.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucevent.newsup.R;
import com.lucevent.newsup.kernel.stats.Statistics;
import com.lucevent.newsup.view.adapter.viewholder.StatisticsViewHolder;

public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsViewHolder> {

	private Statistics mDataSet;

	public StatisticsAdapter()
	{
	}

	@NonNull
	@Override
	public StatisticsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_statistics, parent, false);
		return new StatisticsViewHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull StatisticsViewHolder holder, int position)
	{
		holder.bind(mDataSet.siteStats.get(position));
	}

	@Override
	public int getItemCount()
	{
		return mDataSet == null ? 0 : mDataSet.siteStats.size();
	}

	public void setNewDataSet(Statistics dataSet)
	{
		mDataSet = dataSet;
		notifyDataSetChanged();
	}

}
