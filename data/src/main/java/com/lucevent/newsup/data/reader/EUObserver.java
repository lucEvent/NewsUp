package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class EUObserver extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [description, enclosure, guid, item, link, pubdate, title]

	public EUObserver()
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
	protected void readNewsContent(Document doc, News news)
	{
		Elements article = doc.select(".body");
		article.select(".membership-upsell,.banner").remove();

		for (Element e : article.select(".image-list"))
			e.html(e.select("img,figcaption").outerHtml());

		news.content = finalFormat(article, true);
	}

}
