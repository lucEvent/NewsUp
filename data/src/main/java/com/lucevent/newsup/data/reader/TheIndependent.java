package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TheIndependent extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [author, dc:creator, dc:date, description, guid, item, link, media:content, media:text, media:thumbnail, pubdate, title]

	public TheIndependent()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{},
				new int[]{TAG_PUBDATE},
				new int[]{},
				new int[]{TAG_MEDIA_CONTENT},
				"");
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select(".sub-headline,.hero-wrapper-inner,.body-content");
		article.select("script,.ad-wrapper,i-amphtml-sizer,.i-gallery,.persistent-player-headline,.video-popout-close-container,.inline-readmore,.inline-related,button").remove();

		article.select(".sub-headline").tagName("strong");
		article.select("amp-iframe").tagName("iframe");
		article.select("amp-img").tagName("img");
		cleanAttributes(article.select("img[src]"), "src");

		for (Element e : article.select("amp-brightcove")) {
			e.parent().html(insertIframe(
					"https://players.brightcove.net/" + e.attr("data-account")
							+ "/" + e.attr("data-player")
							+ "_" + e.attr("data-embed")
							+ "/index.html?videoId=" + e.attr("data-video-id"))
			);
		}

		article.select("iframe").html("");

		return finalFormat(article, true);
	}

}
