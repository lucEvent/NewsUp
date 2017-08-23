package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TheGuardian extends com.lucevent.newsup.data.util.NewsReader {

    // tags: [category, dc:creator, dc:date, description, guid, item, link, media:content, media:credit, media:description, pubdate, title]

    public TheGuardian()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_MEDIA_CONTENT},
                "https://www.theguardian.com/",
                "");
    }

    @Override
    protected String parseDescription(Element prop)
    {
        Document doc = jsoupParse(prop);
        doc.select("a").remove();
        return doc.text();
    }

    @Override
    protected Enclosure parseEnclosure(Element prop)
    {
        return new Enclosure(prop.attr("url"), "image", prop.attr("width"));
    }

    @Override
    protected News onNewsRead(News news)
    {
        news.enclosures.keepOnlyLargest();
        return news;
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements article = doc.select("article");
        article.select(".mobile-only").remove();

        if (news.link.contains("/gallery/")) {
            article = article.select(".content__standfirst,.gallery__caption,.gallery__img-container");
            article.select("br").remove();

            for (Element imgBox : article.select(".gallery__img-container")) {
                NewsStylist.cleanAttributes(imgBox);
                imgBox.tagName("p");
                imgBox.html(imgBox.select("picture").outerHtml());
            }

        } else if (news.link.contains("/live/")) {
            article = article.select("ul.timeline,.block--content");
            article.select(".block-time,.block-share,.block-share--liveblog-mobile,.share-modal").remove();

            article.select(".block-title").tagName("h4");

            NewsStylist.cleanAttributes(article.select(".block-elements,.block--content"));
        } else {

            article = article.select(".media-primary picture,.media-primary video,.media-primary iframe,.media-primary figcaption,.content__article-body");

            if (article.isEmpty())
                return;

        }
        article.select("script,aside,.inline-icon,[style='display: none;']").remove();

        for (Element pic : article.select("picture")) {
            Element picContent = pic.select("source,img").first();

            String src = picContent.attr("srcset");
            NewsStylist.cleanAttributes(picContent);
            picContent.tagName("img").attr("src", src);
            pic.html(picContent.outerHtml());
        }
        for (Element fig : article.select("figure")) {
            NewsStylist.cleanAttributes(fig);

            Elements figContent = fig.select("blockquote,img:not(blockquote img),video,figcaption");
            fig.html(figContent.outerHtml());
        }

        article.select("h1,h2").tagName("h3");

        news.content = article.outerHtml();
    }

}