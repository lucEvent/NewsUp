package com.lucevent.newsup.backend.utils;

import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class RequestedSites {

	public List<RequestedSite> sites;

	public static RequestedSites getInstance()
	{
		RequestedSites r = new RequestedSites();
		r.sites = ofy().load().type(RequestedSite.class).list();
		return r;
	}

	public RequestedSite getSiteByCode(int code)
	{
		for (RequestedSite site : sites)
			if (site.code == code)
				return site;

		return null;
	}

	public RequestedSite getSiteByRequest(String request)
	{
		for (RequestedSite site : sites)
			if (site.original_request.equals(request))
				return site;

		return null;
	}

	public RequestedSite createSite(String request, String name, String url, String rss_url, String icon_url, int info, int color)
	{
		RequestedSite r = new RequestedSite();
		r.code = 10000 + (Math.abs(url.hashCode()) % 10000);
		r.original_request = request;
		r.name = name;
		r.url = url;
		r.rss_url = rss_url.startsWith("/") ? url + rss_url : rss_url;
		r.icon_url = icon_url.startsWith("/") ? url + icon_url : icon_url;
		r.info = info;
		r.color = color;

		ofy().save().entity(r).now();
		sites.add(r);

		return r;
	}

}
