package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.util.Enclosure;

public class GoteborgsPostenNewsReader extends NewsReader {

    public GoteborgsPostenNewsReader() {
        super(true);

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Nyheter", 0, "http://www.gp.se/1.970150"));
        SECTIONS.add(new Section("Hela nyhetsdygnet", 0, "http://www.gp.se/1.165654"));

        SECTIONS.add(new Section("Göteborg", 0, "http://www.gp.se/1.16942"));
        SECTIONS.add(new Section("Bohuslän", 0, "http://www.gp.se/1.215341"));
        SECTIONS.add(new Section("Halland", 0, "http://www.gp.se/1.291999"));
        SECTIONS.add(new Section("Sverige", 0, "http://www.gp.se/1.16943"));
        SECTIONS.add(new Section("Världen", 0, "http://www.gp.se/1.16944"));

        SECTIONS.add(new Section("Ledare", 0, "http://www.gp.se/1.872491"));
        SECTIONS.add(new Section("Debatt", 0, "http://www.gp.se/1.315001"));

        SECTIONS.add(new Section("Sport", 0, "http://www.gp.se/1.16542"));
        SECTIONS.add(new Section("Fotboll", 1, "http://www.gp.se/1.2185578"));
        SECTIONS.add(new Section("Handboll", 1, "http://www.gp.se/1.2185611"));
        SECTIONS.add(new Section("Ishockey", 1, "http://www.gp.se/1.2185476"));

        SECTIONS.add(new Section("Kultur & Nöje", 0, "http://www.gp.se/1.16941"));
        SECTIONS.add(new Section("Ekonomi", 0, "http://www.gp.se/1.16555"));
        SECTIONS.add(new Section("Konsument", 0, "http://www.gp.se/1.16558"));
        SECTIONS.add(new Section("Bostad", 0, "http://www.gp.se/1.16562"));
        SECTIONS.add(new Section("Resor", 0, "http://www.gp.se/1.16569"));
        SECTIONS.add(new Section("Motor", 0, "http://www.gp.se/1.16570"));
        SECTIONS.add(new Section("Mat & Dryck", 0, "http://www.gp.se/1.16571"));
        SECTIONS.add(new Section("Litteraturrecensioner", 0, "http://www.gp.se/1.4465"));

    }

    @Override
    protected News applySpecialCase(News news, String content) {
        if (news.description.length() > 500) {
            String img = "";
            for (Enclosure en : news.enclosures) {
                img += en.html();
            }
            news.content = img + news.description;
            news.description = org.jsoup.Jsoup.parseBodyFragment(news.description).text().substring(0, 100);
        } else {
            news.description = org.jsoup.Jsoup.parseBodyFragment(news.description).text();
        }

        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        org.jsoup.select.Elements body = doc.select(".body");

        if (!body.isEmpty()) {

            org.jsoup.select.Elements imgs = doc.select(".imageWrapper,.photoAlbumContainer,.articlePictures").select("img");

            String img = "";
            for (org.jsoup.nodes.Element i : imgs) {
                img += "<img src=\"http://www.gp.se/" + i.attr("src") + "\" />";
            }

            org.jsoup.select.Elements intro = doc.select(".factContainer,#articleContainer > iframe");
            for (org.jsoup.nodes.Element i : intro) {
                i.attr("style", "background-color: #f7f7f7");
            }

            news.content = img + intro.html() + body.html();
        }
    }

}
