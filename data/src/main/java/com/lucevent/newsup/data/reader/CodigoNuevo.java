package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class CodigoNuevo extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [category, dc:creator, description, guid, item, link, media:content, pubdate, title]

	public CodigoNuevo()
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
		Elements article = doc.select(".articleMainImage,.articleTittle,.articleMain");
		article.select("script,.articleTittle h1,.rightAd").remove();

		article.select(".articleFootImg").tagName("figcaption");

		news.content = finalFormat(article, false);
	}

}
