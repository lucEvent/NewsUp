package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;

public class GoteborgsPosten extends com.lucevent.newsup.data.util.NewsReader {

    public GoteborgsPosten()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{},
                new int[]{},
                new int[]{},
                new int[]{},
                new int[]{},
                new int[]{},
                new int[]{});
    }

    @Override
    protected News applySpecialCase(News news, String content)
    {
        if (news.description.length() > 500) {
            String img = "";
            for (Enclosure en : news.enclosures)
                img += en.html();

            news.content = img + news.description;
            news.description = org.jsoup.Jsoup.parseBodyFragment(news.description).text().substring(0, 500);
        } else {
            news.description = org.jsoup.Jsoup.parseBodyFragment(news.description).text();
        }
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        org.jsoup.select.Elements body = doc.select(".body");

        if (!body.isEmpty()) {

            org.jsoup.select.Elements imgs = doc.select(".imageWrapper,.photoAlbumContainer,.articlePictures").select("img");

            String img = "";
            for (org.jsoup.nodes.Element i : imgs)
                img += "<img src=\"http://www.gp.se/" + i.attr("src") + "\" />";

            org.jsoup.select.Elements intro = doc.select(".factContainer,#articleContainer > iframe");
            for (org.jsoup.nodes.Element i : intro)
                i.attr("style", "background-color: #f7f7f7");

            news.content = img + intro.html() + body.html();
        }
    }

}
