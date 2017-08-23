package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.parser.XmlTreeBuilder;
import org.jsoup.select.Elements;

import java.net.URL;

public class Vandal extends com.lucevent.newsup.data.util.NewsReader {

    // tags: [category, description, guid, item, link, pubdate, title]

    public Vandal()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{},
                "http://www.vandal.net/",
                "");
    }

    @Override
    protected News onNewsRead(News news)
    {
        Document doc = jsoupParse(news.content);
        if (news.content.length() > 1000) {
            doc.select("script").remove();

            for (Element video : doc.select("a[href^='http://www.vandal.net/video']")) {
                String link = video.attr("href");
                String video_id = link.substring(28, link.indexOf("/", 28));

                NewsStylist.cleanAttributes(video);
                video.tagName("video")
                        .attr("controls", "")
                        .html("<source src='http://videosold.vandalimg.com/mp4/" + video_id + ".mp4' type='video/mp4'>");
            }
            for (Element img : doc.select("img[src^='http://media.vandalimg.com/t200']"))
                img.attr("src",
                        img.attr("src").replace("http://media.vandalimg.com/t200", "http://mediamaster.vandal.net/m"));

            for (Element e : doc.select(".fright"))
                e.tagName("blockquote").removeAttr("style");

            doc.select("br").tagName("p");

            Element article = doc.body();
            NewsStylist.repairLinks(article);
            news.content = article.html();
        } else {
            news.description = doc.text();
            news.content = "";
        }
        return news;
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements video = doc.select("meta[property='og:video']");
        if (!video.isEmpty()) {
            String link = video.first().attr("content");

            news.content = Enclosure.iframe(link);

            Elements dscr = doc.select("meta[name='description']");
            if (!dscr.isEmpty())
                news.content += "<p>" + dscr.first().attr("content") + "</p>";

        } else {
            Elements e = doc.select(".contenidoprincipal [class='tn mt10']");
            doc.select("[style]").removeAttr("style");
            doc.select("[onclick]").removeAttr("onclick");
            NewsStylist.cleanAttributes(e.select("img"), "src");
            news.content = e.outerHtml();
        }
    }

    @Override
    protected org.jsoup.nodes.Document getDocument(String pagelink)
    {
        try {
            return org.jsoup.Jsoup.parse(new URL(pagelink).openStream(), "ISO-8859-1", pagelink, new Parser(new XmlTreeBuilder()));
        } catch (Exception ignore) {
        }
        return super.getDocument(pagelink);
    }

}
