package com.lucevent.newsup.backend.utils;

import com.lucevent.newsup.backend.Data;
import com.lucevent.newsup.backend.db.RequestedSite;
import com.lucevent.newsup.data.util.SiteCategory;
import com.lucevent.newsup.data.util.SiteCountry;
import com.lucevent.newsup.data.util.SiteLanguage;
import com.lucevent.newsup.data.util.UserSite;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

public class SiteSearchEngine {

	public static class Response {

		public int result;
		public String data;

		public Response(int result, String data)
		{
			this.result = result;
			this.data = data;
		}
	}

	public static Response search(String request)
	{
		JSONObject results;
		try {
			String query_url = "https://feedly.com/v3/search/feeds?q=" + URLEncoder.encode(request, "utf-8") +
					"&n=8&fullTerm=false&organic=true&promoted=true&locale=es-ES&useV2=true&ck="
					+ System.currentTimeMillis() + "&ct=feedly.desktop&cv=31.0.251";
			results = new JSONObject(getUrl(query_url).toString());
		} catch (Exception e) {
			StringWriter writer = new StringWriter();
			e.printStackTrace(new PrintWriter(writer));
			return new Response(UserSite.ERROR_IN_FEEDLY_QUERY, "{\"error\":\"ERROR_IN_FEEDLY_QUERY exception:" + writer.toString() + "\"}");
		}

		StringBuilder sb = new StringBuilder("\"results\":[");
		JSONArray elems = results.getJSONArray("results");
		boolean needsComma = false;
		for (int i = 0; i < elems.length(); i++) {
			JSONObject json = (JSONObject) elems.get(i);
			try {
				String name = new String(json.getString("title").getBytes(), "utf-8");
				String site_url = json.getString("website");
				String rss_url = json.getString("feedId").substring(5);
				String icon_url =
						json.has("visualUrl") ?
								json.getString("visualUrl") :
								"";
				long info = (long) parseInfo(json.getString("language"), json.getJSONArray("deliciousTags"));
				long color =
						json.has("coverColor") ?
								(long) Integer.parseInt(json.getString("coverColor"), 16) :
								-1L;

				RequestedSite site = RequestedSite.get(site_url);
				if (site == null) {
					site = new RequestedSite(request, name, site_url, rss_url, icon_url, info, color);
					Data.requestedSites.add(site);
				} else {
					site.countRequest();
				}

				if (needsComma)
					sb.append(",");
				else
					needsComma = true;

				sb.append("{\"code\":").append(site.getCode())
						.append(",\"name\":\"").append(site.getName())
						.append("\", \"url\":\"").append(site.getUrl())
						.append("\", \"rss\":\"").append(site.getRssUrl())
						.append("\", \"icon\":\"").append(site.getIconUrl())
						.append("\", \"info\":\"").append(site.getInfo())
						.append("\", \"color\":").append(site.getColor())
						.append("}");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		sb.append("]");

		return new Response(UserSite.OK, sb.toString());
	}

	private static String errorToJsonString(String request, String site_url, String error)
	{
		return "{" +
				"\"request\":\"" + request + "\"," +
				"\"url\":\"" + site_url + "\"," +
				"\"error\":\"" + error + "\"" +
				"}";
	}

	public static StringBuilder getUrl(String url) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(new URL(url).openStream()));

		StringBuilder sb = new StringBuilder();
		int len = 2048;
		char[] buffer = new char[len];

		while ((len = in.read(buffer, 0, len)) > 0)
			sb.append(buffer, 0, len);

		in.close();

		return sb;
	}

	private static int parseInfo(String language, JSONArray tags) throws UnsupportedEncodingException
	{
		int lang = SiteLanguage.VARIOUS;
		switch (language) {
			case "de":
				lang = SiteLanguage.GERMAN;
				break;
			case "ar":
				lang = SiteLanguage.ARABIC;
				break;
			case "bn":
				lang = SiteLanguage.BENGALI;
				break;
			case "zh":
				lang = SiteLanguage.CHINESE;
				break;
			case "ko":
				lang = SiteLanguage.KOREAN;
				break;
			case "es":
				lang = SiteLanguage.SPANISH;
				break;
			case "fi":
				lang = SiteLanguage.FINNISH;
				break;
			case "fr":
				lang = SiteLanguage.FRENCH;
				break;
			case "hi":
				lang = SiteLanguage.HINDI;
				break;
			case "id":
				lang = SiteLanguage.INDONESIAN;
				break;
			case "en":
				lang = SiteLanguage.ENGLISH;
				break;
			case "it":
				lang = SiteLanguage.ITALIAN;
				break;
			case "ja":
				lang = SiteLanguage.JAPANESE;
				break;
			case "pt":
				lang = SiteLanguage.PORTUGUESE;
				break;
			case "ru":
				lang = SiteLanguage.RUSSIAN;
				break;
			case "sv":
				lang = SiteLanguage.SWEDISH;
				break;
			case "ur":
				lang = SiteLanguage.URDU;
				break;
		}

		int cat = SiteCategory.NEWS;
		for (int i = 0; i < tags.length(); i++) {
			String tag = new String(tags.getString(i).getBytes(), "UTF-8");
			switch (tag) {
				case "fashion":
				case "moda":
					cat = SiteCategory.FASHION;
					break;
				case "politics":
				case "política":
					cat = SiteCategory.POLITICS;
					break;
				case "sport":
				case "sports":
				case "deporte":
					cat = SiteCategory.SPORTS;
					break;
				case "bloggar":
					cat = SiteCategory.BLOG;
					break;
				case "cultura":
					cat = SiteCategory.CULTURE;
					break;
				case "economia":
					cat = SiteCategory.ECONOMY;
					break;
				case "fitness":
				case "salud":
					cat = SiteCategory.HEALTH_AND_FITNESS;
					break;
				case "lifestyle":
					cat = SiteCategory.LIFESTYLE;
					break;
				case "gastronomia":
					cat = SiteCategory.GASTRONOMY;
					break;
				case "music":
				case "música":
					cat = SiteCategory.MUSIC;
					break;
				case "games":
					cat = SiteCategory.VIDEOGAMES;
					break;
			}
		}

		int country = SiteCountry.VARIOUS;
		for (int i = 0; i < tags.length(); i++) {
			String tag = new String(tags.getString(i).getBytes(), "UTF-8");
			switch (tag) {
				case "uk":
					country = SiteCountry.UK;
					break;
				case "sverige":
				case "sweden":
					country = SiteCountry.SWEDEN;
					break;
				case "catalunya":
				case "notícies":
					lang = SiteLanguage.CATALAN;
					country = SiteCountry.SPAIN;
					break;
				case "colombia":
					country = SiteCountry.COLOMBIA;
					break;
				case "japan":
					country = SiteCountry.JAPAN;
					break;
				case "india":
					country = SiteCountry.INDIA;
					break;
				case "indonesia":
					country = SiteCountry.INDONESIA;
					break;
				case "portugal":
					country = SiteCountry.PORTUGAL;
					break;
				case "russia":
					country = SiteCountry.RUSSIA;
					break;
			}
		}
		return (lang << SiteLanguage.shift) | (country << SiteCountry.shift) | (cat << SiteCategory.shift);
	}

}
