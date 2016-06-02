package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Expressen extends com.lucevent.newsup.data.util.NewsReader {

    // tags:  author, description, guid, item, link, pubdate, title]

    public Expressen()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{});
    }

    @Override
    protected String parseDescription(org.jsoup.nodes.Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text();
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        doc.select("script").remove();

        Elements firstElements, preamble, content;

        content = doc.select(".text--article-body");

        if (!content.isEmpty()) {

            firstElements = doc.select(".slideshow").select("img,.text--image-caption");
            for (Element p : firstElements.select("img")) {
                String src = p.attr("data-src");
                src = src.replace("_format_", "16x9").replace("_width_", "600").replace("_quality_", "90");
                p.attr("src", src);
            }

            preamble = doc.select(".text--article-preamble");

            content.select("h2").remove();
        } else {

            firstElements = doc.select(".b-article .b-article__top-widgets");
            for (Element e : firstElements.select("noscript"))
                e.tagName("p");
            firstElements.select(".b-image__caption-toggle,.b-slideshow__fullscreen-btn,.b-slider__pagination__bullet").remove();
            firstElements = firstElements.select("span");
            for (Element e : firstElements)
                e.removeAttr("class");

            content = doc.select(".b-article .b-article__body__content");

            if (!content.isEmpty()) {

                preamble = doc.select("#article-preamble");

            } else {

                preamble = doc.select(".text--article-preamble");
                content = doc.select(".article__content-body");

            }
        }
        for (Element e : content.select("a,strong")) {
            String text = e.text();
            if (text.startsWith("LÄS MER") || text.startsWith("Läs mer")
                    || text.startsWith("LÄS OCKSÅ"))
                e.parent().remove();
        }

        String s_preamble = "<p>" + preamble.html() + "</p>";
        String s_firsts = "<p>" + firstElements.html() + "</p>";
        String s_content = content.html().replace("<p>&nbsp;</p>", "");

        news.content = s_preamble + s_firsts + "<hr>" + s_content;
    }

}
