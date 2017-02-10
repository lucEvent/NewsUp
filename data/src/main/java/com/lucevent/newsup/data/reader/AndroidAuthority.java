package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class AndroidAuthority extends com.lucevent.newsup.data.util.NewsReader {

    private static final String SITE_STYLE = "<style>h3 img.alignleft{float:left;margin:10px 15px 10px 0;width:150px;}.aa_custom_button_wrapp{width:50%;padding:8px 20px;margin:" +
            "5px auto;background-color:#55cc3a;border-radius:3px;cursor:pointer;}.aa_custom_button_wrapp a{font-size:14px;text-align:center;color:#fff;text-decoration:none;font" +
            "-weight:bold;text-transform:uppercase;}</style>";

    /**
     * Tags
     * [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]
     * [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title, media:content]
     * [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title, media:content, enclosure]
     */

    public AndroidAuthority()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_MEDIA_CONTENT});

        this.style = SITE_STYLE;
    }

    @Override
    protected Enclosure parseEnclosure(Element prop)
    {
        return new Enclosure(prop.attr("url"), "image", "");
    }

    @Override
    protected String parseContent(Element prop)
    {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(prop.text());
        doc.select("script,.aa_see_also_block,.clear").remove();
        doc.select("img[srcset]").removeAttr("srcset");
        doc.select("[style]").removeAttr("style");
        doc.select("h1,h2").tagName("h3");

        for (Element e : doc.select(".youtube-player")) {
            e.parent().html(Enclosure.iframe("https://www.youtube.com/embed/" + e.attr("data-id")));
        }
        return doc.html();
    }

    @Override
    protected Document getDocument(String pagelink)
    {
        try {
            return org.jsoup.Jsoup.connect(pagelink)
                    .timeout(10000)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                    .get();
        } catch (Exception e) {
            System.out.println("[" + e.getClass().getSimpleName() + "] Can't read page. Trying again");
        }
        return super.getDocument(pagelink);
    }

}
