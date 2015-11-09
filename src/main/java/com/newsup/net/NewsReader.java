package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.kernel.list.Tags;
import com.newsup.net.util.Enclosure;
import com.newsup.task.Socket;
import com.newsup.task.TaskMessage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public abstract class NewsReader {

    protected static final int HASH_TITLE = "title".hashCode();
    protected static final int HASH_LINK = "link".hashCode();
    protected static final int HASH_GUIDED = "guid".hashCode();
    protected static final int HASH_DATE_1 = "pubdate".hashCode();
    protected static final int HASH_DATE_2 = "dc:date".hashCode();
    protected static final int HASH_DESCRIPTION = "description".hashCode();
    protected static final int HASH_CATEGORY = "category".hashCode();
    protected static final int HASH_CONTENT = "content:encoded".hashCode();
    protected static final int HASH_ENCLOSURE = "enclosure".hashCode();

    private final boolean catchEnclosures;
    public SectionList SECTIONS;

    public NewsReader() {
        this(false);
    }

    public NewsReader(boolean catchEnclosures) {
        this.catchEnclosures = catchEnclosures;
    }

    public final void readNews(int[] sections, Socket handler) {

        for (int isection : sections) {
            debug("Leyendo: " + this.getClass().getSimpleName() + ". Section: " + SECTIONS.get(isection).name);
            Section section = SECTIONS.get(isection);
            readRssPage(handler, section.link);
        }

    }

    protected void readRssPage(Socket handler, String rsslink) {
        org.jsoup.nodes.Document doc = getDocument(rsslink);
        if (doc == null) return;

        Elements items = doc.select("item");

        for (org.jsoup.nodes.Element item : items) {
            String title = "", link = "", guided = "", description = "", date = "", content = "";
            ArrayList<String> categories = new ArrayList<String>();
            ArrayList<Enclosure> enclosures = new ArrayList<Enclosure>();

            //TODO Arraylist de opciones que se van quitando y lo hace mas eficiente
            Elements props = item.getAllElements();
            for (org.jsoup.nodes.Element prop : props) {
                int taghash = prop.tagName().hashCode();

                if (taghash == HASH_ENCLOSURE && catchEnclosures) {
                    enclosures.add(new Enclosure(prop.attr("url"), prop.attr("type"), prop.attr("length")));
                    continue;
                }
                if (taghash == HASH_TITLE) {
                    title = prop.text();
                    continue;
                }
                if (taghash == HASH_LINK) {
                    link = prop.text();
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
                    continue;
                }
                if (taghash == HASH_CONTENT) {
                    content = prop.text();
                }
            }
            if (!title.isEmpty()) {
                News news = new News(title, link.isEmpty() ? guided : link, description, date, new Tags(categories));
                if (catchEnclosures) {
                    news.enclosures = enclosures;
                }
                news = applySpecialCase(news, content);
                handler.message(TaskMessage.NEWS_READ, news);
            }
        }
    }

    protected News applySpecialCase(News news, String content) {
        return news;
    }

    protected Document getDocument(String pagelink) {
        try {
            return Jsoup.connect(pagelink).get();
        } catch (Exception e) {
            debug("[" + e.getClass().getSimpleName() + "] Intentando nuevamente");
        }
        try {
            return Jsoup.connect(pagelink).userAgent("Mozilla/5.0 (Linux; Android 4.4.2; GT-I9300 Build/KVT49L) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.94 Mobile Safari/537.36").get();
        } catch (java.net.SocketTimeoutException e) {
            debug("SocketTimeoutException con: " + pagelink);
        } catch (Exception e) {
            debug("[" + e.getClass().getSimpleName() + "] No se ha podido leer: " + pagelink);
        }
        return null;
    }

    public final News readNewsContent(News news) {
        org.jsoup.nodes.Document doc = getDocument(news.link);
        if (doc != null) {
            readNewsContent(doc, news);
            if (news.content == null || news.content.isEmpty()) {
                debug("[NO SE HA ENCONTRADO EL CONTENIDO] " + news.link);
            }
        }
        return news;
    }

    protected void readNewsContent(org.jsoup.nodes.Document document, News news) {
        //To implement by Subclasses in case they need
    }

    protected final void debug(String text) {
        android.util.Log.d("##" + this.getClass().getSimpleName() + "##", text);
    }

}
