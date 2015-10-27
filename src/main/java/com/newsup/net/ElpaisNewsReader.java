package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class ElpaisNewsReader extends NewsReader {

    public ElpaisNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Titulares", 0, "http://ep00.epimg.net/rss/elpais/portada.xml"));
        SECTIONS.add(new Section("Tit. Cataluña", 0, "http://ep00.epimg.net/rss/cat/portada.xml"));
        SECTIONS.add(new Section("Tit. America", 0, "http://ep00.epimg.net/rss/elpais/portada_america.xml"));
        SECTIONS.add(new Section("Última hora", 0, "http://ep00.epimg.net/rss/tags/ultimas_noticias.xml"));
        SECTIONS.add(new Section("Más leídos", 0, "http://ep00.epimg.net/rss/tags/noticias_mas_vistas.xml"));

        SECTIONS.add(new Section("Política", 0, "http://ep00.epimg.net/rss/politica/portada.xml"));
        SECTIONS.add(new Section("Economía", 0, "http://ep00.epimg.net/rss/economia/portada.xml"));
        SECTIONS.add(new Section("Ciencia", 0, "http://ep00.epimg.net/rss/elpais/ciencia.xml"));
        SECTIONS.add(new Section("Tecnología", 0, "http://ep00.epimg.net/rss/tecnologia/portada.xml"));
        SECTIONS.add(new Section("Cultura", 0, "http://ep00.epimg.net/rss/cultura/portada.xml"));
        SECTIONS.add(new Section("Deportes", 0, "http://ep00.epimg.net/rss/deportes/portada.xml"));

        SECTIONS.add(new Section("Internacional", 0, null));
        SECTIONS.add(new Section("America latina", 1, "http://elpais.com/tag/rss/latinoamerica/a/"));
        SECTIONS.add(new Section("Europa", 1, "http://elpais.com/tag/rss/mexico/a/"));
        SECTIONS.add(new Section("EEUU", 1, "http://elpais.com/tag/rss/europa/a/"));
        SECTIONS.add(new Section("Oriente próximo", 1, "http://elpais.com/tag/rss/estados_unidos/a/"));
        SECTIONS.add(new Section("México", 1, "http://elpais.com/tag/rss/oriente_proximo/a/"));

        SECTIONS.add(new Section("España", 0, null));
        SECTIONS.add(new Section("Andalucía", 1, "http://ep00.epimg.net/rss/ccaa/andalucia.xml"));
        SECTIONS.add(new Section("Catalunya", 1, "http://ep00.epimg.net/rss/ccaa/catalunya.xml"));
        SECTIONS.add(new Section("C. Valenciana", 1, "http://ep00.epimg.net/rss/ccaa/valencia.xml"));
        SECTIONS.add(new Section("Madrid", 1, "http://ep00.epimg.net/rss/ccaa/madrid.xml"));
        SECTIONS.add(new Section("Pais vasco", 1, "http://ep00.epimg.net/rss/ccaa/paisvasco.xml"));
        SECTIONS.add(new Section("Galicia", 1, "http://ep00.epimg.net/rss/ccaa/galicia.xml"));

        SECTIONS.add(new Section("Deportes", 0, null));
        SECTIONS.add(new Section("Fútbol", 1, "http://elpais.com/tag/rss/futbol/a/"));
        SECTIONS.add(new Section("Motor", 1, "http://elpais.com/tag/rss/deportes_motor/a/"));
        SECTIONS.add(new Section("Baloncesto", 1, "http://elpais.com/tag/rss/baloncesto/a/"));
        SECTIONS.add(new Section("Ciclismo", 1, "http://elpais.com/tag/rss/ciclismo/a/"));
        SECTIONS.add(new Section("Tenis", 1, "http://elpais.com/tag/rss/tenis/a/"));

        SECTIONS.add(new Section("Literatura", 0, "http://elpais.com/tag/rss/libros/a/"));
        SECTIONS.add(new Section("Cine", 0, "http://elpais.com/tag/rss/cine/a/"));
        SECTIONS.add(new Section("Música", 0, "http://elpais.com/tag/rss/musica/a/"));
        SECTIONS.add(new Section("Teatro", 0, "http://elpais.com/tag/rss/teatro/a/"));
        SECTIONS.add(new Section("Danza", 0, "http://elpais.com/tag/rss/danza/a/"));
        SECTIONS.add(new Section("Arte", 0, "http://elpais.com/tag/rss/arte/a/"));
        SECTIONS.add(new Section("Arquitectura", 0, "http://elpais.com/tag/rss/arquitectura/a/"));
        SECTIONS.add(new Section("Estilo", 0, "http://ep00.epimg.net/rss/elpais/estilo.xml"));
        SECTIONS.add(new Section("Televisión", 0, "http://ep00.epimg.net/rss/cultura/television.xml"));
        SECTIONS.add(new Section("Sociedad", 0, "http://ep00.epimg.net/rss/sociedad/portada.xml"));
        SECTIONS.add(new Section("Blogs", 0, "http://ep01.epimg.net/rss/elpais/blogs.xml"));
        SECTIONS.add(new Section("Opinión", 0, "http://ep00.epimg.net/rss/elpais/opinion.xml"));
        SECTIONS.add(new Section("Entrevistas", 0, "http://ep00.epimg.net/rss/elpais/entrevistasdigitales.xml"));

    }

    @Override
    protected News applySpecialCase(News news, String content) {
        if (!content.isEmpty() && !content.contains("Seguir leyendo")) {
            news.content = content;
        }
        return news;
    }

    @Override
    public News readNewsContent(News news) {
        try {
            org.jsoup.nodes.Document doc = getDocument(news.link);
            if (doc == null) return news;

            org.jsoup.select.Elements e = doc.select("#cuerpo_noticia");
            org.jsoup.select.Elements img = doc.select(".contenedor_fotonoticia_compartir");
            if (!e.isEmpty() || !img.isEmpty()) {
                String simg = "";
                if (!img.isEmpty()) simg = img.select("img").outerHtml();

                String mas = doc.select("div[id$=\"|despiece\"]").outerHtml();
                String links = doc.select("div[id$=\"|apoyos\"]").outerHtml();

                e.select("div[id$=\"|despiece\"],div[id$=\"|apoyos\"],div[id$=\"|html\"]").remove();
                e.select("script").remove();

                news.content = simg + e.outerHtml() + mas + links;
            } else {
                e = doc.select(".entry-content");
                e.select("script").remove();
                if (!e.text().isEmpty()) {
                    news.content = e.html();
                } else {
                    debug("[NO SE HA ENCONTRADO LA NOTICIA] " + news.title);
                }
            }
        } catch (Exception e) {
            debug("[ERROR] link:" + news.link);
            e.printStackTrace();
        }
        return news;
    }
}