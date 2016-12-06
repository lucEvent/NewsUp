package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Aftonbladet extends com.lucevent.newsup.data.util.NewsReader {

    // tags: category, description, guid, item, link, pubdate, title

    public Aftonbladet()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{});
    }

    @Override
    protected String parseLink(Element prop)
    {
        String link = super.parseLink(prop);

        if (link.startsWith("/"))
            link = "http://www.aftonbladet.se" + link;

        return link;
    }

    @Override
    protected News onNewsRead(News news)
    {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(news.description);
        news.description = doc.text();

        Elements imgs = doc.select("img");
        if (!imgs.isEmpty()) {
            news.enclosures = new Enclosures();
            news.enclosures.add(new Enclosure(imgs.get(0).attr("src"), "image/jpg", "0"));
        }
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        Elements article = doc.select(".abArticle");

        if (!article.isEmpty()) {
            article.select(".abShareMenu,.abItem,.abFactBoxContainer,.abStatRelated,.abByline").remove();

            for (Element e : article.select(".abVideoSatellite")) {
                e.html(Enclosure.iframe(e.attr("href")));
                e.removeAttr("href");
            }
            for (Element e : article.select(".abRoyalSlider")) {
                for (Element video : e.select(".abMediaBoxVideoWrapper")) {
                    video.html(Enclosure.iframe(video.attr("data-tv-url")));
                    video.removeAttr("data-tv-url");
                }
                for (Element img : e.select(".lazy-load noscript"))
                    img.parent().html(img.html());
            }
            for (Element e : article.select(".abIC noscript:first-child"))
                e.parent().html(e.html());

            Elements articleContent = article.select(".abVideoSatellite,.abRoyalSlider,.abIC:not(.abBodyText .abIC),.abAboveBodyTextBox,.abBodyText");

            if (!articleContent.isEmpty()) {

                articleContent.select(".abBlock").remove();
                articleContent.select("h2").tagName("h3");

                news.content = articleContent.html();
            }
        } else {

            article = doc.select("main");

            if (!article.isEmpty()) {

                Elements articleContent = article.select("[data-test-id='image'],[data-test-id='text']");

                if (!articleContent.isEmpty()) {

                    articleContent.select("[style]").removeAttr("style");
                    for (Element img : articleContent.select("img"))
                        img.removeAttr("srcset").removeAttr("sizes").removeAttr("alt").removeAttr("width").removeAttr("height");
                    for (Element e : articleContent.select("[class]"))
                        e.removeAttr("class").removeAttr("data-test-id").removeAttr("data-reactid");

                    news.content = articleContent.outerHtml();
                }
            }
        }
    }

}
