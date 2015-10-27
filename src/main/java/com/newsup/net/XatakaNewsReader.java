package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class XatakaNewsReader extends NewsReader {

    public XatakaNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Principal", 0, "http://www.xataka.com/index.xml"));
        SECTIONS.add(new Section("Móvil", 0, "http://www.xatakamovil.com/index.xml"));
        SECTIONS.add(new Section("Fotografía", 0, "http://www.xatakafoto.com/index.xml"));
        SECTIONS.add(new Section("Android", 0, "http://www.xatakandroid.com/index.xml"));
        SECTIONS.add(new Section("Smart home", 0, "http://www.xatakahome.com/index.xml"));
        SECTIONS.add(new Section("Ciencia", 0, "http://www.xatakaciencia.com/index.xml"));
        SECTIONS.add(new Section("Windows", 0, "http://www.xatakawindows.com/index.xml"));
        SECTIONS.add(new Section("Videojuegos", 0, "http://www.vidaextra.com/index.xml"));
        SECTIONS.add(new Section("AppleSfera", 0, "http://www.applesfera.com/index.xml"));
        SECTIONS.add(new Section("GenBeta", 0, "http://www.genbeta.com/index.xml"));
        SECTIONS.add(new Section("Developer", 0, "http://www.genbetadev.com/index.xml"));
        SECTIONS.add(new Section("Magnet", 0, "http://magnet.xataka.com/index.xml"));
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        org.jsoup.select.Elements ee = org.jsoup.Jsoup.parse(news.description).select("body").get(0).children();

        org.jsoup.select.Elements elasts = ee.select("h4,br[clear=\"all\"]");
        if (!elasts.isEmpty()) {
            StringBuilder sb = new StringBuilder();

            org.jsoup.nodes.Element last = elasts.get(0);
            for (org.jsoup.nodes.Element elem : ee) {
                if (elem == last) {
                    break;
                }
                sb.append(elem.outerHtml());
            }
            news.content = content.toString();
        } else {
            news.content = ee.html();
        }
        news.description = "";
        return news;
    }

    @Override
    public News readNewsContent(News news) {
        return news;
    }

}
