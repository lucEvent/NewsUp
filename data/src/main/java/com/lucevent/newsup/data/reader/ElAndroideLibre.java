package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ElAndroideLibre extends com.lucevent.newsup.data.util.NewsReader {

	private static final String SITE_STYLE =
			"<style>.APP{width:100%;padding:5px 0;margin:auto;border:1px solid #bfbfbf;background:#ffffff;position:relative;font-family:'Roboto', Arial, " +
					"sans-serif;margin-bottom:20px;color:#191a1e;display:block;font-size:17px;line-height:29px;}.APP img{width:74px;padding:5px;display:i" +
					"nline;height:auto;}.APP .id-app-title{padding:10px 20px;vertical-align:top;color:#191a1e;display:inline-block;font-weight:bold;font-" +
					"size:21px;}.APPinstalarMobile{padding:10px;display:block;vertical-align:top;text-decoration:none;}.APPinstalarBoton{text-align:cente" +
					"r;line-height:30px;color:#ffffff;font-size:16px;text-transform:uppercase;background-size:22px 22px;background-position:left center;b" +
					"ackground-repeat:no-repeat;background-position-x:4px;text-indent:22px;}.APPinstalarBotonGP{background-image:url(https://static.eland" +
					"roidelibre.com/styleV3/googleplay.png); background-color:#6ab344;}.APPinstalarBotonUTD{background-color:#227dc6}.APPinstalarBotonAma" +
					"zon{background-color:#f69b06}" +
					".zio {padding:5px 0;width:80 %;text-align:center;border:1px solid rgb(212, 212, 196);border-radius:15px;margin:10px auto;font-weight" +
					":bold;}.zio>img {width:30%;}.ziotitle {margin:15px;}.high light {color:rgb(0, 150, 136);line-height:35px;}.apibutton {width:60%;marg" +
					"in:5px auto;border-radius:30px;padding:2%;background-color:rgb(0, 150, 136);color:white;}.zio a {text-decoration:none;}</style>";

	// tags: [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

	public ElAndroideLibre()
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
		String dscr = org.jsoup.Jsoup.parse(prop.text()).text();
		int index = dscr.indexOf("[…]");
		if (index != -1)
			dscr = dscr.substring(0, index);

		return dscr;
	}

	@Override
	protected String readNewsContent(Document doc, String news_url)
	{
		Elements article = doc.select("#singlePostHeaderImgBox,.article-body__content");
		article.select("script,a:has(.blockquoteLink),.zioamz,.blockquoteRelated").remove();
		article.select("#noddus2,#admansurf,#sunmedia,.singlePostShare,#singlePostRelated,#instagram,#singlePostAdvertising").remove();
		article.select("zio").tagName("nuwidget");

		for (Element app : article.select(".APP")) {
			app.tagName("nuwidget");
			app.html(app.select(".APPimagen img,.APPnombre a,.APPinstalarMobile").outerHtml());
		}

		cleanAttributes(article.select("p[style]"));
		cleanAttributes(article.select("img[src]"), "src");
		article.select("img+em").tagName("figcaption");

		return finalFormat(article, false);
	}

}
