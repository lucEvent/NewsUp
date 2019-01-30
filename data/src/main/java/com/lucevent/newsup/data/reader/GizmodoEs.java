package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GizmodoEs extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [category, dc:creator, description, guid, item, link, pubdate, title]

	public GizmodoEs()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{},
				"");
	}

	@Override
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		Element article = jsoupParse(news.description);
		if (news.description.contains(">Read more...<")) {

			news.description = article.text();

		} else {

			article.select(".ad-container,.js_ad-dynamic").remove();
			article.select(".pullquote").tagName("blockquote");

			for (Element e : article.select("figure.js_marquee-assetfigure:has(picture)")) {
				e.removeAttr("style");
				e.html(e.select("picture,figcaption").outerHtml());
			}

			cleanAttributes(article.select("img"), "src");

			news.content = finalFormat(article, false);

			Elements ps = article.select("p");
			if (!ps.isEmpty())
				news.description = ps.first().text();
		}
		news.imgSrc = findImageSrc(article);
		return news;
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select(".post-content");
		article.select(".referenced-wide,.js_ad-dynamic,[class^='ad-'],.related-module,.inset--story").remove();

		for (Element e : article.select("figure"))
			e.html(e.select("img,figcaption").outerHtml());

		for (Element img : article.select("img[data-anim-src]")) {
			String src = img.attr("data-anim-src");
			cleanAttributes(img);
			img.attr("src", src);
		}
		for (Element img : article.select("img[data-mp4-src]")) {
			String src = img.attr("data-mp4-src");
			cleanAttributes(img);
			img.attr("src", src);
		}
		for (Element iframe : article.select("iframe")) {
			String src = iframe.attr("data-recommend-id");
			if (src.startsWith("youtube"))
				src = src.replace("youtube://", "https://www.youtube.com/embed/");
			else if (src.startsWith("vimeo"))
				src = src.replace("vimeo://", "https://player.vimeo.com/video/");
			else
				src = iframe.attr("data-src");

			iframe.parent().html(insertIframe(src));
		}
		for (Element slides : doc.select(".slideshow-inset [data-slides]")) {
			String data = slides.attr("data-slides");
			if (data.isEmpty()) {
				continue;
			}

			StringBuilder sb = new StringBuilder();
			try {
				JSONArray items = new JSONObject("{items:" + data + "}").optJSONArray("items");
				for (int i = 0; i < items.length(); i++) {
					JSONObject item = items.getJSONObject(i);

					String imgsrc = "https://i.kinja-img.com/gawker-media/image/upload/" + item.getString("id") + "." + item.getString("format");
					sb.append("<img src='").append(imgsrc).append("'>");
				}

			} catch (JSONException e) {
				//   System.out.println("JSON exception:" + e.getMessage());
			}
			slides.parent().html(sb.toString());
		}

		article.select("img[data-frozen-src]").remove();

		for (Element img : doc.select("img.lazyload[src^='data']")) {
			String id = img.attr("data-chomp-id");
			cleanAttributes(img);
			img.attr("src", "https://i.kinja-img.com/gawker-media/image/upload/" + id);
		}

		article.select("[style]").removeAttr("style");

		return finalFormat(article, false);
	}


}
