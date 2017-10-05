package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ElNacional extends com.lucevent.newsup.data.util.NewsReader {

    /* Tags:
        [category, description, guid, item, link, pubdate, title,... content:encoded, dc:creator, media:content, media:description]
        [category, description, guid, item, link, pubdate, title,... author, enclosure]
    */

    public ElNacional()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_ENCLOSURE, TAG_MEDIA_CONTENT},
                "http://www.elnacional.cat/",
                "");
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = jsoupParse(prop);
        doc.select("script[src*='twitter']").remove();

        for (Element script : doc.select("script[id^='infogram']")) {
            String src = script.attr("id").replaceFirst("infogram_0_", "https://e.infogram.com/") + "?src=embed";

            NewsStylist.cleanAttributes(script);
            script.tagName("div");
            script.html(Enclosure.iframe(src));
        }

        doc.select(".caption").tagName("figcaption");

        return doc.body().html();
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements article = doc.select(".article-img img,.article-body");
        article.select("script,[data-type='related-content']").remove();

        NewsStylist.cleanAttributes(article.select("img"), "src");

        news.content = article.outerHtml();
    }

}
