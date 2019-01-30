package com.lucevent.newsup.backend;

import com.lucevent.newsup.backend.utils.BackendParser;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.NewsMap;
import com.lucevent.newsup.data.util.Sections;
import com.lucevent.newsup.data.util.Site;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebServlet extends HttpServlet {

	private static NewsMap mNewsPool;

	@Override
	public void init() throws ServletException
	{
		super.init();
		new Data();
		mNewsPool = new NewsMap();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	{
		try {
			processPetition(req, resp);
		} catch (Exception e) {
			Data.notifyException(req, e, "WebServlet");
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	{
		try {
			processPetition(req, resp);
		} catch (Exception e) {
			Data.notifyException(req, e, "WebServlet");
		}
	}

	private void processPetition(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("utf-8");

		if (req.getParameter("index") != null) {

			resp_headers(
					req.getParameter("site"),
					req.getRemoteAddr(),
					resp
			);

		} else if (req.getParameter("content") != null) {

			resp_content(
					Data.sites.getSiteByCode(Integer.parseInt(req.getParameter("site"))),
					Integer.parseInt(req.getParameter("nid")),
					resp
			);

		} else if (req.getParameter("sections") != null) {

			resp_sections(
					Data.sites.getSiteByCode(Integer.parseInt(req.getParameter("site"))),
					resp
			);

		} else if (req.getParameter("sites") != null) {

			resp_sites(resp);

		}
	}

	private void resp_headers(String site_request, String address, HttpServletResponse resp) throws IOException
	{
		String[] parts = site_request.split(",");

		Site site = Data.sites.getSiteByCode(Integer.parseInt(parts[0]));
		Data.stats.count(site, address, "web");

		int[] section_codes = new int[parts.length - 1];
		Sections sections = site.getSections();
		for (int i = 0; i < section_codes.length; i++) {
			int section_index = Integer.parseInt(parts[i + 1]);
			if (section_index < sections.size())
				section_codes[i] = sections.get(section_index).code;
		}

		NewsArray res = site.readNewsHeaders(section_codes);
		mNewsPool.addAll(res);

		resp.setContentType("json");
		resp.getWriter().println(
				BackendParser.json(res)
		);
	}

	private void resp_content(Site site, int news_id, HttpServletResponse resp) throws IOException
	{
		News n = mNewsPool.get(news_id);
		if (n != null) {

			if (n.content.isEmpty()) {
				String content = site.readNewsContent(n.link);
				if (content != null)
					n.content = content;
			}

			resp.getWriter().print(site.getStyle() + n.content);
		}
	}

	private void resp_sections(Site site, HttpServletResponse resp) throws IOException
	{
		resp.setContentType("json");
		resp.getWriter().println(BackendParser.json(site.getSections()));
	}

	private void resp_sites(HttpServletResponse resp) throws IOException
	{
		resp.setContentType("json");
		resp.getWriter().println(BackendParser.jsonSites());
	}

}

