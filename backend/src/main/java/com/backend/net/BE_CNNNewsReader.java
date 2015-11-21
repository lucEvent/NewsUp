package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_CNNNewsReader extends BE_NewsReader {

    public BE_CNNNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Top Stories", "http://rss.cnn.com/rss/edition.rss"));
        SECTIONS.add(new BE_Section("Most Recent", "http://rss.cnn.com/rss/cnn_latest.rss"));
        SECTIONS.add(new BE_Section("World", "http://rss.cnn.com/rss/edition_world.rss"));
        SECTIONS.add(new BE_Section("Africa", "http://rss.cnn.com/rss/edition_africa.rss"));
        SECTIONS.add(new BE_Section("Americas", "http://rss.cnn.com/rss/edition_americas.rss"));
        SECTIONS.add(new BE_Section("Asia", "http://rss.cnn.com/rss/edition_asia.rss"));
        SECTIONS.add(new BE_Section("Europe", "http://rss.cnn.com/rss/edition_europe.rss"));
        SECTIONS.add(new BE_Section("Middle East", "http://rss.cnn.com/rss/edition_meast.rss"));
        SECTIONS.add(new BE_Section("U.S.", "http://rss.cnn.com/rss/edition_us.rss"));

        SECTIONS.add(new BE_Section("Money", "http://rss.cnn.com/rss/money_news_international.rss"));
        SECTIONS.add(new BE_Section("Technology", "http://rss.cnn.com/rss/edition_technology.rss"));
        SECTIONS.add(new BE_Section("Entertainment", "http://rss.cnn.com/rss/edition_entertainment.rss"));
        SECTIONS.add(new BE_Section("Politics", "http://rss.cnn.com/rss/cnn_allpolitics.rss"));
        SECTIONS.add(new BE_Section("Health", "http://rss.cnn.com/rss/cnn_health.rss"));
        SECTIONS.add(new BE_Section("Travel", "http://rss.cnn.com/rss/edition_travel.rss"));
        SECTIONS.add(new BE_Section("Living", "http://rss.cnn.com/rss/cnn_living.rss"));
        SECTIONS.add(new BE_Section("Markets", "http://rss.cnn.com/rss/money_markets.rss"));

        SECTIONS.add(new BE_Section("World Sport", "http://rss.cnn.com/rss/edition_sport.rss"));
        SECTIONS.add(new BE_Section("Football", "http://rss.cnn.com/rss/edition_football.rss"));
        SECTIONS.add(new BE_Section("Golf", "http://rss.cnn.com/rss/edition_golf.rss"));
        SECTIONS.add(new BE_Section("Motorsport", "http://rss.cnn.com/rss/edition_motorsport.rss"));
        SECTIONS.add(new BE_Section("Tennis", "http://rss.cnn.com/rss/edition_tennis.rss"));

        SECTIONS.add(new BE_Section("Video", "http://rss.cnn.com/rss/cnn_freevideo.rss"));

        SECTIONS.add(new BE_Section("Student News", "http://rss.cnn.com/services/podcasting/studentnews/rss.xml"));
        SECTIONS.add(new BE_Section("iReports on CNN", "http://rss.ireport.com/feeds/oncnn.rss"));
        SECTIONS.add(new BE_Section("Small Business", "http://rss.cnn.com/rss/money_smbusiness.rss"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {

        org.jsoup.select.Elements e = doc.select("#body-text");

        if (e.isEmpty()) {
            org.jsoup.select.Elements temp = doc.select(".el-carousel__wrapper noscript");

            if (temp.isEmpty()) {

                //          System.out.println("Paren las maquinas");
                //        System.out.println(" ----- " + news.link);
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