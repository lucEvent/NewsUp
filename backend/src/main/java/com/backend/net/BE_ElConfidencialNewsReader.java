package com.backend.net;

import com.backend.kernel.BE_Enclosure;
import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_NewsList;
import com.backend.kernel.list.BE_Sections;

import java.util.ArrayList;

public class BE_ElConfidencialNewsReader extends BE_NewsReader {

    private static final int EC_HASH_LINK = "id".hashCode();
    private static final int EC_HASH_DATE = "updated".hashCode();
    private static final int EC_HASH_DESCRIPTION = "summary".hashCode();
    private static final int EC_HASH_CONTENT = "content".hashCode();
    private static final int EC_HASH_MEDIA = "media:content".hashCode();

    public BE_ElConfidencialNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("España", "http://rss.elconfidencial.com/espana/"));
        SECTIONS.add(new BE_Section("Mundo", "http://rss.elconfidencial.com/mundo/"));
        SECTIONS.add(new BE_Section("Comunicación", "http://rss.elconfidencial.com/comunicacion/"));
        SECTIONS.add(new BE_Section("Sociedad", "http://rss.elconfidencial.com/sociedad/"));

        SECTIONS.add(new BE_Section("Opinión", "http://rss.blogs.elconfidencial.com/"));
        SECTIONS.add(new BE_Section("A. Casado", "http://rss.blogs.elconfidencial.com/espana/al-grano/"));
        SECTIONS.add(new BE_Section("J.A. Zarzalejos", "http://rss.blogs.elconfidencial.com/espana/notebook/"));
        SECTIONS.add(new BE_Section("C. Sánchez", "http://rss.blogs.elconfidencial.com/espana/mientras-tanto/"));
        SECTIONS.add(new BE_Section("El confidente", "http://rss.blogs.elconfidencial.com/espana/el-confidente/"));

        SECTIONS.add(new BE_Section("Cotizalia", null));
        SECTIONS.add(new BE_Section("Mercados", "http://rss.elconfidencial.com/mercados/"));
        SECTIONS.add(new BE_Section("Economía", "http://rss.elconfidencial.com/economia/"));
        SECTIONS.add(new BE_Section("Empresas", "http://rss.elconfidencial.com/empresas/"));
        SECTIONS.add(new BE_Section("Finanzas personales", "http://rss.elconfidencial.com/mercados/finanzas-personales/"));
        SECTIONS.add(new BE_Section("Vivienda", "http://rss.elconfidencial.com/vivienda/"));
        SECTIONS.add(new BE_Section("Fondos de inversión", "http://rss.elconfidencial.com/mercados/fondos-de-inversion/"));

        SECTIONS.add(new BE_Section("Teknautas", "http://rss.elconfidencial.com/tecnologia/"));
        SECTIONS.add(new BE_Section("Aplicaciones", "http://rss.elconfidencial.com/tags/temas/apps-9337/"));
        SECTIONS.add(new BE_Section("Emprendedores", "http://rss.elconfidencial.com/tags/economia/emprendedores-4800/"));
        SECTIONS.add(new BE_Section("Gadgets", "http://rss.elconfidencial.com/tags/temas/gadgets-9340/"));
        SECTIONS.add(new BE_Section("Hardware", "http://rss.elconfidencial.com/tags/temas/hardware-9341/"));
        SECTIONS.add(new BE_Section("Internet", "http://rss.elconfidencial.com/tags/temas/internet-9342/"));
        SECTIONS.add(new BE_Section("Móviles", "http://rss.elconfidencial.com/tags/otros/moviles-8601/"));
        SECTIONS.add(new BE_Section("Redes sociales", "http://rss.elconfidencial.com/tags/otros/redes-sociales-6338/"));

        SECTIONS.add(new BE_Section("Deportes", "http://rss.elconfidencial.com/deportes/"));
        SECTIONS.add(new BE_Section("Fútbol", "http://rss.elconfidencial.com/deportes/futbol/"));
        SECTIONS.add(new BE_Section("Baloncesto", "http://rss.elconfidencial.com/deportes/baloncesto/"));
        SECTIONS.add(new BE_Section("Fórmula 1", "http://rss.elconfidencial.com/deportes/formula-1/"));
        SECTIONS.add(new BE_Section("Motociclismo", "http://rss.elconfidencial.com/deportes/motociclismo/"));
        SECTIONS.add(new BE_Section("Tenis", "http://rss.elconfidencial.com/deportes/tenis/"));
        SECTIONS.add(new BE_Section("Ciclismo", "http://rss.elconfidencial.com/deportes/ciclismo/"));
        SECTIONS.add(new BE_Section("Golf", "http://rss.elconfidencial.com/deportes/golf/"));
        SECTIONS.add(new BE_Section("Otros deportes", "http://rss.elconfidencial.com/deportes/otros-deportes/"));

        SECTIONS.add(new BE_Section("Alma, Corazón, Vida", "http://rss.elconfidencial.com/alma-corazon-vida/"));
        SECTIONS.add(new BE_Section("Alimentación", "http://rss.elconfidencial.com/tags/otros/alimentacion-5601/"));
        SECTIONS.add(new BE_Section("Bienestar", "http://rss.elconfidencial.com/tags/temas/bienestar-9331/"));
        SECTIONS.add(new BE_Section("Educación", "http://rss.elconfidencial.com/tags/otros/educacion-5346/"));
        SECTIONS.add(new BE_Section("Psicología", "http://rss.elconfidencial.com/tags/otros/psicologia-5455/"));
        SECTIONS.add(new BE_Section("Salud", "http://rss.elconfidencial.com/tags/otros/salud-6110/"));
        SECTIONS.add(new BE_Section("Sexualidad", "http://rss.elconfidencial.com/tags/temas/sexualidad-6986/"));
        SECTIONS.add(new BE_Section("Trabajo", "http://rss.elconfidencial.com/tags/economia/trabajo-5284/"));

        SECTIONS.add(new BE_Section("Cultura", "http://rss.elconfidencial.com/cultura/"));
        SECTIONS.add(new BE_Section("Libros", "http://rss.elconfidencial.com/tags/otros/libros-5344/"));
        SECTIONS.add(new BE_Section("Arte", "http://rss.elconfidencial.com/tags/otros/arte-6092/"));
        SECTIONS.add(new BE_Section("Cine", "http://rss.elconfidencial.com/tags/otros/cine-7354/"));
        SECTIONS.add(new BE_Section("Música", "http://rss.elconfidencial.com/tags/otros/musica-5272/"));

        SECTIONS.add(new BE_Section("Vanitatis", null));
        SECTIONS.add(new BE_Section("Actualidad", "http://rss.vanitatis.elconfidencial.com/noticias/"));
        SECTIONS.add(new BE_Section("Tendencias", "http://rss.vanitatis.elconfidencial.com/estilo/"));
        SECTIONS.add(new BE_Section("Televisión", "http://rss.vanitatis.elconfidencial.com/television/"));
        SECTIONS.add(new BE_Section("Casas Reales", "http://rss.vanitatis.elconfidencial.com/casas-reales/"));
        SECTIONS.add(new BE_Section("Blogs", "http://rss.blogs.vanitatis.elconfidencial.com/"));

    }

    @Override
    protected BE_NewsList readRssPage(String rsslink) {
        org.jsoup.nodes.Document doc = getDocument(rsslink);
        if (doc == null) return new BE_NewsList();

        BE_NewsList result = new BE_NewsList();

        org.jsoup.select.Elements items = doc.getElementsByTag("entry");
        for (org.jsoup.nodes.Element item : items) {
            String title = "", link = "", description = "", date = "", content = "";
            ArrayList<String> categories = new ArrayList<String>();
            ArrayList<BE_Enclosure> enclosures = new ArrayList<BE_Enclosure>();
            org.jsoup.select.Elements props = item.getAllElements();

            //TODO Arraylist de opciones que se van quitando y lo hace mas eficiente
            for (org.jsoup.nodes.Element prop : props) {
                int taghash = prop.tagName().hashCode();
                if (taghash == HASH_TITLE) {
                    title = prop.html().replace("&lt;![CDATA[", "").replace("]]&gt;", "").replace("&amp;#039;", "'");
                    continue;
                }
                if (taghash == EC_HASH_LINK) {
                    link = prop.text();
                    continue;
                }
                if (taghash == EC_HASH_DATE) {
                    date = prop.text();
                    continue;
                }
                if (taghash == EC_HASH_DESCRIPTION) {
                    description = prop.text();
                    continue;
                }
                if (taghash == HASH_CATEGORY) {
                    categories.add(prop.text());
                    continue;
                }
                if (taghash == EC_HASH_MEDIA) {
                    enclosures.add(new BE_Enclosure(prop.attr("url"), prop.attr("type"), "0"));
                    continue;
                }
                if (taghash == EC_HASH_CONTENT) {
                    content = prop.html().replace("&lt;", "<").replace("&gt;", ">").replace("&amp;#039;", "'");
                    org.jsoup.nodes.Element con = org.jsoup.Jsoup.parse(content).select("body").get(0);
                    con.children().last().remove();
                    content = con.html();
                }
            }
            BE_News news = new BE_News(title, link, description, date, categories);
            String imgs = "";
            for (BE_Enclosure e : enclosures) {
                imgs += e.html();
            }
            news.content = imgs + content;
            result.add(news);
        }
        return result;
    }

}
