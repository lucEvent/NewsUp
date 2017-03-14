package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TheBolivarCommercial extends com.lucevent.newsup.data.util.NewsReader {

    //tags: [author, category, content, email, entry, id, link, name, published, summary, title, updated]

    public TheBolivarCommercial()
    {
        super(TAG_ITEM_ENTRY,
                new int[]{TAG_TITLE},
                new int[]{TAG_ID},
                new int[]{},
                new int[]{TAG_CONTENT},
                new int[]{TAG_UPDATED},
                new int[]{TAG_CATEGORY},
                new int[]{});

        this.style = NewsStylist.base("http://www.bolivarcommercial.com/");
    }

    @Override
    protected String parseCategory(Element prop)
    {
        return prop.attr("term");
    }

    @Override
    protected News onNewsRead(News news)
    {
        Document doc = org.jsoup.Jsoup.parse(news.content);
        Elements intro = doc.select(".K2FeedIntroText");
        Elements article = doc.select(".K2FeedFullText");
        if (article.isEmpty())
            article = intro;

        // Description
        String dscr = intro.text().trim();
        news.description = dscr.length() > 300 ? dscr.substring(0, 300) : dscr;

        // Enclosure
        Elements imgs = intro.select("img");
        if (!imgs.isEmpty())
            news.enclosures.add(new Enclosure("http://www.bolivarcommercial.com/" + imgs.first().attr("src"), "image", ""));

        // Content
        article.select(".wf_caption span").remove();
        article.select("[style]").removeAttr("style");

        news.content = article.outerHtml();
        return news;
    }

}
