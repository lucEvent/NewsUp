package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Medium extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * Tags:
     * [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]
     * [category,                  dc:creator, description, guid, item, link, pubdate, title]
     * [category, content:encoded, dc:creator,              guid, item, link, pubdate, title]
     */

    public Medium()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_GUID},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{},
                "https://medium.com/",
                "");
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = jsoupParse(prop);
        doc.select("script,[width=1],figcaption").remove();
        doc.select("h1,h2").tagName("h3");
        NewsStylist.repairLinks(doc.body());
        return doc.body().html();
    }

    @Override
    protected News onNewsRead(News news)
    {
        if (news.description != null) {
            Document doc = jsoupParse(news.description);

            Elements img = doc.select("img");
            if (!img.isEmpty())
                news.enclosures.add(new Enclosure(img.first().attr("src"), "image", ""));

            news.description = doc.select(".medium-feed-snippet").text();
        }
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        Elements e = doc.select("main section .section-content");
        e.select("script,.graf--title").remove();

        for (Element fig : e.select("figure:has(noscript)")) {
            fig.html(fig.select("noscript").html());
            NewsStylist.cleanAttributes(fig);
        }

        e.select("h1,h2").tagName("h3");
        e.select(".section-inner,.graf,.progressiveMedia-noscript").removeAttr("class");

        news.content = e.html();
    }

    @Override
    protected Document getDocument(String pagelink)
    {
        try {
            return org.jsoup.Jsoup.connect(pagelink)
                    .timeout(10000)
                    .userAgent(USER_AGENT)
                    .get();
        } catch (Exception ignored) {
        }
        return null;
    }

}
