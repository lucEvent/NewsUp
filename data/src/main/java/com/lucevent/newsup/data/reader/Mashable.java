package com.lucevent.newsup.data.reader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Mashable extends com.lucevent.newsup.data.util.NewsReader {

	// Tags [category, content:encoded, dc:creator, description, guid, item, link, mash:thumbnail, media:thumbnail, pubdate, title]

	public Mashable()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{"media:thumbnail".hashCode()},
				"");
	}

	@Override
	protected String parseDescription(Element prop)
	{
		String descr = Jsoup.parse(prop.text()).text();
		return descr.substring(0, 300 < descr.length() ? 300 : descr.length()) + "...";
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select(".article-image,.article-content");

		if (!article.isEmpty()) {
			article.select(".see-also,.viral-next-up,.image-credit").remove();

			for (Element e : article.select("h2")) {
				String text = e.text();
				if (text.startsWith("RELATED:")
						|| text.startsWith("BONUS:"))
					e.remove();
			}

			article.select("ol,li").tagName("p");

			Elements mashVideos = article.select(".content-mash-video");
			if (mashVideos.size() == 1)
				mashVideos.get(0).remove();

			article.select("[style]").removeAttr("style");

		} else {
			article = doc.select(".video-hub .content-mash-video");

			if (!article.isEmpty()) {

				Elements dscr = doc.select(".video-hub #current-video-info");
				dscr.select("#video-title,#video-shares").remove();
				article.addAll(dscr);

			} else {
				article = doc.select(".long-card");

				if (!article.isEmpty()) {
					article = article.select("[data-type='ImageBlock'] img,[data-type='TextBlock'] p");
				}
			}
		}

		for (Element vid : article.select(".content-mash-video script.playerMetadata")) {
			String info = vid.html();

			String src = findSubstringBetween(info, "\"embedUrl\":\"", "\"", false);
			String desc = findSubstringBetween(info, "\"description\":\"", "\"", false);

			Element p = vid.parent();
			cleanAttributes(p);

			p.html(insertIframe(src) + "<figcaption>" + desc + "</figcaption>");
		}
		article.select("script").remove();
		article.select("[data-channel]").removeAttr("data-channel");

		return finalFormat(article, true);
	}

}
