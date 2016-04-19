package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Tags;

public class HuffingtonPostSpain extends com.lucevent.newsup.data.util.NewsReader {

    public HuffingtonPostSpain() {
        super();
    }

    protected static int HASH_DESCRIPTION_2 = "content".hashCode();
    protected static int HASH_UPDATED = "updated".hashCode();
    protected static int HASH_LINK_2 = "id".hashCode();

    @Override
    protected NewsArray readRssPage(String rsslink) {
        org.jsoup.nodes.Document doc = getDocument(rsslink);
        if (doc == null) return new NewsArray();

        NewsArray result = new NewsArray();

        org.jsoup.select.Elements items = doc.select("item,entry");
        for (org.jsoup.nodes.Element item : items) {
            String title = "", link = "", description = "", date = "", content = "";
            Tags tags = new Tags();
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
                    tags.add(prop.text());
                    continue;
                }
                if (taghash == HASH_DESCRIPTION) {
                    content = prop.text();
                }
            }
            News news = new News(title, link, description, date, tags);
            news = applySpecialCase(news, content);
            result.add(news);
        }
        return result;
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        if (!news.description.isEmpty()) {
            String description = org.jsoup.Jsoup.parse(news.description).text();
            int index = description.indexOf("Leer m�s:");
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
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        doc.select("script,.entry-text > a").remove();

        org.jsoup.select.Elements cnt = doc.select(".top-media,.entry-text");
        cnt.select(".tag-cloud,[style^=\"display: n\"],.comment-button,.read-more,.corrections,.extra-content,.slideshow-thumb").remove();

        org.jsoup.nodes.Element e = cnt.select("blockquote").last();
        if (e != null) {
            String bq = e.html();
            if (bq.contains("ADEM�S") || bq.contains("TE PUEDE INTERESAR") ||
                    bq.contains("AV�SANOS") || bq.contains("M�S SOBRE") ||
                    bq.contains("M�S PARA")) {
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