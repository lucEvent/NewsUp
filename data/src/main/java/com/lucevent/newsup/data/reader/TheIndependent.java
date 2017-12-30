package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TheIndependent extends com.lucevent.newsup.data.util.NewsReader {

    //tags: [author, dc:creator, dc:date, description, guid, item, link, media:content, media:text, media:thumbnail, pubdate, title]

    public TheIndependent()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{TAG_MEDIA_CONTENT},
                "");
    }

    @Override
    protected void readNewsContent(Document doc, News news)
    {
        Elements article = doc.select("[itemprop='associatedMedia image'],[itemprop=articleBody]");
        article.select("script,.inline-block-related-single,.inline-pipes-list,.syndication-btn,#gigya-share-btns-2,.type-gallery,figure header,.relatedlinkslist,.type-video").remove();

        for (Element gallery : article.select(".featured-media:has(.full-gallery)")) {

            Elements imgs = gallery.select("[data-gallery-item]");
            Elements descs = gallery.select("[data-gallery-legend]");
            descs.select(".credits").remove();
            descs.select("h2").tagName("h4");

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < imgs.size(); i++)
                sb.append("<img src='").append(imgs.get(i).attr("data-original")).append("'> ")
                        .append(descs.get(i).html());

            gallery.html(sb.toString());
        }
        article.select(".dnd-caption-wrapper").tagName("figcaption");

        news.content = finalFormat(article, true);
    }

}
