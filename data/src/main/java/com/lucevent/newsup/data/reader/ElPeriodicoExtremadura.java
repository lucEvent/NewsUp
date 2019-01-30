package com.lucevent.newsup.data.reader;

import org.jsoup.select.Elements;

public class ElPeriodicoExtremadura extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [category, dc:creator, description, guid, item, link, pubdate, title]

	public ElPeriodicoExtremadura()
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
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select(".EntradillaDeNoticia,.FotoDeNoticia,#TextoNoticia");
		article.select(".NoticiasRelacionadasDeNoticia,.Creatividad,.Recorte,script[src*='twitter.com'],script[src*='instagram.com']").remove();

		article.select(".EntradillaDeNoticia").tagName("h4");
		article.select(".PieDeFoto").tagName("figcaption");

		cleanAttributes(article.select("img"), "src");

		return finalFormat(article, true);
	}

}
