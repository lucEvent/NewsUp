package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Attribute;
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
                new int[]{},
                "http://heraldscotland.com/",
                "");
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements article = doc.select("#article");
        article.select("#gallery_interstitial_skip").remove();

        Elements content = article.select(".article-hero img,.article-hero a,.article-body");
        content.select("script,#subscription-spinner,.advert-container,.clearfix,.related-articles,figcaption").remove();

        content.select("a[class='responsive-image']").tagName("img");
        content.select("h1,h2").tagName("h3");

        for (Element img : content.select("[martini-mobile-src]")) {
            String src = img.attr("martini-mobile-src");
            int index = src.indexOf("?");
            if (index != -1)
                src = src.substring(0, index);

            img.attr("src", src);
        }
        for (Element img : content.select("img")) {
            String src = img.attr("src");
            for (Attribute attr : img.attributes())
                img.removeAttr(attr.getKey());
            img.attr("src", src);
        }
        for (Element e : content.select("strong")) {
            String text = e.text();
            if (text.startsWith("READ MORE") || text.startsWith("Read more"))
                e.parent().remove();
        }

        news.content = content.outerHtml();
    }

}
