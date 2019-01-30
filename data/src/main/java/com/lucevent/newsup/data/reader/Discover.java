package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Discover extends com.lucevent.newsup.data.util.NewsReader {

	// Tags: [description, guid, item, link, media:content, media:thumbnail, pubdate, title]

	public Discover()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_GUID},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{},
				new int[]{TAG_MEDIA_CONTENT},
				"");
	}

	@Override
	protected String parseLink(Element prop)
	{
		return prop.text();
	}

	@Override
	protected String parseDescription(Element prop)
	{
		return org.jsoup.Jsoup.parse(prop.text()).text();
	}

	@Override
	protected Enclosure parseEnclosure(Element prop)
	{
		return new Enclosure(prop.text(), "image", "");
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		if (doc.baseUri().contains("/galleries/")) {
			Elements article = doc.select(".gallery");

			Elements description = article.select(".description");
			Elements gallery = article.select(".display").select(".photo table img,.credit,h2,.caption");

			article.select("[style]").removeAttr("style");
			article.select(".caption,.credit").tagName("figcaption");

			return finalFormat(description, true) + finalFormat(gallery, true);
		}

		Elements article = doc.select(".entry > p,.entry img:not(.entry > p img,.wp-smiley),.entry > blockquote,.entry h4");

		if (article.isEmpty()) {
			article = doc.select(".segment .mediaContainer,.segment .content");
			article.select(".content").tagName("p");
		}
		article.select("script,.mobile").remove();
		article.select("[style]").removeAttr("style");

		for (Element e : article.select("span[title^='ctx_ver']"))
			e.parent().html("");

		cleanAttributes(article.select("img"), "src");

		return finalFormat(article, true).replace("<p>&nbsp;</p>", "");
	}

}