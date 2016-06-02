package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;

public class As extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * Tags:
     * [category, comments, content:encoded, dc:creator, description, enclosure, guid, item, link, pubdate, title]
     * [dc:creator, description, enclosure, guid, item, link, pubdate, title]
     * [dc:creator, description, guid, item, link, pubdate, title]
     */

    public As()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_ENCLOSURE});
    }

    @Override
    protected News applySpecialCase(News news, String content)
    {
        if (!content.isEmpty())
            news.content = getEnclosures(news) + content;
        return news;
    }

    private String getEnclosures(News news)
    {
        String res = "";
        boolean imgset = false;
        for (Enclosure e : news.enclosures) {
            if (e.isImage() && !imgset && e.size > 10000) {
                res = e.html();
            } else if (e.isVideo()) {
                res = e.html();
                break;
            }
        }
        return res;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        org.jsoup.select.Elements e = doc.select("[itemprop=\"articleBody\"");

        if (!e.isEmpty()) {
            news.content = getEnclosures(news) + e.html();
        } else {
            boolean contains = news.link.contains("video");
            boolean thereis = false;
            String videohtml = "";
            for (Enclosure enclosure : news.enclosures) {
                if (enclosure.isVideo()) {
                    thereis = true;
                    videohtml = enclosure.html();
                    break;
                }
            }
            if (contains && thereis) {
                news.content = videohtml + news.description;
            } else {
                e = doc.select("#contenido-interior > p,.entry-content > p,.floatFix > p,.floatFix > figure");
                if (!e.isEmpty()) {
                    news.content = getEnclosures(news) + e.html();
                } else {
                    e = doc.select("#columna2");
                    if (!e.isEmpty()) {
                        e.select(".redes,.menu_post,.archivado,script").remove();
                        news.content = getEnclosures(news) + e.html();
                    } else {
                        e = doc.select(".marcador-generico,.cmt-live");
                        if (!e.isEmpty()) {
                            news.content = getEnclosures(news) + e.html();
                        }
                    }
                }
            }
        }
    }

}
