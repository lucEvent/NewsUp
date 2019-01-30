package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Meristation extends com.lucevent.newsup.data.util.NewsReader {

	// tags: [description, enclosure, guid, item, link, pubdate, source, title]

	public Meristation()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{},
				new int[]{TAG_ENCLOSURE},
				"");
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select("[class='clear-block']:not(.clear-block .clear-block)");
		article.select("script[src*='.twitter.'],script[src*='.facebook.'],.fb-post,.views-exposed-widget,.galeriaContent").remove();

		for (Element v : article.select("[video]:has([id^='ytplayer'])")) {
			String vid = v.attr("video");
			v.html(insertIframe("https://www.youtube.com/embed/" + vid));
		}
		for (Element e : article.select("#videoEmbed")) {
			String src = "http://www.dailymotion.com/embed/video/" + e.parent().attr("video");
			e.parent().html(insertIframe(src));
		}
		for (Element e : article.select(".galeriaAmpliacion")) {
			StringBuilder sb = new StringBuilder();
			for (Element img : e.select(".gal_data li"))
				sb.append("<img src='").append(img.attr("data-path")).append("'>");

			e.html(sb.toString());
		}
		cleanAttributes(article.select("img[src]"), "src");

		return finalFormat(article, false);
	}

	@Override
	protected org.jsoup.nodes.Document getDocument(String url)
	{
		try {
			return org.jsoup.Jsoup.connect(url)
					.timeout(10000)
					.get();
		} catch (Exception ignored) {
		}
		return super.getDocument(url);
	}

}
