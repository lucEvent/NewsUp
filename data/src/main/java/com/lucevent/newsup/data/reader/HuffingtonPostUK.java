package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;

public class HuffingtonPostUK extends AbstractEnglishHuffingtonPost {

	private static final String PLAYBUZZ_WIDGETS = "<script type='text/javascript' src='http://cdn.playbuzz.com/widget/feed.js'></script>";

	// Tags: [category, dc:created, dc:creator, dc:identifier, dc:publisher, dc:rightsholder, description, guid, ingested, item, link, media:content, media:credit, media:description, media:embed, media:media_html, media:title, modified, pubdate, source_id, ss:media_html, ss:slideshow, title]

	public HuffingtonPostUK()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{},
				new int[]{TAG_DESCRIPTION},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_MEDIA_CONTENT},
				PLAYBUZZ_WIDGETS);
	}

	@Override
	protected String parseContent(Element prop)
	{
		Element article = jsoupParse(prop);
		fixVDBPlayer(article);

		article.select("script,.related-entries,.extra-content").remove();
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

}