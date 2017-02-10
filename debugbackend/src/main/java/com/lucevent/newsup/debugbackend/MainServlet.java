package com.lucevent.newsup.debugbackend;

import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.lucevent.newsup.debugbackend.data.Log;
import com.lucevent.newsup.debugbackend.data.TaskData;
import com.lucevent.newsup.debugbackend.data.TaskState;
import com.lucevent.newsup.debugbackend.kernel.Test;
import com.lucevent.newsup.debugbackend.net.Net;
import com.lucevent.newsup.debugbackend.util.ReportCallback;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet implements ReportCallback {
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

        if (req.getParameter("fulltest") != null) {

            String fullReport = new Test().doTest(this);

            if (fullReport != null) {
                Calendar calendar = new GregorianCalendar();

                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH) + 1;
                Net.sendReport("Test report " + day + "/" + month, fullReport);
            }

        }

    }

    @Override
    public void init() throws ServletException
    {
        super.init();

        ObjectifyFactory oFactory = ObjectifyService.factory();
        oFactory.register(Log.class);
        oFactory.register(TaskState.class);
        oFactory.register(TaskData.class);
        oFactory.begin();
    }

    @Override
    public void report(String report)
    {
        Net.sendReport("Urgent report", report);
    }

}
