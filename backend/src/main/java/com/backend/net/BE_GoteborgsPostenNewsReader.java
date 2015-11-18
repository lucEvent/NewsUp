package com.backend.net;

import com.backend.kernel.BE_Enclosure;
import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_GoteborgsPostenNewsReader extends BE_NewsReader {

    public BE_GoteborgsPostenNewsReader() {
        super(true);

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Nyheter", "http://www.gp.se/1.970150"));
        SECTIONS.add(new BE_Section("Hela nyhetsdygnet", "http://www.gp.se/1.165654"));

        SECTIONS.add(new BE_Section("Göteborg", "http://www.gp.se/1.16942"));
        SECTIONS.add(new BE_Section("Bohuslän", "http://www.gp.se/1.215341"));
        SECTIONS.add(new BE_Section("Halland", "http://www.gp.se/1.291999"));
        SECTIONS.add(new BE_Section("Sverige", "http://www.gp.se/1.16943"));
        SECTIONS.add(new BE_Section("Världen", "http://www.gp.se/1.16944"));

        SECTIONS.add(new BE_Section("Ledare", "http://www.gp.se/1.872491"));
        SECTIONS.add(new BE_Section("Debatt", "http://www.gp.se/1.315001"));

        SECTIONS.add(new BE_Section("Sport", "http://www.gp.se/1.16542"));
        SECTIONS.add(new BE_Section("Fotboll", "http://www.gp.se/1.2185578"));
        SECTIONS.add(new BE_Section("Handboll", "http://www.gp.se/1.2185611"));
        SECTIONS.add(new BE_Section("Ishockey", "http://www.gp.se/1.2185476"));

        SECTIONS.add(new BE_Section("Kultur & Nöje", "http://www.gp.se/1.16941"));
        SECTIONS.add(new BE_Section("Ekonomi", "http://www.gp.se/1.16555"));
        SECTIONS.add(new BE_Section("Konsument", "http://www.gp.se/1.16558"));
        SECTIONS.add(new BE_Section("Bostad", "http://www.gp.se/1.16562"));
        SECTIONS.add(new BE_Section("Resor", "http://www.gp.se/1.16569"));
        SECTIONS.add(new BE_Section("Motor", "http://www.gp.se/1.16570"));
        SECTIONS.add(new BE_Section("Mat & Dryck", "http://www.gp.se/1.16571"));
        SECTIONS.add(new BE_Section("Litteraturrecensioner", "http://www.gp.se/1.4465"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        if (news.description.length() > 500) {
            String img = "";
            for (BE_Enclosure en : news.enclosures) {
                img += en.html();
            }
            news.content = img + news.description;
            news.description = org.jsoup.Jsoup.parseBodyFragment(news.description).text().substring(000);
        } else {
            news.description = org.jsoup.Jsoup.parseBodyFragment(news.description).text();
        }

        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
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
