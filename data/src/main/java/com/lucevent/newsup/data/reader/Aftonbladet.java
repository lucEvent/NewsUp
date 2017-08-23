package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

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
                new int[]{},
                "http://www.aftonbladet.se",
                "");
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

            article = article.select(".abVideoSatellite,.abRoyalSlider,.abIC:not(.abBodyText .abIC),.abAboveBodyTextBox,.abBodyText");

            if (!article.isEmpty()) {

                article.select(".abBlock").remove();
                article.select("h1,h2").tagName("h3");

                news.content = article.html();
            }
        } else {

            article = doc.select("main");

            if (!article.isEmpty()) {

                String parent = "[data-test-id='main-column']";
                String headlines = "[data-test-id='lead-text']";
                String images = "[data-test-id='image']";
                String text = "[data-test-id='text'],h2";

                article = article.select(parent + ">" + headlines + "," + parent + ">" + images + "," + parent + ">" + text + ",.shootitlive-embed");

                article.select(headlines + " p").tagName("li");
                article.select("h1,h2").tagName("h3");
                article.select("[style]").removeAttr("style");

                for (Element e : article.select(images)) {
                    Elements img = e.select("picture");
                    Elements caption = e.select("[data-test-id='image-text'] div");
                    caption.tagName("figcaption");
                    e.html(img.outerHtml() + caption.outerHtml());
                    e.attr("style", "margin-top:10px");
                }
                for (Element e : article.select(".shootitlive-embed")) {
                    String href = e.select("a[href]").attr("href");
                    e.html(Enclosure.iframe(href));
                }

                for (Element e : article.select("[class]"))
                    e.removeAttr("class").removeAttr("data-test-id").removeAttr("data-reactid");

                NewsStylist.cleanAttributes(article.select("img"), "src");

                news.content = article.outerHtml();
            }
        }
        news.content = NewsStylist.cleanComments(news.content);
    }

}
