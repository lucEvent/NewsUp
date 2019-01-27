package com.lucevent.newsup.backend.utils;

import com.lucevent.newsup.backend.db.Report;

import java.util.Comparator;
import java.util.TreeSet;

public class Reports extends TreeSet<Report> {

	public Reports()
	{
	}

	public Reports(Comparator<Report> c)
	{
		super(c);
	}

	public static Reports getAll()
	{
		Reports res = new Reports(new Comparator<Report>() {
			@Override
			public int compare(Report o1, Report o2)
			{
				return -Long.compare(o1.getTime(), o2.getTime());
			}
		});
		res.addAll(Report.getAll());
		return res;
	}

	public static void addReport(String version, String ip, String email, String message)
	{
		new Report(
				System.currentTimeMillis(),
				version,
				ip,
				email,
				message)

				.save();
	}

	public void deleteReport(long id)
	{
		Report.delete(id);
	}

}
