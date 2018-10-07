package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ElPeriodicoEs extends com.lucevent.newsup.data.util.NewsReader {

	/**
	 * Tags
	 * [description, guid, item, link, pubdate, title]
	 * [category, comments, content:encoded, dc:creator, description, guid, item, link, pubdate,  title]
	 */

	public ElPeriodicoEs()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{TAG_CONTENT_ENCODED},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{},
				"");
	}

	@Override
	protected String parseLink(Element prop)
	{
		String link = prop.text();
		if (link.startsWith("/"))
			link = "http://www.elperiodico.com" + link;
		return link;
	}

	@Override
	protected String parseDescription(Element prop)
	{
		return jsoupParse(prop.text()).text().replace("Seguir leyendo....", "");
	}

	@Override
	protected String parseContent(Element prop)
	{
		Element article = jsoupParse(prop);
		article.select("[style]").removeAttr("style");
		article.select(".subtitle").tagName("figcaption");
		return finalFormat(article, false).replace("<span>", "").replace("</span>", "").replace("<p>&nbsp;</p>", "");
	}

	@Override
	protected void readNewsContent(Document doc, News news)
	{
		Elements article = doc.select(".ep-detail-body");

		if (article.isEmpty()) {
			article = doc.select(".slider-item,.onbcn-detail-body");

			article.select("script,.onbcn-themes-related").remove();

		} else
			article.select("script,.hidden-md,.hidden-sm,.ep-toolbar,.close,.ep-related,.ep-opinion,.bottom,.custom-navigation").remove();

		if (!doc.select(".ep-galeria-v2").isEmpty())
			doc.select(".ep-media").remove();

		article.select(".despiece-bottom").tagName("blockquote");
		article.select(".subtitle").tagName("figcaption");

		cleanAttributes(article.select(".slide,.slider-item"));

		news.content = finalFormat(article, false);
	}

}
