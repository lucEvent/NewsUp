package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import java.net.URL;

public class DiarioCordoba extends com.lucevent.newsup.data.util.NewsReader {

    // Tags: category, dc:creator, description, guid, item, link, pubdate, title

    public DiarioCordoba()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{},
                "http://www.diariocordoba.com/",
                "");
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return prop.text().replace("<p>", "").replace("</p>", "");
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        org.jsoup.select.Elements e = doc.select(".bxGaleriaNoticia img:not(.PlayVideo,.ThumbNail),.PlayerVideoBOTR,#CuerpoDeLaNoticia");

        if (e.isEmpty())
            return;

        e.select("script").remove();

        for (Element video : e.select(".PlayerVideoBOTR")) {
            video.tagName("video");

            String src = video.attr("id");
            int i1 = src.indexOf("_") + 1;
            int i2 = src.indexOf("_", i1);
            src = "http://content.jwplatform.com/videos/" + src.substring(i1, i2) + ".mp4";
            video.attr("src", src).attr("controls", "");
        }

        news.content = e.outerHtml();
    }

    @Override
    protected org.jsoup.nodes.Document getDocument(String rsslink)
    {
        try {
            return org.jsoup.Jsoup.parse(new URL(rsslink).openStream(), "ISO-8859-1", rsslink, Parser.xmlParser());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.getDocument(rsslink);
    }

}