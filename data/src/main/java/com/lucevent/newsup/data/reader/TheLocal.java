package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Date;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TheLocal extends com.lucevent.newsup.data.util.NewsReader {

	// tags: [description, enclosure, guid, item, link, pubdate, title]

	public TheLocal()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{},
				new int[]{TAG_ENCLOSURE},
				"");
	}

	@Override
	protected long parseDate(Element prop)
	{
		return Date.toDate(prop.text()) - 7200000; // -2hours
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select("#article-photo,#article-description,#article-body");
		article.select("script,.ad_container").remove();

		article.select("#article-description").tagName("h4");
		article.select("i").tagName("figcaption");
		article.select("[style]").removeAttr("style");

		for (Element img : article.select("img,amp-img")) {
			img.tagName("img");
			cleanAttributes(img, "src");
		}
		for (Element ro : article.select("strong")) {
			String text = ro.text();
			if (text.startsWith("READ ALSO")
					|| text.startsWith("READ MORE")
					|| text.startsWith("See also")) {
				try {
					ro.parent().remove();
				} catch (Exception ignored) {
					ro.remove();
				}
			}
		}

		return finalFormat(article, true);
	}

}
