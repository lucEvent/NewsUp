package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TheNewYorkTimes extends com.lucevent.newsup.data.util.NewsReader {

	/**
	 * tags:
	 * [atom:link, category, dc:creator, description, guid, item, link, media:content, media:credit, media:description, pubdate, title]
	 * [           category, dc:creator, description, guid, item, link, media:content, media:credit, media:description, pubdate, title]
	 * [content:encoded,                 description, guid, item, lastbuilddate, link,      media:content, media:group, pubdate, title]
	 * [category, dc:creator, description, enclosure, guid, item, link, media:content, pubdate, title]
	 * [category, dc:creator, description, guid, item, link, media:content, pubdate, title]
	 * [category, dc:creator, description, enclosure, guid, item, link, pubdate, title]
	 * [category, dc:creator, description, guid, item, link, pubdate, title]
	 * [author, description, guid, item, link, pubdate, title]
	 * [author, description, item, link, pubdate, title]
	 **/

	public TheNewYorkTimes()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_ENCLOSURE, TAG_MEDIA_CONTENT},
				"");
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		doc.select("[class^='RelatedCoverage']").remove();
		for (Element fig : doc.select("figure[itemid]:has(div[class^='LazyImage'])"))
			fig.select("div[class^='LazyImage']").first().attr("src", fig.attr("itemid")).tagName("img");

		Elements article = doc.select("article").select("header img[class^='Image-i'],header figcaption,.StoryBodyCompanionColumn > div,[class^='styles-youtubeIframe'],figcaption[class^='media-caption'],[itemprop='associatedMedia'] img,[itemprop='associatedMedia'] figcaption");

		cleanAttributes(article.select("img[src]"), "src");

		return finalFormat(article, true);
	}

}
