package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Date;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public class TheLocal extends com.lucevent.newsup.data.util.NewsReader {

    // tags: [description, enclosure, guid, item, link, pubdate, title]

    public TheLocal()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{TAG_ENCLOSURE});
    }

    @Override
    protected long parseDate(Element prop)
    {
        return Date.toDate(prop.text()) - 7200000; // -2hours
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        org.jsoup.select.Elements content = doc.select("#main_picture_article > img,.articleTeaser,.articleContent");

        org.jsoup.select.Elements imgs = content.select("img");
        for (org.jsoup.nodes.Element img : imgs) {
            String src = "http://www.thelocal.com" + img.attr("src");
            img.attr("src", src);
            img.attr("style", "");
        }

        news.content = content.outerHtml();
    }

}
