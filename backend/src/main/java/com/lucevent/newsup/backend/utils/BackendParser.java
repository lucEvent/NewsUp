package com.lucevent.newsup.backend.utils;

import com.lucevent.newsup.data.util.Date;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

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
        res.append(news.site_code).append("&date=").append(news.date).append("&link=").append(news.link);
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

    public static StringBuilder toHtml(Statistics stats, String options)
    {
        StringBuilder sb = new StringBuilder();

        StatisticsSet statisticsSet;

        if (options.contains("s")) {
            sb.append("\n # Statistics ordered by site name #\n\n");
            statisticsSet = new StatisticsSet(stats.getSiteStats(), StatisticsSet.CMP_SITE_NAME);
            appendHtml(sb, statisticsSet);
        }
        if (options.contains("n")) {
            sb.append("\n # Statistics ordered by number of requests #\n\n");
            statisticsSet = new StatisticsSet(stats.getSiteStats(), StatisticsSet.CMP_N_ACCESSES);
            appendHtml(sb, statisticsSet);
        }
        if (options.contains("t")) {
            sb.append("\n # Statistics ordered by last access time #\n\n");
            statisticsSet = new StatisticsSet(stats.getSiteStats(), StatisticsSet.CMP_LAST_ACCESS);
            appendHtml(sb, statisticsSet);
        }
        if (options.contains("r")) {
            sb.append("\n # Statistics ordered by readings #\n\n");
            statisticsSet = new StatisticsSet(stats.getSiteStats(), StatisticsSet.CMP_READINGS);
            appendHtml(sb, statisticsSet);
        }

        if (options.isEmpty()) {
            SiteStat[] siteStats = stats.getSiteStats();
            for (SiteStat ss : siteStats)
                if (ss.nAccesses != 0)
                    sb.append("\t").append(ss.siteName).append(": ").append(ss.nAccesses).append(" requests\n");
        }
        return sb;
    }

    private static String[] paddings = {"\t\t\t\t", "\t\t\t", "\t\t", "\t", ""};

    private static void appendHtml(StringBuilder sb, StatisticsSet statisticsSet)
    {
        for (SiteStat ss : statisticsSet)
            if (ss.nAccesses > 0)
                sb.append("\t").append(ss.siteName).append(":").append(paddings[(ss.siteName.length() + 1) >> 3])
                        .append(ss.nAccesses).append(" requests\t")
                        .append(ss.nNewsRead).append(" reads |\t[last ")
                        .append(Date.getAge(ss.lastAccess))
                        .append("]\t[from ").append(ss.lastIp).append("]\n");
    }

    private static void appendEntries(StringBuilder sb, StatisticsSet statisticsSet)
    {
        for (SiteStat ss : statisticsSet)
            if (ss.nAccesses > 0)
                sb.append("<site cd='").append(ss.siteCode)
                        .append("' rq='").append(ss.nAccesses)
                        .append("' rd='").append(ss.nNewsRead)
                        .append("' lt='").append(ss.lastAccess)
                        .append("' ip='").append(ss.lastIp)
                        .append("'/>");
    }

    public static StringBuilder toEntry(Statistics stats, String options)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<statistics since='").append(stats.since)
                .append("' laststart='").append(stats.lastStart).append("'>");

        StatisticsSet statisticsSet = null;
        if (options.contains("s"))
            statisticsSet = new StatisticsSet(stats.getSiteStats(), StatisticsSet.CMP_SITE_NAME);
        else if (options.contains("n"))
            statisticsSet = new StatisticsSet(stats.getSiteStats(), StatisticsSet.CMP_N_ACCESSES);
        else if (options.contains("t"))
            statisticsSet = new StatisticsSet(stats.getSiteStats(), StatisticsSet.CMP_LAST_ACCESS);
        else if (options.contains("r"))
            statisticsSet = new StatisticsSet(stats.getSiteStats(), StatisticsSet.CMP_READINGS);

        if (statisticsSet != null)
            appendEntries(sb, statisticsSet);

        sb.append("</statistics>");
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
