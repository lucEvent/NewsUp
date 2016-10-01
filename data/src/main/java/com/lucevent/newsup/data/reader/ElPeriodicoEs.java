package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class ElPeriodicoEs extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * Tags
     * [description, guid, item, link, pubdate, title]
     * [category, comments, content:encoded, dc:creator, description, guid, item, link, pubdate,  title]
     */

    public ElPeriodicoEs()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text();
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = org.jsoup.Jsoup.parse(prop.text());
        for (Element e : doc.select("[style]"))
            e.removeAttr("style");

        doc.select("h1,h2").tagName("h3");

        return doc.html().replace("<span>", "").replace("</span>", "").replace("<p>&nbsp;</p>", "");
    }

    @Override
    protected Document getDocument(String rsslink)
    {
        if (rsslink.endsWith("rss/rss_portada.xml")) {
            try {
                return org.jsoup.Jsoup.parse(new URL(rsslink).openStream(), "ISO-8859-1", rsslink, Parser.xmlParser());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.getDocument(rsslink);
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        doc.select("script").remove();

        Elements intro = doc.select(".ep-video,.unit > .ep-img,.unit > .ep-galeria");
        intro.select(".carousel").remove();
        intro = intro.select("img");

        Elements content = doc.select(".cuerpo-noticia,.cuerpo-opinion");

        content.select(".fecha,.carousel,.thumb-pie,.cred").remove();
        content = content.select("p,a > img,h2,h3,h4,h5,h6");

        news.content = intro.outerHtml() + content.outerHtml();
        if (news.content.length() < 80)
            news.content = null;

    }

}
