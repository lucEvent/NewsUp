package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;

import org.jsoup.nodes.Element;

public class ElConfidencial extends com.lucevent.newsup.data.util.NewsReader {

	//tags: author, content, id, link, media:content, media:credit, media:text, media:title, name, published, summary, title, updated, uri

	public ElConfidencial()
	{
		super(TAG_ITEM_ENTRY,
				new int[]{TAG_TITLE},
				new int[]{TAG_ID},
				new int[]{TAG_SUMMARY},
				new int[]{TAG_CONTENT},
				new int[]{TAG_UPDATED},
				new int[]{},
				new int[]{TAG_MEDIA_CONTENT},
				"");
	}

	@Override
	protected String parseTitle(Element prop)
	{
		return prop.html().replace("&lt;![CDATA[", "").replace("]]&gt;", "").replace("&amp;#039;", "'");
	}

	@Override
	protected Enclosure parseEnclosure(Element prop)
	{
		return new Enclosure(prop.attr("url"), prop.attr("type"), "0");
	}

	@Override
	protected String parseContent(Element prop)
	{
		org.jsoup.nodes.Element article = jsoupParse(prop);
		article.children().last().remove();
		article.select(".footer-video-text").tagName("figcaption");

		cleanAttributes(article.select("img"), "src");

		return finalFormat(article, false);
	}

}
