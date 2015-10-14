package com.newsup.net;

import android.content.Context;
import android.os.Handler;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.kernel.list.Tags;

public class GizmodoNewsReader extends NewsReader {

    public GizmodoNewsReader(Handler handler, Context context) {
        super(handler, context);

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Main site", 0, "http://feeds.gawker.com/gizmodo/full"));
        SECTIONS.add(new Section("UK version", 0, "http://feeds.feedburner.com/uk/gizmodo"));
        SECTIONS.add(new Section("Spain version", 0, "http://feeds.gawker.com/esgizmodo/full"));
        SECTIONS.add(new Section("Australia version", 0, "http://feeds.gizmodo.com.au/gizmodoaustralia"));

    }

    @Override
    protected News getNewsLastFilter(String title, String link, String description, String date, Tags categories) {
        description = org.jsoup.Jsoup.parse(description).text().replace(" Read more...", "");
        return new News(title, link, description, date, categories);
    }

    @Override
    public News readNewsContent(News news) {
        try {
            org.jsoup.nodes.Document doc = getDocument(news.link);
            if (doc == null) return news;

            org.jsoup.nodes.Element e = doc.select(".post-content,.single-article__content,#content_post").get(0);

            e.select("p[data-textannotation-id=\"6eee416cdd18ed05dcc366f5a5757226\"]").remove();
            for (org.jsoup.nodes.Element elem : e.children()) {
                for (org.jsoup.nodes.Attribute at : elem.attributes()) {
                    elem.removeAttr(at.getKey());
                }
            }
            news.content = e.outerHtml();
        } catch (Exception e) {
            debug("[ERROR] link:" + news.link);
            e.printStackTrace();
        }
        return news;
    }

}
