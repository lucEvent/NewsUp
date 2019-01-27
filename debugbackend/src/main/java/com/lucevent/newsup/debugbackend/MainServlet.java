package com.lucevent.newsup.debugbackend;

import com.lucevent.newsup.data.util.Date;
import com.lucevent.newsup.debugbackend.db.TestCounter;
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

			TestCounter counter = TestCounter.getInstance();

			switch ((int) (counter.getCounter() % NUM_TASKS)) {
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
			counter.count();
			counter.save();
		}
	}

	@Override
	public void init() throws ServletException
	{
		super.init();

		Date.setTitles(new String[]{"%d seconds ago", "%d minutes ago", "%d hours ago", "%d days ago", "%d months ago", "%d years ago",});
	}

	@Override
	public void report(String report)
	{
		Net.sendReport("Urgent report", report);
	}

}
