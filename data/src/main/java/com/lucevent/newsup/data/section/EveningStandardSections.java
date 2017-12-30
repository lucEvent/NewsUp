package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class EveningStandardSections extends Sections {

    public EveningStandardSections()
    {
        super();

        add(new Section("Homepage", "https://www.standard.co.uk/rss", 0));

        add(new Section("News", "https://www.standard.co.uk/news/rss", 0));
        add(new Section("London", "https://www.standard.co.uk/news/london/rss", 1));
        add(new Section("Crime", "https://www.standard.co.uk/news/crime/rss", 1));
        add(new Section("Politics", "https://www.standard.co.uk/news/politics/rss", 1));
        add(new Section("Comment", "https://www.standard.co.uk/comment/rss", 1));

        add(new Section("Football", "https://www.standard.co.uk/sport/football/rss", 0));
        add(new Section("Arsenal", "https://www.standard.co.uk/sport/football/arsenal/rss", 1));
        add(new Section("Chelsea", "https://www.standard.co.uk/sport/football/chelsea/rss", 1));
        add(new Section("Tottenham Hotspur", "https://www.standard.co.uk/sport/football/tottenham/rss", 1));
        add(new Section("Crystal Palace", "https://www.standard.co.uk/sport/football/crystalpalace/rss", 1));
        add(new Section("West Ham", "https://www.standard.co.uk/sport/football/westham/rss", 1));
        add(new Section("Premier League", "https://www.standard.co.uk/sport/football/premier-league/rss", 1));

        add(new Section("Sport", "https://www.standard.co.uk/sport/rss", 0));
        add(new Section("Rugby Union", "https://www.standard.co.uk/sport/rugby/rss", 1));
        add(new Section("Tennis", "https://www.standard.co.uk/sport/tennis/rss", 1));
        add(new Section("Cricket", "https://www.standard.co.uk/sport/cricket/rss", 1));

        add(new Section("Showbiz", "https://www.standard.co.uk/showbiz/rss", 0));

        add(new Section("Going Out", "https://www.standard.co.uk/goingout/rss", 0));
        add(new Section("Restaurants", "https://www.standard.co.uk/goingout/restaurants/rss", 1));
        add(new Section("Arts", "https://www.standard.co.uk/goingout/arts/rss", 1));
        add(new Section("Theatre", "https://www.standard.co.uk/goingout/theatre/rss", 1));
        add(new Section("Music", "https://www.standard.co.uk/goingout/music/rss", 1));

        add(new Section("Staying In", "https://www.standard.co.uk/stayingin/rss", 0));
        add(new Section("Lifestyle", "https://www.standard.co.uk/lifestyle/rss", 0));
        add(new Section("Business & Money", "https://www.standard.co.uk/business/rss", 0));

    }

}
