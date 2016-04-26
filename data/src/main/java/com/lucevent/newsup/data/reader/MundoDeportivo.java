package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public class MundoDeportivo extends com.lucevent.newsup.data.util.NewsReader_v2 {

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
        String intro = doc.select("[itemprop=\"alternativeHeadline\"]").outerHtml();
        org.jsoup.select.Elements imgs = doc.select("[itemprop=\"image\"] img,.gallery-leaf-figure img");

        StringBuilder img = new StringBuilder();
        if (!imgs.isEmpty())
            for (org.jsoup.nodes.Element i : imgs) {
                String attr = i.attr("data-src-md");
                if (attr.isEmpty())
                    attr = i.attr("src");

                img.append("<img src=\"").append(attr).append("\">");
            }

        org.jsoup.select.Elements metas = doc.select("[itemprop=\"video\"] [itemprop=\"image\"]");
        if (!metas.isEmpty())
            for (org.jsoup.nodes.Element i : metas)
                img.append("<img src=\"").append(i.attr("content")).append("\">");

        org.jsoup.select.Elements content = doc.select("[itemprop=\"articleBody\"]");
        content.select(".datetime-story-leaf,.gallery-story-leaf-figcaption").remove();

        news.content = intro + img.toString() + content.html();
    }

}
