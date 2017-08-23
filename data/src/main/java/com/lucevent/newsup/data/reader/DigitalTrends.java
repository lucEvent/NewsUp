package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DigitalTrends extends com.lucevent.newsup.data.util.NewsReader {

    // tags: [category, dc:creator, description, enclosure, guid, item, link, pubdate, thumbnail, title]

    public DigitalTrends()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_ENCLOSURE},
                "https://www.digitaltrends.com/",
                "");
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).getElementsByTag("p").get(0).text();
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        Elements header = doc.select(".m-header-media").select("img,iframe");
        Elements article = doc.select("article[itemprop='articleBody']");

        if (article.isEmpty()) {

            header = doc.select(".alpha .m-testimonial,.m-good-bad");
            article = doc.select("article[itemprop='reviewBody']");

            article.select(".m-linked-product,.m-comparable-products,.m-accessory-pack").remove();
        }

        article.select(".alignright,.m-related-video,script,.h-nonessential,.zoom-button,.m-image-credit").remove();

        article.select("h1,h2,.m-our-take").tagName("h3");
        header.select("iframe[height]").removeAttr("height");
        article.select("iframe[height]").removeAttr("height");

        for (Element e : article.select("img[src^='data']")) {
            e.attr("src", e.attr("data-dt-lazy-src"));
            NewsStylist.cleanAttributes(e, "src");
        }

        try {
            for (Element e : article.select("strong")) {
                String text = e.text();
                if (text.startsWith("More") || text.startsWith("Related"))
                    e.parent().text("");
            }
            for (Element e : article.select("a img")) {
                String src = e.attr("src");
                if (src.endsWith("button-150x39.png") || src.endsWith("-smallest-325x325.jpg"))
                    e.parent().remove();
            }
            for (Element e : article.select(".m-quick-take")) {
                e.select(".title").tagName("b");
                e.tagName("blockquote");
            }
        } catch (Exception ignored) {
        }

        news.content = NewsStylist.cleanComments(header.outerHtml() + article.outerHtml());
    }

}
