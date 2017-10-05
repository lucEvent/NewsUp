package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Mashable extends com.lucevent.newsup.data.util.NewsReader {

    // Tags [category, content:encoded, dc:creator, description, guid, item, link, mash:thumbnail, media:thumbnail, pubdate, title]

    public Mashable()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{"media:thumbnail".hashCode()},
                "http://mashable.com/",
                "");
    }

    @Override
    protected String parseDescription(Element prop)
    {
        String descr = Jsoup.parse(prop.text()).text();
        return descr.substring(0, 300 < descr.length() ? 300 : descr.length()) + "...";
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements article = doc.select(".article-image,.article-content");

        if (!article.isEmpty()) {
            article.select(".see-also,.viral-next-up,figcaption,.image-credit").remove();

            for (Element e : article.select("h2")) {
                String text = e.text();
                if (text.startsWith("RELATED:")
                        || text.startsWith("BONUS:"))
                    e.remove();
            }

            article.select("h1,h2").tagName("h3");
            article.select("ol,li").tagName("p");

            Elements mashVideos = article.select(".content-mash-video");
            if (mashVideos.size() == 1)
                mashVideos.get(0).remove();

            article.select("[style]").removeAttr("style");

        } else {
            article = doc.select(".video-hub .content-mash-video");

            if (!article.isEmpty()) {

                Elements dscr = doc.select(".video-hub #current-video-info");
                dscr.select("#video-title,#video-shares").remove();
                article.addAll(dscr);

            } else {
                article = doc.select(".long-card");

                if (!article.isEmpty()) {
                    article = article.select("[data-type='ImageBlock'] img,[data-type='TextBlock'] p");
                }
            }
        }

        for (Element vid : article.select(".content-mash-video script.playerMetadata")) {
            String info = vid.html();

            String src = NewsStylist.subStringBetween(info, "\"embedUrl\":\"", "\"", false);
            String desc = NewsStylist.subStringBetween(info, "\"description\":\"", "\"", false);

            Element p = vid.parent();
            NewsStylist.cleanAttributes(p);

            p.html(Enclosure.iframe(src) + "<figcaption>" + desc + "</figcaption>");
        }
        article.select("script").remove();

        NewsStylist.repairLinks(article);
        news.content = article.outerHtml();
    }

}
