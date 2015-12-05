package com.backend.net;

import com.backend.kernel.BE_Enclosure;
import com.backend.kernel.BE_News;
import com.backend.kernel.list.BE_NewsList;
import com.backend.kernel.list.BE_Sections;

import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public abstract class BE_NewsReader {

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

    private final boolean catchEnclosures;
    public BE_Sections SECTIONS;

    public BE_NewsReader() {
        this(false);
    }

    public BE_NewsReader(boolean catchEnclosures) {
        this.catchEnclosures = catchEnclosures;
    }

    public final BE_NewsList readNews(int[] sections) {

        BE_NewsList res = new BE_NewsList();
        for (int isection : sections) {
            BE_NewsList section_news = readRssPage(SECTIONS.get(isection).link);
            res.addAll(section_news);
        }
        return res;
    }

    protected BE_NewsList readRssPage(String rsslink) {
        org.jsoup.nodes.Document doc = getDocument(rsslink);
        if (doc == null) return new BE_NewsList();

        BE_NewsList result = new BE_NewsList();

        Elements items = doc.select("item");
        for (org.jsoup.nodes.Element item : items) {
            String title = "", link = "", guided = "", description = "", date = "", content = "";
            ArrayList<String> categories = new ArrayList<>();
            ArrayList<BE_Enclosure> enclosures = new ArrayList<>();

            //TODO Arraylist de opciones que se van quitando y lo hace mas eficiente
            Elements props = item.getAllElements();
            for (org.jsoup.nodes.Element prop : props) {
                int taghash = prop.tagName().hashCode();

                if (taghash == HASH_ENCLOSURE && catchEnclosures) {
                    enclosures.add(new BE_Enclosure(prop.attr("url"), prop.attr("type"), prop.attr("length")));
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
                    categories.add(prop.text());
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
                BE_News news = new BE_News(title, link.isEmpty() ? guided : link, description, date, categories);
                if (catchEnclosures) {
                    news.enclosures = enclosures;
                }
                news = applySpecialCase(news, content);
                result.add(news);
            }
        }
        return result;
    }

    protected BE_News applySpecialCase(BE_News news, String content) {
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

    public BE_News readNewsContent(BE_News news) {
        org.jsoup.nodes.Document doc = getDocument(news.link);
        if (doc != null) {
            readNewsContent(doc, news);
        }
        return news;
    }

    protected void readNewsContent(org.jsoup.nodes.Document document, BE_News news) {
        //To implement by Subclasses in case they need
    }

    protected final void debug(String text) {
        System.out.println("[#" + this.getClass().getSimpleName() + "#]" + text);
    }

}
