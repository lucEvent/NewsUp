package com.lucevent.newsup.backend.utils;

import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;

public class BackendParser {

    public static StringBuilder toEntry(News news)
    {
        StringBuilder res = new StringBuilder("<item><title>");
        res.append(news.title);
        res.append("</title><link>");
        res.append(news.link);
        res.append("</link><date>");
        res.append(news.date);
        res.append("</date><description>");
        res.append(news.description);
        res.append("</description><content>");
        if (news.content != null && !news.content.isEmpty()) res.append(news.content);
        res.append("</content><categories>");
        res.append(news.tags.toString());
        res.append("</categories></item>");
        return res;
    }

    public static StringBuilder toHtml(News news)
    {
        StringBuilder res = new StringBuilder("<div class=\"nu_news\" data-featherlight=\"/web?content&site=");
        res.append(news.site_code + "&date=" + news.date + "&link=" + news.link);
        res.append("\"><h2>");
        res.append(news.title);
        res.append("</h2><p>");
        res.append(news.description);
        res.append("</p><br><small><em>");
        res.append(news.tags.toString());
        res.append("</small></em></div>");
        return res;
    }

    public static StringBuilder toEntry(NewsArray news)
    {
        StringBuilder sb = new StringBuilder("<channel>");

        for (News N : news)
            sb.append(BackendParser.toEntry(N));

        sb.append("</channel>");
        return sb;
    }

    public static StringBuilder toHtml(NewsArray news)
    {
        StringBuilder sb = new StringBuilder();

        for (News N : news)
            sb.append(BackendParser.toHtml(N));

        return sb;
    }

    public static StringBuilder toHtml(Sites sites, Statistics stats)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(stats.since).append("\n\n");
        sb.append(stats.lastStart).append("\n\n");

        for (int i = 0; i < sites.size(); i++) {
            int count = stats.getCount(i);
            if (count != 0) {
                sb.append(sites.get(i).name).append(": ").append(count).append(" requests\n");
            }
        }
        return sb;
    }
}
