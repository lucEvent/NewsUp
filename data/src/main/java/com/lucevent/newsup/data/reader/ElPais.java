package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ElPais extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * Tags:
     * [category, comments, content:encoded, dc:creator, description, enclosure, guid, item, link, pubdate, title]
     * [dc:creator, description, enclosure, guid, item, link, pubdate, title]
     * [author, description, guid, item, link, pubdate, title]
     */

    public ElPais()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_ENCLOSURE},
                "https://elpais.com/",
                "");
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        Elements article = doc.select("#articulo-introduccion p,[representativeofpage='true'] img,#cuerpo_noticia > p,#cuerpo_noticia > h3,#cuerpo_noticia > .sumario_foto img,.sumario_eskup .sumario__interior,.articulo-apertura .articulo-media");

        if (article.isEmpty()) {

            article = doc.select("#contenedorfotos figure");
            if (article.isEmpty()) {

                article = doc.select("article .photo_description img:not(img[src='']),#article_container > div:not(.adv_aside,.aside_summary),#article_container > p");
                if (article.isEmpty()) {
                    article = doc.select(".entry-content");
                }
            } else {

                for (Element div : article.select(".sin_enlace")) {
                    Elements imgs = div.select("meta[itemprop='url']");
                    if (!imgs.isEmpty()) {
                        Element img = imgs.get(0);
                        img.tagName("img");
                        img.attr("src", img.attr("content"));
                        img.removeAttr("content");
                        div.html(img.outerHtml());
                    }
                }
            }
        } else {
            article.select(".sin_enlace").remove();
        }
        article.select("style,script,noscript,.copyfoto").remove();

        article.select("h1,h2").tagName("h3");
        article.select(".wp-caption-text").tagName("figcaption");
        article.select("[style]").removeAttr("style");

        NewsStylist.cleanAttributes(article.select("img"), "src");
        NewsStylist.repairLinks(article);

        news.content = article.outerHtml();
    }

}