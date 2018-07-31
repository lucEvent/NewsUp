package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;

import org.jsoup.nodes.Element;

public class Time extends com.lucevent.newsup.data.util.NewsReader {

	// Tags: [category, content:encoded, dc:creator, description, guid, item, link, media:content, media:thumbnail, media:title, pubdate, title]

	public Time()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{TAG_CONTENT_ENCODED},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_MEDIA_CONTENT},
				"");
	}

	@Override
	protected String parseDescription(Element prop)
	{
		return org.jsoup.Jsoup.parse(prop.text()).text();
	}

	@Override
	protected String parseContent(Element prop)
	{
		Element article = jsoupParse(prop);
		article.select("[width='1'],.ad_brightcove_video-wrapper,script").remove();

		for (Element video : article.select("video")) {
			String attr1 = video.attr("data-account");
			String attr2 = video.attr("data-player");
			String attr3 = video.attr("data-embed");
			String attr4 = video.attr("data-video-id");
			String src = "https://players.brightcove.net/" + attr1 + "/" + attr2 + "_" + attr3 + "/index.html?videoId=" + attr4;
			video.parent().html(insertIframe(src));
		}

		wpcomwidget(article.select("form"));
		article.select("form").remove();

		return finalFormat(article, false);
	}

	@Override
	protected Enclosure parseEnclosure(Element prop)
	{
		return new Enclosure(prop.attr("url"), prop.attr("medium"), "0");
	}

}
