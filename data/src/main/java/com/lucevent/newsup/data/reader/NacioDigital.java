package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NacioDigital extends com.lucevent.newsup.data.util.NewsReader {

    //tags: [category, dc:creator, description, guid, item, link, pubdate, title]

    public NacioDigital()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{},
                "");
    }

    @Override
    protected News onNewsRead(News news)
    {
        // Parsing description
        Element article = jsoupParse(news.description);
        news.description = article.text();
        // end

        // Parsing enclosures
        Elements imgs = article.select("img");
        if (!imgs.isEmpty())
            news.enclosures.add(new Enclosure(imgs.first().attr("src"), "", ""));
        // end
        return news;
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        doc.select(".h3itemrelnoticia").remove();
        Elements article = doc.select(".h3noticia,.fotoportadaampliacio,#_markupCos");
        article.select("script,.fa-fw").remove();

        for (Element img : article.select(".divimatgeeditor:has(noscript),.fotoportadaampliacio:has(noscript)")) {
            cleanAttributes(img);

            Elements ns = img.select("noscript");
            String cap = img.text();
            img.html(ns.html() + "<figcaption>" + cap + "</figcaption>");
        }

        article.select(".peufotografia,.divimatgeeditor p").tagName("figcaption");
        article.select("[style]").removeAttr("style");

        news.content = finalFormat(article, true);
    }

}
