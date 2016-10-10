package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;

import org.jsoup.nodes.Element;

public class TheIntercept extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * tags:
     * [category, content:encoded, dc:creator, description, guid, item, leadimageart, leadimageartcredit, link, media:content, media:description, media:thumbnail, media:title, pubdate, title]
     * [category, content:encoded, dc:creator, description, guid, item, leadimageart, leadimageartcredit, link,                                                                 pubdate, title]
     * [category, content:encoded, dc:creator, description, guid, item,                                   link, media:content, media:description, media:thumbnail, media:title, pubdate, title]
     **/

    public TheIntercept()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{"leadimageart".hashCode(), TAG_MEDIA_CONTENT});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text();
    }

    @Override
    protected String parseContent(Element prop)
    {
        return prop.text().replace("style='", "s='").replace("src='/", "src='http:/");
    }

    @Override
    protected Enclosure parseEnclosure(Element prop)
    {
        if (prop.tagName().equals("leadimageart"))
            return new Enclosure(prop.text(), "image/jpg", "");
        return super.parseEnclosure(prop);
    }

}
