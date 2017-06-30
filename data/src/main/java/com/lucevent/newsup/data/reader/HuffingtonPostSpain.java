package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HuffingtonPostSpain extends com.lucevent.newsup.data.util.NewsReader {

    /**
     * Tags:
     * [author, description, enclosure, guid, item, link, pubdate, title]
     * [author, content, entry, id, link, name, published, title, updated, uri]
     */

    public HuffingtonPostSpain()
    {
        super(TAG_ITEM_ITEMS_ENTRY,
                new int[]{TAG_TITLE},
                new int[]{TAG_ID, TAG_GUID},
                new int[]{TAG_CONTENT},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_PUBDATE, TAG_UPDATED},
                new int[]{TAG_CATEGORY},
                new int[]{TAG_ENCLOSURE, TAG_LINK});
    }

    @Override
    protected String parseDescription(Element prop)
    {
        String description = org.jsoup.Jsoup.parse(prop.text()).text();
        int index = description.indexOf("Leer m\u00e1s:");
        if (index != -1)
            description = description.substring(0, index);
        return description;
    }

    @Override
    protected String parseContent(Element prop)
    {
        Element body = org.jsoup.Jsoup.parse(prop.text().replace("<br />", "<p></p>")).body();
        body.select("script").remove();

        body.select("h1,h2").tagName("h3");

        Elements ee = body.children();
        int index = ee.indexOf(ee.select("blockquote").last()) - 1;
        if (index >= 0)
            for (; index < ee.size(); ++index)
                ee.get(index).remove();

        cleanBlockquotes(body.select("blockquote"));

        for (Element e : body.select("[src*='big.assets.h'],[src*='gen/2966946']"))
            try {
                e.parent().remove();
            } catch (Exception ignored) {
            }

        NewsStylist.repairLinks(body);

        String content = body.outerHtml();
        index = content.indexOf("<hh--");
        if (index != -1)
            content = content.substring(0, index);

        return content;
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements article = doc.select(".entry__body > div.content-list-component,.top-media--image,.top-media--video,.twitter-tweet,.twitter-video,.pull-quote,.entry__body .listicle");

        for (Element e : article.select("[src*='big.assets.h'],[src*='gen/2966946']"))
            try {
                e.parent().remove();
            } catch (Exception ignored) {
            }

        for (Element script : article.select("script")) {
            String html = script.html();
            if (html.isEmpty())
                script.remove();
            else script.parent().html(html);
        }

        article.select("[class]:not(blockquote)").removeAttr("class");
        article.select("li").tagName("p");
        article.select("h1,h2").tagName("h3");

        NewsStylist.repairLinks(article);

        news.content = article.outerHtml();
    }

    @Override
    protected Enclosure parseEnclosure(Element prop)
    {
        String type = prop.attr("type");
        if (type.startsWith("image")) {

            String src;
            if (prop.attr("rel").isEmpty()) {
                src = prop.attr("url").replace("-mini", "-large300");
            } else {
                src = prop.attr("href").replace("-154x114", "-large300");
            }
            return new Enclosure(src, type, "");
        }
        return null;
    }

    private void cleanBlockquotes(Elements select)
    {
        for (Element bq : select) {
            String text = bq.text();

            if (text.contains("ADEM\u00C1S") || text.contains("TE PUEDE INTERESAR")
                    || text.contains("AV\u00CDSANOS") || text.contains("M\u00C1S SOBRE")
                    || text.contains("M\u00C1S PARA"))
                bq.remove();
        }
    }

}