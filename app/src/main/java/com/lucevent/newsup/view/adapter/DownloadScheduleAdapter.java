package com.lucevent.newsup.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucevent.newsup.R;
import com.lucevent.newsup.services.util.Download;
import com.lucevent.newsup.view.adapter.viewholder.DownloadScheduleViewHolder;

import java.util.ArrayList;

public class DownloadScheduleAdapter extends RecyclerView.Adapter<DownloadScheduleViewHolder> {

	private ArrayList<Download> mDataSet;
	private View.OnClickListener mOnItemClickListener;
	private View.OnClickListener mOnDeleteItemListener;

	public DownloadScheduleAdapter(ArrayList<Download> dataSet, View.OnClickListener onItemClickListener,
	                               View.OnClickListener onDeleteItemListener)
	{
		mDataSet = dataSet;
		mOnItemClickListener = onItemClickListener;
		mOnDeleteItemListener = onDeleteItemListener;
	}

	@NonNull
	@Override
	public DownloadScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_download_schedule, parent, false);
		v.setOnClickListener(mOnItemClickListener);
		return new DownloadScheduleViewHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull DownloadScheduleViewHolder holder, int position)
	{
		holder.bind(mDataSet.get(position), mOnDeleteItemListener);
	}

	@Override
	public int getItemCount()
	{
		return mDataSet.size();
	}

	public void remove(Download schedule)
	{
		notifyItemRemoved(mDataSet.indexOf(schedule));
	}

}