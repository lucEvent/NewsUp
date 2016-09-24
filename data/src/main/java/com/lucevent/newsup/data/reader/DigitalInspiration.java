package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;

public class DigitalInspiration extends com.lucevent.newsup.data.util.NewsReader {

    // tags: [category, content:encoded, dc:creator, description, enclosure, guid, item, link, pubdate, title]

    public DigitalInspiration()
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
    protected String parseContent(Element prop)
    {
        org.jsoup.nodes.Document d = org.jsoup.Jsoup.parse(prop.text());
        d.select("script").remove();

        for (Element ytv : d.select(".youtube-player"))
            ytv.prepend("<iframe src=\"https://www.youtube.com/embed/" +
                    ytv.attr("data-id") + "\" frameborder=\"0\" allowfullscreen></iframe>");

        for (Element s : d.select("[style]"))
            s.removeAttr("style");

        String content = d.html();

        int index = content.lastIndexOf("<hr>");
        if (index != -1)
            content = content.substring(0, index);

        return content.replace("<pre ", "<code><p ").replace("/pre>", "/p></code>");
    }

}
