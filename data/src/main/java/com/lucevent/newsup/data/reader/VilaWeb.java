package com.lucevent.newsup.data.reader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

public class VilaWeb extends com.lucevent.newsup.data.util.NewsReader {

	public VilaWeb()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{TAG_CONTENT_ENCODED},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_ENCLOSURE},
				"");
	}

	@Override
	protected String parseDescription(Element prop)
	{
		String descr = Jsoup.parse(prop.text()).text();
		return descr.substring(0, 300 < descr.length() ? 300 : descr.length()) + "...";
	}

	@Override
	protected String parseContent(Element prop)
	{
		Element article = jsoupParse(prop);
		article.select("script[src~=twitter],script[src~=instagram],noscript,#tlTagContainer").remove();

		article.select("[style]").removeAttr("style");
		article.select("[width]").removeAttr("width");

		for (Element e : article.select("a:has(script)"))
			if (e.text().startsWith("Watch video")) {
				String script = e.html();
				int i = script.indexOf(".mp4");
				if (i > 0) {
					int j = script.lastIndexOf("\"", i);
					String src = script.substring(j + 1, i + 4);
					e.html(insertIframe(src));
					e.tagName("p");
				}
			}

		return finalFormat(article, false);
	}

}
