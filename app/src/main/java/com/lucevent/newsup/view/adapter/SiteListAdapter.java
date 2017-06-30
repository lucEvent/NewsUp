package com.lucevent.newsup.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.view.adapter.viewholder.SiteListViewHolder;

public class SiteListAdapter extends RecyclerView.Adapter<SiteListViewHolder> {

    private Sites sites;
    private View.OnClickListener itemListener;

    public SiteListAdapter(Sites sites, View.OnClickListener itemListener)
    {
        this.sites = sites;
        this.itemListener = itemListener;
    }

    @Override
    public SiteListViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_site_list, parent, false);
        v.setOnClickListener(itemListener);
        return new SiteListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SiteListViewHolder holder, int position)
    {
        SiteListViewHolder.populateViewHolder(holder, sites.get(position));
    }

    @Override
    public int getItemCount()
    {
        return sites.size();
    }

}
