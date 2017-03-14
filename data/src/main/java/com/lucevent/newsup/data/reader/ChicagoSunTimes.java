package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ChicagoSunTimes extends com.lucevent.newsup.data.util.NewsReader {

    private static final String SITE_STYLE = "<style>.wp-caption-text{font-size:12px;padding:2px 10px;display:block;}tr,th,td{padding:3px 10px;}</style>";

    /**
     * Tags
     * [category, content:encoded, dc:creator, description, enclosure, guid, item, link, media:content, media:credit, media:text, media:thumbnail, media:title, pubdate, title]
     * [category, content:encoded, dc:creator, description,            guid, item, link,                                                                        pubdate, title]
     */

    public ChicagoSunTimes()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_MEDIA_CONTENT});

        this.style = SITE_STYLE;
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return jsoupParse(prop).text();
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = jsoupParse(prop);
        doc.select("script,a[rel='nofollow'],img[width='1']").remove();

        doc.select("h1,h2").tagName("h3");
        doc.select("[style]").removeAttr("style");
        doc.select("iframe").attr("frameborder", "0");

        for (Element e : doc.select("strong")) {
            String text = e.text();
            if (text.startsWith("RELATED STORY:")
                    || text.startsWith("READ MORE:"))
                try {
                    e.parent().remove();
                } catch (Exception ignored) {
                }
        }
        NewsStylist.cleanAttributes(doc.select("img"), "src");

        return doc.body().html().replaceAll("&nbsp;", "");
    }

    @Override
    protected Enclosure parseEnclosure(Element prop)
    {
        return new Enclosure(prop.attr("url"), "image", "");
    }

}
