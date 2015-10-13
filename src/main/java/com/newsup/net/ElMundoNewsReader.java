package com.newsup.net;

import android.content.Context;
import android.os.Handler;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.kernel.list.Tags;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ElMundoNewsReader extends NewsReader {

    public ElMundoNewsReader(Handler handler, Context context) {
        super(handler, context);

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("PORTADA", 0, "http://estaticos.elmundo.es/elmundo/rss/portada.xml"));
        SECTIONS.add(new Section("ESPAÑA", 0, "http://estaticos.elmundo.es/elmundo/rss/espana.xml"));
        SECTIONS.add(new Section("INTERNACIONAL", 0, "http://estaticos.elmundo.es/elmundo/rss/internacional.xml"));
        SECTIONS.add(new Section("UNIÓN EUROPEA", 0, "http://estaticos.elmundo.es/elmundo/rss/union_europea.xml"));
        SECTIONS.add(new Section("ECONOMÍA", 0, "http://estaticos.elmundo.es/elmundo/rss/economia.xml"));
        SECTIONS.add(new Section("CULTURA", 0, "http://estaticos.elmundo.es/elmundo/rss/cultura.xml"));
        SECTIONS.add(new Section("CIENCIA/ECOLOGÍA", 0, "http://estaticos.elmundo.es/elmundo/rss/ciencia.xml"));
        SECTIONS.add(new Section("MADRID 24 HORAS", 0, "http://estaticos.elmundo.es/elmundo/rss/madrid.xml"));
        SECTIONS.add(new Section("BARCELONA", 0, "http://estaticos.elmundo.es/elmundo/rss/barcelona.xml"));
        SECTIONS.add(new Section("BALEARES", 0, "http://estaticos.elmundo.es/elmundo/rss/baleares.xml"));
        SECTIONS.add(new Section("CASTILLA Y LEÓN", 0, "http://estaticos.elmundo.es/elmundo/rss/castillayleon.xml"));
        SECTIONS.add(new Section("VALLADOLID", 0, "http://estaticos.elmundo.es/elmundo/rss/valladolid.xml"));
        SECTIONS.add(new Section("VALENCIA", 0, "http://estaticos.elmundo.es/elmundo/rss/valencia.xml"));
        SECTIONS.add(new Section("ALICANTE", 0, "http://estaticos.elmundo.es/elmundo/rss/alicante.xml"));
        SECTIONS.add(new Section("CASTELLÓN", 0, "http://estaticos.elmundo.es/elmundo/rss/castellon.xml"));
        SECTIONS.add(new Section("PAÍS VASCO", 0, "http://estaticos.elmundo.es/elmundo/rss/paisvasco.xml"));
        SECTIONS.add(new Section("ANDALUCÍA", 0, "http://estaticos.elmundo.es/elmundo/rss/andalucia.xml"));
        SECTIONS.add(new Section("SEVILLA", 0, "http://estaticos.elmundo.es/elmundo/rss/andalucia_sevilla.xml"));
        SECTIONS.add(new Section("MÁLAGA", 0, "http://estaticos.elmundo.es/elmundo/rss/andalucia_malaga.xml"));
        SECTIONS.add(new Section("SOLIDARIDAD", 0, "http://estaticos.elmundo.es/elmundo/rss/solidaridad.xml"));
        SECTIONS.add(new Section("COMUNICACIÓN", 0, "http://estaticos.elmundo.es/elmundo/rss/comunicacion.xml"));
        SECTIONS.add(new Section("TELEVISIÓN", 0, "http://estaticos.elmundo.es/elmundo/rss/television.xml"));
        SECTIONS.add(new Section("SU VIVIENDA", 0, "http://estaticos.elmundo.es/elmundo/rss/suvivienda.xml"));
        SECTIONS.add(new Section("SALUD", 0, "http://estaticos.elmundo.es/elmundosalud/rss/portada.xml"));

        SECTIONS.add(new Section("DEPORTES", 0, "http://estaticos.elmundo.es/elmundodeporte/rss/portada.xml"));
        SECTIONS.add(new Section("FÚTBOL", -1, "http://estaticos.elmundo.es/elmundodeporte/rss/futbol.xml"));
        SECTIONS.add(new Section("BALONCESTO", -1, "http://estaticos.elmundo.es/elmundodeporte/rss/baloncesto.xml"));
        SECTIONS.add(new Section("CICLISMO", -1, "http://estaticos.elmundo.es/elmundodeporte/rss/ciclismo.xml    "));
        SECTIONS.add(new Section("GOLF", -1, "http://estaticos.elmundo.es/elmundodeporte/rss/golf.xml"));
        SECTIONS.add(new Section("TENIS", -1, "http://estaticos.elmundo.es/elmundodeporte/rss/tenis.xml"));
        SECTIONS.add(new Section("MOTOR", -1, "http://estaticos.elmundo.es/elmundodeporte/rss/motor.xml"));
        SECTIONS.add(new Section("MÁS DEPORTE", -1, "http://estaticos.elmundo.es/elmundodeporte/rss/masdeporte.xml"));

        SECTIONS.add(new Section("YO DONA", 0, "http://estaticos.elmundo.es/yodona/rss/portada.xml"));
        SECTIONS.add(new Section("EL CUENTAHILOS", -1, "http://estaticos.elmundo.es/yodona/rss/blogs/cuentahilos.xml"));
        SECTIONS.add(new Section("MODAMANÍA", -1, "http://estaticos.elmundo.es/yodona/rss/blogs/modamania.xml"));
        SECTIONS.add(new Section("GRAND CLASS", -1, "http://estaticos.elmundo.es/yodona/rss/blogs/grandclass.xml"));

    }


    protected News getNewsLastFilter(String title, String link, String description, String date, Tags categories) {
        Document docD = Jsoup.parse(description);
        docD.select("a, img").remove();
        description = docD.text();
        return new News(title, link, description, date, categories);
    }

    @Override
    public News readNewsContent(News news) {
        try {
            org.jsoup.nodes.Document doc = getDocument(news.link);
            if (doc == null) return news;

            Elements elements = doc.select("#tamano");
            news.content = elements.html();

        } catch (Exception e) {
            debug("[ERROR] title:" + news.title);
            e.printStackTrace();
        }
        return news;
    }

}

