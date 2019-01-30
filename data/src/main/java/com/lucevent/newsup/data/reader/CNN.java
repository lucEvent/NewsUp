package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.parser.XmlTreeBuilder;
import org.jsoup.select.Elements;

import java.net.URL;

public class CNN extends com.lucevent.newsup.data.util.NewsReader {

	/**
	 * Tags
	 * [                      description,            guid, item, link, media:content,                  pubdate,         title]
	 * [                      description,            guid, item, link, media:content, media:thumbnail, pubdate,         title]
	 * [category, dc:creator, description, enclosure, guid, item, link, media:content,                  pubdate, source, title]
	 */

	public CNN()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_MEDIA_CONTENT, "media:thumbnail".hashCode()},
				"");
	}

	@Override
	protected String parseTitle(Element prop)
	{
		return prop.text().replace("<![CDATA[", "").replace("]]>", "");
	}

	@Override
	protected String parseDescription(Element prop)
	{
		return org.jsoup.Jsoup.parse(prop.text()).text();
	}

	@Override
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		if (news.link.contains("/podcasting/"))
			news.content = insertIframe(news.link) + "<p>" + news.description + "</p>";

		if (news.imgSrc != null) {
			int index = news.imgSrc.lastIndexOf("-");
			if (index != -1) {
				index = news.imgSrc.lastIndexOf("-", index - 1);

				news.imgSrc = news.imgSrc.substring(0, index) + "-live-video.jpg";
			}
		}
		return news;
	}

	@Override
	protected Document getDocument(String url)
	{
		try {
			return org.jsoup.Jsoup.parse(new URL(url).openStream(), "utf-8", "https://edition.cnn.com/", new Parser(new XmlTreeBuilder()));
		} catch (Exception ignored) {
		}
		return super.getDocument(url);
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article;

		String url = doc.baseUri();
		if (url.contains("/videos/")) {
			article = doc.select(".media__video--thumbnail,.el__video-collection__main-wrapper .media__video-description");
			article.select("script").remove();
			article.select("img").wrap("<p>").removeAttr("alt");
		} else if (url.contains("/gallery/")) {
			article = doc.select(".el-carousel__wrapper noscript,.el-carousel__wrapper .el__gallery_image-title");
			article.select("script").remove();
			article.select("img").wrap("<p>").removeAttr("alt");
		} else if (url.contains("/money.cnn")) {
			article = doc.select("#storytext");
			article.select("script,.video-play,.cnnVidFooter,#storyFooter,.clearfix,.storytimestamp").remove();

			for (Element link : article.select("a")) {
				String text = link.text();
				if (text.startsWith("Related:"))
					link.remove();
			}
		} else {

			article = doc.select("#body-text > .l-container");

			article.select(".el__leafmedia--storyhighlights,.ad,.zn-body__read-more,.el__article--embed,.el__leafmedia--factbox,.js__video--standard,.el-editorial-source,.el__leafmedia--featured-video-collection").remove();
			article.select(".el-editorial-note,.el__gallery--standard,.el__gallery--expandable,.el__video--expandable,.cnn-mapbox").remove();

			for (Element e : article.select(".zn-body__paragraph")) {
				e.tagName("p").removeAttr("class");
			}

			for (Element js : article.select(".js__image--standard:has(script)")) {
				String script = js.select("script").html();

				String caption = findSubstringBetween(script, "\"description\": \"", "\"", false);
				String src = findSubstringBetween(script, "\"url\": \"", "\"", false);
				if (src == null) {
					src = findSubstringBetween(script, "url: '", "'", false);
				}

				js.parent().html(new Enclosure(src, "", "").html() + (caption != null ? "<figcaption>" + caption + "</figcaption>" : ""));
			}
			for (Element emb : article.select(".el__embedded--fullwidth,.el__image--expandable")) {
				Elements ns = emb.select("noscript");
				if (!ns.isEmpty()) {
					emb.html(ns.html());
				}
			}
			for (Element gallery : article.select(".el__gallery--fullstandardwidth")) {
				Elements content = gallery.select("noscript,.el__gallery_image-title");
				content.select("img").wrap("<p>").removeAttr("alt");
				gallery.html(content.html());
			}
			for (Element link : article.select("a")) {
				String text = link.text();
				if (text.startsWith("READ MORE") || text.startsWith("RELATED")
						|| text.startsWith("Related story") || text.startsWith("Read:")
						|| text.startsWith("READ:")) {
					link.remove();
				}
			}
			for (Element video : article.select("video[data-vid-src]")) {
				String src = video.attr("src");
				if (!src.isEmpty()) {
					String poster = video.attr("poster");
					cleanAttributes(video);
					video.attr("src", src).attr("poster", poster).attr("controls", "");
				}
			}
			article.select("script,.el__embedded--standard:not(.el__embedded--standard:has(a,img,iframe)),.el__special--teaseimage,.cn-zoneAdContainer").remove();

			for (Element e : article.select(".media__image--responsive")) {
				String img = e.parent().select("noscript").html();
				e.parent().parent().html(img);
			}
		}
		repairLinks(article, "poster");
		cleanAttributes(article.select("img[src]"), "src");

		return finalFormat(article, false);
	}

}
