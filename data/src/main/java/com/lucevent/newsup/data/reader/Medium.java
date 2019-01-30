package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Medium extends com.lucevent.newsup.data.util.NewsReader {

	/**
	 * Tags:
	 * [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]
	 * [category,                  dc:creator, description, guid, item, link, pubdate, title]
	 * [category, content:encoded, dc:creator,              guid, item, link, pubdate, title]
	 */

	public Medium()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_GUID},
				new int[]{TAG_DESCRIPTION},
				new int[]{TAG_CONTENT_ENCODED},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{},
				"");
	}

	@Override
	protected String parseContent(Element prop)
	{
		Element article = jsoupParse(prop);
		article.select("script,[width=1]").remove();
		return finalFormat(article, false);
	}

	@Override
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		if (news.description != null) {
			Element article = jsoupParse(news.description);
			news.description = article.select(".medium-feed-snippet").text();
			news.imgSrc = findImageSrc(article);
		}
		return news;
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements e = doc.select("main section .section-content,.story-view__body");
		e.select("script,.graf--title,.story-view__tags,.story-view__data").remove();

		for (Element fig : e.select("figure:has(noscript)")) {
			fig.html(fig.select("noscript").html());
			cleanAttributes(fig);
		}

		e.select(".section-inner,.graf,.progressiveMedia-noscript").removeAttr("class");

		return finalFormat(e, false);
	}

	@Override
	protected Document getDocument(String url)
	{
		try {
			return org.jsoup.Jsoup.connect(url)
					.timeout(10000)
					.validateTLSCertificates(false)
					.userAgent(USER_AGENT)
					.get();
		} catch (Exception ignored) {
		}
		return null;
	}

}
