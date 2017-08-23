package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoteborgsPosten extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * Tags
     * [                                       description, guid, item, link, pubdate, title]
     * [category,                  dc:creator, description, guid, item, link, pubdate, title]
     * [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]
     */

    public GoteborgsPosten()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{},
                "http://www.gp.se/",
                "");
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = jsoupParse(prop);
        doc.select("figcaption").remove();
        doc.select("[style]").removeAttr("style");
        NewsStylist.cleanAttributes(doc.select("img"), "src");
        NewsStylist.repairLinks(doc.body());
        return doc.body().html();
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements article = doc.select(".article__head .img-container,.article__preamble,.article__body__richtext,.article__body__facts");
        article.select(".article__category,script,.wp_rp_wrap,.partner").remove();

        Elements preamble = article.select(".article__preamble");
        if (!preamble.isEmpty()) {
            preamble.html("<p><b>" + preamble.text() + "</b></p>");
            preamble.tagName("p");
        }

        for (Element e : article.select("strong")) {
            String e_text = e.text();
            System.out.print("->" + e_text);
            switch (e.text()) {
                case "L\u00C4S OCKS\u00C5:":
                case "L\u00C4S MER:":
                case "L\u00C4S \u00C4VEN:":
                    try {
                        System.out.print(" (AND REMOVING :)");
                        e.parent().remove();
                    } catch (Exception elemHasNotParent) {
                    }
            }
            System.out.println("");
        }

        article.select(".article__body__facts").tagName("blockquote");
        article.select("h1,h2").tagName("h3");
        article.select("[style]").removeAttr("style");

        NewsStylist.cleanAttributes(article.select("img"), "src");
        NewsStylist.repairLinks(doc.body());

        news.content = article.outerHtml().replaceAll("&nbsp;", "");
    }

    @Override
    protected Document getDocument(String pagelink)
    {
        try {
            return org.jsoup.Jsoup.connect(pagelink)
                    .timeout(10000)
                    .userAgent(USER_AGENT)
                    .get();
        } catch (Exception ignored) {
        }
        return super.getDocument(pagelink);
    }

}
