package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public class LuckyPuppy extends com.lucevent.newsup.data.util.NewsReader {

	private static final String SITE_STYLE = "<style>ol{padding:0;}</style>";

	//tags: [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

	public LuckyPuppy()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{TAG_CONTENT_ENCODED},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{},
				SITE_STYLE);
	}

	@Override
	protected String parseDescription(Element prop)
	{
		Element d = jsoupParse(prop).select("p").first();
		return d != null ? d.text() : "";
	}

	@Override
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		Element article = jsoupParse(news.content);
		article.select("[style]").removeAttr("style");

		news.content = finalFormat(article, false);
		news.imgSrc = findImageSrc(article);
		return news;
	}

}
