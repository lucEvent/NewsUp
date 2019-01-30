package com.lucevent.newsup.data.reader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class USAToday extends com.lucevent.newsup.data.util.NewsReader {

	/**
	 * tags:
	 * [content:encoded, description, guid, item, link, pubdate, title]
	 * [content:encoded, description, enclosure, feedburner:origlink, guid, item, link, pubdate, title]
	 */

	public USAToday()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_GUID},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{},
				new int[]{TAG_ENCLOSURE},
				"");
	}

	@Override
	protected String parseDescription(Element prop)
	{
		String dscr = Jsoup.parse(prop.text()).text();
		return dscr.substring(0, Math.min(300, dscr.length()));
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		if (news_url.contains("/videos/")) {

			StringBuilder sb = new StringBuilder();
			Elements videos = doc.select(".video-player:has(script)");
			for (Element v : videos) {
				String script = v.select("script").first().html();

				String src = findSubstringBetween(script, "previewurl\": \"", "\",", false);
				String dscr = findSubstringBetween(script, "shortdescription\": \"", "\",", false);

				if (src != null) {
					sb.append(insertVideo(src));

					if (dscr != null)
						sb.append("<p>").append(dscr).append("</p>");
				}
			}
			return sb.toString();
		}
		if (news_url.contains("/picture-gallery/")) {

			StringBuilder sb = new StringBuilder();
			for (Element slide : doc.select("media-gallery-vertical slide")) {
				String img_title = slide.attr("caption");
				String img_caption = slide.attr("author");
				String img_srcset = slide.select("slide-crop[ratio='16_9']").attr("srcset");

				sb.append("<img srcset='").append(img_srcset)
						.append("'/><figcaption>").append(img_title)
						.append("<br>").append(img_caption)
						.append("</figcaption><br>");
			}

			return sb.toString();
		}
		if (news_url.contains("azcentral.com"))
			return readContentAzCentral(doc);

		if (doc.baseUri().contains("reviewed.com"))
			return readContentReviewed(doc);

		if (news_url.contains("10best.com"))
			return readContent10best(doc);

		if (news_url.contains(".cincinnati.com"))
			return readContentCincinnati(doc);

		if (news_url.contains("/interactives/") || news_url.startsWith("https://itunes.apple.com") || news_url.startsWith("https://www.facebook.com"))
			return null;

		Elements article = doc.select("article.story");

		if (article.isEmpty()) {
			article = doc.select(".articleBody");

			if (article.isEmpty()) {
				article = doc.select("[role='main']");
				article.select("ad").remove();
				article.select("[class]").removeAttr("class");

			} else {
				article.select(".spike").remove();

				for (Element img : article.select("img")) {
					String src = img.attr("src");
					String srcset = img.attr("srcset");
					cleanAttributes(img);
					img.attr("src", src).attr("srcset", srcset);
				}
				article.select(".wp-caption-text").tagName("figcaption");
			}
		} else {
			article.select("partner-sponsor-logo,.story-topper,story-byline,.exclude-from-newsgate,partner-poster,partner-banner,media-gallery,story-timestamp,.oembed-asset-link,#mc_embed_signup").remove();
		}
		article.select("script,link,partner-inline,partner-outstream,video-playlist").remove();

		for (Element bq : article.select("story-pull-quote")) {
			bq.tagName("blockquote");
			bq.html(bq.attr("text") + " - " + bq.attr("author"));
			cleanAttributes(bq);
		}

		for (Element vid : article.select("video-wrap")) {
			String info = vid.attr("video-info");
			String vid_src = findSubstringBetween(info, "previewurl\":\"", "\"", false);
			String vid_poster = vid.attr("videostill");

			cleanAttributes(vid);
			vid.html("");

			if (vid_src == null)
				continue;

			vid.tagName("video");
			vid.attr("src", vid_src)
					.attr("poster", vid_poster)
					.attr("controls", "");
		}

		for (Element img : article.select("media-image")) {
			img.tagName("figure");
			img.html("<img src='" + img.attr("image-url") + "'><figcaption>" + img.attr("caption") + "</figcaption>");
			cleanAttributes(img);
		}

		for (Element e : article.select("strong"))
			if (e.text().startsWith("More"))
				try {
					e.parent().remove();
				} catch (Exception ignored) {
				}

		Elements forms = article.select("form");
		wpcomwidget(forms);
		forms.remove();

		article.select("video-wrap").tagName("div");
		article.select("[style]").removeAttr("style");

		return finalFormat(article, false);
	}

	private String readContentAzCentral(Document doc)
	{
		Elements article = doc.select("article.story").select(".story-image,.long-caption,.story-body");
		article.select("script,.ad,.story-tools,.byline-container,.timestamp-footnote,#story-share-").remove();

		article.select(".caption-text").tagName("figcaption");
		article.select("[class='body-text']").removeAttr("class");

		return finalFormat(article, true);
	}

	private String readContentCincinnati(Document doc)
	{
		Elements article = doc.select("article .story-body");
		article.select("script,.ad,.gallery-nav,.story-tools,.byline-container,.short-caption,.exclude-from-newsgate,.carousel,.share,.story-share").remove();

		article.select(".caption-text,.caption-credit").tagName("figcaption");
		article.select("[class='body-text']").removeAttr("class");

		return finalFormat(article, true);
	}

	private String readContentReviewed(Document doc)
	{
		Elements article = doc.select("#video-preview,.heropic,[itemprop='articleBody'] .page_section");
		article.select(".callout,.credit,.disclaimer,.brightcove").remove();

		for (Element e : article.select("h3"))
			if (e.text().contains("Related Video"))
				e.remove();

		return finalFormat(article, false);
	}

	private String readContent10best(Document doc)
	{
		Elements article = doc.select(".subheader-article,[itemprop='articleBody']");
		article.select("script,.row,#last,#next-box").remove();
		article.select(".photo-credit").tagName("figcaption");

		return finalFormat(article, false);
	}

	@Override
	protected Document getDocument(String url)
	{
		try {
			return org.jsoup.Jsoup.connect(url)
					.timeout(10000)
					.userAgent(USER_AGENT)
					.get();
		} catch (Exception ignored) {
		}
		try {
			return org.jsoup.Jsoup.connect(url).userAgent(USER_AGENT).get();
		} catch (Exception ignored) {
		}
		return null;
	}

}
