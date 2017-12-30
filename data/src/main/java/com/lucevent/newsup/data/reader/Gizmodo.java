package com.lucevent.newsup.data.reader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Element;

public class Gizmodo extends com.lucevent.newsup.data.util.NewsReader {

    // tags: [category, dc:creator, description, guid, item, link, pubdate, title]

    public Gizmodo()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{},
                "");
    }

    @Override
    protected String parseContent(Element prop)
    {
        Element article = jsoupParse(prop);
        article.select(".ad-container,.js_ad-dynamic").remove();

        article.select(".pullquote").tagName("blockquote");

        for (Element e : article.select("figure.js_marquee-assetfigure:has(picture)")) {
            e.removeAttr("style");
            e.html(e.select("picture,figcaption").outerHtml());
        }

        for (Element slides : article.select(".slideshow-inset [data-slides]")) {
            String data = slides.attr("data-slides");
            if (data.isEmpty())
                continue;

            StringBuilder sb = new StringBuilder();
            try {
                JSONArray items = new JSONObject("{items:" + data + "}").optJSONArray("items");
                for (int i = 0; i < items.length(); i++) {
                    JSONObject item = items.getJSONObject(i);

                    String imgsrc = "https://i.kinja-img.com/gawker-media/image/upload/" + item.getString("id") + "." + item.getString("format");
                    String caption = item.getJSONArray("caption").getJSONObject(0).getString("value");

                    sb.append("<img src='").append(imgsrc).append("'>");
                    sb.append("<figcaption>").append(caption).append("</figcaption>");
                }

            } catch (JSONException e) {
                //   System.out.println("JSON exception:" + e.getMessage());
            }
            slides.parent().html(sb.toString());
        }

        cleanAttributes(article.select("img"), "src");

        return finalFormat(article, false);
    }

}
