package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BBC extends com.lucevent.newsup.data.util.NewsReader {

    // tags: 	[description, guid, item, link, media:thumbnail, pubdate, title]

    public BBC()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_GUID},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{"media:thumbnail".hashCode()});
    }

    @Override
    protected Enclosure parseEnclosure(Element prop)
    {
        return new Enclosure(prop.attr("url"), "image", "");
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements article = doc.select("[property='articleBody']");

        if (article.isEmpty()) {

            article = doc.select(".story-inner img,.story-inner .map-body");
            if (article.isEmpty()) {

                article = doc.select(".main_content_wrapper .main_image,.main_content_wrapper .main_article_text");
                if (article.isEmpty()) {

                    article = doc.select(".gallery .gallery-intro__summary,.gallery .gallery-images");
                    if (article.isEmpty()) {

                        article = doc.select("#story-body");
                        if (article.isEmpty()) {

                            article = doc.select(".gel-body-copy");
                            if (article.isEmpty()) {
                                //todo
                                return;
                            }

                        } else {

                            Elements intro = article.select(".sp-story-body__introduction");
                            if (!intro.isEmpty()) {
                                intro.get(0).tagName("b").wrap("<p>").removeAttr("class");
                            }
                            article.select(".sp-story-body__table").wrap("<blockquote>");
                            article.select(".bbccom_slot,.tab-selector__tab-list,.lx-stream-show-more,.visually-hidden,style,script,ul:has(a)").remove();

                            for (Element figure : article.select("figure")) {
                                Elements img = figure.select("img");

                                if (img.isEmpty()) {
                                    figure.remove();
                                    continue;
                                }
                                String src;
                                if (img.attr("class").equals("sp-lazyload"))
                                    src = img.attr("data-src").replace("{width}{hidpi}", "624");
                                else
                                    src = img.attr("src");

                                figure.html("<img src='" + src + "'>");

                            }
                            for (Element e : article.select("a"))
                                if (e.text().startsWith("Read more"))
                                    NewsStylist.cleanAttributes(e.html(""));

                            article.select("[class]").removeAttr("class");
                            article.select("[data-reactid]").removeAttr("data-reactid");
                            article.select("h2").tagName("h3");

                            news.content = article.html();
                            return;
                        }
                    } else {

                        article.select("ul").tagName("div");
                        for (Element list_item : article.select(".gallery-images__list-item")) {
                            Elements img = list_item.select("img");
                            Elements description = list_item.select(".gallery-images__summary");

                            img.attr("src", img.attr("src").replace("/304/", "/660/"))
                                    .removeAttr("class")
                                    .removeAttr("alt")
                                    .removeAttr("width").removeAttr("height");
                            description.removeAttr("class");

                            list_item.html(img.outerHtml() + description.outerHtml())
                                    .tagName("p")
                                    .removeAttr("class");
                        }

                        news.content = article.html();
                        return;
                    }
                } else {
                    article.select(".related_stories,.related_topics,.pullOut").remove();
                }
            }
        }

        Elements intro = article.select(".story-body__introduction");
        if (!intro.isEmpty())
            intro.get(0).tagName("b").wrap("<p>").removeAttr("class");

        for (Element figure : article.select("figure")) {
            Elements imgs = figure.select("img");
            if (!imgs.isEmpty()) {
                Element img = imgs.get(0);
                img.attr("src", img.attr("src").replace("/320/", "/872/"));
                img.removeAttr("alt").removeAttr("class");
                figure.html(img.outerHtml());
            } else {
                imgs = figure.select(".js-delayed-image-load");
                if (!imgs.isEmpty()) {
                    Element img = imgs.get(0);
                    img.tagName("img");
                    img.attr("src", img.attr("data-src").replace("/320/", "/872/"));
                    img.removeAttr("data-alt").removeAttr("class").removeAttr("data-src");
                    figure.html(img.outerHtml());
                }
            }
            figure.removeAttr("class");
        }
        for (Element e : article.select("a"))
            if (e.text().startsWith("Read more"))
                NewsStylist.cleanAttributes(e.html(""));

        article.select(".mpu-ad,.media-with-caption,.media-player-wrapper,figcaption,style,script,ul:has(a)").remove();

        article.select("h2").tagName("h3");

        news.content = article.outerHtml();
    }

}
