package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TeknikensVarld extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [category, content:encoded, dc:creator, description, guid, item, link, media:content, media:credit, media:description, media:thumbnail, media:title, pubdate, title]

	public TeknikensVarld()
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
	protected Enclosure parseEnclosure(Element prop)
	{
		return new Enclosure(prop.attr("url"), prop.attr("medium"), "");
	}

	@Override
	protected void readNewsContent(Document doc, News news)
	{
		Elements article = doc.select(".entry-content");
		doc.select("script,.-teaser-as-list,.desktop-ad,.mobile-ad").remove();

		doc.select(".wp-caption-text,.caption,.photographer").tagName("figcaption");
		doc.select(".btdm-factbox").tagName("blockquote");

		for (Element fig : article.select("figure:has(noscript)")) {
			cleanAttributes(fig);
			fig.html(fig.select("noscript,.wp-caption-body").html());
		}

		cleanAttributes(doc.select("img"), "src");

		news.content = finalFormat(article, false);
	}

}
