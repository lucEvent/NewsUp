package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HuffingtonPostInt extends com.lucevent.newsup.data.util.NewsReader {

    // Tags: [author, description, enclosure, guid, item, link, pubdate, title]

    public HuffingtonPostInt()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_GUID},
                new int[]{},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{TAG_ENCLOSURE});
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = org.jsoup.Jsoup.parse(prop.text().replace("<br />", "<p></p>"));

        doc.getElementsByTag("h2").tagName("h3");

        String content = doc.html();

        int i0 = content.indexOf("type=type=");
        if (i0 != -1) {
            int i1 = content.indexOf("articlesList=", i0);
            if (i1 != -1) {
                i1 = content.indexOf("<", i1);
                content = content.substring(0, i0) + content.substring(i1, content.length());
            }
        }

        i0 = content.indexOf("<hh-");
        if (i0 != -1) {
            int i1 = content.indexOf("-hh>", i0 + 4);
            i1 = content.indexOf("-hh>", i1 + 4);
            content = content.replace(content.substring(i0, i1 + 4), "");
        }
        return content.replace("=\"/", "=\"http:/");
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