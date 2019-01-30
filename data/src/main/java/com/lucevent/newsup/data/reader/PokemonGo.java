package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Tags;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class PokemonGo extends com.lucevent.newsup.data.util.Reader {

	public PokemonGo()
	{
		super("");
	}

	@Override
	public final NewsArray readRssHeader(String rss_link, int site_code, int section_code)
	{
		org.jsoup.nodes.Document doc = getDocument(rss_link);
		if (doc == null)
			return new NewsArray();

		String lang = doc.select("html").attr("lang");

		NewsArray result = new NewsArray();
		String title, link;
		long date;
		Element eDate, eDescription;

		Elements items = doc.select(".post-list__date-item,.post-list__title a,.post-list__title--nolink");
		for (int i = 0; i < items.size(); i += 2) {
			eDate = items.get(i);
			eDescription = items.get(i + 1);

			title = eDescription.text();
			link = "http://pokemongolive.com" + eDescription.attr("href");
			date = calculateDate(eDate.text().trim(), lang);

			News news = new News(title, link, "", date, null, new Tags(), site_code, section_code, 0);
			news.content = link.isEmpty() ? title : "";
			result.add(news);

		}
		return result;
	}

	private long calculateDate(String date, String language)
	{
		String[] parts;
		String day, month, year;

		switch (language) {
			case "en":
				parts = date.split("/");
				day = parts[1];
				month = parts[0];
				year = parts[2];
				break;
			case "de":
				parts = date.split("\\.");
				day = parts[0];
				month = parts[1];
				year = parts[2];
				break;
			case "es":
			case "fr":
			case "it":
			case "pt-BR":
				parts = date.split("/");
				day = parts[0];
				month = parts[1];
				year = parts[2];
				break;
			case "ja":
				date = date.replace("\u5e74", "/").replace("\u6708", "/").replace("\u65e5", "");
			case "ko":
				date = date.replace("\uB144 ", "/").replace("\uC6D4 ", "/").replace("\uC77C", "");
			case "zh-Hant":
				parts = date.split("/");
				day = parts[2];
				month = parts[1];
				year = parts[0];
				break;
			default:
				return -1;
		}

		date = (year.length() < 4 ? "20" + year : year)
				+ (month.length() < 2 ? "0" + month : month)
				+ (day.length() < 2 ? "0" + day : day);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		try { // 2/8/17
			return sdf.parse(date).getTime();
		} catch (Exception e) {
			//System.out.println("Error converting date: " + date);
		}
		return -1L;
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document doc, String news_url)
	{
		Elements article = doc.select(".grid__item > div.grid__item--10-cols--gt-md");
		article.select("iframe").attr("frameborder", "0");

		return finalFormat(article, true);
	}

}
