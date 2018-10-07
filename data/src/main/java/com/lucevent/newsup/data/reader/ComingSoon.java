package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ComingSoon extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

	public ComingSoon()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{TAG_CONTENT_ENCODED},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{},
				"");
	}

	@Override
	protected String parseDescription(Element prop)
	{
		return jsoupParse(prop).select("p").first().text();
	}

	@Override
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		if (!news.content.isEmpty()) {
			Element article = jsoupParse(news.content);

			for (Element slideshow : article.select(".pbslideshow-wrapper")) {
				StringBuilder res = new StringBuilder();
				for (Element img : slideshow.select("img")) {
					res.append("<figure>")
							.append(insertImg(img.attr("src")))
							.append("<figcaption>").append(img.attr("alt")).append("<figcaption>")
							.append("</figure>");

				}
				slideshow.html(res.toString());
			}
			for (Element rel : article.select("a:has(strong,b),strong:has(a)")) {
				String text = rel.text();
				if (text.startsWith("RELATED"))
					rel.remove();
			}
			article.select("h2:has(img)").tagName("p");
			article.select(".caption").tagName("figcaption");
			article.select("script").remove();

			Elements titles = article.select("h2");
			if (!titles.isEmpty()) {
				titles.first().remove();
			}

			cleanAttributes(article.select("img"), "src");

			news.imgSrc = findImageSrc(article);
			news.content = finalFormat(article, false);
		}
		return news;
	}

}
