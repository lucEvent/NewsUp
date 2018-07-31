package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.select.Elements;

public class DagensNyheter extends com.lucevent.newsup.data.util.NewsReader {

	// tags: [dc:creator, dc:date, description, guid, item, link, media:content, media:credit, media:description, media:thumbnail, pubdate, title]

	public DagensNyheter()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{},
				new int[]{TAG_MEDIA_CONTENT},
				"");
	}

	@Override
	protected String parseDescription(org.jsoup.nodes.Element prop)
	{
		return org.jsoup.Jsoup.parse(prop.text()).text();
	}

	@Override
	protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
	{
		Elements imgs = doc.select(".article__header-img noscript");
		Elements preamble = doc.select(".article__body .article__lead");
		Elements content = doc.select(".article__body .article__body-content");
		content.select("script,.ad-outer-container,.scrbbl-embed,.ad-container").remove();
		content.select("[style]").removeAttr("style");

		cleanAttributes(content.select("img"), "src");

		news.content = finalFormat(preamble, false) + imgs.html() + finalFormat(content, false);
	}

}
