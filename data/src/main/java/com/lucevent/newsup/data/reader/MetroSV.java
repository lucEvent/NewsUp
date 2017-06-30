package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Element;

public class MetroSV extends com.lucevent.newsup.data.util.NewsReader {

    // tags: [description, enclosure, guid, item, link, pubdate, title]

    public MetroSV()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{TAG_ENCLOSURE});

        this.style = NewsStylist.base("http://www.metro.se/");
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return prop.text().replace("...", "");
    }

    protected org.jsoup.nodes.Document getDocument(String pagelink)
    {
        try {
            return org.jsoup.Jsoup.connect(pagelink).get();
        } catch (Exception ignored) {
        }
        try {
            return org.jsoup.Jsoup.connect(pagelink).timeout(10000).get();
        } catch (Exception ignored) {
        }
        return super.getDocument(pagelink);
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        doc.select("script").remove();

        org.jsoup.select.Elements imgs = doc.select(".image-component > img");
        if (!imgs.isEmpty()) {
            org.jsoup.nodes.Element img = imgs.get(0);
            news.content = img.outerHtml();
        }

        org.jsoup.select.Elements e = doc.select(".article-body");
        news.content += e.html();
    }

}
