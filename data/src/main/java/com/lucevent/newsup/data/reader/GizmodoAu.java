package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GizmodoAu extends com.lucevent.newsup.data.util.NewsReader {

    //tags: [content:encoded, dc:creator, description, feedburner:origenclosurelink, feedburner:origlink, guid, item, link, media:thumbnail, pubdate, title]

    public GizmodoAu()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_GUID},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{"feedburner:origenclosurelink".hashCode()});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return jsoupParse(prop).text();
    }


    @Override
    protected Enclosure parseEnclosure(Element prop)
    {
        return new Enclosure(prop.text().replace("t_k-small", "t_original"), "image", "");
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        Elements article = doc.select(".article-content");
        article.select(".vdb_player,script,.referenced-article,.aol-video-container").remove();

        for (Element gif : article.select("img[data-gif-image]"))
            gif.attr("src", gif.attr("data-gif-image"));

        article.select("h1,h2").tagName("h3");
        article.select(".image + p > small").tagName("figcaption");

        news.content = NewsStylist.cleanComments(article.html());
    }

}
