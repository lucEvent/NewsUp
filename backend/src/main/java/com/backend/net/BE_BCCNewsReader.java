package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_BCCNewsReader extends BE_NewsReader {

    public BE_BCCNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Top stories", "http://feeds.bbci.co.uk/news/rss.xml"));
        SECTIONS.add(new BE_Section("World", "http://feeds.bbci.co.uk/news/world/rss.xml"));
        SECTIONS.add(new BE_Section("UK", "http://feeds.bbci.co.uk/news/uk/rss.xml"));
        SECTIONS.add(new BE_Section("Business", "http://feeds.bbci.co.uk/news/business/rss.xml"));
        SECTIONS.add(new BE_Section("Politics", "http://feeds.bbci.co.uk/news/politics/rss.xml"));
        SECTIONS.add(new BE_Section("Health", "http://feeds.bbci.co.uk/news/health/rss.xml"));
        SECTIONS.add(new BE_Section("Education & Family", "http://feeds.bbci.co.uk/news/education/rss.xml"));
        SECTIONS.add(new BE_Section("Science & Environment", "http://feeds.bbci.co.uk/news/science_and_environment/rss.xml"));
        SECTIONS.add(new BE_Section("Technology", "http://feeds.bbci.co.uk/news/technology/rss.xml"));
        SECTIONS.add(new BE_Section("Entertainment & Arts", "http://feeds.bbci.co.uk/news/entertainment_and_arts/rss.xml"));

        SECTIONS.add(new BE_Section("UK News", "http://feeds.bbci.co.uk/news/rss.xml?edition=uk"));
        SECTIONS.add(new BE_Section("England", "http://feeds.bbci.co.uk/news/england/rss.xml"));
        SECTIONS.add(new BE_Section("Northern Ireland", "http://feeds.bbci.co.uk/news/northern_ireland/rss.xml"));
        SECTIONS.add(new BE_Section("Scotland", "http://feeds.bbci.co.uk/news/scotland/rss.xml"));
        SECTIONS.add(new BE_Section("Wales", "http://feeds.bbci.co.uk/news/wales/rss.xml"));

        SECTIONS.add(new BE_Section("World News ", null));
        SECTIONS.add(new BE_Section("Africa", "http://feeds.bbci.co.uk/news/world/africa/rss.xml"));
        SECTIONS.add(new BE_Section("Asia", "http://feeds.bbci.co.uk/news/world/asia/rss.xml"));
        SECTIONS.add(new BE_Section("Europe", "http://feeds.bbci.co.uk/news/world/europe/rss.xml"));
        SECTIONS.add(new BE_Section("Latin America", "http://feeds.bbci.co.uk/news/world/latin_america/rss.xml"));
        SECTIONS.add(new BE_Section("Middle east", "http://feeds.bbci.co.uk/news/world/middle_east/rss.xml"));
        SECTIONS.add(new BE_Section("US & Canada", "http://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml"));

        SECTIONS.add(new BE_Section("Video & Audio", null));
        SECTIONS.add(new BE_Section("Top stories", "http://feeds.bbci.co.uk/news/video_and_audio/news_front_page/rss.xml?edition=uk"));
        SECTIONS.add(new BE_Section("World", "http://feeds.bbci.co.uk/news/video_and_audio/world/rss.xml"));
        SECTIONS.add(new BE_Section("UK", "http://feeds.bbci.co.uk/news/video_and_audio/uk/rss.xml"));
        SECTIONS.add(new BE_Section("Business", "http://feeds.bbci.co.uk/news/video_and_audio/business/rss.xml"));
        SECTIONS.add(new BE_Section("Politics", "http://feeds.bbci.co.uk/news/video_and_audio/politics/rss.xml"));
        SECTIONS.add(new BE_Section("Health", "http://feeds.bbci.co.uk/news/video_and_audio/health/rss.xml"));
        SECTIONS.add(new BE_Section("Science & Environment", "http://feeds.bbci.co.uk/news/video_and_audio/science_and_environment/rss.xml"));
        SECTIONS.add(new BE_Section("Technology", "http://feeds.bbci.co.uk/news/video_and_audio/technology/rss.xml"));
        SECTIONS.add(new BE_Section("Entertainment & Arts", "http://feeds.bbci.co.uk/news/video_and_audio/entertainment_and_arts/rss.xml"));

        SECTIONS.add(new BE_Section("Other News", null));
        SECTIONS.add(new BE_Section("Latest published stories", "http://feeds.bbci.co.uk/news/system/latest_published_content/rss.xml"));
        SECTIONS.add(new BE_Section("Magazine", "http://feeds.bbci.co.uk/news/magazine/rss.xml"));
        SECTIONS.add(new BE_Section("Also in the news", "http://feeds.bbci.co.uk/news/also_in_the_news/rss.xml"));
        SECTIONS.add(new BE_Section("In pictures", "http://newsrss.bbc.co.uk/rss/newsonline_uk_edition/in_pictures/rss.xml"));
        SECTIONS.add(new BE_Section("Special Reports", "http://feeds.bbci.co.uk/news/special_reports/rss.xml"));
        SECTIONS.add(new BE_Section("Have your say", "http://feeds.bbci.co.uk/news/have_your_say/rss.xml"));

        SECTIONS.add(new BE_Section("Sports", "http://feeds.bbci.co.uk/sport/0/rss.xml?edition=uk"));
        SECTIONS.add(new BE_Section("Football", "http://newsrss.bbc.co.uk/rss/sportonline_uk_edition/football/rss.xml"));
        SECTIONS.add(new BE_Section("Cricket", "http://newsrss.bbc.co.uk/rss/sportonline_uk_edition/cricket/rss.xml"));
        SECTIONS.add(new BE_Section("Rugby Union", "http://newsrss.bbc.co.uk/rss/sportonline_uk_edition/rugby_union/rss.xml"));
        SECTIONS.add(new BE_Section("Rugby League", "http://newsrss.bbc.co.uk/rss/sportonline_uk_edition/rugby_league/rss.xml"));
        SECTIONS.add(new BE_Section("Tennis", "http://newsrss.bbc.co.uk/rss/sportonline_uk_edition/tennis/rss.xml"));
        SECTIONS.add(new BE_Section("Golf", "http://newsrss.bbc.co.uk/rss/sportonline_uk_edition/golf/rss.xml"));
        SECTIONS.add(new BE_Section("Snooker", "http://feeds.bbci.co.uk/sport/0/snooker/rss.xml?edition=uk"));

        SECTIONS.add(new BE_Section("Sports Video & Audio", "http://feeds.bbci.co.uk/sport/0/rss.xml?edition=uk"));

    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
        org.jsoup.select.Elements e = doc.select("[property=\"articleBody\"]");

        if (e.isEmpty()) {
            e = doc.select(".story-body");
            if (e.isEmpty()) {
                e = doc.select("#emp-content > .emp-description");
                if (e.isEmpty()) {
                    e = doc.select(".main_content_wrapper");
                    if (e.isEmpty()) {
                        e = doc.select("#main-content .emp-decription");
                        if (e.isEmpty()) {
                            e = doc.select(".storybody");

                            if (e.isEmpty()) {
                                return;
                            } else {
                                e.select(".videoInStoryB,#socialBookMarks,.mvtb,script").remove();
                            }
                        }
                    } else {
                        e.select("h1,.titlebar,.livestats-tracking,.secondary_content_container,.related_topics").remove();
                    }
                }
            } else {
                e.select("h1,.date,.ad_wrapper,#article-sidebar,#headline,.introduction,#also-related-links,.share-tools-footer,.bbccom_advert_placeholder").remove();
            }
        }
        org.jsoup.select.Elements styles = e.select("[style]");
        for (org.jsoup.nodes.Element style : styles) {
            style.attr("style", "");
        }
        org.jsoup.select.Elements imgs = e.select(".js-delayed-image-load");
        for (org.jsoup.nodes.Element img : imgs) {
            String src = img.attr("data-src");
            img.html("<img src=\"" + src + "\" >");
        }
        news.content = e.html();
    }

}
