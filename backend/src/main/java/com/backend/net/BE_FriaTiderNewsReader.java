package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_FriaTiderNewsReader extends BE_NewsReader {

    public BE_FriaTiderNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Nyheter", "http://www.friatider.se/rss.xml"));
        SECTIONS.add(new BE_Section("Politik", "http://www.friatider.se/taxonomy/term/38/feed"));
        SECTIONS.add(new BE_Section("Ekonomi", "http://www.friatider.se/taxonomy/term/2/feed"));
        SECTIONS.add(new BE_Section("Kultur", "http://www.friatider.se/taxonomy/term/3/feed"));
        SECTIONS.add(new BE_Section("Vetenskap", "http://www.friatider.se/taxonomy/term/19/feed"));
        SECTIONS.add(new BE_Section("Inrikes", "http://www.friatider.se/taxonomy/term/20/feed"));
        SECTIONS.add(new BE_Section("Utrikes", "http://www.friatider.se/taxonomy/term/21/feed"));
        SECTIONS.add(new BE_Section("Ledare", "http://www.friatider.se/taxonomy/term/31/feed"));
        SECTIONS.add(new BE_Section("Special: Sidebar top", "http://www.friatider.se/taxonomy/term/16/feed"));
        SECTIONS.add(new BE_Section("Media", "http://www.friatider.se/taxonomy/term/36/feed"));
        SECTIONS.add(new BE_Section("Du betalar", "http://www.friatider.se/taxonomy/term/34/feed"));

        SECTIONS.add(new BE_Section("Top first", "http://www.friatider.se/taxonomy/term/26/feed"));
        SECTIONS.add(new BE_Section("Top second", "http://www.friatider.se/taxonomy/term/27/feed"));

        SECTIONS.add(new BE_Section("Large", "http://www.friatider.se/taxonomy/term/4/feed"));
        SECTIONS.add(new BE_Section("Medium", "http://www.friatider.se/taxonomy/term/5/feed"));
        SECTIONS.add(new BE_Section("Normal", "http://www.friatider.se/taxonomy/term/7/feed"));
        SECTIONS.add(new BE_Section("Wide", "http://www.friatider.se/taxonomy/term/8/feed"));

    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
        org.jsoup.select.Elements e = doc.select(".field-items,.standfirst");
        e.select(".image-credit").remove();

        if (!e.isEmpty()) {
            news.content = e.html();
        }
    }

}
