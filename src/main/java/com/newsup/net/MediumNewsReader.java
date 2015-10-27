package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MediumNewsReader extends NewsReader {

    public MediumNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("All latest", 0, "https://medium.com/feed/the-story"));

        SECTIONS.add(new Section("Absurdist", 0, "https://medium.com/feed/absurdist"));
        SECTIONS.add(new Section("Android & Tech", 0, "https://medium.com/feed/android-news"));
        SECTIONS.add(new Section("ART + marketing", 0, "https://medium.com/feed/art-marketing"));
        SECTIONS.add(new Section("Back channel", 0, "https://medium.com/feed/backchannel"));
        SECTIONS.add(new Section("Bad words", 0, "https://medium.com/feed/bad-words"));
        SECTIONS.add(new Section("Building Basecamp 3", 0, "https://medium.com/feed/woah-basecamp-3"));
        SECTIONS.add(new Section("Climate Desk", 0, "https://medium.com/feed/climate-desk"));
        SECTIONS.add(new Section("Cuepoint", 0, "https://medium.com/feed/cuepoint"));
        SECTIONS.add(new Section("Curiosity Never Killed the Writer", 0, "https://medium.com/feed/curiosity-never-killed-the-writer"));
        SECTIONS.add(new Section("Design Insights from Bridge", 0, "https://medium.com/feed/bridge-collection"));
        SECTIONS.add(new Section("Disability Stories", 0, "https://medium.com/feed/disability-stories"));
        SECTIONS.add(new Section("Entrepreneur's Handbook", 0, "https://medium.com/feed/entrepreneur-s-handbook"));
        SECTIONS.add(new Section("Facebook Design", 0, "https://medium.com/feed/facebook-design"));
        SECTIONS.add(new Section("Hackerpreneur magazine", 0, "https://medium.com/feed/hackerpreneur-magazine"));
        SECTIONS.add(new Section("Life Learning", 0, "https://medium.com/feed/life-learning"));
        SECTIONS.add(new Section("Life Tips", 0, "https://medium.com/feed/life-tips"));
        SECTIONS.add(new Section("Life Well Lived", 0, "https://medium.com/feed/life-well-lived"));
        SECTIONS.add(new Section("Navigating the Sea of Singledom", 0, "https://medium.com/feed/navigating-the-sea-of-singledom"));
        SECTIONS.add(new Section("Personal Growth", 0, "https://medium.com/feed/keep-learning-keep-growing"));
        SECTIONS.add(new Section("Startup Study Group", 0, "https://medium.com/feed/startup-study-group"));
        SECTIONS.add(new Section("Startups, Wanderlust, and Life Hacking", 0, "https://medium.com/feed/swlh"));
        SECTIONS.add(new Section("Strong Medicine", 0, "https://medium.com/feed/strong-medicine"));
        SECTIONS.add(new Section("Tech talks", 0, "https://medium.com/feed/tech-talk"));
        SECTIONS.add(new Section("TED Fellows", 0, "https://medium.com/feed/ted-fellows"));
        SECTIONS.add(new Section("The Coffeelicious", 0, "https://medium.com/feed/the-coffeelicious"));
        SECTIONS.add(new Section("The Lighthouse", 0, "https://medium.com/feed/the-lighthouse"));
        SECTIONS.add(new Section("The Synapse", 0, "https://medium.com/feed/synapse"));
        SECTIONS.add(new Section("The year of the looking glass", 0, "https://medium.com/feed/the-year-of-the-looking-glass"));
        SECTIONS.add(new Section("Travel & Adventure", 0, "https://medium.com/feed/travel-adventure"));
        SECTIONS.add(new Section("Twenty Years in the Valley", 0, "https://medium.com/feed/twenty-years-in-the-valley"));
        SECTIONS.add(new Section("Uprooted IRC", 0, "https://medium.com/feed/uprooted"));
        SECTIONS.add(new Section("What's in the [box]?", 0, "https://medium.com/feed/what-s-in-the-box"));
        SECTIONS.add(new Section("What's The Future of Work?", 0, "https://medium.com/feed/the-wtf-economy"));
        SECTIONS.add(new Section("Working Parents", 0, "https://medium.com/feed/working-parents-in-america"));

        SECTIONS.add(new Section("Authors", 0, null));
        SECTIONS.add(new Section("Aaron Bleyaert", 1, "https://medium.com/feed/@AaronBleyaert"));
        SECTIONS.add(new Section("Akinori Machino", 1, "https://medium.com/feed/@mach"));
        SECTIONS.add(new Section("Alana Massey", 1, "https://medium.com/feed/@AlanaMassey"));
        SECTIONS.add(new Section("Ali Mese", 1, "https://medium.com/feed/@meseali"));
        SECTIONS.add(new Section("Andrei Herasimchuk", 1, "https://medium.com/feed/@trenti"));
        SECTIONS.add(new Section("Andrew Wilkinson", 1, "https://medium.com/feed/@awilkinson"));
        SECTIONS.add(new Section("Bibblio.org", 1, "https://medium.com/feed/@bibblio_org"));
        SECTIONS.add(new Section("Bruce Kasanoff", 1, "https://medium.com/feed/@kasanoff"));
        SECTIONS.add(new Section("CamMi Pham", 1, "https://medium.com/feed/@cammipham"));
        SECTIONS.add(new Section("Cathryn Lavery", 1, "https://medium.com/feed/@bestselfco"));
        SECTIONS.add(new Section("Chaz Hutton", 1, "https://medium.com/feed/@chazhutton"));
        SECTIONS.add(new Section("Chris Danilo", 1, "https://medium.com/feed/@theCountDanilo"));
        SECTIONS.add(new Section("Craig Havighurst", 1, "https://medium.com/feed/@chavighurst"));
        SECTIONS.add(new Section("Daniel Eckler", 1, "https://medium.com/feed/@danieleckler"));
        SECTIONS.add(new Section("Dave Schools", 1, "https://medium.com/feed/@DaveSchools"));
        SECTIONS.add(new Section("Dustin Senos", 1, "https://medium.com/feed/@dustin"));
        SECTIONS.add(new Section("Gustavo Tanaka", 1, "https://medium.com/feed/@gutanaka/"));
        SECTIONS.add(new Section("Hazel Gale", 1, "https://medium.com/feed/@HazelGale"));
        SECTIONS.add(new Section("Henry Wismayer", 1, "https://medium.com/feed/@henrywismayer"));
        SECTIONS.add(new Section("International Rescue Committee", 1, "https://medium.com/feed/@theIRC"));
        SECTIONS.add(new Section("Isaac Morehouse", 1, "https://medium.com/feed/@isaacmorehouse"));
        SECTIONS.add(new Section("Jack Dorsey", 1, "https://medium.com/feed/@jack"));
        SECTIONS.add(new Section("James Altucher", 1, "https://medium.com/feed/@jaltucher"));
        SECTIONS.add(new Section("James L. Walpole", 1, "https://medium.com/feed/@JamesLWalpole"));
        SECTIONS.add(new Section("Jamie Varon", 1, "https://medium.com/feed/@jamievaron"));
        SECTIONS.add(new Section("Jason Fried", 1, "https://medium.com/feed/@jasonfried"));
        SECTIONS.add(new Section("Jay Acunzo", 1, "https://medium.com/feed/@jay_zo"));
        SECTIONS.add(new Section("Jeff Goins", 1, "https://medium.com/feed/@jeffgoins"));
        SECTIONS.add(new Section("Jeffrey Zeldman", 1, "https://medium.com/feed/@zeldman"));
        SECTIONS.add(new Section("Jon Steinback", 1, "https://medium.com/feed/@itsmejon"));
        SECTIONS.add(new Section("Julie Zhuo", 1, "https://medium.com/feed/@joulee"));
        SECTIONS.add(new Section("Katja Bak", 1, "https://medium.com/feed/@katja_ghost"));
        SECTIONS.add(new Section("Louis Tsai", 1, "https://medium.com/feed/@LouisTsai"));
        SECTIONS.add(new Section("M.G. Siegler", 1, "https://medium.com/feed/@mgsiegler"));
        SECTIONS.add(new Section("Margaret Gould Stewart", 1, "https://medium.com/feed/@mags"));
        SECTIONS.add(new Section("Mark S. Luckie", 1, "https://medium.com/feed/@marksluckie"));
        SECTIONS.add(new Section("Max Chanowitz", 1, "https://medium.com/feed/@maxchanowitz"));
        SECTIONS.add(new Section("Meg Nordmann", 1, "https://medium.com/feed/@MegNordmann"));
        SECTIONS.add(new Section("Megan Quinn", 1, "https://medium.com/feed/@msquinn"));
        SECTIONS.add(new Section("Michael Pusateri", 1, "https://medium.com/feed/@cruftbox"));
        SECTIONS.add(new Section("Morgan Rock", 1, "https://medium.com/feed/@morocklo"));
        SECTIONS.add(new Section("Nic Haralambous", 1, "https://medium.com/feed/@nicharry"));
        SECTIONS.add(new Section("Nick Jack Pappas", 1, "https://medium.com/feed/@pappiness"));
        SECTIONS.add(new Section("Preethi Kasireddy", 1, "https://medium.com/feed/@preethikasireddy"));
        SECTIONS.add(new Section("Product Hunt", 1, "https://medium.com/feed/@producthunt"));
        SECTIONS.add(new Section("Rands", 1, "https://medium.com/feed/@rands"));
        SECTIONS.add(new Section("Renée Padgham", 1, "https://medium.com/feed/@reneepadgham"));
        SECTIONS.add(new Section("Sara Goldstein", 1, "https://medium.com/feed/@sarachasen"));
        SECTIONS.add(new Section("Scott Weiss", 1, "https://medium.com/feed/@w_scottweiss"));
        SECTIONS.add(new Section("Stella J.", 1, "https://medium.com/feed/@writingsolo"));
        SECTIONS.add(new Section("Steven Levy", 1, "https://medium.com/feed/@stevenlevy"));
        SECTIONS.add(new Section("Thomas Fuchs", 1, "https://medium.com/feed/@thomasfuchs"));
        SECTIONS.add(new Section("Thomas Oppong", 1, "https://medium.com/feed/@alltopstartups"));
        SECTIONS.add(new Section("Tobias van Schneider", 1, "https://medium.com/feed/@vanschneider"));
        SECTIONS.add(new Section("Umair Haque", 1, "https://medium.com/feed/@umairh"));
        SECTIONS.add(new Section("Xiao Ma", 1, "https://medium.com/feed/@xiaoma"));

    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).select(".medium-feed-snippet").html();
        return news;
    }

    @Override
    public News readNewsContent(News news) {
        try {
            org.jsoup.nodes.Document doc = getDocument(news.link);
            if (doc == null) return news;

            org.jsoup.nodes.Element e = doc.select("main").get(0);
            org.jsoup.select.Elements h3s = e.select("h3");
            if (!h3s.isEmpty()) h3s.first().remove();

            news.content = e.html();
        } catch (Exception e) {
            debug("[ERROR] link:" + news.link);
            e.printStackTrace();
        }
        return news;
    }

    protected Document getDocument(String pagelink) throws IOException {
        try {
            return Jsoup.connect(pagelink).userAgent("Mozilla/5.0 (Linux; Android 4.4.2; GT-I9300 Build/KVT49L) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.94 Mobile Safari/537.36").get();
        } catch (java.net.SocketTimeoutException e) {
            debug("No se ha podido encontrar la pagina: " + pagelink);
        }
        return null;
    }

}
