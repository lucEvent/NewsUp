package com.lucevent.newsup.net;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.ProSettings;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Tags;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public final class NewsReader implements BackendNames {

	private static final String TAG_TITLE = "title";
	private static final String TAG_LINK = "link";
	private static final String TAG_DATE = "date";
	private static final String TAG_DESCRIPTION = "description";
	private static final String TAG_CATEGORIES = "categories";
	private static final String TAG_CONTENT = "content";
	private static final String TAG_ENCLOSURE = "enclosure";
	private static final String TAG_SECTION = "section";
	private static final String TAG_SITE_CODE = "sitecode";

	public static final int NUM_SERVERS = 8;
	private static final String[] SERVER_IDS = {"newsup-1", "newsup-2", "newsup-3", "newsup-4", "newsup-5", "newsup-1", "newsup-2", "newsup-3"};

	private static final String query_index = MAIN_APP_SERVER + "?news&site=%s%s&v=%s" + (ProSettings.isDeveloperModeEnabled() ? "&nc" : "");
	private static final String query_event_index = MAIN_APP_SERVER + "?event=%s&v=%s";
	private static final String query_content = "http://%s.appspot.com/" + APP_SERVLET + "?content&site=%d&l=%s";
	private final String version;

	public NewsReader(String version)
	{
		this.version = version;
	}

	public final NewsArray readNewsHeaders(int site_code, int[] section_codes)
	{
		return readHeaders(String.format(query_index, site_code, stringify(section_codes), version), site_code);
	}

	public NewsArray readEventHeaders(int event_code)
	{
		return readHeaders(String.format(query_event_index, event_code, version), event_code);
	}

	private String stringify(int[] section_codes)
	{
		StringBuilder sectArray = new StringBuilder(section_codes.length * 3);
		for (int section_code : section_codes)
			if (section_code != -1)
				sectArray.append(',').append(section_code);

		return sectArray.toString();
	}

	private final NewsArray readHeaders(String query_link, int site_code)
	{
		AppSettings.printlog("[" + site_code + "] Query: " + query_link);

		org.jsoup.nodes.Document doc = getDocument(query_link);
		if (doc == null) return new NewsArray();

		NewsArray res = new NewsArray();

		for (org.jsoup.nodes.Element item : doc.select("item")) {
			String title = "", link = "", description = "", content = "", categories = "", imgSrc = null;
			int section = 0;
			long date = 0;

			for (org.jsoup.nodes.Element prop : item.children()) {
				switch (prop.tagName()) {
					case TAG_TITLE:
						title = prop.html();
						break;
					case TAG_LINK:
						link = prop.html();
						break;
					case TAG_DATE:
						date = Long.parseLong(prop.html());
						break;
					case TAG_DESCRIPTION:
						description = prop.text();
						break;
					case TAG_CATEGORIES:
						categories = prop.html();
						break;
					case TAG_CONTENT:
						content = prop.html();
						break;
					case TAG_ENCLOSURE:
						imgSrc = prop.text();
						break;
					case TAG_SECTION:
						section = Integer.parseInt(prop.text());
						break;
					case TAG_SITE_CODE:
						site_code = Integer.parseInt(prop.text());
						break;
				}
			}
			if (!title.isEmpty()) {
				News news = new News(title, link, description, date, imgSrc, new Tags(categories), site_code, section, 0);
				news.content = content;

				res.add(news);
			}
		}
		AppSettings.printlog("[" + site_code + "] -> read " + res.size() + " news");
		return res;
	}

	private org.jsoup.nodes.Document getDocument(String url)
	{
		try {
			return org.jsoup.Jsoup.connect(url).parser(org.jsoup.parser.Parser.xmlParser()).timeout(10000).get();
		} catch (Exception e) {
			AppSettings.printerror("[NR] Can't read url: " + url, e);
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
			while ((length = in.read(buffer, 0, buffer.length)) > 0)
				sb.append(buffer, 0, length);

			in.close();

			return sb.toString();
		} catch (Exception e) {
			AppSettings.printerror("[NR] Can't read url: " + query, e);
		}
		return "";
	}

	public final News readNewsContent(int server, News news)
	{
		String query = String.format(query_content, SERVER_IDS[server], news.site_code, news.link);
		String content = getQueryRawContent(query);

		if (!content.isEmpty())
			news.content = content;
		else
			AppSettings.printerror("[NR] CONTENT NOT FOUND OF " + news.link, null);

		return news;
	}

}