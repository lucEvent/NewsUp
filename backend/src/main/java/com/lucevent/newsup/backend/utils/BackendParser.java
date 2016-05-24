package com.lucevent.newsup.backend.utils;

import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.Date;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;
import com.lucevent.newsup.data.util.Site;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

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
        res.append("</p><br><p><small><em>");
        for (String tag : news.tags)
            res.append("<span class='tag'> ").append(tag).append(" </span>");
        res.append("</em></small></p></div>");
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

    public static StringBuilder toHtml(Sites sites, Statistics stats, Statistics.Order order)
    {
        StringBuilder sb = new StringBuilder();

        switch (order) {
            case ByDefault:
                for (int i = 0; i < sites.size(); i++) {
                    int count = stats.getCount(i);
                    if (count != 0) {
                        sb.append("\t").append(sites.get(i).name).append(": ").append(count).append(" requests\n");
                    }
                }
                break;
            case BySiteName:
                sb.append("\n ### Statistics ordered by site name ###\n\n");
                TreeMap<Site, Integer> map_s = new TreeMap<>(new Comparator<Site>() {
                    @Override
                    public int compare(Site o1, Site o2)
                    {
                        return o1.name.compareTo(o2.name);
                    }
                });
                for (int i = 0; i < sites.size(); ++i) {
                    int count = stats.getCount(i);
                    if (count > 0)
                        map_s.put(sites.get(i), count);
                }
                for (Map.Entry<Site, Integer> entry : map_s.entrySet())
                    sb.append("\t").append(entry.getKey().name).append(": ")
                            .append(entry.getValue()).append(" requests\n");
                break;
            case ByNumber:
                sb.append("\n ### Statistics ordered by number of requests ###\n\n");
                TreeMap<Integer, Site> map_n = new TreeMap<>(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2)
                    {
                        return o1 < o2 ? 1 : -1;
                    }
                });
                for (int i = 0; i < sites.size(); ++i) {
                    int count = stats.getCount(i);
                    if (count > 0)
                        map_n.put(count, sites.get(i));
                }
                for (Map.Entry<Integer, Site> entry : map_n.entrySet())
                    sb.append("\t").append(entry.getValue().name).append(": ")
                            .append(entry.getKey()).append(" requests\n");
                break;
            case ByLastAccessTime:
                sb.append("\n ### Statistics ordered by last access time ###\n\n");
                TreeMap<Long, Integer> map_t = new TreeMap<>(new Comparator<Long>() {
                    @Override
                    public int compare(Long o1, Long o2)
                    {
                        return o1 < o2 ? 1 : -1;
                    }
                });
                for (int i = 0; i < sites.size(); ++i)
                    if (stats.getCount(i) > 0)
                        map_t.put(stats.getLastAccess(i), i);

                for (Map.Entry<Long, Integer> entry : map_t.entrySet()) {
                    long time = entry.getKey();
                    int position = entry.getValue();
                    sb.append("\t").append(sites.get(position).name).append(": ")
                            .append(Date.getAge(time)).append(" from ")
                            .append(stats.getLastIp(position));
                }
                break;
        }
        return sb;
    }

    public static StringBuilder toHtml(Sections sections)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sections.size(); i++) {
            Section s = sections.get(i);
            String tag_class;
            switch (s.level) {
                case 0:
                    tag_class = "section groupsection";
                    break;
                case 1:
                    tag_class = "section subsection";
                    break;
                case -1:
                    tag_class = "groupsection";
                    break;
                default:
                    tag_class = "";
            }
            sb.append("<a class='")
                    .append(tag_class)
                    .append("' index='")
                    .append(i)
                    .append("'><p>")
                    .append(s.name)
                    .append("</p></a>");
        }
        return sb;
    }

}
