package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_ElMundoNewsReader extends BE_NewsReader {

    public BE_ElMundoNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Portada", "http://estaticos.elmundo.es/elmundo/rss/portada.xml"));
        SECTIONS.add(new BE_Section("España", "http://estaticos.elmundo.es/elmundo/rss/espana.xml"));
        SECTIONS.add(new BE_Section("Internacional", "http://estaticos.elmundo.es/elmundo/rss/internacional.xml"));
        SECTIONS.add(new BE_Section("Economía", "http://estaticos.elmundo.es/elmundo/rss/economia.xml"));
        SECTIONS.add(new BE_Section("Cultura", "http://estaticos.elmundo.es/elmundo/rss/cultura.xml"));
        SECTIONS.add(new BE_Section("Ciencia", "http://estaticos.elmundo.es/elmundo/rss/ciencia.xml"));

        SECTIONS.add(new BE_Section("Ciudades", null));
        SECTIONS.add(new BE_Section("Alicante", "http://estaticos.elmundo.es/elmundo/rss/alicante.xml"));
        SECTIONS.add(new BE_Section("Castellón", "http://estaticos.elmundo.es/elmundo/rss/castellon.xml"));
        SECTIONS.add(new BE_Section("Madrid", "http://estaticos.elmundo.es/elmundo/rss/madrid.xml"));
        SECTIONS.add(new BE_Section("Málaga", "http://estaticos.elmundo.es/elmundo/rss/andalucia_malaga.xml"));
        SECTIONS.add(new BE_Section("Sevilla", "http://estaticos.elmundo.es/elmundo/rss/andalucia_sevilla.xml"));
        SECTIONS.add(new BE_Section("Valencia", "http://estaticos.elmundo.es/elmundo/rss/valencia.xml"));

        SECTIONS.add(new BE_Section("Regiones", null));
        SECTIONS.add(new BE_Section("Andalucía", "http://estaticos.elmundo.es/elmundo/rss/andalucia.xml"));
        SECTIONS.add(new BE_Section("País vasco", "http://estaticos.elmundo.es/elmundo/rss/paisvasco.xml"));

        SECTIONS.add(new BE_Section("Solidaridad", "http://estaticos.elmundo.es/elmundo/rss/solidaridad.xml"));
        SECTIONS.add(new BE_Section("Comunicación", "http://estaticos.elmundo.es/elmundo/rss/comunicacion.xml"));
        SECTIONS.add(new BE_Section("Televisión", "http://estaticos.elmundo.es/elmundo/rss/television.xml"));
        SECTIONS.add(new BE_Section("Su vivienda", "http://estaticos.elmundo.es/elmundo/rss/suvivienda.xml"));
        SECTIONS.add(new BE_Section("Salud", "http://estaticos.elmundo.es/elmundosalud/rss/portada.xml"));

        SECTIONS.add(new BE_Section("Deportes", "http://estaticos.elmundo.es/elmundodeporte/rss/portada.xml"));
        SECTIONS.add(new BE_Section("Fútbol", "http://estaticos.elmundo.es/elmundodeporte/rss/futbol.xml"));
        SECTIONS.add(new BE_Section("Baloncesto", "http://estaticos.elmundo.es/elmundodeporte/rss/baloncesto.xml"));
        SECTIONS.add(new BE_Section("Ciclismo", "http://estaticos.elmundo.es/elmundodeporte/rss/ciclismo.xml"));
        SECTIONS.add(new BE_Section("Tenis", "http://estaticos.elmundo.es/elmundodeporte/rss/tenis.xml"));

        SECTIONS.add(new BE_Section("Yo dona", "http://estaticos.elmundo.es/yodona/rss/portada.xml"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(news.description);
        doc.select("a, img").remove();
        news.description = doc.text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
        org.jsoup.select.Elements e = doc.select("div[itemprop=\"articleBody\"],#tamano");
        e.select("aside, footer, meta, time").remove();

        if (!e.isEmpty()) {
            news.content = e.html();
        }
    }

}

