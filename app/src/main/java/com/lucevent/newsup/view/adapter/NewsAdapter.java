package com.lucevent.newsup.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.io.BookmarksManager;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.net.ConnectivityManager;
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

	protected final NewsAdapterList mDataSet;
	private final View.OnClickListener mOnClick, mOnBookmarkClick;
	private OnMoreSectionsClickListener mOnMoreClick;
	private boolean mSiteIcon, mImages, mCompactedImages;

	public NewsAdapter(View.OnClickListener onClick, View.OnClickListener onBookmarkClick, NewsAdapterList.SortBy sortBy)
	{
		mOnClick = onClick;
		mOnBookmarkClick = onBookmarkClick;

		mDataSet = new NewsAdapterList(this, sortBy);
		discloseData();
	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		RecyclerView.ViewHolder vh = null;
		switch (viewType) {
			case TYPE_NEWS:
				View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_news, parent, false);
				v.setOnClickListener(mOnClick);
				vh = new NewsViewHolder(v, mOnBookmarkClick);
				break;
			case TYPE_NEWS_WITH_IMG:
				v = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_news_compact, parent, false);
				v.setOnClickListener(mOnClick);
				vh = new NewsCompactViewHolder(v, mOnBookmarkClick);
				break;
			case TYPE_MORE:
				v = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_more_sections, parent, false);
				vh = new MoreSectionsViewHolder(v, mOnMoreClick);
				break;
		}
		return vh;
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
	{
		if (holder instanceof NewsViewHolder) {
			News news = mDataSet.get(position);
			((NewsViewHolder) holder).bind(news, mSiteIcon, mImages, BookmarksManager.isBookmarked(news));
		} else if (holder instanceof NewsCompactViewHolder) {
			News news = mDataSet.get(position);
			((NewsCompactViewHolder) holder).bind(news, mSiteIcon, BookmarksManager.isBookmarked(news));
		} else
			((MoreSectionsViewHolder) holder).bind();
	}

	@Override
	public int getItemCount()
	{
		return mDataSet.size();
	}

	@Override
	public int getItemViewType(int position)
	{
		if (mOnMoreClick != null && position == (mDataSet.size() - 1))
			return TYPE_MORE;

		if (mCompactedImages && mImages) {
			News n = mDataSet.get(position);
			if (n.imgSrc != null)
				return TYPE_NEWS_WITH_IMG;
		}
		return TYPE_NEWS;
	}

	public final void showSiteIcon(boolean showSiteIcon)
	{
		mSiteIcon = showSiteIcon;
	}

	public final void setOnMoreSectionsClick(OnMoreSectionsClickListener onMoreClickListener)
	{
		mOnMoreClick = onMoreClickListener;
	}

	public void setUserPreferences(@NonNull Context context)
	{
		setLoadImages(
				new ConnectivityManager(context),
				AppSettings.loadImages(),
				AppSettings.loadCompactedImages(),
				AppSettings.loadImagesOnlyOnWifi()
		);
	}

	public void setLoadImages(ConnectivityManager cm, boolean loadImages, boolean compactedImages, boolean onlyOnWiFi)
	{
		if (loadImages && cm.isInternetAvailable()) {
			if (!onlyOnWiFi || cm.isOnWifi()) {
				mImages = true;
				mCompactedImages = compactedImages;
				return;
			}
		}
		mImages = false;
		mCompactedImages = false;
	}

	public void setNewDataSet(Collection<News> newDataSet)
	{
		synchronized (mDataSet) {
			mDataSet.beginBatchedUpdates();

			mDataSet.clear();
			mDataSet.addAll(newDataSet);

			if (mOnMoreClick != null)
				mDataSet.add(new News(News.ID_DUMMY, "", "", "", -9, null, null, -1, -1, -1));

			mDataSet.endBatchedUpdates();
		}
		AppData.notifyCurrentNewsListChanged(mDataSet);
	}

	public void addAll(Collection<News> newDataSet)
	{
		mDataSet.beginBatchedUpdates();

		mDataSet.addAll(newDataSet);

		if (newDataSet.size() == mDataSet.size() && mOnMoreClick != null)
			mDataSet.add(new News(-9, "", "", "", -9, null, null, -1, -1, -1));

		mDataSet.endBatchedUpdates();
		AppData.notifyCurrentNewsListChanged(mDataSet);
	}

	public void add(News item)
	{
		mDataSet.add(item);
		AppData.notifyCurrentNewsListChanged(mDataSet);
	}

	public boolean remove(News item)
	{
		boolean r = mDataSet.remove(item);
		AppData.notifyCurrentNewsListChanged(mDataSet);
		return r;
	}

	public final void update(News news)
	{
		int index = mDataSet.indexOf(news);
		mDataSet.updateItemAt(index, news);
		AppData.notifyCurrentNewsListChanged(mDataSet);
	}

	public void replaceAll(Collection<News> newDataSet)
	{
		mDataSet.beginBatchedUpdates();
		for (int i = mDataSet.size() - 1; i >= 0; i--) {
			final News n = mDataSet.get(i);
			if (!newDataSet.contains(n)) {
				mDataSet.remove(n);
			}
		}
		mDataSet.addAll(newDataSet);
		mDataSet.endBatchedUpdates();
		AppData.notifyCurrentNewsListChanged(mDataSet);
	}

	public void clear()
	{
		mDataSet.clear();
		AppData.notifyCurrentNewsListChanged(mDataSet);
	}

	public void discloseData()
	{
		AppData.setCurrentNewsList(mDataSet);
	}

}