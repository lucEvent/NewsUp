package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_DiscoverNewsReader extends BE_NewsReader {

    public BE_DiscoverNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Top stories", "http://feeds.feedburner.com/DiscoverTopStories"));
        SECTIONS.add(new BE_Section("All stories", "http://feeds.feedburner.com/AllDiscovermagazinecomContent"));

        SECTIONS.add(new BE_Section("Blogs", null));
        SECTIONS.add(new BE_Section("Latest blogs", "http://feeds.feedburner.com/DiscoverBlogs"));
        SECTIONS.add(new BE_Section("The Crux", "http://feeds.feedburner.com/discovercrux"));
        SECTIONS.add(new BE_Section("Citizen Science Salon", "http://feeds.feedburner.com/citizen-science-salon"));
        SECTIONS.add(new BE_Section("Drone 360", "http://feeds.feedburner.com/drone360"));
        SECTIONS.add(new BE_Section("The extremo files", "http://feeds.feedburner.com/the-extremo-files"));
        SECTIONS.add(new BE_Section("ImaGeo", "http://feeds.feedburner.com/imageo"));
        SECTIONS.add(new BE_Section("Lovesick Cyborg", "http://feeds.feedburner.com/lovesick-cyborg"));
        SECTIONS.add(new BE_Section("Neuroskeptic", "http://feeds.feedburner.com/neuro-skeptic"));
        SECTIONS.add(new BE_Section("Out There", "http://feeds.feedburner.com/out-there"));
        SECTIONS.add(new BE_Section("Science Sushi", "http://feeds.feedburner.com/Science-Sushi"));

        SECTIONS.add(new BE_Section("Topics", null));
        SECTIONS.add(new BE_Section("Health & Medicine", "http://feeds.feedburner.com/DiscoverHealthMedicine"));
        SECTIONS.add(new BE_Section("Mind Brain", "http://feeds.feedburner.com/DiscoverMindBrain"));
        SECTIONS.add(new BE_Section("Technology", "http://feeds.feedburner.com/DiscoverTechnology"));
        SECTIONS.add(new BE_Section("Space & Physics", "http://feeds.feedburner.com/DiscoverSpace"));
        SECTIONS.add(new BE_Section("Environment", "http://feeds.feedburner.com/DiscoverEnvironment"));

        SECTIONS.add(new BE_Section("Departments", null));
        SECTIONS.add(new BE_Section("20 Things You Didn't Know About...", "http://feeds.feedburner.com/20ThingsYouDidntKnowAbout"));
        SECTIONS.add(new BE_Section("Mind Over Matter", "http://feeds.feedburner.com/mind-over-matter"));
        SECTIONS.add(new BE_Section("Notes From Earth", "http://feeds.feedburner.com/notes-from-earth"));
        SECTIONS.add(new BE_Section("Out There", "http://feeds.feedburner.com/discovermagazine-outthere"));
        SECTIONS.add(new BE_Section("Vital Signs", "http://feeds.feedburner.com/discovermagazine/VitalSigns"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        news.description = org.jsoup.Jsoup.parseBodyFragment(news.description).text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
        org.jsoup.select.Elements e = doc.select(".entry");
        if (e.isEmpty()) {
            e = doc.select(".segment");

            if (e.isEmpty()) {
                return;
            } else {
                org.jsoup.select.Elements imgs = e.select("img");

                for (org.jsoup.nodes.Element img : imgs) {
                    String src = img.attr("src");
                    String start = "http://discovermagazine.com";
                    if (!src.contains(start)) img.attr("src", start + src);
                }
            }
        } else {
            e.select(".navigation,h1,.meta,.shareIcons,blockquote,.categories,#disqus_thread,.fb-post").remove();
        }
        org.jsoup.select.Elements imgs = e.select("[style]");
        for (org.jsoup.nodes.Element img : imgs) {
            img.attr("style", "");
        }
        news.content = e.outerHtml();
    }

}