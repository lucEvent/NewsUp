package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class TheHeraldScotland extends com.lucevent.newsup.data.util.NewsReader {

	// tags: [description, guid, item, link, media:content, media:description, media:thumbnail, pubdate, title]

	public TheHeraldScotland()
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
	protected void readNewsContent(Document doc, News news)
	{
		Elements article = doc.select("#BlockArticleContent .article-hero,#BlockArticleContent .article-body");
		article.select("script,.clearfix,.advert-container,#related-articles,link").remove();

		article.select(".hero-caption").tagName("figcaption");
		article.select(".article-first-paragraph").tagName("h4");

		Elements fotorama = article.select(".fotorama-wrapper");
		if (!fotorama.isEmpty()) {
			for (Element img : fotorama.select("img.responsive-image")) {
				String caption = img.attr("data-caption");
				cleanAttributes(img, "src");
				img.after(insertCaption(caption));
			}
			for (Element img : fotorama.select("a.responsive-image")) {
				String src = img.attr("martini-mobile-src");
				String caption = img.attr("data-caption");
				cleanAttributes(img);
				img.tagName("img")
						.attr("src", src)
						.after(insertCaption(caption));
			}
			article.select("h2,.fotorama-show-full-screen,.fotorama-hide-full-screen,.fotorama_interstitial").remove();
			cleanAttributes(fotorama);
		}

		for (Element e : article.select("li:has(strong)")) {
			String text = e.text();
			if (text.startsWith("READ MORE") || text.startsWith("Read more"))
				e.parent().remove();
		}

		for (Element f : article.select("figure:has(img)"))
			for (Node n : f.textNodes())
				n.before(insertCaption(n.outerHtml()))
						.remove();

		cleanAttributes(article.select("img[src]"), "src");

		news.content = finalFormat(article, false);
	}

}
