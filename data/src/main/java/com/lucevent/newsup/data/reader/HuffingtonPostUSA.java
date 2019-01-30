package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HuffingtonPostUSA extends com.lucevent.newsup.data.util.NewsReader {

	// Tags: [content:encoded, description, enclosure, guid, item, link, pubdate, title]

	public HuffingtonPostUSA()
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
	protected Enclosure parseEnclosure(Element prop)
	{
		String url = prop.attr("url");
		int index = url.indexOf('?');
		if (index != -1) {
			url = url.split("\\?")[0];
		}
		return new Enclosure(url, "image", "");
	}

	@Override
	protected String parseLink(Element prop)
	{
		String link = super.parseLink(prop);
		if (link.startsWith("https://www.huffingtonpost.comhttps"))
			link = link.replaceFirst("://www.huffingtonpost.comhttps", "");
		return link;
	}

	@Override
	protected Document getDocument(String url)
	{
		try {
			return org.jsoup.Jsoup.connect(url)
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
					.timeout(10000)
					.validateTLSCertificates(false)
					.get();
		} catch (Exception ignored) {
		}
		return null;
	}

	@Override
	protected String readNewsContent(Document doc, String news_url)
	{
		Elements article;
		if (doc.baseUri().contains("www.huffpost")) {
			article = doc.select("#entry-body");
		} else
			article = doc.select(".top-media--image figure,.content-list-component");

		article.select("script[src*='.twitter.'],script[src*='.instagram.'],.vdb_player,.cli-related-articles").remove();

		article.select(".image__credit").tagName("figcaption");
		article.select(".quote").tagName("blockquote");

		for (Element ytv : article.select("[data-youtube-id]")) {
			String vid = ytv.attr("data-youtube-id");
			ytv.parent().html(insertIframe("https://www.youtube.com/embed/" + vid));
			cleanAttributes(ytv.parent());
		}
		for (Element live : article.select("[data-spotim-module='spotim-launcher']")) {
			String id = live.attr("data-live-event-code");
			cleanAttributes(live);
			live.tagName("p");
			live.html("<iframe frameborder='0' allowfullscreen src='https://spoxy-eb.spot.im/v2/spot/sp_wKU91MpX/event/" + id + "'></iframe>");
		}
		for (Element emb : article.select("[data-iframely-url]")) {
			String src = emb.attr("data-iframely-url");
			emb.parent().html(insertIframe(src));
			cleanAttributes(emb.parent());
		}
		for (Element e : article.select(".listicle__slide")) {
			String title = e.select(".listicle__slide-header").text();
			String graphic = e.select(".listicle__slide-content").html();
			String content = e.select(".listicle__slide-caption").html();
			e.html("<h3>" + title + "</h3>" + graphic + "<p>" + content + "</p>");
			e.tagName("p");
			cleanAttributes(e);
		}
		for (Element e : article.select("[data-placeholder]")) {
			e.tagName("img");
			String src = e.attr("data-placeholder");
			cleanAttributes(e);
			e.attr("src", src);
		}
		article.select("script").remove();
		article.select("span[style],div[style]:has(iframe),iframe[style]").removeAttr("style");

		return finalFormat(article, false);
	}

}