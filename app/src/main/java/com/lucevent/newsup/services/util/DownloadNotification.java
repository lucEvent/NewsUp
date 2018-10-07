package com.lucevent.newsup.services.util;

import android.util.SparseArray;

import com.lucevent.newsup.data.util.News;

public class DownloadNotification implements java.io.Serializable {

	public long time;   // time here matches with time on DownloadData
	public SparseArray<String> headlines;

	public DownloadNotification()
	{
		headlines = new SparseArray<>();
	}

	public boolean hasHeadline(int siteCode)
	{
		return headlines.get(siteCode) != null;
	}

	public void add(int siteCode, News news)
	{
		headlines.put(siteCode, news.title);
	}

	public boolean isEmpty()
	{
		return headlines.size() == 0;
	}

}