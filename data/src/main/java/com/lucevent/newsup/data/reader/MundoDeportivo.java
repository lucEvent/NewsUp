package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MundoDeportivo extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * Tags
     * [category, description, enclosure, guid, item, link, pubdate, title]
     * [category, description, guid, item, link, pubdate, title]
     */

    public MundoDeportivo()
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
        String description = prop.text();
        int idash = description.indexOf("- ");
        if (idash != -1)
            description = description.substring(idash + 2);
        return description.replace("...", "");
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        Elements e = doc.select(".story-leaf-figure,.story-leaf-body-video,.story-leaf-body .story-leaf-txt-p,.live-scribble");

        news.content = e.outerHtml().replace("src=\"/", "src=\"http:/");
    }

}
