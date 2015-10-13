package com.newsup.net;

import android.content.Context;
import android.os.Handler;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.kernel.list.Tags;

import org.jsoup.Jsoup;

public class DigitalTrendsNewsReader extends NewsReader {

    public DigitalTrendsNewsReader(Handler handler, Context context) {
        super(handler, context);

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("All Digital Trends Articles", 0, "http://www.digitaltrends.com/feed/"));
        SECTIONS.add(new Section("Cars", 0, "http://www.digitaltrends.com/cars/feed/"));
        SECTIONS.add(new Section("Computing", 0, "http://www.digitaltrends.com/computing/feed/"));
        SECTIONS.add(new Section("Cool Tech", 0, "http://www.digitaltrends.com/cool-tech/feed/"));
        SECTIONS.add(new Section("Gaming", 0, "http://www.digitaltrends.com/gaming/feed/"));
        SECTIONS.add(new Section("Home Theater", 0, "http://www.digitaltrends.com/home-theater/feed/"));
        SECTIONS.add(new Section("Mobile", 0, "http://www.digitaltrends.com/mobile/feed/"));
        SECTIONS.add(new Section("Photography", 0, "http://www.digitaltrends.com/photography/feed/"));
        SECTIONS.add(new Section("Social Media", 0, "http://www.digitaltrends.com/social-media/feed/"));
        SECTIONS.add(new Section("Home", 0, "http://www.digitaltrends.com/home/feed/"));
        SECTIONS.add(new Section("Sports", 0, "http://www.digitaltrends.com/sports/feed/"));

        SECTIONS.add(new Section("Android Army", 0, "http://www.digitaltrends.com/android/feed/"));
        SECTIONS.add(new Section("Apple", 0, "http://www.digitaltrends.com/apple/feed/"));
        SECTIONS.add(new Section("Business", 0, "http://www.digitaltrends.com/business/feed/"));
        SECTIONS.add(new Section("Buying Guides", 0, "http://www.digitaltrends.com/buying-guides/feed/"));
        SECTIONS.add(new Section("CES", 0, "http://www.digitaltrends.com/ces/feed/"));
        SECTIONS.add(new Section("CTIA", 0, "http://www.digitaltrends.com/ctia/feed/"));
        SECTIONS.add(new Section("DT Daily", 0, "http://www.digitaltrends.com/dt-daily/feed/"));
        SECTIONS.add(new Section("E3", 0, "http://www.digitaltrends.com/e3/feed/"));
        SECTIONS.add(new Section("Features", 0, "http://www.digitaltrends.com/features/feed/"));
        SECTIONS.add(new Section("GDC", 0, "http://www.digitaltrends.com/gdc-show/feed/"));
        SECTIONS.add(new Section("How-to", 0, "http://www.digitaltrends.com/how-to/feed/"));
        SECTIONS.add(new Section("IFA", 0, "http://www.digitaltrends.com/ifa/feed/"));
        SECTIONS.add(new Section("Mobile World Congress", 0, "http://www.digitaltrends.com/mwc/feed/"));
        SECTIONS.add(new Section("Movies & TV", 0, "http://www.digitaltrends.com/movies/feed/"));
        SECTIONS.add(new Section("Music", 0, "http://www.digitaltrends.com/music/feed/"));
        SECTIONS.add(new Section("Opinion", 0, "http://www.digitaltrends.com/opinion/feed/"));
        SECTIONS.add(new Section("PAX", 0, "http://www.digitaltrends.com/pax/feed/"));
        SECTIONS.add(new Section("Photo Galleries", 0, "http://www.digitaltrends.com/photogalleries/feed/"));
        SECTIONS.add(new Section("Podcasts", 0, "http://www.digitaltrends.com/podcasts/feed/"));
        SECTIONS.add(new Section("The Manual", 0, "http://www.digitaltrends.com/the-manual/feed/"));
        SECTIONS.add(new Section("Wearables", 0, "http://www.digitaltrends.com/wearables/feed/"));
        SECTIONS.add(new Section("Web", 0, "http://www.digitaltrends.com/web/feed/"));

    }

    @Override
    protected News getNewsLastFilter(String title, String link, String description, String date, Tags categories) {
        description = Jsoup.parse(description).getElementsByTag("p").get(0).text();
        return new News(title, link, description, date, categories);
    }

    @Override
    public News readNewsContent(News news) {
        try {
            org.jsoup.nodes.Document doc = getDocument(news.link);
            if (doc == null) return news;

            org.jsoup.nodes.Element element = doc.select(".l-content-wrap").get(0);
            news.content = element.html();

        } catch (Exception e) {
            debug("[ERROR La seleccion del articulo no se ha encontrado] tit:" + news.title);
            e.printStackTrace();
        }
        return news;
    }

}
