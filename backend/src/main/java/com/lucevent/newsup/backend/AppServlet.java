package com.lucevent.newsup.backend;

import com.lucevent.newsup.backend.utils.Reports;
import com.lucevent.newsup.backend.utils.UpdateMessageCreator;
import com.lucevent.newsup.data.util.Site;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Deprecated
public class AppServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	{
		try {
			processPetition(req, resp);
		} catch (Exception e) {
			Data.notifyException(req, e, "AppServlet");
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	{
		try {
			processPetition(req, resp);
		} catch (Exception e) {
			Data.notifyException(req, e, "AppServlet");
		}
	}

	private void processPetition(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		Reports.addReport(
				req.getParameter("v"),
				req.getRemoteAddr(),
				"AppServlet_v1",
				req.getRequestURL() + req.getQueryString());

		resp.setContentType("text/plain");
		resp.setCharacterEncoding("utf-8");

		if (req.getParameter("news") != null) {

			String site_request = req.getParameter("site");
			String[] parts = site_request.split(",");
			Site site = Data.getSite(Integer.parseInt(parts[0]));

			Data.stats.count(site, req.getRemoteAddr(), "[v1] " + req.getParameter("v"));
			// this is a force the update if they want to continue using it
			UpdateMessageCreator.generateUpdateNews(site, resp);

		} else if (req.getParameter("notify") != null) {

			String[] values = req.getParameter("values").split(",");
			for (int i = 0; i < values.length; i += 2) {
				Site site = Data.getSite(Integer.parseInt(values[i]));
				Data.stats.countReadTimes(site, Integer.parseInt(values[i + 1]));
			}

		} else if (req.getParameter("report") != null) {

			Reports.addReport(
					req.getParameter("version"),
					req.getRemoteAddr(),
					req.getParameter("email"),
					req.getParameter("message"));

		}
	}

	@Override
	public void init() throws ServletException
	{
		super.init();
		new Data();
	}

}
