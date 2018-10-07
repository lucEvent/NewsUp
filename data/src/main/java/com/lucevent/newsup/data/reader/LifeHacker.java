package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Element;

public class LifeHacker extends com.lucevent.newsup.data.util.NewsReader {

	// tags: [category, dc:creator, description, guid, item, link, pubdate, title]

	public LifeHacker()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{},
				new int[]{TAG_DESCRIPTION},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{},
				"");
	}

	@Override
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		if (!news.content.isEmpty()) {
			Element article = jsoupParse(news.content);

			article.select(".has-video .jwplayer,.ad-container,.js_ad-dynamic,.show-for-small-only,.animationContainer").remove();

			for (Element slides : article.select(".slideshow-inset [data-slides]")) {
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
						String caption = item.getJSONArray("caption").getJSONObject(0).getString("value");

						sb.append("<img src='").append(imgsrc).append("'>");
						sb.append("<figcaption>").append(caption).append("</figcaption>");
					}

				} catch (JSONException e) {
					//   System.out.println("JSON exception:" + e.getMessage());
				}
				slides.parent().html(sb.toString());
			}

			cleanAttributes(article.select("a"), "href");

			news.imgSrc = findImageSrc(article);
			news.content = finalFormat(article, false);
		}
		return news;
	}

}
