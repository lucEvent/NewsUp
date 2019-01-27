package com.lucevent.newsup.backend.utils;

import com.lucevent.newsup.backend.db.SiteStats;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

public class StatisticsSet extends TreeSet<SiteStats> {

	public StatisticsSet(ArrayList<SiteStats> stats, Comparator<SiteStats> comparator)
	{
		super(comparator);
		addAll(stats);
	}

	public static final Comparator<SiteStats> CMP_TOTAL_REQUESTS = new Comparator<SiteStats>() {
		@Override
		public int compare(SiteStats o1, SiteStats o2)
		{
			return o1.getTotalRequests() < o2.getTotalRequests() ? 1 : -1;
		}
	};

	public static final Comparator<SiteStats> CMP_MONTH_REQUESTS = new Comparator<SiteStats>() {
		@Override
		public int compare(SiteStats o1, SiteStats o2)
		{
			return o1.getMonthRequests() < o2.getMonthRequests() ? 1 : -1;
		}
	};

	public static final Comparator<SiteStats> CMP_LAST_REQUEST = new Comparator<SiteStats>() {
		@Override
		public int compare(SiteStats o1, SiteStats o2)
		{
			return o1.getLastRequest() < o2.getLastRequest() ? 1 : -1;
		}
	};

	public static final Comparator<SiteStats> CMP_SITE_NAME = new Comparator<SiteStats>() {
		@Override
		public int compare(SiteStats o1, SiteStats o2)
		{
			return o1.getSiteName().compareTo(o2.getSiteName());
		}
	};

	public static final Comparator<SiteStats> CMP_READINGS = new Comparator<SiteStats>() {
		@Override
		public int compare(SiteStats o1, SiteStats o2)
		{
			return o1.getReadings() < o2.getReadings() ? 1 : -1;
		}
	};

}
