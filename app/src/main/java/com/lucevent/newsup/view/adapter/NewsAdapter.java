package com.lucevent.newsup.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.NewsMap;
import com.lucevent.newsup.view.adapter.viewholder.NewsViewHolder;

import java.util.Collection;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    public static final int CHUNK = 15;

    private int dataSetVisibleCount;

    private final NewsMap dataMap;
    private final NewsArray dataSet;
    private View.OnClickListener itemListener;

    private boolean showSiteLogo;

    public NewsAdapter(NewsArray dataSet, View.OnClickListener itemListener)
    {
        this.dataSet = dataSet;
        this.itemListener = itemListener;
        this.dataSetVisibleCount = Math.min(CHUNK, dataSet.size());

        this.dataMap = new NewsMap();
        this.showSiteLogo = true;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_news, parent, false);
        v.setOnClickListener(itemListener);
        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position)
    {
        NewsViewHolder.populateViewHolder(holder, dataSet.get(position), showSiteLogo);
    }

    @Override
    public int getItemCount()
    {
        return dataSetVisibleCount;
    }

    public int getActualItemCount()
    {
        return dataSet.size();
    }

    public void loadMoreData()
    {
        int dataAdded = Math.min(this.dataSetVisibleCount + CHUNK, dataSet.size()) - dataSetVisibleCount;
        if (dataAdded > 0) {
            try {
                notifyItemRangeInserted(dataSetVisibleCount, dataAdded);
            } catch (Exception ignored) {
            }
            this.dataSetVisibleCount += dataAdded;
        }
    }

    public void showSiteLogo(boolean showSiteLogo)
    {
        this.showSiteLogo = showSiteLogo;
    }

    public void setNewDataSet(Collection<News> dataSet)
    {
        synchronized (this.dataSet) {
            this.dataMap.clear();
            this.dataMap.addAll(dataSet);
            this.dataSet.clear();
            this.dataSet.addAll(dataMap);

            int newVisibleCount = Math.min(dataSet.size(), CHUNK);
            if (newVisibleCount > dataSetVisibleCount) {
                notifyItemRangeChanged(0, dataSetVisibleCount);
                notifyItemRangeInserted(dataSetVisibleCount, newVisibleCount - dataSetVisibleCount);
            } else if (newVisibleCount < dataSetVisibleCount) {
                notifyItemRangeChanged(0, newVisibleCount);
                notifyItemRangeRemoved(newVisibleCount, dataSetVisibleCount - newVisibleCount);
            } else
                notifyDataSetChanged();

            this.dataSetVisibleCount = newVisibleCount;
        }
    }

    public void addAll(Collection<News> dataSet)
    {
        synchronized (this.dataSet) {
            this.dataMap.addAll(dataSet);
            this.dataSet.clear();
            this.dataSet.addAll(dataMap);

            int current = dataSetVisibleCount;
            dataSetVisibleCount = Math.min(dataSetVisibleCount + CHUNK, this.dataSet.size());
            if (current == 0)
                notifyItemRangeInserted(0, dataSetVisibleCount);
            else
                notifyItemRangeChanged(0, dataSetVisibleCount);
        }
    }

    public void clear()
    {
        dataSet.clear();
        dataMap.clear();
        notifyItemRangeRemoved(0, dataSetVisibleCount);
        dataSetVisibleCount = 0;
    }

}
