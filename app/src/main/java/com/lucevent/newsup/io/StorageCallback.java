package com.lucevent.newsup.io;

public interface StorageCallback {

    void saveNews(com.lucevent.newsup.data.util.News news);

    com.lucevent.newsup.data.util.NewsMap getHistoryOf(com.lucevent.newsup.data.util.Site site);

    void deleteOldNews(long timeBound);

}
