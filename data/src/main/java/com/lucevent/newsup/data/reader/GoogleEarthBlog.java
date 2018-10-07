package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

public class GoogleEarthBlog extends com.lucevent.newsup.data.util.NewsReader {

	private static final String SITE_STYLE = "<style>img[src$='mac-cmdkey.gif'],img[src$='gelogoicon.gif'],img[src*='/icons/']{width:4%;}code{display:block;padding: 5px 0;wid" +
			"th:100%;overflow:scroll;font-size:16px;font-family:courier;}code:before{content:'Slide left to see more';color:#777777;display:block;padding:12px 0 5px;}</style>";

	//tags: [category, content:encoded, dc:creator, description, guid, item, link, post-id, pubdate, title]

	public GoogleEarthBlog()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{TAG_CONTENT_ENCODED},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{},
				SITE_STYLE);
	}

	@Override
	protected String parseDescription(Element prop)
	{
		String description = org.jsoup.Jsoup.parse(prop.text()).text();

		int index = description.indexOf(" […]");
		if (index > 0)
			description = description.substring(0, index) + "...";

		return description;
	}

	@Override
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		if (!news.content.isEmpty()) {
			Element article = jsoupParse(news.content);
			article.select("script,link,label,input").remove();

			article.select("img+br+i").tagName("figcaption");
			article.select("strike").tagName("s");
			article.select("[style]").removeAttr("style");
			article.select("[width]").removeAttr("width");
			article.select("iframe").attr("frameborder", "0");

			for (Element img : article.select("img")) {
				String src = img.attr("src");
				img.attr("src", src.replace("resize=150%2C150", "resize=676%2C530"));
				if (src.endsWith("mac-cmdkey.gif") && src.endsWith("gelogoicon.gif") && src.contains("/icons/"))
					img.wrap("<p>");
			}

			news.imgSrc = findImageSrc(article);
			news.content = finalFormat(article, false);
		}
		return news;
	}

	@Override
	protected org.jsoup.nodes.Document getDocument(String url)
	{
		try {
			return org.jsoup.Jsoup.connect(url)
					.parser(Parser.xmlParser())
					.userAgent(USER_AGENT)
					.get();
		} catch (Exception ignored) {
		}
		return super.getDocument(url);
	}

}
