package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Expressen extends com.lucevent.newsup.data.util.NewsReader {

	// tags: [author, description, guid, item, link, pubdate, title]

	public Expressen()
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
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		if (!news.description.isEmpty()) {
			Element e = jsoupParse(news.description);
			news.description = e.text();
			news.imgSrc = findImageSrc(e);
			if (news.imgSrc != null)
				news.imgSrc = news.imgSrc.replace("x6/265@70.jpg", "x9/632@60.jpg");
		}
		return news;
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements widgets, preamble, article;

		article = doc.select(".b-article__body");
		if (!article.isEmpty()) {

			widgets = doc.select(".b-article__top-widgets figure img:not(noscript figure img,.b-slideshow__item--video img),.b-article__top-widgets iframe");
			preamble = doc.select(".b-article__preamble");

		} else {

			widgets = doc.select(".tv-widget-container iframe,.slideshow__wrapper");
			preamble = doc.select(".text--article-preamble");
			article = doc.select(".text--article-body");

		}

		article.select("img[src$='.svg']").remove();
		cleanSources(widgets);
		cleanSources(article);

		for (Element mer : article.select("strong,a")) {
			String text = mer.text();
			if (text.startsWith("L\u00C4S MER") || text.startsWith("L\u00E4s mer")
					|| text.startsWith("L\u00C4S OCKS\u00C5") || text.startsWith("L\u00C4S \u00C4VEN")) {
				try {
					mer.parent().remove();
				} catch (Exception notParentFound) {
					mer.remove();
				}
			}
		}

		article.select(".b-photo__description-wrap,script").remove();
		article.select(".factbox").tagName("blockquote");
		article.select(".b-photo__description,.image__caption").tagName("figcaption");
		widgets.select(".slideshow__caption-container").tagName("figcaption");

		widgets.select("[style]").removeAttr("style");
		article.select("[style]").removeAttr("style");

		return finalFormat(widgets, true)
				+ "<b>" + finalFormat(preamble, false) + "</b><br>"
				+ finalFormat(article, false).replace("<p>&nbsp;</p>", "");
	}

	private void cleanSources(Elements elems)
	{
		for (Element img : elems.select("img")) {
			String srcTag = "src";
			String srcValue;

			if (img.hasAttr("srcset")) {
				srcTag = "srcset";
				srcValue = img.attr("srcset").replace("/1x1/", "/4x3/");
			} else if (img.hasAttr("data-srcset")) {
				srcTag = "srcset";
				srcValue = img.attr("data-srcset").replace("/1x1/", "/4x3/");
			} else if (img.hasAttr("data-src")) {
				srcTag = "src";
				srcValue = img.attr("data-src").replace("_format_", "16x9").replace("_width_", "600").replace("_quality_", "90");
			} else {
				srcValue = img.attr("src");
			}

			cleanAttributes(img);
			img.attr(srcTag, srcValue);
		}
		for (Element iframe : elems.select("iframe[data-src]"))
			iframe.attr("src", iframe.attr("data-src")).removeAttr("data-src");
	}

}
