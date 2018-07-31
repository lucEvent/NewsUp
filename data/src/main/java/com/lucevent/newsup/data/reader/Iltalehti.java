package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Iltalehti extends com.lucevent.newsup.data.util.NewsReader {

	// tags [category, description, enclosure, guid, item, link, media:content, pubdate, title]

	public Iltalehti()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_ENCLOSURE},
				"");
	}

	@Override
	protected void readNewsContent(Document doc, News news)
	{
		Elements article = doc.select(".article__description,.article-body");

		if (!article.isEmpty()) {
			article.select(".article__image__preview").remove();

			article.select(".article__description").tagName("strong");
			article.select(".article__image__info").tagName("figcaption");

			cleanAttributes(article.select(".article__image,.article__image__container"));
			cleanAttributes(article.select("img[src]"), "src");

			news.content = finalFormat(article, true);
		}
	}

}
