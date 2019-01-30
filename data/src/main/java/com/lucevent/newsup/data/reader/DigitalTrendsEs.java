package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DigitalTrendsEs extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [category, dc:creator, description, enclosure, guid, item, link, pubdate, title]

	public DigitalTrendsEs()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_ENCLOSURE},
				"");
	}

	@Override
	protected String parseDescription(Element prop)
	{
		Element dscr = jsoupParse(prop).select("p").first();
		return dscr == null ? "" : dscr.text();
	}

	@Override
	protected String readNewsContent(Document doc, String news_url)
	{
		Elements article = doc.select("[itemprop='articleBody']");
		article.select("script,[itemprop='publisher'],[itemprop='image'],.h-nonessential,.m-editors-rec").remove();

		for (Element img : article.select("img[data-dt-lazy-src]"))
			img.attr("src", img.attr("data-dt-lazy-src"));

		for (Element c : article.select(".m-carousel"))
			c.html(c.select("img,figcaption").outerHtml());

		cleanAttributes(article.select("img"), "src");
		cleanAttributes(article.select("figure"));
		article.select(".m-our-take").tagName("h5");

		return finalFormat(article, false);
	}

}
