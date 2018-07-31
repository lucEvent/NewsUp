package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TheNational extends com.lucevent.newsup.data.util.NewsReader {

	// tags: [description, guid, item, link, pubdate, title]

	public TheNational()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{},
				new int[]{},
				"");
	}

	@Override
	protected void readNewsContent(Document doc, News news)
	{
		Elements article = doc.select(".article-hero,.article-body");
		article.select("script,#subscription-spinner,.advert-container,.clearfix,#related-articles,.fotorama-fullscreen-element").remove();

		article.select(".hero-caption").tagName("figcaption");

		for (Element fr : article.select(".fotorama")) {
			StringBuilder sb = new StringBuilder();
			for (Element img : fr.select(".responsive-image")) {
				String src = img.hasAttr("martini-desktop-src") ? img.attr("martini-desktop-src") : img.attr("href");
				String caption = img.attr("data-caption");
				sb.append("<img src='").append(src).append("'>");
				sb.append("<figcaption>").append(caption).append("</figcaption>");
			}
			fr.parent().html(sb.toString());
			fr.parent().select("h2").remove();
		}

		for (Element img : article.select("img[martini-desktop-src]")) {
			String src = img.attr("martini-desktop-src");
			cleanAttributes(img);
			img.attr("src", src);
		}
		for (Element rm : article.select("a")) {
			String text = rm.text();
			if (text.startsWith("READ MORE"))
				rm.parent().html("");
		}

		news.content = finalFormat(article, false);
	}

}
