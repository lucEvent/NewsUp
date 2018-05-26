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
        Elements article = doc.select(".amp_subtitol,.amp_fotografia_peu,.amp_textnoticia");
        article.select("script,.intext-publi,.infolandingsocnacio_noticies").remove();

        for (Element img : article.select(".amp_fotografia noscript")) {
            img.parent().html(img.html());
        }
        for (Element div : article.select(".divimatgeeditor:has(noscript)")) {
            Elements img = div.select("noscript");
            Elements caption = div.select("em").tagName("figcaption");
            div.html(img.html()+caption.outerHtml());
        }
        for (Element e :article.select("img[src]")) {
            String src = e.attr("src").replace("..//../", "");
            cleanAttributes(e);
            e.attr("src", src);
        }

        cleanAttributes(article.select(".divimatgeeditor"));
        article.select(".amp_subtitol h2").tagName("b");
        article.select(".amp_peu_fotografia").tagName("figcaption");
        article.select("[style]").removeAttr("style");
        article.select("noscript").remove();

        news.content = finalFormat(article, true);
    }

}
