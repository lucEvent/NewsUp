package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Clipset extends com.lucevent.newsup.data.util.NewsReader {

    private static final String SITE_STYLE = "<style>.wp-caption-text{font-size:12px;padding:2px 10px;display:block;}</style>";

    //tags: [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

    public Clipset()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{});

        this.style = SITE_STYLE;
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text();
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = org.jsoup.Jsoup.parse(prop.text());

        for (Element e : doc.select("map")) {
            e.parent().remove();
        }
        doc.select(".wp-embedded-content,script").remove();

        doc.select("h2").tagName("h3");
        doc.select("h5").tagName("blockquote");
        doc.select("[srcset]").removeAttr("srcset");
        doc.select("[style]").removeAttr("style");

        NewsStylist.cleanAttributes(doc.select("img"), "src");

        return doc.body().html();
    }

}
