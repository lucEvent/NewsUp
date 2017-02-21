package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class LifeHacker extends com.lucevent.newsup.data.util.NewsReader {

    // tags: [category, dc:creator, description, guid, item, link, pubdate, title]

    public LifeHacker()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{});
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = org.jsoup.Jsoup.parse(prop.text());

        doc.select("figcaption,.has-video .jwplayer").remove();
        doc.select("h2").tagName("h3");

        for (Element e : doc.select("a")) {
            String href = e.attr("href");
            for (Attribute attr : e.attributes())
                e.removeAttr(attr.getKey());
            e.attr("href", href);
        }

        Element body = doc.body();
        NewsStylist.completeSrcHttp(body);

        return NewsStylist.base("http://lifehacker.com/") + doc.body().html();
    }

}
