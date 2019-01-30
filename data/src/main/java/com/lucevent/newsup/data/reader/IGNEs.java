package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class IGNEs extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [category, description, enclosure, guid, item, link, pubdate, title]

	public IGNEs()
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
		return jsoupParse(prop).text();
	}

	@Override
	protected String readNewsContent(Document doc, String news_url)
	{
		if (news_url.contains("/trailer/") || news_url.contains("/video/") || news_url.contains("/videointerview/")) {

			Elements video = doc.select("iframe.vplayer");
			fixIframes(video);

			return video.outerHtml();

		} else if (news_url.contains("/gallery/")) {

			Elements slides = doc.select(".swiper-slide img.swiper-lazy");
			for (Element s : slides) {
				String src = s.attr("data-src");
				cleanAttributes(s);
				s.attr("src", src);
			}
			return slides.outerHtml();
		}

		Elements article = doc.select(".articleBody");
		article.select("script").remove();

		fixIframes(article.select("iframe[src]"));
		cleanAttributes(article.select("img[src]"), "src");

		return finalFormat(article, false);
	}

	private void fixIframes(Elements iframes)
	{
		for (Element e : iframes) {
			String preUrl = e.attr("abs:src");
			try {
				Document d = getDocument(preUrl);
				String json = d.select(".video-embed-content-v6").first().attr("data-settings");

				String url = findSubstringBetween(json, "1080\":{\"url\":\"", "\"", false);
				if (url == null) {
					url = findSubstringBetween(json, "720\":{\"url\":\"", "\"", false);
					if (url == null) {
						url = findSubstringBetween(json, "540\":{\"url\":\"", "\"", false);
						if (url == null) {
							url = findSubstringBetween(json, "480\":{\"url\":\"", "\"", false);
							if (url == null) {
								url = findSubstringBetween(json, "360\":{\"url\":\"", "\"", false);
								if (url == null) {
									continue;
								}
							}
						}
					}
				}
				e.attr("src", url.replace("\\", ""));
			} catch (Exception ignored) {
			}
		}
	}

}
