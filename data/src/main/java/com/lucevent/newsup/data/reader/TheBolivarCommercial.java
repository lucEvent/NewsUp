package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TheBolivarCommercial extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [author, category, content, email, entry, id, link, name, published, summary, title, updated]

	public TheBolivarCommercial()
	{
		super(TAG_ITEM_ENTRY,
				new int[]{TAG_TITLE},
				new int[]{TAG_ID},
				new int[]{},
				new int[]{TAG_CONTENT},
				new int[]{TAG_UPDATED},
				new int[]{TAG_CATEGORY},
				new int[]{},
				"");
	}

	@Override
	protected String parseCategory(Element prop)
	{
		return prop.attr("term");
	}

	@Override
	protected News onNewsRead(News news, Enclosures enclosures)
	{
		Element body = jsoupParse(news.content);
		Elements intro = body.select(".K2FeedIntroText");
		Elements article = body.select(".K2FeedFullText");
		if (article.isEmpty())
			article = intro;

		// Description
		String dscr = intro.text().trim();
		news.description = dscr.length() > 300 ? dscr.substring(0, 300) : dscr;

		// Image
		news.imgSrc = findImageSrc(intro);
		if (news.imgSrc != null)
			news.imgSrc = "http://www.bolivarcommercial.com/" + news.imgSrc;

		// Content
		article.select(".wf_caption span").remove();
		article.select("[style]").removeAttr("style");
		news.content = finalFormat(article, true);
		return news;
	}

}
