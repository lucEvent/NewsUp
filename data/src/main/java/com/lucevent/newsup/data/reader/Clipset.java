package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Clipset extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

	public Clipset()
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
	protected String parseDescription(Element prop)
	{
		return org.jsoup.Jsoup.parse(prop.text()).text();
	}

	@Override
	protected void readNewsContent(Document doc, News news)
	{
		Elements article = doc.select("article").select(".video-destacado,.imagen-destacada,.subtitular,.contenido-post");
		doc.select(".wp-embedded-content,script").remove();

		doc.select(".wp-caption-text").tagName("figcaption");
		doc.select("[style]").removeAttr("style");

		cleanAttributes(doc.select("img"), "src");

		news.content = finalFormat(article, true);
	}

}
