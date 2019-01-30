package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Make extends com.lucevent.newsup.data.util.NewsReader {

	// Tags [category, dc:creator, description, guid, item, link, post-id, pubdate, title]

	public Make()
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
		if (!news.description.isEmpty()) {
			Document doc = org.jsoup.Jsoup.parse(news.description);

			news.imgSrc = findImageSrc(doc);
			if (news.imgSrc != null)
				news.imgSrc = news.imgSrc.replace("200%2C200", "500%2C300");

			news.description = doc.select("p:nth-of-type(1)").text();
		}
		return news;
	}

	@Override
	protected String readNewsContent(Document doc, String news_url)
	{
		Elements article = doc.select("noscript .story-hero-image,.article-body");
		article.select("noscript,script,.ctx-clearfix,.ctx-article-root,.ctx-sidebar-container").remove();

		for (Element img : article.select("img")) {
			String src = img.attr("src");
			if (src.endsWith("1x1.trans.gif") || src.isEmpty()) {
				img.attr("src", img.attr("data-lazy-src"))
						.removeAttr("data-lazy-src");
			}

			cleanAttributes(img, "src");
		}

		doc.select(".wp-caption-text").tagName("figcaption");
		article.select("[style]").removeAttr("style");
		article.select("[width]").removeAttr("width");
		article.select("iframe").attr("frameborder", "0");

		cleanAttributes(article.select("video"), "controls");

		return finalFormat(article, true);
	}

	@Override
	protected Document getDocument(String url)
	{
		try {
			return org.jsoup.Jsoup.connect(url)
					.timeout(10000)
					.userAgent(USER_AGENT)
					.get();
		} catch (IOException ignore) {
		}
		return super.getDocument(url);
	}

}
