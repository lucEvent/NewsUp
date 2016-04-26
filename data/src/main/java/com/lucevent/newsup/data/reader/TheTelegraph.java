package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class TheTelegraph extends com.lucevent.newsup.data.util.NewsReader {

    public TheTelegraph() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {

        org.jsoup.select.Elements img = doc.select(".articleImage");
        org.jsoup.select.Elements content = doc.select("#mainBodyAreaMobile");

        if (content.isEmpty()) {
            content = doc.select("#upperSet a");

            if (content.isEmpty()) {

                content = doc.select("[itemprop=\"articleBody\"] .component-content");

                if (content.isEmpty()) {

                    content = doc.select(".gallery__item");

                    if (content.isEmpty()) {
                        content = doc.select(".section-text");

                        if (content.isEmpty()) {
                            content = doc.select(".article-footer");

                            if (!content.isEmpty()) {
                                news.content = content.html().replace("src=\"/", "src=\"http://www.telegraph.co.uk/");
                            }
                        } else {
                            news.content = content.html();
                        }
                    } else {

                        for (org.jsoup.nodes.Element i : content.select("img")) {
                            String data = i.attr("data-frz-src-array");

                            int ind1 = data.indexOf(":");
                            int ind2 = data.indexOf(",");
                            if (ind1 != -1 && ind2 != -1) {
                                i.attr("src", "http://www.telegraph.co.uk" + data.substring(ind1 + 2, ind2 - 1));
                            }
                            i.attr("data-frz-src-array", "");
                        }
                        news.content = content.html();
                    }
                } else {
                    img = doc.select(".article__content header [itemprop=\"image\"]");
                    img.attr("src", "http://www.telegraph.co.uk" + img.attr("src"));
                    img.attr("data-frz-src-array", "");

                    news.content = img.outerHtml() + content.html();
                }
            } else {

                StringBuilder sb = new StringBuilder();
                for (org.jsoup.nodes.Element element : content) {
                    String href = element.attr("href");
                    String descr = element.attr("title");

                    sb.append("<p>").append(descr).append("</p><img src=\"").append(href).append("\">");
                }
                news.content = sb.toString();
            }
        } else {
            content.select("[class*=\"Advert\"],[class*=\"pullquote\"]").remove();
            news.content = img.outerHtml() + content.html();
        }

    }
}