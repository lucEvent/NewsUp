package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MundoDeportivo extends com.lucevent.newsup.data.util.NewsReader {

	/**
	 * Tags
	 * [category, description, enclosure, guid, item, link, pubdate, title]
	 * [category, description, guid, item, link, pubdate, title]
	 */

	public MundoDeportivo()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_ENCLOSURE},
				"");
	}

	@Override
	protected String parseDescription(Element prop)
	{
		String description = prop.text();

		if (description.contains("&lt;"))
			return "";

		int idash = description.indexOf("- ");
		if (idash != -1)
			description = description.substring(idash + 2);

		return description;
	}

	@Override
	protected Enclosure parseEnclosure(Element prop)
	{
		return new Enclosure(prop.attr("url").replace("a/thumbnail", ""), prop.attr("type"), prop.attr("length"));
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select("article.gallery-leaf");

		if (article.isEmpty()) {
			article = doc.select(".story-leaf-figure,.story-leaf-body-video,.story-leaf-body .story-leaf-txt-p,.live-scribble");
			article.select("script,.story-leaf-relatednews").remove();

			for (Element e : article.select(".twitter-tweet > a[href*='script']")) {
				Element p = e.parent().parent();
				p.html(e.attr("href"));
				p.select("script").remove();
			}
		} else {
			article = article.select(".gallery-leaf-image,.gallery-leaf-title");
			article.select(".gallery-leaf-title").tagName("p");
		}
		article.select("script,.adv,.hidden-md,.poll-leaf,.related-news-story,.story-leaf-social-btns,.publi-box,.hidden-xs,.story-leaf-block-comments,[data-name='modulo-outbrain'],ins").remove();
		article.select(".story-leaf-box").tagName("blockquote");

		cleanAttributes(article.select("figure"));
		cleanAttributes(article.select("img"), "src");

		return finalFormat(article, true);
	}

}
