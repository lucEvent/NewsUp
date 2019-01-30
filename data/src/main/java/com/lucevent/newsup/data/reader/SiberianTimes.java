package com.lucevent.newsup.data.reader;

import org.jsoup.select.Elements;

public class SiberianTimes extends com.lucevent.newsup.data.util.NewsReader {

	// tags: [category, description, enclosure, guid, item, link, pubdate, title, yandex:full-text]

	public SiberianTimes()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_ENCLOSURE},
				"");
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select(".topListItems");
		return finalFormat(article, false);
	}

}