package com.lucevent.newsup.io;

import com.lucevent.newsup.data.util.Site;

public interface StorageCallback {

    void saveNews(com.lucevent.newsup.data.util.News news);

    com.lucevent.newsup.data.util.NewsMap getSavedNews(Site site);

    com.lucevent.newsup.data.util.NewsMap getSavedNews(Site site, int[] sections);

    void deleteOldNews(long timeBound);

}
