package com.lucevent.newsup.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.NewsSet;
import com.lucevent.newsup.io.BookmarksManager;
import com.lucevent.newsup.view.adapter.viewholder.NewsViewHolder;
import com.lucevent.newsup.view.util.OnMoreSectionsClickListener;

import java.util.Collection;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int CHUNK = 10;
    private static final int TYPE_NEWS = 0;
    private static final int TYPE_MORE = 1;

    private int dataSetVisibleCount;

    private final NewsSet dataSet;
    private final NewsArray dataArray;
    private final View.OnClickListener onClick, onBookmarkClick;
    private final View.OnLongClickListener onLongClick;
    private OnMoreSectionsClickListener onMoreClick;

    private boolean showSiteLogo, loadImage;

    public NewsAdapter(NewsArray dataArray, View.OnClickListener onClick, View.OnLongClickListener onLongClick,
                       View.OnClickListener onBookmarkClick)
    {
        this.dataArray = dataArray;
        this.onClick = onClick;
        this.onLongClick = onLongClick;
        this.onBookmarkClick = onBookmarkClick;
        this.dataSetVisibleCount = Math.min(CHUNK, dataArray.size());

        this.dataSet = new NewsSet();
        this.showSiteLogo = true;
        this.loadImage = true;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        RecyclerView.ViewHolder vh;
        if (viewType == TYPE_NEWS) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_news, parent, false);
            v.setOnClickListener(onClick);
//            v.setOnLongClickListener(onLongClick);
            vh = new NewsViewHolder(v, onBookmarkClick);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_more_sections, parent, false);
            vh = new MoreSectionsViewHolder(v, onMoreClick);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        if (holder instanceof NewsViewHolder) {
            News news = dataArray.get(position);
            ((NewsViewHolder) holder).populate(news, showSiteLogo, loadImage, BookmarksManager.isBookmarked(news));
        } else
            ((MoreSectionsViewHolder) holder).populate();
    }

    @Override
    public final int getItemCount()
    {
        return dataSetVisibleCount;
    }

    @Override
    public int getItemViewType(int position)
    {
        return onMoreClick == null ? TYPE_NEWS : (position < dataArray.size() ? TYPE_NEWS : TYPE_MORE);
    }

    public final void loadMoreData()
    {
        int dataAdded = Math.min(dataSetVisibleCount + CHUNK, dataArray.size()) - dataSetVisibleCount;
        if (dataAdded > 0) {
            try {
                notifyItemRangeInserted(dataSetVisibleCount, dataAdded);
            } catch (Exception ignored) {
            }
            dataSetVisibleCount += dataAdded;
        } else if (dataSetVisibleCount == dataArray.size()) {
            if (onMoreClick != null) {
                notifyItemRangeInserted(dataSetVisibleCount, 1);
                dataSetVisibleCount++;
            }
        }
    }

    public final void showSiteLogo(boolean showSiteLogo)
    {
        this.showSiteLogo = showSiteLogo;
    }

    public final void setOnMoreSectionsClick(OnMoreSectionsClickListener onMoreClickListener)
    {
        this.onMoreClick = onMoreClickListener;
    }

    public void loadImages(boolean loadImages)
    {
        this.loadImage = loadImages;
    }

    public final void setNewDataSet(Collection<News> dataSet)
    {
        synchronized (this.dataArray) {
            this.dataSet.clear();
            this.dataSet.addAll(dataSet);
            this.dataArray.clear();
            this.dataArray.addAll(this.dataSet);

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

    public final void addAll(Collection<News> dataSet)
    {
        synchronized (this.dataArray) {
            this.dataSet.addAll(dataSet);
            this.dataArray.clear();
            this.dataArray.addAll(this.dataSet);

            if (dataSetVisibleCount == 0)
                dataSetVisibleCount = Math.min(CHUNK, this.dataArray.size());

            notifyItemRangeChanged(0, dataSetVisibleCount);
        }
    }

    public final void remove(News news)
    {
        int position = -1;
        for (int i = 0; i < dataArray.size(); i++) {
            if (dataArray.get(i).compareTo(news) == 0) {
                position = i;
                break;
            }
        }
        dataArray.remove(position);
        dataSetVisibleCount--;
        notifyItemRemoved(position);
    }

    public final void update(News news)
    {
        int position = -1;
        for (int i = 0; i < dataArray.size(); i++) {
            if (dataArray.get(i).id == news.id) {
                position = i;
                break;
            }
        }
        notifyItemChanged(position);
    }

    public final void clear()
    {
        dataArray.clear();
        dataSet.clear();
        notifyItemRangeRemoved(0, dataSetVisibleCount);
        dataSetVisibleCount = 0;
    }

}
