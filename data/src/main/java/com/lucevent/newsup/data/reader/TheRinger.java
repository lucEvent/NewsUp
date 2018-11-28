package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public class TheRinger extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [author, content, entry, id, link, name, published, title, updated]

	public TheRinger()
	{
		super(TAG_ITEM_ENTRY,
				new int[]{TAG_TITLE},
				new int[]{TAG_ID},
				new int[]{},
				new int[]{TAG_CONTENT},
				new int[]{TAG_UPDATED},
				new int[]{},
				new int[]{},
				"");
	}

	@Override
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		if (news.content != null && !news.content.isEmpty()) {
			Element article = jsoupParse(news.content);
			article.select("script").remove();

			news.description = article.select("p").first().text();
			news.imgSrc = findImageSrc(article);
			news.content = finalFormat(article, false);
		}
		return news;
	}

}
