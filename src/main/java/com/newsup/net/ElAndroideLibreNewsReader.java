package com.newsup.net;


import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

import org.jsoup.Jsoup;

public class ElAndroideLibreNewsReader extends NewsReader {

    public ElAndroideLibreNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Principal", 0, "http://feeds.feedburner.com/elandroidelibre"));

    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = Jsoup.parse(news.description).getElementsByTag("p").get(0).text().replace("[...]", "");
        return news;
    }

    @Override
    public News readNewsContent(News news) {
        try {
            org.jsoup.nodes.Document doc = getDocument(news.link);
            if (doc == null) return news;

            news.content = doc.select("#singlePostContent").get(0).html();

        } catch (Exception e) {
            debug("[ERROR La seleccion del articulo no se ha encontrado] tit:" + news.title);
            e.printStackTrace();
        }
        return news;
    }

}
