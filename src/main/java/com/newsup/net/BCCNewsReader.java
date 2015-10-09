package com.newsup.net;

import android.content.Context;
import android.os.Handler;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class BCCNewsReader extends NewsReader {

    public BCCNewsReader(Handler handler, Context context) {
        super(handler, context);

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
        SECTIONS.add(new Section("Editors Blog", 1, "http://www.bbc.co.uk/blogs/theeditors/rss.xml"));

        SECTIONS.add(new Section("Sports", 0, "http://feeds.bbci.co.uk/sport/0/rss.xml?edition=uk"));
        SECTIONS.add(new Section("Latest Stories", 1, "http://newsrss.bbc.co.uk/rss/sportonline_uk_edition/latest_published_stories/rss.xml"));
        SECTIONS.add(new Section("Football", 1, "http://newsrss.bbc.co.uk/rss/sportonline_uk_edition/football/rss.xml"));
        SECTIONS.add(new Section("Cricket", 1, "http://newsrss.bbc.co.uk/rss/sportonline_uk_edition/cricket/rss.xml"));
        SECTIONS.add(new Section("Rugby Union", 1, "http://newsrss.bbc.co.uk/rss/sportonline_uk_edition/rugby_union/rss.xml"));
        SECTIONS.add(new Section("Rugby League", 1, "http://newsrss.bbc.co.uk/rss/sportonline_uk_edition/rugby_league/rss.xml"));
        SECTIONS.add(new Section("Tennis", 1, "http://newsrss.bbc.co.uk/rss/sportonline_uk_edition/tennis/rss.xml"));
        SECTIONS.add(new Section("Golf", 1, "http://newsrss.bbc.co.uk/rss/sportonline_uk_edition/golf/rss.xml"));
        SECTIONS.add(new Section("Motorsport", 1, "http://newsrss.bbc.co.uk/rss/sportonline_uk_edition/motorsport/rss.xml"));
        SECTIONS.add(new Section("Boxing", 1, "http://newsrss.bbc.co.uk/rss/sportonline_uk_edition/boxing/rss.xml"));
        SECTIONS.add(new Section("Athletics", 1, "http://newsrss.bbc.co.uk/rss/sportonline_uk_edition/athletics/rss.xml"));
        SECTIONS.add(new Section("Snooker", 1, "http://feeds.bbci.co.uk/sport/0/snooker/rss.xml?edition=uk"));
        SECTIONS.add(new Section("Horse Racing", 1, "http://newsrss.bbc.co.uk/rss/sportonline_uk_edition/other_sports/horse_racing/rss.xml"));
        SECTIONS.add(new Section("Cycling", 1, "http://newsrss.bbc.co.uk/rss/sportonline_uk_edition/other_sports/cycling/rss.xml"));
        SECTIONS.add(new Section("Disability Sport", 1, "http://newsrss.bbc.co.uk/rss/sportonline_uk_edition/other_sports/disability_sport/rss.xml"));
        SECTIONS.add(new Section("Other Sport", 1, "http://newsrss.bbc.co.uk/rss/sportonline_uk_edition/other_sports/rss.xml"));

        SECTIONS.add(new Section("Sports Video & Audio", 0, "http://feeds.bbci.co.uk/sport/0/rss.xml?edition=uk"));
        SECTIONS.add(new Section("Football", 1, "http://newsrss.bbc.co.uk/rss/sportplayer_uk_edition/football/rss.xml"));
        SECTIONS.add(new Section("Cricket", 1, "http://newsrss.bbc.co.uk/rss/sportplayer_uk_edition/cricket/rss.xml"));
        SECTIONS.add(new Section("Rugby Union", 1, "http://newsrss.bbc.co.uk/rss/sportplayer_uk_edition/rugby_union/rss.xml"));
        SECTIONS.add(new Section("Rugby League", 1, "http://newsrss.bbc.co.uk/rss/sportplayer_uk_edition/rugby_league/rss.xml"));
        SECTIONS.add(new Section("Tennis", 1, "http://newsrss.bbc.co.uk/rss/sportplayer_uk_edition/tennis/rss.xml"));
        SECTIONS.add(new Section("Golf", 1, "http://newsrss.bbc.co.uk/rss/sportplayer_uk_edition/golf/rss.xml"));
        SECTIONS.add(new Section("Motorsport", 1, "http://newsrss.bbc.co.uk/rss/sportplayer_uk_edition/motorsport/rss.xml"));
        SECTIONS.add(new Section("Boxing", 1, "http://newsrss.bbc.co.uk/rss/sportplayer_uk_edition/boxing/rss.xml"));
        SECTIONS.add(new Section("Athletics", 1, "http://newsrss.bbc.co.uk/rss/sportplayer_uk_edition/athletics/rss.xml"));
        SECTIONS.add(new Section("Snooker", 1, "http://newsrss.bbc.co.uk/rss/sportplayer_uk_edition/snooker/rss.xml"));
        SECTIONS.add(new Section("Horse Racing", 1, "http://newsrss.bbc.co.uk/rss/sportplayer_uk_edition/horse_racing/rss.xml"));
        SECTIONS.add(new Section("Cycling", 1, "http://newsrss.bbc.co.uk/rss/sportplayer_uk_edition/cycling/rss.xml"));
        SECTIONS.add(new Section("Disability Sport", 1, "http://newsrss.bbc.co.uk/rss/sportplayer_uk_edition/disability_sport/rss.xml"));
        SECTIONS.add(new Section("Other Sport", 1, "http://newsrss.bbc.co.uk/rss/sportplayer_uk_edition/other_sport/rss.xml"));

        SECTIONS.add(new Section("Sport Selection", 0, null));
        SECTIONS.add(new Section("Match of the day", 1, "http://newsrss.bbc.co.uk/rss/sportplayer_uk_edition/match_of_the_day/rss.xml"));
        SECTIONS.add(new Section("Football Focus", 1, "http://newsrss.bbc.co.uk/rss/sportplayer_uk_edition/football_focus/rss.xml"));
        SECTIONS.add(new Section("Score", 1, "http://newsrss.bbc.co.uk/rss/sportplayer_uk_edition/score_interactive/rss.xml"));

    }

    @Override
    public News readNewsContent(News news) {
        org.jsoup.nodes.Document doc = getNewsPage(news);
        if (doc == null) return news;

        try {
            org.jsoup.nodes.Element e = doc.select(".story-body__inner").get(0);
            org.jsoup.select.Elements ads = e.select("script");
            for (org.jsoup.nodes.Element ad : ads) ad.remove();
            news.content = e.html();

        } catch (Exception exception) {
            try {
                org.jsoup.nodes.Element e = doc.select(".article").get(0);
                org.jsoup.select.Elements ads = e.select("#headline,script,.data-table-outer,#also-related-links,.share-tools-footer");
                for (org.jsoup.nodes.Element ad : ads) ad.remove();

                news.content = e.html();

            } catch (Exception e) {
                debug("[ERROR] title:" + news.title);
            }
        }
        return news;
    }

    protected void debug(String text) {
        android.util.Log.d("##BCCNewsReader##", text);
    }

}
