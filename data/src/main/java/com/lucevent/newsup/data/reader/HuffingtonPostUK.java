package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;

public class HuffingtonPostUK extends com.lucevent.newsup.data.util.NewsReader_v2 {

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
                new int[]{TAG_ENCLOSURE});
    }

    @Override
    protected String parseContent(Element prop)
    {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(prop.text().replace("<br />", "<p></p>"));

        org.jsoup.select.Elements ads = doc.select("strong");
        for (org.jsoup.nodes.Element ad : ads)
            if (ad.text().contains("SEE ALSO:"))
                ad.parent().remove();
            else if (ad.text().contains("READ MORE:"))
                ad.remove();

        doc.select("ul").remove();

        String content = doc.html();
        int index = content.indexOf("<hh-");
        if (index != -1) {
            int indexM = content.indexOf("-hh>", index + 4);
            int index2 = content.indexOf("-hh>", indexM + 4);
            content = content.replace(content.substring(index, index2 + 4), "");
        }
        return content;
    }

}