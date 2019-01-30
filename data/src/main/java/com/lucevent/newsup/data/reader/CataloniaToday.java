package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CataloniaToday extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [author, category, description, guid, item, link, pubdate, title]

	public CataloniaToday()
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
		return jsoupParse(prop).text();
	}


	@Override
	protected String readNewsContent(Document doc, String news_url)
	{
		Elements article = doc.select("article").select("header .subtitol,.bxslider img,.bxslider .caption,#article-content,.article-fitxes");
		article.select("script").remove();

		for (Element subtitle : article.select(".subtitol")) {
			subtitle.tagName("p");
			subtitle.html("<li>" + subtitle.html() + "</li>");
			cleanAttributes(subtitle);
		}
		article.select(".caption").tagName("figcaption");
		article.select(".article-fitxes,.frase").tagName("blockquote");
		article.select(".formatperfil,.formatpre").tagName("h4");

		return finalFormat(article, true);
	}


}
