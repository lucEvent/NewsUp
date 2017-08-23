package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Swedroid extends com.lucevent.newsup.data.util.NewsReader {

    private static final String SITE_STYLE = "<style>tr,th,td{padding:3px 10px;}</style>";

    // Tags [category, content:encoded, dc:creator, description, guid, item, link, post-id, pubdate, title]

    public Swedroid()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{},
                "http://www.swedroid.se/",
                SITE_STYLE);
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = jsoupParse(prop);
        doc.select("script,#review-statistics").remove();

        doc.select("h1,h2").tagName("h3");
        doc.select("[style]").removeAttr("style");
        doc.select(".wp-caption-text").tagName("figcaption");

        for (Element img : doc.select("img")) {
            String remove = "-" + img.attr("width") + "x" + img.attr("height");
            String src = img.attr("src").replace(remove, "");
            NewsStylist.cleanAttributes(img);
            img.attr("src", src);
        }

        return doc.body().html();
    }

    @Override
    protected News onNewsRead(News news)
    {
        Document description = jsoupParse(news.description);
        Elements imgs = description.select("img");
        if (!imgs.isEmpty()) {
            Element img = imgs.first();
            news.enclosures.add(new Enclosure(img.attr("src"), "image", ""));
        }

        news.description = description.text();
        int i = news.description.indexOf("[");
        if (i != -1)
            news.description = news.description.substring(0, i) + "...";

        return news;
    }

}