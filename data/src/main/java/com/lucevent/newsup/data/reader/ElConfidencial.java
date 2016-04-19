package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Tags;

public class ElConfidencial extends com.lucevent.newsup.data.util.NewsReader {

    private static final int EC_HASH_LINK = "id".hashCode();
    private static final int EC_HASH_DATE = "updated".hashCode();
    private static final int EC_HASH_DESCRIPTION = "summary".hashCode();
    private static final int EC_HASH_CONTENT = "content".hashCode();
    private static final int EC_HASH_MEDIA = "media:content".hashCode();

    public ElConfidencial() {
        super();
    }

    @Override
    protected NewsArray readRssPage(String rsslink) {
        org.jsoup.nodes.Document doc = getDocument(rsslink);
        if (doc == null) return new NewsArray();

        NewsArray result = new NewsArray();

        org.jsoup.select.Elements items = doc.getElementsByTag("entry");
        for (org.jsoup.nodes.Element item : items) {
            String title = "", link = "", description = "", date = "", content = "";
            Tags tags = new Tags();
            Enclosures enclosures = new Enclosures();
            org.jsoup.select.Elements props = item.getAllElements();

            //TODO Arraylist de opciones que se van quitando y lo hace mas eficiente
            for (org.jsoup.nodes.Element prop : props) {
                int taghash = prop.tagName().hashCode();
                if (taghash == HASH_TITLE) {
                    title = prop.html().replace("&lt;![CDATA[", "").replace("]]&gt;", "").replace("&amp;#039;", "'");
                    continue;
                }
                if (taghash == EC_HASH_LINK) {
                    link = prop.text();
                    continue;
                }
                if (taghash == EC_HASH_DATE) {
                    date = prop.text();
                    continue;
                }
                if (taghash == EC_HASH_DESCRIPTION) {
                    description = prop.text();
                    continue;
                }
                if (taghash == HASH_CATEGORY) {
                    tags.add(prop.text());
                    continue;
                }
                if (taghash == EC_HASH_MEDIA) {
                    enclosures.add(new Enclosure(prop.attr("url"), prop.attr("type"), "0"));
                    continue;
                }
                if (taghash == EC_HASH_CONTENT) {
                    content = prop.html().replace("&lt;", "<").replace("&gt;", ">").replace("&amp;#039;", "'");
                    org.jsoup.nodes.Element con = org.jsoup.Jsoup.parse(content).select("body").get(0);
                    con.children().last().remove();
                    content = con.html();
                }
            }
            News news = new News(title, link, description, date, tags);
            String imgs = "";
            for (Enclosure e : enclosures) {
                imgs += e.html();
            }
            news.content = imgs + content;
            result.add(news);
        }
        return result;
    }

}
