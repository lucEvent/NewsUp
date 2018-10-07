package com.lucevent.newsup.backend.utils;

import com.lucevent.newsup.backend.Data;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Site;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class EventFinder {

	private static final String QUERY_IMAGE = "https://www.google.com/search?q=%s&source=lnms&tbm=isch";

	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36";

	private interface EventExtractor {

		ArrayList<String> getTopics(Document d);
	}

	// ignored words which length > 1
	private static final String[] ES_IGNORED_WORDS = {"la", "las", "lo", "los", "el", "un", "una", "del", "ante", "bajo", "cabe", "con", "contra", "de", "desde", "en", "entre", "hacia", "hasta", "para", "por", "según", "sin", "so", "sobre", "tras"};
	private static final String[] EN_IGNORED_WORDS = {"a", "about", "all", "also", "and", "as", "at", "be", "because", "but", "by", "can", "come", "could", "day", "do", "even", "find", "first", "for", "from", "get", "give", "go", "have", "he", "her", "here", "him", "his", "how", "I", "if", "in", "into", "it", "its", "just", "know", "like", "look", "make", "man", "many", "me", "more", "my", "new", "no", "not", "now", "of", "on", "one", "only", "or", "other", "our", "out", "people", "say", "see", "she", "so", "some", "take", "tell", "than", "that", "the", "their", "them", "then", "there", "these", "they", "thing", "think", "this", "those", "time", "to", "two", "up", "use", "very", "want", "way", "we", "well", "what", "when", "which", "who", "will", "with", "would", "year", "you", "your"};
	private static final String[] CA_IGNORED_WORDS = {};
	private static final String[] SV_IGNORED_WORDS = {};

	private static final int[][] ES_SOURCES = {
			{100, -306162022},
			{105, 132737269},
			{110, 1272519773},
			{125, 2085053711},
			{130, 132737269},
			{135, -2141651978},
			{140, 2085053711},
			{145, 1272519773},
			{155, -778380837},
			{165, 1272519773},
			{205, 1272519773},
			{210, 191926286}
	};
	private static final int[][] UK_SOURCES = {
			{500, 816398440}, // BBC
			{505, 2424563}, // The Telegraph
			{510, -420727794}, // The Huffington Post UK
			{515, -369395939}, // Metro.co.uk
			{520, 307565117}, // The Guardian
			{535, -420727794}, // The Independent
			{550, -420727794}, // Metro UK
			{555, -420727794}, // Evening Standard
			{570, 2424563}, // Daily Express
	};
	private static final int[][] CAT_SOURCES = {
			{200, 1272519773}, // El Periódico (Cat)
			{225, 1771822894}, // Racó Català
			{230, 191926286}, // VilaWeb
			{235, 1272519773}, // El Punt Avui
			{250, -890380364}, // Nació Digital
	};
	private static final int[][] US_SOURCES = {
			{600, -1813915576}, // CNN
			{605, 536598325}, // The Huffington Post USA
			{610, 1796885759}, // USA Today
			{615, -232053456}, // The New York Times
	};
	private static final int[][] SV_SOURCES = {
			{300, 438456953}, // Aftonbladet
			{305, -225859847}, // Expressen
			{310, -1290138452}, // Dagens Nyheter
			{315, -1226593567}, // Svenska Dagbladet
			{320, 80204866}, // Göteborgs-Posten
			{325, -673985891}, // Fria Tider
	};

	public ArrayList<Event> find()
	{
		ArrayList<Event> tts_es = findEvents("es", "es", "es_es", "https://www.20minutos.es/", new EventExtractor() {
			@Override
			public ArrayList<String> getTopics(Document d)
			{
				Elements elems = d.select(".subheader-menu-left a");

				ArrayList<String> res = new ArrayList<>(elems.size());
				for (Element e : elems) {
					res.add(e.text());
				}
				return res;
			}
		});

		findNews(tts_es);

		ArrayList<Event> tts_uk = findEvents("uk", "en", "en_uk", "https://www.theguardian.com/uk", new EventExtractor() {
			@Override
			public ArrayList<String> getTopics(Document d)
			{
				Elements elems = d.select("[data-title='Headlines'] .fc-item__kicker");

				ArrayList<String> res = new ArrayList<>(elems.size());
				for (Element e : elems) {
					res.add(e.text());
				}
				return res;
			}
		});

		findNews(tts_es);

		tts_es.addAll(tts_uk);
		return tts_es;
	}

	private void findNews(ArrayList<Event> tts)
	{
		int n_source = tts == null || tts.isEmpty() ? 0 : tts.get(0).sites.length;
		for (int i = 0; i < n_source; i++) {
			Site site = Data.getSite(tts.get(0).sites[i].site_code);

			NewsArray news = site.readNewsHeaders(tts.get(0).sites[i].section_codes);
			for (News n : news)
				for (Event e : tts) {
					if (n.hasKeyWords(e.tags)) {

						if (ofy().load().type(EventNews.class)
								.filter("event_code", e.code)
								.filter("site_code", site.code)
								.filter("link", n.link)
								.count() == 0) {

							EventNews en = new EventNews();
							en.id = n.id * e.code;
							en.event_code = e.code;
							en.site_code = site.code;
							en.link = n.link;
							en.title = n.title;
							en.description = n.description;
							en.content = n.content;
							en.imgSrc = n.imgSrc;
							en.date = n.date;
							en.section_code = n.section_code;
							en.tags = n.tags;

							ofy().save().entity(en).now();
						}
					}
				}
		}
	}

	private ArrayList<Event> findEvents(String countryCode, String langCode, String regionCode, String url, EventExtractor eventExtractor)
	{
		ArrayList<Event> events = new ArrayList<>();

		// Getting last minute topics
		Document d = getDocument(url, false);
		if (d == null) {
			//	System.out.println("Doc is null");
			return new ArrayList<>();
		}

		ArrayList<String> tts = eventExtractor.getTopics(d);

		// Extracting tags
		TreeSet ignoredWords = getIgnoredWords(langCode);
		for (String tt : tts) {
			String[] words = tt.trim().toLowerCase().split(" ");
			ArrayList<String> tags = new ArrayList<>(words.length);
			for (String w : words)
				if (w.length() > 1 && !ignoredWords.contains(w))
					tags.add(w);

			Event e = new Event();
			e.tags = tags.toArray(new String[tags.size()]);
			events.add(e);
		}

		// Getting images
		for (int i = 0; i < tts.size(); i++) {
			final String tt = tts.get(i).replace(" ", "+");

			d = getDocument(String.format(QUERY_IMAGE, tt), true);
			if (d == null) {
				//	System.out.println("Doc is null");
				continue;
			}

			Elements imgCon = d.select(".rg_meta");
			if (imgCon.isEmpty())
				continue;

			events.get(i).imgSrc = findSubstringBetween(imgCon.get(0).text(), "\"ou\":\"", "\"", false);
		}

		// Setting codes, dates, descriptions
		long startDate = System.currentTimeMillis();
		long endDate = startDate + 12 * 60 * 60 * 1000; // startDate + 1/2 Day
		Event.EventSite[] siteSources = getSources(countryCode);
		for (int i = 0; i < tts.size(); i++) {
			Event e = events.get(i);
			String tt = tts.get(i);

			e.code = 20000 + (tt.hashCode() % 10000);
			e.region_code = regionCode;
			e.startTime = startDate - i;
			e.endTime = endDate + i;
			e.title = tt;
			e.sites = siteSources;
			e.visible = true;
		}

		return events;
	}

	private static TreeSet getIgnoredWords(String langCode)
	{
		String[] ignoredWords;
		switch (langCode) {
			case "es":
				ignoredWords = ES_IGNORED_WORDS;
				break;
			case "en":
				ignoredWords = EN_IGNORED_WORDS;
				break;
			case "ca":
				ignoredWords = CA_IGNORED_WORDS;
				break;
			case "sv":
				ignoredWords = SV_IGNORED_WORDS;
				break;
			default:
				ignoredWords = new String[0];
		}
		return new TreeSet<>(Arrays.asList(ignoredWords));
	}

	private Event.EventSite[] getSources(String countryCode)
	{
		int[][] sources;
		switch (countryCode) {
			case "es":
				sources = ES_SOURCES;
				break;
			case "uk":
				sources = UK_SOURCES;
				break;
			case "us":
				sources = US_SOURCES;
				break;
			case "cat":
				sources = CAT_SOURCES;
				break;
			case "sv":
				sources = SV_SOURCES;
				break;
			default:
				return null;
		}
		Event.EventSite[] res = new Event.EventSite[sources.length];
		for (int i = 0; i < sources.length; i++) {
			int[] s = sources[i];

			res[i] = new Event.EventSite();
			res[i].site_code = s[0];
			res[i].section_codes = new int[]{s[1]};
		}
		return res;
	}

	private org.jsoup.nodes.Document getDocument(String url, boolean readRaw)
	{
		try {
			if (readRaw)
				return org.jsoup.Jsoup.parse(getUrl(url).toString());

			else
				return org.jsoup.Jsoup.connect(url)
						.userAgent(USER_AGENT)
						.timeout(10000)
						//.validateTLSCertificates(false)
						.get();

		} catch (Exception e) {
			//System.out.println("[EventEngine] Can't read page " + url);
		}
		return null;
	}

	public static StringBuilder getUrl(String url) throws IOException
	{
		URLConnection con = new URL(url).openConnection();
		con.setRequestProperty("User-Agent", USER_AGENT);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

		StringBuilder sb = new StringBuilder();
		int len = 2048;
		char[] buffer = new char[len];

		while ((len = in.read(buffer, 0, len)) > 0)
			sb.append(buffer, 0, len);

		in.close();

		return sb;
	}


	/**
	 * Copied from Reader.java
	 *
	 * @param data      String the substring will be looked up
	 * @param start     Starting of the substring
	 * @param end       Ending of the substring
	 * @param inclusive Whether or not include the start and end
	 * @return the substring delimited by start and end (including start and end if inclusive=true), null if not found
	 */
	private String findSubstringBetween(String data, String start, String end, boolean inclusive)
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

}
