package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class MundoDeportivo extends com.lucevent.newsup.data.util.NewsReader {

    public MundoDeportivo() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        int idash = news.description.indexOf("- ");
        if (idash != -1) news.description = news.description.substring(idash + 2);
        news.description = news.description.replace("...", "");
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        String intro = doc.select("[itemprop=\"alternativeHeadline\"]").outerHtml();
        org.jsoup.select.Elements imgs = doc.select("[itemprop=\"image\"] img,.gallery-leaf-figure img");

        StringBuilder img = new StringBuilder();
        if (!imgs.isEmpty()) {
            for (org.jsoup.nodes.Element i : imgs) {
                String attr = i.attr("data-src-md");
                if (attr.isEmpty())
                    attr = i.attr("src");

                img.append("<img src=\"" + attr + "\">");
            }
        }
        org.jsoup.select.Elements metas = doc.select("[itemprop=\"video\"] [itemprop=\"image\"]");
        if (!metas.isEmpty()) {
            for (org.jsoup.nodes.Element i : metas) {
                img.append("<img src=\"" + i.attr("content") + "\">");
            }
        }

        org.jsoup.select.Elements content = doc.select("[itemprop=\"articleBody\"]");
        content.select(".datetime-story-leaf,.gallery-story-leaf-figcaption").remove();

        news.content = intro + img.toString() + content.html();
    }

}
