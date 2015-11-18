package com.newsup.net.dev;

import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.NewsReader;

public class TheTelegraphNewsReader extends NewsReader {

    public TheTelegraphNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new SectionDeprecated("UK News", 0, "http://www.telegraph.co.uk/news/uknews/rss"));
        SECTIONS.add(new SectionDeprecated("World News", 0, "http://www.telegraph.co.uk/news/worldnews/rss"));
        SECTIONS.add(new SectionDeprecated("Politics", 0, "http://www.telegraph.co.uk/news/politics/rss"));
        SECTIONS.add(new SectionDeprecated("How about that?", 0, "http://www.telegraph.co.uk/news/newstopics/howaboutthat/rss"));
        SECTIONS.add(new SectionDeprecated("Technology News", 0, "http://www.telegraph.co.uk/technology/news/rss"));
        SECTIONS.add(new SectionDeprecated("Finance", 0, "http://www.telegraph.co.uk/finance/rss"));
        SECTIONS.add(new SectionDeprecated("Sport", 0, "http://www.telegraph.co.uk/sport/rss"));
        SECTIONS.add(new SectionDeprecated("Football", 0, "http://www.telegraph.co.uk/sport/football/rss"));
        SECTIONS.add(new SectionDeprecated("Christmas", 0, "http://www.telegraph.co.uk/topics/christmas/rss"));
        SECTIONS.add(new SectionDeprecated("Travel News", 0, "http://www.telegraph.co.uk/travel/travelnews/rss"));
        SECTIONS.add(new SectionDeprecated("Personal Finance", 0, "http://www.telegraph.co.uk/finance/personalfinance/rss"));

        SECTIONS.add(new SectionDeprecated("News", 0, "http://www.telegraph.co.uk/news/rss"));
        SECTIONS.add(new SectionDeprecated("Politics", 1, "http://www.telegraph.co.uk/news/politics/rss"));
        SECTIONS.add(new SectionDeprecated("The Royal Family", 1, "http://www.telegraph.co.uk/news/uknews/theroyalfamily/rss"));
        SECTIONS.add(new SectionDeprecated("Celebrity news", 1, "http://www.telegraph.co.uk/news/celebritynews/rss"));
        SECTIONS.add(new SectionDeprecated("Science News", 1, "http://www.telegraph.co.uk/news/science/science-news/rss"));
        SECTIONS.add(new SectionDeprecated("Earth News", 1, "http://www.telegraph.co.uk/news/earth/earthnews/rss"));

        SECTIONS.add(new SectionDeprecated("Finance", 0, "http://www.telegraph.co.uk/finance/rss"));
        SECTIONS.add(new SectionDeprecated("Ambrose Evans-Pritchard", 1, "http://www.telegraph.co.uk/finance/comment/ambroseevans_pritchard/rss"));
        SECTIONS.add(new SectionDeprecated("Jeff Randall", 1, "http://www.telegraph.co.uk/finance/comment/jeffrandall/rss"));
        SECTIONS.add(new SectionDeprecated("Economics", 1, "http://www.telegraph.co.uk/finance/economics/rss"));
        SECTIONS.add(new SectionDeprecated("Markets", 1, "http://www.telegraph.co.uk/finance/markets/rss"));

        SECTIONS.add(new SectionDeprecated("Comment", 0, "http://www.telegraph.co.uk/comment/rss"));
        SECTIONS.add(new SectionDeprecated("Columnists", 1, "http://www.telegraph.co.uk/comment/columnists/rss"));
        SECTIONS.add(new SectionDeprecated("Boris Johnson", 1, "http://www.telegraph.co.uk/comment/columnists/borisjohnson/rss"));
        SECTIONS.add(new SectionDeprecated("Simon Heffer", 1, "http://www.telegraph.co.uk/comment/columnists/simonheffer/rss"));

        SECTIONS.add(new SectionDeprecated("Motoring", 0, "http://www.telegraph.co.uk/motoring/rss"));
        SECTIONS.add(new SectionDeprecated("Car Advice", 1, "http://www.telegraph.co.uk/motoring/caradvice/rss"));
        SECTIONS.add(new SectionDeprecated("Honest John", 1, "http://www.telegraph.co.uk/motoring/caradvice/honestjohn/rss"));
        SECTIONS.add(new SectionDeprecated("First Drives", 1, "http://www.telegraph.co.uk/motoring/first-drives/rss"));
        SECTIONS.add(new SectionDeprecated("James May", 1, "http://www.telegraph.co.uk/motoring/columnists/jamesmay/rss"));
        SECTIONS.add(new SectionDeprecated("Motoring News", 1, "http://www.telegraph.co.uk/motoring/news/rss"));

        SECTIONS.add(new SectionDeprecated("Property", 0, "http://www.telegraph.co.uk/finance/property/rss"));
        SECTIONS.add(new SectionDeprecated("International Property", 1, "http://www.telegraph.co.uk/finance/property/international/rss"));
        SECTIONS.add(new SectionDeprecated("Property Market", 1, "http://www.telegraph.co.uk/finance/property/property-market/rss"));
        SECTIONS.add(new SectionDeprecated("Property News", 1, "http://www.telegraph.co.uk/finance/property/news/rss"));

        SECTIONS.add(new SectionDeprecated("Sport", 0, "http://www.telegraph.co.uk/sport/rss"));
        SECTIONS.add(new SectionDeprecated("Columnists", 1, "http://www.telegraph.co.uk/sport/columnists/rss"));
        SECTIONS.add(new SectionDeprecated("Cricket", 1, "http://www.telegraph.co.uk/sport/cricket/rss"));
        SECTIONS.add(new SectionDeprecated("Premier League", 1, "http://www.telegraph.co.uk/sport/football/competitions/premier-league/rss"));
        SECTIONS.add(new SectionDeprecated("Formula One", 1, "http://www.telegraph.co.uk/sport/motorsport/formulaone/rss"));
        SECTIONS.add(new SectionDeprecated("Rugby Union", 1, "http://www.telegraph.co.uk/sport/rugbyunion/rss"));

        SECTIONS.add(new SectionDeprecated("Travel", 0, "http://www.telegraph.co.uk/travel/rss"));
        SECTIONS.add(new SectionDeprecated("Columnists", 1, "http://www.telegraph.co.uk/travel/columnists/rss"));
        SECTIONS.add(new SectionDeprecated("Hotels", 1, "http://www.telegraph.co.uk/travel/hotels/rss"));
        SECTIONS.add(new SectionDeprecated("Cruises", 1, "http://www.telegraph.co.uk/travel/cruises/rss"));
        SECTIONS.add(new SectionDeprecated("Destinations", 1, "http://www.telegraph.co.uk/travel/destinations/rss"));

        SECTIONS.add(new SectionDeprecated("Culture", 0, "http://www.telegraph.co.uk/culture/rss"));
        SECTIONS.add(new SectionDeprecated("Art", 1, "http://www.telegraph.co.uk/culture/art/rss"));
        SECTIONS.add(new SectionDeprecated("Books", 1, "http://www.telegraph.co.uk/culture/books/rss"));
        SECTIONS.add(new SectionDeprecated("Film", 1, "http://www.telegraph.co.uk/culture/film/rss"));
        SECTIONS.add(new SectionDeprecated("Music", 1, "http://www.telegraph.co.uk/culture/music/rss"));
        SECTIONS.add(new SectionDeprecated("TV and Radio", 1, "http://www.telegraph.co.uk/culture/tvandradio/rss"));

        SECTIONS.add(new SectionDeprecated("Technology", 0, "http://www.telegraph.co.uk/technology/rss"));

        SECTIONS.add(new SectionDeprecated("Mother Tongue", 0, "http://www.telegraph.co.uk/women/mother-tongue/rss"));
        SECTIONS.add(new SectionDeprecated("Family Advice", 1, "http://www.telegraph.co.uk/women/mother-tongue/familyadvice/rss"));

        SECTIONS.add(new SectionDeprecated("Picture Galleries", 0, "http://www.telegraph.co.uk/news/picturegalleries/rss"));
        SECTIONS.add(new SectionDeprecated("Pictures of the day", 1, "http://www.telegraph.co.uk/news/picturegalleries/picturesoftheday/rss"));

        SECTIONS.add(new SectionDeprecated("Food and Drink", 0, "http://www.telegraph.co.uk/foodanddrink/rss"));
        SECTIONS.add(new SectionDeprecated("Food and Drink Advice", 0, "http://www.telegraph.co.uk/foodanddrink/foodanddrinkadvice/rss"));
        SECTIONS.add(new SectionDeprecated("Recipes", 0, "http://www.telegraph.co.uk/foodanddrink/recipes/rss"));
        SECTIONS.add(new SectionDeprecated("Restaurants", 0, "http://www.telegraph.co.uk/foodanddrink/restaurants/rss"));

    }

}