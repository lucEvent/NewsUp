package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;

public class Sport extends com.lucevent.newsup.data.util.NewsReader {

    // tags:  description, guid, item, link, pubdate, title

    public Sport()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parseBodyFragment(prop.text()).getElementsByTag("p").text();
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        org.jsoup.select.Elements img = doc.select(".sp-img,.sp-video").select("img");

        org.jsoup.select.Elements content = doc.select(".cuerpo-noticia");
        content.select(".sp-autor").remove();

        if (!content.isEmpty())
            news.content = img.outerHtml() + content.html();
        else {
            content = doc.select(".cuerpo-opinion");
            content.select(".firma").remove();

            if (!content.isEmpty())
                news.content = content.html();
        }
    }

}