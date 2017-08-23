package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ComingSoon extends com.lucevent.newsup.data.util.NewsReader {

    //tags: [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

    public ComingSoon()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{},
                "http://www.comingsoon.net/",
                "");
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return jsoupParse(prop).select("p").first().text();
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = jsoupParse(prop);

        for (Element slideshow : doc.select(".pbslideshow-wrapper")) {
            StringBuilder res = new StringBuilder();
            for (Element img : slideshow.select("img")) {
                res.append("<figure>")
                        .append(NewsStylist.img(img.attr("src")))
                        .append("<div class='caption'>").append(img.attr("alt")).append("</div>")
                        .append("</figure>");

            }
            slideshow.html(res.toString());
        }
        for (Element rel : doc.select("a:has(strong,b),strong:has(a)")) {
            String text = rel.text();
            if (text.startsWith("RELATED"))
                rel.remove();
        }
        doc.select("h2:has(img)").tagName("p");
        doc.select(".caption").tagName("figcaption");
        doc.select("script").remove();

        Elements titles = doc.select("h2");
        if (!titles.isEmpty()) {
            titles.first().remove();
            titles.tagName("h3");
        }

        NewsStylist.repairLinks(doc.body());
        NewsStylist.cleanAttributes(doc.select("img"), "src");

        return doc.body().html();
    }

}
