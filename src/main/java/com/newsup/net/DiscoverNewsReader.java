package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class DiscoverNewsReader extends NewsReader {


    public DiscoverNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Top stories", 0, "http://feeds.feedburner.com/DiscoverTopStories"));
        SECTIONS.add(new Section("All stories", 0, "http://feeds.feedburner.com/AllDiscovermagazinecomContent"));

        SECTIONS.add(new Section("Blogs", 0, null));
        SECTIONS.add(new Section("Latest blogs", 1, "http://feeds.feedburner.com/DiscoverBlogs"));
        SECTIONS.add(new Section("The Crux", 1, "http://feeds.feedburner.com/discovercrux"));
        SECTIONS.add(new Section("Citizen Science Salon", 1, "http://feeds.feedburner.com/citizen-science-salon"));
        SECTIONS.add(new Section("Drone 360", 1, "http://feeds.feedburner.com/drone360"));
        SECTIONS.add(new Section("The extremo files", 1, "http://feeds.feedburner.com/the-extremo-files"));
        SECTIONS.add(new Section("ImaGeo", 1, "http://feeds.feedburner.com/imageo"));
        SECTIONS.add(new Section("Lovesick Cyborg", 1, "http://feeds.feedburner.com/lovesick-cyborg"));
        SECTIONS.add(new Section("Neuroskeptic", 1, "http://feeds.feedburner.com/neuro-skeptic"));
        SECTIONS.add(new Section("Out There", 1, "http://feeds.feedburner.com/out-there"));
        SECTIONS.add(new Section("Science Sushi", 1, "http://feeds.feedburner.com/Science-Sushi"));

        SECTIONS.add(new Section("Topics", 0, null));
        SECTIONS.add(new Section("Health & Medicine", 1, "http://feeds.feedburner.com/DiscoverHealthMedicine"));
        SECTIONS.add(new Section("Mind Brain", 1, "http://feeds.feedburner.com/DiscoverMindBrain"));
        SECTIONS.add(new Section("Technology", 1, "http://feeds.feedburner.com/DiscoverTechnology"));
        SECTIONS.add(new Section("Space & Physics", 1, "http://feeds.feedburner.com/DiscoverSpace"));
        SECTIONS.add(new Section("Environment", 1, "http://feeds.feedburner.com/DiscoverEnvironment"));

        SECTIONS.add(new Section("Departments", 0, null));
        SECTIONS.add(new Section("20 Things You Didn't Know About...", 1, "http://feeds.feedburner.com/20ThingsYouDidntKnowAbout"));
        SECTIONS.add(new Section("Mind Over Matter", 1, "http://feeds.feedburner.com/mind-over-matter"));
        SECTIONS.add(new Section("Notes From Earth", 1, "http://feeds.feedburner.com/notes-from-earth"));
        SECTIONS.add(new Section("Out There", 1, "http://feeds.feedburner.com/discovermagazine-outthere"));
        SECTIONS.add(new Section("Vital Signs", 1, "http://feeds.feedburner.com/discovermagazine/VitalSigns"));

    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parseBodyFragment(news.description).text();
        return news;
    }

    @Override
    public News readNewsContent(News news) {
        org.jsoup.nodes.Document doc = getDocument(news.link);
        if (doc == null) return news;

        org.jsoup.select.Elements e = doc.select(".entry");
        if (e.isEmpty()) {
            e = doc.select(".segment");

            if (e.isEmpty()) {
                debug("No hay elementos");
                return news;
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
            img.attr("style","");
        }
        news.content = e.outerHtml();

        return news;
    }

}