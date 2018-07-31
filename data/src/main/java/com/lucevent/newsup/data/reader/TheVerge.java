package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;

public class TheVerge extends com.lucevent.newsup.data.util.NewsReader {

	// tags: [author, content, entry, id, link, name, published, title, updated]

	public TheVerge()
	{
		super(TAG_ITEM_ENTRY,
				new int[]{TAG_TITLE},
				new int[]{TAG_ID},
				new int[]{},
				new int[]{TAG_CONTENT},
				new int[]{TAG_UPDATED},
				new int[]{},
				new int[]{},
				"");
	}

	@Override
	protected String parseContent(Element prop)
	{
		Element article = jsoupParse(prop);
		article.select("script").remove();

		article.select("figure cite,.caption,q").tagName("figcaption");
		article.select("[style]:not(.instagram-media [style]").removeAttr("style");

		for (Element e : article.select(".c-float-right,.c-float-left"))
			e.tagName("blockquote").removeAttr("class");

		return finalFormat(article, false);
	}

}
