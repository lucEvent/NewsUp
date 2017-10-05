package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LuckyPuppy extends com.lucevent.newsup.data.util.NewsReader {

    private static final String SITE_STYLE = "<style>ol{padding:0;}</style>";

    //tags: [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

    public LuckyPuppy()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{},
                "https://www.luckypuppymag.com/",
                SITE_STYLE);
    }

    @Override
    protected String parseDescription(Element prop)
    {
        Element d = jsoupParse(prop).select("p").first();
        return d != null ? d.text() : "";
    }

    @Override
    protected News onNewsRead(News news)
    {
        // Parsing content
        Document doc = jsoupParse(news.content);
        doc.select("h1,h2").remove();
        doc.select("[style]").removeAttr("style");
        NewsStylist.repairLinks(doc.body());

        news.content = doc.body().html();
        // end

        // Parsing enclosures
        Elements imgs = doc.select("img");
        if (!imgs.isEmpty())
            news.enclosures.add(new Enclosure(imgs.first().attr("src"), "", ""));
        // end
        return news;
    }

}
