package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CounterPunch extends com.lucevent.newsup.data.util.NewsReader {

    //tags: [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

    public CounterPunch()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{},
                "https://www.counterpunch.org/",
                "");
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return jsoupParse(prop).text();
    }

    @Override
    protected News onNewsRead(News news)
    {
        // Parsing content
        Document doc = jsoupParse(news.content);
        doc.select("script").remove();
        doc.select("[style]").removeAttr("style");
        doc.select(".wp-caption-text").tagName("figcaption");

        news.content = doc.body().html();
        // end

        // Parsing enclosures
        Elements imgs = doc.select("img");
        if (!imgs.isEmpty())
            news.enclosures.add(new Enclosure(imgs.first().attr("src"), "", ""));
        // end
        return news;
    }

    @Override
    protected Document getDocument(String url)
    {
        try {
            return org.jsoup.Jsoup.connect(url)
                    .userAgent(USER_AGENT)
                    .timeout(10000)
                    .validateTLSCertificates(false)
                    .get();
        } catch (Exception e) {
        }
        return super.getDocument(url);
    }

}
