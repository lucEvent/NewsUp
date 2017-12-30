package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class _20Minutos extends com.lucevent.newsup.data.util.NewsReader {

    //tags: dc:creator, dc:date, description, link, title

    public _20Minutos()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_DC_DATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_ENCLOSURE},
                "");
    }

    @Override
    protected News onNewsRead(News news)
    {
        // Parsing content
        Element article = jsoupParse(news.content);
        article.parent().select("body > br, body > img, body > a").remove();
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