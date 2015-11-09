package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class AndroidAuthorityNewsReader extends NewsReader {

    public AndroidAuthorityNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Lasts", 0, "http://feed.androidauthority.com/"));
        SECTIONS.add(new Section("News", 0, "http://www.androidauthority.com/news/feed/"));

        SECTIONS.add(new Section("Reviews", 0, "http://www.androidauthority.com/reviews/feed/"));
        SECTIONS.add(new Section("Apps", 1, "http://www.androidauthority.com/apps/app-reviews/feed/"));
        SECTIONS.add(new Section("Phones", 1, "http://www.androidauthority.com/reviews/phones/feed/"));

        SECTIONS.add(new Section("Apps", 0, "http://www.androidauthority.com/apps/feed/"));
        SECTIONS.add(new Section("Lists", 1, "http://www.androidauthority.com/apps/app-lists/feed/"));
        SECTIONS.add(new Section("Games", 1, "http://www.androidauthority.com/apps/games/feed/"));

        SECTIONS.add(new Section("Features", 0, "http://www.androidauthority.com/features/feed/"));
        SECTIONS.add(new Section("Opinions", 0, "http://www.androidauthority.com/features/opinions/feed/"));
        SECTIONS.add(new Section("Podcast", 0, "http://www.androidauthority.com/podcast/feed/"));
        SECTIONS.add(new Section("Tips", 0, "http://www.androidauthority.com/tips/feed/"));
        SECTIONS.add(new Section("Giveaways", 0, "http://www.androidauthority.com/giveaways/feed/"));

    }

    @Override
    protected News applySpecialCase(News news, String content) {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(content);
        doc.select(".aa_button_wrapper,.aa_see_also_block,.cbc-latest-videos").remove();

        news.content = doc.html();
        return news;
    }

}
