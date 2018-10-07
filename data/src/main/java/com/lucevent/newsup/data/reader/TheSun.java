package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public class TheSun extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [category, content:encoded, dc:creator, description, guid, item, link, post-id, pubdate, title]

	public TheSun()
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
		return super.parseDescription(prop).replace(" [&#8230;]", "...");
	}

	@Override
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		if (!news.content.isEmpty()) {
			Element article = jsoupParse(news.content);
			article.getElementsByTag("fb:post").remove();
			article.select("script,.incon_widgets,.sun-grid-container,opta-widget,[rel='noopener'],.article-boxout").remove();

			article.select("figure[style]").removeAttr("style");
			article.select("figcredit").tagName("figcaption");
			article.select("div:has(video)").removeAttr("style");

			for (Element bcvideo : article.select("video[data-video-id]")) {
				bcvideo.parent()
						.html(insertIframe(
								"https://players.brightcove.net/"
										+ bcvideo.attr("data-account") + "/"
										+ bcvideo.attr("data-player") + "_"
										+ bcvideo.attr("data-embed") + "/index.html?videoId="
										+ bcvideo.attr("data-video-id")
						));
			}

			news.content = finalFormat(article, false);
			news.imgSrc = findImageSrc(article);
		}
		return news;
	}

}
