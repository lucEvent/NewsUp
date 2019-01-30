package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HelsinkiTimes extends com.lucevent.newsup.data.util.NewsReader {

	//Tags:[author, category, description, guid, item, link, pubdate, title]

	public HelsinkiTimes()
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
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		Document doc = org.jsoup.Jsoup.parse(news.description);
		news.imgSrc = findImageSrc(doc);
		news.description = doc.text();
		return news;
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select(".article-content-main");
		article.select("script,.infobox").remove();

		article.select("[style]").removeAttr("style");

		return finalFormat(article, true);
	}

}