package com.lucevent.newsup.services.util;

import com.lucevent.newsup.data.util.News;

import java.util.ArrayList;

public class DownloadNotification implements java.io.Serializable {

	public class Source implements java.io.Serializable {
		public int siteCode;
		public int[] sections;

		Source(int siteCode, int[] sections)
		{
			this.siteCode = siteCode;
			this.sections = sections;
		}
	}

	public ArrayList<Source> sources;
	public ArrayList<String> headlines;
	public String[] filters;

	public DownloadNotification(int capacity)
	{
		sources = new ArrayList<>(capacity);
		headlines = new ArrayList<>(capacity);
	}

	public void add(int siteCode, int[] sections, News news)
	{
		sources.add(new Source(siteCode, sections));
		headlines.add(news.title);
	}

	public boolean isEmpty()
	{
		return headlines.isEmpty();
	}

}