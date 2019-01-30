package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class As extends com.lucevent.newsup.data.util.NewsReader {

	/**
	 * Tags:
	 * [category, comments, content:encoded, dc:creator, description, enclosure, guid, item, link, pubdate, title]
	 * [dc:creator, description, enclosure, guid, item, link, pubdate, title]
	 * [dc:creator, description, guid, item, link, pubdate, title]
	 */

	public As()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{TAG_CONTENT_ENCODED},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_ENCLOSURE},
				"");
	}

	@Override
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		if (!news.content.isEmpty()) {
			String media = "";
			boolean imgSet = false;
			for (Enclosure e : enclosures) {
				if (!imgSet && e.isImage() && e.size > 10000) {
					media = e.html();
					imgSet = true;
				} else if (e.isVideo()) {
					media = e.html();
					break;
				}
			}

			Element article = jsoupParse(news.content);
			news.content = media + finalFormat(article, false);
		} else if (news.link.contains("video")) {

			for (Enclosure enclosure : enclosures)
				if (enclosure.isVideo()) {
					news.content = enclosure.html() + news.description;
					break;
				}
		}
		return news;
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article;
		if (news_url.contains("video")) {
			article = doc.select(".item-multimedia script");
			if (!article.isEmpty()) {
				String content = article.html();
				int vStart = content.indexOf("var urlVideo");
				if (vStart != -1) {

					int vEnd = content.indexOf(".mp4", vStart);
					String videoURL = content.substring(vStart + 39, vEnd + 4);

					return insertIframe("http://as.com" + videoURL);
				}
			}
		}

		article = doc.select("[itemprop='articleBody']");
		if (!article.isEmpty()) {

			article.select("script,section,.noticias-rel,.cont-art-tags,.comentarios,.no-visible").remove();
			article.select(".escudo-equipo img").attr("style", "width:10%");

			return finalFormat(article, true);

		} else {
			article = doc.select("#contenedorfotos");
			if (!article.isEmpty()) {
				article = article.select("[itemprop='contentURL'],[itemprop='headline']");
				for (Element img : article.select("[itemprop='contentURL']")) {
					img.tagName("img");

					String src = img.attr("content");
					cleanAttributes(img);
					img.attr("src", src);
				}
				article.select("[itemprop='headline']").tagName("p").select("h2,span").remove();

				return finalFormat(article, true);
			} else {
				article = doc.select("#contenido-interior > p,.entry-content > p,.floatFix > p,.floatFix > figure");
				if (article.isEmpty()) {

					article = doc.select("#columna2");
					if (article.isEmpty()) {
						article = doc.select(".marcador-generico,.cmt-live");
						if (article.isEmpty()) {

							article = doc.select(".post");
							if (article.isEmpty()) {
								// No content found
								return null;
							} else {
								article.select(".post-info,.post-ftr,#comments,.comments,.redes,#comment-form,h2,a[rel='prev'],a[rel='next'],a[rel='author'],script,aside").remove();
							}
						}
					} else {
						article.select(".redes,.menu_post,.archivado,script").remove();
					}
				}
				article.select("noscript,.social-bar,.entry-footer").remove();
				article.select("[class]").removeAttr("class");

				return finalFormat(article, false);
			}
		}
	}

}
