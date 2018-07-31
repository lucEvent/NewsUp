package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TheTimesOfIndia extends com.lucevent.newsup.data.util.NewsReader {

	/**
	 * tags:
	 * [                             description, guid, item, link, pubdate, title]
	 * [category, cmsid, dc:creator, description, guid, item, link, pubdate, title]
	 */

	public TheTimesOfIndia()
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
	protected void readNewsContent(Document doc, News news)
	{
		doc.select(".forcehide").remove();

		Elements article = doc.select(".articleimg .vidContent,.agencymainimg,.articlehighlights,.article-txt");

		if (article.isEmpty()) {
			article = doc.select(".article .content");

			for (Element img : article.select("img[src^='../..']"))
				img.attr("src", img.attr("src").replace("../..", "http://blogs.timesofindia.indiatimes.com"));

		}
		article.select("script,.articlehighlights h3,.ad1,.dontmiss,.readalso,.track:not(.newsincontext),.vidAdContainer,.hide,.playicon_amp").remove();

		for (Element img : article.select("img[data-src]"))
			img.attr("src", img.attr("data-src"));

		cleanAttributes(article.select("img"), "src");
		article.select("artsummary").tagName("div");
		article.select("[style]").removeAttr("style");
		article.select(".clearfix").tagName("p");
		article.select(".agencytxt,.italictext,.wp-caption-text").tagName("figcaption");

		news.content = finalFormat(article, false).replace("<br> <br>", "</p><p>");
	}

}
