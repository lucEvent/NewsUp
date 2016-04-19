package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class CNN extends com.lucevent.newsup.data.util.NewsReader {

    public CNN() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text();
        return news;
    }

    @Override
    public News readNewsContent(News news) {
        if (news.link.contains("podcasts")) {

            String urltemp = news.link.substring(news.link.indexOf("podcasts"), news.link.indexOf("/story"));

            String result = "http://" + urltemp.replace("0B", ".").replace("0C", "/").replace("0E", "-").replace("0I", "_").replace("A", "");

            news.content = "<video controls autoplay><source src=\"" + result + "\" type=\"video/mp4\"></video>";
        } else {
            return super.readNewsContent(news);
        }
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        org.jsoup.select.Elements e = doc.select("#body-text");

        if (e.isEmpty()) {
            org.jsoup.select.Elements temp = doc.select(".el-carousel__wrapper noscript");

            if (temp.isEmpty()) {
                return;
            }
        } else {
            e.select(".zn-body__read-more,.cn-zoneAdContainer,script,.el-editorial-source,.el__leafmedia--raw-html,.zn-body__read-more-outbrain,.el__leafmedia--storyhighlights,.ad--epic").remove();
            e.select(".js-media__caption,.el__gallery-showhide,.owl-filmstrip,.el__storyelement__title,.media__video--thumbnail-wrapper").remove();

            for (org.jsoup.nodes.Element i : e.select("noscript")) {
                i.parent().html(i.html());
            }
            for (org.jsoup.nodes.Element i : e.select(".el-embed-youtube__content")) {
                String src = i.attr("src");
                if (!src.startsWith("http")) {
                    i.attr("src", "http:" + src);
                    i.attr("frameborder", "0");
                }
            }
        }
        org.jsoup.select.Elements img = doc.select("img[itemprop=\"image\"]");
        org.jsoup.select.Elements carrousel = doc.select(".el-carousel__wrapper noscript");

        news.content = img.outerHtml() + e.html() + carrousel.html();
    }

}
