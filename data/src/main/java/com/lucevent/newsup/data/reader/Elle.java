package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Elle extends com.lucevent.newsup.data.util.NewsReader {

    //  Tags:
    //  [category,                              description, guid, item, link,                                          pubdate, title]
    //  [category, content:encoded, dc:creator, description, guid, item, link, postid, postimg, postimgapa, postimglow, pubdate, title]

    public Elle()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{"postimgapa".hashCode()},
                "http://www.elle.es/",
                "");
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text();
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = jsoupParse(prop);
        NewsStylist.repairLinks(doc.body());
        return doc.body().html();
    }

    @Override
    protected Enclosure parseEnclosure(Element prop)
    {
        return new Enclosure(prop.text(), "image", "");
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements articleBody = doc.select("[itemprop='articleBody']");

        Elements article = articleBody.select(".standard-article-body--content");

        if (!article.isEmpty()) {

            article.select(".ad-gpt-breaker-container,.social-byline,.zoomable-expand,.embedded-image--lead-image-share,.image-share,.image-copyright,.module,.animated-image--overlay,.pullquote-share").remove();

        } else {

            article = articleBody.select(".longform-article-body--text");

            if (!article.isEmpty()) {

                article.select(".longform-article--header-info,.embedded-image--info,.zoomable-expand,.image-share,.pullquote-share,.animated-image--overlay").remove();

            } else {

                article = articleBody.select(".listicle--section-inner");

                if (!article.isEmpty()) {

                    article.select(".listicle--share,.listicle--ad-rail,.listicle--ad-mobile,.zoomable-expand,.image-share,.related--galleries-container,.listicle--ad-leaderboard,.embedded-image--lead-copyright,.listicle--item-copyright").remove();

                } else {

                    article = doc.select(".gallery-main-view,.listicle--section-inner");

                    if (!article.isEmpty()) {

                        article.select(".gallery-prev-arrow,.view-gallery,.gallery-description--more-btn,.view-gallery--scroll,.related--galleries-container,.image-share,.listicle--ad-leaderboard,.listicle--item-copyright,.listicle-slide--tools").remove();

                        Elements description = article.select("h2");
                        if (!description.isEmpty())
                            description.get(0).tagName("p");

                    } else {
                        //todo
                        return;
                    }
                }
            }
        }

        for (Element e : article.select("img")) {
            String src = e.attr("data-src");
            for (Attribute attr : e.attributes())
                e.removeAttr(attr.getKey());
            e.attr("src", src);
        }
        for (Element e : article.select("iframe")) {
            String src = e.attr("data-src");
            if (!src.isEmpty()) {
                e.attr("src", src);
                e.removeAttr("data-src");
            }
        }
        for (Element e : article.select(".fb-video")) {
            String src = e.attr("data-href");
            if (!src.contains("video.php")) {
                int i1 = src.indexOf("videos/");
                if (i1 > 0) {
                    int i2 = src.indexOf("/", i1 + 7);
                    e.parent().html(Enclosure.iframe("https://m.facebook.com/video/video.php?v=" + src.substring(i1 + 7, i2)));
                }
            }
        }
        for (Element e : article.select(".embed--twitter")) {
            Elements sch = e.select("blockquote");
            if (!sch.isEmpty()) {
                Element tweet = sch.get(0).attr("class", "twitter-tweet");
                e.html(tweet.outerHtml());
            }
            e.removeAttr("class");
        }

        NewsStylist.repairLinks(article);

        article.select("h1,h2").tagName("h3");

        news.content = article.outerHtml();
    }

}
