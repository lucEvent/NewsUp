package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class People extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * Tags
     * [category, dc:creator, description, guid, item, link, media:content, pubdate, title]
     * [category, dc:creator, description, guid, item, link,                pubdate, title]
     */

    public People()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_MEDIA_CONTENT});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text();
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        Elements article = doc.select(".article-single__content");
        article.select("script,noscript,.article-footer,.article-figure__share,figcaption,.article-body__show-full,.brightcove-player").remove();

        for (Element e : article.select("strong")) {
            String text = e.text();
            if (text.startsWith("RELATED VIDEO")
                    || text.startsWith("VIDEO")
                    || text.startsWith("RELATED")) {
                e.parent().remove();
            }
        }
        for (Element img : article.select("img[data-src]")) {
            img.attr("src", img.attr("data-src"));
            img.removeAttr("data-src").removeAttr("width").removeAttr("height");
        }

        article.select("h1,h2").tagName("h3");

        news.content = article.html().replace("<p>&nbsp;</p>", "");
    }

}
