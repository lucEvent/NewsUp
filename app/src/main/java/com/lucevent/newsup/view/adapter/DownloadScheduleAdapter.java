package com.lucevent.newsup.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucevent.newsup.R;
import com.lucevent.newsup.services.util.DownloadSchedule;
import com.lucevent.newsup.view.adapter.viewholder.DownloadScheduleViewHolder;

import java.util.ArrayList;

public class DownloadScheduleAdapter extends RecyclerView.Adapter<DownloadScheduleViewHolder> {

    private ArrayList<DownloadSchedule> dataset;
    private View.OnClickListener itemListener;
    private View.OnClickListener deleteListener;

    public DownloadScheduleAdapter(ArrayList<DownloadSchedule> dataset, View.OnClickListener itemListener,
                                   View.OnClickListener deleteListener)
    {
        this.dataset = dataset;
        this.itemListener = itemListener;
        this.deleteListener = deleteListener;
    }

    @Override
    public DownloadScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_download_schedule, parent, false);
        v.setOnClickListener(itemListener);
        return new DownloadScheduleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DownloadScheduleViewHolder holder, int position)
    {
        DownloadScheduleViewHolder.populateViewHolder(holder, dataset.get(position), deleteListener);
    }

    @Override
    public int getItemCount()
    {
        return dataset.size();
    }

    public void remove(DownloadSchedule schedule)
    {
        notifyItemRemoved(dataset.indexOf(schedule));
    }

}