package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

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
                new int[]{},
                "");
    }

    @Override
    protected News onNewsRead(News news)
    {
        Element article = jsoupParse(news.description);

        news.description = article.select(".field-field-seo-description .field-items").text();

        article.select("script,.field,.darwin-phpbb-comments-link,.galeriaContent").remove();

        for (Element e : article.select("#videoEmbed")) {
            String src = "http://www.dailymotion.com/embed/video/" + e.parent().attr("video");
            e.parent().html(insertIframe(src));
        }
        for (Element e : article.select(".galeriaAmpliacion")) {
            StringBuilder sb = new StringBuilder();
            for (Element img : e.select(".gal_data li"))
                sb.append("<img src='").append(img.attr("data-path")).append("'>");

            e.html(sb.toString());
        }

        news.content = finalFormat(article, false);
        return news;
    }

    @Override
    protected org.jsoup.nodes.Document getDocument(String url)
    {
        try {
            return org.jsoup.Jsoup.connect(url)
                    .timeout(10000)
                    .get();
        } catch (Exception ignored) {
        }
        return super.getDocument(url);
    }

}
