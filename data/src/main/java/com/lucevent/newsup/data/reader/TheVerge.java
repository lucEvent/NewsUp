package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TheVerge extends com.lucevent.newsup.data.util.NewsReader {

    // tags: [author, content, entry, id, link, name, published, title, updated]

    public TheVerge()
    {
        super(TAG_ITEM_ENTRY,
                new int[]{TAG_TITLE},
                new int[]{TAG_ID},
                new int[]{},
                new int[]{TAG_CONTENT},
                new int[]{TAG_UPDATED},
                new int[]{},
                new int[]{TAG_LINK});
    }

    @Override
    protected Enclosure parseEnclosure(Element prop)
    {
        return new Enclosure(prop.attr("href"), prop.attr("type"), "");
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document d = Jsoup.parse(prop.text());
        d.select("h1,h2").tagName("h3");

        Elements article = d.select(".m-snippet");
        if (article.size() < 2) {
            d.select("hr").remove();
            return d.html().replace("src=\"/", "src=\"http:/");
        }
        article.select(".lede").remove();
        return article.outerHtml().replace("src=\"/", "src=\"http:/");
    }

    @Override
    protected News applySpecialCase(News news, String content)
    {
        if (news.link.contains("theverge.com/video")) {
            Document d = Jsoup.parse(news.content);
            d.select("iframe").remove();

            news.content = d.html();

            Elements v = d.select(".volume-video");
            if (v.isEmpty()) {
                for (Enclosure e : news.enclosures) {
                    if (e.isVideo()) {

                        String url = e.src;
                        int index = url.indexOf("?url=");
                        if (index != -1)
                            url = url.substring(index + 5, url.length());

                        news.content += "<video controls><source src=\"" + url + "\" type=\"video/mp4\"></video>" +
                                "<a href='" + url + "'>See externally</a>";

                    }
                }
            } else {
                String video_id = v.first().attr("data-volume-uuid");
                news.content += new Enclosure("https://volume.vox-cdn.com/embed/" + video_id, "video", "").html() +
                        "<a href='https://volume.vox-cdn.com/embed/" + video_id + "'>See externally</a>";
                v.remove();
            }
        }
        return news;
    }

}
