package com.lucevent.newsup.data.util;

public class Section {

	public final String name;
	public final String url;
	public final int level;
	public final int code;

	public Section(String name, String url, int level)
	{
		this.name = name;
		this.url = url;
		this.level = level;
		this.code = name.hashCode();
	}

}
