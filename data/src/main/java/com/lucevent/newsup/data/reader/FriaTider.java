package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class FriaTider extends com.lucevent.newsup.data.util.NewsReader {

	// tags: [comments, dc:creator, description, guid, item, link, pubdate, title]

	public FriaTider()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{},
				new int[]{},
				"");
	}

	@Override
	protected String readNewsContent(Document doc, String news_url)
	{
		Elements article = doc.select(".field-items,.standfirst");
		article.select("script,.bargraph").remove();

		article.select(".field-slideshow-credit,.field-slideshow-caption,.image-credit").tagName("figcaption");

		article.select("#bargraph").attr("style", "height:400px;");
		if (!article.isEmpty())
			return finalFormat(article, false);

		return null;
	}

	@Override
	protected Document getDocument(String url)
	{
		try {
			return org.jsoup.Jsoup.connect(url).timeout(10000).userAgent(USER_AGENT).get();
		} catch (Exception ignored) {
		}
		try {
			return org.jsoup.Jsoup.connect(url).timeout(10000).get();
		} catch (Exception ignored) {
		}
		return null;
	}

}
