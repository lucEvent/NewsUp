package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.nio.charset.Charset;

public class ElJueves extends com.lucevent.newsup.data.util.NewsReader {

	/**
	 * tags:
	 * [author, dc:abstract, dc:creator, dc:modified, description, guid, item, link,                                          pubdate, title]
	 * [author, dc:abstract, dc:creator, dc:modified, description, guid, item, link, media:content, media:credit, media:text, pubdate, title]
	 */

	public ElJueves()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{},
				new int[]{TAG_MEDIA_CONTENT},
				"");
	}

	@Override
	protected String parseTitle(Element prop)
	{
		Document d = org.jsoup.Jsoup.parse(prop.text());
		d.charset(Charset.forName("ISO-8859-1"));
		return d.text();
	}

	@Override
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		if (news.link.contains("/vineta-del-dia/") && news.imgSrc != null) {
			news.content = insertImg(news.imgSrc);
		} else if (news.link.contains("/juevflix/")) {
			news.content = news.description;
			news.description = "";
		}
		if (!news.description.isEmpty()) {
			news.description = org.jsoup.Jsoup.parse(news.description).text();
		}
		return news;
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		doc.select("script").remove();

		Elements main = doc.select("#main");

		String tag = findSubstringBetween(news_url, "www.eljueves.es/", "/", false);
		Elements article;
		switch (tag) {
			case "news":
			case "opinion":
				// jarticulos/vloggers/articulos
				article = main.select(".fgs-slider .slide[id]");
				if (!article.isEmpty()) {
					article = article.select("img,.txt");
					for (Element img : article.select("img[data-src]"))
						img.attr("src", img.attr("data-src"));
					break;
				}

				article = main.select(".vineta img");
				if (!article.isEmpty())
					break;

				article = main.select(".sub-title,.detailMedia img,#detail");
				article.select("h2").tagName("h4");
				article.select("[style]").removeAttr("style");
				break;
			case "manda-guevos":

				article = main.select("article").get(0).select(".sub-title,.detailMedia,#detail");

				for (Element e : article.select("figure.detalle-img"))
					e.html(e.select("img").outerHtml());

				break;
			case "mmmh":
			case "temazo":
			case "se-esta-hablando":
				// sexo

				article = main.select(".fgs-slider .slide[id] img");

				for (Element img : article.select("img[data-src]"))
					img.attr("src", img.attr("data-src"));

				break;
			case "blogs":

				article = main.select("article").get(0).select("#detail");
				break;
			default:
				return null;
		}

		Elements images = article.select("img");
		for (Element img : images) {
			String src = img.attr(img.hasAttr("data-src") ? "data-src" : "src");

			cleanAttributes(img);

			img.attr("src", src);
		}

		return finalFormat(article, true);
	}

}