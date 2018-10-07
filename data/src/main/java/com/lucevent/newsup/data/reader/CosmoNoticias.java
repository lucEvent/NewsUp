package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public class CosmoNoticias extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [category, content:encoded, dc:creator, description, guid, item, link, post-id, pubdate, title]

	public CosmoNoticias()
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
		return jsoupParse(prop).text().replace(" … Sigue leyendo →", "...");
	}

	@Override
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		if (!news.content.isEmpty()) {
			Element article = jsoupParse(news.content);
			article.select("script").remove();

			article.select(".wp-caption-text").tagName("figcaption");
			article.select("[style]").removeAttr("style");
			article.select("[onclick]").removeAttr("onclick");

			cleanAttributes(article.select("img"), "src");

			news.imgSrc = findImageSrc(article);
			news.content = finalFormat(article, false);
		}
		return news;
	}

}
