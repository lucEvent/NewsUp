package com.newsup.net.dev;

import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.NewsReader;

public class TheTelegraphNewsReader extends NewsReader {

    public TheTelegraphNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("UK News", 0, "http://www.telegraph.co.uk/news/uknews/rss"));
        SECTIONS.add(new Section("World News", 0, "http://www.telegraph.co.uk/news/worldnews/rss"));
        SECTIONS.add(new Section("Politics", 0, "http://www.telegraph.co.uk/news/politics/rss"));
        SECTIONS.add(new Section("How about that?", 0, "http://www.telegraph.co.uk/news/newstopics/howaboutthat/rss"));
        SECTIONS.add(new Section("Technology News", 0, "http://www.telegraph.co.uk/technology/news/rss"));
        SECTIONS.add(new Section("Finance", 0, "http://www.telegraph.co.uk/finance/rss"));
        SECTIONS.add(new Section("Sport", 0, "http://www.telegraph.co.uk/sport/rss"));
        SECTIONS.add(new Section("Football", 0, "http://www.telegraph.co.uk/sport/football/rss"));
        SECTIONS.add(new Section("Christmas", 0, "http://www.telegraph.co.uk/topics/christmas/rss"));
        SECTIONS.add(new Section("Travel News", 0, "http://www.telegraph.co.uk/travel/travelnews/rss"));
        SECTIONS.add(new Section("Personal Finance", 0, "http://www.telegraph.co.uk/finance/personalfinance/rss"));

        SECTIONS.add(new Section("News", 0, "http://www.telegraph.co.uk/news/rss"));
        SECTIONS.add(new Section("Politics", 1, "http://www.telegraph.co.uk/news/politics/rss"));
        SECTIONS.add(new Section("The Royal Family", 1, "http://www.telegraph.co.uk/news/uknews/theroyalfamily/rss"));
        SECTIONS.add(new Section("Celebrity news", 1, "http://www.telegraph.co.uk/news/celebritynews/rss"));
        SECTIONS.add(new Section("Science News", 1, "http://www.telegraph.co.uk/news/science/science-news/rss"));
        SECTIONS.add(new Section("Earth News", 1, "http://www.telegraph.co.uk/news/earth/earthnews/rss"));

        SECTIONS.add(new Section("Finance", 0, "http://www.telegraph.co.uk/finance/rss"));
        SECTIONS.add(new Section("Ambrose Evans-Pritchard", 1, "http://www.telegraph.co.uk/finance/comment/ambroseevans_pritchard/rss"));
        SECTIONS.add(new Section("Jeff Randall", 1, "http://www.telegraph.co.uk/finance/comment/jeffrandall/rss"));
        SECTIONS.add(new Section("Economics", 1, "http://www.telegraph.co.uk/finance/economics/rss"));
        SECTIONS.add(new Section("Markets", 1, "http://www.telegraph.co.uk/finance/markets/rss"));

        SECTIONS.add(new Section("Comment", 0, "http://www.telegraph.co.uk/comment/rss"));
        SECTIONS.add(new Section("Columnists", 1, "http://www.telegraph.co.uk/comment/columnists/rss"));
        SECTIONS.add(new Section("Boris Johnson", 1, "http://www.telegraph.co.uk/comment/columnists/borisjohnson/rss"));
        SECTIONS.add(new Section("Simon Heffer", 1, "http://www.telegraph.co.uk/comment/columnists/simonheffer/rss"));

        SECTIONS.add(new Section("Motoring", 0, "http://www.telegraph.co.uk/motoring/rss"));
        SECTIONS.add(new Section("Car Advice", 1, "http://www.telegraph.co.uk/motoring/caradvice/rss"));
        SECTIONS.add(new Section("Honest John", 1, "http://www.telegraph.co.uk/motoring/caradvice/honestjohn/rss"));
        SECTIONS.add(new Section("First Drives", 1, "http://www.telegraph.co.uk/motoring/first-drives/rss"));
        SECTIONS.add(new Section("James May", 1, "http://www.telegraph.co.uk/motoring/columnists/jamesmay/rss"));
        SECTIONS.add(new Section("Motoring News", 1, "http://www.telegraph.co.uk/motoring/news/rss"));

        SECTIONS.add(new Section("Property", 0, "http://www.telegraph.co.uk/finance/property/rss"));
        SECTIONS.add(new Section("International Property", 1, "http://www.telegraph.co.uk/finance/property/international/rss"));
        SECTIONS.add(new Section("Property Market", 1, "http://www.telegraph.co.uk/finance/property/property-market/rss"));
        SECTIONS.add(new Section("Property News", 1, "http://www.telegraph.co.uk/finance/property/news/rss"));

        SECTIONS.add(new Section("Sport", 0, "http://www.telegraph.co.uk/sport/rss"));
        SECTIONS.add(new Section("Columnists", 1, "http://www.telegraph.co.uk/sport/columnists/rss"));
        SECTIONS.add(new Section("Cricket", 1, "http://www.telegraph.co.uk/sport/cricket/rss"));
        SECTIONS.add(new Section("Premier League", 1, "http://www.telegraph.co.uk/sport/football/competitions/premier-league/rss"));
        SECTIONS.add(new Section("Formula One", 1, "http://www.telegraph.co.uk/sport/motorsport/formulaone/rss"));
        SECTIONS.add(new Section("Rugby Union", 1, "http://www.telegraph.co.uk/sport/rugbyunion/rss"));

        SECTIONS.add(new Section("Travel", 0, "http://www.telegraph.co.uk/travel/rss"));
        SECTIONS.add(new Section("Columnists", 1, "http://www.telegraph.co.uk/travel/columnists/rss"));
        SECTIONS.add(new Section("Hotels", 1, "http://www.telegraph.co.uk/travel/hotels/rss"));
        SECTIONS.add(new Section("Cruises", 1, "http://www.telegraph.co.uk/travel/cruises/rss"));
        SECTIONS.add(new Section("Destinations", 1, "http://www.telegraph.co.uk/travel/destinations/rss"));

        SECTIONS.add(new Section("Culture", 0, "http://www.telegraph.co.uk/culture/rss"));
        SECTIONS.add(new Section("Art", 1, "http://www.telegraph.co.uk/culture/art/rss"));
        SECTIONS.add(new Section("Books", 1, "http://www.telegraph.co.uk/culture/books/rss"));
        SECTIONS.add(new Section("Film", 1, "http://www.telegraph.co.uk/culture/film/rss"));
        SECTIONS.add(new Section("Music", 1, "http://www.telegraph.co.uk/culture/music/rss"));
        SECTIONS.add(new Section("TV and Radio", 1, "http://www.telegraph.co.uk/culture/tvandradio/rss"));

        SECTIONS.add(new Section("Technology", 0, "http://www.telegraph.co.uk/technology/rss"));

        SECTIONS.add(new Section("Mother Tongue", 0, "http://www.telegraph.co.uk/women/mother-tongue/rss"));
        SECTIONS.add(new Section("Family Advice", 1, "http://www.telegraph.co.uk/women/mother-tongue/familyadvice/rss"));

        SECTIONS.add(new Section("Picture Galleries", 0, "http://www.telegraph.co.uk/news/picturegalleries/rss"));
        SECTIONS.add(new Section("Pictures of the day", 1, "http://www.telegraph.co.uk/news/picturegalleries/picturesoftheday/rss"));

        SECTIONS.add(new Section("Food and Drink", 0, "http://www.telegraph.co.uk/foodanddrink/rss"));
        SECTIONS.add(new Section("Food and Drink Advice", 0, "http://www.telegraph.co.uk/foodanddrink/foodanddrinkadvice/rss"));
        SECTIONS.add(new Section("Recipes", 0, "http://www.telegraph.co.uk/foodanddrink/recipes/rss"));
        SECTIONS.add(new Section("Restaurants", 0, "http://www.telegraph.co.uk/foodanddrink/restaurants/rss"));

    }

}