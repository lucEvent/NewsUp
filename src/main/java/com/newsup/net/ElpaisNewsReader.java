package com.newsup.net;

import android.content.Context;
import android.os.Handler;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

import org.jsoup.select.Elements;

public class ElpaisNewsReader extends NewsReader {

    public ElpaisNewsReader(Handler handler, Context context) {
        super(handler, context);

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("TITULARES", 0, "http://ep00.epimg.net/rss/elpais/portada.xml"));
        SECTIONS.add(new Section("TIT. CATALUÑA", 0, "http://ep00.epimg.net/rss/cat/portada.xml"));
        SECTIONS.add(new Section("TIT. AMERICA", 0, "http://ep00.epimg.net/rss/elpais/portada_america.xml"));
        SECTIONS.add(new Section("ÚLTIMA HORA", 0, "http://ep00.epimg.net/rss/tags/ultimas_noticias.xml"));
        SECTIONS.add(new Section("MÁS LEÍDOS", 0, "http://ep00.epimg.net/rss/tags/noticias_mas_vistas.xml"));

        SECTIONS.add(new Section("POLÍTICA", 0, "http://ep00.epimg.net/rss/politica/portada.xml"));
        SECTIONS.add(new Section("ECONOMÍA", 0, "http://ep00.epimg.net/rss/economia/portada.xml"));
        SECTIONS.add(new Section("CIENCIA", 0, "http://ep00.epimg.net/rss/elpais/ciencia.xml"));
        SECTIONS.add(new Section("TECNOLOGÍA", 0, "http://ep00.epimg.net/rss/tecnologia/portada.xml"));
        SECTIONS.add(new Section("CULTURA", 0, "http://ep00.epimg.net/rss/cultura/portada.xml"));
        SECTIONS.add(new Section("DEPORTES", 0, "http://ep00.epimg.net/rss/deportes/portada.xml"));

        SECTIONS.add(new Section("INTERNACIONAL", 0, null));
        SECTIONS.add(new Section("AMERICA LATINA", 1, "http://elpais.com/tag/rss/latinoamerica/a/"));
        SECTIONS.add(new Section("EUROPA", 1, "http://elpais.com/tag/rss/mexico/a/"));
        SECTIONS.add(new Section("EEUU", 1, "http://elpais.com/tag/rss/europa/a/"));
        SECTIONS.add(new Section("ORIENTE PRÓXIMO", 1, "http://elpais.com/tag/rss/estados_unidos/a/"));
        SECTIONS.add(new Section("MÉXICO", 1, "http://elpais.com/tag/rss/oriente_proximo/a/"));

        SECTIONS.add(new Section("ESPAÑA", 0, null));
        SECTIONS.add(new Section("ANDALUCÍA", -1, "http://ep00.epimg.net/rss/ccaa/andalucia.xml"));
        SECTIONS.add(new Section("CATALUNYA", -1, "http://ep00.epimg.net/rss/ccaa/catalunya.xml"));
        SECTIONS.add(new Section("C. VALENCIANA", -1, "http://ep00.epimg.net/rss/ccaa/valencia.xml"));
        SECTIONS.add(new Section("MADRID", -1, "http://ep00.epimg.net/rss/ccaa/madrid.xml"));
        SECTIONS.add(new Section("PAIS VASCO", -1, "http://ep00.epimg.net/rss/ccaa/paisvasco.xml"));
        SECTIONS.add(new Section("GALICIA", -1, "http://ep00.epimg.net/rss/ccaa/galicia.xml"));

        SECTIONS.add(new Section("DEPORTES", 0, null));
        SECTIONS.add(new Section("FÚTBOL", -1, "http://elpais.com/tag/rss/futbol/a/"));
        SECTIONS.add(new Section("MOTOR", -1, "http://elpais.com/tag/rss/deportes_motor/a/"));
        SECTIONS.add(new Section("BALONCESTO", -1, "http://elpais.com/tag/rss/baloncesto/a/"));
        SECTIONS.add(new Section("CICLISMO", -1, "http://elpais.com/tag/rss/ciclismo/a/"));
        SECTIONS.add(new Section("TENIS", -1, "http://elpais.com/tag/rss/tenis/a/"));

        SECTIONS.add(new Section("LITERATURA", 0, "http://elpais.com/tag/rss/libros/a/"));
        SECTIONS.add(new Section("CINE", 0, "http://elpais.com/tag/rss/cine/a/"));
        SECTIONS.add(new Section("MÚSICA", 0, "http://elpais.com/tag/rss/musica/a/"));
        SECTIONS.add(new Section("TEATRO", 0, "http://elpais.com/tag/rss/teatro/a/"));
        SECTIONS.add(new Section("DANZA", 0, "http://elpais.com/tag/rss/danza/a/"));
        SECTIONS.add(new Section("ARTE", 0, "http://elpais.com/tag/rss/arte/a/"));
        SECTIONS.add(new Section("ARQUITECTURA", 0, "http://elpais.com/tag/rss/arquitectura/a/"));
        SECTIONS.add(new Section("ESTILO", 0, "http://ep00.epimg.net/rss/elpais/estilo.xml"));
        SECTIONS.add(new Section("TELEVISIÓN", 0, "http://ep00.epimg.net/rss/cultura/television.xml"));
        SECTIONS.add(new Section("SOCIEDAD", 0, "http://ep00.epimg.net/rss/sociedad/portada.xml"));
        SECTIONS.add(new Section("BLOGS", 0, "http://ep01.epimg.net/rss/elpais/blogs.xml"));
        SECTIONS.add(new Section("OPINIÓN", 0, "http://ep00.epimg.net/rss/elpais/opinion.xml"));
        SECTIONS.add(new Section("ENTREVISTAS", 0, "http://ep00.epimg.net/rss/elpais/entrevistasdigitales.xml"));

    }

    @Override
    public News readNewsContent(News news) {
        try {
            org.jsoup.nodes.Document doc = getDocument(news.link);
            if (doc == null) return news;

            org.jsoup.nodes.Element element = doc.select("#cuerpo_noticia").get(0);

            element.select("blockquote").remove();

            Elements links = element.select("a");
            for (org.jsoup.nodes.Element link : links) {
                link.html(link.text());
            }
            news.content = element.html();

        } catch (Exception e) {
            debug("[ERROR La seleccion del articulo no se ha encontrado] tit:" + news.title);
            e.printStackTrace();
        }
        return news;
    }

}
