package com.lucevent.newsup.debugbackend.data;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Entity
public class Task {

	@Id
	@Index
	public Long id;

	@Index
	public long startTime;

	@Index
	public long finishTime;

	@Unindex
	public int rounds;

	@Unindex
	public int currentEvaluatingSite;

	@Unindex
	public int currentEvaluatingSection;

	@Unindex
	public int totalNumNews;

	@Unindex
	public int totalTestResults[];

	@Unindex
	public int siteNumNews;

	@Unindex
	public int siteTestResults[];

	public Task()
	{
	}

	public void newRound()
	{
		rounds++;
		save();
	}

	public void finish()
	{
		finishTime = System.currentTimeMillis();
		save();
	}

	public void save()
	{
		ofy().save().entity(this).now();
	}

	public void saveDeferred()
	{
		ofy().defer().save().entity(this);
	}

}
