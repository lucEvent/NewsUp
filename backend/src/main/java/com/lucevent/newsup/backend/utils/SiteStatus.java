package com.lucevent.newsup.backend.utils;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Entity
public class SiteStatus {

	public static final int STATUS_WORKING = 0;
	public static final int STATUS_NOT_WORKING = 1;
	public static final int STATUS_UNDER_REVISION = 2;
	public static final int STATUS_REMOVED = 3;
	public static final int STATUS_ON_DEVELOPMENT = 4;
	public static final int STATUS_SCHEDULED = 5;

	@Id
	@Index
	public long code;

	public String name;

	public int info;

	public int status;

	public long last_check_time, last_check_duration;

	public int last_check_rounds;

	public int current_num_news, current_num_news_without_content;

	public SiteStatus()
	{
	}

	public static SiteStatus makeInstance(int code, String name, int info)
	{
		SiteStatus r = new SiteStatus();
		r.code = code;
		r.name = name;
		r.info = info;
		r.status = STATUS_SCHEDULED;
		r.last_check_time = 0;
		r.last_check_duration = 0;
		r.last_check_rounds = 0;
		r.current_num_news = 0;
		r.current_num_news_without_content = 0;
		return r;
	}

	public static SiteStatus getInstance(int code)
	{
		return ofy().load().type(SiteStatus.class).id(code).now();
	}

	public static List<SiteStatus> getAllInstances()
	{
		return ofy().load().type(SiteStatus.class).list();
	}

	public void save()
	{
		ofy().save().entity(this).now();
	}

}
