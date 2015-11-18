package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_TheLocalNewsReader extends BE_NewsReader {

    public BE_TheLocalNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Austria", "http://www.thelocal.at/feeds/rss.php"));
        SECTIONS.add(new BE_Section("Denmark", "http://www.thelocal.dk/feeds/rss.php"));
        SECTIONS.add(new BE_Section("France", "http://www.thelocal.fr/feeds/rss.php"));
        SECTIONS.add(new BE_Section("Germany", "http://www.thelocal.de/feeds/rss.php"));
        SECTIONS.add(new BE_Section("Italy", "http://www.thelocal.it/feeds/rss.php"));
        SECTIONS.add(new BE_Section("Norway", "http://www.thelocal.no/feeds/rss.php"));
        SECTIONS.add(new BE_Section("Spain", "http://www.thelocal.es/feeds/rss.php"));
        SECTIONS.add(new BE_Section("Sweden", "http://www.thelocal.se/feeds/rss.php"));
        SECTIONS.add(new BE_Section("Switzerland", "http://www.thelocal.ch/feeds/rss.php"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        news.date += (-2 * 3600000);
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
        org.jsoup.select.Elements content = doc.select("#main_picture_article > img,.articleTeaser,.articleContent");

        org.jsoup.select.Elements imgs = content.select("img");
        for (org.jsoup.nodes.Element img : imgs) {
            String src = "http://www.thelocal.com" + img.attr("src");
            img.attr("src", src);
            img.attr("style", "");
        }

        news.content = content.outerHtml();
    }

}
