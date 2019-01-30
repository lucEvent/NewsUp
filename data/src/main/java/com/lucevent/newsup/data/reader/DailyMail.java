package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DailyMail extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [description, enclosure, guid, item, link, media:content, media:credit, media:description, media:thumbnail, pubdate, title]

	public DailyMail()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{},
				new int[]{TAG_ENCLOSURE},
				"");
	}

	@Override
	protected String readNewsContent(Document doc, String news_url)
	{
		if (news_url.contains("/video/")) {
			Elements article = doc.select(".vjs-video-container,.video-single");
			article.select("h2").remove();
			cleanAttributes(article.select("video"), "controls");
			return finalFormat(article, false);
		}
		Elements article = doc.select("article");
		article.select("script,h1,.byline,.social,.sjs-share-container,.more,.adHolder,#external-source-links,noscript,[data-swipe-article=false],.fff-m-inline,.lc-social,.lc-live-update,.poll,.lc-icon,[style='display:none'],link,.season_at_glance,.see-more").remove();

		for (Element img : article.select("img[data-src]")) {
			String src = img.attr("data-src");
			cleanAttributes(img);
			img.attr("src", src);
		}

		article.select(".mol-factbox,.mol-article-quote").tagName("blockquote");
		article.select(".mol-para-with-font").removeAttr("class");
		cleanAttributes(article.select("video"), "controls");

		return finalFormat(article, false);
	}

}
