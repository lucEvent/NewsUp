package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public class LaVanguardia extends com.lucevent.newsup.data.util.NewsReader_v2 {

    /**
     * [category, description, enclosure, guid, item, link, pubdate, title]
     */

    public LaVanguardia()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_ENCLOSURE});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        String description = org.jsoup.Jsoup.parse(prop.text()).text();
        int idash = description.indexOf("- ");
        if (idash != -1)
            description = description.substring(idash + 2);
        return description;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        org.jsoup.select.Elements e = doc.select("[itemprop=\"articleBody\"]");

        for (org.jsoup.nodes.Element style : e.select("[style]"))
            style.removeAttr("style");

        if (!e.isEmpty())
            news.content = e.html();
    }

}
