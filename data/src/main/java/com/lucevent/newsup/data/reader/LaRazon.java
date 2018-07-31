package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;

public class LaRazon extends com.lucevent.newsup.data.util.NewsReader {

	/**
	 * Tags
	 * [description, enclosure, guid, item, link, media:content, media:thumbnail, media:title, pubdate, source, title]
	 * [description, enclosure, guid, item, link, media:content, media:title, pubdate, source, title]
	 * [description, guid, item, link, media:content, media:title, pubdate, source, title]
	 */

	public LaRazon()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{},
				new int[]{TAG_DESCRIPTION},
				new int[]{TAG_PUBDATE},
				new int[]{},
				new int[]{TAG_MEDIA_CONTENT},
				"");
	}

	@Override
	protected String parseContent(Element prop)
	{
		Element article = jsoupParse(prop.text().replace("\u201D", "'"));
		article.select("script").remove();
		article.select("[style]").removeAttr("style");
		return finalFormat(article, false);
	}

}