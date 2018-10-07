package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class LaPatilla extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

	public LaPatilla()
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
		String dscr = jsoupParse(prop).text();

		int i = dscr.indexOf("[");
		if (i > 0) {
			dscr = dscr.substring(0, i);
		}
		return dscr.replace("\u00A0", "").trim();
	}

	@Override
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		if (!news.content.isEmpty()) {
			Element article = jsoupParse(news.content);
			article.select("script,br,.image_credits,.image_author").remove();

			article.select("[style]").removeAttr("style");

			cleanAttributes(article.select("img"), "src");

			news.imgSrc = findImageSrc(article);
			news.content = finalFormat(article, false).replace("<p>&nbsp;</p>", "");
		}
		return news;
	}

	@Override
	protected Document getDocument(String url)
	{
		try {
			return org.jsoup.Jsoup.connect(url)
					.userAgent(USER_AGENT)
					.timeout(10000)
					.validateTLSCertificates(false)
					.get();
		} catch (Exception ignored) {
		}
		return null;
	}

}
