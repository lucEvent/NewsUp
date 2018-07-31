package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class CounterPunch extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

	public CounterPunch()
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
		return jsoupParse(prop).text();
	}

	@Override
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		Element article = jsoupParse(news.content);
		article.select("script").remove();
		article.select("[style]").removeAttr("style");
		article.select(".wp-caption-text").tagName("figcaption");

		news.content = finalFormat(article, false);
		news.imgSrc = findImageSrc(article);
		return news;
	}

	@Override
	protected Document getDocument(String url)
	{
		try {
			return org.jsoup.Jsoup.connect(url)
					.userAgent(USER_AGENT)
					.timeout(10000)
					.validateTLSCertificates(false)
					.get();
		} catch (Exception ignored) {
		}
		return super.getDocument(url);
	}

}
