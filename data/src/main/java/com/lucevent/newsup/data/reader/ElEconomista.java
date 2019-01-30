package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ElEconomista extends com.lucevent.newsup.data.util.NewsReader {

	//tags: [description, guid, item, link, pubdate, title]

	public ElEconomista()
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
	protected String parseLink(Element prop)
	{
		String l = super.parseLink(prop);
		return l.startsWith("//") ? "https:" + l : l;
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article;
		if (news_url.contains("/ecomotor/")) {
			article = doc.select(".int-noti article");
		} else {

			article = doc.select(".articleHero_wrapper,.Article__paragraphGroup");
			if (article.isEmpty()) {
				article = doc.select(".noticia-foto,#noticia-cuerpo");
				if (article.isEmpty()) {
					article = doc.select("#cuerpo_noticia");

					if (article.isEmpty()) {
						article = doc.select("#cuerpo-noticia");

						if (article.isEmpty()) {
							// TODO: 27/10/2018
							return null;
						}
					}
				}
				article.select("h1,.firma,.social-share,.p-tag,.xtb-link,.info-box,.large-collapse").remove();
			}
		}
		article.select(".info-noti,.s-horizontal,.wrap-rela,h1,h5,#1x4,#1x5,#1x6,[width='1']").remove();
		article.select("script,.no-gutters,[class*='pub_mob'],.read_More,.video-player,.relacionadas").remove();

		for (Element img : article.select("img[data-srcset]")) {
			String[] srcset = img.absUrl("data-srcset").split(" ");
			cleanAttributes(img);
			img.attr("src", srcset[srcset.length - 2]);
		}
		cleanAttributes(article.select("figure,[onclick]"));
		cleanAttributes(article.select("img[itemtype]"), "src");

		return finalFormat(article, true);
	}

}
