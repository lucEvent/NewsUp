package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class BCCNewsReader extends NewsReader {

    public BCCNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Top stories", 0, "http://feeds.bbci.co.uk/news/rss.xml"));
        SECTIONS.add(new Section("World", 1, "http://feeds.bbci.co.uk/news/world/rss.xml"));
        SECTIONS.add(new Section("UK", 1, "http://feeds.bbci.co.uk/news/uk/rss.xml"));
        SECTIONS.add(new Section("Business", 1, "http://feeds.bbci.co.uk/news/business/rss.xml"));
        SECTIONS.add(new Section("Politics", 1, "http://feeds.bbci.co.uk/news/politics/rss.xml"));
        SECTIONS.add(new Section("Health", 1, "http://feeds.bbci.co.uk/news/health/rss.xml"));
        SECTIONS.add(new Section("Education & Family", 1, "http://feeds.bbci.co.uk/news/education/rss.xml"));
        SECTIONS.add(new Section("Science & Environment", 1, "http://feeds.bbci.co.uk/news/science_and_environment/rss.xml"));
        SECTIONS.add(new Section("Technology", 1, "http://feeds.bbci.co.uk/news/technology/rss.xml"));
        SECTIONS.add(new Section("Entertainment & Arts", 1, "http://feeds.bbci.co.uk/news/entertainment_and_arts/rss.xml"));

        SECTIONS.add(new Section("UK News", 0, "http://feeds.bbci.co.uk/news/rss.xml?edition=uk"));
        SECTIONS.add(new Section("England", 1, "http://feeds.bbci.co.uk/news/england/rss.xml"));
        SECTIONS.add(new Section("Northern Ireland", 1, "http://feeds.bbci.co.uk/news/northern_ireland/rss.xml"));
        SECTIONS.add(new Section("Scotland", 1, "http://feeds.bbci.co.uk/news/scotland/rss.xml"));
        SECTIONS.add(new Section("Wales", 1, "http://feeds.bbci.co.uk/news/wales/rss.xml"));

        SECTIONS.add(new Section("World News ", 0, null));
        SECTIONS.add(new Section("Africa", 1, "http://feeds.bbci.co.uk/news/world/africa/rss.xml"));
        SECTIONS.add(new Section("Asia", 1, "http://feeds.bbci.co.uk/news/world/asia/rss.xml"));
        SECTIONS.add(new Section("Europe", 1, "http://feeds.bbci.co.uk/news/world/europe/rss.xml"));
        SECTIONS.add(new Section("Latin America", 1, "http://feeds.bbci.co.uk/news/world/latin_america/rss.xml"));
        SECTIONS.add(new Section("Middle east", 1, "http://feeds.bbci.co.uk/news/world/middle_east/rss.xml"));
        SECTIONS.add(new Section("US & Canada", 1, "http://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml"));

        SECTIONS.add(new Section("Video & Audio", 0, null));
        SECTIONS.add(new Section("Top stories", 1, "http://feeds.bbci.co.uk/news/video_and_audio/news_front_page/rss.xml?edition=uk"));
        SECTIONS.add(new Section("World", 1, "http://feeds.bbci.co.uk/news/video_and_audio/world/rss.xml"));
        SECTIONS.add(new Section("UK", 1, "http://feeds.bbci.co.uk/news/video_and_audio/uk/rss.xml"));
        SECTIONS.add(new Section("Business", 1, "http://feeds.bbci.co.uk/news/video_and_audio/business/rss.xml"));
        SECTIONS.add(new Section("Politics", 1, "http://feeds.bbci.co.uk/news/video_and_audio/politics/rss.xml"));
        SECTIONS.add(new Section("Health", 1, "http://feeds.bbci.co.uk/news/video_and_audio/health/rss.xml"));
        SECTIONS.add(new Section("Science & Environment", 1, "http://feeds.bbci.co.uk/news/video_and_audio/science_and_environment/rss.xml"));
        SECTIONS.add(new Section("Technology", 1, "http://feeds.bbci.co.uk/news/video_and_audio/technology/rss.xml"));
        SECTIONS.add(new Section("Entertainment & Arts", 1, "http://feeds.bbci.co.uk/news/video_and_audio/entertainment_and_arts/rss.xml"));

        SECTIONS.add(new Section("Other News", 0, null));
        SECTIONS.add(new Section("Latest published stories", 1, "http://feeds.bbci.co.uk/news/system/latest_published_content/rss.xml"));
        SECTIONS.add(new Section("Magazine", 1, "http://feeds.bbci.co.uk/news/magazine/rss.xml"));
        SECTIONS.add(new Section("Also in the news", 1, "http://feeds.bbci.co.uk/news/also_in_the_news/rss.xml"));
        SECTIONS.add(new Section("In pictures", 1, "http://newsrss.bbc.co.uk/rss/newsonline_uk_edition/in_pictures/rss.xml"));
        SECTIONS.add(new Section("Special Reports", 1, "http://feeds.bbci.co.uk/news/special_reports/rss.xml"));
        SECTIONS.add(new Section("Have your say", 1, "http://feeds.bbci.co.uk/news/have_your_say/rss.xml"));

        SECTIONS.add(new Section("Sports", 0, "http://feeds.bbci.co.uk/sport/0/rss.xml?edition=uk"));
        SECTIONS.add(new Section("Football", 1, "http://newsrss.bbc.co.uk/rss/sportonline_uk_edition/football/rss.xml"));
        SECTIONS.add(new Section("Cricket", 1, "http://newsrss.bbc.co.uk/rss/sportonline_uk_edition/cricket/rss.xml"));
        SECTIONS.add(new Section("Rugby Union", 1, "http://newsrss.bbc.co.uk/rss/sportonline_uk_edition/rugby_union/rss.xml"));
        SECTIONS.add(new Section("Rugby League", 1, "http://newsrss.bbc.co.uk/rss/sportonline_uk_edition/rugby_league/rss.xml"));
        SECTIONS.add(new Section("Tennis", 1, "http://newsrss.bbc.co.uk/rss/sportonline_uk_edition/tennis/rss.xml"));
        SECTIONS.add(new Section("Golf", 1, "http://newsrss.bbc.co.uk/rss/sportonline_uk_edition/golf/rss.xml"));
        SECTIONS.add(new Section("Snooker", 1, "http://feeds.bbci.co.uk/sport/0/snooker/rss.xml?edition=uk"));

        SECTIONS.add(new Section("Sports Video & Audio", 0, "http://feeds.bbci.co.uk/sport/0/rss.xml?edition=uk"));

    }

    @Override
    public News readNewsContent(News news) {
        org.jsoup.nodes.Document doc = getDocument(news.link);
        if (doc == null) return news;

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
                                debug("NO SE HA ENCONTRADO EL CONTENIDO:" + news.link);
                                return news;

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

        return news;
    }

}
