package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public class TED extends com.lucevent.newsup.data.util.NewsReader {

	/**
	 * Tags
	 * [category, description, guid, item, link, media:content, media:thumbnail, pubdate, title | enclosure, jwplayer:talkid ]
	 * [category, description, guid, item, link, media:content, media:thumbnail, pubdate, title | content:encoded, dc:creator, media:title]
	 */

	public TED()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{TAG_CONTENT_ENCODED},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_ENCLOSURE, "media:thumbnail".hashCode()},
				"");
	}

	@Override
	protected String parseDescription(Element prop)
	{
		return org.jsoup.Jsoup.parse(prop.text()).text();
	}

	@Override
	protected String parseContent(Element prop)
	{
		Element article = jsoupParse(prop);
		article.select("[rel='nofollow'] ~ *,[rel='nofollow'],img[src^='http://pixel.wp.com']").remove();

		article.select(".wp-caption").tagName("p");
		article.select("[style]").removeAttr("style");
		article.select("iframe").attr("frameborder", "0");

		wpcomwidget(article.select("form[id]"));
		article.select("form,script").remove();

		cleanAttributes(article.select("img"), "src");
		return finalFormat(article, false);
	}

	@Override
	protected Enclosure parseEnclosure(Element prop)
	{
		String type = prop.attr("type");
		if (!type.contains("video"))
			type = "image";

		return new Enclosure(prop.attr("url"), type, prop.attr("length"));
	}

	@Override
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		if (news.content.isEmpty() && !enclosures.isEmpty()) {
			for (Enclosure e : enclosures)
				if (e.isVideo()) {
					news.content = e.html();
					break;
				}
			news.content += "<p>" + news.description + "</p>";
		}
		return news;
	}

}
