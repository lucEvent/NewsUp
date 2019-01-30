package com.lucevent.newsup.data.reader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class VogueEs extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [author, entry, id, link, name, summary, title, updated]

	public VogueEs()
	{
		super(TAG_ITEM_ENTRY,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_SUMMARY},
				new int[]{},
				new int[]{TAG_UPDATED},
				new int[]{},
				new int[]{},
				"");
	}

	@Override
	protected String parseLink(Element prop)
	{
		return prop.attr("href");
	}

	@Override
	protected long parseDate(Element prop)
	{
		return super.parseDate(prop) - 2 * 60 * 60 * 1000; // - 2 hours
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		if (news_url.contains("/galerias/")) {

			String gallery_json = "";
			for (Element sc : doc.select("script")) {
				gallery_json = sc.html();
				if (gallery_json.startsWith("var gallery_content"))
					break;
			}
			gallery_json = gallery_json.replaceFirst("var gallery_content = ", "");

			StringBuilder sb = new StringBuilder();
			try {
				JSONObject json_data = new JSONObject(gallery_json);

				String description = json_data.getString("subtitle");
				sb.append("<p><strong>").append(description).append("</strong></p><br/>");

				JSONArray pics = json_data.optJSONArray("pictures");
				for (int i = 0; i < pics.length() - 1; i++) {
					JSONObject pic = pics.getJSONObject(i);

					String pic_title = pic.getString("picture_title");
					String pic_desc = pic.getString("description");
					String pic_src = pic.getString("picture_src");

					sb.append("<h4>").append(pic_title).append("</h4>");
					sb.append("<img src='").append(pic_src).append("'>");
					sb.append("<p>").append(pic_desc).append("</p><br/>");
				}
			} catch (JSONException e) {
				//     System.out.println("JSON exception:" + e.getMessage());
			}

			Element article = jsoupParse(sb.toString());
			article.getElementsByTag("o:p").remove();
			article.select("[style]").removeAttr("style");

			return finalFormat(article, false);
		}
		doc.getElementsByTag("o:p").remove();
		Elements article = doc.select(".article_subtitle,.article_content");
		article.select("script,.interior_articulo_ad,[id^='div-gpt-ad-'],.BrightcoveExperience,.read_more,.quick_nav_and_social,#videos_more_viewed").remove();

		for (Element subtitle : article.select(".article_subtitle"))
			subtitle.tagName("p").html("<strong>" + subtitle.text() + "</strong>");

		for (Element lazy_img : article.select("img.lazy")) {
			String src = lazy_img.attr("data-src");
			cleanAttributes(lazy_img);
			lazy_img.attr("src", src);
		}

		article.select(".full_image_block .info").tagName("figcaption");

		return finalFormat(article, true);
	}

}
