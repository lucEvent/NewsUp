package com.lucevent.newsup.debugbackend;

import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.debugbackend.data.Bug;
import com.lucevent.newsup.debugbackend.data.Database;
import com.lucevent.newsup.debugbackend.data.Task;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.googlecode.objectify.ObjectifyService.ofy;

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

			ArrayList<Bug> bugs = db.getBugs();

			boolean needsComma = false;
			boolean withContent = req.getParameter("withContent") != null;
			for (Bug b : bugs) {
				if (needsComma)
					sb.append(",");
				else needsComma = true;

				sb.append("{\"id\":").append(b.id)
						.append(",\"sc\":").append(b.site_code)
						.append(",\"sn\":\"").append(sites.getSiteByCode(b.site_code).name)
						.append("\",\"t\":").append(b.time)
						.append(",\"d\":\"").append(URLEncoder.encode(b.description, "utf-8"));

				if (withContent)
					sb.append("\",\"l\":\"").append(b.link)
							.append("\",\"c\":\"").append(URLEncoder.encode(b.content, "utf-8"));

				sb.append("\"}");
			}

			sb.append("]}");
		} else if (req.getParameter("tasks") != null) {
			sb.append("{\"tasks\":[");

			TreeSet<Task> tasks = db.getTasks();

			boolean needsComma = false;
			for (Task t : tasks) {
				if (needsComma)
					sb.append(",");
				else needsComma = true;

				sb.append("{\"id\":").append(t.id)
						.append(",\"cur\":").append(t.currentEvaluatingSite)
						.append(",\"sta\":").append(t.startTime)
						.append(",\"fin\":").append(t.finishTime)
						.append(",\"rou\":").append(t.rounds)
						.append(",\"num\":").append(t.totalNumNews)
						.append(",\"res\":\"").append(Arrays.toString(t.totalTestResults))
						.append("\"}");
			}

			sb.append("]}");
		} else if (req.getParameter("delete") != null) {

			if (req.getParameter("task_id") != null) {

				long task_id = Long.parseLong(req.getParameter("task_id"));
				ofy().delete().type(Task.class).id(task_id).now();
				sb.append("{\"result\":1}");

			} else if (req.getParameter("bug_id") != null) {

				long bug_id = Long.parseLong(req.getParameter("bug_id"));
				ofy().delete().type(Bug.class).id(bug_id).now();
				sb.append("{\"result\":1}");

			} else return;

		}

		resp.setContentType("json");
		resp.getWriter().println(sb);
		resp.flushBuffer();
	}

	static private Sites sites;
	static private Database db;

	@Override
	public void init() throws ServletException
	{
		super.init();

		sites = Sites.getDefault(true);
		db = new Database();
	}

}
