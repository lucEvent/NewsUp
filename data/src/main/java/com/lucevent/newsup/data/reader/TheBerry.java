package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TheBerry extends com.lucevent.newsup.data.util.NewsReader {

    //tags: [category, dc:creator, description, enclosure, guid, item, link, media:category, media:content, media:thumbnail, media:title, pubdate, title]

    public TheBerry() {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{"media:thumbnail".hashCode()},
                "http://theberry.com/",
                "");
    }

    @Override
    protected String parseDescription(Element prop) {
        return jsoupParse(prop).text();
    }

    @Override
    protected Enclosure parseEnclosure(Element prop) {
        String src = prop.attr("url");
        int index = src.indexOf('?');
        if (index != -1) {
            src = src.split("\\?")[0];
        }
        return new Enclosure(src, "", "");
    }

    @Override
    protected void readNewsContent(Document doc, News news) {
        Elements article = doc.select("article header .media,article .post-content");
        article.select("script,.type-ad").remove();

        article.select("div[style]:has(img)").removeAttr("style");
        article.select(".wp-caption-text").tagName("figcaption");

        Elements forms = article.select("form");
        if (!forms.isEmpty()) {
            NewsStylist.wpcomwidget(forms);
            forms.remove();
        }

        NewsStylist.cleanAttributes(article.select("img"), "src");

        news.content = article.outerHtml();
    }

}
