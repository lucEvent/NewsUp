package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Publico extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [articleslug, author, category, categoryslug, description, enclosure, guid, item, link, pubdate, title]

	public Publico()
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
	protected Enclosure parseEnclosure(Element prop)
	{
		String url = prop.attr("url");
		if (url.contains("/crop/")) {
			url = url.replace("files/crop/", "");
			url = url.substring(0, url.indexOf(".", 30)) + ".jpg";
		}
		return new Enclosure(url, prop.attr("type"), prop.attr("length"));
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select(".article-header-epigraph,.article-multimedia-content,.article-body");
		article.select("script,.relacionadas,footer").remove();

		for (Element fig : article.select("figure[itemprop='image']")) {
			Elements img = fig.select("img");
			Elements caption = fig.select("[itemprop='description']");
			cleanAttributes(fig);
			cleanAttributes(img, "src");
			cleanAttributes(caption);

			fig.html(img.outerHtml() + caption.outerHtml());
		}
		for (Element fig : article.select(".image-article")) {
			Elements img = fig.select("img");
			Elements caption = fig.select(".description-overlay-image p");
			caption.tagName("figcaption");

			cleanAttributes(fig);
			cleanAttributes(img, "src");
			cleanAttributes(caption);

			fig.html(img.outerHtml() + caption.outerHtml());
		}

		article.select(".quote").tagName("blockquote");

		return finalFormat(article, false);
	}

	@Override
	protected Document getDocument(String url)
	{
		try {
			return org.jsoup.Jsoup.connect(url)
					.timeout(10000)
					.validateTLSCertificates(false)
					.get();
		} catch (Exception e) {
			//System.out.println("[" + e.getClass().getSimpleName() + "] Can't read page. Trying again");
		}
		return null;
	}

}
