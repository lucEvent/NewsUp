package com.lucevent.newsup.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucevent.newsup.R;
import com.lucevent.newsup.services.util.DownloadData;
import com.lucevent.newsup.view.adapter.viewholder.NotificationViewHolder;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationViewHolder> {

	private ArrayList<DownloadData> mDataSet;

	private View.OnClickListener mOnItemClickListener, mOnDeleteListener;
	private LayoutInflater mInflater;

	public NotificationAdapter(Context context, ArrayList<DownloadData> notifications,
	                           View.OnClickListener onItemClickListener, View.OnClickListener onDeleteListener)
	{
		mDataSet = notifications;
		mOnItemClickListener = onItemClickListener;
		mOnDeleteListener = onDeleteListener;
		mInflater = LayoutInflater.from(context);
	}

	@NonNull
	@Override
	public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		View v = mInflater.inflate(R.layout.i_notification, parent, false);
		v.setOnClickListener(mOnItemClickListener);
		return new NotificationViewHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position)
	{
		holder.bind(mDataSet.get(position), mOnDeleteListener);
	}

	@Override
	public int getItemCount()
	{
		return mDataSet.size();
	}


	public void remove(DownloadData data)
	{
		int pos = mDataSet.indexOf(data);
		if (pos != -1) {
			mDataSet.remove(pos);
			notifyItemRemoved(pos);
		}
	}

	public void clear()
	{
		mDataSet.clear();
		notifyDataSetChanged();
	}

}
