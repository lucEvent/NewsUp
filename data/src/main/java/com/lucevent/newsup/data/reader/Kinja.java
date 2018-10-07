package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public abstract class Kinja extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [category, dc:creator, description, guid, item, link, pubdate, title]

	public Kinja()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{},
				new int[]{TAG_DESCRIPTION},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{},
				"");
	}

	@Override
	protected String parseLink(Element prop)
	{
		String l = super.parseLink(prop);
		if (l.startsWith("https://kinjadeals"))
			return "";
		else return l;
	}

	@Override
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		if (!news.content.isEmpty()) {
			Element article = jsoupParse(news.content);
			article.select("script,.js_ad-dynamic,.ad-container").remove();
			article.select(".gif-play,svg,.animationContainer").remove();
			for (Element e : article.select("picture source:not(source[media])")) {
				e.tagName("img").attr("src", e.attr("data-srcset")).removeAttr("data-srcset");
				e.parent().html(e.outerHtml());
			}

			onFormatContent(article);

			news.imgSrc = findImageSrc(article);
			news.content = finalFormat(article, false);
		}
		return news;
	}

	protected void onFormatContent(Element content)
	{
	}

}
