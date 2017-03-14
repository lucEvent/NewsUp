package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoteborgsPosten extends com.lucevent.newsup.data.util.NewsReader {

    private static final String SITE_STYLE = "<style>figcaption{font-size:12px;padding:2px 10px;display:block;}tr,th,td{padding:3px 10px;}</style>";

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
                new int[]{});

        this.style = NewsStylist.base("http://www.gp.se/") + SITE_STYLE;
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = jsoupParse(prop);
        doc.select("figcaption").remove();
        doc.select("[style]").removeAttr("style");
        NewsStylist.cleanAttributes(doc.select("img"), "src");
        return doc.body().html();
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements article = doc.select(".article__head .img-container,.article__preamble,.article__body__richtext,.article__body__facts");
        article.select(".article__category,script,.wp_rp_wrap,.partner").remove();

        Elements preamble = article.select(".article__preamble");
        if (!preamble.isEmpty()) {
            preamble.html("<b>" + preamble.text() + "</b>");
        }
        article.select(".article__preamble").tagName("p");
        article.select(".article__body__facts").tagName("blockquote");
        article.select("h1,h2").tagName("h3");
        article.select("[style]").removeAttr("style");

        NewsStylist.cleanAttributes(article.select("img"), "src");

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
