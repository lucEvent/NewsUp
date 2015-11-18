package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_DigitalTrendsNewsReader extends BE_NewsReader {

    public BE_DigitalTrendsNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("All Articles", "http://www.digitaltrends.com/feed/"));
        SECTIONS.add(new BE_Section("Cars", "http://www.digitaltrends.com/cars/feed/"));
        SECTIONS.add(new BE_Section("Computing", "http://www.digitaltrends.com/computing/feed/"));
        SECTIONS.add(new BE_Section("Cool Tech", "http://www.digitaltrends.com/cool-tech/feed/"));
        SECTIONS.add(new BE_Section("Gaming", "http://www.digitaltrends.com/gaming/feed/"));
        SECTIONS.add(new BE_Section("Home Theater", "http://www.digitaltrends.com/home-theater/feed/"));

        SECTIONS.add(new BE_Section("Mobile", "http://www.digitaltrends.com/mobile/feed/"));
        SECTIONS.add(new BE_Section("Android Army", "http://www.digitaltrends.com/android/feed/"));
        SECTIONS.add(new BE_Section("Apple", "http://www.digitaltrends.com/apple/feed/"));
        SECTIONS.add(new BE_Section("Mobile World Congress", "http://www.digitaltrends.com/mwc/feed/"));

        SECTIONS.add(new BE_Section("Photography", "http://www.digitaltrends.com/photography/feed/"));
        SECTIONS.add(new BE_Section("Social Media", "http://www.digitaltrends.com/social-media/feed/"));
        SECTIONS.add(new BE_Section("Home", "http://www.digitaltrends.com/home/feed/"));
        SECTIONS.add(new BE_Section("Sports", "http://www.digitaltrends.com/sports/feed/"));
        SECTIONS.add(new BE_Section("Business", "http://www.digitaltrends.com/business/feed/"));
        SECTIONS.add(new BE_Section("Buying Guides", "http://www.digitaltrends.com/buying-guides/feed/"));
        SECTIONS.add(new BE_Section("CES", "http://www.digitaltrends.com/ces/feed/"));
        SECTIONS.add(new BE_Section("DT Daily", "http://www.digitaltrends.com/dt-daily/feed/"));
        SECTIONS.add(new BE_Section("Features", "http://www.digitaltrends.com/features/feed/"));
        SECTIONS.add(new BE_Section("How-to", "http://www.digitaltrends.com/how-to/feed/"));
        SECTIONS.add(new BE_Section("IFA", "http://www.digitaltrends.com/ifa/feed/"));
        SECTIONS.add(new BE_Section("Movies & TV", "http://www.digitaltrends.com/movies/feed/"));
        SECTIONS.add(new BE_Section("Music", "http://www.digitaltrends.com/music/feed/"));
        SECTIONS.add(new BE_Section("Opinion", "http://www.digitaltrends.com/opinion/feed/"));
        SECTIONS.add(new BE_Section("Photo Galleries", "http://www.digitaltrends.com/photogalleries/feed/"));
        SECTIONS.add(new BE_Section("Podcasts", "http://www.digitaltrends.com/podcasts/feed/"));
        SECTIONS.add(new BE_Section("The Manual", "http://www.digitaltrends.com/the-manual/feed/"));
        SECTIONS.add(new BE_Section("Wearables", "http://www.digitaltrends.com/wearables/feed/"));
        SECTIONS.add(new BE_Section("Web", "http://www.digitaltrends.com/web/feed/"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        news.description = org.jsoup.Jsoup.parseBodyFragment(news.description).getElementsByTag("p").get(0).text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
        org.jsoup.select.Elements e = doc.select(".dt-video-container,.dt-iframe-header-media,.attachment-dt_header_media,.attachment-dt_header_media_full_width,article");

        if (!e.isEmpty()) {
            news.content = e.outerHtml();
        }
    }

}
