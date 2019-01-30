package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FinlandToday extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [category, dc:creator, description, guid, item, link, pubdate, title]

	public FinlandToday()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{},
				"");
	}

	@Override
	protected String parseDescription(Element prop)
	{
		Elements dscr = jsoupParse(prop).select("p");
		return dscr.isEmpty() ? "" : dscr.first().text();
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select(".entry-content");
		article.select("script,.finla-pilke-mobile-abf,.finla-local-bistro,br,[style*='clear:both'],.finla-adsense-infeed-4-after-content,.et_social_bottom_trigger").remove();

		article.select("div[style]").removeAttr("style");
		article.select(".wp-caption-text").tagName("figcaption");
		cleanAttributes(article.select("img[src]"), "src");

		return finalFormat(article, false);
	}

}
