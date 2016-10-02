package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.parser.XmlTreeBuilder;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class Vandal extends com.lucevent.newsup.data.util.NewsReader {

    // tags: [category, description, guid, item, link, pubdate, title]

    public Vandal()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{});
    }

    @Override
    protected String parseContent(Element prop)
    {
        String content = prop.text();
        if (content.length() > 1000) {
            Document d = Jsoup.parse(content);

            Elements videos = d.select("a[href^=\"http://www.vandal.net/video\"]");

            for (Element v : videos) {
                String link = v.attr("href");
                String video_id = link.substring(28, link.indexOf("/", 28));

                v.previousElementSibling()
                        .append(
                                "<video controls><source src=\"http://videosold.vandalimg.com/mp4/" + video_id + ".mp4\" type=\"video/mp4\"></video>"
                        );
                v.remove();
            }
            d.select("br").tagName("p");
            return d.outerHtml();
        }
        return "";

    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements video = doc.select("meta[property=\"og:video\"]");
        if (!video.isEmpty()) {
            String link = video.first().attr("content");

            news.content = "<iframe allowfullscreen src='" + link + "'></iframe>";

            Elements dscr = doc.select("meta[name=\"description\"]");
            if (!dscr.isEmpty())
                news.content += "<p>" + dscr.first().attr("content") + "</p>";

        } else {
            Elements e = doc.select(".contenidoprincipal [class=\"tn mt10\"]");
            news.content = e.outerHtml();
        }
    }

    @Override
    protected org.jsoup.nodes.Document getDocument(String pagelink)
    {
        try {
            return org.jsoup.Jsoup.parse(new URL(pagelink).openStream(), "ISO-8859-1", pagelink, new Parser(new XmlTreeBuilder()));
        } catch (IOException ignore) {
        }
        return super.getDocument(pagelink);
    }

}
