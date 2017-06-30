package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HelsinkiTimes extends com.lucevent.newsup.data.util.NewsReader {

    //Tags:[author, category, description, guid, item, link, pubdate, title]

    public HelsinkiTimes()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{});

        this.style = NewsStylist.base("http://www.helsinkitimes.fi/");
    }

    @Override
    protected News onNewsRead(News news)
    {
        Document doc = org.jsoup.Jsoup.parse(news.description);

        Elements enc = doc.select("img");
        if (!enc.isEmpty())
            news.enclosures.add(new Enclosure(enc.get(0).attr("src"), "image", ""));

        news.description = doc.text();

        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        Elements article = doc.select(".article-content-main");
        article.select("figcaption,script,.infobox").remove();

        article.select("[style]").removeAttr("style");

        news.content = article.outerHtml();
    }

}