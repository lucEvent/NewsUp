package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ElPeriodicoEs extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * Tags
     * [description, guid, item, link, pubdate, title]
     * [category, comments, content:encoded, dc:creator, description, guid, item, link, pubdate,  title]
     */

    public ElPeriodicoEs()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{});
    }

    @Override
    protected String parseLink(Element prop)
    {
        String link = prop.text();
        if (link.startsWith("/"))
            link = "http://www.elperiodico.com" + link;
        return link;
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text().replace("&lt;br /&gt;", ". ");
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = org.jsoup.Jsoup.parse(prop.text());
        doc.select("[style]").removeAttr("style");
        doc.select("h1,h2").tagName("h3");
        doc.select(".subtitle").tagName("figcaption");
        return doc.html().replace("<span>", "").replace("</span>", "").replace("<p>&nbsp;</p>", "");
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements article = doc.select(".ep-detail-body");

        if (article.isEmpty()) {
            article = doc.select(".slider-item,.onbcn-detail-body");

            article.select(".onbcn-themes-related").remove();

        } else
            article.select("style,script,.hidden-md,.hidden-sm,.ep-toolbar,.close,.ep-related,.ep-opinion,.bottom,.custom-navigation").remove();

        if (!doc.select(".ep-galeria-v2").isEmpty())
            doc.select(".ep-media").remove();

        article.select(".despiece-bottom").tagName("blockquote");
        article.select(".subtitle").tagName("figcaption");
        article.select("h1,h2").tagName("h3");

        NewsStylist.cleanAttributes(article.select(".slide,.slider-item"));

        news.content = article.html();
    }

}
