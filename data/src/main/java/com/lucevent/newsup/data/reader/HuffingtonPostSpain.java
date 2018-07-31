package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public class HuffingtonPostSpain extends com.lucevent.newsup.data.util.NewsReader {

	// tags: [category, dc:created, dc:creator, dc:identifier, dc:publisher, dc:rightsholder, description, guid, ingested, item, link, media:content, media:credit, media:description, media:embed, media:media_html, media:title, modified, pubdate, source_id, ss:media_html, ss:slideshow, title]

	public HuffingtonPostSpain()
	{
		super(TAG_ITEM_ITEMS_ENTRY,
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
		Element article = jsoupParse(prop);
		article.getElementsByTag("blhttps:").remove();
		article.select("script,.related-entries").remove();

		for (Element e : article.select(".listicle")) {
			cleanAttributes(e);
			e.tagName("p");

			for (Element slide : e.select(".listicle__slide")) {
				String title = slide.select(".listicle__slide-header").text();
				slide.html(
						(!title.isEmpty() ? "<h3>" + title + "</h3>" : "")
								+ insertImg(slide.attr("data-sharingimage"))
								+ insertCaption(slide.attr("data-credit"))
								+ "<p>" + slide.select(".listicle__slide-caption").text() + "</p>"
				);
				slide.tagName("p");
				cleanAttributes(slide);
			}
		}
		return finalFormat(article, false);
	}

	@Override
	protected Enclosure parseEnclosure(Element prop)
	{
		String src = prop.attr("url");
		if (src.contains("trans.gif"))
			return null;
		if (src.startsWith("//"))
			src = "http:" + src;
		return new Enclosure(src, prop.attr("medium"), "");
	}

	@Override
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		if ((news.title.contains("imágenes") || news.title.contains("fotos")) && enclosures.size() > 1) {
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i < enclosures.size(); i++)
				sb.append(enclosures.get(i).html());

			news.content += sb.toString();
		}
		return news;
	}

}