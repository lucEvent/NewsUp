package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class USAToday extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * tags:
     * [content:encoded, description, guid, item, link, pubdate, title]
     * [content:encoded, description, enclosure, feedburner:origlink, guid, item, link, pubdate, title]
     */

    public USAToday()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{TAG_ENCLOSURE});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        String dscr = Jsoup.parse(prop.text()).text();
        return dscr.substring(0, Math.min(300, dscr.length()));
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        String baseUri = doc.baseUri();

        if (baseUri.contains("/videos/")) {

            StringBuilder sb = new StringBuilder();
            Elements videos = doc.select(".video-player:has(script)");
            for (Element v : videos) {
                String script = v.select("script").first().html();

                String src = NewsStylist.subStringBetween(script, "previewurl\": \"", "\",", false);
                String dscr = NewsStylist.subStringBetween(script, "shortdescription\": \"", "\",", false);

                if (src != null) {
                    sb.append(NewsStylist.video(src));

                    if (dscr != null)
                        sb.append("<p>").append(dscr).append("</p>");
                }
            }
            news.content = sb.toString();
            return;
        } else if (baseUri.contains("/picture-gallery/")) {
            Element script = doc.select(".photos-container script").first();

            String data = script.html().replace("\\", "/");

            if (data.isEmpty())
                return;

            data = "{items:" + NewsStylist.subStringBetween(data, "[", "]", true) + "}";

            StringBuilder sb = new StringBuilder();
            try {
                JSONArray items = new JSONObject(data).optJSONArray("items");
                for (int i = 0; i < items.length() - 1; i++) {
                    JSONObject item = items.getJSONObject(i);

                    if (item.has("photo")) {
                        String imgsrc = item.getString("photo");
                        String text = item.getString("caption");

                        sb.append("<img src='").append(imgsrc).append("'>");
                        sb.append("<p>").append(text).append("</p>");
                    }
                }

            } catch (JSONException e) {
                //System.out.println("JSON exeption:" + e.getMessage());
            }
            news.content = sb.toString();
            return;
        } else if (baseUri.contains("reviewed.com")) {

            Elements article = doc.select("#video-preview,.heropic,[itemprop='articleBody'] .page_section");
            article.select(".callout,.credit,figcaption,.disclaimer,.brightcove").remove();
            article.select("h1,h2").tagName("h3");

            for (Element e : article.select("h3"))
                if (e.text().contains("Related Video"))
                    e.remove();

            news.content = article.html();
            return;
        }

        Elements article = doc.select(".video-player,.story-image,.body-text,.presto-h2,.presto-h3,.oembed-graphiq,.oembed-twitter,.oembed-youtube,.oembed-frame,.oembed-asset-photo-image,.interactive-map-iframe");

        if (article.isEmpty()) {

            article = doc.select(".articleBody");

            if (article.isEmpty()) {
                if (news.link.contains("newser.com")) {

                    article = doc.select("#ImageMain,#storyParagraphContainer .storyParagraph");

                    NewsStylist.cleanAttributes(article.select("img"), "src");
                } else {
                    // TODO: 19/01/2017
                    return;
                }
            } else {
                article.select("object").remove();
            }
        }

        for (Element v : article.select(".video-player:has(script)")) {
            String src = NewsStylist.subStringBetween(v.select("script").first().html(), "previewurl\": \"", "\",", false);

            if (src != null)
                v.html(NewsStylist.video(src));

            v.tagName("p");
            NewsStylist.cleanAttributes(v);
        }
        for (Element s : article.select("strong"))
            if (s.text().equals("Read more:"))
                s.html("");

        article.select("script").remove();
        article.select("[width]").removeAttr("width");
        article.select("h1,h2").tagName("h3");

        news.content = article.outerHtml();
    }

    @Override
    protected Document getDocument(String pagelink)
    {
        try {
            return org.jsoup.Jsoup.connect(pagelink)
                    .timeout(10000)
                    .userAgent(USER_AGENT)
                    .get();
        } catch (Exception e) {

            try {
                return org.jsoup.Jsoup.connect(pagelink).userAgent(USER_AGENT).get();
            } catch (Exception ignored) {
            }
        }
        return null;
    }

}
