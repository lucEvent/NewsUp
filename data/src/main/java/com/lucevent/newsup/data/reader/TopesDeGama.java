package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TopesDeGama extends com.lucevent.newsup.data.util.NewsReader {

    //tags: [category, dc:creator, description, guid, item, link, post-id, pubdate, title]

    public TopesDeGama()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{},
                "https://topesdegama.com/",
                "");
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements article = doc.select("[itemprop='articleBody']");
        if (article.isEmpty() && doc.baseUri().contains("/analisis/")) {
            article = doc.select("[itemprop='reviewBody']");
            article.select(".widget").remove();

            article.select("[style]").removeAttr("style");

            Elements header = doc.select("header [itemprop='video']");
            if (!header.isEmpty()) {
                Element video = header.first();

                String src = video.select("[itemprop='embedURL']").attr("content");

                NewsStylist.cleanAttributes(video);
                video.tagName("div");
                video.html(Enclosure.iframe(src));

                article.add(0, video);
            }
        }
        article.select("script,style,.jetpack-slideshow-noscript,div:has(#div-desktop-article-filmstrip)").remove();

        article.select("h1,h2").tagName("h3");

        for (Element slides : article.select(".slideshow-window")) {
            String data = "{items:" + slides.attr("data-gallery") + "}";

            StringBuilder sb = new StringBuilder();
            try {
                JSONArray items = new JSONObject(data).optJSONArray("items");
                for (int i = 0; i < items.length(); i++) {
                    JSONObject item = items.getJSONObject(i);

                    sb.append("<p>");

                    String caption = item.getString("caption");
                    if (!caption.isEmpty())
                        sb.append("<center><b>").append(caption).append("</b></center>");

                    sb.append("<img src='").append(item.getString("src")).append("'></p>");
                }
            } catch (JSONException e) {
                //System.out.println("JSON exeption:" + e.getMessage());
            }
            slides.html(sb.toString());
            NewsStylist.cleanAttributes(slides);
        }
        for (Element slides : article.select(".gallery,.tiled-gallery")) {
            StringBuilder sb = new StringBuilder();
            for (Element img : slides.select("img"))
                sb.append("<img src='").append(img.attr("data-orig-file")).append("'>");

            slides.html(sb.toString());
            NewsStylist.cleanAttributes(slides);
        }
        for (Element ad : article.select("strong")) {
            String text = ad.text();
            if (text.startsWith("Te puede interes")
                    || text.startsWith("Te recomend")
                    || text.startsWith("También te puede inter")) {
                ad.parent().remove();
            }
        }
        NewsStylist.cleanAttributes(article.select("img"), "src");
        NewsStylist.repairLinks(article);
        article.select("[style]").removeAttr("style");

        news.content = article.html();
    }

    @Override
    protected Document getDocument(String pagelink)
    {
        try {
            return org.jsoup.Jsoup.connect(pagelink)
                    .timeout(10000)
                    .userAgent(USER_AGENT)
                    .get();
        } catch (Exception ignored) {
        }
        return null;
    }

}
