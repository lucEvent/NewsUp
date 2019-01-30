package com.lucevent.newsup.data.util;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URLEncoder;
import java.util.List;

public abstract class Reader {

	public static final String USER_AGENT = "Mozilla/5.0 (Linux; Android 6.0.1; GT-I9300 Build/MOB30Z) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.91 Mobile Safari/537.36";

	public final String style;

	public Reader(String style)
	{
		this.style = style;
	}

	public abstract NewsArray readRssHeader(String rss_link, int site_code, int section_code);

	public final String readContent(String news_url)
	{
		org.jsoup.nodes.Document doc = getDocument(news_url);
		return doc == null ? null : readNewsContent(doc, news_url);
	}

	protected abstract String readNewsContent(org.jsoup.nodes.Document doc, String news_url);

	protected final org.jsoup.nodes.Element jsoupParse(org.jsoup.nodes.Element prop)
	{
		return org.jsoup.Jsoup.parse(prop.text()).body();
	}

	protected final org.jsoup.nodes.Element jsoupParse(String data)
	{
		return org.jsoup.Jsoup.parse(data).body();
	}

	protected org.jsoup.nodes.Document getDocument(String url)
	{
		try {
			return org.jsoup.Jsoup.connect(url)
					.timeout(10000)
					.userAgent(USER_AGENT)
					.validateTLSCertificates(false)
					.get();
		} catch (Exception e) {
			//    System.out.println("[" + this.getClass().getSimpleName() + " | " + e.getClass().getSimpleName() + "] Can't read page. Trying again");
		}
		try {
			return org.jsoup.Jsoup.connect(url)
					.timeout(10000)
					.validateTLSCertificates(false)
					.get();
		} catch (Exception e) {
			//   System.out.println("[" + this.getClass().getSimpleName() + " | " + e.getClass().getSimpleName() + "] Couldn't read page: " + url);
		}
		return null;
	}

	protected final void cleanAttributes(Elements elems)
	{
		for (Element elem : elems) {
			List<Attribute> attrs = elem.attributes().asList();
			for (Attribute attr : attrs)
				elem.removeAttr(attr.getKey());
		}
	}

	protected final void cleanAttributes(Elements elems, String keep)
	{
		for (Element elem : elems) {
			String atk = elem.attr(keep);

			List<Attribute> attrs = elem.attributes().asList();
			for (Attribute attr : attrs)
				elem.removeAttr(attr.getKey());

			elem.attr(keep, atk);
		}
	}

	protected final void cleanAttributes(Element elem)
	{
		List<Attribute> attrs = elem.attributes().asList();
		for (Attribute attr : attrs)
			elem.removeAttr(attr.getKey());
	}

	protected final void cleanAttributes(Element elem, String keep)
	{
		String atk = elem.attr(keep);

		List<Attribute> attrs = elem.attributes().asList();
		for (Attribute attr : attrs)
			elem.removeAttr(attr.getKey());

		elem.attr(keep, atk);
	}

	private final String cleanComments(String s)
	{
		return s.replaceAll("(?s)<!--.*?-->", "");
	}

	private final void repairLinks(Elements elems)
	{
		for (Element e : elems.select("[src^='//']"))
			e.attr("src", "http:" + e.attr("src"));

		for (Element e : elems.select("[href^='//']"))
			e.attr("href", "http:" + e.attr("href"));
	}

	private final void repairLinks(Element elems)
	{
		for (Element e : elems.select("[src^='//']"))
			e.attr("src", "http:" + e.attr("src"));

		for (Element e : elems.select("[href^='//']"))
			e.attr("href", "http:" + e.attr("href"));
	}

	protected final void repairLinks(Elements elems, String attr)
	{
		for (Element e : elems.select("[" + attr + "^='//']"))
			e.attr(attr, "http:" + e.attr(attr));
	}

	protected final void repairLinks(Element elem, String attr)
	{
		for (Element e : elem.select("[" + attr + "^='//']"))
			e.attr(attr, "http:" + e.attr(attr));
	}

	protected final String insertVideo(String src)
	{
		return "<video controls src='" + src + "'></video>";
	}

	protected final String insertImg(String src)
	{
		return "<img src='" + src + "'>";
	}

	protected final String insertCaption(String caption)
	{
		return "<figcaption>" + caption + "</figcaption>";
	}

	protected final String insertIframe(String src)
	{
		return "<iframe frameborder='0' allowfullscreen src='" + src + "' scrolling='no'></iframe>";
	}

	/**
	 * @param data      String the substring will be looked up
	 * @param start     Starting of the substring
	 * @param end       Ending of the substring
	 * @param inclusive Whether or not include the start and end
	 * @return the substring delimited by start and end (including start and end if inclusive=true), null if not found
	 */
	protected final String findSubstringBetween(String data, String start, String end, boolean inclusive)
	{
		try {
			int istart = data.indexOf(start);
			if (istart == -1)
				return null;
			int iend = data.indexOf(end, istart + start.length()) + (inclusive ? end.length() : 0);
			return data.substring(istart + (inclusive ? 0 : start.length()), iend);
		} catch (Exception ignored) {
			ignored.printStackTrace();
		}
		return null;
	}

	protected final void wpcomwidget(Elements elems)
	{
		for (Element vform : elems.select("form[id]")) {
			StringBuilder sb = new StringBuilder("https://wpcomwidgets.com/?");

			Elements values = vform.select("input");
			sb.append(values.get(0).attr("name")).append("=").append(values.get(0).attr("value").replace(" ", "%20"));
			for (int i = 1; i < values.size(); i++) {
				try {
					Element v = values.get(i);
					sb.append("&").append(v.attr("name")).append("=").append(URLEncoder.encode(v.attr("value"), "UTF-8"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			Element iframe = vform.nextElementSibling();
			cleanAttributes(iframe);
			iframe.attr("src", sb.toString())
					.attr("frameborder", "0")
					.attr("allowfullscreen", "");
		}
	}

	protected final String finalFormat(Element elem, boolean outerHtml)
	{
		elem.select("h1,h2").tagName("h3");
		repairLinks(elem);
		elem.select("a:has(img,iframe,video,figure,picture)").tagName("div");
		elem.select("style,meta,link").remove();
		return cleanComments(outerHtml ? elem.outerHtml() : elem.html());
	}

	protected final String finalFormat(Elements elems, boolean outerHtml)
	{
		elems.select("h1,h2").tagName("h3");
		repairLinks(elems);
		elems.select("a:has(img,iframe,video,figure,picture)").tagName("div");
		elems.select("style,meta,link").remove();
		return cleanComments(outerHtml ? elems.outerHtml() : elems.html());
	}

}
