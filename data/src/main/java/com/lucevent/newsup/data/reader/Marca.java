package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Marca extends com.lucevent.newsup.data.util.NewsReader {

    // tags:  category, dc:creator, description, guid, item, link, media:content, media:description, media:thumbnail, media:title, pubdate, title

    public Marca()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{"media:description".hashCode()},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_MEDIA_CONTENT});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return jsoupParse(prop.text().replace("&lt;", "<").replace("&gt;", ">")).text();
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        Elements article = doc.select(".news-item");

        if (article.isEmpty()) {
            // ARTICLE EMPTY!!!!
            return;
        }

        article.select("script,.kicker,.content-ad,.section-title-group,.js-headline,.js-headlineb,.subtitle-items,.sharing-tools,.ad-item,.related-tags,.comments-btn,.aside-comments,.content,[itemprop='author']").remove();

        for (Element e : article.select("[itemprop='video']")) {
            e.html(e.select("noscript").html());
            e.select("[style]").removeAttr("style");
        }

        news.content = article.outerHtml();
    }

}
