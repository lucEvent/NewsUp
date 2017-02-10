package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ElPeriodicoEs extends com.lucevent.newsup.data.util.NewsReader {

    private static final String SITE_STYLE = "<style>.subtitle{font-size:12px;padding:2px 10px;display:block;}</style>";

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

        this.style = SITE_STYLE;
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

        article.select("[width]").removeAttr("width");
        article.select("[style]").removeAttr("style");

        news.content = article.outerHtml();
    }

}
