package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class ElMundoNewsReader extends NewsReader {

    public ElMundoNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Portada", 0, "http://estaticos.elmundo.es/elmundo/rss/portada.xml"));
        SECTIONS.add(new Section("España", 0, "http://estaticos.elmundo.es/elmundo/rss/espana.xml"));
        SECTIONS.add(new Section("Internacional", 0, "http://estaticos.elmundo.es/elmundo/rss/internacional.xml"));
        SECTIONS.add(new Section("Unión europea", 0, "http://estaticos.elmundo.es/elmundo/rss/union_europea.xml"));
        SECTIONS.add(new Section("Economía", 0, "http://estaticos.elmundo.es/elmundo/rss/economia.xml"));
        SECTIONS.add(new Section("Cultura", 0, "http://estaticos.elmundo.es/elmundo/rss/cultura.xml"));
        SECTIONS.add(new Section("Ciencia", 0, "http://estaticos.elmundo.es/elmundo/rss/ciencia.xml"));

        SECTIONS.add(new Section("Ciudades", 0, null));
        SECTIONS.add(new Section("Alicante", 1, "http://estaticos.elmundo.es/elmundo/rss/alicante.xml"));
        SECTIONS.add(new Section("Barcelona", 1, "http://estaticos.elmundo.es/elmundo/rss/barcelona.xml"));
        SECTIONS.add(new Section("Castellón", 1, "http://estaticos.elmundo.es/elmundo/rss/castellon.xml"));
        SECTIONS.add(new Section("Madrid", 1, "http://estaticos.elmundo.es/elmundo/rss/madrid.xml"));
        SECTIONS.add(new Section("Málaga", 1, "http://estaticos.elmundo.es/elmundo/rss/andalucia_malaga.xml"));
        SECTIONS.add(new Section("Sevilla", 1, "http://estaticos.elmundo.es/elmundo/rss/andalucia_sevilla.xml"));
        SECTIONS.add(new Section("Valencia", 1, "http://estaticos.elmundo.es/elmundo/rss/valencia.xml"));
        SECTIONS.add(new Section("Valladolid", 1, "http://estaticos.elmundo.es/elmundo/rss/valladolid.xml"));

        SECTIONS.add(new Section("Regiones", 0, null));
        SECTIONS.add(new Section("Andalucía", 1, "http://estaticos.elmundo.es/elmundo/rss/andalucia.xml"));
        SECTIONS.add(new Section("Baleares", 1, "http://estaticos.elmundo.es/elmundo/rss/baleares.xml"));
        SECTIONS.add(new Section("Castilla y león", 1, "http://estaticos.elmundo.es/elmundo/rss/castillayleon.xml"));
        SECTIONS.add(new Section("País vasco", 1, "http://estaticos.elmundo.es/elmundo/rss/paisvasco.xml"));

        SECTIONS.add(new Section("Solidaridad", 0, "http://estaticos.elmundo.es/elmundo/rss/solidaridad.xml"));
        SECTIONS.add(new Section("Comunicación", 0, "http://estaticos.elmundo.es/elmundo/rss/comunicacion.xml"));
        SECTIONS.add(new Section("Televisión", 0, "http://estaticos.elmundo.es/elmundo/rss/television.xml"));
        SECTIONS.add(new Section("Su vivienda", 0, "http://estaticos.elmundo.es/elmundo/rss/suvivienda.xml"));
        SECTIONS.add(new Section("Salud", 0, "http://estaticos.elmundo.es/elmundosalud/rss/portada.xml"));

        SECTIONS.add(new Section("Deportes", 0, "http://estaticos.elmundo.es/elmundodeporte/rss/portada.xml"));
        SECTIONS.add(new Section("Fútbol", 1, "http://estaticos.elmundo.es/elmundodeporte/rss/futbol.xml"));
        SECTIONS.add(new Section("Baloncesto", 1, "http://estaticos.elmundo.es/elmundodeporte/rss/baloncesto.xml"));
        SECTIONS.add(new Section("Ciclismo", 1, "http://estaticos.elmundo.es/elmundodeporte/rss/ciclismo.xml"));
        SECTIONS.add(new Section("Golf", 1, "http://estaticos.elmundo.es/elmundodeporte/rss/golf.xml"));
        SECTIONS.add(new Section("Tenis", 1, "http://estaticos.elmundo.es/elmundodeporte/rss/tenis.xml"));
        SECTIONS.add(new Section("Motor", 1, "http://estaticos.elmundo.es/elmundodeporte/rss/motor.xml"));
        SECTIONS.add(new Section("Más deporte", 1, "http://estaticos.elmundo.es/elmundodeporte/rss/masdeporte.xml"));

        SECTIONS.add(new Section("Yo dona", 0, "http://estaticos.elmundo.es/yodona/rss/portada.xml"));
        SECTIONS.add(new Section("El cuentahilos", 1, "http://estaticos.elmundo.es/yodona/rss/blogs/cuentahilos.xml"));
        SECTIONS.add(new Section("Modamanía", 1, "http://estaticos.elmundo.es/yodona/rss/blogs/modamania.xml"));
        SECTIONS.add(new Section("Grand class", 1, "http://estaticos.elmundo.es/yodona/rss/blogs/grandclass.xml"));

    }


    @Override
    protected News applySpecialCase(News news, String content) {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(news.description);
        doc.select("a, img").remove();
        news.description = doc.text();
        return news;
    }

    @Override
    public News readNewsContent(News news) {
        org.jsoup.nodes.Document doc = getDocument(news.link);
        if (doc == null) return news;

        org.jsoup.select.Elements e = doc.select("div[itemprop=\"articleBody\"],#tamano");

        e.select("aside, footer, meta, time").remove();

        if (!e.isEmpty()) {
            news.content = e.html();
        } else {
            debug("No se puede leer: " + news.title);
        }
        return news;
    }

}

