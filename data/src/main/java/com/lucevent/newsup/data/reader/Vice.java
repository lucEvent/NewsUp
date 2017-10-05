package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Date;
import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Vice extends com.lucevent.newsup.data.util.NewsReader {

    //tags: [category, content:encoded, dc:creator, description, enclosure, guid, item, link, media:category, media:thumbnail, pubdate, title]

    public Vice()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_ENCLOSURE},
                "https://www.vice.com/",
                "");
    }

    @Override
    protected long parseDate(Element prop)
    {
        String date = prop.text();
        if (date.length() < 24)
            return 0;
        return Date.toDate(prop.text().substring(0, 24));
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = org.jsoup.Jsoup.parse(prop.text());

        doc.select("[style]").removeAttr("style");
        doc.select("iframe").attr("frameborder", "0");
        doc.select("h1,h2").tagName("h3");
        doc.select(".article__pull-quote").tagName("blockquote");
        doc.select(".article__image-caption").tagName("figcaption");

        for (Element iframe : doc.select("iframe[data-iframely-url]"))
            iframe.parent().html(Enclosure.iframe(iframe.attr("data-iframely-url")));

        Element article = doc.body();
        NewsStylist.repairLinks(article);

        return article.html();
    }

    @Override
    protected Document getDocument(String url)
    {
        try {
            return org.jsoup.Jsoup.connect(url)
                    .userAgent(USER_AGENT)
                    .cookie("cookiewall", "yes")
                    .get();
        } catch (Exception ignored) {
        }
        return super.getDocument(url);
    }

}
