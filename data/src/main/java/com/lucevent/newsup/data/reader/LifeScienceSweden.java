package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LifeScienceSweden extends com.lucevent.newsup.data.util.NewsReader {

    //tags: [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

    public LifeScienceSweden()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{});
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = jsoupParse(prop);
        doc.select("h3~*,h3,.wp-caption,h1").remove();

        doc.select("h2").tagName("h3");

        return doc.body().html().replace("<p>&nbsp;</p>", "");
    }

    @Override
    protected News onNewsRead(News news)
    {
        if (!news.description.isEmpty()) {
            Document doc = jsoupParse(news.description);

            Elements img = doc.select("img");
            if (!img.isEmpty())
                news.enclosures.add(new Enclosure(img.first().attr("src"), "image", ""));

            news.description = doc.text().replace("Read more »", "").trim();
        }
        return news;
    }

}
