package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;

public class PokemonGoHub extends com.lucevent.newsup.data.util.NewsReader {

	// tags: [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

	public PokemonGoHub()
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
		article.select("img[src*='/user_avatar/'],script").remove();
		article.select("h6").tagName("h4");
		article.select("pre").tagName("blockquote");
		article.select("figure[style],img[style],iframe[style]").removeAttr("style");
		cleanAttributes(article.select("img[srcset]"), "src");

		return finalFormat(article, false);
	}

}
