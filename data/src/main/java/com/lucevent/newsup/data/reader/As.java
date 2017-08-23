package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
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
                new int[]{TAG_ENCLOSURE},
                "https://as.com/",
                "");
    }

    @Override
    protected News onNewsRead(News news)
    {
        if (!news.content.isEmpty()) {
            Document doc = jsoupParse(news.content);
            doc.select("h1,h2").tagName("h3");
            NewsStylist.repairLinks(doc.body());

            news.content = getEnclosures(news) + doc.body().html();
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

                    news.content = Enclosure.iframe("http://as.com" + videoURL) + news.description;
                    return;
                }
            }
        }

        article = doc.select("[itemprop='articleBody']");
        if (!article.isEmpty()) {

            article.select("script,section,.noticias-rel,.cont-art-tags").remove();
            article.select("h1,h2").tagName("h3");
            article.select(".escudo-equipo img").attr("style", "width:10%");

            NewsStylist.repairLinks(article);

            news.content = getEnclosures(news) + article.outerHtml();

        } else {
            article = doc.select("#contenedorfotos");
            if (!article.isEmpty()) {
                article = article.select("[itemprop='contentURL'],[itemprop='headline']");
                for (Element img : article.select("[itemprop='contentURL']")) {
                    img.tagName("img");

                    String src = img.attr("content");
                    NewsStylist.cleanAttributes(img);
                    img.attr("src", src);
                }
                article.select("[itemprop='headline']").tagName("p").select("h2,span").remove();

                NewsStylist.repairLinks(article);

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
                                // No content found
                                return;
                            } else {
                                article.select(".post-info,.post-ftr,#comments,.comments,.redes,#comment-form,h2,a[rel='prev'],a[rel='next'],a[rel='author'],script,aside").remove();
                            }
                        }
                    } else {
                        article.select(".redes,.menu_post,.archivado,script").remove();
                    }
                }
                NewsStylist.repairLinks(article);

                article.select("[class]").removeAttr("class");
                article.select("h1,h2").tagName("h3");

                news.content = getEnclosures(news) + article.html();
            }
        }
    }

}
