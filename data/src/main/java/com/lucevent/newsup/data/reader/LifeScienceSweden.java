package com.lucevent.newsup.data.reader;

import org.jsoup.select.Elements;

public class LifeScienceSweden extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [description, guid, item, link, pubdate, title]

	public LifeScienceSweden()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_GUID},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{"pubdate".hashCode()},
				new int[]{},
				new int[]{},
				"");
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select("article > h3,article > .body-text");
		article.select("script,.more-about").remove();

		article.select("[style]").removeAttr("style");
		article.select(".popup-gallery div").tagName("figcaption");

		return finalFormat(article, true);
	}

}
