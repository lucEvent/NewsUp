package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Elle extends com.lucevent.newsup.data.util.NewsReader {

    //tags: [category, description, guid, item, link, pubdate, title]

    public Elle()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text();
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements article = doc.select(".body-el-text,.embed--inner,.embedded-image--lead img,.standard-article-body--text > iframe,.embedded-image--inner img,.body-el-h2");
        for (Element e : article.select("iframe,img")) {
            String src = e.attr("data-src");
            if (!src.isEmpty()) {
                e.attr("src", src);
                e.removeAttr("data-src");
                e.removeAttr("class");
                e.removeAttr("data-img320");
                e.removeAttr("data-zoom");
                e.removeAttr("data-img480");
                e.removeAttr("data-img768");
                e.removeAttr("data-img640");
            }
        }
        for (Element e : article.select("h2"))
            if (!e.children().isEmpty())
                e.html("");

        news.content = article.outerHtml();
    }

}
