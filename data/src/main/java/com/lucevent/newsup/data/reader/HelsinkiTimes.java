package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public class HelsinkiTimes extends com.lucevent.newsup.data.util.NewsReader {

    //Tags:[author, category, description, guid, item, link, pubdate, title]

    public HelsinkiTimes()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parseBodyFragment(prop.text()).text();
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        org.jsoup.select.Elements e = doc.select(".item-page > p,.item-page .thumbnail img");

        if (!e.isEmpty()) {

            for (org.jsoup.nodes.Element img : e.select("img"))
                img.attr("src", "http://www.helsinkitimes.fi" + img.attr("src"));

            news.content = e.outerHtml();
        }
    }

}