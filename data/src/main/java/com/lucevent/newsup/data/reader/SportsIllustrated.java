package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class SportsIllustrated extends com.lucevent.newsup.data.util.NewsReader {

	// tags: [category, dc:creator, description, guid, item, link, media:content, media:thumbnail, pubdate, title]

	public SportsIllustrated()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_MEDIA_CONTENT},
				"");
	}

	@Override
	protected String readNewsContent(Document doc, String news_url)
	{
		Elements article = doc.select(".lead-image .lazy-image,.article-content .media-video,#article-body");
		article.select("script,link,.js-inner-container,.image-wrap-container,.ad-container,.ad-sticky-container,.inline-article,.video,wbr,.riddle-container").remove();

		article.select("noscript").tagName("p");
		article.select("[data-saferedirecturl]").removeAttr("data-saferedirecturl");
		article.select("[data-label]").removeAttr("data-label");
		article.select("img[style],iframe[style]").removeAttr("style");

		return finalFormat(article, true);
	}

	@Override
	protected Document getDocument(String url)
	{
		try {
			return org.jsoup.Jsoup.connect(url)
					.userAgent(USER_AGENT)
					.timeout(10000)
					.get();
		} catch (Exception ignored) {
		}
		return null;
	}

}
