package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class People extends com.lucevent.newsup.data.util.NewsReader {

	// Tags: [category, dc:creator, description, guid, item, link, media:content, media:credit, media:text, media:title, pubdate, title]

	public People()
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
	protected String parseDescription(Element prop)
	{
		String dscr = org.jsoup.Jsoup.parse(prop.text()).text();
		return dscr.substring(0, Math.min(300, dscr.length()));
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select(".article-content .sticky-video,.lead-image,#article-body");
		article.select("script,.ad").remove();

		for (Element vdiv : article.select(".video:has(video)")) {
			Element v = vdiv.select("video").first();

			String src = "https://players.brightcove.net/"
					+ v.attr("data-account") + "/" + v.attr("data-player") + "_" + v.attr("data-embed") + "/index.html?videoId=" + v.attr("data-video-id");

			vdiv.html(insertIframe(src));
		}

		for (Element top : article.select(".oembed")) {
			Element e = top;
			Elements e_children = e.children();
			Element first = e_children.first();
			while (e_children.size() == 1 && (first.tagName().equals("div") || first.tagName().equals("span"))) {
				e = first;
				e_children = e.children();
				first = e_children.first();
			}
			cleanAttributes(top);
			top.html(e.html());
		}
		for (Element img : article.select(".image-wrapper .lazy-image,.image-and-burst .lazy-image")) {
			String src = img.attr("data-src");
			String cap = img.select(".credit").html();
			img.parent().html(insertImg(src) + insertCaption(cap));
		}
		for (Element e : article.select("strong")) {
			String text = e.text();
			if (text.startsWith("RELATED")
					|| text.startsWith("VIDEO"))
				e.parent().remove();
		}

		return finalFormat(article, false);
	}

	@Override
	protected org.jsoup.nodes.Document getDocument(String url)
	{
		try {
			return org.jsoup.Jsoup.connect(url)
					.userAgent(USER_AGENT)
					.timeout(10000)
					.cookie("euConsent", "true")
					.get();
		} catch (Exception ignored) {
		}
		return super.getDocument(url);
	}

}
