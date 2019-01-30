package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public class Yle extends com.lucevent.newsup.data.util.NewsReader {

	// Tags: [category, content:encoded, description, enclosure, guid, item, link, pubdate, title]

	public Yle()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{TAG_CONTENT_ENCODED},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_ENCLOSURE},
				"");
	}

	@Override
	protected Enclosure parseEnclosure(Element prop)
	{
		String src = prop.attr("url").replace("/w_205,h_115,q_70", "/w_615,h_345,q_100");
		if (src.startsWith("//"))
			src = "https:" + src;

		return new Enclosure(src, prop.attr("type"), prop.attr("length"));
	}

	@Override
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		if (!news.content.isEmpty()) {
			Element article = jsoupParse(news.content);
			if (article.text().length() != 0) {
				article.select("[class]").removeAttr("class");

				StringBuilder img = new StringBuilder();
				for (Enclosure e : enclosures)
					img.append(e.html());

				news.content = img + finalFormat(article, false);
			}
		}
		return news;
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		org.jsoup.select.Elements article = doc.select(".text");

		if (!article.isEmpty())
			return finalFormat(article, false);

		return null;
	}

}