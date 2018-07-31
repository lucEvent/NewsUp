package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Date;
import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Abc extends com.lucevent.newsup.data.util.NewsReader {

	/**
	 * tags:
	 * [category,                  dc:creator, description, guid,         item, link, pubdate,         title]
	 * [comments, content:encoded, dc:creator, description, guid, imagen, item, link, pubdate, source, title]
	 **/

	public Abc()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{TAG_CONTENT_ENCODED},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{"imagen".hashCode()},
				"");
	}

	@Override
	protected long parseDate(Element prop)
	{
		return Date.toDate(prop.text()) - 7200000;//2 hours
	}

	@Override
	protected Enclosure parseEnclosure(Element prop)
	{
		String enclosure = prop.text();
		if (!enclosure.isEmpty()) {

			Document d = Jsoup.parse(prop.text());
			Elements imgs = d.select("img");
			if (!imgs.isEmpty())
				return new Enclosure(imgs.get(0).attr("src"), "image", "");
		}
		return null;
	}

	@Override
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		Element article;
		// Parsing content (and description)
		if (news.content.isEmpty()) {
			article = jsoupParse(news.description);

			String dscr = article.text();
			news.description = dscr.substring(0, Math.min(dscr.length(), 300));
		} else
			article = jsoupParse(news.content);

		news.content = mParseContent(article);
		// end

		if (news.imgSrc == null)
			news.imgSrc = findImageSrc(article);

		return news;
	}

	private String mParseContent(Element article)
	{
		article.select(".remision-galeria,script").remove();

		for (Element e : article.select("embed[src*='youtube.com']")) {
			Element p = e.parent();
			p.html(insertIframe(e.attr("src")));
			p.tagName("p");
			cleanAttributes(p);
		}
		for (Element e : article.select("a[data-lightbox-src]")) {
			String href = e.attr("data-lightbox-src");
			cleanAttributes(e);
			e.attr("href", href);
		}

		cleanAttributes(article.select("img[src]"), "src");

		return finalFormat(article, false);
	}

}
