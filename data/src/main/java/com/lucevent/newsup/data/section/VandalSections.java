package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class VandalSections extends Sections {

	public VandalSections()
	{
		super();

		add(new Section("Principal", "https://vandal.elespanol.com/xml.cgi", 0));
		add(new Section("Videos", "http://feeds.feedburner.com/VideosVandalOnline", 0));
		add(new Section("Blogs", "http://feeds.feedburner.com/BlogsVandalOnline", 0));

	}

}
