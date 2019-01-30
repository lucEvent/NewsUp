package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ElPais extends com.lucevent.newsup.data.util.NewsReader {

	/**
	 * Tags:
	 * All have -> [description, guid, item, link, pubdate, title]
	 * Some have -> [author] [category] [dc:creator] [enclosure]
	 */

	public ElPais()
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
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		if (news_url.contains("/album/") || news_url.contains("/fotorrelato/")) {
			Elements article = doc.select("#contenedor_fotos li");

			for (Element img : article.select("img[data-src]")) {
				String src = img.attr("data-src");
				cleanAttributes(img);
				img.attr("src", src);
			}
			for (Element cap : article.select("figcaption")) {
				cap.tagName("p");
				cap.select(".foto-numero").remove();
				cap.select(".foto-titulo").tagName("h4");
				cap.select(".foto-texto").tagName("p");
				cap.select(".foto-firma").tagName("figcaption");
			}

			return finalFormat(article, false);
		}
		if (news_url.contains("/media/"))
			return null;
		if (news_url.contains("blogs.elpais.com")) {
			Elements article = doc.select(".entry-body");
			article.select("script,.txt-comentarios,.entry-footer").remove();
			article.select("[style]").removeAttr("style");
			article.select(".asset-img-link ~ span").tagName("figcaption");
			cleanAttributes(article.select("img[src]"), "src");

			return finalFormat(article, false);
		}
		if (doc.baseUri().contains("motor.elpais.com")) {
			Elements article = doc.select(".articulo-grande picture img,.wp-caption-text__grande,.entry-content");
			article.select(".articulos-relacionados").remove();
			article.select("[style]").removeAttr("style");
			article.select(".wp-caption-text").tagName("figcaption");
			cleanAttributes(article.select("img[src]"), "src");

			return finalFormat(article, true);
		}

		Elements article = doc.select(".articulo-apertura>figure,#articulo_contenedor>[itemprop='image'],#cuerpo_noticia");
		article.select("script,.sumario_apoyos,.boton_ampliar,noframes").remove();
		article.select(".sumario_despiece,.texto_grande").tagName("blockquote");
		article.select(".nota_pie").tagName("figcaption");

		for (Element img : article.select("img[data-src]")) {
			String src = img.attr("data-src");
			cleanAttributes(img);
			img.attr("src", src);
		}

		for (Element e : article.select(".sumario_eskup")) {
			Elements content = e.select("noscript");
			content.select(".autor").remove();
			cleanAttributes(content.select(".article"));
			for (Element d : content.select(".contenedorbotones")) {
				d.tagName("figcaption");
				d.html(d.text());
			}
			for (Element t : content.select("[itemprop='text'] a[href*='twitter']")) {
				t.parent().parent().html(
						"<blockquote class='twitter-tweet'><a href='"
								+ t.attr("href")
								+ "'></a></blockquote>");
			}
			e.html(content.html());
		}
		for (Element e : article.select("picture:has(source)")) {
			Elements img = e.select("img");
			if (!img.isEmpty())
				e.html(img.outerHtml());
		}
		cleanAttributes(article.select("img[src]"), "src");
		article.select("[data-mce-href]").removeAttr("data-mce-href");
		article.select("a[title]").removeAttr("title");
		article.select("[itemtype]").removeAttr("itemtype");

		return finalFormat(article, false);
	}

}