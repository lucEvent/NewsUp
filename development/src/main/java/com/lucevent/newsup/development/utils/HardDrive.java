package com.lucevent.newsup.development.utils;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.Site;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class HardDrive {

	private static final String PATH = "development\\deb_cache\\";
	public static int n = 1;

	public static void copyToDeb(org.jsoup.nodes.Document doc, boolean removeScript)
	{
		if (removeScript) {
			doc.select("script").remove();
		}
		copyTo("deb", null, doc.outerHtml());
	}

	public static void copyToDeb(String content)
	{
		copyTo("deb", null, content);
	}

	public static void copyTo(Site site, News news)
	{
		copyTo("n" + n++, news, site == null ? "" : site.getStyle() + news.content);
	}

	public static void copyTo(String filename, News news)
	{
		copyTo(filename, news, news.content);
	}

	public static void copyToWithTitle(News news)
	{
		String t = news.title;
		if (t.length() > 20) {
			t = t.substring(0, 20);
		}
		t = t.replace("¿", "_").replace("?", "_").replace("-", "_").replace("\"", "").replace("“", "").replace(":", "");
		copyTo(t, news);
	}

	public static void copyTo(String filename, News news, String content)
	{
		File f = new File(PATH, filename + ".html");

		try {
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(f));

			if (news != null) {
				String link = "<a href=\"" + news.link + "\">" + news.title + "</a>";
				stream.write(link.getBytes());
			}
			stream.write("<meta charset=\"utf-8\">".getBytes());
			stream.write(NEWS_STYLE.getBytes());
			stream.write(GRAPHYCS_STYLE.getBytes());
			stream.write(content.getBytes());

			stream.flush();
			stream.close();

		} catch (Exception e) {
			System.out.println("Error in NR.copyToDeb [filename=" + filename + "]");
			e.printStackTrace();
		}

	}

	public static void copy(News news, String filename)
	{
		File f = new File(PATH, filename + ".html");

		try {
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(f));

			String link = "<a href=\"" + news.link + "\">" + news.title + "</a>";
			stream.write(link.getBytes());
			stream.write("<meta charset=\"utf-8\">".getBytes());
			stream.write(NEWS_STYLE.getBytes());
			stream.write(GRAPHYCS_STYLE.getBytes());
			stream.write(news.content.getBytes());

			stream.flush();
			stream.close();

		} catch (Exception e) {
			System.out.println("Error in NR.copyToDeb [filename=" + filename + ".html]");
			e.printStackTrace();
		}
	}

	private static final String NEWS_STYLE = "<style>"
			+ "body {margin:20px;font-family:sans-serif-light;font-weight:300;font-size:17px;line-height:1.7;}"
			+ "blockquote{margin:10px;padding:5px 10px 5px 10px;background-color:#f2f2f2}"
			+ "figcaption{font-size:12px;padding:2px 10px;display:block;}"
			+ "</style>";

	private static final String GRAPHYCS_STYLE = "<style>"
			+ "iframe, video {width: 100%;min-height:320px;max-height:100%;margin: 0; padding: 0}"
			+ "img, figure {width: 100%; height:auto; margin: 0; padding: 0}"
			+ "div > h2 > a > img {width: auto;}"
			+ "</style>";

}
