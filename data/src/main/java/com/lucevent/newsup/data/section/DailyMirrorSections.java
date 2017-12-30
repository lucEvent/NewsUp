package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class DailyMirrorSections extends Sections {

    public DailyMirrorSections()
    {
        super();

        add(new Section("Homepage", "http://www.mirror.co.uk/rss.xml", 0));

        add(new Section("News", "http://www.mirror.co.uk/news/rss.xml", 0));
        add(new Section("UK News", "http://www.mirror.co.uk/news/uk-news/rss.xml", 1));
        add(new Section("World News", "http://www.mirror.co.uk/news/world-news/rss.xml", 1));
        add(new Section("Weird News", "http://www.mirror.co.uk/news/weird-news/rss.xml", 1));
        add(new Section("Real Life Stories", "http://www.mirror.co.uk/news/real-life-stories/rss.xml", 1));
        add(new Section("Science", "http://www.mirror.co.uk/science/rss.xml", 1));
        add(new Section("Health", "http://www.mirror.co.uk/lifestyle/health/rss.xml", 1));
        add(new Section("Motoring", "http://www.mirror.co.uk/lifestyle/motoring/rss.xml", 1));

        add(new Section("Politics", "http://www.mirror.co.uk/news/politics/rss.xml", 0));

        add(new Section("Sport", "http://www.mirror.co.uk/sport/rss.xml", 0));
        add(new Section("Football", "http://www.mirror.co.uk/sport/football/rss.xml", 1));
        add(new Section("Boxing", "http://www.mirror.co.uk/sport/boxing/rss.xml", 1));
        add(new Section("WWE", "http://www.mirror.co.uk/sport/other-sports/wrestling/rss.xml", 1));
        add(new Section("F1", "http://www.mirror.co.uk/sport/formula-1/rss.xml", 1));
        add(new Section("Racing", "http://www.mirror.co.uk/sport/horse-racing/rss.xml", 1));
        add(new Section("Cricket", "http://www.mirror.co.uk/sport/cricket/rss.xml", 1));
        add(new Section("Rugby Union", "http://www.mirror.co.uk/sport/rugby-union/rss.xml", 1));
        add(new Section("Rugby League", "http://www.mirror.co.uk/sport/rugby-league/rss.xml", 1));
        add(new Section("Golf", "http://www.mirror.co.uk/sport/golf/rss.xml", 1));
        add(new Section("Tennis", "http://www.mirror.co.uk/sport/tennis/rss.xml", 1));
        add(new Section("Athletics", "http://www.mirror.co.uk/sport/other-sports/athletics/rss.xml", 1));
        add(new Section("Other Sports", "http://www.mirror.co.uk/sport/other-sports/rss.xml", 1));

        add(new Section("Celebs", "http://www.mirror.co.uk/3am/rss.xml", 0));
        add(new Section("TV & Film", "http://www.mirror.co.uk/tv/rss.xml", 0));
        add(new Section("Tech", "http://www.mirror.co.uk/tech/rss.xml", 0));
        add(new Section("Money", "http://www.mirror.co.uk/money/rss.xml", 0));

        add(new Section("Travel", "http://www.mirror.co.uk/travel/rss.xml", 0));
        add(new Section("Africa", "http://www.mirror.co.uk/travel/africa/rss.xml", 1));
        add(new Section("Caribbean", "http://www.mirror.co.uk/travel/caribbean/rss.xml", 1));
        add(new Section("Cheap Flights", "http://www.mirror.co.uk/travel/cheap-flights/rss.xml", 1));
        add(new Section("Cruises", "http://www.mirror.co.uk/travel/cruises/rss.xml", 1));
        add(new Section("Europe", "http://www.mirror.co.uk/travel/europe/rss.xml", 1));
        add(new Section("Travel News", "http://www.mirror.co.uk/travel/news/rss.xml", 1));
        add(new Section("UK & Ireland", "http://www.mirror.co.uk/travel/uk-ireland/rss.xml", 1));
        add(new Section("USA & Canada", "http://www.mirror.co.uk/travel/usa-canada/rss.xml", 1));

        add(new Section("Fashion", "http://www.mirror.co.uk/3am/style/rss.xml", 0));
        add(new Section("Mums", "http://www.mirror.co.uk/lifestyle/family/rss.xml", 0));
        add(new Section("Quizzes", "http://www.mirror.co.uk/play/quizzes/rss.xml", 0));

    }

}
