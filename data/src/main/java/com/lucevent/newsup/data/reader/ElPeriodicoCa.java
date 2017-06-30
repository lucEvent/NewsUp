package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ElPeriodicoCa extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * Tags
     * [                                       description, guid, item, link, pubdate, title]
     * [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]
     */

    public ElPeriodicoCa()
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
            link = "http://www.elperiodico.cat" + link;
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
        doc.select("script").remove();

        Elements article = doc.select(".ep-img img,.ep-galeria .slider img,.ep-video img,"
                + ".comp iframe:not(.cuerpo-noticia iframe,.cuerpo-opinion iframe),"
                + ".cuerpo-noticia > *:not(.complementos,.despiece,.destacado,.claves,.frase),"
                + ".cuerpo-opinion > *:not(.complementos,.despiece,.destacado,.claves,.frase)");

        if (article.isEmpty()) {
            article = doc.select("article .onbcn-slider,article .onbcn-detail-body");

            article.select(".custom-navigation,.box-left,.player-zeta-ob").remove();
        }

        article.select(".despiece-bottom").tagName("blockquote");
        article.select("h2").tagName("h3");
        article.select(".subtitle").tagName("figcaption");

        article.select("[width]").removeAttr("width");
        article.select("[style]").removeAttr("style");

        news.content = article.outerHtml();
    }

}
