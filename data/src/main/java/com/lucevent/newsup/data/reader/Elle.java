package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Elle extends com.lucevent.newsup.data.util.NewsReader {

	//  Tags:
	//  [category,                              description, guid, item, link, media:content,                           pubdate, title]
	//  [category, content:encoded, dc:creator, description, guid, item, link, postid, postimg, postimgapa, postimglow, pubdate, title]

	public Elle()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{TAG_CONTENT_ENCODED},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_MEDIA_CONTENT, "postimgapa".hashCode()},
				"");
	}

	@Override
	protected String parseDescription(Element prop)
	{
		return org.jsoup.Jsoup.parse(prop.text()).text();
	}

	@Override
	protected String parseContent(Element prop)
	{
		Element article = jsoupParse(prop);
		article.select("script[src*='instagram']").remove();
		return finalFormat(article, false);
	}

	@Override
	protected Enclosure parseEnclosure(Element prop)
	{
		if (prop.tagName().startsWith("post"))
			return new Enclosure(prop.text(), "image", "");
		return new Enclosure(prop.attr("url"), "image", "");
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		if (news_url.contains("/pasarelas/")) {
			Elements slides = doc.select(".slide-container");
			slides = slides.select(".slide-media img,.image-credit,.slideshow-slide-hed");
			slides.select(".slideshow-slide-hed").tagName("p");
			slides.select(".image-credit").tagName("figcaption");
			for (Element img : slides.select("img[data-src]")) {
				String src = img.attr("data-src");
				cleanAttributes(img);
				img.attr("src", src);
			}
			return finalFormat(slides, true);
		}

		Elements article = doc.select(".standard-body");

		if (article.isEmpty()) {
			article = doc.select(".listicle-body");
			if (article.isEmpty()) {
				article = doc.select(".longform-body");
				if (article.isEmpty()) {
					article = doc.select(".slideshow-desktop-dek,.slide-container");
					if (article.isEmpty()) {
						// No content found
						return null;
					} else {
						article = article.select(".slideshow-desktop-dek,.slide-media img,.image-credit,.slideshow-slide-dek");
						article.select(".image-credit").tagName("figcaption");
					}
				}
			} else {    // listicle-body
				article = article.select(".listicle-intro,.listicle-slide-hed,.slide-media img,.slide-media iframe,.slide-media .image-credit,.listicle-slide-dek");
				article.select(".listicle-slide-hed").tagName("h4");
			}
		}
		article.select(".standard-info,.breaker-ad,.longform-info,.social-button-group").remove();
		article.select(".image-credit").tagName("figcaption");

		for (Element img : article.select("img[data-src]")) {
			String src = img.attr("data-src");
			cleanAttributes(img);
			img.attr("src", src);
		}
		for (Element e : article.select("iframe[data-src]")) {
			String src = e.attr("data-src");
			e.attr("src", src);
			e.removeAttr("data-src");
		}
		for (Element e : article.select(".hearstPlayer[data-player-id]")) {
			e.parent().html(insertIframe("https://player.elle.com/?id=" + e.attr("data-player-id")));
		}
		for (Element e : article.select(".fb-video")) {
			String src = e.attr("data-href");
			if (!src.contains("video.php")) {
				int i1 = src.indexOf("videos/");
				if (i1 > 0) {
					int i2 = src.indexOf("/", i1 + 7);
					e.parent().html(insertIframe("https://m.facebook.com/video/video.php?v=" + src.substring(i1 + 7, i2)));
				}
			}
		}
		for (Element e : article.select(".embed--twitter")) {
			Elements sch = e.select("blockquote");
			if (!sch.isEmpty()) {
				Element tweet = sch.get(0).attr("class", "twitter-tweet");
				e.html(tweet.outerHtml());
			}
			e.removeAttr("class");
		}

		return finalFormat(article, true);
	}

}