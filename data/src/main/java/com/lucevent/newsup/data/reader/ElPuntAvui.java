package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ElPuntAvui extends com.lucevent.newsup.data.util.NewsReader {

    //tags: [author, category, description, guid, item, link, pubdate, title]

    public ElPuntAvui()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{},
                "http://www.elpuntavui.cat/",
                "");
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return jsoupParse(prop).text();
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements article = doc.select("article").select("header .subtitol,.bxslider img,.bxslider .caption,#article-content,.article-fitxes");
        article.select("script").remove();

        for (Element subtitle : article.select(".subtitol")) {
            subtitle.tagName("p");
            subtitle.html("<li>" + subtitle.html() + "</li>");
            NewsStylist.cleanAttributes(subtitle);
        }
        article.select(".caption").tagName("figcaption");
        article.select(".article-fitxes").tagName("blockquote");
        article.select(".formatperfil,.formatpre").tagName("h4");

        news.content = article.outerHtml();
    }

}
