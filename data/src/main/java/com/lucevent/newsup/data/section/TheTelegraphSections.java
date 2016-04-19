package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TheTelegraphSections extends Sections {

    public TheTelegraphSections() {
        super();

        add(new Section("News", "http://www.telegraph.co.uk/news/rss", 0));
        add(new Section("UK News", "http://www.telegraph.co.uk/news/uknews/rss", 0));
        add(new Section("World News", "http://www.telegraph.co.uk/news/worldnews/rss", 0));
        add(new Section("Politics", "http://www.telegraph.co.uk/news/politics/rss", 0));
        add(new Section("How about that?", "http://www.telegraph.co.uk/news/newstopics/howaboutthat/rss", 0));
        add(new Section("Technology News", "http://www.telegraph.co.uk/technology/news/rss", 0));
        add(new Section("Travel News", "http://www.telegraph.co.uk/travel/travelnews/rss", 0));
        add(new Section("The Royal Family", "http://www.telegraph.co.uk/news/uknews/theroyalfamily/rss", 0));
        add(new Section("Celebrity news", "http://www.telegraph.co.uk/news/celebritynews/rss", 0));
        add(new Section("Science News", "http://www.telegraph.co.uk/news/science/science-news/rss", 0));
        add(new Section("Earth News", "http://www.telegraph.co.uk/news/earth/earthnews/rss", 0));

        add(new Section("Finance", "http://www.telegraph.co.uk/finance/rss", 0));
        add(new Section("Personal Finance", "http://www.telegraph.co.uk/finance/personalfinance/rss", 1));
        add(new Section("Ambrose Evans-Pritchard", "http://www.telegraph.co.uk/finance/comment/ambroseevans_pritchard/rss", 1));
        add(new Section("Economics", "http://www.telegraph.co.uk/finance/economics/rss", 1));
        add(new Section("Markets", "http://www.telegraph.co.uk/finance/markets/rss", 1));
        add(new Section("Property", "http://www.telegraph.co.uk/finance/property/rss", 1));
        add(new Section("International Property", "http://www.telegraph.co.uk/finance/property/international/rss", 1));
        add(new Section("Property Market", "http://www.telegraph.co.uk/finance/property/property-market/rss", 1));
        add(new Section("Property News", "http://www.telegraph.co.uk/finance/property/news/rss", 1));

        add(new Section("Comment", "http://www.telegraph.co.uk/comment/rss", 0));
        add(new Section("Columnists", "http://www.telegraph.co.uk/comment/columnists/rss", 1));
        add(new Section("Simon Heffer", "http://www.telegraph.co.uk/comment/columnists/simonheffer/rss", 1));

        add(new Section("Motoring", "http://www.telegraph.co.uk/motoring/rss", 0));
        add(new Section("Motoring News", "http://www.telegraph.co.uk/motoring/news/rss", 1));

        add(new Section("Sport", "http://www.telegraph.co.uk/sport/rss", 0));
        add(new Section("Football", "http://www.telegraph.co.uk/sport/football/rss", 1));
        add(new Section("Cricket", "http://www.telegraph.co.uk/sport/cricket/rss", 1));
        add(new Section("Premier League", "http://www.telegraph.co.uk/sport/football/competitions/premier-league/rss", 1));
        add(new Section("Formula One", "http://www.telegraph.co.uk/sport/motorsport/formulaone/rss", 1));
        add(new Section("Rugby Union", "http://www.telegraph.co.uk/sport/rugbyunion/rss", 1));

        add(new Section("Travel", "http://www.telegraph.co.uk/travel/rss", 0));
        add(new Section("Hotels", "http://www.telegraph.co.uk/travel/hotels/rss", 1));
        add(new Section("Cruises", "http://www.telegraph.co.uk/travel/cruises/rss", 1));
        add(new Section("Destinations", "http://www.telegraph.co.uk/travel/destinations/rss", 1));

        add(new Section("Culture", "http://www.telegraph.co.uk/culture/rss", 0));
        add(new Section("Art", "http://www.telegraph.co.uk/culture/art/rss", 1));
        add(new Section("Books", "http://www.telegraph.co.uk/culture/books/rss", 1));
        add(new Section("Film", "http://www.telegraph.co.uk/culture/film/rss", 1));
        add(new Section("Music", "http://www.telegraph.co.uk/culture/music/rss", 1));
        add(new Section("TV and Radio", "http://www.telegraph.co.uk/culture/tvandradio/rss", 1));

        add(new Section("Technology", "http://www.telegraph.co.uk/technology/rss", 0));

        add(new Section("Picture Galleries", "http://www.telegraph.co.uk/news/picturegalleries/rss", 0));
        add(new Section("Pictures of the day", "http://www.telegraph.co.uk/news/picturegalleries/picturesoftheday/rss", 1));

        add(new Section("Food and Drink", "http://www.telegraph.co.uk/foodanddrink/rss", 0));

    }

}
