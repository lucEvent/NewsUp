package com.lucevent.newsup.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucevent.newsup.R;
import com.lucevent.newsup.kernel.util.Notes;
import com.lucevent.newsup.view.adapter.viewholder.NoteItemViewHolder;

public class NoteAdapter extends RecyclerView.Adapter<NoteItemViewHolder> {

	private Notes mItems;

	private LayoutInflater mInflater;

	public NoteAdapter(Context context, Notes items)
	{
		mItems = items;
		mInflater = LayoutInflater.from(context);
	}

	@NonNull
	@Override
	public NoteItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		View v = mInflater.inflate(R.layout.i_note, parent, false);
		return new NoteItemViewHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull NoteItemViewHolder holder, int position)
	{
		holder.bind(mItems.get(position));
	}

	@Override
	public int getItemCount()
	{
		return mItems.size();
	}

}