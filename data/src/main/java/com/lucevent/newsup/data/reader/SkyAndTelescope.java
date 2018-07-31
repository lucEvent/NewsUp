package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SkyAndTelescope extends com.lucevent.newsup.data.util.NewsReader {

	/**
	 * tags:
	 * [category, dc:creator, description,            guid, item, link, pubdate, title]
	 * [category, dc:creator, description, enclosure, guid, item, link, pubdate, title]
	 */

	public SkyAndTelescope()
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
		String dscr = jsoupParse(prop).select("p").first().text();
		return dscr.startsWith("The post") ? "" : dscr;
	}

	@Override
	protected void readNewsContent(Document doc, News news)
	{
		Elements article;
		if (news.link.contains("/astronomy-events/"))
			article = doc.select(".tribe-events-schedule,.tribe_events");
		else
			article = doc.select(".entry-content");

		article.select(".addthis_tool,.sharethis").remove();

		cleanAttributes(article.select("div.wp-caption"));
		cleanAttributes(article.select("img[src]"), "src");
		article.select(".wp-caption-text").tagName("figcaption");

		news.content = finalFormat(article, false);
	}

}
