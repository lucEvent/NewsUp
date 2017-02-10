package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ComputerHoy extends com.lucevent.newsup.data.util.NewsReader {

    private static final String SITE_STYLE = "<style>.minificha{margin:35px 0;border-top:1px solid #ccc;border-bottom:1px solid #ccc;padding:30px 0;}.minificha>figure,.minifich" +
            "a>header{text-align:center;}.minificha figure img{margin:10px;width:auto;max-width:100%;}.minificha header h3{font-size:23px;margin-bottom:10px;font-family:serif;m" +
            "argin:0 0 15px;font-weight:normal;}.minificha a{color:#1a1a1a;background:transparent;text-decoration:none;}.minificha-tabs{padding:0 10px;line-height:26px;margin:1" +
            "0px auto 0;}.minificha-tabs li{list-style:none;}.minificha-tabs h3{background-color:#eee;padding:10px;margin:5px 0 0;font-size:14px;font-family:sans-serif;font-wei" +
            "ght:normal;text-align:center;}.minificha-tabs .tab-content{padding:18px;background:#eee;}.minificha-tabs .value{font-weight:bold;}.minificha-tabs .link a{color:#de" +
            "0222;display:inline-block;font-weight:bold;text-align:right;text-decoration:none;padding:10px 0 0;width:100%;}.precio_amazon img,.precio_ebay img{margin-right:8px;" +
            "width:60px;top:6px;position:relative;}.minificha .precio_amazon a,.minificha .precio_ebay a{display:inline-block;background:#de0222;color:#fff;font-family:serif;pa" +
            "dding:3px 0;margin-left:10px;border-radius:3px;border-bottom:3px solid #90061a;font-size:14px;width:114px;}.puntuaciones ul{font-family:sans-serif;margin:10px 0;li" +
            "ne-height:22px;padding:0;}.puntuaciones li{list-style:none;padding:0;margin:10px 0;}.puntuaciones .label{display:inline-block;vertical-align:top;line-height:30px;f" +
            "ont-size:16px;width:28%;}.puntuaciones .nota{width:58%;background:#f5f5f5;position:relative;display:inline-block;vertical-align:top;height:26px;margin-right:10px;}" +
            ".puntuaciones .nota>span{position:absolute;left:0;top:0;height:100%;display:inline-block;}.yellow>span{background:#d7d00c;}.green>span{background:#189653;}.red>spa" +
            "n{background:#f00;}.puntuaciones .suffix{font-size:23px;width:11%;text-align:right;display:inline-block;line-height:30px;}.puntuaciones .suffix>span{color:#ccc;}.l" +
            "ink-paso a{position:relative;float:right;top:-25px;color:#fff;background:#444;padding:10px 30px;border:3px solid #fff;border-radius:4px;font-size:16px;text-decorat" +
            "ion:none;}</style>";

    // tags: [dc:creator, description, guid, item, link, pubdate, title]

    public ComputerHoy()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{});

        this.style = SITE_STYLE;
    }

    @Override
    protected News onNewsRead(News news)
    {
        Document doc = org.jsoup.Jsoup.parse(news.description);
        news.description = doc.text();

        Elements enc = doc.select("[itemprop='image']");
        if (!enc.isEmpty())
            news.enclosures.add(new Enclosure(enc.get(0).attr("src"), "image/jpg", ""));

        return news;
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements img = doc.select("[itemprop='image']");
        Elements article = doc.select("#ab_stickyid");

        if (article.isEmpty()) {
            article = doc.select("article > p,.link-paso");

            if (article.isEmpty()) {

                article = doc.select(".even article");

                if (article.isEmpty()) {
                    // TODO: 15/12/2016
                    return;
                } else {
                    img.clear();
                    article.select("header,.galerias,.submitted,.galeria-inline").remove();
                }
            }
        } else
            article.select(".adcuadrado,blockquote,.galeria-inline").remove();

        for (Element iframe : article.select("iframe")) {
            String src = iframe.attr("src");
            if (!src.startsWith("http"))
                iframe.attr("src", "http:" + src);
        }

        article.select("h2").tagName("h3");
        article.select("img[style],iframe[style]").removeAttr("style");
        article.select("[onmouseover]").removeAttr("onmouseover");
        article.select("[onmouseout]").removeAttr("onmouseout");
        article.select("meta,.valoracion").remove();

        news.content = img.outerHtml() + article.outerHtml();
    }

}