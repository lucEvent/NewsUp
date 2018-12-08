package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public class iFixit extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [category, content:encoded, dc:creator, description, feedburner:origlink, guid, item, link, pubdate, title]

	public iFixit()
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
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		Element article = jsoupParse(news.content);
		article.select("script,img[width='1']").remove();
		article.select("div[style]:has(img)").removeAttr("style");
		article.select(".wp-caption-text").tagName("figcaption");

		news.imgSrc = findImageSrc(article);
		news.content = finalFormat(article, false);
		return news;
	}

}
