package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public class TheVerge extends com.lucevent.newsup.data.util.NewsReader {

	// tags: [author, content, entry, id, link, name, published, title, updated]

	public TheVerge()
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
		if (!news.content.isEmpty()) {
			Element article = jsoupParse(news.content);
			article.select("script").remove();

			article.select("figure cite,.caption,q").tagName("figcaption");
			article.select("[style]:not(.instagram-media [style])").removeAttr("style");

			for (Element e : article.select(".c-float-right,.c-float-left"))
				e.tagName("blockquote").removeAttr("class");

			news.imgSrc = findImageSrc(article);
			news.content = finalFormat(article, false);
		}
		return news;
	}

}
