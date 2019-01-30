package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DailyMirror extends com.lucevent.newsup.data.util.NewsReader {

	// tags: [author, category, description, enclosure, guid, item, link, media:content, media:keywords, media:thumbnail, pubdate, title]

	public DailyMirror()
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
	protected String readNewsContent(Document doc, String news_url)
	{
		Elements article = doc.select("p[itemprop='description'],article > [itemprop='image'],.mod-video:not(.article-body .mod-video),.article-body");
		article.select("script,.skinny-signup,.opta-score,.read-more-links,.embedded-image-grid,.poll").remove();

		for (Element ytp : article.select("[data-player-type='youtube']:has(img)")) {
			cleanAttributes(ytp);
			String src = ytp.select("img").first().attr("data-src");
			src = findSubstringBetween(src, "vi/", "/0.j", false);
			ytp.html(insertIframe("https://www.youtube.com/embed/" + src));
		}

		Elements topVideo = article.select(".mod-video");
		if (!topVideo.isEmpty()) {
			Elements vidSrc = doc.select("meta[property='videoUrl']");
			if (!vidSrc.isEmpty()) {
				Element eVideo = topVideo.get(0);
				cleanAttributes(eVideo);
				eVideo.html(insertIframe(vidSrc.get(0).attr("content")));
			} else {
				topVideo.remove();
			}
		}
		for (Element modImg : article.select(".mod-image img[data-src]")) {
			modImg.parent().parent().html("<img src='" + modImg.attr("data-src") + "'>");
		}
		for (Element live : article.select(".live-event-container")) {
			live.select(".entry-info,.entry-content").tagName("p");
			live.select("time").wrap("<strong></strong>").append("&nbsp;&nbsp;");
			live.select("mark").remove();
			cleanAttributes(live);
			cleanAttributes(live.select("div,p,figure"));
		}
		article.select("[data-json]").removeAttr("data-json");
		article.select("[data-callback-url]").removeAttr("data-callback-url");

		return finalFormat(article, true);
	}

}
