package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;

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
        doc.getElementsByTag("st1:place").tagName("span");
        doc.getElementsByTag("st1:city").tagName("span");
        doc.getElementsByTag("st1:time").tagName("span");
        Elements article = doc.select(".lead-container img,.lead-container iframe,.article-content");

        if (article.isEmpty()) {
            article = doc.select(".collection-set");

            if (article.isEmpty()) {
                return;
            } else
                article.select(".total,.collection-info,.collection-item-media-player").remove();
        }
        article.select("script,.module-related,#module-more-news,.lazy-placeholder,.ad-container").remove();

        cleanAttributes(article.select("img"), "src");

        news.content = finalFormat(article, true);
    }

}
