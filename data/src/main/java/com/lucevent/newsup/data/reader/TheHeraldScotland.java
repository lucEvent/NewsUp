package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TheHeraldScotland extends com.lucevent.newsup.data.util.NewsReader {

    //tags: [description, guid, item, link, pubdate, title]

    public TheHeraldScotland()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{});
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements article = doc.select("#article");

        Elements content = article.select(".article-hero img,.article-hero a,.article-body");
        content.select("script,#subscription-spinner,.advert-container,.clearfix,.related-articles").remove();

        for (Element e : content.select("[martini-mobile-src]")) {
            String src = e.attr("martini-mobile-src");
            int index = src.indexOf("?");
            if (index != -1)
                src = src.substring(0, index);

            e.attr("src", src);
            e.removeAttr("martini-mobile-src");
            e.removeAttr("alt");
        }
        for (Element e : content.select("a[class=\"responsive-image\"]"))
            e.tagName("img");

        for (Element e : content.select("strong")) {
            String text = e.text();
            if (text.startsWith("READ MORE") || text.startsWith("Read more"))
                e.parent().remove();
        }

        news.content = content.outerHtml().replace("src=\"/", "src=\"http://heraldscotland.com/");
    }

}
