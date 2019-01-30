package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class UniverseToday extends com.lucevent.newsup.data.util.NewsReader {

	// tags: [category, dc:creator, description, guid, item, link, post-id, pubdate, title]

	public UniverseToday()
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
	protected String parseDescription(Element prop)
	{
		return jsoupParse(prop).select("p").first().text();
	}

	@Override
	protected Document getDocument(String url)
	{
		try {
			return org.jsoup.Jsoup.connect(url)
					.ignoreContentType(true)
					.userAgent(USER_AGENT)
					.timeout(10000)
					.get();
		} catch (Exception ignored) {
		}
		return null;
	}

	@Override
	protected String readNewsContent(Document doc, String news_url)
	{
		Elements article = doc.select(".entry-content");
		article.select("script,.unive-in-content,.sharedaddy").remove();

		cleanAttributes(article.select("figure"));
		article.select("[style]").removeAttr("style");

		return finalFormat(article, false);
	}

}