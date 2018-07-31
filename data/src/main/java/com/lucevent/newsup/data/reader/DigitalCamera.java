package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;

public class DigitalCamera extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

	public DigitalCamera()
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
	protected String parseContent(Element prop)
	{
		Element article = jsoupParse(prop);
		article.select("script").remove();
		article.select("[style]").removeAttr("style");
		article.select("[id]").removeAttr("id");
		article.select(".wp-caption-text").tagName("figcaption");
		return finalFormat(article, false).replace("<p>&nbsp;</p>", "");
	}

}
