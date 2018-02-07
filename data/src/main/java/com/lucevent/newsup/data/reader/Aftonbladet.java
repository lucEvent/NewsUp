package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Aftonbladet extends com.lucevent.newsup.data.util.NewsReader {

    // tags: category, description, guid, item, link, pubdate, title

    public Aftonbladet()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{},
                "");
    }

    @Override
    protected String parseLink(Element prop)
    {
        String link = super.parseLink(prop);

        if (link.startsWith("/"))
            link = "http://www.aftonbladet.se" + link;

        return link;
    }

    @Override
    protected News onNewsRead(News news)
    {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(news.description);
        news.description = doc.text();

        Elements imgs = doc.select("img");
        if (!imgs.isEmpty()) {
            news.enclosures = new Enclosures();
            news.enclosures.add(new Enclosure(imgs.get(0).attr("src"), "image/jpg", "0"));
        }

        if (news.link.contains("/abtv/")) {
            String[] p = news.link.split("/");
            String vidId = p[p.length - 1];
            news.content = insertIframe("https://tv.aftonbladet.se/abtv/articles/" + vidId + "?service=embedded&autoplay=false");
        }

        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news)
    {
        Elements article = doc.select("._10OUE");
        article.select("._11S-G,[id^='abAdArea'],._1pdIt,._7gDWL,._3DeZc,._2XS3K,.abThemeBorder,._1V3Dg,[data-test-id='inline-teaser']").remove();
        article.select("[data-reactid]").removeAttr("data-reactid");

        //
        article.select("._3oEVrB,._12nap,._3oEVr").tagName("h3");
        article.select("._1hqNA").tagName("figcaption");
        article.select("._3tWGC").tagName("blockquote");
        //
        for (Element e : article.select(".c-Cz1")) {
            cleanAttributes(e);
            e.html("<strong>" + e.html() + "</strong>");
        }

        // Gallery
        for (Element e : article.select("._1nGyY"))
            e.html(e.select("._2XM84,picture").outerHtml());
        // end gallery

        // Videos
        String vidScript = null;
        for (Element candidate : doc.select("script")) {
            String script = candidate.html();
            if (script.startsWith("window.FLUX_STATE")) {
                vidScript = script;
                break;
            }
        }
        if (vidScript != null) {
            for (Element e : article.select("._1W-u7,._2XM84")) {
                String vidId = e.select("[id^='meetricsId-']").get(0).id().replace("meetricsId-", "");
                if (!vidId.isEmpty()) {
                    int i = vidScript.indexOf("\"id\":\"" + vidId);
                    if (i > 0) {
                        String src = findSubstringBetween(vidScript.substring(i), "\"mp4\":\"", "\"", false);
                        if (src != null && !src.isEmpty()) {
                            src = src.replace("\\u002F", "/");
                            e.html(insertIframe(src));
                            continue;
                        }
                    }
                }
                e.html("");
            }
        } else
            article.select("._1W-u7").remove();
        // end videos

        news.content = finalFormat(article, false);
    }

}