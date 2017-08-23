package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class PokemonGoHub extends com.lucevent.newsup.data.util.NewsReader {

    private static final String SITE_STYLE = "<style>.td-paragraph-padding-1{padding-left:4%;padding-right:4%;}.emoji,.wp-smiley{height:1em;width:1em;padding:0;}</style>";

    // tags: [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

    public PokemonGoHub()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{},
                "https://pokemongohub.net/",
                SITE_STYLE);
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return jsoupParse(prop).select("p").first().text();
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = jsoupParse(prop);
        doc.select("img[src*='/user_avatar/'],script").remove();
        doc.select("h6").tagName("h4");
        doc.select("h1,h2").tagName("h3");
        doc.select("pre").tagName("blockquote");
        doc.select("figure[style],img[style],iframe[style]").removeAttr("style");

        return doc.body().html();
    }

}
