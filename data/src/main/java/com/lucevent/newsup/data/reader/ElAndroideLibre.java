package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;

public class ElAndroideLibre extends com.lucevent.newsup.data.util.NewsReader_v2 {

    // tags: [category, content:encoded, dc:creator, description, feedburner:origlink, guid, item, link, pubdate, title

    public ElAndroideLibre()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_GUID},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).getElementsByTag("p").text().replace("[...]", "");
    }

    @Override
    protected String parseContent(Element prop)
    {
        org.jsoup.select.Elements doc = org.jsoup.Jsoup.parseBodyFragment(prop.text()).getElementsByTag("body");
        doc.select("[clear=\"all\"] ~ *,br,.feedflare,[width=\"1\"]").remove();
        return doc.html();
    }

}
