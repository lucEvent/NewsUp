package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Date;
import com.lucevent.newsup.data.util.Enclosure;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class EveningStandard extends com.lucevent.newsup.data.util.NewsReader {

	private static final String INSTAGRAM_EMBED_BEGIN = "<blockquote class='instagram-media' data-instgrm-version='7' style=' background:#FFF; border:0; border-radius:3px; box-shadow:0 0 1px 0 rgba(0,0,0,0.5),0 1px 10px 0 rgba(0,0,0,0.15); margin: 1px; max-width:658px; padding:0; width:99.375%; width:-webkit-calc(100% - 2px); width:calc(100% - 2px);'><div style='padding:8px;'> <div style=' background:#F8F8F8; line-height:0; margin-top:40px; padding:61.853448275862064% 0; text-align:center; width:100%;'> <div style=' background:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACwAAAAsCAMAAAApWqozAAAABGdBTUEAALGPC/xhBQAAAAFzUkdCAK7OHOkAAAAMUExURczMzPf399fX1+bm5mzY9AMAAADiSURBVDjLvZXbEsMgCES5/P8/t9FuRVCRmU73JWlzosgSIIZURCjo/ad+EQJJB4Hv8BFt+IDpQoCx1wjOSBFhh2XssxEIYn3ulI/6MNReE07UIWJEv8UEOWDS88LY97kqyTliJKKtuYBbruAyVh5wOHiXmpi5we58Ek028czwyuQdLKPG1Bkb4NnM+VeAnfHqn1k4+GPT6uGQcvu2h2OVuIf/gWUFyy8OWEpdyZSa3aVCqpVoVvzZZ2VTnn2wU8qzVjDDetO90GSy9mVLqtgYSy231MxrY6I2gGqjrTY0L8fxCxfCBbhWrsYYAAAAAElFTkSuQmCC); display:block; height:44px; margin:0 auto -44px; position:relative; top:-22px; width:44px;'></div></div><p style=' color:#c9c8cd; font-family:Arial,sans-serif; font-size:14px; line-height:17px; margin-bottom:0; margin-top:8px; overflow:hidden; padding:8px 0 7px; text-align:center; text-overflow:ellipsis; white-space:nowrap;'><a href='https://www.instagram.com/p/";

	private static final String INSTAGRAM_EMBED_END = "/' style=' color:#c9c8cd; font-family:Arial,sans-serif; font-size:14px; font-style:normal; font-weight:normal; line-height:17px; text-decoration:none;' target='_blank'>Una publicación compartida de Carles Puigdemont (@carlespuigdemont)</a> el <time style=' font-family:Arial,sans-serif; font-size:14px; line-height:17px;' datetime='2017-11-06T15:40:46+00:00'>6 de Nov de 2017 a la(s) 7:40 PST</time></p></div></blockquote>";

	// tags: [author, dc:creator, dc:date, description, guid, item, link, media:content, media:text, media:thumbnail, pubdate, title]

	public EveningStandard()
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
	protected Enclosure parseEnclosure(Element prop)
	{
		return new Enclosure(prop.attr("url"), "", "");
	}

	@Override
	protected String readNewsContent(Document doc, String news_url)
	{
		Elements article = doc.select(".hero-wrapper-inner,.body-content");
		article.select("script,.ad-wrapper,.inline-readmore,i-amphtml-sizer,.inline-gallery,.inline-related").remove();
		article.select(".video-popout-close-container,#gs-wrapper").remove();

		article.select("amp-img").tagName("img");
		article.select(".persistent-player-headline").tagName("figcaption");

		for (Element gallery : article.select(".hero-gallery")) {
			gallery.tagName("div");
			Elements content = gallery.select("figure");
			gallery.html(content.html());
		}
		for (Element iframe : article.select("amp-iframe")) {
			String height = iframe.attr("height");
			cleanAttributes(iframe, "src");
			iframe.tagName("iframe");
			iframe.attr("frameborder", "0")
					.attr("allowfullscreen", "")
					.attr("height", height);
		}
		for (Element bcvideo : article.select("amp-brightcove")) {
			bcvideo.parent().html(insertIframe(
					"https://players.brightcove.net/"
							+ bcvideo.attr("data-account") + "/"
							+ bcvideo.attr("data-player") + "_"
							+ bcvideo.attr("data-embed") + "/index.html?videoId="
							+ bcvideo.attr("data-video-id")
			));
		}
		for (Element tweet : article.select("amp-twitter")) {
			tweet.tagName("blockquote");
			tweet.attr("class", "twitter-tweet");
		}
		for (Element ytv : article.select("amp-youtube")) {
			ytv.parent().html(insertIframe("https://www.youtube.com/embed/" + ytv.attr("data-videoid")));
		}
		for (Element ig : article.select("amp-instagram")) {
			ig.html(INSTAGRAM_EMBED_BEGIN + ig.attr("data-shortcode") + INSTAGRAM_EMBED_END);
			ig.tagName("div");
			cleanAttributes(ig);
		}
		for (Element fb : article.select("amp-facebook")) {
			try {
				fb.html(insertIframe("https://www.facebook.com/plugins/post.php?href=" + URLEncoder.encode(fb.attr("data-href"), "UTF-8")));
			} catch (UnsupportedEncodingException ignored) {
			}
			fb.tagName("div");
			cleanAttributes(fb);
		}

		for (Element livelist : article.select("amp-live-list")) {
			livelist.select("button,.pagination").remove();

			for (Element etime : article.select("amp-timeago")) {
				Element p = etime.parent();

				String stime = p.parent().parent().attr("data-sort-time");

				p.tagName("p");
				p.removeAttr("href");
				try {
					p.html("<b>" + Date.getAge(Long.parseLong(stime)) + "</b>");
				} catch (NumberFormatException e) {
					p.html("");
				}
			}

			livelist.select(".liveblog-content").tagName("p");
			livelist.tagName("div");
			cleanAttributes(livelist.select(".liveblog-item"));
		}

		Element fp = article.select("p").first();
		if (fp != null)
			fp.html("<b>" + fp.html() + "</b>");

		return finalFormat(article, false);
	}

}
