package com.lucevent.newsup.data.util;

import org.jsoup.select.Elements;

import java.io.IOException;

public abstract class NewsReader {

    public static final String USER_AGENT = "Mozilla/5.0 (Linux; Android 4.4.2; GT-I9300 Build/KVT49L) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.94 Mobile Safari/537.36";

    protected int HASH_TITLE = "title".hashCode();
    protected int HASH_LINK = "link".hashCode();
    protected int HASH_GUIDED = "guid".hashCode();
    protected int HASH_DATE_1 = "pubdate".hashCode();
    protected int HASH_DATE_2 = "dc:date".hashCode();
    protected int HASH_DESCRIPTION = "description".hashCode();
    protected int HASH_CATEGORY = "category".hashCode();
    protected int HASH_CONTENT = "content:encoded".hashCode();
    protected int HASH_ENCLOSURE = "enclosure".hashCode();

    public NewsReader() {
    }

    public final NewsArray readNewsHeaders(String header_url) {
        return readRssPage(header_url);
    }

    protected NewsArray readRssPage(String rsslink) {
        org.jsoup.nodes.Document doc = getDocument(rsslink);
        if (doc == null) return new NewsArray();

        NewsArray result = new NewsArray();

        Elements items = doc.select("item");
        for (org.jsoup.nodes.Element item : items) {
            String title = "", link = "", guided = "", description = "", date = "", content = "";
            Tags tags = new Tags();
            Enclosures enclosures = new Enclosures();

            //TODO Arraylist de opciones que se van quitando y lo hace mas eficiente
            Elements props = item.getAllElements();
            for (org.jsoup.nodes.Element prop : props) {
                int taghash = prop.tagName().hashCode();

                if (taghash == HASH_ENCLOSURE) {
                    enclosures.add(new Enclosure(prop.attr("url"), prop.attr("type"), prop.attr("length")));
                    continue;
                }
                if (taghash == HASH_TITLE) {
                    title = prop.text();
                    continue;
                }
                if (taghash == HASH_LINK) {
                    link = prop.text();
                    int index = link.indexOf('#');
                    if (index != -1) {
                        link = link.split("#")[0];
                    }
                    index = link.indexOf('?');
                    if (index != -1) {
                        link = link.split("\\?")[0];
                    }
                    continue;
                }
                if (taghash == HASH_DATE_1 || taghash == HASH_DATE_2) {
                    date = prop.text();
                    continue;
                }
                if (taghash == HASH_DESCRIPTION) {
                    description = prop.text();
                    continue;
                }
                if (taghash == HASH_CATEGORY) {
                    tags.add(prop.text());
                    continue;
                }
                if (taghash == HASH_GUIDED) {
                    guided = prop.text();
                    int index = guided.indexOf('#');
                    if (index != -1) {
                        guided = guided.split("#")[0];
                    }
                    index = guided.indexOf('?');
                    if (index != -1) {
                        guided = guided.split("\\?")[0];
                    }
                    continue;
                }
                if (taghash == HASH_CONTENT) {
                    content = prop.text();
                }
            }
            if (!title.isEmpty()) {
                News news = new News(title, link.isEmpty() ? guided : link, description, date, tags);
                news.enclosures = enclosures;

                news = applySpecialCase(news, content);
                result.add(news);
            }
        }
        return result;
    }

    protected News applySpecialCase(News news, String content) {
        return news;
    }

    protected org.jsoup.nodes.Document getDocument(String pagelink) {
        try {
            return org.jsoup.Jsoup.connect(pagelink).userAgent(USER_AGENT).get();
        } catch (IOException e) {
            debug("[" + e.getClass().getSimpleName() + "] Intentando nuevamente");
        }
        try {
            return org.jsoup.Jsoup.connect(pagelink).get();
        } catch (IOException e) {
            debug("[" + e.getClass().getSimpleName() + "] No se ha podido leer: " + pagelink);
        }
        return null;
    }

    public News readNewsContent(News news) {
        org.jsoup.nodes.Document doc = getDocument(news.link);
        if (doc != null) {
            readNewsContent(doc, news);
        }
        return news;
    }

    protected void readNewsContent(org.jsoup.nodes.Document document, News news) {
        //To implement by Subclasses in case they need
    }

    protected final void debug(String text) {
        System.out.println("[#" + this.getClass().getSimpleName() + "#]" + text);
    }

}
