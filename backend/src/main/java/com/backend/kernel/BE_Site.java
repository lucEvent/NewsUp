package com.backend.kernel;

import com.backend.kernel.list.BE_NewsList;
import com.backend.kernel.list.BE_Sections;
import com.backend.kernel.util.BE_ContentCache;
import com.backend.net.BE_NewsReader;

import java.util.TreeSet;

public class BE_Site {

    /**
     * Identify code for the site
     */
    public final int code;

    /**
     * Name of the site (Not important for the server)
     */
    public final String name;

    /**
     *
     */
    public BE_ContentCache contentCache;

    /**
     * Ordered (by time) list of news of the site (== Historial)
     */
    public TreeSet<BE_News> historial;

    /**
     * Site's specific NewsReader
     */
    private BE_NewsReader reader;

    public BE_Site(int code, String name, BE_NewsReader reader) {
        this.code = code;
        this.name = name;
        this.historial = new TreeSet<>();
        this.contentCache = new BE_ContentCache(reader.SECTIONS.size());
        this.reader = reader;
    }

    public void addNews(BE_NewsList news) {
        historial.addAll(news);
    }

    public BE_NewsList readNews(int[] sections) {
        BE_NewsList res = reader.readNews(sections);
        for (BE_News N : res) {
            N.site_code = code;
        }
        return res;
    }

    public void readNewsContent(BE_News news) {
        reader.readNewsContent(news);
    }

    public BE_Sections getSections() {
        return reader.SECTIONS;
    }

    public void addContent(int section, BE_NewsList news) {
        StringBuilder content = new StringBuilder();
        for (BE_News N : news) {
            content.append(N.toEntry());
        }
        contentCache.addContent(section, content.toString());
        historial.addAll(news);
    }

    public long getSectionUpdateTime(int section) {
        return contentCache.getTime(section);
    }

    public String getSectionContent(int section) {
        return contentCache.getContent(section);
    }
}
