package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ElImparcial extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [category, description, guid, item, link, media:thumbnail, pubdate, shortdesc, thumbnail, title]

	public ElImparcial()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{"shortdesc".hashCode()},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{"thumbnail".hashCode()},
				"");
	}

	@Override
	protected Enclosure parseEnclosure(Element prop)
	{
		String src = prop.text().replace("T.JPG", "N.jpg");
		if (!src.isEmpty())
			return new Enclosure(src, "image", "");
		return null;
	}

	@Override
	protected String readNewsContent(Document doc, String news_url)
	{
		Elements article = doc.select(".nota-title-foto [itemprop='image'],[itemprop='articleBody']");
		article.select(".nota-tags,.rs_skip,script").remove();

		article.select("[itemprop='articleBody']").tagName("p");
		article.select(".nota-procedencia").wrap("<b><em>").unwrap();
		article.select(".notecontent").unwrap();

		cleanAttributes(article.select("img"), "src");

		return finalFormat(article, true);
	}

}
