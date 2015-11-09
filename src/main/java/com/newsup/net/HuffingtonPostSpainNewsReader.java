package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.kernel.list.Tags;
import com.newsup.task.Socket;
import com.newsup.task.TaskMessage;

import org.jsoup.select.Elements;

import java.util.ArrayList;

public class HuffingtonPostSpainNewsReader extends NewsReader {

    public HuffingtonPostSpainNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("España", 0, "http://www.huffingtonpost.es/feeds/verticals/spain/news.xml"));
        SECTIONS.add(new Section("Cataluña", 0, "http://www.huffingtonpost.es/news/cataluna/feed//"));
        SECTIONS.add(new Section("Política", 0, "http://www.huffingtonpost.es/news/es-politica/feed/"));
        SECTIONS.add(new Section("Economía", 0, "http://www.huffingtonpost.es/news/es-economia/feed/"));
        SECTIONS.add(new Section("Internacional", 0, "http://www.huffingtonpost.es/news/es-internacional/feed/"));
        SECTIONS.add(new Section("Tendencias", 0, "http://www.huffingtonpost.es/news/es-tendencias/feed/"));
        SECTIONS.add(new Section("Ciencia", 0, "http://www.huffingtonpost.es/news/es-ciencia/feed/"));
        SECTIONS.add(new Section("Tecnología", 0, "http://www.huffingtonpost.es/news/es-tecnologia/feed/"));
        SECTIONS.add(new Section("Deportes", 0, "http://www.huffingtonpost.es/news/es-deportes/feed/"));
        SECTIONS.add(new Section("Gente", 0, "http://www.huffingtonpost.es/news/es-gente/feed/"));
        SECTIONS.add(new Section("Asi sí", 0, "http://www.huffingtonpost.es/news/asi-si/feed/"));
        SECTIONS.add(new Section("Exprime la vida", 0, "http://www.huffingtonpost.es/news/exprime-la-vida/feed/"));
        SECTIONS.add(new Section("Lo más visto", 0, "http://www.huffingtonpost.es/news/lo-mas-visto/feed/"));
        SECTIONS.add(new Section("Sociedad", 0, "http://www.huffingtonpost.es/news/sociedad/feed/"));
        SECTIONS.add(new Section("Ñam Ñam", 0, "http://www.huffingtonpost.es/news/comida-y-bebida/feed/"));
        SECTIONS.add(new Section("Televisión", 0, "http://www.huffingtonpost.es/news/es-television/feed/"));
        SECTIONS.add(new Section("Lujo", 0, "http://www.huffingtonpost.es/news/lujo/feed/"));
        SECTIONS.add(new Section("Cultura", 0, "http://www.huffingtonpost.es/news/es-cultura/feed/"));
        SECTIONS.add(new Section("Libros", 0, "http://www.huffingtonpost.es/news/libros/feed/"));
        SECTIONS.add(new Section("Viajes", 0, "http://www.huffingtonpost.es/news/virales/feed/"));
        SECTIONS.add(new Section("Virales", 0, "http://www.huffingtonpost.es/news/viajes/feed/"));
        SECTIONS.add(new Section("Animales", 0, "http://www.huffingtonpost.es/news/es-animales/feed/"));
        SECTIONS.add(new Section("Blogs", 0, "http://www.huffingtonpost.es/feeds/verticals/spain/blog.xml"));

    }

    protected static final int HASH_DESCRIPTION_2 = "content".hashCode();
    protected static final int HASH_UPDATED = "updated".hashCode();
    protected static final int HASH_LINK_2 = "id".hashCode();

    @Override
    protected void readRssPage(Socket handler, String rsslink) {
        org.jsoup.nodes.Document doc = getDocument(rsslink);
        if (doc == null) return;

        Elements items = doc.select("item,entry");
        for (org.jsoup.nodes.Element item : items) {
            String title = "", link = "", description = "", date = "", content = "";
            ArrayList<String> categories = new ArrayList<String>();
            Elements props = item.getAllElements();

            //TODO Arraylist de opciones que se van quitando y lo hace mas eficiente
            for (org.jsoup.nodes.Element prop : props) {
                int taghash = prop.tagName().hashCode();

                if (taghash == HASH_TITLE) {
                    title = prop.text();
                    continue;
                }
                if (taghash == HASH_LINK_2 || taghash == HASH_GUIDED) {
                    link = prop.text();
                    continue;
                }
                if (taghash == HASH_DATE_1 || taghash == HASH_UPDATED) {
                    date = prop.text();
                    continue;
                }
                if (taghash == HASH_DESCRIPTION_2) {
                    description = prop.text();
                    continue;
                }
                if (taghash == HASH_CATEGORY) {
                    categories.add(prop.text());
                    continue;
                }
                if (taghash == HASH_DESCRIPTION) {
                    content = prop.text();
                }
            }
            News news = new News(title, link, description, date, new Tags(categories));
            news = applySpecialCase(news, content);
            handler.message(TaskMessage.NEWS_READ, news);
        }
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        if (!news.description.isEmpty()) {
            String description = org.jsoup.Jsoup.parse(news.description).text();
            int index = description.indexOf("Leer más:");
            if (index != -1) {
                news.description = description.substring(0, index);
            }
            return news;
        }
        if (!content.isEmpty()) {
            org.jsoup.nodes.Element body = org.jsoup.Jsoup.parse(content).getElementsByTag("body").get(0);
            org.jsoup.select.Elements ee = body.children();

            int index = ee.indexOf(ee.select("blockquote").last()) - 1;
            if (index >= 0) {
                for (; index < ee.size(); ++index) {
                    ee.get(index).remove();
                }
            }
            content = body.outerHtml();
            index = content.indexOf("<hh--");
            if (index != -1) {
                content = content.substring(0, index);
            }

            news.content = content;
            if (news.title.contains("Fotos: Pol")) {
                System.out.println(news.content);
            }
        }
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        doc.select("script,.entry-text > a").remove();

        org.jsoup.select.Elements cnt = doc.select(".top-media,.entry-text");
        cnt.select(".tag-cloud,[style^=\"display: n\"],.comment-button,.read-more,.corrections,.extra-content,.slideshow-thumb").remove();

        org.jsoup.nodes.Element e = cnt.select("blockquote").last();
        if (e != null) {
            String bq = e.html();
            if (bq.contains("ADEMÁS") || bq.contains("TE PUEDE INTERESAR") ||
                    bq.contains("AVÍSANOS") || bq.contains("MÁS SOBRE") ||
                    bq.contains("MÁS PARA")) {
                e.remove();
            }
        }
        e = cnt.select("p").last();
        if (e != null) {
            if (e.html().contains("big.assets.huffingtonpost")) {
                e.remove();
            }
        }
        cnt.select("img[src^=\"http://big.assets\"]");

        news.content = cnt.html();
    }

}