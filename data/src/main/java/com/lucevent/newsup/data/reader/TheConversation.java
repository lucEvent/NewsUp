package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public class TheConversation extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [author, description, guid, item, link, pubdate, title]

	public TheConversation()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{},
				new int[]{TAG_DESCRIPTION},
				new int[]{TAG_PUBDATE},
				new int[]{},
				new int[]{},
				"");
	}

	@Override
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		if (!news.content.isEmpty()) {
			Element article = jsoupParse(news.content);
			article.select("script,[width='1']").remove();
			article.select("[style]").removeAttr("style");

			news.content = finalFormat(article, false);
			news.imgSrc = findImageSrc(article);
		}
		return news;
	}

}
