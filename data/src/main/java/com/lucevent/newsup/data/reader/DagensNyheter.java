package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DagensNyheter extends com.lucevent.newsup.data.util.NewsReader {

	// tags: [dc:creator, dc:date, description, guid, item, link, media:content, media:credit, media:description, media:thumbnail, pubdate, title]

	public DagensNyheter()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{},
				new int[]{TAG_MEDIA_CONTENT},
				"");
	}

	@Override
	protected String parseDescription(org.jsoup.nodes.Element prop)
	{
		return org.jsoup.Jsoup.parse(prop.text()).text();
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select(".article__img,.article__content");
		if (article.isEmpty()) {
			article = doc.select(".article__header-img,.article__body");
			article.select(".article-toolbar,.byline,.article__widgets").remove();

			for (Element e : article.select(".image-box__container"))
				e.html(e.select("noscript").html());

			article.select(".image-box__caption").tagName("figcaption");
		}
		article.select("time,.paywall,.sharing,.author-box,.tools,.ad,.article-teaser-list").remove();

		article.select(".article__lead").tagName("h4");

		for (Element fbox : article.select(".fact-box,.js-article-block")) {
			fbox.tagName("blockquote");
			fbox.select("hidden").remove();
		}

		article.select("div[style]").removeAttr("style");
		cleanAttributes(article.select("img[src]"), "src");

		return finalFormat(article, false);
	}

}
