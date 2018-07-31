package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;

public class Dogster extends com.lucevent.newsup.data.util.NewsReader {

	// Tags: [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

	public Dogster()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{TAG_CONTENT_ENCODED},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{},
				"");
	}

	@Override
	protected String parseDescription(Element prop)
	{
		return jsoupParse(prop).select("p").first().text();
	}

	@Override
	protected String parseContent(Element prop)
	{
		Element article = jsoupParse(prop);
		article.select("script").remove();

		article.select(".wp-caption-text").tagName("figcaption");
		article.select("[style]").removeAttr("style");
		article.select("[class^='m_'").removeAttr("class");

		cleanAttributes(article.select("img"), "src");

		return finalFormat(article, false);
	}

}