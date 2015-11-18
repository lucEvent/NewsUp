package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_ElPaisNewsReader extends BE_NewsReader {

    public BE_ElPaisNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Titulares", "http://ep00.epimg.net/rss/elpais/portada.xml"));
        SECTIONS.add(new BE_Section("Tit. Cataluña", "http://ep00.epimg.net/rss/cat/portada.xml"));
        SECTIONS.add(new BE_Section("Tit. America", "http://ep00.epimg.net/rss/elpais/portada_america.xml"));
        SECTIONS.add(new BE_Section("Última hora", "http://ep00.epimg.net/rss/tags/ultimas_noticias.xml"));
        SECTIONS.add(new BE_Section("Más leídos", "http://ep00.epimg.net/rss/tags/noticias_mas_vistas.xml"));

        SECTIONS.add(new BE_Section("Política", "http://ep00.epimg.net/rss/politica/portada.xml"));
        SECTIONS.add(new BE_Section("Economía", "http://ep00.epimg.net/rss/economia/portada.xml"));
        SECTIONS.add(new BE_Section("Ciencia", "http://ep00.epimg.net/rss/elpais/ciencia.xml"));
        SECTIONS.add(new BE_Section("Tecnología", "http://ep00.epimg.net/rss/tecnologia/portada.xml"));
        SECTIONS.add(new BE_Section("Cultura", "http://ep00.epimg.net/rss/cultura/portada.xml"));
        SECTIONS.add(new BE_Section("Deportes", "http://ep00.epimg.net/rss/deportes/portada.xml"));

        SECTIONS.add(new BE_Section("Internacional", null));
        SECTIONS.add(new BE_Section("America latina", "http://elpais.com/tag/rss/latinoamerica/a/"));
        SECTIONS.add(new BE_Section("Europa", "http://elpais.com/tag/rss/mexico/a/"));
        SECTIONS.add(new BE_Section("EEUU", "http://elpais.com/tag/rss/europa/a/"));
        SECTIONS.add(new BE_Section("Oriente próximo", "http://elpais.com/tag/rss/estados_unidos/a/"));
        SECTIONS.add(new BE_Section("México", "http://elpais.com/tag/rss/oriente_proximo/a/"));

        SECTIONS.add(new BE_Section("España", null));
        SECTIONS.add(new BE_Section("Andalucía", "http://ep00.epimg.net/rss/ccaa/andalucia.xml"));
        SECTIONS.add(new BE_Section("Catalunya", "http://ep00.epimg.net/rss/ccaa/catalunya.xml"));
        SECTIONS.add(new BE_Section("C. Valenciana", "http://ep00.epimg.net/rss/ccaa/valencia.xml"));
        SECTIONS.add(new BE_Section("Madrid", "http://ep00.epimg.net/rss/ccaa/madrid.xml"));
        SECTIONS.add(new BE_Section("Pais vasco", "http://ep00.epimg.net/rss/ccaa/paisvasco.xml"));
        SECTIONS.add(new BE_Section("Galicia", "http://ep00.epimg.net/rss/ccaa/galicia.xml"));

        SECTIONS.add(new BE_Section("Deportes", null));
        SECTIONS.add(new BE_Section("Fútbol", "http://elpais.com/tag/rss/futbol/a/"));
        SECTIONS.add(new BE_Section("Motor", "http://elpais.com/tag/rss/deportes_motor/a/"));
        SECTIONS.add(new BE_Section("Baloncesto", "http://elpais.com/tag/rss/baloncesto/a/"));
        SECTIONS.add(new BE_Section("Ciclismo", "http://elpais.com/tag/rss/ciclismo/a/"));
        SECTIONS.add(new BE_Section("Tenis", "http://elpais.com/tag/rss/tenis/a/"));

        SECTIONS.add(new BE_Section("Literatura", "http://elpais.com/tag/rss/libros/a/"));
        SECTIONS.add(new BE_Section("Cine", "http://elpais.com/tag/rss/cine/a/"));
        SECTIONS.add(new BE_Section("Música", "http://elpais.com/tag/rss/musica/a/"));
        SECTIONS.add(new BE_Section("Teatro", "http://elpais.com/tag/rss/teatro/a/"));
        SECTIONS.add(new BE_Section("Danza", "http://elpais.com/tag/rss/danza/a/"));
        SECTIONS.add(new BE_Section("Arte", "http://elpais.com/tag/rss/arte/a/"));
        SECTIONS.add(new BE_Section("Arquitectura", "http://elpais.com/tag/rss/arquitectura/a/"));
        SECTIONS.add(new BE_Section("Estilo", "http://ep00.epimg.net/rss/elpais/estilo.xml"));
        SECTIONS.add(new BE_Section("Televisión", "http://ep00.epimg.net/rss/cultura/television.xml"));
        SECTIONS.add(new BE_Section("Sociedad", "http://ep00.epimg.net/rss/sociedad/portada.xml"));
        SECTIONS.add(new BE_Section("Blogs", "http://ep01.epimg.net/rss/elpais/blogs.xml"));
        SECTIONS.add(new BE_Section("Opinión", "http://ep00.epimg.net/rss/elpais/opinion.xml"));
        SECTIONS.add(new BE_Section("Entrevistas", "http://ep00.epimg.net/rss/elpais/entrevistasdigitales.xml"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        if (!content.isEmpty() && !content.contains("Seguir leyendo")) {
            news.content = content;
        }
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
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
            }
        }
    }
}