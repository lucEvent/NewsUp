package com.lucevent.newsup.data.util;

import org.jsoup.select.Elements;

import java.io.Serializable;
import java.util.ArrayList;

public class News implements Comparable<News>, Serializable {

	public static final int ID_DUMMY = -9;

	public final int id;

	public final String title, link;

	public final long date;
	public final int site_code, section_code;
	public final Tags tags;
	public long readOn;
	public String description, content;
	public String imgSrc;

	public News(int id, String title, String link, String description, long date, String imgSrc, Tags tags, int site_code, int section_code, long readOn)
	{
		this.id = id;
		this.title = title;
		this.link = link;
		this.description = description;
		this.date = date;
		this.imgSrc = imgSrc;
		this.tags = tags;
		this.section_code = section_code;
		this.readOn = readOn;
		this.site_code = site_code;
	}

	public News(String title, String link, String description, long date, String imgSrc, Tags categories, int site_code, int section_code, long readOn)
	{
		this(link.hashCode(), title, link, description, date, imgSrc, categories, site_code, section_code, readOn);
	}

	public News clone()
	{
		return new News(id, title, link, description, date, imgSrc, tags, site_code, section_code, readOn);
	}

	public boolean hasKeyWords(ArrayList<String> keyWords)
	{
		String[] titleTags = title.toLowerCase().replaceAll("[^\\p{IsAlphabetic}^\\p{Blank}^\\p{IsDigit}]", "").split(" ");
		for (String keyW : keyWords) {
			for (String tag : tags)
				if (tag.equals(keyW))
					return true;

			for (String tag : titleTags)
				if (tag.equals(keyW))
					return true;
		}
		return false;
	}

	@Override
	public int compareTo(News o)
	{
		int comparison = Long.compare(this.date, o.date);
		return comparison != 0 ? -comparison : Integer.compare(this.id, o.id);
	}

	public void forceImage()
	{
		if ((content == null || content.isEmpty()) && imgSrc == null)
			return;

		Elements images = org.jsoup.Jsoup.parse(content).select("img[src]");
		if (images.isEmpty())
			return;

		imgSrc = images.first().attr("src");
	}

}
