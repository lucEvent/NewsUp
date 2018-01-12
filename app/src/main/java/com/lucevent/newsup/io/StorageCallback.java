package com.lucevent.newsup.io;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsMap;
import com.lucevent.newsup.data.util.Site;

public interface StorageCallback {

    void save(News news);

    boolean contains(News news);

    NewsMap getNewsOf(Site site);

    NewsMap getNewsOf(Site site, int[] sections);

    void deleteOldNews(long timeBound);

}
