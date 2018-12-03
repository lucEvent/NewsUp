package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class OttawaCitizen extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [category, content:encoded, dc:creator, description, guid, item, link, media:content, media:thumbnail, media:title, postmedia:alexa, pubdate, title]

	public OttawaCitizen()
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
	protected String parseContent(Element prop)
	{
		Element article = jsoupParse(prop);
		article.select("script").remove();

		Elements rel = article.select(".related_links");
		for (Element e : rel) {
			Element prev = e.previousElementSibling();
			if (prev != null && prev.className().contains("cat-head"))
				prev.remove();
		}
		rel.remove();

		Elements wpcomwidgets = article.select("form");
		if (!wpcomwidgets.isEmpty()) {
			wpcomwidget(wpcomwidgets);
			wpcomwidgets.remove();
		}

		article.select("div[style]:has(img)").removeAttr("style");
		article.select(".wp-caption-text").tagName("figcaption");
		cleanAttributes(article.select("img[src]"), "src");

		return finalFormat(article, false);
	}

}
