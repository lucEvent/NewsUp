package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.parser.XmlTreeBuilder;
import org.jsoup.select.Elements;

import java.net.URL;

public class Vandal extends com.lucevent.newsup.data.util.NewsReader {

	// tags: [category, description, guid, item, link, pubdate, title]

	public Vandal()
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
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		Element article = jsoupParse(news.content);
		if (!news.link.contains("feedproxy")) {
			article.select("script").remove();

			for (Element video : article.select("a[href^='http://www.vandal.net/video']")) {
				String link = video.attr("href");
				String video_id = link.substring(28, link.indexOf("/", 28));

				cleanAttributes(video);
				video.tagName("video")
						.attr("controls", "")
						.html("<source src='http://videosold.vandalimg.com/mp4/" + video_id + ".mp4' type='video/mp4'>");
			}
			for (Element img : article.select("img[src^='http://media.vandalimg.com/t200']"))
				img.attr("src",
						img.attr("src").replace("http://media.vandalimg.com/t200", "http://mediamaster.vandal.net/m"));

			for (Element e : article.select(".fright"))
				e.tagName("blockquote").removeAttr("style");

			article.select("br").tagName("p");

			news.content = finalFormat(article, false);
		} else {
			news.description = article.text();
			news.content = "";
		}
		news.imgSrc = findImageSrc(article);
		return news;
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		if (news_url.contains("VideosVandalOnline")) {
			Elements video = doc.select("meta[property='og:video']");

			String link = video.first().attr("content");

			String content = insertIframe(link);

			Elements dscr = doc.select("meta[name='description']");
			if (!dscr.isEmpty())
				content += "<p>" + dscr.first().attr("content") + "</p>";

			return content;

		} else if (news_url.contains("BlogsVandalOnline")) {
			Elements e = doc.select(".contenidoprincipal [class='tn mt10']");
			doc.select("[style]").removeAttr("style");
			doc.select("[onclick]").removeAttr("onclick");
			cleanAttributes(e.select("img"), "src");
			return finalFormat(e, true);
		}
		return null;
	}

	@Override
	protected org.jsoup.nodes.Document getDocument(String url)
	{
		try {
			return org.jsoup.Jsoup.parse(new URL(url).openStream(), "ISO-8859-1", url, new Parser(new XmlTreeBuilder()));
		} catch (Exception ignore) {
		}
		return super.getDocument(url);
	}

}
