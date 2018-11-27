package com.lucevent.newsup.backend;

import com.googlecode.objectify.cmd.LoadType;
import com.lucevent.newsup.backend.utils.Event;
import com.lucevent.newsup.backend.utils.EventFinder;
import com.lucevent.newsup.backend.utils.EventNews;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.googlecode.objectify.ObjectifyService.ofy;

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
			LoadType<Event> loader = ofy().load().type(Event.class);
			for (Event e : events) {
				Event dbEvent = loader.id(e.code).now();
				if (dbEvent == null)
					ofy().save().entity(e).now();
				else {
					dbEvent.lastNewsTime = e.lastNewsTime;
					dbEvent.imgSrc = e.imgSrc;
					ofy().save().entity(e).now();
				}
			}

		} else if (req.getParameter("clear_events") != null) {
			List<Event> e = ofy().load().type(Event.class).list();
			ofy().delete().entities(e).now();

			List<EventNews> l = ofy().load().type(EventNews.class).list();
			ofy().delete().entities(l).now();
		}
	}

	@Override
	public void init() throws ServletException
	{
		super.init();

		new Data();
	}

}

