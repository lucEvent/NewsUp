package com.lucevent.newsup.backend;

import com.lucevent.newsup.backend.db.RequestedSite;
import com.lucevent.newsup.backend.db.Statistics;
import com.lucevent.newsup.backend.utils.Reports;
import com.lucevent.newsup.backend.utils.RequestedSites;
import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.Date;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.data.util.UserSite;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

public class Data {

	public static Sites sites;

	public static RequestedSites requestedSites;

	public static Statistics stats;

	public Data()
	{
		Date.setTitles(new String[]{"%d seconds ago", "%d minutes ago", "%d hours ago", "%d days ago", "%d months ago", "%d years ago",});

		if (sites == null) {
			sites = Sites.getDefault(true);
			// to avoid errors if some old version requests this dep site
			sites.add(new Site(330, "Metro", 0, "", 0, 0, 0, com.lucevent.newsup.data.util.Sections.class, com.lucevent.newsup.data.reader.MetroSV.class));
			sites.add(new Site(1710, "The Berry", 0, "", 0, 0, 0, com.lucevent.newsup.data.util.Sections.class, com.lucevent.newsup.data.reader.MetroSV.class));
			sites.add(new Site(885, "The Geek Hammer", 0, "", 0, 0, 0, com.lucevent.newsup.data.util.Sections.class, com.lucevent.newsup.data.reader.MetroSV.class));
			sites.add(new Site(1800, "Full M\u00FAsculo", 0, "", 0, 0, 0, com.lucevent.newsup.data.util.Sections.class, com.lucevent.newsup.data.reader.MetroSV.class));
			sites.add(new Site(1300, "Meristation", 0, "", 0, 0, 0, com.lucevent.newsup.data.util.Sections.class, com.lucevent.newsup.data.reader.MetroSV.class));
		}

		if (stats == null)
			stats = Statistics.getInstance();

		if (requestedSites == null)
			requestedSites = RequestedSites.getInstance();
	}

	public static Site getSite(int code)
	{
		Site r = sites.getSiteByCode(code);
		if (r != null)
			return r;

		RequestedSite rs = getRequestedSite(code);
		if (rs != null) {
			Site s = new UserSite((int) rs.getCode(), rs.getName(), (int) rs.getColor(), rs.getUrl(), (int) rs.getInfo(), rs.getRssUrl(), rs.getIconUrl());
			sites.add(s);
			return s;
		}
		return null;
	}

	public static RequestedSite getRequestedSite(int code)
	{
		return requestedSites.getSiteByCode(code);
	}

	public static RequestedSite getRequestedSite(String request)
	{
		return requestedSites.getSiteByRequest(request);
	}

	static void notifyException(HttpServletRequest req, Exception e, String servletName)
	{
		StringWriter writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		e.printStackTrace(printWriter);

		Reports.addReport(
				req.getParameter("v"),
				req.getRequestURL() + "?" + req.getQueryString(),
				servletName,
				writer.toString());
	}

}
