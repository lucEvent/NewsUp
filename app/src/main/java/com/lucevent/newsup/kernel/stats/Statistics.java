package com.lucevent.newsup.kernel.stats;

public class Statistics {

	public final Long since;

	public final Long lastStart;

	public final SiteStats siteStats;

	public Statistics(Long since, Long lastStart, SiteStats siteStats)
	{
		this.since = since;
		this.lastStart = lastStart;
		this.siteStats = siteStats;
	}

}
