package com.lucevent.newsup.data.reader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Andro4all extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [category, dc:creator, description, guid, item, link, media:content, media:copyright, media:description, media:thumbnail, media:title, pubdate, title]

	public Andro4all()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_MEDIA_CONTENT},
				"");
	}

	@Override
	protected String readNewsContent(Document doc, String news_url)
	{
		Elements article = doc.select(".content,.post_content");
		article.select("script,.jetpack-slideshow-noscript,div:has(#div-desktop-article-filmstrip)").remove();

		for (Element slides : article.select(".slideshow-window")) {
			String data = "{items:" + slides.attr("data-gallery") + "}";

			StringBuilder sb = new StringBuilder();
			try {
				JSONArray items = new JSONObject(data).optJSONArray("items");
				for (int i = 0; i < items.length(); i++) {
					JSONObject item = items.getJSONObject(i);

					sb.append("<p>");

					String caption = item.getString("caption");
					if (!caption.isEmpty())
						sb.append("<center><b>").append(caption).append("</b></center>");

					sb.append("<img src='").append(item.getString("src")).append("'></p>");
				}
			} catch (JSONException e) {
				//System.out.println("JSON exeption:" + e.getMessage());
			}
			slides.html(sb.toString());
			cleanAttributes(slides);
		}
		for (Element slides : article.select(".gallery")) {
			StringBuilder sb = new StringBuilder();
			for (Element img : slides.select("img")) {
				sb.append("<img src='").append(img.attr("data-orig-file")).append("'>");
			}
			slides.html(sb.toString());
			cleanAttributes(slides);
		}
		for (Element ad : article.select("strong")) {
			String text = ad.text();
			if (text.startsWith("Te puede interes")
					|| text.startsWith("Te recomend")
					|| text.startsWith("También te puede inter")) {
				ad.parent().remove();
			}
		}
		cleanAttributes(article.select("img"), "src");
		article.select("[style]").removeAttr("style");
		article.select(".wp-caption-text").tagName("figcaption");

		return finalFormat(article, false);
	}

	@Override
	protected Document getDocument(String url)
	{
		try {
			return org.jsoup.Jsoup.connect(url)
					.timeout(10000)
					.userAgent(USER_AGENT)
					.validateTLSCertificates(false)
					.get();
		} catch (Exception ignored) {
		}
		return null;
	}

}
