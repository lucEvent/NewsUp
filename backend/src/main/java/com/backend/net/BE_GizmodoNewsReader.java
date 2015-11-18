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
        org.jsoup.nodes.Element e = doc.select(".post-content,.single-article__content,#content_post").get(0);

        e.select("p[data-textannotation-id=\"6eee416cdd18ed05dcc366f5a5757226\"]").remove();
        for (org.jsoup.nodes.Element elem : e.children()) {
            for (org.jsoup.nodes.Attribute at : elem.attributes()) {
                elem.removeAttr(at.getKey());
            }
        }
        news.content = e.outerHtml();
    }

}
