package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Ara extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [description, enclosure, guid, item, link, media:content, media:description, media:keywords, media:thumbnail, media:title, pubdate, title]

	public Ara()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{"media:keywords".hashCode()},
				new int[]{TAG_ENCLOSURE},
				"");
	}

	@Override
	protected String parseDescription(Element prop)
	{
		return jsoupParse(prop).text();
	}

	@Override
	protected String readNewsContent(Document doc, String news_url)
	{
		if (news_url.contains("/fotografies/")) {
			Elements article = doc.select(".gallery-view");
			article.select("#slide_0,.slide_ender").remove();

			for (Element img : article.select("img[data-src]")) {
				String src = img.attr("data-src");
				cleanAttributes(img);
				img.attr("src", src);
			}

			article.html(article.select("img,figcaption").outerHtml());

			return finalFormat(article, false);
		}

		Elements article = doc.select(".inset-main figure,.mce-body");
		article.select("script,.md-ad-dfp,.holder-article-end,.tag-content-box,.inset-rel-story,.rel-content-box").remove();

		for (Element img : article.select("img[data-src]")) {
			String src = img.attr("data-src");
			cleanAttributes(img);
			img.attr("src", src);
		}

		cleanAttributes(article.select("figure a").tagName("figcaption"));

		return finalFormat(article, true);
	}

}
