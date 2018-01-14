package com.lucevent.newsup.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.io.BookmarksManager;
import com.lucevent.newsup.view.adapter.viewholder.MoreSectionsViewHolder;
import com.lucevent.newsup.view.adapter.viewholder.NewsCompactViewHolder;
import com.lucevent.newsup.view.adapter.viewholder.NewsViewHolder;
import com.lucevent.newsup.view.util.NewsAdapterList;
import com.lucevent.newsup.view.util.OnMoreSectionsClickListener;

import java.util.Collection;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_NEWS = 0;
    private static final int TYPE_NEWS_WITH_IMG = 1;
    private static final int TYPE_MORE = 2;

    private final View.OnClickListener onClick, onBookmarkClick;
    //   private final View.OnLongClickListener onLongClick;
    private OnMoreSectionsClickListener onMoreClick;

    private boolean mSiteIcon, mImages, mCompactedImages;

    protected final NewsAdapterList dataSet;

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
                v.setOnClickListener(onClick);
                vh = new NewsViewHolder(v, onBookmarkClick);
                break;
            case TYPE_NEWS_WITH_IMG:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_news_compact, parent, false);
                v.setOnClickListener(onClick);
                vh = new NewsCompactViewHolder(v, onBookmarkClick);
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
            ((NewsViewHolder) holder).bind(news, mSiteIcon, mImages, BookmarksManager.isBookmarked(news));
        } else if (holder instanceof NewsCompactViewHolder) {
            News news = dataSet.get(position);
            ((NewsCompactViewHolder) holder).bind(news, mSiteIcon, BookmarksManager.isBookmarked(news));
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
        if (onMoreClick != null && position == (dataSet.size() - 1))
            return TYPE_MORE;

        if (mCompactedImages && mImages) {
            News n = dataSet.get(position);
            if (n.enclosures != null && !n.enclosures.isEmpty() && !n.enclosures.get(0).src.isEmpty())
                return TYPE_NEWS_WITH_IMG;
        }
        return TYPE_NEWS;
    }

    public final void showSiteIcon(boolean showSiteIcon)
    {
        this.mSiteIcon = showSiteIcon;
    }

    public final void setOnMoreSectionsClick(OnMoreSectionsClickListener onMoreClickListener)
    {
        this.onMoreClick = onMoreClickListener;
    }

    public void setUserPreferences()
    {
        setLoadImages(
                AppSettings.loadImages(),
                AppSettings.loadCompactedImages()
        );
    }

    public void setLoadImages(boolean loadImages, boolean compactedImages)
    {
        this.mImages = loadImages;
        this.mCompactedImages = compactedImages;
    }

    public void setNewDataSet(Collection<News> newDataSet)
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

    public void replaceAll(Collection<News> newDataSet)
    {
        dataSet.beginBatchedUpdates();
        for (int i = dataSet.size() - 1; i >= 0; i--) {
            final News n = dataSet.get(i);
            if (!newDataSet.contains(n)) {
                dataSet.remove(n);
            }
        }
        dataSet.addAll(newDataSet);
        dataSet.endBatchedUpdates();
    }

    public void clear()
    {
        dataSet.beginBatchedUpdates();
        dataSet.clear();
        dataSet.endBatchedUpdates();
    }

}