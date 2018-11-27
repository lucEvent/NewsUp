package com.lucevent.newsup.development.reader;

public class FinlandToday extends com.lucevent.newsup.development.utils.NewsReader {

	//tags:

	public FinlandToday()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{TAG_CONTENT_ENCODED},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_ENCLOSURE},
				"style      // todo or empty");
	}

}
