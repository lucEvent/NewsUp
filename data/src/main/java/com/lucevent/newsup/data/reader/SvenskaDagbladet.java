package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SvenskaDagbladet extends com.lucevent.newsup.data.util.NewsReader {

	// tags: [category, description, guid, item, link, pubdate, title]

	public SvenskaDagbladet()
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
	protected String parseDescription(Element prop)
	{
		return org.jsoup.Jsoup.parse(prop.text()).text();
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements e = doc.select(".Deck,.Body");
		e.select("script,.Body-ad,.AdPositionData,.Body-pull,.Quote,.ExternalLink,.Ad,.ThumbnailList,.paywall-loader,a[href='/premium'],.Figure-expandIcon,.scrbbl-embed,[data-loader-url]").remove();
		e.select("[style]:not(.instagram-media,.instagram-media *)").removeAttr("style");

		cleanAttributes(e.select("img[srcset]"), "srcset");

		return finalFormat(e, true);
	}

}
