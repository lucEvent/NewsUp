package com.lucevent.newsup.development.reader;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.development.utils.HardDrive;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class NewScientist extends com.lucevent.newsup.development.utils.NewsReader {

	//tags: [category, description, guid, item, link, pubdate, title]

	public NewScientist()
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
	protected void readNewsContent(Document doc, News news)
	{
		//HardDrive.copyToDeb(doc, true);

		Elements article = doc.select(".article-content");

		article.select(".credit").tagName("figcaption");



		news.content = finalFormat(article, false);
	}
}
