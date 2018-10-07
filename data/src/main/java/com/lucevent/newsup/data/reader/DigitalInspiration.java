package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public class DigitalInspiration extends com.lucevent.newsup.data.util.NewsReader {

	// tags: [category, content:encoded, dc:creator, description, enclosure, guid, item, link, post-id, pubdate, title]

	public DigitalInspiration()
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
	protected Enclosure parseEnclosure(Element prop)
	{
		String src = prop.attr("url");
		return src.endsWith(".jpg") ?
				new Enclosure(src, prop.attr("type"), prop.attr("length")) : null;
	}

	@Override
	protected String parseDescription(Element prop)
	{
		return jsoupParse(prop).select("p").first().text();
	}

	@Override
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		if (!news.content.isEmpty()) {
			Element article = jsoupParse(news.content);
			article.select("script").remove();

			for (Element ytv : article.select(".youtube-player,.youtube"))
				ytv.html(insertIframe("https://www.youtube.com/embed/" + ytv.attr("data-id")));

			for (Element code : article.select("pre"))
				code.tagName("p").wrap("code");

			article.select("[style]").removeAttr("style");

			news.imgSrc = findImageSrc(article);
			String content = finalFormat(article, false);

			int index = content.lastIndexOf("<hr>");
			if (index != -1)
				content = content.substring(0, index);

			news.content = content;
		}
		return news;
	}

}
