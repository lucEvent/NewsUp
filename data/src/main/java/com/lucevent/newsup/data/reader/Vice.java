package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Date;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

public class Vice extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [category, content:encoded, dc:creator, description, enclosure, guid, item, link, media:category, media:thumbnail, pubdate, title]

	public Vice()
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
	protected long parseDate(Element prop)
	{
		String date = prop.text();
		if (date.length() < 24)
			return 0;
		return Date.toDate(prop.text().substring(0, 24));
	}

	@Override
	protected String parseContent(Element prop)
	{
		Element article = jsoupParse(prop);
		article.select("script[src*='twitter'],script[src*='instagram']").remove();
		article.select("[style]").removeAttr("style");
		article.select("iframe").attr("frameborder", "0");
		article.select(".article__pull-quote").tagName("blockquote");
		article.select(".article__image-caption").tagName("figcaption");

		for (Element v : article.select("div[data-iframely-id]")) {
			v.html(insertIframe("http://oembed.vice.com/" + v.attr("data-iframely-id")));
			cleanAttributes(v);
		}

		return finalFormat(article, false);
	}

	@Override
	protected Document getDocument(String url)
	{
		try {
			return org.jsoup.Jsoup.connect(url)
					.userAgent(USER_AGENT)
					.cookie("cookiewall", "yes")
					.parser(Parser.xmlParser())
					.get();
		} catch (Exception ignored) {
		}
		return super.getDocument(url);
	}

}
