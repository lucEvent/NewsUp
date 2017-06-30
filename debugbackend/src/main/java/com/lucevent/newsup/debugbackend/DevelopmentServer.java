package com.lucevent.newsup.debugbackend;

import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.debugbackend.data.Database;
import com.lucevent.newsup.debugbackend.data.Error;
import com.lucevent.newsup.debugbackend.data.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

        if (req.getParameter("error") != null) {

            StringBuilder sb = new StringBuilder();
            sb.append("<content>");

            ArrayList<Error> errors = db.getErrors();
            for (Error e : errors)
                sb.append("<error sc='").append(e.n_site)
                        .append("' sn='").append(sites.getSiteByCode(e.n_site).name)
                        .append("'><link>").append(e.n_link)
                        .append("</link><content>").append(e.n_content)
                        .append("</content></error>");

            sb.append("</content>");
            resp.getWriter().println(sb);

        } else if (req.getParameter("tasks") != null) {

            StringBuilder sb = new StringBuilder();
            sb.append("<content>");

            TreeSet<Task> tasks = db.getTasks();
            for (Task task : tasks) {
                sb.append("<task current='").append(task.currentEvaluatingSite)
                        .append("' start='").append(task.startTime)
                        .append("' end='").append(task.finishTime)
                        .append("' rounds='").append(task.rounds)
                        .append("' num='").append(task.totalNumNews)
                        .append("' results='").append(Arrays.toString(task.totalTestResults))
                        .append("'/>");
            }
            sb.append("</content>");
            resp.getWriter().println(sb);

        }

    }

    static private Sites sites;
    static private Database db;

    @Override
    public void init() throws ServletException
    {
        super.init();

        sites = Sites.getDefault(true);
        db = new Database();
    }

}
