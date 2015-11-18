package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_XatakaNewsReader extends BE_NewsReader {

    public BE_XatakaNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Principal", "http://www.xataka.com/index.xml"));
        SECTIONS.add(new BE_Section("Móvil", "http://www.xatakamovil.com/index.xml"));
        SECTIONS.add(new BE_Section("Fotografía", "http://www.xatakafoto.com/index.xml"));
        SECTIONS.add(new BE_Section("Android", "http://www.xatakandroid.com/index.xml"));
        SECTIONS.add(new BE_Section("Smart home", "http://www.xatakahome.com/index.xml"));
        SECTIONS.add(new BE_Section("Ciencia", "http://www.xatakaciencia.com/index.xml"));
        SECTIONS.add(new BE_Section("Windows", "http://www.xatakawindows.com/index.xml"));
        SECTIONS.add(new BE_Section("Videojuegos", "http://www.vidaextra.com/index.xml"));
        SECTIONS.add(new BE_Section("AppleSfera", "http://www.applesfera.com/index.xml"));
        SECTIONS.add(new BE_Section("GenBeta", "http://www.genbeta.com/index.xml"));
        SECTIONS.add(new BE_Section("Developer", "http://www.genbetadev.com/index.xml"));
        SECTIONS.add(new BE_Section("Magnet", "http://magnet.xataka.com/index.xml"));
    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parseBodyFragment(news.description);

        doc.select("h4 ~ *,h4,[clear=\"all\"] ~ *").remove();
        news.content = doc.body().html();
        news.description = "";
        return news;
    }

}
