package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Tags;

import org.jsoup.select.Elements;

public class HuffingtonPostUK extends com.lucevent.newsup.data.util.NewsReader {

    public HuffingtonPostUK() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        if (!content.isEmpty()) {
            org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(content.replace("<br />", "<p></p>"));

            org.jsoup.select.Elements ads = doc.select("strong");
            for (org.jsoup.nodes.Element ad : ads) {
                if (ad.text().contains("SEE ALSO:")) {
                    ad.parent().remove();
                } else if (ad.text().contains("READ MORE:")) {
                    ad.remove();
                }
            }
            doc.select("ul").remove();

            String aux = doc.html();
            int index = aux.indexOf("<hh-");
            if (index != -1) {
                int indexM = aux.indexOf("-hh>", index + 4);
                int index2 = aux.indexOf("-hh>", indexM + 4);
                news.content = aux.replace(aux.substring(index, index2 + 4), "");
            } else {
                news.content = aux;
            }
        }
        if (!news.description.isEmpty()) {
            news.description = org.jsoup.Jsoup.parse(news.description).text();
            int index = news.description.indexOf("...");
            if (index != -1) {
                news.description = news.description.substring(0, index);
            }
        }
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        org.jsoup.select.Elements content = doc.select(".feature-section");

        if (!content.isEmpty()) {
            doc.select(".feature-section > section,.feature-section > div").remove();
        } else {
            doc.select(".entry__body > section,.entry__body > div").remove();

            content = doc.select(".entry__body");
        }

        org.jsoup.select.Elements ads = content.select("strong");
        for (org.jsoup.nodes.Element ad : ads) {
            String adtext = ad.text();
            if (adtext.contains("SEE ALSO:") || adtext.contains("READ MORE:")
                    || adtext.contains("LIKE US ON FACEBOOK")) {
                ad.parent().remove();
            }
        }
        content.select("ul,script,.slideshow-thumb").remove();

        news.content = content.html();
    }

    protected static final int HASH_ID = "id".hashCode();
    protected static final int HASH_UPDATED = "updated".hashCode();
    protected static final int HASH_CONTENT = "content".hashCode();

    @Override
    protected NewsArray readRssPage(String rsslink) {
        org.jsoup.nodes.Document doc = getDocument(rsslink);
        if (doc == null) return new NewsArray();

        NewsArray result = new NewsArray();

        Elements items = doc.select("item,entry");
        for (org.jsoup.nodes.Element item : items) {
            String title = "", link = "", description = "", date = "", content = "";
            Elements props = item.getAllElements();

            //TODO Arraylist de opciones que se van quitando y lo hace mas eficiente
            for (org.jsoup.nodes.Element prop : props) {
                int taghash = prop.tagName().hashCode();

                if (taghash == HASH_TITLE) {
                    title = prop.text();
                    continue;
                }
                if (taghash == HASH_GUIDED || taghash == HASH_ID) {
                    link = prop.text();
                    continue;
                }
                if (taghash == HASH_DATE_1 || taghash == HASH_UPDATED) {
                    date = prop.text();
                    continue;
                }
                if (taghash == HASH_DESCRIPTION) {
                    content = prop.text();
                    continue;
                }
                if (taghash == HASH_CONTENT) {
                    description = prop.text();
                }
            }

            if (!title.isEmpty()) {
                News news = new News(title, link, description, date, new Tags());
                news = applySpecialCase(news, content);
                result.add(news);
            }
        }
        return result;
    }

}