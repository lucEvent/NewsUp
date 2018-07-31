package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;

import org.jsoup.nodes.Element;

public class ChicagoSunTimes extends com.lucevent.newsup.data.util.NewsReader {

	/**
	 * Tags
	 * [category, content:encoded, dc:creator, description, enclosure, guid, item, link, media:content, media:credit, media:text, media:thumbnail, media:title, pubdate, title]
	 * [category, content:encoded, dc:creator, description,            guid, item, link,                                                                        pubdate, title]
	 */

	public ChicagoSunTimes()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{TAG_CONTENT_ENCODED},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_MEDIA_CONTENT},
				"");
	}

	@Override
	protected String parseDescription(Element prop)
	{
		return jsoupParse(prop).text();
	}

	@Override
	protected String parseContent(Element prop)
	{
		Element article = jsoupParse(prop);
		article.select("script,a[rel='nofollow'],img[width='1']").remove();

		article.select(".wp-caption-text").tagName("figcaption");
		article.select("[style]").removeAttr("style");
		article.select("iframe").attr("frameborder", "0");

		wpcomwidget(article.select("form"));
		article.select("form").remove();

		for (Element e : article.select("strong")) {
			String text = e.text();
			if (text.startsWith("RELATED STORY:")
					|| text.startsWith("READ MORE:"))
				try {
					e.parent().remove();
				} catch (Exception ignored) {
				}
		}
		cleanAttributes(article.select("img"), "src");

		return finalFormat(article, false).replaceAll("&nbsp;", "");
	}

	@Override
	protected Enclosure parseEnclosure(Element prop)
	{
		return new Enclosure(prop.attr("url"), "image", "");
	}

}
