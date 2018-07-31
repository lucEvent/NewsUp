package com.lucevent.newsup.data.reader;

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
		return super.parseDescription(prop).trim().replace("[...]", "...");
	}

	@Override
	protected String parseContent(Element prop)
	{
		Element article = jsoupParse(prop);
		article.select("script,br,.image_credits,.image_author").remove();

		article.select("[style]").removeAttr("style");

		cleanAttributes(article.select("img"), "src");

		return finalFormat(article, false).replace("<p>&nbsp;</p>", "");
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
