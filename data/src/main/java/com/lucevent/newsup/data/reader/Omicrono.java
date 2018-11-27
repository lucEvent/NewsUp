package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
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
	protected void readNewsContent(Document doc, News news)
	{
		Elements article = doc.select("#singlePostHeaderImgBox,#singlePostContent");
		article.select("script,.blockquoteLink,.blockquoteRelated,.singlePostShare,#singlePostRelated,#singlePostAdvertising").remove();

		article.select(".wp-caption-text").tagName("figcaption");

		cleanAttributes(article.select("img[src]"), "src");
		cleanAttributes(article.select("div[style]"));

		news.content = finalFormat(article, false);
	}

}
