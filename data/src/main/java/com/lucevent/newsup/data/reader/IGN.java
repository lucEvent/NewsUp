package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class IGN extends com.lucevent.newsup.data.util.NewsReader {

		/* tags:
			[content:encoded, dc:creator, dc:date, description, feedburner:origlink, guid, item, link, media:content, media:description, pubdate, title]
			[category, comments, description, enclosure, guid, item, link, pubdate, title]
		 */

	public IGN()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_ENCLOSURE, TAG_MEDIA_CONTENT},
				"");
	}

	@Override
	protected String parseDescription(Element prop)
	{
		return jsoupParse(prop).text();
	}

	@Override
	protected String parseLink(Element prop)
	{
		return fixChineseBaseUrl(super.parseLink(prop));
	}

	@Override
	protected Enclosure parseEnclosure(Element prop)
	{
		return new Enclosure(fixChineseBaseUrl(prop.attr("url")), prop.attr("type"), prop.attr("length"));
	}

	@Override
	protected String readNewsContent(Document doc, String news_url)
	{
		if (news_url.contains("/feeds.ign.")) {
			Elements article = doc.select(".page-content");
			article.select("script,.mob-article,.accentDivider,.theater-ui,.theater-social,.recirc-module").remove();
			article.select(".wp-caption-text").tagName("figcaption");

			for (Element img : article.select("img[data-original]")) {
				String src = img.attr("data-original");
				cleanAttributes(img);
				img.attr("src", src);
			}
			for (Element vid : article.select(".video-embed-content-v6[data-settings]")) {

				String url = findSubstringBetween(vid.attr("data-settings"), "1080\":{\"url\":\"", "\"", false);
				if (url == null)
					url = findSubstringBetween(vid.attr("data-settings"), "360\":{\"url\":\"", "\"", false);

				vid.parent().html(insertIframe(url.replace("\\", "")));
			}
			for (Element slides : article.select(".image-gallery-widget"))
				slides.html(slides.select(".fs-album-title,.fs-caption,.fullscreen-image").outerHtml());

			return finalFormat(article, false);
		} else if (news_url.contains("/trailer/") || news_url.contains("/video/") || news_url.contains("/videointerview/")) {

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
		article.select("script,.mob-article").remove();

		// fix links
		for (Element e : article.select("[src]"))
			e.attr("src", e.attr("abs:src"));
		for (Element e : article.select("[href]"))
			e.attr("href", e.attr("abs:href"));

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

	private String fixChineseBaseUrl(String url)
	{
		return url.contains("%E4%B8%AD%E5%9B%BD") ? url.replace("%E4%B8%AD%E5%9B%BD", "xn--fiqs8s") : url;
	}

}


