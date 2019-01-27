package com.lucevent.newsup.backend.utils;

import com.lucevent.newsup.backend.db.RequestedSite;

import java.util.List;

public class RequestedSites {

	public List<RequestedSite> sites;

	public static RequestedSites getInstance()
	{
		RequestedSites r = new RequestedSites();
		r.sites = RequestedSite.getAll();
		return r;
	}

	public RequestedSite getSiteByCode(int code)
	{
		for (RequestedSite site : sites)
			if (site.getCode() == code)
				return site;

		return null;
	}

	public RequestedSite getSiteByRequest(String request)
	{
		for (RequestedSite site : sites)
			if (site.getOriginalRequest().equals(request))
				return site;

		return null;
	}

	public boolean add(RequestedSite rs)
	{
		return sites.add(rs);
	}

}
