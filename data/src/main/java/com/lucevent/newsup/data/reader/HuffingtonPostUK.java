package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;

import org.jsoup.nodes.Element;

public class HuffingtonPostUK extends com.lucevent.newsup.data.util.NewsReader {

	private static final String PLAYBUZZ_WIDGETS = "<script type='text/javascript' src='http://cdn.playbuzz.com/widget/feed.js'>";

	// Tags: [author, description, enclosure, guid, item, link, pubdate, title]

	public HuffingtonPostUK()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{},
				new int[]{TAG_DESCRIPTION},
				new int[]{TAG_PUBDATE},
				new int[]{},
				new int[]{TAG_ENCLOSURE},
				PLAYBUZZ_WIDGETS);
	}

	@Override
	protected String parseContent(Element prop)
	{
		Element article = jsoupParse(prop);
		article.select("script").remove();
		article.select("br").tagName("p");

		for (Element e : article.select("strong"))
			if (e.text().startsWith("SEE ALSO:"))
				try {
					e.parent().remove();
				} catch (Exception ignored) {
				}

		for (Element e : article.select(".pb_feed,.playbuzz")) {
			e.html(e.outerHtml());
			cleanAttributes(e);
			e.tagName("nuwidget");
		}

		article.select("[style]").removeAttr("style");

		repairLinks(article, "data-placeholder");
		repairLinks(article, "data-iframely-url");

		String content = finalFormat(article, false);

		int i0 = content.indexOf("type=type=");
		if (i0 != -1) {
			int i1 = content.indexOf("articlesList=", i0);
			if (i1 != -1) {
				i1 = content.indexOf("<", i1);
				content = content.substring(0, i0) + content.substring(i1, content.length());
			}
		}
		return content;
	}

	@Override
	protected Enclosure parseEnclosure(Element prop)
	{
		String type = prop.attr("type");
		if (type.startsWith("image")) {
			String url = prop.attr("url");
			if (url.contains("74_58"))
				url = url.replace("74_58", "300_219");
			else if (url.contains("-mini")) {
				url = url.replace("-mini", "-large300");
			}
			return new Enclosure(url, type, "");
		}
		return null;
	}

}