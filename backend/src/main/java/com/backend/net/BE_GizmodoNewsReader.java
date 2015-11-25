package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_GizmodoNewsReader extends BE_NewsReader {

    public BE_GizmodoNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Main site", "http://feeds.gawker.com/gizmodo/full"));
        SECTIONS.add(new BE_Section("UK version", "http://feeds.feedburner.com/uk/gizmodo"));
        SECTIONS.add(new BE_Section("Spain version", "http://feeds.gawker.com/esgizmodo/full"));
        SECTIONS.add(new BE_Section("Australia version", "http://feeds.gizmodo.com.au/gizmodoaustralia"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text().replace(" Read more...", "");
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {

        org.jsoup.select.Elements e = doc.select(".entry-content");
        if (e.isEmpty()) {
            e = doc.select(".single-article__content");

            if (e.isEmpty()) {
                e = doc.select("#content_post");

                if (e.isEmpty()) {
                    return;
                }
            }
        } else {
            e.select(".recommended,.ad-mobile,.referenced-wide,[x-inset=\"hidden\"]").remove();

            for (org.jsoup.nodes.Element iframe : e.select(".core-inset")) {
                String id = iframe.attr("id");
                if (id.startsWith("youtube")) {
                    String src = iframe.attr("data-recommend-id").replace("youtube://", "https://www.youtube.com/embed/");
                    iframe.attr("src", src);
                } else if (id.startsWith("vimeo")) {
                    String src = iframe.attr("data-recommend-id").replace("vimeo://", "https://player.vimeo.com/video/");
                    iframe.attr("src", src);
                } else {
                    iframe.remove();
                }
            }
        }
        news.content = e.html();
    }

}
