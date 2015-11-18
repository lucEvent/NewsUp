package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_PeopleNewsReader extends BE_NewsReader {

    public BE_PeopleNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Latest News", "http://rss.people.com/web/people/rss/topheadlines/index.xml"));
        SECTIONS.add(new BE_Section("StyleWatch", "http://feeds.feedburner.com/people/stylewatch/offtherack"));
        SECTIONS.add(new BE_Section("TV Watch", "http://rss.people.com/web/people/rss/tvwatch/index.xml"));
        SECTIONS.add(new BE_Section("Pets", "http://rss.people.com/web/people/rss/pets/index.xml"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text();
        news.title = news.title.replace("<em>", "").replace("</em>", "").replace("&#8211","-");
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
        org.jsoup.select.Elements e = doc.select("#main-article .article-image-wrapper .node-image,#main-article .field-type-text-with-summary");

        if (e.isEmpty()) {
            e = doc.select("div[class=content]");

            if (e.isEmpty()) {
                e = doc.select(".post-body");

                if (e.isEmpty()) {
                    e = doc.select("article[class=content]");

                    if (e.isEmpty()) {
                        return;
                    } else {
                        e.select("header,.byline,[class=\"social article\"],.brightcovePlayerInline,#partner-content,.emote-wrap,#article-comments").remove();
                    }
                } else {
                    e.select("div").remove();
                }
            } else {
                e.select(".byline,[class=\"social article\"],.brightcovePlayerInline").remove();
            }
        } else {
            e.select("object").remove();

        }

        for (org.jsoup.nodes.Element img : e.select("noscript")) {
            img.parent().html(img.html());
        }
        for (org.jsoup.nodes.Element img : e.select("noscript")) {
            img.parent().html(img.html());
        }

        for (org.jsoup.nodes.Element video : e.select("iframe")) {
            String src = video.attr("src");
            if (!src.contains("http:")) {
                src = "http:" + src;
                video.attr("src", src);
            }
        }

        for (org.jsoup.nodes.Element video : e.select(".youtube")) {
            try {
                String src = video.select("embed").first().attr("src").replace("/v/", "/embed/");
                video.html("<iframe src=\"" + src + "\" frameborder=\"0\" allowfullscreen></iframe>");
            } catch (Exception ex) {
            }
        }
        news.content = e.html();
    }

}
