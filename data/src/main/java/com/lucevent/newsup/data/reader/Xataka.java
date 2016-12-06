package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Xataka extends com.lucevent.newsup.data.util.NewsReader {

    private static final String SITE_STYLE = "<style>.sumario{color:#748e8f;text-align:left;font-family:Roboto Condensed,Roboto,sans-serif;font-weight:400;text-transform" +
            ":uppercase;font-size:16px;line-height:28.8px;margin-top:0;margin-bottom:24px;border-top:1px solid #e9e9e9;border-bottom:1px solid #e9e9e9;padding:20px 0;}</style>";

    /**
     * Tags
     * [dc:creator, description, feedburner:origlink, guid, item, link, pubdate, title]
     * [dc:creator, description,                      guid, item, link, pubdate, title]
     */

    public Xataka()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_GUID},
                new int[]{},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{});

        this.style = SITE_STYLE;
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = org.jsoup.Jsoup.parse(prop.text());

        doc.select("body > h4 ~ *,body > h4,.feedflare,[width='1']").remove();

        doc.select("li").tagName("p");
        doc.select("h2").tagName("h3");

        return doc.body().html().replace("=\"//", "=\"http://");
    }

}
