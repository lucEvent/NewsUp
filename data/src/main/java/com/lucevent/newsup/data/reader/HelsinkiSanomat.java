package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HelsinkiSanomat extends com.lucevent.newsup.data.util.NewsReader {

	//Tags: [category, description, enclosure, guid, item, link, media:content, pubdate, title]

	public HelsinkiSanomat()
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
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements image = doc.select(".article-main-image noscript img");
		Elements article = doc.select("[itemprop='articleBody']");

		if (article.isEmpty())
			return null;

		article.select(".hidden,script,[itemprop='video'],.article-ad-block").remove();

		for (Element f : article.select("figure:has(img)")) {
			cleanAttributes(f);

			Element img = f.select("img").first();
			f.html(insertImg(
					img.hasAttr("src") ? img.attr("src") : img.attr("data-srcset")
			));
		}
		article.select(".votsikko").tagName("h4");
		article.select("[style]").removeAttr("style");

		return finalFormat(image, true) +
				"<p>" + finalFormat(article, true).replace("<br>", "</p><p>") + "</p>";
	}

}