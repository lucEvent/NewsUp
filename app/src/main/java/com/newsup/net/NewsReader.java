package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Site;
import com.newsup.kernel.list.SectionList;
import com.newsup.kernel.list.Tags;
import com.newsup.task.Socket;
import com.newsup.task.TaskMessage;

public final class NewsReader {

    private static final String query_index = "http://newsup-2406.appspot.com/request?index&site=";
    private static final String query_content = "http://newsup-2406.appspot.com/request?content&site=";

    public SectionList SECTIONS;

    public NewsReader() {
    }

    public final void readNewsHeader(Site site, int[] sections, Socket handler) {

        StringBuilder sectArray = new StringBuilder(sections.length * 3);
        for (int section : sections) sectArray.append(',').append(section);

        String query = query_index + site.code + sectArray;
        System.out.println("La query es: " + query);

        readRssPage(handler, query);

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

        for (org.jsoup.nodes.Element item : doc.select("item")) {
            String title = "", link = "", description = "", content = "", categories = "";
            long date = 0;

            for (org.jsoup.nodes.Element prop : item.children()) {
                int taghash = prop.tagName().hashCode();

                if (taghash == HASH_TITLE) {
                    title = prop.html();
                    continue;
                }
                if (taghash == HASH_LINK) {
                    link = prop.html();
                    continue;
                }
                if (taghash == HASH_DATE) {
                    date = Long.parseLong(prop.html());
                    continue;
                }
                if (taghash == HASH_DESCRIPTION) {
                    description = prop.text();
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
            return org.jsoup.Jsoup.connect(pagelink).parser(org.jsoup.parser.Parser.xmlParser()).get();
        } catch (Exception e) {
            debug("[NewsReader] No se ha podido leer: " + pagelink);
            e.printStackTrace();
        }
        return null;
    }

    public final News readNewsContent(Site site, News news) {

        String query = query_content + site.code + "&date=" + news.date + "&link=" + news.link;

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