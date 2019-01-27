package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public class Dogster extends com.lucevent.newsup.data.util.NewsReader {

	// Tags: [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

	public Dogster()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{},
				new int[]{TAG_CONTENT_ENCODED},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{},
				"");
	}

	@Override
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		if (!news.content.isEmpty()) {
			Element article = jsoupParse(news.content);
			article.select("script").remove();

			article.select(".wp-caption-text").tagName("figcaption");
			article.select("[style]").removeAttr("style");
			article.select("[class^='m_']").removeAttr("class");

			cleanAttributes(article.select("img"), "src");

			news.imgSrc = findImageSrc(article);
			news.content = finalFormat(article, false);
		}
		return news;
	}

}