package com.lucevent.newsup.backend;

import com.lucevent.newsup.backend.utils.MonthStats;
import com.lucevent.newsup.backend.utils.Report;
import com.lucevent.newsup.backend.utils.Reports;
import com.lucevent.newsup.backend.utils.SiteStats;
import com.lucevent.newsup.backend.utils.StatisticsSet;

import java.io.IOException;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DevelopmentServer extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        processPetition(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        processPetition(req, resp);
    }

    private void processPetition(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");

        if (req.getParameter("stats") != null) {

            //   if (req.getParameter("reset") != null)
            //       Data.stats.reset();

            StringBuilder sb = new StringBuilder();
            sb.append("<content>");

            // Month stats
            TreeSet<MonthStats> monthStats = Data.stats.getMonthStats();
            for (MonthStats ms : monthStats)
                sb.append("<month na='").append(ms.id)
                        .append("' va='").append(ms.counter)
                        .append("'/>");

            // Load distribution
            sb.append("<load>").append(Data.stats.getDistribution())
                    .append("</load>");

            // Site stats
            StatisticsSet statisticsSet = new StatisticsSet(Data.stats.getSiteStats(), StatisticsSet.CMP_N_ACCESSES);
            for (SiteStats ss : statisticsSet)
                if (ss.nAccesses > 0)
                    sb.append("<site co='").append(ss.siteCode)
                            .append("' na='").append(ss.siteName)
                            .append("' ac='").append(ss.nAccesses)
                            .append("' re='").append(ss.nNewsRead)
                            .append("' la='").append(ss.lastAccess)
                            .append("' ve='").append(ss.fromVersion)
                            .append("'/>");

            sb.append("</content>");
            resp.getWriter().println(sb);

        } else if (req.getParameter("reports") != null) {

            StringBuilder sb = new StringBuilder();
            sb.append("<content>");

            Reports reports = Data.reports.getReports();
            for (Report r : reports)
                sb.append("<report ti='").append(r.time)
                        .append("' ve='").append(r.appVersion)
                        .append("' ip='").append(r.ip)
                        .append("' em='").append(r.email)
                        .append("'><message><![CDATA[").append(r.message)
                        .append("]]></message></report>");

            sb.append("</content>");
            resp.getWriter().println(sb);

        }

    }

    @Override
    public void init() throws ServletException
    {
        super.init();

        new Data();
    }

}

