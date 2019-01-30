package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DigitalTrends extends com.lucevent.newsup.data.util.NewsReader {

	// tags: [category, dc:creator, description, enclosure, guid, item, link, pubdate, thumbnail, title]

	public DigitalTrends()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_ENCLOSURE},
				"");
	}

	@Override
	protected String parseDescription(Element prop)
	{
		Elements ps = org.jsoup.Jsoup.parse(prop.text()).select("p");
		return ps.isEmpty() ? "" : ps.get(0).text();
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements header = doc.select(".m-header-media").select("img,iframe");
		Elements article = doc.select("article[itemprop='articleBody']");

		if (article.isEmpty()) {

			header = doc.select(".alpha .m-testimonial,.m-good-bad");
			article = doc.select("article[itemprop='reviewBody']");

			article.select(".m-linked-product,.m-comparable-products,.m-accessory-pack").remove();
		}

		article.select("script,.alignright,.m-related-video,.h-nonessential,.zoom-button,.m-image-credit,[itemprop='publisher'],.dtads-inject-hook,.m-aff-button").remove();

		article.select(".m-our-take").tagName("h3");
		header.select("iframe[height]").removeAttr("height");
		article.select("iframe[height]").removeAttr("height");

		for (Element e : article.select("img[src^='data']")) {
			e.attr("src", e.attr("data-dt-lazy-src"));
			cleanAttributes(e, "src");
		}

		for (Element i : article.select("meta[itemprop='embedUrl']"))
			i.parent().html(insertIframe(i.attr("content")));

		try {
			for (Element e : article.select("strong")) {
				String text = e.text();
				if (text.startsWith("More") || text.startsWith("Related"))
					e.parent().text("");
			}
			for (Element e : article.select("a img")) {
				String src = e.attr("src");
				if (src.endsWith("button-150x39.png") || src.endsWith("-smallest-325x325.jpg"))
					e.parent().remove();
			}
			for (Element e : article.select(".m-quick-take")) {
				e.select(".title").tagName("b");
				e.tagName("blockquote");
			}
		} catch (Exception ignored) {
		}
		header.select("[style]").removeAttr("style");
		article.select("[style]:not(.instagram-media,.instagram-media *)").removeAttr("style");

		return finalFormat(header, true) + finalFormat(article, true);
	}

}
