package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ElMundo extends com.lucevent.newsup.data.util.NewsReader {

	// tags: category, dc:creator, description, guid, item, link, media:content, media:description, media:thumbnail, media:title, pubdate, title

	public ElMundo()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_MEDIA_CONTENT},
				"");
	}

	@Override
	protected String parseDescription(Element prop)
	{
		org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(prop.text());
		doc.select("a,img").remove();
		return doc.text();
	}

	@Override
	protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
	{
		Elements article = doc.select("div[itemprop='articleBody']:not(#tamano div[itemprop='articleBody']),#tamano");
		article.select("script,aside,footer,link,time,.summary-lead,br,.pie-foto,.publicidad").remove();

		article.select("noscript").tagName("div");
		article.select("[style]").removeAttr("style");

		if (!article.isEmpty())
			news.content = finalFormat(article, false);
	}

}

