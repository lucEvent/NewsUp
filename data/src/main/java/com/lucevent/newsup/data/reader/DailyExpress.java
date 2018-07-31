package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DailyExpress extends com.lucevent.newsup.data.util.NewsReader {

	// tags: [description, guid, item, link, pubdate, title]

	public DailyExpress()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_GUID},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{},
				new int[]{},
				"");
	}

	@Override
	protected String parseDescription(Element prop)
	{
		return jsoupParse(prop).text();
	}

	@Override
	protected void readNewsContent(Document doc, News news)
	{
		Elements article = doc.select("#singleArticle>header h3,#singleArticle [data-type='article-body']");
		article.select("script,.photo-caption,.clear,.external-gallery").remove();

		Elements h3s = article.select("h3");
		if (!h3s.isEmpty()) {
			Element intro = h3s.first();
			intro.tagName("p");
			intro.html("<strong>" + intro.html() + "</strong>");
		}

		for (Element scribble : article.select("article[data-trigger='scribble']")) {
			cleanAttributes(scribble);
			scribble.tagName("div");
			Element ns = scribble.nextElementSibling();
			scribble.html(ns.select("header img,.list .item .time,.list .item .content").outerHtml());
			ns.remove();
		}

		for (Element bcvideo : article.select(".htmlappend video")) {
			bcvideo.parent().parent().parent()
					.html(insertIframe(
							"https://players.brightcove.net/"
									+ bcvideo.attr("data-account") + "/"
									+ bcvideo.attr("data-player") + "_"
									+ bcvideo.attr("data-embed") + "/index.html?videoId="
									+ bcvideo.attr("data-video-id")
					));
		}

		article.select(".newsCaption").tagName("figcaption");

		news.content = finalFormat(article, true);
	}

}
