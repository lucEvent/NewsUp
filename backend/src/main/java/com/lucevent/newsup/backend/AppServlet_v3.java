package com.lucevent.newsup.backend;

import com.lucevent.newsup.backend.utils.Alerts;
import com.lucevent.newsup.backend.utils.BackendParser;
import com.lucevent.newsup.backend.utils.Event;
import com.lucevent.newsup.backend.utils.EventNews;
import com.lucevent.newsup.backend.utils.Reports;
import com.lucevent.newsup.backend.utils.SiteSearchEngine;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.data.util.UserSite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Starting from app version 2.8.2
 */
public class AppServlet_v3 extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	{
		try {
			processPetition(req, resp);
		} catch (Exception e) {
			Data.notifyException(req, e, "AppServlet_v3");
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	{
		doGet(req, resp);
	}

	private void processPetition(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("utf-8");

		if (req.getParameter("content") != null) {

			resp_content(
					Integer.parseInt(req.getParameter("site")),
					req.getParameter("l"),
					resp
			);

		} else if (req.getParameter("news") != null) {

			resp_headers(
					req.getParameter("site"),
					req.getParameter("nc"),
					req.getRemoteAddr(),
					req.getParameter("v"),
					resp
			);

		} else if (req.getParameter("notify") != null) {

			String[] values = req.getParameter("values").split(",");
			for (int i = 0; i < values.length; i += 2) {
				Site site = Data.getSite(Integer.parseInt(values[i]));
				Data.stats.read(site, Integer.parseInt(values[i + 1]));
			}

		} else if (req.getParameter("notifyevent") != null) {

			Data.stats.countEvent(
					Integer.parseInt(req.getParameter("notifyevent")),
					req.getRemoteAddr(),
					req.getParameter("v")
			);

		} else if (req.getParameter("report") != null) {

			Reports.addReport(
					req.getParameter("version"),
					req.getRemoteAddr(),
					req.getParameter("email"),
					req.getParameter("message"));

		} else if (req.getParameter("events") != null) {

			resp_events(req.getParameter("events"), resp);

		} else if (req.getParameter("event") != null) {

			resp_event(Long.parseLong(req.getParameter("event")), resp);

		} else if (req.getParameter("alerts") != null) {

			resp_alerts(
					req.getParameter("v"),
					req.getParameter("alerts"),
					resp);

		} else if (req.getParameter("request_site") != null) {

			resp_request_site(req.getParameter("request_site"), resp);

		} else if (req.getParameter("poll") != null) {

			new Alerts().pollAnswer(
					Integer.parseInt(req.getParameter("poll")),
					Integer.parseInt(req.getParameter("ans"))
			);

		}
		resp.flushBuffer();
	}

	private void resp_headers(String site_request, String no_count, String address, String version, HttpServletResponse resp) throws IOException
	{
		String[] parts = site_request.split(",");
		Site site = Data.getSite(Integer.parseInt(parts[0]));

		if (no_count == null)
			Data.stats.count(site, address, version);
/*
        if (UpdateMessageCreator.needsUpdate(version)) {
            UpdateMessageCreator.generateUpdateNews(site, resp);
            return;
        }
*/
		NewsArray news = getHeaders(site, parts);

		resp.getWriter().println(BackendParser.toEntry(news).toString());
	}

	private NewsArray getHeaders(Site site, String[] parts)
	{
		int[] section_codes = new int[parts.length - 1];
		for (int i = 0; i < section_codes.length; i++)
			section_codes[i] = Integer.parseInt(parts[i + 1]);

		NewsArray news = site.readNewsHeaders(section_codes);
		site.news.addAll(news);

		return news;
	}

	private void resp_content(int site_code, String link, HttpServletResponse resp) throws IOException
	{
		Site site = Data.getSite(site_code);
		if (site == null)
			return;

		int id = link.hashCode();

		News news = site.news.get(id);
		if (news == null) {
			news = new News(id, "", link, "", -1, "", null, -1, -1, -1);
			news.content = "";
		}
		if (news.content.isEmpty())
			site.readNewsContent(news);

		if (!news.content.isEmpty()) {
			resp.getWriter().print(news.content);

			site.news.put(id, news);
		}
	}

	private static final long MAX_EVENT_TIME_ON = 12 * 60 * 60 * 1000; // 1/2 Day (ms)

	private void resp_events(String region_codes, HttpServletResponse resp) throws IOException
	{
		long currentTime = System.currentTimeMillis();
		ArrayList<Event> aux = new ArrayList<>(ofy().load().type(Event.class)
				.filter("visible", true)
				.filter("lastNewsTime >", currentTime - MAX_EVENT_TIME_ON)
				.list());

		String[] m_region_codes = region_codes.split(",");
		for (int i = aux.size() - 1; i >= 0; i--) {
			Event e = aux.get(i);
			// checking if regions requested match
			boolean match = false;
			for (String rc : m_region_codes)
				if (e.region_code.equals(rc)) {
					match = true;
					break;
				}

			if (!match)
				aux.remove(i);
		}

		TreeSet<Event> events = new TreeSet<>(new Comparator<Event>() {
			@Override
			public int compare(Event o1, Event o2)
			{
				int r = Long.compare(o1.lastNewsTime, o2.lastNewsTime);
				return r != 0 ? -r : 1;
			}
		});
		events.addAll(aux);

		resp.getWriter().println(BackendParser.json(events).toString());
	}

	private void resp_event(long event_code, HttpServletResponse resp) throws IOException
	{
		List<EventNews> event_news = ofy().load().type(EventNews.class)
				.filter("event_code", event_code)
				.list();

		resp.getWriter().println(BackendParser.toEntry(event_news).toString());
	}

	private void resp_alerts(String app_version, String lang, HttpServletResponse resp) throws IOException
	{
		Alerts alerts = new Alerts();

		if (app_version == null || app_version.isEmpty() ||
				!(app_version.startsWith("2.7.") || app_version.startsWith("2.8."))) {
			alerts.addUpdateAlert();
		} else {

			if (app_version.startsWith("2.8."))
				alerts.addPolls(lang);

			alerts.addRateAlert();
			alerts.addReportAlert();
		}

		resp.setContentType("json");
		resp.getWriter().println(BackendParser.json(alerts));
	}

	private void resp_request_site(String request_site, HttpServletResponse resp) throws IOException
	{
		SiteSearchEngine.Response r = SiteSearchEngine.search(request_site);
		if (r.result != UserSite.OK) {
			Reports.addReport(
					"servlet v2",
					"",
					"site request",
					"request:" + request_site + "\nresult code:" + r.result + "\nresult data:" + r.data.replace("\"", "'"));
		}

		resp.getWriter().println(
				"{\"result\":" + r.result + "," + r.data + "}"
		);
	}

}
