package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Date;
import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Abc extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * tags:
     * [category,                  dc:creator, description, guid,         item, link, pubdate,         title]
     * [comments, content:encoded, dc:creator, description, guid, imagen, item, link, pubdate, source, title]
     **/

    public Abc()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{"imagen".hashCode()});
    }

    @Override
    protected long parseDate(Element prop)
    {
        return Date.toDate(prop.text()) - 7200000;//2 hours
    }

    @Override
    protected Enclosure parseEnclosure(Element prop)
    {
        String enclosure = prop.text();
        if (!enclosure.isEmpty()) {

            Document d = Jsoup.parse(prop.text());
            Elements imgs = d.select("img");
            if (!imgs.isEmpty())
                return new Enclosure(imgs.get(0).attr("src"), "image", "");
        }
        return null;
    }

    @Override
    protected News applySpecialCase(News news, String content)
    {
        if (content.isEmpty()) {
            Document d = Jsoup.parse(news.description);
            d.select(".remision-galeria").remove();

            news.content = d.outerHtml();

            String dscr = d.text();
            news.description = dscr.substring(0, Math.min(dscr.length(), 300));
        }
        return news;
    }

}
