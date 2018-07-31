package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Omicrono extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [category, content:encoded, dc:creator, description, feedburner:origlink, guid, item, link, pubdate, title]

	public Omicrono()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{"feedburner:origlink".hashCode()},
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
		Element article = jsoupParse(prop);
		Elements e = article.select("p");
		if (!e.isEmpty())
			return e.first().text();
		return article.text();
	}

	@Override
	protected String parseContent(Element prop)
	{
		Element article = jsoupParse(prop);
		article.select("script,.blockquoteLink,.feedflare,[width='1'],.blockquoteRelated").remove();
		cleanAttributes(article.select("img"), "src");
		return finalFormat(article, false);
	}

}
