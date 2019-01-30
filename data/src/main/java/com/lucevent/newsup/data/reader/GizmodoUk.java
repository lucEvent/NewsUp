package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GizmodoUk extends com.lucevent.newsup.data.util.NewsReader {

	// tags: [description, enclosure, guid, item, link, pubdate, source, title]

	public GizmodoUk()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{},
				new int[]{TAG_ENCLOSURE},
				"");
	}

	@Override
	protected String parseDescription(Element prop)
	{
		return jsoupParse(prop).text();
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select(".single-article").select(".single-article__canvas,.single-article__content");
		doc.select("script").remove();

		for (Element e : article.select("noscript"))
			e.parent().html(e.html());

		article.select("img + em").tagName("figcaption");

		Elements more = article.select(".grid,.site-content");
		for (Element e : more) {
			Element precedent = e.previousElementSibling();
			if (precedent.tagName().equals("h3"))
				precedent.remove();
		}
		more.remove();

		return finalFormat(article, false);
	}

}
