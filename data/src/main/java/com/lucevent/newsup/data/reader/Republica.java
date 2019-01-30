package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Republica extends com.lucevent.newsup.data.util.NewsReader {

	/* tags:
		[category, dc:creator, description, guid, item, link, pubdate, title]
		[category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]
	*/

	public Republica()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{},
				"");
	}


	@Override
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		Element article = jsoupParse(news.description);

		String dscr = article.text();
		news.description = dscr.length() <= 300 ? dscr : dscr.substring(0, 300);
		news.imgSrc = findImageSrc(article);
		return news;
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select("[itemprop='articleBody']");

		if (article.isEmpty()) {
			article = doc.select(".entry-content");
			article.select(".sharedaddy,.swp_social_panel,.swp-content-locator").remove();
		}
		article.select("script").remove();

		article.select("span[style],p[style]").removeAttr("style");
		cleanAttributes(article.select("img[src]"), "src");

		return finalFormat(article, false);
	}

}
