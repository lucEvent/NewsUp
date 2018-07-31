package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;

import org.jsoup.nodes.Element;

public class RacoCatala extends com.lucevent.newsup.data.util.NewsReader {

	// tags: category, description, guid, image, item, link, pubdate, title

	public RacoCatala()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{},
				new int[]{TAG_DESCRIPTION},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_IMAGE},
				"");
	}

	@Override
	protected Enclosure parseEnclosure(Element prop)
	{
		return new Enclosure(prop.text().replace("/100/100/", "/700/400/"), "image", "");
	}

	@Override
	protected String parseContent(Element prop)
	{
		Element article = jsoupParse(prop);
		article.select("script").remove();
		return finalFormat(article, false);
	}

}
