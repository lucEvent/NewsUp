package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public class Swedroid extends com.lucevent.newsup.data.util.NewsReader {

	private static final String SITE_STYLE = "<style>tr,th,td{padding:3px 10px;}</style>";

	// Tags [category, content:encoded, dc:creator, description, guid, item, link, post-id, pubdate, title]

	public Swedroid()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{TAG_CONTENT_ENCODED},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{},
				SITE_STYLE);
	}

	@Override
	protected String parseContent(Element prop)
	{
		Element article = jsoupParse(prop);
		article.select("script,#review-statistics").remove();

		article.select("[style]").removeAttr("style");
		article.select(".wp-caption-text").tagName("figcaption");

		for (Element img : article.select("img")) {
			String remove = "-" + img.attr("width") + "x" + img.attr("height");
			String src = img.attr("src").replace(remove, "");
			cleanAttributes(img);
			img.attr("src", src);
		}

		return finalFormat(article, false);
	}

	@Override
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		Element description = jsoupParse(news.description);
		news.imgSrc = findImageSrc(description);

		news.description = description.text();
		int i = news.description.indexOf("[");
		if (i != -1)
			news.description = news.description.substring(0, i) + "...";

		return news;
	}

}