package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Date;
import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Abc extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * tags:
     * [category,                  dc:creator, description, guid,         item, link, pubdate,         title]
     * [comments, content:encoded, dc:creator, description, guid, imagen, item, link, pubdate, source, title]
     **/

    public Abc()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{"imagen".hashCode()},
                "http://www.abc.es/",
                "");
    }

    @Override
    protected long parseDate(Element prop)
    {
        return Date.toDate(prop.text()) - 7200000;//2 hours
    }

    @Override
    protected Enclosure parseEnclosure(Element prop)
    {
        String enclosure = prop.text();
        if (!enclosure.isEmpty()) {

            Document d = Jsoup.parse(prop.text());
            Elements imgs = d.select("img");
            if (!imgs.isEmpty())
                return new Enclosure(imgs.get(0).attr("src"), "image", "");
        }
        return null;
    }

    @Override
    protected News onNewsRead(News news)
    {
        Document doc;
        // Parsing content (and description)
        if (news.content.isEmpty()) {
            doc = jsoupParse(news.description);

            String dscr = doc.text();
            news.description = dscr.substring(0, Math.min(dscr.length(), 300));
        } else
            doc = jsoupParse(news.content);

        news.content = parseContent(doc);
        // end

        // Parsing enclosures
        if (news.enclosures.isEmpty()) {
            Elements imgs = doc.select("img");
            if (!imgs.isEmpty())
                news.enclosures.add(new Enclosure(imgs.first().attr("src"), "", ""));
        }
        // end
        return news;
    }

    private String parseContent(Document doc)
    {
        doc.select(".remision-galeria,script").remove();

        for (Element e : doc.select("embed[src*='youtube.com']")) {
            Element p = e.parent();
            p.html(Enclosure.iframe(e.attr("src")));
            p.tagName("p");
            NewsStylist.cleanAttributes(p);
        }
        for (Element e : doc.select("a[data-lightbox-src]")) {
            String href = e.attr("data-lightbox-src");
            NewsStylist.cleanAttributes(e);
            e.attr("href", href);
        }

        doc.select("h1,h2").tagName("h3");
        NewsStylist.cleanAttributes(doc.select("img[src]"), "src");
        NewsStylist.repairLinks(doc.body());

        return NewsStylist.cleanComments(doc.body().html());
    }

}
