package com.lucevent.newsup.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.event.Events;
import com.lucevent.newsup.view.adapter.viewholder.EventViewHolder;

public class EventsAdapter extends RecyclerView.Adapter<EventViewHolder> {

	private final View.OnClickListener mOnItemClickListener;

	private final Events mDataSet;

	public EventsAdapter(View.OnClickListener onItemClickListener)
	{
		mOnItemClickListener = onItemClickListener;
		mDataSet = new Events();
	}

	@NonNull
	@Override
	public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_event, parent, false);
		v.setOnClickListener(mOnItemClickListener);
		return new EventViewHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull EventViewHolder holder, int position)
	{
		holder.bind(mDataSet.get(position));
	}

	@Override
	public int getItemCount()
	{
		return mDataSet.size();
	}

	public void setNewsDataSet(Events newDataSet)
	{
		mDataSet.clear();
		mDataSet.addAll(newDataSet);
		notifyDataSetChanged();
	}

}

