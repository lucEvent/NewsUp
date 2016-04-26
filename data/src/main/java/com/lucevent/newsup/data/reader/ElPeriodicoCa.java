package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import java.io.IOException;
import java.net.URL;

public class ElPeriodicoCa extends com.lucevent.newsup.data.util.NewsReader_v2 {

    /**
     * Tags
     * [description, guid, item, link, pubdate, title]
     * [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]
     * [category, content:encoded, dc:creator, description, guid, item, link, pubdate, slash:comments, title, wfw:commentrss]
     */

    public ElPeriodicoCa()
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
    protected org.jsoup.nodes.Document getDocument(String rsslink)
    {
        if (rsslink.equals("http://www.elperiodico.cat/ca/rss/rss_portada.xml")) {
            try {
                return org.jsoup.Jsoup.parse(new URL(rsslink).openStream(), "ISO-8859-1", rsslink, Parser.xmlParser());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.getDocument(rsslink);
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        doc.select("script").remove();

        org.jsoup.select.Elements intro = doc.select(".ep-video,.unit > .ep-img,.unit > .ep-galeria");
        intro.select(".carousel").remove();
        intro = intro.select("img");

        org.jsoup.select.Elements content = doc.select(".cuerpo-noticia,.cuerpo-opinion");

        content.select(".fecha,.carousel,.thumb-pie,.cred").remove();
        content = content.select("p,a > img,h2,h3,h4,h5,h6");

        news.content = intro.outerHtml() + content.outerHtml();
        if (news.content.length() < 80) {
            news.content = null;
        }
    }

}
