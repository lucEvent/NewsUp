package com.lucevent.newsup.services.util;

public class DownloadData {

	public final long time;
	public final int[] site_codes;
	public final int[] news_ids;

	public DownloadData(long time, int[] site_codes, int[] news_ids)
	{
		this.time = time;
		this.site_codes = site_codes;
		this.news_ids = news_ids;
	}

}