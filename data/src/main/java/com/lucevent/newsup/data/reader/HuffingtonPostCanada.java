package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HuffingtonPostCanada extends AbstractEnglishHuffingtonPost {

	// Tags: [author, description, enclosure, guid, item, link, pubdate, title]

	public HuffingtonPostCanada()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{},
				new int[]{TAG_DESCRIPTION},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_MEDIA_CONTENT},
				"");
	}

	@Override
	protected String parseContent(Element prop)
	{
		Element article = jsoupParse(prop.text().replace("<br />", "<p></p>"));
		fixVDBPlayer(article);

		article.select("script[src*='.twitter.'],script[src*='.instagram.']").remove();

		repairLinks(article, "data-placeholder");
		repairLinks(article, "data-config");
		cleanAttributes(article.select("img"), "src");

		String content = finalFormat(article, false);

		int i0 = content.indexOf("type=type=");
		if (i0 != -1) {
			int i1 = content.indexOf("articlesList=", i0);
			if (i1 != -1) {
				i1 = content.indexOf("<", i1);
				content = content.substring(0, i0) + content.substring(i1, content.length());
			}
		}

		i0 = content.indexOf("<hh-");
		if (i0 != -1) {
			int i1 = content.indexOf("-hh>", i0 + 4);
			i1 = content.indexOf("-hh>", i1 + 4);
			content = content.replace(content.substring(i0, i1 + 4), "");
		}
		return content;
	}

	@Override
	protected Enclosure parseEnclosure(Element prop)
	{
		return new Enclosure(prop.attr("url"), prop.attr("medium"), "");
	}

	@Override
	protected Document getDocument(String url)
	{
		try {
			return org.jsoup.Jsoup.connect(url)
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
					.timeout(10000)
					.validateTLSCertificates(false)
					.get();
		} catch (Exception e) {
			//System.out.println("[" + e.getClass().getSimpleName() + "] Can't read page. Trying again");
		}
		return null;
	}

}