package com.lucevent.newsup.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucevent.newsup.R;
import com.lucevent.newsup.kernel.util.Notes;
import com.lucevent.newsup.view.adapter.viewholder.NoteItemViewHolder;

public class NoteAdapter extends RecyclerView.Adapter<NoteItemViewHolder> {

    private Notes items;

    private LayoutInflater inflater;

    public NoteAdapter(Context context, Notes items)
    {
        this.items = items;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public NoteItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = inflater.inflate(R.layout.i_note, parent, false);
        return new NoteItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NoteItemViewHolder holder, int position)
    {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

}