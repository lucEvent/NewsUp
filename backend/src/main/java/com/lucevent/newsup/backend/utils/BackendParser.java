package com.lucevent.newsup.backend.utils;

import com.lucevent.newsup.backend.Data;
import com.lucevent.newsup.backend.db.Event;
import com.lucevent.newsup.backend.db.EventNews;
import com.lucevent.newsup.backend.db.MonthStats;
import com.lucevent.newsup.backend.db.SiteStats;
import com.lucevent.newsup.backend.db.Statistics;
import com.lucevent.newsup.backend.db.TimeStats;
import com.lucevent.newsup.data.alert.Alert;
import com.lucevent.newsup.data.alert.AlertCode;
import com.lucevent.newsup.data.util.Date;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;
import com.lucevent.newsup.data.util.Site;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class BackendParser {

	public static StringBuilder toEntry(News news)
	{
		StringBuilder res = new StringBuilder("<item><sid>");
		res.append(news.id);    // deprecated
		res.append("</sid><title>");
		res.append(news.title);
		res.append("</title><link>");
		res.append(news.link);
		res.append("</link><date>");
		res.append(news.date);
		res.append("</date><description>");
		res.append(news.description);
		res.append("</description><content>");
		if (news.content != null && !news.content.isEmpty()) res.append(news.content);
		res.append("</content><categories>");
		res.append(news.tags.toString());
		res.append("</categories><section>");
		res.append(news.section_code);
		res.append("</section>");
		if (news.imgSrc != null && !news.imgSrc.isEmpty())
			res.append("<enclosure>").append(news.imgSrc).append("</enclosure>");
		res.append("</item>");
		return res;
	}

	public static StringBuilder toEntry(EventNews news)
	{
		StringBuilder res = new StringBuilder("<item><title>");
		res.append(news.getTitle());
		res.append("</title><link>");
		res.append(news.getLink());
		res.append("</link><date>");
		res.append(news.getDate());
		res.append("</date><description>");
		res.append(news.getDescription());
		res.append("</description><content>");
		if (news.getContent() != null && !news.getContent().isEmpty())
			res.append(news.getContent());
		res.append("</content><categories>");
		if (news.getTags() != null) res.append(news.getTags().toString());
		res.append("</categories><sitecode>");
		res.append(news.getSiteCode());
		res.append("</sitecode><section>");
		res.append(news.getSectionCode());
		res.append("</section>");
		if (news.getImgSrc() != null && !news.getImgSrc().isEmpty())
			res.append("<enclosure>").append(news.getImgSrc()).append("</enclosure>");
		res.append("</item>");
		return res;
	}

	public static StringBuilder json(StringBuilder builder, News news)
	{
		builder.append("{\"title\":").append(JSONObject.quote(news.title))
				.append(", \"sc\":").append(news.site_code)
				.append(", \"age\":\"").append(Date.getAge(news.date))
				.append("\", \"imgsrc\":\"").append(news.imgSrc)
				.append("\", \"nid\":").append(news.id)
				.append(", \"dscr\":").append(JSONObject.quote(news.description))
				.append(", \"tags\":[");

		int i = 0;
		for (String tag : news.tags) {
			if (i++ != 0)
				builder.append(",");
			builder.append("\"").append(tag).append("\"");
		}
		builder.append("]}");

		return builder;
	}

	public static StringBuilder toEntry(NewsArray news)
	{
		StringBuilder sb = new StringBuilder("<channel>");

		for (News N : news)
			sb.append(BackendParser.toEntry(N));

		sb.append("</channel>");
		return sb;
	}

	public static StringBuilder toEntry(List<EventNews> news)
	{
		StringBuilder sb = new StringBuilder("<channel>");

		for (EventNews N : news)
			sb.append(BackendParser.toEntry(N));

		sb.append("</channel>");
		return sb;
	}

	public static String json(NewsArray news)
	{
		StringBuilder builder = new StringBuilder(1000);
		builder.append("[");

		int i = 0;
		for (News N : news)
			try {
				if (i++ != 0)
					builder.append(",");
				json(builder, N);
			} catch (Exception ignored) {
			}

		return builder.append("]").toString();
	}

	public static StringBuilder json(Statistics stats, String options, String[] filters)
	{
		ArrayList<SiteStats> allStats = stats.getSiteStats();
		ArrayList<SiteStats> siteStats;
		if (filters == null)
			siteStats = allStats;
		else {
			for (int i = 0; i < filters.length; i++)
				filters[i] = filters[i].trim().toLowerCase();

			siteStats = new ArrayList<>(filters.length);
			for (SiteStats ss : allStats)
				for (String filter : filters)
					if (ss.getSiteName().toLowerCase().contains(filter)) {
						siteStats.add(ss);
						break;
					}
		}
		StatisticsSet statisticsSet;
		switch (options) {
			case "s":
				statisticsSet = new StatisticsSet(siteStats, StatisticsSet.CMP_SITE_NAME);
				break;
			case "n":
				statisticsSet = new StatisticsSet(siteStats, StatisticsSet.CMP_TOTAL_REQUESTS);
				break;
			case "m":
				statisticsSet = new StatisticsSet(siteStats, StatisticsSet.CMP_MONTH_REQUESTS);
				break;
			case "r":
				statisticsSet = new StatisticsSet(siteStats, StatisticsSet.CMP_READINGS);
				break;
			case "t":
			default:
				statisticsSet = new StatisticsSet(siteStats, StatisticsSet.CMP_LAST_REQUEST);
		}

		StringBuilder sb = new StringBuilder(1000);
		sb.append("\"stats\":{\"since\":")
				.append(stats.getSince())
				.append(",\"last\":")
				.append(stats.getLastStart())
				.append(",\"sites\":[");

		boolean needsComma = false;
		for (SiteStats ss : statisticsSet) {
			if (needsComma)
				sb.append(",");
			else needsComma = true;

			sb.append("{\"c\":").append(ss.getSiteCode())
					.append(",\"n\":\"").append(ss.getSiteName())
					.append("\",\"a\":").append(ss.getTotalRequests())
					.append(",\"m\":").append(ss.getMonthRequests())
					.append(",\"r\":").append(ss.getReadings())
					.append(",\"l\":").append(ss.getLastRequest())
					.append(",\"i\":\"").append(ss.getLastIp())
					.append("\",\"v\":\"").append(ss.getFromVersion())
					.append("\"}");
		}

		sb.append("]}");
		return sb;
	}

	public static StringBuilder json(TreeSet<MonthStats> monthStats)
	{
		StringBuilder sb = new StringBuilder(200);
		sb.append("\"months\":[");

		boolean needsComma = false;
		for (MonthStats ms : monthStats) {
			if (needsComma)
				sb.append(",");
			else needsComma = true;

			sb.append("{\"na\":\"").append(ms.getId())
					.append("\", \"va\":").append(ms.getCounter())
					.append("}");
		}
		sb.append("]");
		return sb;
	}

	public static StringBuilder json(TimeStats timeStats)
	{
		StringBuilder sb = new StringBuilder(100);
		sb.append("\"load\":")
				.append(timeStats.toString());
		return sb;
	}

	public static String json(Sections sections)
	{
		StringBuilder sb = new StringBuilder("[");
		for (Section s : sections) {
			if (sb.length() > 1) sb.append(",");
			sb.append("{")
					.append("\"code\":").append(s.code)
					.append(",\"level\":").append(s.level)
					.append(",\"name\":\"").append(s.name)
					.append("\"}");
		}
		sb.append("]");

		return sb.toString();
	}

	public static StringBuilder json(Set<Event> events)
	{
		StringBuilder sb = new StringBuilder(200);
		sb.append("[");

		boolean needsComma = false;
		for (Event E : events) {
			if (needsComma)
				sb.append(",");
			else needsComma = true;

			sb.append("{\"code\":").append(E.getCode())
					.append(", \"title\":\"").append(E.getTitle())
					.append("\", \"imgsrc\":\"").append(E.getImgSrc())
					.append("\", \"sources\":[");

			int[][] eventSources = EventSource.getSources(E.getRegionCode());
			for (int i = 0; i < eventSources.length; i++) {
				if (i != 0) sb.append(",");
				sb.append("{\"si_c\":").append(eventSources[i][0])  // siteCode
						.append(", \"se_c\":[").append(eventSources[i][1]) // sectionCode
						.append("]}");
			}

			ArrayList<String> tags = E.getTags();
			sb.append("], \"tags\":\"");
			for (int i = 0; i < tags.size(); i++) {
				if (i != 0) sb.append(",");
				sb.append(tags.get(i));
			}

			sb.append("\"}");
		}
		sb.append("]");
		return sb;
	}

	public static String jsonSites()
	{
		StringBuilder sb = new StringBuilder("[");

		for (Site s : Data.sites) {
			if (sb.length() > 1) sb.append(",");
			sb.append("{")
					.append("\"code\":").append(s.code)
					.append(",\"co\":").append(s.getCountry())
					.append(",\"la\":").append(s.getLanguage())
					.append(",\"ty\":").append(s.getCategory())
					.append(",\"name\":\"").append(s.name)
					.append("\"}");
		}
		sb.append("]");

		return sb.toString();
	}

	public static String json(Alerts alerts)
	{
		StringBuilder sb = new StringBuilder("[");
		for (Alert a : alerts) {
			if (sb.length() > 1) sb.append(",");
			sb.append("{")
					.append("\"" + AlertCode.JSON_ID + "\":").append(a.id)
					.append(",\"" + AlertCode.JSON_PROBABILITY + "\":").append(a.probability)
					.append(",\"" + AlertCode.JSON_MESSAGE_CODE + "\":").append(a.message_code)
					.append(",\"" + AlertCode.JSON_MESSAGE + "\":").append(JSONObject.quote(a.message))
					.append(",\"" + AlertCode.JSON_BTN_START_ACTION + "\":").append(a.btn_start_action)
					.append(",\"" + AlertCode.JSON_BTN_START_CODE + "\":").append(a.btn_start_code)
					.append(",\"" + AlertCode.JSON_BTN_START_TEXT + "\":").append(JSONObject.quote(a.btn_start_text))
					.append(",\"" + AlertCode.JSON_BTN_CENTER_ACTION + "\":").append(a.btn_center_action)
					.append(",\"" + AlertCode.JSON_BTN_CENTER_CODE + "\":").append(a.btn_center_code)
					.append(",\"" + AlertCode.JSON_BTN_CENTER_TEXT + "\":").append(JSONObject.quote(a.btn_center_text))
					.append(",\"" + AlertCode.JSON_BTN_END_ACTION + "\":").append(a.btn_end_action)
					.append(",\"" + AlertCode.JSON_BTN_END_CODE + "\":").append(a.btn_end_code)
					.append(",\"" + AlertCode.JSON_BTN_END_TEXT + "\":").append(JSONObject.quote(a.btn_end_text))
					.append("}");
		}
		sb.append("]");
		return sb.toString();
	}

}
