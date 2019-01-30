package com.lucevent.newsup.data.reader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TheTelegraph extends com.lucevent.newsup.data.util.NewsReader {

	/**
	 * Tags:
	 * [author, category,              enclosure, guid, item, link, pubdate, title]
	 * [author, category, description, enclosure, guid, item, link, pubdate, title]
	 */

	public TheTelegraph()
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
		return org.jsoup.Jsoup.parse(prop.text()).text();
	}

	@Override
	protected String parseCategory(Element prop)
	{
		String[] cats = prop.text().split(":");
		return cats.length > 0 ? cats[cats.length - 1] : "";
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select(".article__content");

		if (article.isEmpty()) {

			article = doc.select(".gallery");

			if (article.isEmpty()) {

				article = doc.select(".page__feature img,.info-bar__container,.page-article,.product-gallery__thumbnail");

				if (article.isEmpty()) {

					article = doc.select("#slideshow,#TourContainer");

					article.select("#breadcrumb,#accommodation_pane,#deckplan_pane,#gallery_pane,#reviews_pane,#supplierdetails").remove();
					article.select("[style]").removeAttr("style");
					article.select("[class]").removeAttr("class");
					article.select("[id]").removeAttr("id");

				} else {
					article.select(".product-supplier,.article__footer,.social-share,[itemprop='articleBody'],.gallery-trigger").remove();

					article.select(".departure-dates__section-header").tagName("b");

					for (Element img : article.select(".product-gallery__thumbnail img"))
						img.attr("src", img.attr("src").replace("-thumbnail", ""));
					for (Element galleryItem : article.select(".product-gallery__thumbnail"))
						galleryItem.tagName("p").removeAttr("id").removeAttr("class").removeAttr("aria-labels").removeAttr("data-thumbnail-index");
				}
			} else {

				String data = article.select(".gallery__elements").html();
				data = "{items:" + data.substring(0, data.length()).replace("\n", "") + "}";

				StringBuilder sb = new StringBuilder();
				try {
					JSONArray items = new JSONObject(data).optJSONArray("items");
					for (int i = 0; i < items.length(); i++) {
						JSONObject item = items.getJSONObject(i);

						String imgsrc = item.getString("image-src").replace("-small", "-large");
						String text = item.getString("image-caption");

						sb.append("<p>").append(text).append("</p>");
						sb.append("<img src='").append(imgsrc).append("'>");
					}

				} catch (JSONException e) {
					//System.out.println("JSON exception:" + e.getMessage());
				}

				return sb.toString();
			}
		} else {
			article = article.select("header img:not(.videoPlayer img),header .videoPlayer,[itemprop='articleBody']");
		}

		if (news_url.contains("/travel/destinations/")) {
			article.select(".snippetReference,.travelProductListing").remove();

			article.select("ul").tagName("blockquote");
			article.select("li").tagName("center");
		}

		for (Element img : article.select(".articleBodyImage")) {
			img.html(img.select("noscript").html());
		}
		for (Element img : article.select("img")) {
			img.attr("src", img.attr("src").replace("-small", "-large"));
			cleanAttributes(img, "src");
		}
		for (Element video : article.select(".videoPlayer")) {
			Elements div = video.select(".video-player");
			if (!div.isEmpty()) {
				String video_id = div.get(0).attr("id").replace("video-", "");
				video.html(insertIframe("https://static.telegraph.co.uk/tpp-secure/video.html?embed=" + video_id + "&custom="));
			}
		}
		for (Element iframe : article.select(".htmlEmbed")) {
			Elements div = iframe.select("aside");
			if (!div.isEmpty()) {
				String iframe_src = div.get(0).attr("data-html-uri");
				if (iframe_src.startsWith("//"))
					iframe_src = "http:" + iframe_src;

				iframe.html(insertIframe(iframe_src));
			}
		}
		for (Element img : article.select("div.lazy-image")) {
			String src = img.attr("data-src");
			cleanAttributes(img);
			img.tagName("img")
					.attr("src", src);
			img.html("");
		}
		article.select("script,input,.live-stream__bar,button,opta,.tmg-particle,.component-content:has(.embed--brexit--bulletin--container)").remove();
		article.select(".quote").tagName("blockquote");
		article.select("q,tmg-travel-availability").tagName("div");
		article.select("strike").tagName("s");

		return finalFormat(article, true);
	}

}