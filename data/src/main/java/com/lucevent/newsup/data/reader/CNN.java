package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CNN extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * Tags
     * [                      description,            guid, item, link, media:content,                  pubdate,         title]
     * [                      description,            guid, item, link, media:content, media:thumbnail, pubdate,         title]
     * [category, dc:creator, description, enclosure, guid, item, link, media:content,                  pubdate, source, title]
     */

    public CNN()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_MEDIA_CONTENT, "media:thumbnail".hashCode()},
                "http://edition.cnn.com/",
                "");
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text();
    }

    @Override
    protected News onNewsRead(News news)
    {
        if (news.link.contains("/podcasting/")) {
            news.content = Enclosure.iframe(news.link) + "<p>" + news.description + "</p>";
            news.enclosures.clear();
        }
        if (news.enclosures.size() > 1) {
            Enclosure e = news.enclosures.get(0);
            int index = e.src.lastIndexOf("-");
            if (index != -1) {
                index = e.src.lastIndexOf("-", index - 1);

                String src = e.src.substring(0, index) + "-live-video.jpg";
                news.enclosures.clear();
                news.enclosures.add(new Enclosure(src, "image", ""));
            }
        }
        return news;
    }

    @Override
    protected Document getDocument(String pagelink)
    {
        try {
            return org.jsoup.Jsoup.connect(pagelink).get();
        } catch (Exception ignored) {
        }
        return super.getDocument(pagelink);
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        String url = doc.baseUri();
        if (url.contains("/videos/")) {
            Elements article = doc.select(".media__video--thumbnail,.el__video-collection__main-wrapper .media__video-description");
            article.select("script,style").remove();
            article.select("img").wrap("<p>").removeAttr("alt");
            news.content = article.html();
            return;
        } else if (url.contains("/gallery/")) {
            Elements article = doc.select(".el-carousel__wrapper noscript,.el-carousel__wrapper .el__gallery_image-title");
            article.select("script,style").remove();
            article.select("img").wrap("<p>").removeAttr("alt");
            news.content = article.html();
            return;
        } else if (url.contains("/money.cnn")) {

            Elements article = doc.select("#storytext");
            article.select(".video-play,.cnnVidFooter,figcaption,#storyFooter,.clearfix,.storytimestamp,script,style").remove();

            article.select("h1,h2").tagName("h3");
            for (Element link : article.select("a")) {
                String text = link.text();
                if (text.startsWith("Related:"))
                    link.remove();
            }

            news.content = article.html();
            return;
        }

        Elements article = doc.select("#body-text > .l-container");

        article.select("script,.el__leafmedia--storyhighlights,.ad,.zn-body__read-more,.el__article--embed,.el__leafmedia--factbox,.js__video--standard,.el-editorial-source,.el__leafmedia--featured-video-collection").remove();
        article.select(".el-editorial-note,.el__gallery--standard,.el__gallery--expandable,.el__video--expandable,.cnn-mapbox,style").remove();

        for (Element e : article.select(".zn-body__paragraph"))
            e.tagName("p").removeAttr("class");

        for (Element emb : article.select(".el__embedded--fullwidth,.el__image--expandable,.el__image--standard")) {
            Elements ns = emb.select("noscript");
            if (!ns.isEmpty())
                emb.html(ns.html());
        }
        for (Element gallery : article.select(".el__gallery--fullstandardwidth")) {
            Elements content = gallery.select("noscript,.el__gallery_image-title");
            content.select("img").wrap("<p>").removeAttr("alt");
            gallery.html(content.html());
        }
        for (Element link : article.select("a")) {
            String text = link.text();
            if (text.startsWith("READ MORE") || text.startsWith("RELATED")
                    || text.startsWith("Related story") || text.startsWith("Read:")
                    || text.startsWith("READ:")) {
                link.remove();
            }
        }
        article.select("h1,h2").tagName("h3");

        news.content = article.outerHtml();
    }

}
