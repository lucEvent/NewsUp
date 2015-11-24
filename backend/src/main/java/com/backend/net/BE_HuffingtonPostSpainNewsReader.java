package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_NewsList;
import com.backend.kernel.list.BE_Sections;

import java.util.ArrayList;

public class BE_HuffingtonPostSpainNewsReader extends BE_NewsReader {

    public BE_HuffingtonPostSpainNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("España", "http://www.huffingtonpost.es/feeds/verticals/spain/news.xml"));
        SECTIONS.add(new BE_Section("Cataluña", "http://www.huffingtonpost.es/news/cataluna/feed//"));
        SECTIONS.add(new BE_Section("Política", "http://www.huffingtonpost.es/news/es-politica/feed/"));
        SECTIONS.add(new BE_Section("Economía", "http://www.huffingtonpost.es/news/es-economia/feed/"));
        SECTIONS.add(new BE_Section("Internacional", "http://www.huffingtonpost.es/news/es-internacional/feed/"));
        SECTIONS.add(new BE_Section("Tendencias", "http://www.huffingtonpost.es/news/es-tendencias/feed/"));
        SECTIONS.add(new BE_Section("Ciencia", "http://www.huffingtonpost.es/news/es-ciencia/feed/"));
        SECTIONS.add(new BE_Section("Tecnología", "http://www.huffingtonpost.es/news/es-tecnologia/feed/"));
        SECTIONS.add(new BE_Section("Deportes", "http://www.huffingtonpost.es/news/es-deportes/feed/"));
        SECTIONS.add(new BE_Section("Gente", "http://www.huffingtonpost.es/news/es-gente/feed/"));
        SECTIONS.add(new BE_Section("Asi sí", "http://www.huffingtonpost.es/news/asi-si/feed/"));
        SECTIONS.add(new BE_Section("Exprime la vida", "http://www.huffingtonpost.es/news/exprime-la-vida/feed/"));
        SECTIONS.add(new BE_Section("Lo más visto", "http://www.huffingtonpost.es/news/lo-mas-visto/feed/"));
        SECTIONS.add(new BE_Section("Sociedad", "http://www.huffingtonpost.es/news/sociedad/feed/"));
        SECTIONS.add(new BE_Section("Ñam Ñam", "http://www.huffingtonpost.es/news/comida-y-bebida/feed/"));
        SECTIONS.add(new BE_Section("Televisión", "http://www.huffingtonpost.es/news/es-television/feed/"));
        SECTIONS.add(new BE_Section("Lujo", "http://www.huffingtonpost.es/news/lujo/feed/"));
        SECTIONS.add(new BE_Section("Cultura", "http://www.huffingtonpost.es/news/es-cultura/feed/"));
        SECTIONS.add(new BE_Section("Libros", "http://www.huffingtonpost.es/news/libros/feed/"));
        SECTIONS.add(new BE_Section("Viajes", "http://www.huffingtonpost.es/news/virales/feed/"));
        SECTIONS.add(new BE_Section("Virales", "http://www.huffingtonpost.es/news/viajes/feed/"));
        SECTIONS.add(new BE_Section("Animales", "http://www.huffingtonpost.es/news/es-animales/feed/"));
        SECTIONS.add(new BE_Section("Blogs", "http://www.huffingtonpost.es/feeds/verticals/spain/blog.xml"));

    }

    protected static int HASH_DESCRIPTION_2 = "content".hashCode();
    protected static int HASH_UPDATED = "updated".hashCode();
    protected static int HASH_LINK_2 = "id".hashCode();

    @Override
    protected BE_NewsList readRssPage(String rsslink) {
        org.jsoup.nodes.Document doc = getDocument(rsslink);
        if (doc == null) return new BE_NewsList();

        BE_NewsList result = new BE_NewsList();

        org.jsoup.select.Elements items = doc.select("item,entry");
        for (org.jsoup.nodes.Element item : items) {
            String title = "", link = "", description = "", date = "", content = "";
            ArrayList<String> categories = new ArrayList<String>();
            org.jsoup.select.Elements props = item.getAllElements();

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
            BE_News news = new BE_News(title, link, description, date, categories);
            news = applySpecialCase(news, content);
            result.add(news);
        }
        return result;
    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
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

        }
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
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