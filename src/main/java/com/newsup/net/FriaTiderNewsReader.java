package com.newsup.net;


import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class FriaTiderNewsReader extends NewsReader {

    public FriaTiderNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Nyheter", 0, "http://www.friatider.se/rss.xml"));
        SECTIONS.add(new Section("Politik", 0, "http://www.friatider.se/taxonomy/term/38/feed"));
        SECTIONS.add(new Section("Ekonomi", 0, "http://www.friatider.se/taxonomy/term/2/feed"));
        SECTIONS.add(new Section("Kultur", 0, "http://www.friatider.se/taxonomy/term/3/feed"));
        SECTIONS.add(new Section("Vetenskap", 0, "http://www.friatider.se/taxonomy/term/19/feed"));
        SECTIONS.add(new Section("Inrikes", 0, "http://www.friatider.se/taxonomy/term/20/feed"));
        SECTIONS.add(new Section("Utrikes", 0, "http://www.friatider.se/taxonomy/term/21/feed"));
        SECTIONS.add(new Section("Ledare", 0, "http://www.friatider.se/taxonomy/term/31/feed"));
        SECTIONS.add(new Section("Special: Sidebar top", 0, "http://www.friatider.se/taxonomy/term/16/feed"));
        SECTIONS.add(new Section("Media", 0, "http://www.friatider.se/taxonomy/term/36/feed"));
        SECTIONS.add(new Section("Du betalar", 0, "http://www.friatider.se/taxonomy/term/34/feed"));

        SECTIONS.add(new Section("Top first", 0, "http://www.friatider.se/taxonomy/term/26/feed"));
        SECTIONS.add(new Section("Top second", 0, "http://www.friatider.se/taxonomy/term/27/feed"));

        SECTIONS.add(new Section("Large", 0, "http://www.friatider.se/taxonomy/term/4/feed"));
        SECTIONS.add(new Section("Medium", 0, "http://www.friatider.se/taxonomy/term/5/feed"));
        SECTIONS.add(new Section("Normal", 0, "http://www.friatider.se/taxonomy/term/7/feed"));
        SECTIONS.add(new Section("Wide", 0, "http://www.friatider.se/taxonomy/term/8/feed"));

    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        org.jsoup.select.Elements e = doc.select(".field-items,.standfirst");
        e.select(".image-credit").remove();

        if (!e.isEmpty()) {
            news.content = e.html();
        }
    }

}
