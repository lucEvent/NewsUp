package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GizmodoEs extends com.lucevent.newsup.data.util.NewsReader {

    //tags: [category, dc:creator, description, guid, item, link, pubdate, title]

    public GizmodoEs()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{},
                "http://es.gizmodo.com/",
                "");
    }

    @Override
    protected News onNewsRead(News news)
    {
        Document doc = jsoupParse(news.description);
        if (news.description.contains(">Read more...<")) {

            news.description = doc.text();

            Elements imgs = doc.select("img");
            if (!imgs.isEmpty())
                news.enclosures.add(new Enclosure(imgs.first().attr("src"), "image", ""));

        } else {

            doc.select(".ad-container,.js_ad-dynamic").remove();

            doc.select("h1,h2").tagName("h3");
            doc.select(".pullquote").tagName("blockquote");

            for (Element e : doc.select("figure.js_marquee-assetfigure:has(picture)")) {
                e.removeAttr("style");
                e.html(e.select("picture,figcaption").outerHtml());
            }

            NewsStylist.cleanAttributes(doc.select("img"), "src");

            news.content = NewsStylist.cleanComments(doc.body().html());
        }

        return news;
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements article = doc.select(".post-content");
        article.select(".referenced-wide,.js_ad-dynamic,[class^='ad-'],.related-module").remove();

        for (Element e : article.select("figure"))
            e.html(e.select("img,figcaption").outerHtml());

        for (Element img : article.select("img[data-anim-src]")) {
            String src = img.attr("data-anim-src");
            NewsStylist.cleanAttributes(img);
            img.attr("src", src);
        }
        for (Element img : article.select("img[data-mp4-src]")) {
            String src = img.attr("data-mp4-src");
            NewsStylist.cleanAttributes(img);
            img.attr("src", src);
        }
        for (Element iframe : article.select("iframe")) {
            String src = iframe.attr("data-recommend-id");
            if (src.startsWith("youtube"))
                src = src.replace("youtube://", "https://www.youtube.com/embed/");
            else if (src.startsWith("vimeo"))
                src = src.replace("vimeo://", "https://player.vimeo.com/video/");
            else
                src = iframe.attr("data-src");

            iframe.parent().html(Enclosure.iframe(src));
        }

        article.select("img[data-frozen-src]").remove();
        article.select("[style]").removeAttr("style");
        article.select("h1,h2").tagName("h3");

        news.content = NewsStylist.cleanComments(article.html());
    }


}
