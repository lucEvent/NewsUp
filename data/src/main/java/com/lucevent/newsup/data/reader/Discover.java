package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public class Discover extends com.lucevent.newsup.data.util.NewsReader {

    // Tags: [description, guid, item, link, media:content, media:thumbnail, pubdate, title]

    public Discover()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{TAG_MEDIA_CONTENT});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text();
    }

    @Override
    protected Enclosure parseEnclosure(Element prop)
    {
        return new Enclosure(prop.text(), "image", "0");
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        doc.select("script").remove();

        org.jsoup.select.Elements content = doc.select(".entry > p,.entry img:not(.entry > p img),.entry > blockquote,.entry h4");

        if (!content.isEmpty()) {

            for (Element ad : content.select("strong"))
                if (ad.text().startsWith("SEE ALSO"))
                    ad.parent().text("");

            for (Element ad : content.select(".Z3988"))
                ad.parent().text("");

            news.content = content.outerHtml();

        } else {

            content = doc.select(".segment .mediaContainer,.segment .content");
            for (Element img : content.select("img")) {
                String src = img.attr("src");
                if (!src.startsWith("http"))
                    img.attr("src", "http://discovermagazine.com" + src);
            }
            content.select(".mobile,.credit").remove();
            news.content = content.html();
        }
    }

}