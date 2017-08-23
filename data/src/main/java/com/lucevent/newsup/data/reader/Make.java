package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Make extends com.lucevent.newsup.data.util.NewsReader {

    // Tags [category, dc:creator, description, guid, item, link, post-id, pubdate, title]

    public Make()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{},
                "http://makezine.com/",
                "");
    }

    @Override
    protected News onNewsRead(News news)
    {
        if (!news.description.isEmpty()) {
            Document doc = org.jsoup.Jsoup.parse(news.description);

            Elements enc = doc.select("img");
            if (!enc.isEmpty()) {
                String src = enc.get(0).attr("src").replace("200%2C200", "500%2C300");
                news.enclosures.add(new Enclosure(src, "image", ""));
            }
            news.description = doc.select("p:nth-of-type(1)").text();
        }
        return news;
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements article = doc.select("noscript .story-hero-image,.article-body");

        article.select("style,noscript,script,.ctx-clearfix,.ctx-article-root,.ctx-sidebar-container").remove();

        for (Element img : article.select("img")) {
            String src = img.attr("src");
            if (src.endsWith("1x1.trans.gif") || src.isEmpty()) {
                img.attr("src", img.attr("data-lazy-src"))
                        .removeAttr("data-lazy-src");
            }

            NewsStylist.cleanAttributes(img, "src");
        }

        article.select("h1,h2").tagName("h3");
        doc.select(".wp-caption-text").tagName("figcaption");
        article.select("[style]").removeAttr("style");
        article.select("[width]").removeAttr("width");
        article.select("iframe").attr("frameborder", "0");

        news.content = article.outerHtml();

    }

    @Override
    protected Document getDocument(String pagelink)
    {
        try {
            return org.jsoup.Jsoup.connect(pagelink)
                    .timeout(10000)
                    .userAgent(USER_AGENT)
                    .get();
        } catch (IOException ignore) {
        }
        return super.getDocument(pagelink);
    }

}
