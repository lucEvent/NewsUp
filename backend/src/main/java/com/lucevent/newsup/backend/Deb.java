package com.lucevent.newsup.backend;

import com.lucevent.newsup.data.Sites;

import org.jsoup.Connection;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class Deb {

	public static void main(String[] args)
	{
		int start = 0;
		int n = 10;
		Sites sites = Sites.getDefault(true);
		Deb deb = new Deb();

		for (int i = start; i < start + n; i++) {
			deb.search(sites.get(i).url);
		}
	}

	//  SiteSearchEngine

	public void search(String request)
	{
		Elements dd;
		try {
			dd = getPage("http://www.google.com/search?start=0&num=4&q=" + request.replace(" ", "+")).select("cite");
		} catch (Exception e) {
			System.out.println("# # # # # #");
			System.out.println("#");
			System.out.println("# Result: ERROR_IN_GOOGLE_SEARCH");
			System.out.println("#");
			System.out.println("# Data: " + e.getMessage());
			System.out.println("#");
			System.out.println("# # # # # #");
			return;
		}

		if (!dd.isEmpty()) {
			request = dd.first().text();
		} else {
			request = request.toLowerCase().replace(" ", "");
			if (!request.contains("."))
				request = request + ".com";
		}
		if (request.endsWith("/"))
			request = request.substring(0, request.length() - 1);

		//  System.out.println("RS:" + request);

		// First try HTTP
		String site_url = request;
		if (!request.startsWith("http"))
			site_url = "http://" + site_url;

		Document d;
		try {
			d = getPage(site_url);
		} catch (Exception e) {
			System.out.println("# # # # # #");
			System.out.println("#");
			System.out.println("# Result: ERROR_GETTING_PAGE");
			System.out.println("#");
			System.out.println("# Error: " + e.getMessage());
			System.out.println("#");
			System.out.println("# # # # # #");
			return;
		}
/*
		System.out.println("# M E T A S #");
		for (Element e : d.select("meta")) {
			System.out.println("# ");
			for (Attribute a : e.attributes())
				System.out.println("# " + a.getKey() + " = " + a.getValue());
		}
*/
		// extract info
		String rss_url = extractRssUrl(d);
		if (rss_url == null || rss_url.isEmpty()) {
			System.out.println("# # # # # #");
			System.out.println("#");
			System.out.println("# Result: ERROR_RSS_NOT_FOUND");
			System.out.println("#");
			System.out.println("# site_url: " + site_url);
			System.out.println("#");
			System.out.println("# # # # # #");
			return;
		}
		String icon = extractIcon(d);
		int color = extractColor(d);
		String name = extractName(d, request);


		System.out.println("# # # # # #");
		System.out.println("#");
		System.out.println("# Result OK");
		System.out.println("#");
		System.out.println("# Code: " + 10000 + (Math.abs(site_url.hashCode()) % 10000));
		System.out.println("# Name: " + name);
		System.out.println("# Url: " + site_url);
		System.out.println("# Rss: " + (rss_url.startsWith("/") ? site_url + rss_url : rss_url));
		System.out.println("# Icon: " + (icon.startsWith("/") ? site_url + icon : icon));
		System.out.println("# Color: " + color);
		System.out.println("#");
		System.out.println("# # # # # #");
	}

	private static Document getPage(String url) throws Exception
	{
		return org.jsoup.Jsoup.connect(url)
				.timeout(10000)
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")
				.referrer("http://www.google.com/")
				//   .validateTLSCertificates(false)
				.ignoreContentType(true)
				.method(Connection.Method.GET)
				.get();
	}

	private static String extractIcon(Document d)
	{
		Elements icons = d.select("link[rel=icon],link[rel='shortcut icon']");
		return icons.isEmpty() ? "" : icons.last().attr("href");
	}

	private static String extractRssUrl(Document d)
	{
		//<a href="https://www.lasprovincias.es/rss/" title="RSS">
		Elements links = d.select("link[type='application/rss+xml']");
		if (links.isEmpty()) {
			return null;
		}
		return links.first().attr("href");
	}

	private static int extractColor(Document d)
	{
		Elements colors = d.select("meta[name='theme-color']");
		String color_s = colors.isEmpty() ? "" : colors.first().attr("content");
		return color_s.isEmpty() ? /*0x8BC34A*/ -1 : Integer.parseInt(color_s.replace("#", ""), 16);
	}

	private static String extractName(Document d, String request)
	{
		Elements etitle = d.select("title");
		if (etitle.isEmpty()) {
			int last_dot_index = request.lastIndexOf(".");
			String site_name = request.substring(0, last_dot_index == -1 ? request.length() : last_dot_index);
			site_name = site_name.replace("http://", "").replace("https://", "").replace("www.", "");
			return Character.toUpperCase(site_name.charAt(0)) + site_name.substring(1);
		}

		String title = etitle.first().text();
		int c = title.indexOf("-");
		if (c != -1)
			title = title.substring(0, c).trim();
		title = title.toLowerCase();
		char[] letters = title.toCharArray();
		boolean capitalize = true;
		for (int i = 0; i < letters.length; i++) {
			if (letters[i] == ' ') {
				capitalize = true;
				continue;
			}
			if (capitalize) {
				letters[i] = Character.toUpperCase(letters[i]);
				capitalize = false;
			}
		}

		System.out.println("n1:" + new String(letters));
		System.out.println("n2:" + extractName2(d));
		System.out.println("n3:" + extractName3(d));
		System.out.println("n4:" + extractName4(d));
		return new String(letters);
	}

	private static String extractName2(Document d)
	{
		Elements s = d.select("meta[name='apple-mobile-web-app-title']");
		return s.isEmpty() ? "" : s.first().attr("content");
	}

	private static String extractName3(Document d)
	{
		Elements s = d.select("meta[name='application-name']");
		return s.isEmpty() ? "" : s.first().attr("content");
	}

	private static String extractName4(Document d)
	{
		Elements s = d.select("meta[property='og:site_name']");
		return s.isEmpty() ? "" : s.first().attr("content");
	}

	private static String extractTwitter(Document d)
	{
		Elements s = d.select("meta[name='twitter:site']");
		if (s.isEmpty())
			return null;

		String r = s.first().attr("content").replace("@", "");
		return "https://twitter.com/" + r;
	}

}
