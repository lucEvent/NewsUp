package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Omicrono extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [category, dc:creator, description, feedburner:origlink, guid, item, link, pubdate, title]

	public Omicrono()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{"feedburner:origlink".hashCode()},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{},
				"");
	}

	@Override
	protected String parseDescription(Element prop)
	{
		Element dscr = jsoupParse(prop);
		Elements ps = dscr.select("p");
		return ps.isEmpty() ? dscr.text() : ps.first().text();
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select("#singlePostHeaderImgBox,#singlePostContent");
		article.select("script,.blockquoteLink,.blockquoteRelated,.singlePostShare,#singlePostRelated,#singlePostAdvertising").remove();

		article.select(".wp-caption-text").tagName("figcaption");

		cleanAttributes(article.select("img[src]"), "src");
		cleanAttributes(article.select("div[style]"));

		return finalFormat(article, false);
	}

}
