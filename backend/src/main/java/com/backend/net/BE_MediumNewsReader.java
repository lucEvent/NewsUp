package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_MediumNewsReader extends BE_NewsReader {

    public BE_MediumNewsReader() {
        super();
        HASH_LINK = "guid".hashCode();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Medium news", "https://medium.com/feed/the-story"));

        SECTIONS.add(new BE_Section("Languages", null));
        SECTIONS.add(new BE_Section("Deutsch", "https://medium.com/feed/deutsch"));
        SECTIONS.add(new BE_Section("Español", "https://medium.com/feed/espanol"));
        SECTIONS.add(new BE_Section("Français", "https://medium.com/feed/france"));
        SECTIONS.add(new BE_Section("Italiano", "https://medium.com/feed/italia"));
        SECTIONS.add(new BE_Section("Português", "https://medium.com/feed/brasil"));
        SECTIONS.add(new BE_Section("Polski", "https://medium.com/feed/polish"));
        SECTIONS.add(new BE_Section("Русский", "https://medium.com/feed/russian"));
        SECTIONS.add(new BE_Section("Türkçe", "https://medium.com/feed/turkce"));
        SECTIONS.add(new BE_Section("Український", "https://medium.com/feed/in-ukrainian"));
        SECTIONS.add(new BE_Section("日本語", "https://medium.com/feed/mediumjp"));
        SECTIONS.add(new BE_Section("中文", "https://medium.com/feed/chinese-language-collection"));
        SECTIONS.add(new BE_Section("한국어", "https://medium.com/feed/korean-medium-post"));

        SECTIONS.add(new BE_Section("Absurdist", "https://medium.com/feed/absurdist"));
        SECTIONS.add(new BE_Section("Android & Tech", "https://medium.com/feed/android-news"));
        SECTIONS.add(new BE_Section("ART + marketing", "https://medium.com/feed/art-marketing"));
        SECTIONS.add(new BE_Section("Back channel", "https://medium.com/feed/backchannel"));
        SECTIONS.add(new BE_Section("Bad words", "https://medium.com/feed/bad-words"));
        SECTIONS.add(new BE_Section("Building Basecamp 3", "https://medium.com/feed/woah-basecamp-3"));
        SECTIONS.add(new BE_Section("Climate Desk", "https://medium.com/feed/climate-desk"));
        SECTIONS.add(new BE_Section("Cuepoint", "https://medium.com/feed/cuepoint"));
        SECTIONS.add(new BE_Section("Curiosity Never Killed the Writer", "https://medium.com/feed/curiosity-never-killed-the-writer"));
        SECTIONS.add(new BE_Section("Design Insights from Bridge", "https://medium.com/feed/bridge-collection"));
        SECTIONS.add(new BE_Section("Entrepreneur's Handbook", "https://medium.com/feed/entrepreneur-s-handbook"));
        SECTIONS.add(new BE_Section("Facebook Design", "https://medium.com/feed/facebook-design"));
        SECTIONS.add(new BE_Section("Hackerpreneur magazine", "https://medium.com/feed/hackerpreneur-magazine"));
        SECTIONS.add(new BE_Section("Life Learning", "https://medium.com/feed/life-learning"));
        SECTIONS.add(new BE_Section("Life Well Lived", "https://medium.com/feed/life-well-lived"));
        SECTIONS.add(new BE_Section("Navigating the Sea of Singledom", "https://medium.com/feed/navigating-the-sea-of-singledom"));
        SECTIONS.add(new BE_Section("Personal Growth", "https://medium.com/feed/keep-learning-keep-growing"));
        SECTIONS.add(new BE_Section("Startup Study Group", "https://medium.com/feed/startup-study-group"));
        SECTIONS.add(new BE_Section("Startups, Wanderlust, and Life Hacking", "https://medium.com/feed/swlh"));
        SECTIONS.add(new BE_Section("Strong Medicine", "https://medium.com/feed/strong-medicine"));
        SECTIONS.add(new BE_Section("TED Fellows", "https://medium.com/feed/ted-fellows"));
        SECTIONS.add(new BE_Section("The Coffeelicious", "https://medium.com/feed/the-coffeelicious"));
        SECTIONS.add(new BE_Section("The Lighthouse", "https://medium.com/feed/the-lighthouse"));
        SECTIONS.add(new BE_Section("The Synapse", "https://medium.com/feed/synapse"));
        SECTIONS.add(new BE_Section("The year of the looking glass", "https://medium.com/feed/the-year-of-the-looking-glass"));
        SECTIONS.add(new BE_Section("Travel & Adventure", "https://medium.com/feed/travel-adventure"));
        SECTIONS.add(new BE_Section("Twenty Years in the Valley", "https://medium.com/feed/twenty-years-in-the-valley"));
        SECTIONS.add(new BE_Section("Uprooted IRC", "https://medium.com/feed/uprooted"));
        SECTIONS.add(new BE_Section("What's in the [box]?", "https://medium.com/feed/what-s-in-the-box"));
        SECTIONS.add(new BE_Section("What's The Future of Work?", "https://medium.com/feed/the-wtf-economy"));
        SECTIONS.add(new BE_Section("Working Parents", "https://medium.com/feed/working-parents-in-america"));

        SECTIONS.add(new BE_Section("Authors", null));
        SECTIONS.add(new BE_Section("Aaron Bleyaert", "https://medium.com/feed/@AaronBleyaert"));
        SECTIONS.add(new BE_Section("Akinori Machino", "https://medium.com/feed/@mach"));
        SECTIONS.add(new BE_Section("Alana Massey", "https://medium.com/feed/@AlanaMassey"));
        SECTIONS.add(new BE_Section("Ali Mese", "https://medium.com/feed/@meseali"));
        SECTIONS.add(new BE_Section("Bibblio.org", "https://medium.com/feed/@bibblio_org"));
        SECTIONS.add(new BE_Section("Chaz Hutton", "https://medium.com/feed/@chazhutton"));
        SECTIONS.add(new BE_Section("Chris Danilo", "https://medium.com/feed/@theCountDanilo"));
        SECTIONS.add(new BE_Section("Dave Schools", "https://medium.com/feed/@DaveSchools"));
        SECTIONS.add(new BE_Section("Dustin Senos", "https://medium.com/feed/@dustin"));
        SECTIONS.add(new BE_Section("Gustavo Tanaka", "https://medium.com/feed/@gutanaka/"));
        SECTIONS.add(new BE_Section("Hazel Gale", "https://medium.com/feed/@HazelGale"));
        SECTIONS.add(new BE_Section("Henry Wismayer", "https://medium.com/feed/@henrywismayer"));
        SECTIONS.add(new BE_Section("International Rescue Committee", "https://medium.com/feed/@theIRC"));
        SECTIONS.add(new BE_Section("Isaac Morehouse", "https://medium.com/feed/@isaacmorehouse"));
        SECTIONS.add(new BE_Section("James Altucher", "https://medium.com/feed/@jaltucher"));
        SECTIONS.add(new BE_Section("James L. Walpole", "https://medium.com/feed/@JamesLWalpole"));
        SECTIONS.add(new BE_Section("Jamie Varon", "https://medium.com/feed/@jamievaron"));
        SECTIONS.add(new BE_Section("Jason Fried", "https://medium.com/feed/@jasonfried"));
        SECTIONS.add(new BE_Section("Jay Acunzo", "https://medium.com/feed/@jay_zo"));
        SECTIONS.add(new BE_Section("Jeff Goins", "https://medium.com/feed/@jeffgoins"));
        SECTIONS.add(new BE_Section("Jeffrey Zeldman", "https://medium.com/feed/@zeldman"));
        SECTIONS.add(new BE_Section("Julie Zhuo", "https://medium.com/feed/@joulee"));
        SECTIONS.add(new BE_Section("Katja Bak", "https://medium.com/feed/@katja_ghost"));
        SECTIONS.add(new BE_Section("Louis Tsai", "https://medium.com/feed/@LouisTsai"));
        SECTIONS.add(new BE_Section("M.G. Siegler", "https://medium.com/feed/@mgsiegler"));
        SECTIONS.add(new BE_Section("Mark S. Luckie", "https://medium.com/feed/@marksluckie"));
        SECTIONS.add(new BE_Section("Megan Quinn", "https://medium.com/feed/@msquinn"));
        SECTIONS.add(new BE_Section("Michael Pusateri", "https://medium.com/feed/@cruftbox"));
        SECTIONS.add(new BE_Section("Nic Haralambous", "https://medium.com/feed/@nicharry"));
        SECTIONS.add(new BE_Section("Nick Jack Pappas", "https://medium.com/feed/@pappiness"));
        SECTIONS.add(new BE_Section("Preethi Kasireddy", "https://medium.com/feed/@preethikasireddy"));
        SECTIONS.add(new BE_Section("Product Hunt", "https://medium.com/feed/@producthunt"));
        SECTIONS.add(new BE_Section("Rands", "https://medium.com/feed/@rands"));
        SECTIONS.add(new BE_Section("Renée Padgham", "https://medium.com/feed/@reneepadgham"));
        SECTIONS.add(new BE_Section("Stella J.", "https://medium.com/feed/@writingsolo"));
        SECTIONS.add(new BE_Section("Steven Levy", "https://medium.com/feed/@stevenlevy"));
        SECTIONS.add(new BE_Section("Thomas Fuchs", "https://medium.com/feed/@thomasfuchs"));
        SECTIONS.add(new BE_Section("Thomas Oppong", "https://medium.com/feed/@alltopstartups"));
        SECTIONS.add(new BE_Section("Tobias van Schneider", "https://medium.com/feed/@vanschneider"));
    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).select(".medium-feed-snippet").html();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
        org.jsoup.select.Elements e = doc.select("main section .section-content");

        for (org.jsoup.nodes.Element title : e.select("h3")) {
            title.remove();
            break;
        }

        for (org.jsoup.nodes.Element fig : e.select("figure")) {
            org.jsoup.select.Elements img = fig.select("noscript");
            fig.html(img.html());
        }

        news.content = e.html();
    }

}
