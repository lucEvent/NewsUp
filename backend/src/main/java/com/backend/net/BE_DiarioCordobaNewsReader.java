package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

import java.io.IOException;
import java.net.URL;

public class BE_DiarioCordobaNewsReader extends BE_NewsReader {

    public BE_DiarioCordobaNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Portada", "http://zetaestaticos.com/cordoba/rss/portada_es.xml"));
        SECTIONS.add(new BE_Section("Última hora", "http://zetaestaticos.com/cordoba/rss/ultimahora_es.xml"));
        SECTIONS.add(new BE_Section("Andalucía", "http://zetaestaticos.com/cordoba/rss/1_es.xml"));
        SECTIONS.add(new BE_Section("España", "http://zetaestaticos.com/cordoba/rss/7_es.xml"));
        SECTIONS.add(new BE_Section("Internacional", "http://zetaestaticos.com/cordoba/rss/6_es.xml"));

        SECTIONS.add(new BE_Section("Deportes", "http://zetaestaticos.com/cordoba/rss/4_es.xml"));
        SECTIONS.add(new BE_Section("Área Blanquiverde", "http://zetaestaticos.com/cordoba/rss/8_es.xml"));
        SECTIONS.add(new BE_Section("Atletismo", "http://zetaestaticos.com/cordoba/rss/262_es.xml"));
        SECTIONS.add(new BE_Section("Baloncesto", "http://zetaestaticos.com/cordoba/rss/264_es.xml"));
        SECTIONS.add(new BE_Section("Balonmano", "http://zetaestaticos.com/cordoba/rss/263_es.xml"));
        SECTIONS.add(new BE_Section("Hípica", "http://zetaestaticos.com/cordoba/rss/242_es.xml"));
        SECTIONS.add(new BE_Section("Polideportivo", "http://zetaestaticos.com/cordoba/rss/265_es.xml"));

        SECTIONS.add(new BE_Section("Cultura", "http://zetaestaticos.com/cordoba/rss/3_es.xml"));
        SECTIONS.add(new BE_Section("Cofradías", "http://zetaestaticos.com/cordoba/rss/246_es.xml"));

        SECTIONS.add(new BE_Section("Economía", "http://zetaestaticos.com/cordoba/rss/5_es.xml"));

        SECTIONS.add(new BE_Section("Provincia", "http://zetaestaticos.com/cordoba/rss/102_es.xml"));
        SECTIONS.add(new BE_Section("Alto Guadalquivir", "http://zetaestaticos.com/cordoba/rss/233_es.xml"));
        SECTIONS.add(new BE_Section("Cabra", "http://zetaestaticos.com/cordoba/rss/234_es.xml"));
        SECTIONS.add(new BE_Section("Local", "http://zetaestaticos.com/cordoba/rss/101_es.xml"));
        SECTIONS.add(new BE_Section("Lucena", "http://zetaestaticos.com/cordoba/rss/235_es.xml"));
        SECTIONS.add(new BE_Section("Montilla", "http://zetaestaticos.com/cordoba/rss/236_es.xml"));
        SECTIONS.add(new BE_Section("Palma del Río", "http://zetaestaticos.com/cordoba/rss/237_es.xml"));
        SECTIONS.add(new BE_Section("Pozoblanco", "http://zetaestaticos.com/cordoba/rss/238_es.xml"));

        SECTIONS.add(new BE_Section("Gente", "http://zetaestaticos.com/cordoba/rss/204_es.xml"));
        SECTIONS.add(new BE_Section("Sociedad", "http://zetaestaticos.com/cordoba/rss/103_es.xml"));
        SECTIONS.add(new BE_Section("Televisión", "http://zetaestaticos.com/cordoba/rss/104_es.xml"));
        SECTIONS.add(new BE_Section("Turismo", "http://zetaestaticos.com/cordoba/rss/251_es.xml"));

        SECTIONS.add(new BE_Section("Tema del día", "http://zetaestaticos.com/cordoba/rss/106_es.xml"));
        SECTIONS.add(new BE_Section("Opinión", "http://zetaestaticos.com/cordoba/rss/100_es.xml"));
        SECTIONS.add(new BE_Section("Tu Informas", "http://zetaestaticos.com/cordoba/rss/221_es.xml"));

        SECTIONS.add(new BE_Section("La Brimz X en Córdoba", "http://zetaestaticos.com/cordoba/rss/256_es.xml"));
        SECTIONS.add(new BE_Section("UCOniversitas", "http://zetaestaticos.com/cordoba/rss/261_es.xml"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        news.title = news.title.replace("<![CDATA[", "").replace("]]>", "");
        news.description = news.description.replace("<p>", "").replace("</p>", "");
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {

        org.jsoup.select.Elements e = doc.select(".bxGaleriaNoticia img,#CuerpoDeLaNoticia");

        if (e.isEmpty()) {
            return;
        }

        news.content = e.outerHtml();
    }

    @Override
    protected org.jsoup.nodes.Document getDocument(String rsslink) {
        try {
            return org.jsoup.Jsoup.parse(new URL(rsslink).openStream(), "ISO-8859-1", rsslink);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.getDocument(rsslink);
    }

}