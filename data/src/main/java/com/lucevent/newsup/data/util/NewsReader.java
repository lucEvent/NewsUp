package com.lucevent.newsup.data.util;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;

public abstract class NewsReader {

    protected static final String TAG_ITEM_ITEMS = "item";
    protected static final String TAG_ITEM_ENTRY = "entry";
    protected static final String TAG_ITEM_ITEMS_ENTRY = "item,entry";

    protected static final int TAG_TITLE = "title".hashCode();
    protected static final int TAG_LINK = "link".hashCode();
    protected static final int TAG_GUID = "guid".hashCode();
    protected static final int TAG_PUBDATE = "pubdate".hashCode();
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

    protected static final String USER_AGENT = "Mozilla/5.0 (Linux; Android 6.0.1; GT-I9300 Build/MOB30Z) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.91 Mobile Safari/537.36";

    private static final int TITLE = 0;
    private static final int LINK = 1;
    private static final int DATE = 2;
    private static final int DESCRIPTION = 3;
    private static final int CONTENT = 4;
    private static final int CATEGORY = 5;
    private static final int ENCLOSURE = 6;

    private String itemTag;
    private HashMap<Integer, Integer> tagMap;

    public String style = "";

    public NewsReader(String itemTag, int[] titleTags, int[] linkTags, int[] descriptionTags,
                      int[] contentTags, int[] dateTags, int[] categoryTags, int[] enclosureTags)
    {
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

    public final NewsArray readRssHeader(String rss_link)
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
                News news = new News(title, link, description, date, tags);
                news.content = content;
                news.enclosures = enclosures;
                news = onNewsRead(news);
                result.add(news);
            }
        }
        return result;
    }

    protected org.jsoup.nodes.Document getDocument(String pagelink)
    {
        try {
            return org.jsoup.Jsoup.connect(pagelink).userAgent(USER_AGENT).get();
        } catch (Exception e) {
//            System.out.println("[" + e.getClass().getSimpleName() + "] Can't read page. Trying again");
        }
        try {
            return org.jsoup.Jsoup.connect(pagelink).get();
        } catch (Exception e) {
//            System.out.println("[" + e.getClass().getSimpleName() + "] Couldn't read page: " + pagelink);
        }
        return null;
    }

    protected News onNewsRead(News news)
    {
        return news;
    }

    public final News readNewsContent(News news)
    {
        org.jsoup.nodes.Document doc = getDocument(news.link);
        if (doc != null) {
            readNewsContent(doc, news);
        }
        return news;
    }

    protected void readNewsContent(org.jsoup.nodes.Document document, News news)
    {
        //To implement by Subclasses in case they need
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

    protected org.jsoup.nodes.Document jsoupParse(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text());
    }

    protected org.jsoup.nodes.Document jsoupParse(String data)
    {
        return org.jsoup.Jsoup.parse(data);
    }

}