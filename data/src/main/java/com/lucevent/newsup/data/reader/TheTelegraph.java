package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TheTelegraph extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * Tags:[author, category,              enclosure, guid, item, link, pubdate, title]
     * Tags:[author, category, description, enclosure, guid, item, link, pubdate, title]
     */

    public TheTelegraph()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_ENCLOSURE});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text();
    }

    @Override
    protected String parseCategory(Element prop)
    {
        String[] cats = prop.text().split(":");
        return cats.length > 0 ? cats[cats.length - 1] : "";
    }

    private static final String garbage = "[itemprop=\"caption\"],[itemprop=\"copyrightHolder\"],.video-player__image-controls-wrapper";
    int t = 1;

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        doc.select("script").remove();

        String content = "";
        Elements intro = doc.select(".article__content .hero-area-wrapper");
        intro.select(garbage).remove();
        cleanImages(intro.select("img"));

        Element article = doc.select("article[itemprop=\"articleBody\"]").first();
        if (article == null) {

            Elements articles = doc.select(".gallery__content img,.gallery__content p");

            if (articles.isEmpty()) {

                articles = doc.select(".main-column");

                if (articles.isEmpty()) {
                    //todo
                    System.out.println("No content");

                } else {

                    articles.select("style,.html-block").remove();
                    String rootUrl = news.link.replace("index.html", "");
                    content = articles.outerHtml().replace("=\"/", "=\"http:/").replace("=\"./", "=\"" + rootUrl);

                }
            } else {
                content = articles.outerHtml();
            }

        } else {
            article.select(garbage).remove();
            cleanImages(article.select("img"));

            content = intro.outerHtml() + article.outerHtml();
        }

        news.content = content.replace("=\"/", "=\"http://www.telegraph.co.uk/");
    }

    private void cleanImages(Elements imgs)
    {
        for (Element img : imgs) {
            img.after("<img src=\"" + img.attr("src") + "\"/>");
            img.remove();
        }
    }

}