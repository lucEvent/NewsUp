package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.parser.XmlTreeBuilder;

public class AndroidAuthority extends com.lucevent.newsup.data.util.NewsReader {

	private static final String SITE_STYLE = "<style>h3 img.alignleft{float:left;margin:10px 15px 10px 0;width:150px;}.aa_custom_button_wrapp{width:50%;padding:8px 20px;margin:" +
			"5px auto;background-color:#55cc3a;border-radius:3px;cursor:pointer;}.aa_custom_button_wrapp a{font-size:14px;text-align:center;color:#fff;text-decoration:none;font" +
			"-weight:bold;text-transform:uppercase;}" +
			".aa_best_app_logo{width:100px;height:100px;border:2px solid #dfe8ea;float:left;margin:0 22px 0 0;}.aa_best_app_header{overflow:hidden;margin-bottom:10px;}.aa_best_" +
			"app_name{margin:0;padding:0;color:#3f3f3f;}.aa_best_app_price{line-height:16px;}.aa_best_app_button{display:inline-block;background:#55cc3a;border-radius:2px;margi" +
			"n:10px 0;overflow:hidden;padding:10px 25px;}.aa_best_app_button a{ text-decoration:none; color:#fff; font-size:14px; font-weight:bold;}" +
			"</style>";

	// tags: [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title, media:content, enclosure]

	public AndroidAuthority()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{TAG_CONTENT_ENCODED},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_MEDIA_CONTENT},
				SITE_STYLE);
	}

	@Override
	protected Enclosure parseEnclosure(Element prop)
	{
		return new Enclosure(prop.attr("url"), "image", "");
	}

	@Override
	protected String parseContent(Element prop)
	{
		Element article = jsoupParse(prop);
		article.select("script,.aa_see_also_block,.clear,.vr_related_articles,.aa_best_app_wrapper > .aa_best_app_button,.aa_newsletter_shortcode_wrapper").remove();

		article.select("[style]").removeAttr("style");
		article.select(".wp-caption-text").tagName("figcaption");
		article.select(".aa_best_app_desc").tagName("p");

		for (Element e : article.select(".aa_best_app_button"))
			e.html(e.select(".overlay-link").html(e.text()).outerHtml());

		for (Element e : article.select(".youtube-player"))
			e.parent().html(insertIframe("https://www.youtube.com/embed/" + e.attr("data-id")));

		for (Element e : article.select("strong")) {
			String text = e.text();
			if (text.startsWith("Read:")
					|| text.startsWith("Read next:"))
				e.parent().remove();
		}

		cleanAttributes(article.select("img"), "src");

		return finalFormat(article, false);
	}

	@Override
	protected Document getDocument(String url)
	{
		try {
			return org.jsoup.Jsoup.connect(url)
					.timeout(10000)
					.parser(new Parser(new XmlTreeBuilder()))
					.userAgent(USER_AGENT)
					.get();
		} catch (Exception ignored) {
		}
		return super.getDocument(url);
	}

}
