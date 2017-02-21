package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Meristation extends com.lucevent.newsup.data.util.NewsReader {

    // tags: [dc:creator, description, guid, item, link, pubdate, title]

    public Meristation()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{});
    }

    @Override
    protected News onNewsRead(News news)
    {
        Document doc = jsoupParse(news.description);

        news.description = doc.select(".field-field-seo-description .field-items").text();

        doc.select("script,.field,.darwin-phpbb-comments-link,.galeriaContent").remove();

        for (Element e : doc.select("#videoEmbed")) {
            String src = "http://www.dailymotion.com/embed/video/" + e.parent().attr("video");
            e.parent().html(Enclosure.iframe(src));
        }
        for (Element e : doc.select(".galeriaAmpliacion")) {
            StringBuilder sb = new StringBuilder();
            for (Element img : e.select(".gal_data li"))
                sb.append("<img src='").append(img.attr("data-path")).append("'>");

            e.html(sb.toString());
        }

        doc.select("h1,h2").tagName("h3");

        news.content = doc.html();
        return news;
    }

    @Override
    protected org.jsoup.nodes.Document getDocument(String pagelink)
    {
        try {
            return org.jsoup.Jsoup.connect(pagelink)
                    .timeout(10000)
                    .get();
        } catch (Exception ignored) {
        }
        return super.getDocument(pagelink);
    }

}
