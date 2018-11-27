package com.lucevent.newsup.debugbackend.data;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Entity
public class PartialTestResult {

	@Id
	private Long id;

	@Index
	public Long taskId;

	@Unindex
	public int siteCode;

	@Unindex
	public int numNews;

	@Unindex
	public int testResults[];

	public PartialTestResult()
	{
	}

	public void save()
	{
		ofy().save().entity(this).now();
	}

}
