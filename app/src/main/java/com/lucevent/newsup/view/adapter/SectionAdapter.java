package com.lucevent.newsup.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.Sections;
import com.lucevent.newsup.view.adapter.viewholder.SectionViewHolder;

public class SectionAdapter extends RecyclerView.Adapter<SectionViewHolder> {

    private Sections dataset;
    private View.OnClickListener itemListener;

    public SectionAdapter(Sections dataset, View.OnClickListener itemListener)
    {
        this.dataset = dataset;
        this.itemListener = itemListener;
    }

    @Override
    public int getItemViewType(int position)
    {
        return dataset.get(position).level <= 0 ? 0 : 1;
    }

    @Override
    public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        int res_id = viewType == 0 ? R.layout.i_section_header : R.layout.i_section;
        View v = LayoutInflater.from(parent.getContext()).inflate(res_id, parent, false);
        v.setOnClickListener(itemListener);
        return new SectionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SectionViewHolder holder, int position)
    {
        SectionViewHolder.populateViewHolder(holder, dataset.get(position));
    }

    @Override
    public int getItemCount()
    {
        return dataset.size();
    }

    public void setNewDataSet(Sections dataset)
    {
        this.dataset = dataset;
        notifyDataSetChanged();
    }

}
