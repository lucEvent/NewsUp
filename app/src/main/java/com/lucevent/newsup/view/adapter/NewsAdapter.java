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

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    public static final int CHUNK = 15;

    private int datasetVisibleCount;

    private final NewsArray dataset;
    private View.OnClickListener itemListener;

    private NewsMap newsmap;

    private boolean showSiteLogo;

    public NewsAdapter(NewsArray dataset, View.OnClickListener itemListener)
    {
        this.dataset = dataset;
        this.itemListener = itemListener;
        this.datasetVisibleCount = Math.min(CHUNK, dataset.size());

        newsmap = new NewsMap();
        showSiteLogo = true;
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
        NewsViewHolder.populateViewHolder(holder, dataset.get(position), showSiteLogo);
    }

    @Override
    public int getItemCount()
    {
        return datasetVisibleCount;
    }

    public int getActualItemCount()
    {
        return dataset.size();
    }

    public void loadMoreData()
    {
        int dataAdded = Math.min(this.datasetVisibleCount + CHUNK, dataset.size()) - datasetVisibleCount;
        try {
            notifyItemRangeInserted(datasetVisibleCount, datasetVisibleCount + dataAdded - 1);
        } catch (Exception ignored) {
        }
        this.datasetVisibleCount += dataAdded;
    }

    public void showSiteLogo(boolean showSiteLogo)
    {
        this.showSiteLogo = showSiteLogo;
    }

    public void setNewDataSet(NewsMap dataset)
    {
        this.dataset.clear();
        this.dataset.addAll(dataset);

        int newVisibleCount = Math.min(dataset.size(), CHUNK);
        if (newVisibleCount > datasetVisibleCount) {
            notifyItemRangeChanged(0, datasetVisibleCount - 1);
            notifyItemRangeInserted(datasetVisibleCount, newVisibleCount - 1);
        } else if (newVisibleCount < datasetVisibleCount) {
            notifyItemRangeChanged(0, newVisibleCount - 1);
            notifyItemRangeRemoved(newVisibleCount, datasetVisibleCount - 1);
        } else
            notifyDataSetChanged();

        this.datasetVisibleCount = newVisibleCount;
    }

    public void add(News news)
    {
        synchronized (dataset) {
            dataset.add(news);
            if (datasetVisibleCount < CHUNK) {
                notifyItemInserted(datasetVisibleCount);
                datasetVisibleCount++;
            }
        }
    }

    public void addAll(NewsMap news)
    {
        synchronized (dataset) {
            this.dataset.addAll(news);

            if (datasetVisibleCount == dataset.size() - news.size() || datasetVisibleCount < CHUNK) {
                datasetVisibleCount = Math.min(datasetVisibleCount + CHUNK, dataset.size());
                notifyItemRangeInserted(dataset.size() - news.size() - 1, datasetVisibleCount - 1);
            }
        }
    }

    public void clear()
    {
        notifyItemRangeRemoved(0, datasetVisibleCount);
        datasetVisibleCount = 0;
        dataset.clear();
        notifyDataSetChanged();
    }

}
