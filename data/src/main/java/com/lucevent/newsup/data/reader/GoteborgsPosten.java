package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoteborgsPosten extends com.lucevent.newsup.data.util.NewsReader {

	/**
	 * Tags
	 * [category, dc:creator, description, guid, item, link, pubdate, title]
	 * [category, contentid, description, guid, item, link, pubdate, source, tag, title]
	 */

	public GoteborgsPosten()
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
	protected String readNewsContent(Document doc, String news_url)
	{
		Elements article = doc.select(".c-article__head figure,.c-article__body__content");

		Elements preamble = article.select(".article__preamble");
		if (!preamble.isEmpty()) {
			preamble.html("<p><b>" + preamble.text() + "</b></p>");
			preamble.tagName("p");
		}

		for (Element e : article.select("strong")) {
			String text = e.text();
			if (text.startsWith("L\u00C4S OCKS\u00C5")
					|| text.startsWith("L\u00C4S MER:")
					|| text.startsWith("L\u00C4S \u00C4VEN:"))
				try {
					e.parent().remove();
				} catch (Exception elemHasNotParent) {
				}
		}

		article.select(".article__body__facts").tagName("blockquote");
		article.select("[style]").removeAttr("style");
		article.select("a[data-iframely-url]").removeAttr("data-iframely-url");

		cleanAttributes(article.select("img"), "src");

		return finalFormat(article, true);
	}

	@Override
	protected Document getDocument(String url)
	{
		try {
			return org.jsoup.Jsoup.connect(url)
					.timeout(10000)
					.userAgent("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
					.get();
		} catch (Exception ignored) {
		}
		return super.getDocument(url);
	}

}
