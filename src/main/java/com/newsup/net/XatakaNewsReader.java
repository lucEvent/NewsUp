package com.newsup.net;

import android.content.Context;
import android.os.Handler;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.kernel.list.Tags;

public class XatakaNewsReader extends NewsReader {

    public XatakaNewsReader(Handler handler, Context context) {
        super(handler, context);

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
    protected News getNewsLastFilter(String title, String link, String description, String date, Tags categories) {
        org.jsoup.select.Elements ee = org.jsoup.Jsoup.parseBodyFragment(description).select("body").get(0).children();

        StringBuilder content = new StringBuilder();

        org.jsoup.nodes.Element last = ee.select("h4").get(0);
        for (org.jsoup.nodes.Element elem : ee) {
            if (elem == last) {
                break;
            }
            content.append(elem.outerHtml());
        }

        News res = new News(title, link, "", date, categories);
        res.content = content.toString();
        return res;
    }

    @Override
    public News readNewsContent(News news) {
        return news;
    }

    protected void debug(String text) {
        android.util.Log.d("##XatakaNewsReader##", text);
    }

}
