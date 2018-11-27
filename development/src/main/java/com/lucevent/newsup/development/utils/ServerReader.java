package com.lucevent.newsup.development.utils;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Tags;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class ServerReader {

	private static final int HASH_TITLE = 110371416;
	private static final int HASH_LINK = 3321850;
	private static final int HASH_DATE = 3076014;
	private static final int HASH_DESCRIPTION = -1724546052;
	private static final int HASH_CATEGORIES = 1296516636;
	private static final int HASH_CONTENT = 951530617;
	private static final int HASH_ENCLOSURE = 1432853874;
	private static final int HASH_SECTION = 1970241253;

	public static final int NUM_SERVERS = 8;
	private static final String[] SERVER_IDS = {"newsup-1", "newsup-2", "newsup-3", "newsup-4", "newsup-5", "newsup-1", "newsup-2", "newsup-3"};

	private static final String query_index = "http://newsup-2406.appspot.com/appv2?news&site=%s%s&v=debug&nc";
	private static final String query_event_index = "http://newsup-2406.appspot.com/appv2?eventnews&site=%s%s&ecode=%s&v=debug";
	private static final String query_content = "http://%s.appspot.com/appv2?content&site=%d&l=%s";

	public ServerReader()
	{
	}

	public final NewsArray readNewsHeaders(int site_code, int[] section_codes)
	{
		return readHeaders(String.format(query_index, site_code, stringify(section_codes)), site_code);
	}

	public NewsArray readEventHeaders(int site_code, int[] section_codes, int event_code)
	{
		return readHeaders(String.format(query_event_index, site_code, stringify(section_codes), event_code), site_code);
	}

	private String stringify(int[] section_codes)
	{
		StringBuilder sectArray = new StringBuilder(section_codes.length * 3);
		for (int section_code : section_codes) {
			if (section_code != -1) {
				sectArray.append(',').append(section_code);
			}
		}

		return sectArray.toString();
	}

	private final NewsArray readHeaders(String query_link, int site_code)
	{
		System.out.println("[" + site_code + "] Query: " + query_link);

		org.jsoup.nodes.Document doc = getDocument(query_link);
		if (doc == null) {
			return new NewsArray();
		}

		NewsArray res = new NewsArray();

		for (org.jsoup.nodes.Element item : doc.select("item")) {
			String title = "", link = "", description = "", content = "", categories = "";
			int section = 0;
			long date = 0;
			Enclosures enclosures = new Enclosures();

			for (org.jsoup.nodes.Element prop : item.children()) {

				switch (prop.tagName().hashCode()) {
					case HASH_TITLE:
						title = prop.html();
						break;
					case HASH_LINK:
						link = prop.html();
						break;
					case HASH_DATE:
						date = Long.parseLong(prop.html());
						break;
					case HASH_DESCRIPTION:
						description = prop.text();
						break;
					case HASH_CATEGORIES:
						categories = prop.html();
						break;
					case HASH_CONTENT:
						content = prop.html();
						break;
					case HASH_ENCLOSURE:
						enclosures.add(new Enclosure(prop.text(), "image", ""));
						break;
					case HASH_SECTION:
						section = Integer.parseInt(prop.text());
						break;
				}
			}
			if (!title.isEmpty()) {
				String imgSrc = null;
				if (!enclosures.isEmpty()) {
					for (Enclosure e : enclosures) {
						if (e.type == Enclosure.TYPE_IMAGE) {
							imgSrc = e.src;
							break;
						}
					}
				}

				News news = new News(title, link, description, date, imgSrc, new Tags(categories), site_code, section, 0);
				news.content = content;
				res.add(news);
			}
		}
		System.out.println("[" + site_code + "] -> read " + res.size() + " news");
		return res;
	}

	private org.jsoup.nodes.Document getDocument(String url)
	{
		try {
			return org.jsoup.Jsoup.connect(url).parser(org.jsoup.parser.Parser.xmlParser()).timeout(10000).get();
		} catch (Exception e) {
			System.out.println("[NR] Can't read url: " + url);
			e.printStackTrace();
		}
		return null;
	}

	private String getQueryRawContent(String query)
	{
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new URL(query).openStream()));

			int length;
			char[] buffer = new char[2048];

			StringBuilder sb = new StringBuilder();
			while ((length = in.read(buffer, 0, buffer.length)) > 0) {
				sb.append(buffer, 0, length);
			}

			in.close();

			return sb.toString();
		} catch (Exception e) {
			System.out.println("[NR] Can't read url: " + query);
			e.printStackTrace();
		}
		return "";
	}

	public final News readNewsContent(int server, News news)
	{
		String query = String.format(query_content, SERVER_IDS[server], news.site_code, news.link);
		String content = getQueryRawContent(query);

		if (!content.isEmpty()) {
			news.content = content;
		} else {
			System.out.println("[CNF] " + query);
		}

		return news;
	}

}
