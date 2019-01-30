package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;

import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

public class TheAtlantic extends com.lucevent.newsup.data.util.NewsReader {

	/**
	 * Tags
	 * [author,           content, entry, id, link, media:content, name, published, summary, title, updated, uri]
	 * [author, category, content, entry, id, link,                name, published, summary, title,          uri]
	 * [category, description, guid, item, link, media:content, media:text, media:thumbnail, pubdate, title]
	 */

	public TheAtlantic()
	{
		super(TAG_ITEM_ITEMS_ENTRY,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION, TAG_SUMMARY},
				new int[]{TAG_CONTENT},
				new int[]{TAG_PUBDATE, TAG_PUBLISHED},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_MEDIA_CONTENT},
				"");
	}

	@Override
	protected String parseLink(Element prop)
	{
		String link = prop.text();
		if (link.isEmpty())
			link = prop.attr("href");
		return link;
	}

	@Override
	protected String parseCategory(Element prop)
	{
		String category = prop.text();
		if (category.isEmpty())
			category = prop.attr("term");
		return category;
	}

	@Override
	protected Enclosure parseEnclosure(Element prop)
	{
		return new Enclosure(prop.attr("url"), "image", "");
	}

	@Override
	protected String parseDescription(Element prop)
	{
		return org.jsoup.Jsoup.clean(prop.text(), Whitelist.simpleText());
	}

	@Override
	protected String parseContent(Element prop)
	{
		Element article = jsoupParse(prop);
		article.select("script,.callout,.partner-box,img[width='1']").remove();

		return finalFormat(article, false);
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select("#article-content,.photo img,.photo .caption");

		for (Element img : article.select("img")) {
			String src = img.attr("data-src");
			cleanAttributes(img);
			img.attr("src", src);
		}

		return finalFormat(article, true);
	}

}
