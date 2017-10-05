package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class UniverseToday extends com.lucevent.newsup.data.util.NewsReader {

    //tags:[category, content:encoded, dc:creator, description, guid, item, link, post-id, pubdate, title]

    public UniverseToday()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{},
                "https://www.universetoday.com/",
                "");
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return jsoupParse(prop).select("p").first().text();
    }

    @Override
    protected News onNewsRead(News news)
    {
        // Parsing content
        Document doc = jsoupParse(news.content);
        doc.select("script").remove();

        doc.select("h1,h2").tagName("h3");
        doc.select(".wp-caption-text").tagName("figcaption");
        doc.select("[style]").removeAttr("style");
        doc.select("iframe").attr("frameborder", "0");

        news.content = NewsStylist.cleanComments(doc.body().html());
        // end

        // Parsing enclosures
        Elements imgs = doc.select("img");
        if (!imgs.isEmpty())
            news.enclosures.add(new Enclosure(imgs.first().attr("src"), "", ""));
        // end
        return news;
    }

    @Override
    protected Document getDocument(String pagelink)
    {
        try {
            return org.jsoup.Jsoup.connect(pagelink)
                    .ignoreContentType(true)
                    .userAgent(USER_AGENT)
                    .timeout(10000)
                    .get();
        } catch (Exception ignored) {
        }
        return null;
    }

}