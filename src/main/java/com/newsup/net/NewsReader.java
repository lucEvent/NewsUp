package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.kernel.list.Tags;
import com.newsup.task.Socket;
import com.newsup.task.TaskMessage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public abstract class NewsReader {

    public SectionList SECTIONS;

    public NewsReader() {
    }

    public final void readNews(int[] sections, Socket handler) {

        for (int isection : sections) {
            debug("Leyendo: " + this.getClass().getSimpleName() + ". Section: " + SECTIONS.get(isection).name);
            Section section = SECTIONS.get(isection);
            handler.message(TaskMessage.SECTION_BEGIN, section.name);
            readRssPage(handler, section.link);
        }

    }

    protected void readRssPage(Socket handler, String rsslink) {
        org.jsoup.nodes.Document doc;
        try {
            doc = getDocument(rsslink);
        } catch (Exception e) {
            debug("[ERROR No se puede leer el link RSS] link:" + rsslink);
            e.printStackTrace();
            return;
        }
        int titlehash = "title".hashCode();
        int linkhash = "link".hashCode();
        int datehash = "pubdate".hashCode();
        int date2hash = "dc:date".hashCode();
        int descrhash = "description".hashCode();
        int categhash = "category".hashCode();
        int guidhash = "guid".hashCode();

        Elements items = doc.getElementsByTag("item");

        for (org.jsoup.nodes.Element item : items) {
            String title = "", link = "", description = "", date = "";
            ArrayList<String> categoriesList = new ArrayList<String>();
            Elements props = item.getAllElements();

            //TODO Arraylist de opciones que se van quitando y lo hace mas eficiente
            for (org.jsoup.nodes.Element prop : props) {
                int taghash = prop.tagName().hashCode();
                if (taghash == titlehash) {
                    title = prop.text();
                    continue;
                }
                if (taghash == linkhash) {
                    link = prop.text();
                    continue;
                }
                if (taghash == datehash || taghash == date2hash) {
                    date = prop.text();
                    continue;
                }
                if (taghash == descrhash) {
                    description = prop.text();
                    continue;
                }
                if (taghash == categhash) {
                    categoriesList.add(prop.text());
                    continue;
                }
                if (taghash == guidhash) {
                    if (link.isEmpty()) {
                        link = prop.text();
                    }
                }
            }
            News news = getNewsLastFilter(title, link, description, date, new Tags(categoriesList));
            handler.message(TaskMessage.NEWS_READ, news);
        }
    }

    protected News getNewsLastFilter(String title, String link, String description, String date, Tags categories) {
        return new News(title, link, description, date, categories);
    }

    protected Document getDocument(String pagelink) throws IOException {
        try {
            return Jsoup.connect(pagelink).get();
        } catch (Exception e) {
            debug("Fallo de la conexión. Intentando nuevamente");
        }
        try {
            return Jsoup.connect(pagelink).userAgent("Mozilla/5.0 (Linux; Android 4.4.2; GT-I9300 Build/KVT49L) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.94 Mobile Safari/537.36").get();
        } catch (java.net.SocketTimeoutException e) {
            debug("No se ha podido encontrar la pagina: " + pagelink);
        }
        return null;
    }

    public abstract News readNewsContent(News news);

    protected final void debug(String text) {
        android.util.Log.d("##" + this.getClass().getSimpleName() + "##", text);
    }


}
