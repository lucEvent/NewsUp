package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TeknikensVarld extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

	public TeknikensVarld()
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
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		if (!news.content.isEmpty()) {
			Element article = jsoupParse(news.content);
			article.select("script,.-teaser-as-list,.desktop-ad,.mobile-ad,.c-image-slider").remove();
			article.select(".wp-caption-text,.caption,.photographer").tagName("figcaption");
			article.select(".btdm-factbox").tagName("blockquote");

			news.imgSrc = findImageSrc(article);
			news.content = finalFormat(article, false);
		}
		return news;
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select(".entry-content");
		doc.select("script,.-teaser-as-list,.desktop-ad,.mobile-ad").remove();

		doc.select(".wp-caption-text,.caption,.photographer").tagName("figcaption");
		doc.select(".btdm-factbox").tagName("blockquote");

		for (Element fig : article.select("figure:has(noscript)")) {
			cleanAttributes(fig);
			fig.html(fig.select("noscript,.wp-caption-body").html());
		}

		cleanAttributes(doc.select("img"), "src");

		return finalFormat(article, false);
	}

}
