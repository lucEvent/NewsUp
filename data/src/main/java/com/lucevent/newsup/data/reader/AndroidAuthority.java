package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class AndroidAuthority extends com.lucevent.newsup.data.util.NewsReader {

    public AndroidAuthority() {
        super();
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(content);
        doc.select(".aa_button_wrapper,.aa_see_also_block,.cbc-latest-videos").remove();

        news.content = doc.html();
        return news;
    }

}
