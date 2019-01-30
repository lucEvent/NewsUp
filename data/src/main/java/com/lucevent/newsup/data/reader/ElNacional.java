package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ElNacional extends com.lucevent.newsup.data.util.NewsReader {

    /* Tags:
        [category, description, guid, item, link, pubdate, title,... content:encoded, dc:creator, media:content, media:description]
        [category, description, guid, item, link, pubdate, title,... author, enclosure]
    */

	public ElNacional()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{TAG_CONTENT_ENCODED},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_ENCLOSURE, TAG_MEDIA_CONTENT},
				"");
	}

	@Override
	protected String parseContent(Element prop)
	{
		Element article = jsoupParse(prop);
		article.select("script,link,.videoPC,.eb-picto").remove();

		for (Element script : article.select("script[id^='infogram']")) {
			String src = script.attr("id").replaceFirst("infogram_0_", "https://e.infogram.com/") + "?src=embed";

			cleanAttributes(script);
			script.tagName("div");
			script.html(insertIframe(src));
		}
		for (Element h : article.select(".cs-horoscop")) {
			h.select("td:has(.dates)").tagName("h3");
			Elements elems = h.select("h3,p");
			h.html(elems.outerHtml());
		}

		article.select(".caption").tagName("figcaption");

		return finalFormat(article, false);
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select(".article-img img,.article-body");
		article.select("script,[data-type='related-content']").remove();

		cleanAttributes(article.select("img"), "src");

		return finalFormat(article, true);
	}

}
