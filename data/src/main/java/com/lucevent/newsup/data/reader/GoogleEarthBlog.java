package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

public class GoogleEarthBlog extends com.lucevent.newsup.data.util.NewsReader {

    private static final String SITE_STYLE = "<style>img[src$='mac-cmdkey.gif'],img[src$='gelogoicon.gif'],img[src*='/icons/']{width:4%;}code{display:block;padding: 5px 0;wid" +
            "th:100%;overflow:scroll;font-size:16px;font-family:courier;}code:before{content:'Slide left to see more';color:#777777;display:block;padding:12px 0 5px;}</style>";

    //tags: [category, content:encoded, dc:creator, description, guid, item, link, post-id, pubdate, title]

    public GoogleEarthBlog()
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
    protected String parseDescription(Element prop)
    {
        String description = org.jsoup.Jsoup.parse(prop.text()).text();

        int index = description.indexOf(" […]");
        if (index > 0)
            description = description.substring(0, index) + "...";

        return description;
    }

    protected String parseContent(Element prop)
    {
        Document doc = org.jsoup.Jsoup.parse(prop.text());
        doc.select("script").remove();

        doc.select("h1,h2").tagName("h3");
        doc.select("img+br+i").tagName("figcaption");
        doc.select("[style]").removeAttr("style");
        doc.select("[width]").removeAttr("width");
        doc.select("iframe").attr("frameborder", "0");

        for (Element img : doc.select("img")) {
            String src = img.attr("src");
            img.attr("src", src.replace("resize=150%2C150", "resize=676%2C530"));
            if (src.endsWith("mac-cmdkey.gif") && src.endsWith("gelogoicon.gif") && src.contains("/icons/"))
                img.wrap("<p>");
        }
        return doc.body().html();
    }

    @Override
    protected org.jsoup.nodes.Document getDocument(String rsslink)
    {
        try {
            return org.jsoup.Jsoup.connect(rsslink)
                    .parser(Parser.xmlParser())
                    .userAgent(USER_AGENT)
                    .get();
        } catch (Exception ignored) {
        }
        return super.getDocument(rsslink);
    }

}
