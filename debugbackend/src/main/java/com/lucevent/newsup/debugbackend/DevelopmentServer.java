package com.lucevent.newsup.debugbackend;

import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.debugbackend.db.Bug;
import com.lucevent.newsup.debugbackend.db.Task;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
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
		resp.setCharacterEncoding("utf-8");
		StringBuilder sb = new StringBuilder();

		if (req.getParameter("bugs") != null) {
			sb.append("{\"bugs\":[");

			List<Bug> bugs = Bug.getAll();

			boolean needsComma = false;
			boolean withContent = req.getParameter("withContent") != null;
			for (Bug b : bugs) {
				if (needsComma)
					sb.append(",");
				else needsComma = true;

				sb.append("{\"id\":").append(b.getId())
						.append(",\"sc\":").append(b.getSiteCode())
						.append(",\"sn\":\"").append(sites.getSiteByCode((int) b.getSiteCode()).name)
						.append("\",\"t\":").append(b.getTime())
						.append(",\"d\":\"").append(URLEncoder.encode(b.getDescription(), "utf-8"));

				if (withContent)
					sb.append("\",\"l\":\"").append(b.getLink())
							.append("\",\"c\":\"").append(URLEncoder.encode(b.getContent(), "utf-8"));

				sb.append("\"}");
			}

			sb.append("]}");
		} else if (req.getParameter("tasks") != null) {
			sb.append("{\"tasks\":[");

			TreeSet<Task> tasks = Task.getAll();

			boolean needsComma = false;
			for (Task t : tasks) {
				if (needsComma)
					sb.append(",");
				else needsComma = true;

				sb.append("{\"id\":").append(t.getId())
						.append(",\"cur\":").append(t.getCurrentEvaluatingSite())
						.append(",\"sta\":").append(t.getStartTime())
						.append(",\"fin\":").append(t.getEndTime())
						.append(",\"rou\":").append(t.getRounds())
						.append(",\"num\":").append(t.getTotalNumNews())
						.append(",\"res\":\"").append(t.getTotalTestResults().toString())
						.append("\"}");
			}

			sb.append("]}");
		} else if (req.getParameter("delete") != null) {

			if (req.getParameter("task_id") != null) {

				boolean b = Task.delete(
						Long.parseLong(req.getParameter("task_id"))
				);

				sb.append("{\"result\":").append(b ? "1" : "0").append("}");

			} else if (req.getParameter("bug_id") != null) {

				boolean b = Bug.delete(
						Long.parseLong(req.getParameter("bug_id"))
				);

				sb.append("{\"result\":").append(b ? "1" : "0").append("}");

			} else return;

		}

		resp.setContentType("json");
		resp.getWriter().println(sb);
		resp.flushBuffer();
	}

	static private Sites sites;

	@Override
	public void init() throws ServletException
	{
		super.init();

		sites = Sites.getDefault(true);
	}

}
