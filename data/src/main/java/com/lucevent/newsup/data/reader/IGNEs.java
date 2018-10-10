package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

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
	protected void readNewsContent(Document doc, News news)
	{
		if (news.link.contains("/trailer/") || news.link.contains("/video/") || news.link.contains("/videointerview/")) {

			Elements video = doc.select("iframe.vplayer");
			fixIframes(video);

			news.content = video.outerHtml();
			return;

		} else if (news.link.contains("/gallery/")) {

			Elements slides = doc.select(".swiper-slide img.swiper-lazy");
			for (Element s : slides) {
				String src = s.attr("data-src");
				cleanAttributes(s);
				s.attr("src", src);
			}
			news.content = slides.outerHtml();
			return;
		}

		Elements article = doc.select(".articleBody");
		article.select("script").remove();

		fixIframes(article.select("iframe[src]"));
		cleanAttributes(article.select("img[src]"), "src");

		news.content = finalFormat(article, false);
	}

	private void fixIframes(Elements iframes)
	{
		for (Element e : iframes) {
			String preUrl = e.attr("src");
			if (preUrl.startsWith("/"))
				preUrl = "https://es.ign.com" + preUrl;

			try {
				Document d = getDocument(preUrl);
				String json = d.select(".video-embed-content-v6").first().attr("data-settings");

				e.attr("src",
						findSubstringBetween(json, "1080\":{\"url\":\"", "\"", false).replace("\\", ""));
			} catch (Exception ignored) {
			}
		}
	}

}
