package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BBC extends com.lucevent.newsup.data.util.NewsReader {

	// tags: [description, guid, item, link, media:thumbnail, pubdate, title]

	public BBC()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_GUID},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{},
				new int[]{"media:thumbnail".hashCode()},
				"");
	}

	@Override
	protected Enclosure parseEnclosure(Element prop)
	{
		return new Enclosure(prop.attr("url"), "image", "");
	}

	@Override
	protected String readNewsContent(Document doc, String news_url)
	{
		Elements article = doc.select("[property='articleBody']");

		if (article.isEmpty()) {

			article = doc.select(".story-inner img,.story-inner .map-body");
			if (article.isEmpty()) {

				article = doc.select(".main_content_wrapper .main_image,.main_content_wrapper .main_article_text");
				if (article.isEmpty()) {

					article = doc.select(".gallery .gallery-intro__summary,.gallery .gallery-images");
					if (article.isEmpty()) {

						article = doc.select("#story-body");
						if (article.isEmpty()) {

							article = doc.select(".gel-body-copy");
							if (article.isEmpty()) {
								//todo
								return null;
							}

						} else {

							Elements intro = article.select(".sp-story-body__introduction");
							if (!intro.isEmpty()) {
								intro.get(0).tagName("b").wrap("<p>").removeAttr("class");
							}
							article.select(".sp-story-body__table").wrap("<blockquote>");
							article.select(".bbccom_slot,.tab-selector__tab-list,.lx-stream-show-more,.visually-hidden,style,script,ul:has(a),button").remove();

							for (Element figure : article.select("figure")) {
								Elements img = figure.select("img");

								if (img.isEmpty()) {
									figure.remove();
									continue;
								}
								String src;
								if (img.attr("class").equals("sp-lazyload"))
									src = img.attr("data-src").replace("{width}{hidpi}", "624");
								else
									src = img.attr("src");

								figure.html("<img src='" + src + "'>");

							}
							for (Element e : article.select("a"))
								if (e.text().startsWith("Read more"))
									cleanAttributes(e.html(""));

							for (Element e : article.select("div[data-url]")) {
								e.html(insertIframe(e.attr("data-url")));
								cleanAttributes(e);
							}

							article.select("[class]").removeAttr("class");
							article.select("[data-reactid]").removeAttr("data-reactid");

							return finalFormat(article, false);
						}
					} else {

						article.select("ul").tagName("div");
						for (Element list_item : article.select(".gallery-images__list-item")) {
							Elements img = list_item.select("img");
							Elements description = list_item.select(".gallery-images__summary");

							img.attr("src", img.attr("src").replace("/304/", "/660/"))
									.removeAttr("class")
									.removeAttr("alt")
									.removeAttr("width").removeAttr("height");
							description.removeAttr("class");

							list_item.html(img.outerHtml() + description.outerHtml())
									.tagName("p")
									.removeAttr("class");
						}

						return finalFormat(article, false);
					}
				} else {
					article.select(".related_stories,.related_topics,.pullOut").remove();
				}
			}
		}
		article.select("noscript,link,#js-lookup-container,.news-vj-js-required,[style='display: none']").remove();

		for (Element js : article.select(".bbc-news-vj-iframe-wrapper")) {
			Elements ns = js.select("noscript");
			js.html(ns.isEmpty() ? "" : ns.html());
		}

		Elements intro = article.select(".story-body__introduction");
		if (!intro.isEmpty())
			intro.get(0).tagName("b").wrap("<p>").removeAttr("class");

		for (Element figure : article.select("figure")) {
			Elements imgs = figure.select("img");
			if (!imgs.isEmpty()) {
				Element img = imgs.get(0);
				img.attr("src", img.attr("src").replace("/320/", "/872/"));
				img.removeAttr("alt").removeAttr("class");
				figure.html(img.outerHtml());
			} else {
				imgs = figure.select(".js-delayed-image-load");
				if (!imgs.isEmpty()) {
					Element img = imgs.get(0);
					img.tagName("img");
					img.attr("src", img.attr("data-src").replace("/320/", "/872/"));
					img.removeAttr("data-alt").removeAttr("class").removeAttr("data-src");
					figure.html(img.outerHtml());
				}
			}
			figure.removeAttr("class");
		}
		for (Element e : article.select("a"))
			if (e.text().startsWith("Read more"))
				cleanAttributes(e.html(""));

		article.select("script,.mpu-ad,.media-with-caption,.media-player-wrapper,ul:has(a)").remove();

		return finalFormat(article, true);
	}
}
