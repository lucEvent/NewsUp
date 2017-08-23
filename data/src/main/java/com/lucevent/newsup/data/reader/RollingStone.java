package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RollingStone extends com.lucevent.newsup.data.util.NewsReader {

    private static final String SITE_STYLE = "<style>.collection-nav-nojs{padding:0;list-style:none;}.collection-nav-nojs a{color:#fff;background-color:#c81429;display:block;mar" +
            "gin:1em;padding:.8em;font-size:22px;text-align:center;text-transform:uppercase;border-radius:.25em;text-decoration:none;}</style>";

    // tags: [content:encoded, guid, item, link, media:content, media:group, media:thumbnail, pubdate, title]

    public RollingStone()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{TAG_MEDIA_CONTENT},
                "http://www.rollingstone.com/",
                SITE_STYLE);
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text();
    }

    @Override
    protected Enclosure parseEnclosure(Element prop)
    {
        String url = prop.attr("url");
        if (url.contains("/featured/")) {
            return new Enclosure(url, prop.attr("type"), prop.attr("length"));
        }
        return null;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        Elements article = doc.select(".lead-container img,.lead-container iframe,.article-content");

        if (article.isEmpty()) {
            article = doc.select(".collection-set");

            if (article.isEmpty()) {
                return;
            } else {
                article.select(".total,.collection-info,.collection-item-media-player").remove();
            }
        }
        article.select(".module-related,#module-more-news,figcaption,script").remove();
        article.select("h1,h2").tagName("h3");

        NewsStylist.cleanAttributes(article.select("img"), "src");
        NewsStylist.repairLinks(article);

        news.content = article.outerHtml();
    }

}
