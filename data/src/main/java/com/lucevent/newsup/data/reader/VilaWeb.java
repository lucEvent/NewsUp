package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class VilaWeb extends com.lucevent.newsup.data.util.NewsReader {

    public VilaWeb()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_ENCLOSURE});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        String descr = Jsoup.parse(prop.text()).text();
        return descr.substring(0, 300 < descr.length() ? 300 : descr.length()) + "...";
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = org.jsoup.Jsoup.parse(prop.text());
        doc.select("script[src~=twitter],script[src~=instagram],noscript,#tlTagContainer").remove();

        doc.select("[style]").removeAttr("style");
        doc.select("[width]").removeAttr("width");
        doc.select("h1,h2").tagName("h3");

        Element article = doc.body();
        NewsStylist.completeSrcHttp(article);

        return article.html();
    }

}
