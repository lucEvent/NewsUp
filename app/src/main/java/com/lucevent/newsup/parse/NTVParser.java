package com.lucevent.newsup.parse;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.data.util.Site;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.util.ArrayList;
import java.util.List;

public class NTVParser {

	private Site mSite;

	public NTVParser()
	{
	}

	public NewsElements parse(String newsContent, Site site)
	{
		mSite = site;

		Document d = org.jsoup.Jsoup.parse(newsContent);
		for (Element e : d.select("[src^='/']"))
			e.attr("src", site.url + e.attr("src"));
		for (Element e : d.select("[href^='/']"))
			e.attr("href", site.url + e.attr("href"));

		NewsElements res = new NewsElements();
		try {
			roll(res, d.body().childNodes(), new NewsParagraph());
		} catch (Exception ex) {
			AppSettings.printerror("Error parsing news", ex);
		}
		return res;
	}

	private void roll(List<NewsElement> res, List<Node> elements, NewsElement currentElement)
	{
		NewsElement ne;

		for (Node e : elements) {
			String tag = e.nodeName();

			switch (tag) {
				case "blockquote":
					switch (e.attr("class")) {
						case "twitter-tweet":
							if (!res.isEmpty() && res.get(res.size() - 1) instanceof NewsTweet) {
								NewsTweet tweet = (NewsTweet) res.get(res.size() - 1);
								tweet.setContent(tweet.getContent() + e.outerHtml());
							} else {
								ne = new NewsTweet();
								ne.setContent(e.outerHtml());
								res.add(ne);
							}
							break;
						case "instagram-media":
							if (!res.isEmpty() && res.get(res.size() - 1) instanceof NewsInstagram) {
								NewsInstagram insta = (NewsInstagram) res.get(res.size() - 1);
								insta.setContent(insta.getContent() + e.outerHtml());
							} else {
								ne = new NewsInstagram();
								ne.setContent(e.outerHtml());
								res.add(ne);
							}
							break;
						default:
							endBlock(res);
							NewsBlockquote bq = new NewsBlockquote();
							roll(bq, e.childNodes(), new NewsParagraph());
							endBlock(bq);
							res.add(bq);
							break;
					}
					continue;
				case "iframe":
					ne = new NewsIframe();
					ne.setContent(e.attr("src"));
					res.add(ne);
					continue;
				case "video":
					ne = new NewsVideo();
					ne.setContent(e.outerHtml());
					res.add(ne);
					continue;
				case "audio":
					ne = new NewsAudio();
					ne.setContent(e.outerHtml());
					res.add(ne);
					continue;
				case "aside":
				case "header":
				case "section":
					endBlock(res);
				case "div":
				case "center":
					if (currentElement == null) {
						currentElement = new NewsParagraph();
					}
					roll(res, e.childNodes(), currentElement);
					continue;
				case "dl":
					endBlock(res);
					for (Node i : e.childNodes()) {
						if (i.nodeName().equals("dt"))
							roll(res, i.childNodes(), new NewsDLTitle());
						else if (i.nodeName().equals("dd"))
							roll(res, i.childNodes(), new NewsDLDescription());
						endBlock(res);
					}
					continue;
				case "h1":
				case "h2":
				case "h3":
					// Title/Subtitle
					endBlock(res);
					roll(res, e.childNodes(), new NewsSubtitle());
					endBlock(res);
					continue;
				case "img":
					NewsImage ni = new NewsImage();
					String src = e.attr(e.hasAttr("src") ? "src" : "srcset");
					ni.setContent(src);
					res.add(ni);
					continue;
				case "picture":
					for (int ch = e.childNodeSize() - 1; ch >= 0; ch--) {
						Node childNode = e.childNode(ch);
						src = childNode.attr("src");
						if (src.isEmpty()) {
							src = childNode.attr("srcset");

							if (src.isEmpty())
								continue;

							src = src.split(" ")[0];
						}

						NewsImage np = new NewsImage();
						np.setContent(src);
						res.add(np);
						break;
					}
					continue;
				case "figcaption":
					ne = new NewsCaption();
					ne.setContent(((Element) e).html());
					res.add(ne);
					continue;
				case "table":
					NewsTable nt = new NewsTable();
					Element elem = (Element) e;
					for (Element tr : elem.select("tr")) {
						ArrayList<String> row = new ArrayList<>();
						for (Element td : tr.select("th,td"))
							row.add(td.html());

						nt.addRow(row);
					}
					res.add(nt);
					continue;
				case "p":
				case "article":
				case "main":
				case "amp-live-list":
				case "footer":
					endBlock(res);
					roll(res, e.childNodes(), new NewsParagraph());
					endBlock(res);
					continue;
				case "span":
					roll(res, e.childNodes(), currentElement);
					continue;
				case "font":
				case "a":
				case "em":
				case "strong":
				case "br":
				case "sub":
				case "b":
				case "i":
				case "h4":
				case "h5":
				case "h6":
				case "s":
				case "del":
				case "sup":
				case "u":
				case "hr":
				case "time":
				case "small":
				case "code":
				case "address":
				case "pre":
				case "cite":
				case "tt":
				case "abbr":
				case "svg":
				case "r":
					if (!res.isEmpty() && res.get(res.size() - 1).isAppendable()) {
						ne = res.get(res.size() - 1);
						String c = ((NewsElement<String>) ne).getContent();
						((NewsElement<String>) ne).setContent(c + e.outerHtml());
					} else {
						ne = currentElement.getNewElement();
						ne.setContent(e.outerHtml());
						res.add(ne);
					}
					continue;
				case "ul":
				case "ol":
					endBlock(res);
					for (Node i : e.childNodes())
						if (i.nodeName().equals("li")) {
							roll(res, i.childNodes(), new NewsListItem());
							endBlock(res);
						}
					continue;
				case "#text":
					String htmlContent = e.outerHtml();
					if (htmlContent.trim().isEmpty()) {
						continue;
					}

					if (!res.isEmpty() && res.get(res.size() - 1).isAppendable()) {
						ne = res.get(res.size() - 1);
						String c = ((NewsElement<String>) ne).getContent();
						((NewsElement<String>) ne).setContent(c + e.outerHtml());
					} else if (currentElement != null) {
						ne = currentElement.getNewElement();
						ne.setContent(e.outerHtml());
						res.add(ne);
					} else {
						AppSettings.printlog("currentElement==null");
					}
					continue;
					// tags treated but not necessary
				case "li":
					endBlock(res);
					roll(res, e.childNodes(), new NewsListItem());
					endBlock(res);
					continue;
				case "figure":
					endBlock(res);
					roll(res, e.childNodes(), new NewsParagraph());
					endBlock(res);
					continue;
				case "nuwidget":
					ne = new NewsNUWidget();
					ne.setContent(mSite.getStyle() + e.outerHtml());
					res.add(ne);
					continue;
				case "#comment":
				case "noscript":
				case "object":
				case "script":
					// ignore this
					continue;
				default:
					AppSettings.printlog("Tag not treated :( ->[" + tag + "]");
			}
			roll(res, e.childNodes(), null);
		}
	}

	private static void endBlock(List<NewsElement> res)
	{
		if (!res.isEmpty())
			res.get(res.size() - 1).setNotAppendable();
	}

}
