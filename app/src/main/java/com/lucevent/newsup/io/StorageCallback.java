package com.lucevent.newsup.io;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsMap;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.services.util.DownloadData;

public interface StorageCallback {

	void save(News news);

	void save(DownloadData downloadData);

	boolean hasNews(int news_id);

	NewsMap getNewsOf(Site site, int[] sections);

	void deleteOldNews(long timeBound);

}
