package com.lucevent.newsup.debugbackend;

import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.lucevent.newsup.data.util.Date;
import com.lucevent.newsup.debugbackend.data.Bug;
import com.lucevent.newsup.debugbackend.data.PartialTestResult;
import com.lucevent.newsup.debugbackend.data.Task;
import com.lucevent.newsup.debugbackend.data.TestCounter;
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

import static com.googlecode.objectify.ObjectifyService.ofy;

public class MainServlet extends HttpServlet implements ReportCallback {

    private static final int NUM_TASKS = 2;

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

            TestCounter counter = ofy().load().type(TestCounter.class).first().now();

            if (counter == null) {
                counter = new TestCounter();
                counter.counter = 0;
                ofy().save().entity(counter).now();
            }

            switch ((int) (counter.counter % NUM_TASKS)) {
                case 0:
                    Test test = new Test();

                    String fullReport = test.doTest(this);

                    if (fullReport != null) {
                        Calendar calendar = new GregorianCalendar();

                        int day = calendar.get(Calendar.DAY_OF_MONTH);
                        int month = calendar.get(Calendar.MONTH) + 1;
                        Net.sendReport("Test report " + day + "/" + month, fullReport);

                        test.clearTestCache();
                        break;
                    }

                    return;
                case 1:
                    //  new SectionsTest().doTest(this);
                    break;
            }
            counter.counter++;
            ofy().save().entity(counter).now();
        }
    }

    @Override
    public void init() throws ServletException
    {
        super.init();

        Date.setTitles(new String[]{"%d seconds ago", "%d minutes ago", "%d hours ago", "%d days ago", "%d months ago", "%d years ago",});

        ObjectifyFactory oFactory = ObjectifyService.factory();
        oFactory.register(PartialTestResult.class);
        oFactory.register(Task.class);
        oFactory.register(TestCounter.class);
        oFactory.register(Bug.class);
        oFactory.begin();
    }

    @Override
    public void report(String report)
    {
        Net.sendReport("Urgent report", report);
    }

}
