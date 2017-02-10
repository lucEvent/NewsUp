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
        Elements widgets, preamble, article;

        article = doc.select(".b-article__body");
        if (!article.isEmpty()) {

            widgets = doc.select(".b-article__top-widgets figure img:not(noscript figure img,.b-slideshow__item--video img),.b-article__top-widgets iframe");
            preamble = doc.select(".b-article__preamble");

            for (Element img : widgets) {
                if (img.tagName().equals("img")) {
                    String srcset = img.attr("data-srcset");
                    if (!srcset.isEmpty())
                        img.attr("srcset", srcset).removeAttr("data-srcset");
                    if (img.attr("src").isEmpty() && img.attr("srcset").isEmpty())
                        img.tagName("div");
                }
            }

        } else {

            widgets = doc.select(".tv-widget-container iframe,.slideshow__wrapper");
            preamble = doc.select(".text--article-preamble");
            article = doc.select(".text--article-body");

            for (Element img : widgets.select("img,iframe")) {
                String src = img.attr("data-src");
                src = src.replace("_format_", "16x9").replace("_width_", "600").replace("_quality_", "90");
                img.attr("src", src).removeAttr("data-src").removeAttr("alt").removeAttr("data-base-ttid");
                img.parent().parent().html(img.outerHtml());
            }

        }

        article.select("h2").tagName("h3");
        for (Element mer : article.select("strong,a")) {
            String text = mer.text();
            if (text.startsWith("L\u00C4S MER") || text.startsWith("L\u00E4s mer")
                    || text.startsWith("L\u00C4S OCKS\u00C5") || text.startsWith("L\u00C4S \u00C4VEN")) {
                try {
                    mer.parent().remove();
                } catch (Exception notParentFound) {
                    mer.remove();
                }
            }
        }
        article.select("[style]").removeAttr("style");
        article.select(".b-photo__description-wrap,script").remove();

        news.content = widgets.outerHtml() + "<b>" + preamble.html() + "</b><br>" + article.html().replace("<p>&nbsp;</p>", "");
    }

}
