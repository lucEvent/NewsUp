package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HuffingtonPostSpain extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * Tags:
     * [author, description, enclosure, guid, item, link, pubdate, title]
     * [author, content, entry, id, link, name, published, title, updated, uri]
     */

    public HuffingtonPostSpain()
    {
        super(TAG_ITEM_ITEMS_ENTRY,
                new int[]{TAG_TITLE},
                new int[]{TAG_ID, TAG_GUID},
                new int[]{TAG_CONTENT},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_PUBDATE, TAG_UPDATED},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_ENCLOSURE});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        String description = org.jsoup.Jsoup.parse(prop.text()).text();
        int index = description.indexOf("Leer más:");
        if (index != -1)
            description = description.substring(0, index);
        return description;
    }

    @Override
    protected String parseContent(Element prop)
    {
        Element body = org.jsoup.Jsoup.parse(prop.text().replace("<br />", "<p></p>")).getElementsByTag("body").get(0);
        Elements ee = body.children();

        int index = ee.indexOf(ee.select("blockquote").last()) - 1;
        if (index >= 0)
            for (; index < ee.size(); ++index)
                ee.get(index).remove();

        cleanBlockquotes(body.select("blockquote"));

        String content = body.outerHtml();
        index = content.indexOf("<hh--");
        if (index != -1)
            content = content.substring(0, index);

        return content;
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        doc.select("script").remove();

        org.jsoup.select.Elements e = doc.select(".top-media,.entry__body");
        e.select("[style=\"display: none;\"],.slideshow-thumb,.tag-cloud,.comment-button,.corrections-links,.read-more,.extra-content").remove();

        cleanBlockquotes(e.select("blockquote"));

        for (Element ps : e.select(".entry__body > p"))
            if (ps.text().isEmpty())
                ps.remove();

        Element ps = e.select("div").last();
        if (ps != null)
            if (ps.text().contains("Ve a nuestra portada"))
                ps.remove();

        news.content = e.html();
    }

    private void cleanBlockquotes(Elements select)
    {
        for (Element bq : select) {
            String text = bq.text();

            if (text.contains("ADEMÁS") || text.contains("TE PUEDE INTERESAR")
                    || text.contains("AVÍSANOS") || text.contains("MÁS SOBRE")
                    || text.contains("MÁS PARA"))
                bq.remove();
        }
    }

}