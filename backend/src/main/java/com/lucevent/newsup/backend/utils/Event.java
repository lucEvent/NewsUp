package com.lucevent.newsup.backend.utils;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Entity
public class Event {

	public static class EventInfo {
		public String lang, title, topic;
	}

	public static class EventSite {
		public int site_code;
		public int[] section_codes;
	}

	@Id
	@Index
	public long code;

	@Index
	public long startTime;

	@Index
	public long endTime;

	@Index
	public boolean visible;

	@Unindex
	public EventInfo[] info;

	@Unindex
	public String imgSrc;

	@Unindex
	public String[] tags;

	@Unindex
	public EventSite[] sites;

	public Event()
	{
	}

	public EventInfo getInfo(String lang)
	{
		for (EventInfo ei : info)
			if (lang.equals(ei.lang))
				return ei;
		return getInfo("en");
	}

	public static Event parse(String json) throws Exception
	{
		Event res = new Event();
		try {
			JSONObject parsed = new JSONObject(json);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

			res.visible = parsed.getBoolean("visible");
			res.code = parsed.getInt("code");

			try {
				res.startTime = sdf.parse(parsed.getString("start")).getTime();
			} catch (Exception e) {
				throw new Exception("Wrong start time");
			}
			try {
				res.endTime = sdf.parse(parsed.getString("end")).getTime();
			} catch (Exception e) {
				throw new Exception("Wrong end time");
			}
			try {
				res.imgSrc = new URL(parsed.getString("imgsrc")).toURI().toString();
			} catch (Exception e) {
				throw new Exception("Wrong image link");
			}
			String[] tempTags = parsed.getString("tags").split(",");
			int validTags = 0;
			String[] auxTags = new String[tempTags.length];
			for (int i = 0; i < tempTags.length; i++) {
				auxTags[validTags] = tempTags[i].trim().toLowerCase();
				if (auxTags[i].length() > 0)
					validTags++;
			}
			if (validTags == 0)
				throw new Exception("No valid tags");
			if (validTags != tempTags.length)
				auxTags = Arrays.copyOfRange(auxTags, 0, validTags);
			res.tags = auxTags;


			JSONArray parsedInfo = parsed.getJSONArray("info");
			res.info = new EventInfo[parsedInfo.length()];
			for (int i = 0; i < parsedInfo.length(); i++) {
				JSONObject p = parsedInfo.getJSONObject(i);

				res.info[i] = new EventInfo();
				res.info[i].lang = p.getString("lang");
				res.info[i].title = p.getString("title");
				res.info[i].topic = p.getString("topic");
			}

			JSONArray parsedSites = parsed.getJSONArray("sites");
			res.sites = new EventSite[parsedSites.length()];
			for (int i = 0; i < parsedSites.length(); i++) {
				JSONObject p = parsedSites.getJSONObject(i);

				res.sites[i] = new EventSite();
				res.sites[i].site_code = p.getInt("code");

				String[] section_codes = p.getString("sections").split(",");
				res.sites[i].section_codes = new int[section_codes.length];
				for (int j = 0; j < section_codes.length; j++)
					res.sites[i].section_codes[j] = Integer.parseInt(section_codes[j]);
			}

		} catch (JSONException e) {
			//System.out.println("JSON exception:" + e.getMessage());
			//e.printStackTrace();
			throw new Exception("Json error" + e.getMessage());
		}
		return res;
	}

}
