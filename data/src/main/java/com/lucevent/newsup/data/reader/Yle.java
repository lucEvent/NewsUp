package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Yle extends com.lucevent.newsup.data.util.NewsReader {

    // Tags: [category, content:encoded, description, enclosure, guid, item, link, pubdate, title]

    public Yle()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_ENCLOSURE});
    }

    @Override
    protected Enclosure parseEnclosure(Element prop)
    {
        return new Enclosure(prop.attr("url").replace("/w_205,h_115,q_70", "/w_615,h_345,q_100"), prop.attr("type"), prop.attr("length"));
    }

    @Override
    protected News onNewsRead(News news)
    {
        if (!news.content.isEmpty()) {
            Document doc = jsoupParse(news.content);
            if (doc.text().length() != 0) {

                doc.select("h1,h2").tagName("h3");
                doc.select("[class]").removeAttr("class");
                NewsStylist.repairLinks(doc.body());

                String img = "";
                for (Enclosure e : news.enclosures)
                    img += e.html();

                news.content = "<meta charset='UTF-8'>" + img + doc.body().html();
            }
        }
        return news;
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        org.jsoup.select.Elements article = doc.select(".text");

        if (!article.isEmpty()) {
            article.select("h1,h2").tagName("h3");

            news.content = article.html();
        }
    }

}