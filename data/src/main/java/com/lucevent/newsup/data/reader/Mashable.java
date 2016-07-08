package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Mashable extends com.lucevent.newsup.data.util.NewsReader {

    // Tags [category, content:encoded, description, guid, item, link, mash:thumbnail, media:thumbnail, pubdate, title, wfw:commentrss]

    public Mashable()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{"media:thumbnail".hashCode()});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        String descr = Jsoup.parse(prop.text()).text();
        return descr.substring(0, 300 < descr.length() ? 300 : descr.length()) + "...";
    }

    @Override
    protected void readNewsContent(Document document, News news)
    {
        document.select("script").remove();

        Elements content = document.select("#story .viral-video-lead,#story .article-image,#story .post-text");

        if (!content.isEmpty()) {

            content.select(".see-also,.image-credit").remove();
            news.content = content.outerHtml().replace("src=\"//", "src=\"http://");

        } else {

            Elements video = document.select("#player [data-sourcefile]");
            if (video.size() > 0) {

                String src = video.get(0).attr("data-sourcefile");
                news.content = "<video controls><source src=\"" + src + "\" type=\"video/mp4\"></video>";

            } else {

                Elements longcards = document.select(".long-card");
                if (!longcards.isEmpty()) {

                    longcards = longcards.select("[data-type=\"ImageBlock\"] img,[data-type=\"TextBlock\"] p");
                    news.content = longcards.outerHtml();

                }
            }
        }
    }

}
