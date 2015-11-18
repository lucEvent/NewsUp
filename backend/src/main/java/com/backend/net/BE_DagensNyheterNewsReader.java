package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_DagensNyheterNewsReader extends BE_NewsReader {

    public BE_DagensNyheterNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Senaste nytt", "http://www.dn.se/rss/senaste-nytt/"));
        SECTIONS.add(new BE_Section("Nyheter", "http://www.dn.se/nyheter/rss/"));
        SECTIONS.add(new BE_Section("Goda Nyheter", "http://www.dn.se/goda-nyheter/goda-nyheter-hem/rss"));

        SECTIONS.add(new BE_Section("Stockholm", "http://www.dn.se/sthlm/rss/"));
        SECTIONS.add(new BE_Section("Sverige", "http://www.dn.se/nyheter/sverige/rss"));
        SECTIONS.add(new BE_Section("Världen", "http://www.dn.se/nyheter/varlden/rss/"));

        SECTIONS.add(new BE_Section("Ekonomi", "http://www.dn.se/ekonomi/rss/"));
        SECTIONS.add(new BE_Section("Politik", "http://www.dn.se/nyheter/politik/rss"));
        SECTIONS.add(new BE_Section("Vetenskap", "http://www.dn.se/nyheter/vetenskap/rss"));
        SECTIONS.add(new BE_Section("Motor", "http://www.dn.se/motor/rss"));

        SECTIONS.add(new BE_Section("Sport", "http://www.dn.se/sport/rss/"));
        SECTIONS.add(new BE_Section("Stories", "http://www.dn.se/stories/stories-sport/rss"));
        SECTIONS.add(new BE_Section("Ishockey", "http://www.dn.se/sport/ishockey/rss"));
        SECTIONS.add(new BE_Section("Fotboll", "http://www.dn.se/sport/fotboll/rss"));
        SECTIONS.add(new BE_Section("Målservice", "http://www.dn.se/sport/malservice/rss"));
        SECTIONS.add(new BE_Section("Slutresultat", "http://www.dn.se/sport/slutresultat/rss"));
        SECTIONS.add(new BE_Section("Engelska ligan", "http://www.dn.se/sport/engelska-ligan/rss"));

        SECTIONS.add(new BE_Section("Kultur", "http://www.dn.se/kultur-noje/rss/"));
        SECTIONS.add(new BE_Section("Bok", "http://www.dn.se/dnbok/rss"));
        SECTIONS.add(new BE_Section("Kulturdebatt", "http://www.dn.se/kultur-noje/kulturdebatt/rss"));
        SECTIONS.add(new BE_Section("Film - TV", "http://www.dn.se/kultur-noje/film-tv/rss"));
        SECTIONS.add(new BE_Section("Musik", "http://www.dn.se/kultur-noje/musik/rss"));
        SECTIONS.add(new BE_Section("Scen", "http://www.dn.se/kultur-noje/scen/rss"));
        SECTIONS.add(new BE_Section("Spel", "http://www.dn.se/spel/spel-hem/rss"));

        SECTIONS.add(new BE_Section("Val", "http://www.dn.se/val/rss"));

        SECTIONS.add(new BE_Section("Frågesport", "http://www.dn.se/nyheter/fragesport/rss"));
        SECTIONS.add(new BE_Section("Åsikt", "http://asikt.dn.se/feed/"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
        org.jsoup.select.Elements e = doc.select(".article_preamble,.article_text");

        if (e.isEmpty()) {
            e = doc.select(".m-webtv-preview-container,.m-webtv-preamble");

            if (e.isEmpty()) {
                e = doc.select(".content > .excerpt,.content > p");

                if (e.isEmpty()) return;

            } else {
                org.jsoup.select.Elements imgs = doc.select("img");
                for (org.jsoup.nodes.Element img : imgs) {
                    String src = "http://www.dn.se" + img.attr("src");
                    img.attr("src", src);
                }
            }
        }
        news.content = e.outerHtml();
    }

}
