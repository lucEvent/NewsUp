package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public class Nintenderos extends com.lucevent.newsup.data.util.NewsReader {

	// tags: [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

	public Nintenderos()
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
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		Element article = jsoupParse(news.content);
		article.select("script").remove();
		cleanAttributes(article.select("img[src]"), "src");

		news.content = finalFormat(article, false);
		news.imgSrc = findImageSrc(article);
		return news;
	}

}
