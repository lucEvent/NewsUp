package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

public class Motherboard extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [category, content:encoded, dc:creator, description, enclosure, guid, item, link, pubdate, title]

	public Motherboard()
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
	protected String parseContent(Element prop)
	{
		Element article = jsoupParse(prop);
		article.select("[data-related-article='true']").remove();

		article.select(".article__image-caption").tagName("figcaption");
		article.select(".article__pull-quote").tagName("blockquote");

		for (Element i : article.select("iframe[data-iframely-url]")) {
			i.attr("src", i.attr("data-iframely-url"));
			i.removeAttr("data-iframely-url").removeAttr("style");
			Element p = i.parent();
			while (p != null) {
				cleanAttributes(p);
				p = p.parent();
			}
		}
		for (Element i : article.select("div[data-embedded-url]")) {
			i.html(insertIframe(i.attr("data-embedded-url").replace("watch?v=", "embed/")));
			cleanAttributes(i);
		}

		return finalFormat(article, false);
	}

	@Override
	protected Document getDocument(String url)
	{
		try {
			return org.jsoup.Jsoup.connect(url)
					.timeout(10000)
					.parser(Parser.xmlParser())
					.userAgent(USER_AGENT)
					.validateTLSCertificates(false)
					.get();
		} catch (Exception ignored) {
		}
		return super.getDocument(url);
	}

}
