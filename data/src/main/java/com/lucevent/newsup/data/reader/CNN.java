package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CNN extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * Tags
     * [                      description,            guid, item, link, media:content, media:group,     pubdate, title]
     * [                      description,            guid, item, link, media:content, media:thumbnail, pubdate, title]
     * [category, dc:creator, description, enclosure, guid, item, link, media:content,                  pubdate, source, title]
     */

    public CNN()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_ENCLOSURE});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text();
    }

   @Override
    protected News onNewsRead(News news)
    {
         if (news.link.contains("podcasts") && !news.enclosures.isEmpty()) {

            news.content = news.enclosures.get(0).html() + "<p>" + news.description + "</p>";
            news.enclosures.clear();
//            news.content = "<video controls autoplay><source src=\"" + result + "\" type='video/mp4'></video>";
        }
        /*
           String urltemp = news.link.substring(news.link.indexOf("podcasts"), news.link.indexOf("/story"));

            String result = "http://" + urltemp.replace("0B", ".").replace("0C", "/").replace("0E", "-").replace("0I", "_").replace("A", "");
         */
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        Elements e = doc.select("#body-text");

        if (e.isEmpty()) {
            Elements temp = doc.select(".el-carousel__wrapper noscript");

            if (temp.isEmpty()) {
                return;
            }
        } else {
            e.select(".zn-body__read-more,.cn-zoneAdContainer,script,.el-editorial-source,.el__leafmedia--raw-html,.zn-body__read-more-outbrain,.el__leafmedia--storyhighlights,.ad--epic").remove();
            e.select(".js-media__caption,.el__gallery-showhide,.owl-filmstrip,.el__storyelement__title,.media__video--thumbnail-wrapper").remove();

            for (Element i : e.select("noscript"))
                i.parent().html(i.html());

            for (Element i : e.select(".el-embed-youtube__content")) {
                String src = i.attr("src");
                if (!src.startsWith("http")) {
                    i.attr("src", "http:" + src);
                    i.attr("frameborder", "0");
                }
            }
        }
        Elements img = doc.select("img[itemprop='image']");
        Elements carrousel = doc.select(".el-carousel__wrapper noscript");

        news.content = img.outerHtml() + e.html() + carrousel.html();
    }

}
