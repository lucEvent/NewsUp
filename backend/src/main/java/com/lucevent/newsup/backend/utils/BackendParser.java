package com.lucevent.newsup.backend.utils;

import com.lucevent.newsup.data.util.Date;
import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

import java.util.ArrayList;

public class BackendParser {

    public static StringBuilder toEntry(News news)
    {
        StringBuilder res = new StringBuilder("<item><sid>");
        res.append(news.server_id);
        res.append("</sid><title>");
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
        res.append("</categories>");
        for (Enclosure e : news.enclosures)
            res.append("<enclosure>").append(e.src).append("</enclosure>");
        res.append("</item>");
        return res;
    }

    public static StringBuilder toHtml(News news)
    {
        StringBuilder res = new StringBuilder("<div class='news' sitecode='")
                .append(news.site_code)
                .append("' title=\"")
                .append(news.title.replace("\"", "'"))
                .append("\" age='")
                .append(Date.getAge(news.date))
                .append("' nid='")
                .append(news.server_id)
                .append("'><h3>")
                .append(news.title)
                .append("</h3><p>")
                .append(news.description)
                .append("</p><br><p><small><em>");
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

    public static StringBuilder toHtml(Statistics stats, String options, String[] filters)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<span class='starts'>Since ").append(Date.getAge(stats.since));
        sb.append(" / Last start ").append(Date.getAge(stats.lastStart)).append("</span>");

        ArrayList<SiteStats> allStats = stats.getSiteStats();
        ArrayList<SiteStats> siteStats;
        if (filters == null)
            siteStats = allStats;
        else {
            siteStats = new ArrayList<>(filters.length);
            for (SiteStats ss : allStats) {
                for (String filter : filters) {
                    if (ss.siteName.toLowerCase().contains(filter.toLowerCase())) {
                        siteStats.add(ss);
                        break;
                    }
                }
            }
        }
        StatisticsSet statisticsSet;

        if (options.contains("s")) {
            sb.append("<span class='order'>site name</span>");
            statisticsSet = new StatisticsSet(siteStats, StatisticsSet.CMP_SITE_NAME);
            appendHtml(sb, statisticsSet);
        }
        if (options.contains("n")) {
            sb.append("<span class='order'>number of requests</span>");
            statisticsSet = new StatisticsSet(siteStats, StatisticsSet.CMP_N_ACCESSES);
            appendHtml(sb, statisticsSet);
        }
        if (options.contains("t")) {
            sb.append("<span class='order'>last access time</span>");
            statisticsSet = new StatisticsSet(siteStats, StatisticsSet.CMP_LAST_ACCESS);
            appendHtml(sb, statisticsSet);
        }
        if (options.contains("r")) {
            sb.append("<span class='order'>readings</span>");
            statisticsSet = new StatisticsSet(siteStats, StatisticsSet.CMP_READINGS);
            appendHtml(sb, statisticsSet);
        }

        if (options.isEmpty()) {
            for (SiteStats ss : stats.getSiteStats())
                if (ss.nAccesses != 0)
                    sb.append("\t").append(ss.siteName).append(": ").append(ss.nAccesses).append(" requests\n");
        }
        return sb;
    }

    private static String[] paddings = {"\t\t\t\t", "\t\t\t", "\t\t", "\t", ""};

    private static void appendHtml(StringBuilder sb, StatisticsSet statisticsSet)
    {
        sb.append("<table><tr>" +
                "<th>#</th>" +
                "<th>Site</th>" +
                "<th># Accesses</th>" +
                "<th># Readings</th>" +
                "<th>Last access</th>" +
                "<th>Last ip</th>" +
                "<th>From version</th>" +
                "</tr>");

        int i = 1;
        for (SiteStats ss : statisticsSet)
            if (ss.nAccesses > 0)
                sb.append("<tr><td>").append(i++)
                        .append("</td><td>").append(ss.siteName)
                        .append("</td><td>").append(ss.nAccesses)
                        .append("</td><td>").append(ss.nNewsRead)
                        .append("</td><td>").append(Date.getAge(ss.lastAccess))
                        .append("</td><td>").append(ss.lastIp)
                        .append("</td><td>").append(ss.fromVersion)
                        .append("</td></tr>");

        sb.append("</table>");
    }

    private static void appendEntries(StringBuilder sb, StatisticsSet statisticsSet)
    {
        for (SiteStats ss : statisticsSet)
            if (ss.nAccesses > 0)
                sb.append("<site cd='").append(ss.siteCode)
                        .append("' rq='").append(ss.nAccesses)
                        .append("' rd='").append(ss.nNewsRead)
                        .append("' lt='").append(ss.lastAccess)
                        .append("' v='").append(ss.fromVersion)
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
