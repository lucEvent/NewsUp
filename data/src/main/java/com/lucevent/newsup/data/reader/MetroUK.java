package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MetroUK extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * Tags
     * [category, content:encoded, dc:creator, description, enclosure, guid, item, link, media:content, media:description, media:thumbnail, media:title, pubdate, section, title]
     * [category, content:encoded, dc:creator, description, enclosure, guid, item, link, media:content, media:description, media:thumbnail, media:title, pubdate,          title]
     * [category, content:encoded, dc:creator, description,            guid, item, link, media:content, media:description, media:thumbnail, media:title, pubdate,          title]
     **/

    public MetroUK()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{"media:thumbnail".hashCode()});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return Jsoup.parse(prop.text()).text();
    }

    @Override
    protected Enclosure parseEnclosure(Element prop)
    {
        String url = prop.attr("url");
        int index = url.indexOf('?');
        if (index != -1) url = url.split("\\?")[0];
        return new Enclosure(url + "?quality=25", "image", "0");
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        Elements article = doc.select(".article-body");

        article.select("script,figcaption,.item-share,.thumbnail-link,.zone-post-strip,#article-below-content,.anchor,.mor-link,.metro-sassy-poll,[data-track^='fb-promo'],.metro-email-signup,form,span.icon-video").remove();

        for (Element video : article.select("div video")) {
            video.attr("controls", "");
            video.parent().html(video.outerHtml());
        }
        article.select("img").removeAttr("alt").removeAttr("width").removeAttr("height");
        article.select("[style]").removeAttr("style");
        article.select("[class]:not([class^='twitter'])").removeAttr("class");
        article.select("h2").tagName("h3");

        news.content = article.html().replace("<p></p>", "").replace("<p>&nbsp;</p>", "");
    }

}
