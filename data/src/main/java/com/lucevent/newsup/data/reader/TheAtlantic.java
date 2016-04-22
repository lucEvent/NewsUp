package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Tags;

public class TheAtlantic extends com.lucevent.newsup.data.util.NewsReader {

    protected static final int HASH_PUBLISHED = "published".hashCode();
    protected static final int HASH_SUMMARY = "summary".hashCode();
    protected static final int HASH_CONTENT_2 = "content".hashCode();
    protected static final int HASH_ORIGLINK = "feedburner:origlink".hashCode();

    public TheAtlantic()
    {
        super();
    }

    @Override
    protected NewsArray readRssPage(String rsslink)
    {
        org.jsoup.nodes.Document doc = getDocument(rsslink);
        if (doc == null) return new NewsArray();

        NewsArray result = new NewsArray();

        org.jsoup.select.Elements items = doc.select("item,entry");
        for (org.jsoup.nodes.Element item : items) {
            String title = "", link = "", description = "", date = "", content = "";
            Tags tags = new Tags();

            //TODO Arraylist de opciones que se van quitando y lo hace mas eficiente
            org.jsoup.select.Elements props = item.getAllElements();
            for (org.jsoup.nodes.Element prop : props) {
                int taghash = prop.tagName().hashCode();

                if (taghash == HASH_TITLE) {
                    title = prop.text().replace("<em>", "").replace("</em>", "").replace("<i>", "").replace("</i>", "");
                    continue;
                }
                if ((taghash == HASH_LINK || taghash == HASH_ORIGLINK) && link.isEmpty()) {
                    link = prop.text();
                    continue;
                }
                if (taghash == HASH_DATE_1 || taghash == HASH_PUBLISHED) {
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
                    tags.add(cat);
                    continue;
                }
                if (taghash == HASH_CONTENT || taghash == HASH_CONTENT_2) {
                    content = prop.text();
                }
            }
            if (!title.isEmpty()) {
                News news = new News(title, link, description, date, tags);
                news = applySpecialCase(news, content);
                result.add(news);
            }
        }
        return result;
    }

    @Override
    protected News applySpecialCase(News news, String content)
    {
        news.description = org.jsoup.Jsoup.parse(news.description).text();

        if (!content.isEmpty()) {
            org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(content);
            doc.select("[clear=\"all\"] ~ *,[clear=\"all\"],.callout,.partner-box,img[height=\"1\"]").remove();
            news.content = doc.html();
        }
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        org.jsoup.select.Elements e = doc.select(".embed-code,[itemprop=\"description\"]");

        if (!e.isEmpty()) {
            news.content = e.html().replace("&lt;", "<").replace("&quot;", "\"").replace("&gt;", ">");
        } else {

            e = doc.select("picture > img,.caption");
            if (!e.isEmpty()) {
                news.content = e.outerHtml().replace("data-src", "src");
            }
        }
    }

}
