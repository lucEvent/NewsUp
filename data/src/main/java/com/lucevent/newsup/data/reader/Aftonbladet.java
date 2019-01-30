package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Aftonbladet extends com.lucevent.newsup.data.util.NewsReader {

	// tags: category, description, guid, item, link, pubdate, title

	public Aftonbladet()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{},
				"");
	}

	@Override
	protected String parseLink(Element prop)
	{
		String link = super.parseLink(prop);

		if (link.startsWith("/"))
			link = "http://www.aftonbladet.se" + link;

		return link;
	}

	@Override
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(news.description);
		news.description = doc.text();
		news.imgSrc = findImageSrc(doc);

		if (news.link.contains("/abtv/")) {
			String[] p = news.link.split("/");
			String vidId = p[p.length - 1];
			news.content = insertIframe("https://tv.aftonbladet.se/abtv/articles/" + vidId + "?service=embedded&autoplay=false");
		}

		return news;
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select("main > div");
		article.select("[data-component-key='streamer'],[data-test-id='headline'],[data-test-id='label'],[id^='abAdArea']").remove();
		article.select("._3u81U,._11S-G,._1pdIt,._7gDWL,._2XS3K,.abThemeBorder,._1V3Dg,[data-test-id='inline-teaser'],button").remove();
		//
		article.select("._3oEVrB,._12nap,._3oEVr").tagName("h3");
		article.select("._1hqNA").tagName("figcaption");
		article.select("._3tWGC").tagName("blockquote");
		//
		for (Element e : article.select(".c-Cz1")) {
			cleanAttributes(e);
			e.html("<strong>" + e.html() + "</strong>");
		}
		for (Element e : article.select("svg:has(text)")) {
			e.tagName("strong");
			e.html(e.text());
			cleanAttributes(e);
		}
		// Pictures
		for (Element e : article.select("picture source")) {
			String src = e.attr("srcset");
			src = src.substring(0, src.indexOf(" "));

			e = e.parent();
			e.tagName("img");
			e.attr("src", src);
			e.html("");
		}
		for (Element e : article.select("[data-test-id='image']")) {
			e.html(e.select("img,figcaption").outerHtml());
		}
		// Gallery
		for (Element e : article.select("._1nGyY")) {
			e.html(e.select("._2XM84,img").outerHtml());
		}
		// end gallery

		// Videos
		String vidScript = null;
		for (Element candidate : doc.select("script")) {
			String script = candidate.html();
			if (script.startsWith("window.FLUX_STATE")) {
				vidScript = script;
				break;
			}
		}
		if (vidScript != null) {
			for (Element e : article.select("._1W-u7,._2XM84")) {
				Elements aux = e.select("[id^='meetricsId-']");
				if (!aux.isEmpty()) {
					String vidId = aux.get(0).id().replace("meetricsId-", "");
					if (!vidId.isEmpty()) {
						int i = vidScript.indexOf("\"id\":\"" + vidId);
						if (i > 0) {
							String src = findSubstringBetween(vidScript.substring(i), "\"mp4\":\"", "\"", false);
							if (src != null && !src.isEmpty()) {
								src = src.replace("\\u002F", "/");
								e.html(insertIframe(src));
								continue;
							}
						}
					}
				}
				e.html("");
			}
		} else
			article.select("._1W-u7").remove();
		// end videos

		return finalFormat(article, false);
	}

}