package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.net.URL;

public class WwwhatsNew extends com.lucevent.newsup.data.util.NewsReader {

    //tags: [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

    public WwwhatsNew()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{},
                "");
    }

    @Override
    protected News onNewsRead(News news)
    {
        // Parsing content
        Element article = jsoupParse(news.content);
        article.select(".feedflare,[width='1']").remove();

        news.content = finalFormat(article, false);
        // end

        // Parsing enclosures
        Elements imgs = article.select("img");
        if (!imgs.isEmpty())
            news.enclosures.add(new Enclosure(imgs.first().attr("src"), "", ""));
        // end
        return news;
    }

    @Override
    protected org.jsoup.nodes.Document getDocument(String url)
    {
        try {
            return org.jsoup.Jsoup.parse(new URL(url).openStream(), "utf-8", url, Parser.xmlParser());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.getDocument(url);
    }


}
