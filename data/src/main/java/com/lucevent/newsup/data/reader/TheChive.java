package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class TheChive extends com.lucevent.newsup.data.util.NewsReader {

    private static final String SITE_STYLE = "<style>body{margin:0px;padding:0}</style>";

    // tags: [category, dc:creator, description, enclosure, guid, item, link, media:category, media:content, media:thumbnail, media:title, pubdate, title]

    public TheChive()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_MEDIA_CONTENT},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{"dc:identifier".hashCode()},
                "http://thechive.com/",
                SITE_STYLE);

        items = new Enclosures();
        descriptions = new ArrayList<>();
    }

    private Enclosures items;
    private ArrayList<String> descriptions;

    @Override
    protected String parseContent(Element prop)
    {
        String item = clearLink(prop.attr("url"));
        String type = prop.attr("medium");
        String description = prop.text();
        if (!description.contains(" "))
            description = "";

        items.add(new Enclosure(item, type, ""));
        descriptions.add(description);
        return "";
    }

    @Override
    protected Enclosure parseEnclosure(Element prop)
    {
        return new Enclosure(prop.text(), "text", "");
    }

    @Override
    protected News onNewsRead(News news)
    {
        if (!news.enclosures.isEmpty()) {

            String src = "https://cdnapisec.kaltura.com/html5/html5lib/v2.52/mwEmbedFrame.php/p/1289861/uiconf_id/37489151/entry_id/"
                    + news.enclosures.get(0).src + "?wid=_1289861";

            news.content = Enclosure.iframe(src);
            news.enclosures.clear();

        } else {

            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < items.size(); i++) {
                sb.append("<p>").append(descriptions.get(i)).append("</p>");
                sb.append(items.get(i).html());
            }
            news.content = sb.toString();
        }

        items.clear();
        descriptions.clear();

        if (!news.description.isEmpty()) {
            Elements imgs = jsoupParse(news.description).select("img");
            if (!imgs.isEmpty()) {
                String src = clearLink(imgs.first().attr("src"));
                news.enclosures.add(new Enclosure(src, "image", ""));
            }
            news.description = "";
        }

        return news;
    }

    private String clearLink(String link)
    {
        int index = link.indexOf('#');
        if (index != -1)
            link = link.split("#")[0];

        index = link.indexOf('?');
        if (index != -1)
            link = link.split("\\?")[0];

        return link;
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements article = doc.select("[itemprop='articleBody']");

        article.select(".post-sharing-buttons,.slideshow-toggle,script").remove();

        for (Element e : article.select(".video-container")) {
            String id = e.id();
            if (id.startsWith("kaltura-player")) {

                String src = "https://cdnapisec.kaltura.com/html5/html5lib/v2.52/mwEmbedFrame.php/p/1289861/uiconf_id/37489151/entry_id/"
                        + id.replace("kaltura-player-", "") + "?wid=_1289861";

                e.html(Enclosure.iframe(src));
            }
        }
        for (Element e : article.select(".gallery")) {
            Elements items = e.select(".gallery-icon,.gallery-caption");
            e.html(items.outerHtml());
        }

        news.content = NewsStylist.cleanComments(article.html());
    }

}
