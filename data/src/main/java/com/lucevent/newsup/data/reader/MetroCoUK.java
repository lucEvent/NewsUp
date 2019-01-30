package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MetroCoUK extends com.lucevent.newsup.data.util.NewsReader {

	/**
	 * Tags
	 * [category, content:encoded, dc:creator, description, enclosure, guid, item, link, media:content, media:description, media:thumbnail, media:title, pubdate, section, title]
	 * [category, content:encoded, dc:creator, description, enclosure, guid, item, link, media:content, media:description, media:thumbnail, media:title, pubdate,          title]
	 * [category, content:encoded, dc:creator, description,            guid, item, link, media:content, media:description, media:thumbnail, media:title, pubdate,          title]
	 **/

	public MetroCoUK()
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
		return Jsoup.parse(prop.text()).text();
	}

	@Override
	protected Enclosure parseEnclosure(Element prop)
	{
		String url = prop.attr("url");
		int index = url.indexOf('?');
		if (index != -1) url = url.split("\\?")[0];
		return new Enclosure(url + "?quality=25", "image", "0");
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select(".article-body");

		article.select("script,post,.item-share,.thumbnail-link,.zone-post-strip,#article-below-content,.anchor,.mor-link,.metro-sassy-poll,[data-track^='fb-promo'],.metro-email-signup,form,span.icon-video").remove();

		for (Element video : article.select("div video")) {
			video.attr("controls", "");
			video.parent().html(video.outerHtml());
		}
		for (Element img : article.select("img[src^='data:image']"))
			img.attr("src", img.attr("data-src"));

		cleanAttributes(article.select("img"), "src");

		article.select("[style]").removeAttr("style");
		article.select("[class]:not([class^='twitter'])").removeAttr("class");

		return finalFormat(article, false).replace("<p></p>", "").replace("<p>&nbsp;</p>", "");
	}

}
