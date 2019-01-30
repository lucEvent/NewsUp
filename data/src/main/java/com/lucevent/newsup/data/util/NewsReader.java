package com.lucevent.newsup.data.util;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;

public abstract class NewsReader extends Reader {

	protected static final String TAG_ITEM_ITEMS = "item";
	protected static final String TAG_ITEM_ENTRY = "entry";
	protected static final String TAG_ITEM_ITEMS_ENTRY = "item,entry";

	protected static final int TAG_TITLE = "title".hashCode();
	protected static final int TAG_LINK = "link".hashCode();
	protected static final int TAG_GUID = "guid".hashCode();
	protected static final int TAG_PUBDATE = "pubDate".hashCode();
	protected static final int TAG_DC_DATE = "dc:date".hashCode();
	protected static final int TAG_DESCRIPTION = "description".hashCode();
	protected static final int TAG_CATEGORY = "category".hashCode();
	protected static final int TAG_CONTENT_ENCODED = "content:encoded".hashCode();
	protected static final int TAG_ENCLOSURE = "enclosure".hashCode();
	protected static final int TAG_ID = "id".hashCode();
	protected static final int TAG_UPDATED = "updated".hashCode();
	protected static final int TAG_SUMMARY = "summary".hashCode();
	protected static final int TAG_CONTENT = "content".hashCode();
	protected static final int TAG_MEDIA_CONTENT = "media:content".hashCode();
	protected static final int TAG_IMAGE = "image".hashCode();
	protected static final int TAG_PUBLISHED = "published".hashCode();

	private static final int TITLE = 0;
	private static final int LINK = 1;
	private static final int DATE = 2;
	private static final int DESCRIPTION = 3;
	private static final int CONTENT = 4;
	private static final int CATEGORY = 5;
	private static final int ENCLOSURE = 6;

	private String itemTag;
	private HashMap<Integer, Integer> tagMap;

	public NewsReader(String itemTag, int[] titleTags, int[] linkTags, int[] descriptionTags,
	                  int[] contentTags, int[] dateTags, int[] categoryTags, int[] enclosureTags,
	                  String style)
	{
		super(style);

		this.itemTag = itemTag;
		tagMap = new HashMap<>(7);
		for (int title : titleTags)
			tagMap.put(title, TITLE);
		for (int link : linkTags)
			tagMap.put(link, LINK);
		for (int description : descriptionTags)
			tagMap.put(description, DESCRIPTION);
		for (int content : contentTags)
			tagMap.put(content, CONTENT);
		for (int date : dateTags)
			tagMap.put(date, DATE);
		for (int category : categoryTags)
			tagMap.put(category, CATEGORY);
		for (int enclosure : enclosureTags)
			tagMap.put(enclosure, ENCLOSURE);
	}

	@Override
	public final NewsArray readRssHeader(String rss_link, int site_code, int section_code)
	{
		org.jsoup.nodes.Document doc = getDocument(rss_link);
		if (doc == null) return new NewsArray();

		NewsArray result = new NewsArray();
		String title, link, description, content;
		long date;

		Elements items = doc.select(itemTag);
		for (org.jsoup.nodes.Element item : items) {
			title = link = description = content = "";
			date = 0;
			Tags tags = new Tags();
			Enclosures enclosures = new Enclosures();

			Elements props = item.getAllElements();
			for (org.jsoup.nodes.Element prop : props) {
				Integer tag = tagMap.get(prop.tagName().hashCode());

				if (tag == null) continue;

				switch (tag) {
					case TITLE:
						title = parseTitle(prop);
						break;
					case LINK:
						link = parseLink(prop);
						break;
					case DATE:
						date = parseDate(prop);
						break;
					case DESCRIPTION:
						description = parseDescription(prop);
						break;
					case CONTENT:
						content = parseContent(prop);
						break;
					case CATEGORY:
						tags.add(parseCategory(prop));
						break;
					case ENCLOSURE:
						Enclosure enc = parseEnclosure(prop);
						if (enc != null)
							enclosures.add(enc);
						break;
				}
			}
			if (!title.isEmpty() && !link.isEmpty()) {
				String imgSrc = null;
				if (!enclosures.isEmpty())
					for (Enclosure e : enclosures)
						if (e.type == Enclosure.TYPE_IMAGE) {
							imgSrc = e.src;
							break;
						}

				News news = new News(title, link, description, date, imgSrc, tags, site_code, section_code, 0);
				news.content = content;
				news = onNewsRead(news, enclosures);
				result.add(news);
			}
		}
		return result;
	}

	protected News onNewsRead(News news, Enclosures enclosures)
	{
		return news;
	}

	@Override
	protected String readNewsContent(org.jsoup.nodes.Document document, String news_url)
	{
		//To implement by Subclasses in case they need
		return null;
	}

	protected String parseTitle(Element prop)
	{
		return prop.text();
	}

	protected String parseLink(Element prop)
	{
		String link = prop.text();
		int index = link.indexOf('#');
		if (index != -1) link = link.split("#")[0];
		index = link.indexOf('?');
		if (index != -1) link = link.split("\\?")[0];
		return link;
	}

	protected long parseDate(Element prop)
	{
		return Date.toDate(prop.text());
	}

	protected String parseDescription(Element prop)
	{
		return prop.text();
	}

	protected String parseContent(Element prop)
	{
		return prop.text();
	}

	protected String parseCategory(Element prop)
	{
		return prop.text();
	}

	protected Enclosure parseEnclosure(Element prop)
	{
		return new Enclosure(prop.attr("url"), prop.attr("type"), prop.attr("length"));
	}

	protected String findImageSrc(Element e)
	{
		Elements images = e.select("img[src]");
		return images.isEmpty() ? null : images.first().attr("src");
	}

	protected String findImageSrc(Elements e)
	{
		Elements images = e.select("img[src]");
		return images.isEmpty() ? null : images.first().attr("src");
	}


}