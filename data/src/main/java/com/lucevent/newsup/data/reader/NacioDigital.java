package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

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
                "http://www.naciodigital.cat/",
                "");
    }

    @Override
    protected News onNewsRead(News news)
    {
        // Parsing description
        Document doc = jsoupParse(news.description);
        news.description = doc.text();
        // end

        // Parsing enclosures
        Elements imgs = doc.select("img");
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
        article.select("script").remove();

        for (Element img : article.select(".divimatgeeditor,.fotoportadaampliacio")) {
            NewsStylist.cleanAttributes(img);

            Elements ns = img.select("noscript");
            img.html(ns.isEmpty() ? img.select("img").outerHtml() : ns.html()
                    + "<figcaption>" + img.text() + "</figcaption>");
        }

        article.select(".h3noticia").tagName("h4");
        NewsStylist.cleanAttributes(article.select("img"), "src");
        NewsStylist.repairLinks(article);

        news.content = article.outerHtml();
    }

}
