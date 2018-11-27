package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;

public class ElDiario extends com.lucevent.newsup.data.util.NewsReader {

	/**
	 * Tags
	 * [author, description, enclosure, guid, item, link, media:content, media:keywords, media:thumbnail, media:title, pubdate, title]
	 * [author, description, guid, item, link, pubdate, title]
	 * [description, enclosure, guid, item, link, media:content, media:keywords, media:thumbnail, media:title, pubdate, title]
	 */

	public ElDiario()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{},
				new int[]{TAG_DESCRIPTION},
				new int[]{TAG_PUBDATE},
				new int[]{"media:keywords".hashCode()},
				new int[]{TAG_ENCLOSURE},
				"");
	}

	@Override
	protected String parseContent(Element prop)
	{
		org.jsoup.nodes.Element article = jsoupParse(prop.text());
		article.select("script,img[width='1'],br").remove();
		article.select("[style]").removeAttr("style");
		article.select("[data-mce-src]").removeAttr("data-mce-src");

		return finalFormat(article, false);
	}

}
