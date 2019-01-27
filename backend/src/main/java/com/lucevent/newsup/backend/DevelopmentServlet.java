package com.lucevent.newsup.backend;

import com.lucevent.newsup.backend.db.Event;
import com.lucevent.newsup.backend.db.Poll;
import com.lucevent.newsup.backend.db.Report;
import com.lucevent.newsup.backend.db.SiteStatus;
import com.lucevent.newsup.backend.utils.Alerts;
import com.lucevent.newsup.backend.utils.BackendParser;
import com.lucevent.newsup.backend.utils.EventSource;
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

public class DevelopmentServlet extends HttpServlet {

	private static final int DEVELOPER_HASH_CODE = -80680689;

	@Override
	public void init() throws ServletException
	{
		super.init();
		new Data();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		try {
			processPetition(req, resp);
		} catch (Exception e) {
			Data.notifyException(req, e, "DevelopmentServlet");
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	{
		try {
			processPetition(req, resp);
		} catch (Exception e) {
			Data.notifyException(req, e, "DevelopmentServlet");
		}
	}

	private void processPetition(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("utf-8");

		if (req.getParameter("stats") != null) {

			if (req.getParameter("reset") != null) {
				String resetPassword = req.getParameter("pass");
				if (resetPassword != null && resetPassword.hashCode() == DEVELOPER_HASH_CODE)
					Data.stats.reset();
			}

			resp_stats(
					req.getParameter("options"),
					req.getParameter("only"),
					resp
			);

		} else if (req.getParameter("reports") != null) {

			resp_reports(resp);

		} else if (req.getParameter("search_sections") != null) {

			resp_key_sections(req.getParameter("key").split(","), resp);

		} else if (req.getParameter("events") != null) {

			resp_events(resp);

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
				Report.delete(report_id);
				resp.getWriter().println("{\"result\":1}");
			}

		} else if (req.getParameter("new_status") != null) {

			resp_new_status(req);

		}

	}

	private void resp_stats(String options, String filters, HttpServletResponse resp) throws IOException
	{
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

		Reports reports = Reports.getAll();

		boolean needsComma = false;
		for (Report r : reports) {
			if (needsComma)
				sb.append(",");
			else needsComma = true;

			sb.append("{\"id\":").append(r.getId())
					.append(",\"ti\":").append(r.getTime())
					.append(",\"ve\":\"").append(r.getAppVersion())
					.append("\",\"ip\":\"").append(r.getIP())
					.append("\",\"em\":\"").append(r.getEmail())
					.append("\",\"msg\":\"").append(r.getMessage())
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

	private static final long MAX_EVENT_TIME_ON = 12 * 60 * 60 * 1000; // 1/2 Day (ms)

	private void resp_events(HttpServletResponse resp) throws IOException
	{
		List<Event> events = Event.getAll(0);
		StringBuilder sb = new StringBuilder("<data>");
		for (Event E : events) {
			sb.append("<event code='")
					.append(E.getCode())
					.append("' visible=true'")  // // TODO: 16/01/2019 remove
					.append("' imgsrc='")
					.append(E.getImgSrc())
					.append("' start='")    //  // TODO: 31/10/2018 merge start and end and only send lastTime
					.append(E.getLastNewsTime())
					.append("' end='")
					.append(E.getLastNewsTime() + MAX_EVENT_TIME_ON)
					.append("' sources='");

			int[][] eventSources = EventSource.getSources(E.getRegionCode());
			for (int i = 0; i < eventSources.length; i++) {
				if (i != 0) sb.append(";");
				sb.append(eventSources[i][0])                    // siteCode
						.append(",").append(eventSources[i][1]); // sectionCode
			}

			ArrayList<String> tags = E.getTags();
			sb.append("' tags='");
			for (int i = 0; i < tags.size(); i++) {
				if (i != 0) sb.append(",");
				sb.append(tags.get(i));
			}

			sb.append("'>");

			sb.append("<description lang='").append(E.getRegionCode())
					.append("' title=\"").append(E.getTitle())
					.append("\"/>");

			sb.append("</event>");
		}
		sb.append("</data>");

		resp.getWriter().println(sb.toString());
	}

	private void resp_removeEvent(long event_code, HttpServletResponse resp) throws IOException
	{
		resp.getWriter().println(
				Event.delete(event_code) ?
						"Event removed successfully" :
						"Event not found"

		);
	}

	private void resp_polls(HttpServletResponse resp) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("{\"polls\":[");

		ArrayList<Poll> polls = Poll.getAll();
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
				if (a.id == p.getId()) {
					poll_alert = a;
					break;
				}

			sb.append("{\"id\":").append(p.getId())
					.append(",\"q\":\"").append(poll_alert.message)
					.append("\",\"a1\":").append(p.getAnswer1Code())
					.append(",\"a2\":").append(p.getAnswer2Code())
					.append(",\"a3\":").append(p.getAnswer3Code())
					.append(",\"c1\":").append(p.getAnswer1Counter())
					.append(",\"c2\":").append(p.getAnswer2Counter())
					.append(",\"c3\":").append(p.getAnswer3Counter());

			sb.append("}");
		}
		sb.append("]}");

		resp.setContentType("json");
		resp.getWriter().println(sb);
	}

	private void resp_new_status(HttpServletRequest req)
	{
		int code = Integer.parseInt(req.getParameter("code"));
		String name = req.getParameter("name");
		int info = Integer.parseInt(req.getParameter("info"));

		SiteStatus ss = SiteStatus.get(code);
		if (ss == null)
			ss = SiteStatus.makeInstance(code, name, info);

		ss.setLastCheckTime(Long.parseLong(req.getParameter("check_time")));
		ss.setLastCheckDuration(Long.parseLong(req.getParameter("check_duration")));
		ss.setLastCheckRounds(Integer.parseInt(req.getParameter("check_rounds")));
		ss.setCurrentNumNews(Integer.parseInt(req.getParameter("num_news")));
		ss.setCurrentNumNewsWithoutContent(Integer.parseInt(req.getParameter("no_content")));

		if (ss.getStatus() == SiteStatus.STATUS_WORKING) {

			if (ss.getCurrentNumNews() == 0 || (ss.getCurrentNumNews() / 2) < ss.getCurrentNumNewsWithoutContent())
				ss.setStatus(SiteStatus.STATUS_NOT_WORKING);

		} else {

			if (ss.getCurrentNumNews() > 0 && (ss.getCurrentNumNews() / 2) > ss.getCurrentNumNewsWithoutContent())
				ss.setStatus(SiteStatus.STATUS_WORKING);

		}
		ss.save();
	}

}

