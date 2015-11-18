package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Site;
import com.newsup.kernel.list.SectionList;
import com.newsup.kernel.list.Tags;
import com.newsup.task.Socket;
import com.newsup.task.TaskMessage;

import java.util.Arrays;

public class NewsReader {

    private static final String query_index = "http://newsup-2406.appspot.com/request?index&site=";
    private static final String query_content = "http://newsup-2406.appspot.com/request?content&site=";

    public SectionList SECTIONS;

    public NewsReader() {
    }

    public final void readNews(Site site, int[] sections, Socket handler) {

        String query = query_index + site.name + Arrays.toString(sections).replace(" ", "");
        System.out.println("La query es: " + query);

        readRssPage(handler, query);
        /*
        for (int isection : sections) {
            debug("Leyendo: " + this.getClass().getSimpleName() + ". Section: " + SECTIONS.get(isection).name);
            Section section = SECTIONS.get(isection);
            readRssPage(handler, section.link);
        }*/

    }

    private static final int HASH_TITLE = "title".hashCode();
    private static final int HASH_LINK = "link".hashCode();
    private static final int HASH_DATE = "date".hashCode();
    private static final int HASH_DESCRIPTION = "description".hashCode();
    private static final int HASH_CATEGORIES = "categories".hashCode();
    private static final int HASH_CONTENT = "content".hashCode();

    protected void readRssPage(Socket handler, String rsslink) {
        org.jsoup.nodes.Document doc = getDocument(rsslink);
        if (doc == null) return;
        System.out.println("Doc:"+doc.outerHtml());
        System.out.println("############################");
        System.out.println("############################");
        System.out.println("############################");
        System.out.println("############################");

        int deb = 0;
        for (org.jsoup.nodes.Element item : doc.select("item")) {
            String title = "", link = "", description = "", content = "", categories = "";
            long date = 0;

            if (deb == 0) {
                deb++;
                System.out.println("Item:"+item.outerHtml());
                System.out.println("##################3");
            }
            for (org.jsoup.nodes.Element prop : item.children()) {
                int taghash = prop.tagName().hashCode();

                if (taghash == HASH_TITLE) {
                    title = prop.html();
                    continue;
                }
                if (taghash == HASH_LINK) {
                    System.out.println("Link:"+prop.outerHtml());
                    link = prop.html();
                    continue;
                }
                if (taghash == HASH_DATE) {
                    date = Long.parseLong(prop.html());
                    continue;
                }
                if (taghash == HASH_DESCRIPTION) {
                    description = prop.html();
                    continue;
                }
                if (taghash == HASH_CATEGORIES) {
                    categories = prop.html();
                    continue;
                }
                if (taghash == HASH_CONTENT) {
                    content = prop.html();
                }
            }
            if (!title.isEmpty()) {
                News news = new News(title, link, description, date, new Tags(categories));

                if (!content.isEmpty()) {

                    news.content = content;
                }
                handler.message(TaskMessage.NEWS_READ, news);
            }
        }
    }

    protected org.jsoup.nodes.Document getDocument(String pagelink) {
        try {
            return org.jsoup.Jsoup.connect(pagelink).get();
        } catch (Exception e) {
            debug("[" + e.getClass().getSimpleName() + "] Intentando nuevamente");
        }
        try {
            String ua = "Mozilla/5.0 (Linux; Android 4.4.2; GT-I9300 Build/KVT49L) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.94 Mobile Safari/537.36";
            return org.jsoup.Jsoup.connect(pagelink).userAgent(ua).get();
        } catch (java.net.SocketTimeoutException e) {
            debug("SocketTimeoutException con: " + pagelink);
        } catch (Exception e) {
            debug("[" + e.getClass().getSimpleName() + "] No se ha podido leer: " + pagelink);
        }
        return null;
    }

    public final News readNewsContent(Site site, News news) {

        String query = query_content + site.name + "&date=" + news.date + "&link=" + news.link;
        System.out.println("Esque el link es:"+news.link);
        debug(query);
        org.jsoup.nodes.Document doc = getDocument(query);
        if (doc != null) {
            String content = doc.html();

            if (!content.isEmpty()) {
                news.content = content;
            } else {
                debug("[NO SE HA ENCONTRADO EL CONTENIDO] " + news.link);
            }
        }
        return news;
    }

    protected final void debug(String text) {
        android.util.Log.d("##" + this.getClass().getSimpleName() + "##", text);
    }

}