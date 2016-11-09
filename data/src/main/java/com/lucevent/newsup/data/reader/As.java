package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
    protected News onNewsRead(News news)
    {
        if (!news.content.isEmpty()) {
            news.content = getEnclosures(news) + news.content;
        } else if (news.link.contains("video")) {

            for (Enclosure enclosure : news.enclosures)
                if (enclosure.isVideo()) {
                    news.content = enclosure.html() + news.description;
                    break;
                }
        }
        return news;
    }

    private String getEnclosures(News news)
    {
        String res = "";
        boolean imgSet = false;
        for (Enclosure e : news.enclosures) {
            if (e.isImage() && !imgSet && e.size > 10000) {
                res = e.html();
                imgSet = true;
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
        Elements article;
        if (news.link.contains("video")) {
            article = doc.select(".item-multimedia script");
            if (!article.isEmpty()) {
                String content = article.html();
                int vStart = content.indexOf("var urlVideo");
                if (vStart != -1) {

                    int vEnd = content.indexOf(".mp4", vStart);
                    String videoURL = content.substring(vStart + 39, vEnd + 4);

                    news.content = "<iframe frameborder='0' allowfullscreen src='http://as.com" + videoURL + "'></iframe>" + news.description;
                    return;
                }
            }
        }

        article = doc.select("[itemprop=\"articleBody\"");
        if (!article.isEmpty()) {
            for (Element escudo : article.select(".escudo-equipo img"))
                escudo.attr("style", "width:10%");
            for (Element e : article.select("[class]"))
                e.removeAttr("class");
            for (Element e : article.select("h2"))
                e.tagName("h3");
            article.select("section").remove();

            news.content = getEnclosures(news) + article.outerHtml();
        } else {

            article = doc.select("#contenedorfotos");
            if (!article.isEmpty()) {

                article = article.select("[itemprop=\"contentURL\"],[itemprop=\"headline\"]");
                for (Element img : article.select("[itemprop=\"contentURL\"]")) {
                    String src = img.attr("content");
                    img.attr("src", src);
                    img.removeAttr("content");
                    img.removeAttr("itemprop");
                    img.tagName("img");
                }
                news.content = article.outerHtml();

            } else {

                article = doc.select("#contenido-interior > p,.entry-content > p,.floatFix > p,.floatFix > figure");
                if (article.isEmpty()) {

                    article = doc.select("#columna2");
                    if (article.isEmpty()) {

                        article = doc.select(".marcador-generico,.cmt-live");
                        if (article.isEmpty()) {

                            article = doc.select(".post");
                            if (article.isEmpty()) {
                                // No content found // TODO: 06/11/2016
                                return;
                            } else {
                                article.select(".post-info,.post-ftr,#comments,.comments,.redes,#comment-form,h2").remove();
                            }
                        }
                    } else {
                        article.select(".redes,.menu_post,.archivado,script").remove();
                    }
                }
                for (Element e : article.select("h2"))
                    e.tagName("h3");
                for (Element e : article.select("[class]"))
                    e.removeAttr("class");

                news.content = getEnclosures(news) + article.html();
            }
        }
    }

}
