package com.lucevent.newsup.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.io.BookmarksManager;
import com.lucevent.newsup.view.adapter.viewholder.MoreSectionsViewHolder;
import com.lucevent.newsup.view.adapter.viewholder.NewsViewHolder;
import com.lucevent.newsup.view.util.NewsAdapterList;
import com.lucevent.newsup.view.util.OnMoreSectionsClickListener;

import java.util.Collection;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_NEWS = 0;
    private static final int TYPE_MORE = 1;

    private final View.OnClickListener onClick, onBookmarkClick;
    //   private final View.OnLongClickListener onLongClick;
    private OnMoreSectionsClickListener onMoreClick;

    private boolean showSiteLogo, loadImage;

    private final NewsAdapterList dataSet;

    public NewsAdapter(View.OnClickListener onClick,
                       View.OnLongClickListener onLongClick,
                       View.OnClickListener onBookmarkClick, NewsAdapterList.SortBy sortBy)
    {
        this.onClick = onClick;
        // this.onLongClick = onLongClick;
        this.onBookmarkClick = onBookmarkClick;

        dataSet = new NewsAdapterList(this, sortBy);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        RecyclerView.ViewHolder vh = null;
        switch (viewType) {
            case TYPE_NEWS:
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_news, parent, false);
                //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_news_compact, parent, false);
                v.setOnClickListener(onClick);
//            v.setOnLongClickListener(onLongClick);
                vh = new NewsViewHolder(v, onBookmarkClick);
                break;
            case TYPE_MORE:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_more_sections, parent, false);
                vh = new MoreSectionsViewHolder(v, onMoreClick);
                break;
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        if (holder instanceof NewsViewHolder) {
            News news = dataSet.get(position);
            ((NewsViewHolder) holder).bind(news, showSiteLogo, loadImage, BookmarksManager.isBookmarked(news));
        } else
            ((MoreSectionsViewHolder) holder).bind();
    }

    @Override
    public int getItemCount()
    {
        return dataSet.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        return onMoreClick == null ? TYPE_NEWS : (position < (dataSet.size() - 1) ? TYPE_NEWS : TYPE_MORE);
    }

    public final void showSiteLogo(boolean showSiteLogo)
    {
        this.showSiteLogo = showSiteLogo;
    }

    public final void setOnMoreSectionsClick(OnMoreSectionsClickListener onMoreClickListener)
    {
        this.onMoreClick = onMoreClickListener;
    }

    public void setLoadImages(boolean loadImages)
    {
        this.loadImage = loadImages;
    }

    public final void setNewDataSet(Collection<News> newDataSet)
    {
        synchronized (this.dataSet) {
            dataSet.beginBatchedUpdates();

            dataSet.clear();
            dataSet.addAll(newDataSet);

            if (this.onMoreClick != null)
                dataSet.add(new News(-9, "", "", "", -9, null, -1, -1, -1));

            dataSet.endBatchedUpdates();
        }
    }

    public void addAll(Collection<News> newDataSet)
    {
        dataSet.beginBatchedUpdates();

        dataSet.addAll(newDataSet);

        if (newDataSet.size() == dataSet.size() && this.onMoreClick != null)
            dataSet.add(new News(-9, "", "", "", -9, null, -1, -1, -1));

        dataSet.endBatchedUpdates();
    }

    public void add(News item)
    {
        dataSet.add(item);
    }

    public boolean remove(News item)
    {
        return dataSet.remove(item);
    }

    public final void update(News news)
    {
        int index = dataSet.indexOf(news);
        dataSet.updateItemAt(index, news);
    }

    public void clear()
    {
        dataSet.beginBatchedUpdates();
        dataSet.clear();
        dataSet.endBatchedUpdates();
    }

}