package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SvenskaDagbladet extends com.lucevent.newsup.data.util.NewsReader {

    // tags: [category, description, guid, item, link, pubdate, title]

    public SvenskaDagbladet()
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
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        Elements e = doc.select(".Deck,.Body");

        e.select(".Body-ad,.AdPositionData,.Body-pull,figcaption,.Quote,.ExternalLink,.Ad").remove();

        for (Element img : e.select("img")) {
            String src = img.attr("srcset");

            if (!src.startsWith("http:"))
                src = "http:" + src;

            int i = src.indexOf(" ");
            if (i != -1)
                src = src.substring(0, i);

            img.attr("src", src).removeAttr("srcset");
        }
        e.select("h2").tagName("h3");
        e.select("[class]").removeAttr("class");
        e.select("[alt]").removeAttr("alt");

        news.content = e.html().replace("=\"//", "=\"http://").replace("=\"/", "=\"http://www.svd.se/");
    }

}
