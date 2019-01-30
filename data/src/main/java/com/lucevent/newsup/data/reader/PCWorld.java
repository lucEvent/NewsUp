package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PCWorld extends com.lucevent.newsup.data.util.NewsReader {

	/**
	 * tags:
	 * [description, guid, item, link, media:category, media:credit, media:description, media:keywords, media:thumbnail, media:title, pubdate, title]
	 * [author, categories, category, dc:creator, description, enclosure, item, link, pubdate, title]
	 */

	public PCWorld()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY, "media:category".hashCode()},
				new int[]{TAG_ENCLOSURE, "media:content".hashCode(), "media:thumbnail".hashCode()},
				"");
	}

	@Override
	protected String parseDescription(Element prop)
	{
		Element article = jsoupParse(prop);
		article.select(".jumpTag").remove();
		return article.text();
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		if (news_url.contains("pcworld.com/video"))
			return null;

		Elements article = doc.select("article");

		if (!article.isEmpty()) {
			Elements elems = article.select(".hero-img img,[itemprop='articleBody'],[itemprop='reviewBody']");

			if (elems.isEmpty()) {
				elems = article.select(".product-hub-panoramic,.product-hub-articleBody");

				if (elems.isEmpty()) {

					article = article.select(".description,#slideshowCarousel .slick-slide figure");
					article.select(".image-info,noscript").remove();
					article.select(".description,[itemprop=headline]").tagName("h4");
					article.select(".slide figcaption").tagName("p");

					for (Element img : article.select("img[data-original]")) {
						String src = img.attr("data-original");
						cleanAttributes(img);
						img.attr("src", src);
					}
				} else {
					article = elems;
				}
			} else
				article = elems;

			article.select("script,aside,.end-note,.credit,.product-list").remove();

		} else {

			article = doc.select("#slides");

			if (!article.isEmpty()) {
				article = article.select(".carousel-items .slide");

				for (Element slide : article) {
					cleanAttributes(slide);
					slide.tagName("p");
					slide.html(slide.select("img:not(.lazyslide),.title,.body").outerHtml());
				}
				article.select(".title").tagName("h3");
			}

		}
		article.select("[style]:not(.instagram-media,.instagram-media *)").removeAttr("style");

		return finalFormat(article, true);
	}

}