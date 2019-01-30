package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Verne extends com.lucevent.newsup.data.util.NewsReader {

	private static final String PLAYBUZZ_WIDGETS = "<script type='text/javascript' src='http://cdn.playbuzz.com/widget/feed.js'>";

	//tags: [category, content:encoded, dc:creator, description, enclosure, guid, item, link, pubdate, title]

	public Verne()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_ENCLOSURE},
				PLAYBUZZ_WIDGETS);
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select("[itemprop='articleBody']");
		article.select(".pie_video,.nota_pie,script[src*='twitter'],script[src*='instagram'],script[src*='playbuzz'],.cont-art-tags,.buscador-contenedor").remove();

		for (Element e : article.select(".embed iframe"))
			e.attr("src", e.attr("data-src"));

		for (Element video : article.select(".media:has(script)")) {
			String script = video.select("script").html();
			int i1 = script.indexOf("urls.push('");

			if (i1 == -1) {
				i1 = script.indexOf("urlVideo_1");
				if (i1 == -1) {
					video.html("");
					continue;
				}

				i1 += 26;
				int i2 = script.indexOf("';", i1);
				video.html(insertIframe("http://ep02.epimg.net/" + script.substring(i1, i2)));
			} else {
				i1 += 11;
				int i2 = script.indexOf("')", i1);
				video.html(insertIframe(script.substring(i1, i2).replace("watch?v=", "embed/")));
			}
		}
		for (Element e : article.select(".pb_feed,.playbuzz,[id^='sumario']:has(script)")) {
			e.html(e.outerHtml());
			cleanAttributes(e);
			e.tagName("nuwidget");
		}
		cleanAttributes(article.select("img[src]"), "src");
		article.select("script").remove();

		return finalFormat(article, false);
	}

}