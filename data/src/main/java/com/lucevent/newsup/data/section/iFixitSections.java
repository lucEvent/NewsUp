package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class iFixitSections extends Sections {

	public iFixitSections()
	{
		super();

		add(new Section("Homepage", "http://feeds.ifixit.com/ifixitorg", 0));

	}

}
