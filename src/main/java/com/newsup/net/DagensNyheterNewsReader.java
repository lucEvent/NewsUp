package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class DagensNyheterNewsReader extends NewsReader {

    public DagensNyheterNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Senaste nytt", 0, "http://www.dn.se/rss/senaste-nytt/"));
        SECTIONS.add(new Section("Nyheter", 0, "http://www.dn.se/nyheter/rss/"));
        SECTIONS.add(new Section("Goda Nyheter", 0, "http://www.dn.se/goda-nyheter/goda-nyheter-hem/rss"));

        SECTIONS.add(new Section("Stockholm", 0, "http://www.dn.se/sthlm/rss/"));
        SECTIONS.add(new Section("Sverige", 0, "http://www.dn.se/nyheter/sverige/rss"));
        SECTIONS.add(new Section("Världen", 0, "http://www.dn.se/nyheter/varlden/rss/"));

        SECTIONS.add(new Section("Ekonomi", 0, "http://www.dn.se/ekonomi/rss/"));
        SECTIONS.add(new Section("Politik", 0, "http://www.dn.se/nyheter/politik/rss"));
        SECTIONS.add(new Section("Vetenskap", 0, "http://www.dn.se/nyheter/vetenskap/rss"));
        SECTIONS.add(new Section("Motor", 0, "http://www.dn.se/motor/rss"));

        SECTIONS.add(new Section("Sport", 0, "http://www.dn.se/sport/rss/"));
        SECTIONS.add(new Section("Stories", 1, "http://www.dn.se/stories/stories-sport/rss"));
        SECTIONS.add(new Section("Ishockey", 1, "http://www.dn.se/sport/ishockey/rss"));
        SECTIONS.add(new Section("Fotboll", 1, "http://www.dn.se/sport/fotboll/rss"));
        SECTIONS.add(new Section("Målservice", 1, "http://www.dn.se/sport/malservice/rss"));
        SECTIONS.add(new Section("Slutresultat", 1, "http://www.dn.se/sport/slutresultat/rss"));
        SECTIONS.add(new Section("Engelska ligan", 1, "http://www.dn.se/sport/engelska-ligan/rss"));

        SECTIONS.add(new Section("Kultur", 0, "http://www.dn.se/kultur-noje/rss/"));
        SECTIONS.add(new Section("Bok", 1, "http://www.dn.se/dnbok/rss"));
        SECTIONS.add(new Section("Kulturdebatt", 1, "http://www.dn.se/kultur-noje/kulturdebatt/rss"));
        SECTIONS.add(new Section("Film - TV", 1, "http://www.dn.se/kultur-noje/film-tv/rss"));
        SECTIONS.add(new Section("Musik", 1, "http://www.dn.se/kultur-noje/musik/rss"));
        SECTIONS.add(new Section("Scen", 1, "http://www.dn.se/kultur-noje/scen/rss"));
        SECTIONS.add(new Section("Spel", 1, "http://www.dn.se/spel/spel-hem/rss"));

        SECTIONS.add(new Section("Val", 0, "http://www.dn.se/val/rss"));

        SECTIONS.add(new Section("Frågesport", 0, "http://www.dn.se/nyheter/fragesport/rss"));
        SECTIONS.add(new Section("Åsikt", 0, "http://asikt.dn.se/feed/"));

    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text();
        return news;
    }

    @Override
    public News readNewsContent(News news) {
        org.jsoup.nodes.Document doc = getDocument(news.link);
        if (doc == null) return news;

        org.jsoup.select.Elements e = doc.select(".article_preamble,.article_text");

        if (e.isEmpty()) {
            e = doc.select(".m-webtv-preview-container,.m-webtv-preamble");

            if (e.isEmpty()) {
                e = doc.select(".content > .excerpt,.content > p");

                if (e.isEmpty()) {
                    debug("Noticia sin contenido??: " + news.link);
                    return news;
                }
            } else {
                org.jsoup.select.Elements imgs = doc.select("img");
                for (org.jsoup.nodes.Element img : imgs) {
                    String src = "http://www.dn.se" + img.attr("src");
                    img.attr("src", src);
                }
            }
        }
        news.content = e.outerHtml();

        return news;
    }

}
