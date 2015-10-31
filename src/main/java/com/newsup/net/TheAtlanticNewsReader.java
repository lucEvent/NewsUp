package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.kernel.list.Tags;
import com.newsup.task.Socket;
import com.newsup.task.TaskMessage;

import org.jsoup.select.Elements;

import java.util.ArrayList;

public class TheAtlanticNewsReader extends NewsReader {

    protected static final int HASH_UPDATED = "updated".hashCode();
    protected static final int HASH_SUMMARY = "summary".hashCode();
    protected static final int HASH_CONTENT_2 = "content".hashCode();
    protected static final int HASH_ORIGLINK = "feedburner:origlink".hashCode();

    public TheAtlanticNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("The Atlantic", 0, "http://feeds.feedburner.com/TheAtlantic"));
        SECTIONS.add(new Section("Politics", 1, "http://feeds.feedburner.com/AtlanticPoliticsChannel"));
        SECTIONS.add(new Section("Business", 1, "http://feeds.feedburner.com/AtlanticBusinessChannel"));
        SECTIONS.add(new Section("Culture", 1, "http://feeds.feedburner.com/AtlanticCulture"));
        SECTIONS.add(new Section("Global", 1, "http://feeds.feedburner.com/AtlanticInternational"));
        SECTIONS.add(new Section("Technology", 1, "http://feeds.feedburner.com/AtlanticScienceAndTechnology"));
        SECTIONS.add(new Section("U.S.", 1, "http://feeds.feedburner.com/AtlanticNational"));
        SECTIONS.add(new Section("Health", 1, "http://feeds.feedburner.com/AtlanticFood"));
        SECTIONS.add(new Section("Video", 1, "http://feeds.feedburner.com/AtlanticVideo"));
        SECTIONS.add(new Section("Education", 1, "http://feeds.feedburner.com/AtlanticEducationChannel"));
        SECTIONS.add(new Section("Science", 1, "http://feeds.feedburner.com/AtlanticScienceChannel"));
        SECTIONS.add(new Section("Photo", 1, "http://feeds.feedburner.com/theatlantic/infocus"));
        SECTIONS.add(new Section("Notes", 1, "http://feeds.feedburner.com/TheAtlanticNotes"));

        SECTIONS.add(new Section("The Wire", 0, "http://feeds.feedburner.com/TheAtlanticWire"));
        SECTIONS.add(new Section("CityLab", 0, "http://feeds.feedburner.com/TheAtlanticCities"));

    }

    @Override
    protected void readRssPage(Socket handler, String rsslink) {
        org.jsoup.nodes.Document doc;
        try {
            doc = getDocument(rsslink);
        } catch (Exception e) {
            debug("[ERROR No se puede leer el link RSS] link:" + rsslink);
            e.printStackTrace();
            return;
        }

        Elements items = doc.select("item,entry");
        for (org.jsoup.nodes.Element item : items) {
            String title = "", link = "", description = "", date = "", content = "";
            ArrayList<String> categories = new ArrayList<String>();

            //TODO Arraylist de opciones que se van quitando y lo hace mas eficiente
            Elements props = item.getAllElements();
            for (org.jsoup.nodes.Element prop : props) {
                int taghash = prop.tagName().hashCode();

                if (taghash == HASH_TITLE) {
                    title = prop.text().replace("<em>","").replace("</em>","").replace("<i>","").replace("</i>","");
                    continue;
                }
                if ((taghash == HASH_LINK || taghash == HASH_ORIGLINK) && link.isEmpty()) {
                    link = prop.text();
                    continue;
                }
                if (taghash == HASH_DATE_1 || taghash == HASH_UPDATED) {
                    date = prop.text();
                    continue;
                }
                if (taghash == HASH_DESCRIPTION || taghash == HASH_SUMMARY) {
                    description = prop.text();
                    continue;
                }
                if (taghash == HASH_CATEGORY) {
                    String cat = prop.text();
                    if (cat.isEmpty()) {
                        cat = prop.attr("term");
                    }
                    categories.add(cat);
                    continue;
                }
                if (taghash == HASH_CONTENT || taghash == HASH_CONTENT_2) {
                    content = prop.text();
                }
            }
            if (!title.isEmpty()) {
                News news = new News(title, link, description, date, new Tags(categories));
                news = applySpecialCase(news, content);
                handler.message(TaskMessage.NEWS_READ, news);
            }
        }
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text();

        if (!content.isEmpty()) {
            org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(content);
            doc.select("[clear=\"all\"] ~ *,[clear=\"all\"],.callout,.partner-box,img[height=\"1\"]").remove();
            news.content = doc.html();
        }
        return news;
    }

    @Override
    public News readNewsContent(News news) {
        try {
            org.jsoup.nodes.Document doc = getDocument(news.link);
            if (doc == null) return news;

            org.jsoup.select.Elements e = doc.select(".embed-code,[itemprop=\"description\"]");

            if (!e.isEmpty()) {
                news.content = e.html().replace("&lt;", "<").replace("&quot;", "\"").replace("&gt;", ">");
            } else {

                e = doc.select("picture > img,.caption");
                if (!e.isEmpty()) {
                    news.content = e.outerHtml().replace("data-src", "src");
                } else {
                    System.out.println("NO SE HA PODIDO LEER EL CONTENIDO:: " + news.link);
                }
            }
        } catch (Exception e) {
            debug("[ERROR] link:" + news.link);
            e.printStackTrace();
        }
        return news;
    }

}
