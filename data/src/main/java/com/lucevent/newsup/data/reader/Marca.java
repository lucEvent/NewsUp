package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Marca extends com.lucevent.newsup.data.util.NewsReader {

	private static final String SITE_STYLE = "<style>.ue-scoreboard-dual__state-item{list-style-type:none;}.ue-scoreboard-dual__team{display:flex;margin:10px 15px;}" +
			".ue-scoreboard-dual__team-name{display:flex;align-items:center;padding:0px 10px;}.ue-scoreboard-dual__team-badge{width:22px;height:auto;margin:0 5px;}" +
			".ue-scoreboard-dual__body{display:flex;margin:3px 0;}.ue-scoreboard-dual__score{border-radius:4px;background-color:#737373;color:#f1f1f1;padding:0 5px;}" +
			".ue-scoreboard-dual__match-time{display:none;}.away-team{flex-direction:row-reverse;}</style>";

	// tags:  category, dc:creator, description, guid, item, link, media:content, media:description, media:thumbnail, media:title, pubdate, title

	public Marca()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{"media:description".hashCode()},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_MEDIA_CONTENT},
				SITE_STYLE);
	}

	@Override
	protected String parseDescription(Element prop)
	{
		return jsoupParse(prop.text().replace("&lt;", "<").replace("&gt;", ">")).text();
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select(".news-item");

		if (article.isEmpty()) {
			// ARTICLE EMPTY!!!!
			return null;
		}

		article.select("script,.kicker,.content-ad,.section-title-group,.js-headline,.js-headlineb,.subtitle-items,.sharing-tools,.ad-item,.related-tags,.comments-btn,.aside-comments,.content,[itemprop='author'],.ue-scoreboard-ad-scoreboard").remove();

		for (Element e : article.select("[itemprop='video']")) {
			e.html(e.select("noscript").html());
			e.select("[style]").removeAttr("style");
		}

		article.select("[style]:not(.instagram-media,.instagram-media *)").removeAttr("style");

		return finalFormat(article, true);
	}

}
