package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NationalGeographicEs extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

	public NationalGeographicEs()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{TAG_CONTENT_ENCODED},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{},
				"");
	}

	@Override
	protected String parseDescription(Element prop)
	{
		Elements ps = jsoupParse(prop).select("p");
		return ps.isEmpty() ? "" : ps.first().text();
	}

	@Override
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		if (news.content != null && !news.content.isEmpty()) {
			Element article = jsoupParse(news.content);
			article.select("script,.ad-container").remove();

			cleanAttributes(article.select("img[src]"), "src");
			cleanAttributes(article.select("figure"));

			news.imgSrc = findImageSrc(article);
			news.content = finalFormat(article, false);
		}
		return news;
	}

}
