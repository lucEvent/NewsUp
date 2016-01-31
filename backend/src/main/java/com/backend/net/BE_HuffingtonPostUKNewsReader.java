package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_NewsList;
import com.backend.kernel.list.BE_Sections;

import org.jsoup.select.Elements;

import java.util.ArrayList;

public class BE_HuffingtonPostUKNewsReader extends BE_NewsReader {

    public BE_HuffingtonPostUKNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Main news", "http://www.huffingtonpost.co.uk/feeds/verticals/uk/news.xml"));
        SECTIONS.add(new BE_Section("Politics", "http://www.huffingtonpost.co.uk/feeds/verticals/uk-politics/news.xml"));
        SECTIONS.add(new BE_Section("Entertainment", "http://www.huffingtonpost.co.uk/feeds/verticals/uk-entertainment/news.xml"));
        SECTIONS.add(new BE_Section("Style", "http://www.huffingtonpost.co.uk/feeds/verticals/uk-style/news.xml"));
        SECTIONS.add(new BE_Section("Education", "http://www.huffingtonpost.co.uk/feeds/verticals/uk-universities-education/index.xml"));
        SECTIONS.add(new BE_Section("Lifestyle", "http://www.huffingtonpost.co.uk/feeds/verticals/uk-lifestyle/news.xml"));
        SECTIONS.add(new BE_Section("Comedy", "http://www.huffingtonpost.co.uk/feeds/verticals/uk-comedy/news.xml"));
        SECTIONS.add(new BE_Section("Celebrity", "http://www.huffingtonpost.co.uk/news/celebrity/feed/"));
        SECTIONS.add(new BE_Section("Tech", "http://www.huffingtonpost.co.uk/feeds/verticals/uk-tech/news.xml"));
        SECTIONS.add(new BE_Section("Sport", "http://www.huffingtonpost.co.uk/feeds/verticals/uk-sport/news.xml"));
        SECTIONS.add(new BE_Section("Parents", "http://www.huffingtonpost.co.uk/feeds/verticals/uk-parents/news.xml"));
        SECTIONS.add(new BE_Section("Comedy", "http://www.huffingtonpost.co.uk/feeds/verticals/uk-comedy/index.xml"));
        SECTIONS.add(new BE_Section("Entertainment", "http://www.huffingtonpost.co.uk/feeds/verticals/uk-entertainment/index.xml"));
        SECTIONS.add(new BE_Section("Media", "http://www.huffingtonpost.co.uk/news/media/feed/"));
        SECTIONS.add(new BE_Section("Women", "http://www.huffingtonpost.co.uk/news/women/feed/"));
        SECTIONS.add(new BE_Section("Men", "http://www.huffingtonpost.co.uk/news/men/feed/"));
        SECTIONS.add(new BE_Section("Environment", "http://www.huffingtonpost.co.uk/news/environment/feed/"));
        SECTIONS.add(new BE_Section("Whats-working", "http://www.huffingtonpost.co.uk/news/whats-working/feed/"));
        SECTIONS.add(new BE_Section("Impact", "http://www.huffingtonpost.co.uk/news/impact/feed/"));

        SECTIONS.add(new BE_Section("Blog", "http://www.huffingtonpost.co.uk/feeds/blog.xml"));
        SECTIONS.add(new BE_Section("Politics", "http://www.huffingtonpost.co.uk/feeds/verticals/uk-politics/blog.xml"));
        SECTIONS.add(new BE_Section("Entertainment", "http://www.huffingtonpost.co.uk/feeds/verticals/uk-entertainment/blog.xml"));
        SECTIONS.add(new BE_Section("Style", "http://www.huffingtonpost.co.uk/feeds/verticals/uk-style/blog.xml"));
        SECTIONS.add(new BE_Section("Education", "http://www.huffingtonpost.co.uk/feeds/verticals/uk-universities-education/blog.xml"));
        SECTIONS.add(new BE_Section("Lifestyle", "http://www.huffingtonpost.co.uk/feeds/verticals/uk-lifestyle/blog.xml"));
        SECTIONS.add(new BE_Section("Comedy", "http://www.huffingtonpost.co.uk/feeds/verticals/uk-comedy/blog.xml"));
        SECTIONS.add(new BE_Section("Tech", "http://www.huffingtonpost.co.uk/feeds/verticals/uk-tech/blog.xml"));
        SECTIONS.add(new BE_Section("Sport", "http://www.huffingtonpost.co.uk/feeds/verticals/uk-sport/blog.xml"));
        SECTIONS.add(new BE_Section("Parents", "http://www.huffingtonpost.co.uk/feeds/verticals/uk-parents/blog.xml"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
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
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
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
    protected BE_NewsList readRssPage(String rsslink) {
        org.jsoup.nodes.Document doc = getDocument(rsslink);
        if (doc == null) return new BE_NewsList();

        BE_NewsList result = new BE_NewsList();

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
                BE_News news = new BE_News(title, link, description, date, new ArrayList<String>());
                news = applySpecialCase(news, content);
                result.add(news);
            }
        }
        return result;
    }

}