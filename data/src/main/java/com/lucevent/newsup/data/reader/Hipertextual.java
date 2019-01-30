package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.parser.XmlTreeBuilder;
import org.jsoup.select.Elements;

public class Hipertextual extends com.lucevent.newsup.data.util.NewsReader {

	// Tags: [dc:creator, description, guid, item, link, pubdate, title]

	public Hipertextual()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{},
				new int[]{},
				"");
	}

	@Override
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		Element article = jsoupParse(news.description);
		news.description = article.text();
		news.imgSrc = findImageSrc(article);
		return news;
	}

	@Override
	protected String readNewsContent(Document doc, String news_url)
	{
		Elements article = doc.select("main");

		if (article.isEmpty()) {
			// ARTICLE EMPTY!!!!
			return null;
		}

		article = article.select(".headlineSingle__lead,.articleHead,.historia");
		article.select("script,.wrapperBanner").remove();

		article.select("aside,q").tagName("blockquote");

		for (Element e : article.select("figure:has(noscript)"))
			e.html(e.select("noscript").html());

		cleanAttributes(article.select("img[src]"), "src");
		for (Element e : article.select("img[data-cfsrc]")) {
			String src = e.attr("data-cfsrc");
			cleanAttributes(e);
			e.attr("src", src);
		}

		return finalFormat(article, true);
	}

	@Override
	protected Document getDocument(String url)
	{
		try {
			return org.jsoup.Jsoup.connect(url)
					.userAgent(USER_AGENT)
					.timeout(10000)
					.validateTLSCertificates(false)
					.parser(new Parser(new XmlTreeBuilder()))
					.get();
		} catch (Exception ignored) {
		}
		return null;
	}

}