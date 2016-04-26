package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

public class DagensNyheter extends com.lucevent.newsup.data.util.NewsReader_v2 {

    /**
     * Tags
     * [dc:date, description, guid, item, link, pubdate, title]
     * [dc:creator, description, guid, item, link, pubdate, title, ]
     */

    public DagensNyheter()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{});
    }

    @Override
    protected String parseDescription(org.jsoup.nodes.Element prop)
    {
        return org.jsoup.Jsoup.parse(prop.text()).text();
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        org.jsoup.select.Elements e = doc.select(".article_preamble,.article_text");

        if (e.isEmpty()) {
            e = doc.select(".m-webtv-preview-container,.m-webtv-preamble");

            if (e.isEmpty()) {
                e = doc.select(".content > .excerpt,.content > p");

                if (e.isEmpty()) return;

            } else {
                org.jsoup.select.Elements imgs = doc.select("img");
                for (org.jsoup.nodes.Element img : imgs) {
                    String src = "http://www.dn.se" + img.attr("src");
                    img.attr("src", src);
                }
            }
        }
        news.content = e.outerHtml();
    }

}
