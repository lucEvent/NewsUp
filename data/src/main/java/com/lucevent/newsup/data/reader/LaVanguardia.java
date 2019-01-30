package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LaVanguardia extends com.lucevent.newsup.data.util.NewsReader {

	private static final String BOOKS_STYLE = "<style>.html-books-h2{background-color:#fff;border-top:3px solid #aab4bf;color:#3e4d67;padding:12px;font-size:18px;}.text-center{" +
			"text-align:center;color:#000;margin-bottom:20px;font-size:18px;line-height:26px;}.html-books-article{margin-bottom:10px;overflow:hidden;padding-bottom:10px;backgro" +
			"und-color:#f3f3f3;} .html-books-number{background-color:#3e4d67;padding:5px 20px;margin:0 0 2px 0;display:block;font-size:1.625rem;color:#fff;}.html-book-item{marg" +
			"in:0 -8px 0;font-size:.625rem;background-color:#eaeaea;color:#3e4d67;font-family:Arial,Helvetica;padding:5px;line-height:1.625rem;text-align:center;}.html-book-bac" +
			"kground{background-color:#f3f3f3;padding:5px 15px ;border-left:3px solid #fff;}.html-books .image{margin:1px auto;display:block;width:83px;}.html-books-titlebook{c" +
			"olor:#3e4d67;font-size:.938rem;line-height:1.125rem;}.html-books-authorbook,.html-books-publisherbook{color:#3e4d67;font-size:14px;line-height:16px;margin-bottom:2" +
			"0px;}.html-books-description{color:#3e4d67;font-size:14px;line-height:16px;}.html-books-ul{color:#3e4d67;margin:20px 0;font-size:14px;line-height:16px;padding-left" +
			":15px;}.col-xs-2,.col-xs-3{width:20%;float:left;}.col-xs-4{width:35%;float:left;}.col-xs-7{width:80%;float:left;}.col-xs-8{width:65%;float:left; }.col-xs-12{width:" +
			"100%;display:block;}</style>";

	// tags: [category, description, enclosure, guid, item, link, pubdate, title]

	public LaVanguardia()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_ENCLOSURE},
				BOOKS_STYLE);
	}

	@Override
	protected String parseDescription(Element prop)
	{
		String description = org.jsoup.Jsoup.parse(prop.text()).text();
		int idash = description.indexOf("- ");
		if (idash != -1)
			description = description.substring(idash + 2);
		return description;
	}

	@Override
	protected Enclosure parseEnclosure(Element prop)
	{
		return new Enclosure(prop.attr("url").replace("a/thumbnail", ""), prop.attr("type"), prop.attr("length"));
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select("#single-player,.story-leaf-body-video:not([itemprop='articleBody'] .story-leaf-body-video),[itemprop='articleBody']");

		if (article.isEmpty()) {
			return null;
		} else if (!article.select(".multimedia-leaf-txt").isEmpty()) {
			Elements imgs = doc.select("#multimedia-gallery-leaf img");
			Elements txts = doc.select(".multimedia-leaf-txt p");
			Elements gallery = new Elements();
			for (int i = 0; i < imgs.size(); i++) {
				Element img = imgs.get(i);
				String src = img.attr("data-src");
				cleanAttributes(img);
				img.attr("src", src);

				gallery.add(imgs.get(i));
				gallery.add(txts.get(i));
			}
			return finalFormat(gallery, true);
		}
		article.select("script,.tpl-related-inside-story,.hidden,.button-login-premium,ins,.html-books-header,.poll-leaf-body-header,.poll-leaf-body").remove();

		article.select("question").tagName("h4");
		article.select("answer").tagName("p");
		article.select("ntrans").tagName("div");
		article.select(".wp-caption-text").tagName("figcaption");
		article.select("[style]").removeAttr("style");
		article.select("iframe").attr("frameborder", "0");
		article.select(".html-books").tagName("nuwidget");

		for (Element e : article.select(".story-leaf-despiece-quotes"))
			e.tagName("blockquote")
					.removeAttr("class");

		for (Element e : article.select("[data-mediaset-src]")) {
			String src = e.attr("data-mediaset-src");
			cleanAttributes(e);
			e.attr("src", src);
		}

		for (Element e : article.select(".story-leaf-despiece"))
			e.html(e.text())
					.tagName("h3")
					.removeAttr("class");

		for (Element e : article.select("blockquote.twitter-tweet > a[href^='<'],blockquote.instagram-media a[href^='<']")) {
			String html = e.attr("href");

			int i = html.indexOf("<script");
			if (i > 0)
				html = html.substring(0, i);

			Element parent = e.parent();
			while (!parent.tagName().equals("blockquote"))
				parent = parent.parent();

			parent.parent().html(html);
		}

		cleanAttributes(article.select("img"), "src");

		return finalFormat(article, true);
	}

}
