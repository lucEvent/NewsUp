package com.backend.kernel.util;

public class BE_ContentCache {

    class CacheItem {
        long time;
        String content;
    }

    private CacheItem[] caches;

    public BE_ContentCache(int size) {
        caches = new CacheItem[size];
        for (int p = 0; p < caches.length; ++p) {
            caches[p] = new CacheItem();
            caches[p].time = -1;
            caches[p].content = null;
        }
    }

    public long getTime(int position) {
        return caches[position].time;
    }

    public String getContent(int position) {
        return caches[position].content;
    }

    public void addContent(int position, String content) {
        caches[position].time = System.currentTimeMillis();
        caches[position].content = content;
    }

}
