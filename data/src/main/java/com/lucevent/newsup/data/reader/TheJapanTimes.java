package com.lucevent.newsup.data.reader;

import org.jsoup.select.Elements;

public class TheJapanTimes extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [author, category, description, guid, item, link, media:content, media:full, media:thumbnail, pubdate, tag, title]

	public TheJapanTimes()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{"tag".hashCode()},
				new int[]{TAG_MEDIA_CONTENT},
				"");
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select("article[role='main'] .gallery,#jtarticle");
		doc.select("script,.clearfix,.jt_content_ad,.single-sns-area,.jtarticle_related").remove();

		return finalFormat(article, false);
	}

}
