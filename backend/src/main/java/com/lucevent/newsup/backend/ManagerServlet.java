package com.lucevent.newsup.backend;

import com.lucevent.newsup.backend.db.Event;
import com.lucevent.newsup.backend.db.EventNews;
import com.lucevent.newsup.backend.utils.EventFinder;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	{
		try {
			processPetition(req, resp);
		} catch (Exception e) {
			Data.notifyException(req, e, "ManagerServlet");
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	{
		try {
			processPetition(req, resp);
		} catch (Exception e) {
			Data.notifyException(req, e, "ManagerServlet");
		}
	}

	private void processPetition(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		if (req.getParameter("new_month") != null) {

			Data.stats.newMonth();

		} else if (req.getParameter("find_events") != null) {

			if (!req.getServerName().startsWith("newsup-2406"))
				return;

			ArrayList<Event> events = new EventFinder().find();
			for (Event e : events) {
				Event dbEvent = Event.get(e.getCode());
				if (dbEvent == null)
					e.save();
				else {
					dbEvent.setLastNewsTime(e.getLastNewsTime());
					dbEvent.setImgSrc(e.getImgSrc());
					dbEvent.save();
				}
			}

		} else if (req.getParameter("clear_events") != null) {
			Event.deleteAll();
			EventNews.deleteAll();
		}
	}

	@Override
	public void init() throws ServletException
	{
		super.init();

		new Data();
	}

}

