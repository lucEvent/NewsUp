package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public class HuffingtonPostUK extends com.lucevent.newsup.data.util.NewsReader_v2 {

    private String style = "<style>blockquote{margin:10px;padding:5px 10px 5px 10px;background-color:#f2f2f2}</style>";

    /**
     * Tags
     * [author, description, enclosure, guid, item, link, pubdate, title]
     * [author, content, entry, id, link, name, published, title, updated, uri]
     */

    public HuffingtonPostUK()
    {
        super(TAG_ITEM_ITEMS_ENTRY,
                new int[]{TAG_TITLE},
                new int[]{TAG_GUID, TAG_ID},
                new int[]{TAG_CONTENT},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_PUBDATE, TAG_UPDATED},
                new int[]{},
                new int[]{});
        super.style = style;
    }

    @Override
    protected String parseDescription(Element prop)
    {
        String description = org.jsoup.Jsoup.parse(prop.text()).text();
        int index = description.indexOf("...");
        if (index != -1)
            description = description.substring(0, index);
        return description;
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

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        org.jsoup.select.Elements content = doc.select(".feature-section");

        if (!content.isEmpty()) {
            doc.select(".feature-section > section,.feature-section > div").remove();
        } else {
            doc.select(".entry__body > section,.entry__body > div").remove();

            content = doc.select(".entry__body");
        }

        org.jsoup.select.Elements ads = content.select("strong");
        for (org.jsoup.nodes.Element ad : ads) {
            String adtext = ad.text();
            if (adtext.contains("SEE ALSO:") || adtext.contains("READ MORE:")
                    || adtext.contains("LIKE US ON FACEBOOK")) {
                ad.parent().remove();
            }
        }
        content.select("ul,script,.slideshow-thumb").remove();

        news.content = content.html();
    }

}