package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;

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
        Element article = jsoupParse(news.content);
        article.select("[style]").removeAttr("style");

        news.content = finalFormat(article, false);
        // end

        // Parsing enclosures
        Elements imgs = article.select("img");
        if (!imgs.isEmpty())
            news.enclosures.add(new Enclosure(imgs.first().attr("src"), "", ""));
        // end
        return news;
    }

}
