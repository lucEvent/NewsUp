package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TechCrunch extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * tags:
     * [category, dc:creator, description, guid, item, link, media:content, media:thumbnail, media:title, pubdate, title]
     * [category, dc:creator, description, enclosure, guid, item, link, media:content, media:thumbnail, media:title, pubdate, title]
     */

    public TechCrunch()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_ENCLOSURE, TAG_MEDIA_CONTENT});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text();
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements article = doc.select("article .article-entry");

        if (article.isEmpty()) {
            article = doc.select(".textwidget");

            if (article.isEmpty()) {
                article = doc.select(".slide");
                article.select(".slide-last").html("");
            }

        }
        for (Element e : article.select(".vdb_player")) {
            Elements script = e.select("script");
            if (script.isEmpty())
                continue;

            String video_id = NewsStylist.subStringBetween(script.first().attr("src"), "vid=", "/", false);

            String url = doc.baseUri();
            int i1 = url.indexOf(".com/");
            if (i1 == -1)
                continue;

            String date = url.substring(i1 + 5, i1 + 16).replaceFirst("/", "-");

            e.html(Enclosure.iframe("https://cdn.vidible.tv/prod/" + date + video_id + "_v2.orig.mp4"));
            NewsStylist.cleanAttributes(e);
        }

        article.select("script,.aside-related-articles,.controls,.slideshow .enter-wrapper,.inset-section,.social-share,.inset-ad,.contributor-byline").remove();
        article.select("div").tagName("p");

        for (Element img : article.select("img")) {
            String src = img.attr("data-full-size-image");
            if (!src.isEmpty())
                img.attr("src", src);

            NewsStylist.cleanAttributes(img, "src");
        }

        article.select("h1,h2").tagName("h3");
        article.select("[style]").removeAttr("style");
        article.select("iframe").attr("frameborder", "0");

        news.content = NewsStylist.cleanComments(article.html());
    }

}
