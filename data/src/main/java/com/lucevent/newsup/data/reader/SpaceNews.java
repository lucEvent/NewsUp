package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SpaceNews extends com.lucevent.newsup.data.util.NewsReader {

    // Tags: [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

    public SpaceNews()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{},
                "");
    }

    @Override
    protected String parseContent(Element prop)
    {
        Element article = jsoupParse(prop);
        article.select(".ctx-clearfix,.ctx-article-root,script").remove();

        for (Element e : article.select("img")) {
            String src = clearImgSrc(e);
            cleanAttributes(e);
            e.attr("src", src);
        }

        article.select("[style]").removeAttr("style");
        article.select(".pullquote,.pullquoteleft").tagName("blockquote");

        return finalFormat(article, false);
    }

    @Override
    protected News onNewsRead(News news)
    {
        if (!news.description.isEmpty()) {
            Element e = jsoupParse(news.description);

            Elements imgs = e.select("img");
            if (!imgs.isEmpty())
                news.enclosures.add(new Enclosure(clearImgSrc(imgs.first()), "image", ""));

            news.description = e.text().replace("SpaceNews.com", "");
        }
        return news;
    }

    private String clearImgSrc(Element img)
    {
        String remove = "-" + img.attr("width") + "x" + img.attr("height");
        return img.attr("src").replace(remove, "");
    }

}