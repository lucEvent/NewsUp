package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HelsinkiSanomat extends com.lucevent.newsup.data.util.NewsReader {

    //Tags: [category, description, enclosure, guid, item, link, media:content, pubdate, title]

    public HelsinkiSanomat()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_ENCLOSURE});
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        Elements image = doc.select(".article-main-image noscript img");
        Elements article = doc.select("[itemprop='articleBody']");

        if (article.isEmpty()) {
            return;
        }
        article.select(".hidden,script,[itemprop='video']").remove();

        for (Element figure : article.select("figure.image")) {
            figure.removeAttr("class");

            Elements img = figure.select("img");
            if (!img.isEmpty())
                figure.html(img.outerHtml());
        }

        article.select("h2").tagName("h3");
        article.select(".votsikko").tagName("h4");

        news.content = image.outerHtml().replace("src=\"//", "src=\"http://") + "<p>" + article.outerHtml()
                .replace("href=\"/", "href=\"http://www.hs.fi/").replace("src=\"//", "src=\"http://").replace("<br>", "</p><p>") + "</p>";
    }

}