package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RollingStone extends com.lucevent.newsup.data.util.NewsReader {

	// tags: [category, dc:creator, description, guid, item, link, post-id, pubdate, title]

	public RollingStone()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{},
				"");
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select("article .c-picture__frame,.c-content");
		article.select("script, q.c-picture__badge,.admz,.l-article-content__pull").remove();
		article.select("[hidden]").removeAttr("hidden");
		article.select(".wp-caption-text").tagName("figcaption");

		for (Element e : article.select("[data-src]"))
			e.attr("src", e.attr("data-src"))
					.removeAttr("data-src");

		cleanAttributes(article.select("img"), "src");

		return finalFormat(article, false);
	}

}
