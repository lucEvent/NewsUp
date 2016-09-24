package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PCWorld extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * tags:
     * [author, categories, category, dc:creator, description, item, link, media:content, media:thumbnail, pubdate, title]
     * [description, enclosure, guid, item, link, media:category, media:content, media:credit, media:description, media:keywords, media:thumbnail, media:title, pubdate, title]
     **/

    public PCWorld()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY, "media:category".hashCode()},
                new int[]{"media:thumbnail".hashCode(), TAG_MEDIA_CONTENT});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        String dsc = Jsoup.parse(prop.text()).text();
        return dsc.substring(0, Math.min(300, dsc.length()));
    }

    /* Only when video section is working
        @Override
        protected News applySpecialCase(News news, String content)
        {
            if (news.link.contains("pcworld.com/video"))
                for (Enclosure e : news.enclosures)
                    if (e.isVideo()) {
                        news.content = e.html();
                        break;
                    }
            return news;
        }
    */

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        Elements article = doc.select("article");

        if (!news.link.contains("pcworld")) {

            if (news.link.contains("greenbot.com")) {
                article = article.select(".hero-img,.deck,[itemprop=\"reviewBody\"],[itemprop=\"articleBody\"]");
                article.select("h2").tagName("h3");
                news.content = article.html();
            }
        } else if (article.isEmpty()) {

            article = doc.select(".carousel-inside-crop");
            article.select(".image-info,#ss-bottom-nav").remove();
            article.select("h2").tagName("h3");

            news.content = article.html().replace("data-original", "src");

        } else {
            article.select(".breadcrumbs,[itemprop=\"name\"],.sharing-tools,#comment-bubble-idgcsmb,.article-meta,script,.department,.similar-videos,link").remove();
            article.select(".pagination,.tocWrapper,.product-sidebar").remove();
            article.select("h2").tagName("h3");

            news.content = article.html();
        }
    }

}
