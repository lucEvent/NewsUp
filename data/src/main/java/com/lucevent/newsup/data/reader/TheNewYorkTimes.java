package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class TheNewYorkTimes extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * tags:
     * [atom:link, category, dc:creator, description, guid, item, link, media:content, media:credit, media:description, pubdate, title]
     * [           category, dc:creator, description, guid, item, link, media:content, media:credit, media:description, pubdate, title]
     * [content:encoded,                 description, guid, item, lastbuilddate, link,      media:content, media:group, pubdate, title]
     * [category, dc:creator, description, enclosure, guid, item, link, media:content, pubdate, title]
     * [category, dc:creator, description, guid, item, link, media:content, pubdate, title]
     * [category, dc:creator, description, enclosure, guid, item, link, pubdate, title]
     * [category, dc:creator, description, guid, item, link, pubdate, title]
     * [author, description, guid, item, link, pubdate, title]
     * [author, description, item, link, pubdate, title]
     **/
    public TheNewYorkTimes()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_ENCLOSURE, TAG_MEDIA_CONTENT});
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
    //    news.content = doc.outerHtml();
    //    if (true) return;

        Elements article = doc.select("article .p-block:not(.article-interactive)");
        article.select(".lazyload,.image-caption,.image-credit").remove();

        if (article.isEmpty()) {

            article = doc.select("#promo-info");

            if (article.isEmpty()) {

                article = doc.select("article figure img,article .summary");

                if (article.size() < 2) {

                    article = doc.select("[data-view=\"slideshow-slide\"]");

                    if (article.isEmpty()) {
                        ///System.out.println("Returning");
                        return;
                    } else {
                        for (Element e : article) {
                            String text = e.select(".slide-text p").text();
                            String img = e.attr("data-view-src");

                            e.tagName("p");
                            e.removeAttr("data-view-src");
                            e.html("<img src='" + img + "'/>" + text);
                        }
                    }
                } else {
                    Element img = article.get(0);
                    Element content = article.get(1);
                    article = new Elements();
                    article.add(img);
                    article.add(content);
                }
            } else
                article.select("li").tagName("p");
        }
        for (Element e : article.select(".span-image")) {
            Elements g = e.select("img,video,picture");
            if (!g.isEmpty())
                e.html(g.outerHtml());
        }
        for (Element e : article.select("[style]"))
            e.removeAttr("style");

        news.content = article.outerHtml();
    }
/*
    protected org.jsoup.nodes.Document getDocument(String pagelink)
    {
        try {

            URL oracle = new URL(pagelink);
            BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

            StringBuilder sb = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                sb.append(inputLine);
            in.close();

            System.out.println("Link:"+pagelink);
            System.out.println("Size:"+sb.length());
            System.out.println(sb);
            return org.jsoup.Jsoup.parse(sb.toString(), "", new org.jsoup.parser.Parser(new org.jsoup.parser.XmlTreeBuilder()));

      //  System.out.println("Asereje:"+pagelink);
      //      return org.jsoup.Jsoup.connect(pagelink)
       //             .referrer("https://www.google.com/")
       //             .userAgent("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html")
       ///             .timeout(10000)
       //             .get();
        } catch (Exception e) {
            System.out.println("[NYTimes] Couldn't read page: " + pagelink);
            e.printStackTrace();
        }
        return null;
    }
*/
}
