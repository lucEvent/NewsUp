package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DiarioCordoba extends com.lucevent.newsup.data.util.NewsReader {

    // Tags: [category, dc:creator, description, guid, item, link, pubdate, title]

    public DiarioCordoba()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{},
                "http://www.diariocordoba.com/",
                "");
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return prop.text().replace("<p>", "").replace("</p>", "");
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        Elements article = doc.select(".FotoDeNoticia,#TextoNoticia");
        article.select("script,.Creatividad,.NoticiasRelacionadasDeNoticia,.Recorte").remove();

        article.select(".PieDeFoto").tagName("figcaption");
        NewsStylist.cleanAttributes(article.select("img"), "src");
        NewsStylist.repairLinks(article);

        for (Element video : article.select(".PlayerVideoBOTR")) {
            video.tagName("video");

            String src = video.attr("id");
            int i1 = src.indexOf("_") + 1;
            int i2 = src.indexOf("_", i1);
            if (i1 > 0 && i2 > 0) {
                src = "http://content.jwplatform.com/videos/" + src.substring(i1, i2) + ".mp4";
                video.attr("src", src).attr("controls", "");
            }
        }

        news.content = NewsStylist.cleanComments(article.html());
    }

}