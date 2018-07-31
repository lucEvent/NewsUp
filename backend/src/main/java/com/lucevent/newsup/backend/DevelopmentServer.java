package com.lucevent.newsup.backend;

import com.lucevent.newsup.backend.utils.Alerts;
import com.lucevent.newsup.backend.utils.BackendParser;
import com.lucevent.newsup.backend.utils.Event;
import com.lucevent.newsup.backend.utils.Poll;
import com.lucevent.newsup.backend.utils.Report;
import com.lucevent.newsup.backend.utils.Reports;
import com.lucevent.newsup.data.alert.Alert;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;
import com.lucevent.newsup.data.util.Site;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class DevelopmentServer extends HttpServlet {

	@Override
	public void init() throws ServletException
	{
		super.init();
		new Data();
	}

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

			resp_stats(
					req.getParameter("options"),
					req.getParameter("only"),
					req.getParameter("reset"),
					resp
			);

		} else if (req.getParameter("reports") != null) {

			resp_reports(resp);

		} else if (req.getParameter("search_sections") != null) {

			resp_key_sections(req.getParameter("key").split(","), resp);

		} else if (req.getParameter("events") != null) {

			resp_events(resp);

		} else if (req.getParameter("add_event") != null) {

			resp_addEvent(
					req.getParameter("data"),
					resp);

		} else if (req.getParameter("remove_event") != null) {

			resp_removeEvent(
					Long.parseLong(req.getParameter("code")),
					resp);

		} else if (req.getParameter("polls") != null) {

			resp_polls(resp);

		} else if (req.getParameter("delete") != null) {
			resp.setContentType("json");

			String par = req.getParameter("report_id");
			if (par != null) {
				long report_id = Long.parseLong(par);
				ofy().delete().type(Report.class).id(report_id).now();
				resp.getWriter().println("{\"result\":1}");
			}

		}

	}

	private void resp_stats(String options, String filters, String reset, HttpServletResponse resp) throws IOException
	{
		if (reset != null)
			Data.stats.reset();

		StringBuilder sb = new StringBuilder();
		sb.append("{")
				.append(BackendParser.json(Data.stats.getMonthStats()))
				.append(",")
				.append(BackendParser.json(
						Data.stats,
						options != null ? options : "",
						filters != null && !filters.isEmpty() ? filters.split(",") : null))
				.append(",")
				.append(BackendParser.json(Data.stats.getDistribution()))
				.append("}");

		resp.setContentType("json");
		resp.getWriter().println(sb);
	}

	private void resp_reports(HttpServletResponse resp) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("{\"reports\":[");

		Reports reports = new Reports();

		boolean needsComma = false;
		for (Report r : reports) {
			if (needsComma)
				sb.append(",");
			else needsComma = true;

			sb.append("{\"id\":").append(r.id)
					.append(",\"ti\":").append(r.time)
					.append(",\"ve\":\"").append(r.appVersion)
					.append("\",\"ip\":\"").append(r.ip)
					.append("\",\"em\":\"").append(r.email)
					.append("\",\"msg\":\"").append(r.message)
					.append("\"}");
		}
		sb.append("]}");

		resp.setContentType("json");
		resp.getWriter().println(sb);
	}

	private void resp_key_sections(String[] keys, HttpServletResponse resp) throws IOException
	{
		for (int i = 0; i < keys.length; i++)
			keys[i] = keys[i].toLowerCase();

		StringBuilder sb = new StringBuilder("[");
		for (Site s : Data.sites) {
			Sections sections = s.getSections();
			for (int i = 0; i < sections.size(); i++) {
				Section sec = sections.get(i);
				for (String key : keys) {
					if (key.equals(sec.name.toLowerCase())) {
						if (sb.length() > 1)
							sb.append(",");

						String pname = "";
						if (sec.level > 0) {
							int j = i - 1;
							while (sections.get(j).level > 0)
								j--;
							pname = sections.get(j).name;
						}
						sb.append("{\"level\":").append(sec.level)
								.append(",\"name\":\"").append(sec.name)
								.append("\",\"code\":").append(sec.code)
								.append(",\"pname\":\"").append(pname)
								.append("\",\"scode\":").append(s.code)
								.append(",\"sname\":\"").append(s.name)
								.append("\"}");
					}
				}
			}
		}
		sb.append("]");
		resp.getWriter().println(sb);
	}

	private void resp_events(HttpServletResponse resp) throws IOException
	{
		List<Event> events = ofy().load().type(Event.class).list();
		StringBuilder sb = new StringBuilder("<data>");
		for (Event E : events) {
			sb.append("<event code='")
					.append(E.code)
					.append("' visible='")
					.append(E.visible)
					.append("' imgsrc='")
					.append(E.imgSrc)
					.append("' start='")
					.append(E.startTime)
					.append("' end='")
					.append(E.endTime)
					.append("' sources='");

			for (int i = 0; i < E.sites.length; i++) {
				if (i != 0) sb.append(";");
				Event.EventSite s = E.sites[i];
				sb.append(s.site_code);
				for (int is : s.section_codes)
					sb.append(",").append(is);
			}

			sb.append("' tags='");
			for (int i = 0; i < E.tags.length; i++) {
				if (i != 0) sb.append(",");
				sb.append(E.tags[i]);
			}

			sb.append("'>");

			for (int i = 0; i < E.info.length; i++) {
				Event.EventInfo eventInfo = E.info[i];
				sb.append("<description lang='")
						.append(eventInfo.lang)
						.append("' title=\"")
						.append(eventInfo.title)
						.append("\" topic=\"")
						.append(eventInfo.topic)
						.append("\"/>");
			}
			sb.append("</event>");
		}
		sb.append("</data>");

		resp.getWriter().println(sb.toString());
	}

	private void resp_addEvent(String event_data, HttpServletResponse resp) throws IOException
	{
		try {
			Event newEvent = Event.parse(event_data);
			if (newEvent != null) {
				Event e = ofy().load().type(Event.class).id(newEvent.code).now();
				if (e == null) {
					ofy().save().entity(newEvent).now();
					resp.getWriter().println("Event saved successfully");
				} else {
					e.visible = newEvent.visible;
					e.imgSrc = newEvent.imgSrc;
					e.startTime = newEvent.startTime;
					e.endTime = newEvent.endTime;
					e.tags = newEvent.tags;
					e.info = newEvent.info;
					e.sites = newEvent.sites;
					ofy().save().entity(e).now();
					resp.getWriter().println("Event updated successfully");
				}
			} else
				resp.getWriter().println("Unknown error");
		} catch (Exception e) {
			resp.getWriter().println(e.getMessage());
		}
	}

	private void resp_removeEvent(long event_code, HttpServletResponse resp) throws IOException
	{
		Event e = ofy().load().type(Event.class).id(event_code).now();
		if (e == null) {
			resp.getWriter().println("Event not found");
		} else {
			ofy().delete().type(Event.class).id(event_code).now();
			resp.getWriter().println("Event removed successfully");
		}
	}

	private void resp_polls(HttpServletResponse resp) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("{\"polls\":[");

		ArrayList<Poll> polls = new ArrayList<>(ofy().load().type(Poll.class).list());
		Alerts alerts = new Alerts();
		alerts.addPolls("en");
		alerts.addOldPolls();

		boolean needsComma = false;
		for (Poll p : polls) {
			if (needsComma)
				sb.append(",");
			else needsComma = true;

			Alert poll_alert = null;
			for (Alert a : alerts)
				if (a.id == p.id) {
					poll_alert = a;
					break;
				}

			sb.append("{\"id\":").append(p.id)
					.append(",\"q\":\"").append(poll_alert.message)
					.append("\",\"a1\":").append(p.answer_1_code)
					.append(",\"a2\":").append(p.answer_2_code)
					.append(",\"a3\":").append(p.answer_3_code)
					.append(",\"c1\":").append(p.answer_1_counter)
					.append(",\"c2\":").append(p.answer_2_counter)
					.append(",\"c3\":").append(p.answer_3_counter);

			sb.append("}");
		}
		sb.append("]}");

		resp.setContentType("json");
		resp.getWriter().println(sb);
	}

}

