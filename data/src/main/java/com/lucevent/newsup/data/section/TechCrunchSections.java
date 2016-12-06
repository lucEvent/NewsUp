package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TechCrunchSections extends Sections {

    public TechCrunchSections()
    {
        super();

        add(new Section("Main", "http://feeds.feedburner.com/TechCrunch", 0));
        add(new Section("Startups", "http://feeds.feedburner.com/TechCrunch/startups", 0));
        add(new Section("Fundings and Exits", "http://feeds.feedburner.com/TechCrunch/fundings-exits", 0));
        add(new Section("Social", "http://feeds.feedburner.com/TechCrunch/social", 0));
        add(new Section("Mobile", "http://feeds.feedburner.com/Mobilecrunch", 0));
        add(new Section("Gadgets", "http://feeds.feedburner.com/crunchgear", 0));
        add(new Section("Europe", "http://feeds.feedburner.com/Techcrunch/europe", 0));
        add(new Section("Enterprise / TechCrunchIT", "http://feeds.feedburner.com/TechCrunchIT", 0));
        add(new Section("GreenTech", "http://feeds.feedburner.com/TechCrunch/greentech", 0));

        add(new Section("Authors", null, -1));
        add(new Section("John Biggs", "http://feeds.feedburner.com/TechCrunch/JohnBiggs", 1));
        add(new Section("Matt Burns", "http://feeds.feedburner.com/TechCrunch/MattBurns", 1));
        add(new Section("Mike Butcher", "http://feeds.feedburner.com/TechCrunch/MikeButcher", 1));
        add(new Section("Josh Constine", "http://feeds.feedburner.com/TechCrunch/JoshConstine", 1));
        add(new Section("Jordan Crook", "http://feeds.feedburner.com/TechCrunch/JordanCrook", 1));
        add(new Section("Anthony Ha", "http://feeds.feedburner.com/TechCrunch/AnthonyHa", 1));
        add(new Section("Frederic Lardinois", "http://feeds.feedburner.com/TechCrunch/FredericLardinois", 1));
        add(new Section("Ingrid Lunden", "http://feeds.feedburner.com/TechCrunch/IngridLunden", 1));
        add(new Section("Natasha Lomas", "http://feeds.feedburner.com/TechCrunch/NatashaLomas", 1));
        add(new Section("Sarah Perez", "http://feeds.feedburner.com/TechCrunch/SarahPerez", 1));

        add(new Section("Popular Subjects", null, -1));
        add(new Section("Amazon", "http://feeds.feedburner.com/TechCrunch/Amazon", 1));
        add(new Section("Android", "http://feeds.feedburner.com/TechCrunch/Android", 1));
        add(new Section("AOL", "http://feeds.feedburner.com/TechCrunch/AOL", 1));
        add(new Section("Apple", "http://feeds.feedburner.com/TechCrunch/Apple", 1));
        add(new Section("Facebook", "http://feeds.feedburner.com/TechCrunch/Facebook", 1));
        add(new Section("Google", "http://feeds.feedburner.com/TechCrunch/Google", 1));
        add(new Section("iPad", "http://feeds.feedburner.com/TechCrunch/ipad", 1));
        add(new Section("iPhone", "http://feeds.feedburner.com/TechCrunch/iphone", 1));
        add(new Section("LinkedIn", "http://feeds.feedburner.com/TechCrunch/LinkedIn", 1));
        add(new Section("Microsoft", "http://feeds.feedburner.com/TechCrunch/Microsoft", 1));
        add(new Section("Samsung", "http://feeds.feedburner.com/TechCrunch/Samsung", 1));
        add(new Section("Square", "http://feeds.feedburner.com/TechCrunch/Square", 1));
        add(new Section("Twitter", "http://feeds.feedburner.com/TechCrunch/Twitter", 1));
        add(new Section("Yahoo", "http://feeds.feedburner.com/TechCrunch/Yahoo", 1));

    }

}
