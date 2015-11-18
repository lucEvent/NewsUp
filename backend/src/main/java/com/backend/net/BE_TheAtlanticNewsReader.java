package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_NewsList;
import com.backend.kernel.list.BE_Sections;

import java.util.ArrayList;

public class BE_TheAtlanticNewsReader extends BE_NewsReader {

    protected static final int HASH_UPDATED = "updated".hashCode();
    protected static final int HASH_SUMMARY = "summary".hashCode();
    protected static final int HASH_CONTENT_2 = "content".hashCode();
    protected static final int HASH_ORIGLINK = "feedburner:origlink".hashCode();

    public BE_TheAtlanticNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("The Atlantic", "http://feeds.feedburner.com/TheAtlantic"));
        SECTIONS.add(new BE_Section("Politics", "http://feeds.feedburner.com/AtlanticPoliticsChannel"));
        SECTIONS.add(new BE_Section("Business", "http://feeds.feedburner.com/AtlanticBusinessChannel"));
        SECTIONS.add(new BE_Section("Culture", "http://feeds.feedburner.com/AtlanticCulture"));
        SECTIONS.add(new BE_Section("Global", "http://feeds.feedburner.com/AtlanticInternational"));
        SECTIONS.add(new BE_Section("Technology", "http://feeds.feedburner.com/AtlanticScienceAndTechnology"));
        SECTIONS.add(new BE_Section("U.S.", "http://feeds.feedburner.com/AtlanticNational"));
        SECTIONS.add(new BE_Section("Health", "http://feeds.feedburner.com/AtlanticFood"));
        SECTIONS.add(new BE_Section("Video", "http://feeds.feedburner.com/AtlanticVideo"));
        SECTIONS.add(new BE_Section("Education", "http://feeds.feedburner.com/AtlanticEducationChannel"));
        SECTIONS.add(new BE_Section("Science", "http://feeds.feedburner.com/AtlanticScienceChannel"));
        SECTIONS.add(new BE_Section("Photo", "http://feeds.feedburner.com/theatlantic/infocus"));
        SECTIONS.add(new BE_Section("Notes", "http://feeds.feedburner.com/TheAtlanticNotes"));

        SECTIONS.add(new BE_Section("The Wire", "http://feeds.feedburner.com/TheAtlanticWire"));
        SECTIONS.add(new BE_Section("CityLab", "http://feeds.feedburner.com/TheAtlanticCities"));

    }

    @Override
    protected BE_NewsList readRssPage(String rsslink) {
        org.jsoup.nodes.Document doc = getDocument(rsslink);
        if (doc == null) return new BE_NewsList();

        BE_NewsList result = new BE_NewsList();

        org.jsoup.select.Elements items = doc.select("item,entry");
        for (org.jsoup.nodes.Element item : items) {
            String title = "", link = "", description = "", date = "", content = "";
            ArrayList<String> categories = new ArrayList<String>();

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
                BE_News news = new BE_News(title, link, description, date, categories);
                news = applySpecialCase(news, content);
                result.add(news);
            }
        }
        return result;
    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text();

        if (!content.isEmpty()) {
            org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(content);
            doc.select("[clear=\"all\"] ~ *,[clear=\"all\"],.callout,.partner-box,img[height=\"1\"]").remove();
            news.content = doc.html();
        }
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
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
