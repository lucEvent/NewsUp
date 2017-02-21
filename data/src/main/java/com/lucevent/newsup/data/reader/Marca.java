package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Marca extends com.lucevent.newsup.data.util.NewsReader {

    // tags:  category, dc:creator, description, guid, item, link, media:content, media:description, media:thumbnail, media:title, pubdate, title

    public Marca()
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
        return org.jsoup.Jsoup.parse(prop.text()).text().replace("Leer", "");
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        doc.select("script").remove();

        Elements article = doc.select(".news-item");

        if (article.isEmpty()) {

            article = doc.select("#contenido-noticia");

            if (!article.isEmpty()) {

                Elements img = doc.select(".cubrereproductor noscript");
                Elements content = doc.select(".cuerpo_articulo > p");


                if (content.isEmpty()) {
                    content = article.select(".bloque-foto img");
                    content.select("[style]").removeAttr("style");

                    news.content = content.outerHtml();
                } else {
                    content.select("[style]").removeAttr("style");

                    news.content = img.html() + content.outerHtml();
                }
            } else {
                // TODO: 10/02/2017
            }
        } else {

            article = doc.select(".full-image:not([itemprop='articleBody'] .full-image),[itemprop='video']:not([itemprop='articleBody'] [itemprop='video']),[itemprop='articleBody'] > p,[itemprop='articleBody'] > blockquote,[itemprop='articleBody'] figure,h3.list-header");
            if (!article.isEmpty()) {

                for (Element video : article.select("[itemprop='video']")) {
                    String noscript = video.select("noscript").html();
                    String descr = video.select("[itemprop='description']").text();
                    video.html(noscript + "<figcaption>" + descr + "</figcaption>");

                    video.removeAttr("itemtype").removeAttr("class").removeAttr("itemprop");
                }
                article.select("[style]").removeAttr("style");

                NewsStylist.completeSrcHttp(article);

                news.content = article.outerHtml();
            }
        }
    }

}
