package com.lucevent.newsup.services.util;

public abstract class Schedule implements Comparable<Schedule> {

	public long time;

	public Schedule()
	{
	}

	@Override
	public int compareTo(Schedule another)
	{
		return this.time < another.time ? -1 : (this.time == another.time ? 0 : 1);
	}

}
