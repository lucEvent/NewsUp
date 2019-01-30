package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URL;

public class Wired extends com.lucevent.newsup.data.util.NewsReader {

	// tags: [category, dc:creator, dc:modified, dc:publisher, description, guid, item, link, media:content, media:keywords, media:thumbnail, pubdate, title]

	public Wired()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{"media:thumbnail".hashCode()},
				"");
	}

	@Override
	protected Document getDocument(String url)
	{
		try {
			if (url.endsWith("rss"))
				return org.jsoup.Jsoup.connect(url)
						.timeout(10000)
						.userAgent(USER_AGENT)
						.validateTLSCertificates(false)
						.get();
			else
				return org.jsoup.Jsoup.parse(new URL(url).openStream(), "utf-8", "https://www.wired.com/");
		} catch (Exception ignored) {
		}
		return null;
	}

	@Override
	protected String readNewsContent(Document doc, String news_url)
	{
		Elements article = doc.select("main .article-lede-component__photo,.article-body-component");

		if (!article.isEmpty()) {
			article.select(".inset-left-component,.image-embed-component,.related-cne-video-component,.mid-banner-wrap").remove();
		} else {
			article = doc.select("main header .wp-caption,main .content");
			article.select(".ui-social-wrapper,.clearfix,#related,.visually-hidden,#article-tags").remove();
		}
		article.select("[data-reactid]").removeAttr("data-reactid");
		article.select(".special-carve").tagName("blockquote");

		cleanAttributes(article.select("img[src]"), "src");
		cleanAttributes(article.select("div[data-url]"));

		return finalFormat(article, false);
	}

}
