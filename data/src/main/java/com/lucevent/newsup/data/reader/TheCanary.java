package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TheCanary extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [category, dc:creator, description, enclosure, guid, item, link, post-id, pubdate, title]

	public TheCanary()
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
		Element dscr = jsoupParse(prop).select("p").first();
		return dscr == null ? "" : dscr.text();
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select(".post-header-img noscript,[itemprop='articleBody']");
		article.select("script,.in-content-ad,.subscribe-panel-wrap,.reveal,.related-posts-inline,#CAN_MPU_inline,h4:has(.lazyload)").remove();

		for (Element i : article.select("iframe[data-src]")) {
			i.attr("src", i.attr("data-src"))
					.removeAttr("data-src")
					.removeAttr("style");
			Element p = i.parent();
			if (p != null)
				p.removeAttr("style");
		}

		cleanAttributes(article.select("img[src]"), "src");

		return finalFormat(article, false);
	}

}
