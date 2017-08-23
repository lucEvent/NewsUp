package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HuffingtonPostUK extends com.lucevent.newsup.data.util.NewsReader {

    // Tags: [author, description, enclosure, guid, item, link, pubdate, title]

    public HuffingtonPostUK()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_GUID},
                new int[]{},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{TAG_ENCLOSURE},
                "http://www.huffingtonpost.co.uk/",
                "");
    }

    @Override
    protected String parseContent(Element prop)
    {
        String text = prop.text();
        Document doc = org.jsoup.Jsoup.parse(text);
        doc.select("br").tagName("p");

        for (Element e : doc.select("strong")) {
            if (e.text().startsWith("SEE ALSO:"))
                e.parent().remove();
        }
        doc.select("[style]").removeAttr("style");
        doc.select("h1,h2").tagName("h3");

        Element body = doc.body();
        NewsStylist.repairLinks(body);

        String content = body.html();

        int i0 = content.indexOf("type=type=");
        if (i0 != -1) {
            int i1 = content.indexOf("articlesList=", i0);
            if (i1 != -1) {
                i1 = content.indexOf("<", i1);
                content = content.substring(0, i0) + content.substring(i1, content.length());
            }
        }
        return content;
    }

    @Override
    protected Enclosure parseEnclosure(Element prop)
    {
        String type = prop.attr("type");
        if (type.startsWith("image")) {
            String url = prop.attr("url");
            if (url.contains("74_58"))
                url = url.replace("74_58", "300_219");
            else if (url.contains("-mini")) {
                url = url.replace("-mini", "-large300");
            }
            return new Enclosure(url, type, "");
        }
        return null;
    }

}