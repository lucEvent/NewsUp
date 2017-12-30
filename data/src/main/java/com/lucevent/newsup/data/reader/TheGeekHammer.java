package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class TheGeekHammer extends com.lucevent.newsup.data.util.NewsReader {

    //tags: [category, content:encoded, dc:creator, description, guid, item, link, post-id, pubdate, title]

    public TheGeekHammer()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{},
                "");
    }

    @Override
    protected String parseContent(Element prop)
    {
        Element article = jsoupParse(prop);
        article.select("script,.yuzo_related_post,.code-block").remove();

        for (Element iframe : article.select("iframe"))
            iframe.removeAttr("style")
                    .attr("frameborder", "0");

        cleanAttributes(article.select("img"), "src");

        return finalFormat(article, false);
    }

    @Override
    protected Document getDocument(String url)
    {
        try {
            return org.jsoup.Jsoup.connect(url)
                    .userAgent(USER_AGENT)
                    .timeout(10000)
                    .validateTLSCertificates(false)
                    .get();
        } catch (Exception ignored) {
        }
        return super.getDocument(url);
    }

}
