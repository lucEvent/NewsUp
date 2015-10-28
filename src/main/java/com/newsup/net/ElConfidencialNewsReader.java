package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.kernel.list.Tags;
import com.newsup.net.util.Enclosure;
import com.newsup.task.Socket;
import com.newsup.task.TaskMessage;

import org.jsoup.select.Elements;

import java.util.ArrayList;

public class ElConfidencialNewsReader extends NewsReader {

    private static final int EC_HASH_LINK = "id".hashCode();
    private static final int EC_HASH_DATE = "updated".hashCode();
    private static final int EC_HASH_DESCRIPTION = "summary".hashCode();
    private static final int EC_HASH_CONTENT = "content".hashCode();
    private static final int EC_HASH_MEDIA = "media:content".hashCode();

    public ElConfidencialNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("España", 0, "http://rss.elconfidencial.com/espana/"));
        SECTIONS.add(new Section("Mundo", 0, "http://rss.elconfidencial.com/mundo/"));
        SECTIONS.add(new Section("Comunicación", 0, "http://rss.elconfidencial.com/comunicacion/"));
        SECTIONS.add(new Section("Sociedad", 0, "http://rss.elconfidencial.com/sociedad/"));

        SECTIONS.add(new Section("Opinión", 0, "http://rss.blogs.elconfidencial.com/"));
        SECTIONS.add(new Section("A. Casado", 1, "http://rss.blogs.elconfidencial.com/espana/al-grano/"));
        SECTIONS.add(new Section("J.A. Zarzalejos", 1, "http://rss.blogs.elconfidencial.com/espana/notebook/"));
        SECTIONS.add(new Section("C. Sánchez", 1, "http://rss.blogs.elconfidencial.com/espana/mientras-tanto/"));
        SECTIONS.add(new Section("El confidente", 1, "http://rss.blogs.elconfidencial.com/espana/el-confidente/"));

        SECTIONS.add(new Section("Cotizalia", 0, null));
        SECTIONS.add(new Section("Mercados", 1, "http://rss.elconfidencial.com/mercados/"));
        SECTIONS.add(new Section("Economía", 1, "http://rss.elconfidencial.com/economia/"));
        SECTIONS.add(new Section("Empresas", 1, "http://rss.elconfidencial.com/empresas/"));
        SECTIONS.add(new Section("Finanzas personales", 1, "http://rss.elconfidencial.com/mercados/finanzas-personales/"));
        SECTIONS.add(new Section("Vivienda", 1, "http://rss.elconfidencial.com/vivienda/"));
        SECTIONS.add(new Section("Fondos de inversión", 1, "http://rss.elconfidencial.com/mercados/fondos-de-inversion/"));

        SECTIONS.add(new Section("Teknautas", 0, "http://rss.elconfidencial.com/tecnologia/"));
        SECTIONS.add(new Section("Aplicaciones", 1, "http://rss.elconfidencial.com/tags/temas/apps-9337/"));
        SECTIONS.add(new Section("Emprendedores", 1, "http://rss.elconfidencial.com/tags/economia/emprendedores-4800/"));
        SECTIONS.add(new Section("Gadgets", 1, "http://rss.elconfidencial.com/tags/temas/gadgets-9340/"));
        SECTIONS.add(new Section("Hardware", 1, "http://rss.elconfidencial.com/tags/temas/hardware-9341/"));
        SECTIONS.add(new Section("Internet", 1, "http://rss.elconfidencial.com/tags/temas/internet-9342/"));
        SECTIONS.add(new Section("Móviles", 1, "http://rss.elconfidencial.com/tags/otros/moviles-8601/"));
        SECTIONS.add(new Section("Redes sociales", 1, "http://rss.elconfidencial.com/tags/otros/redes-sociales-6338/"));

        SECTIONS.add(new Section("Deportes", 0, "http://rss.elconfidencial.com/deportes/"));
        SECTIONS.add(new Section("Fútbol", 1, "http://rss.elconfidencial.com/deportes/futbol/"));
        SECTIONS.add(new Section("Baloncesto", 1, "http://rss.elconfidencial.com/deportes/baloncesto/"));
        SECTIONS.add(new Section("Fórmula 1", 1, "http://rss.elconfidencial.com/deportes/formula-1/"));
        SECTIONS.add(new Section("Motociclismo", 1, "http://rss.elconfidencial.com/deportes/motociclismo/"));
        SECTIONS.add(new Section("Tenis", 1, "http://rss.elconfidencial.com/deportes/tenis/"));
        SECTIONS.add(new Section("Ciclismo", 1, "http://rss.elconfidencial.com/deportes/ciclismo/"));
        SECTIONS.add(new Section("Golf", 1, "http://rss.elconfidencial.com/deportes/golf/"));
        SECTIONS.add(new Section("Otros deportes", 1, "http://rss.elconfidencial.com/deportes/otros-deportes/"));

        SECTIONS.add(new Section("Alma, Corazón, Vida", 0, "http://rss.elconfidencial.com/alma-corazon-vida/"));
        SECTIONS.add(new Section("Alimentación", 1, "http://rss.elconfidencial.com/tags/otros/alimentacion-5601/"));
        SECTIONS.add(new Section("Bienestar", 1, "http://rss.elconfidencial.com/tags/temas/bienestar-9331/"));
        SECTIONS.add(new Section("Educación", 1, "http://rss.elconfidencial.com/tags/otros/educacion-5346/"));
        SECTIONS.add(new Section("Psicología", 1, "http://rss.elconfidencial.com/tags/otros/psicologia-5455/"));
        SECTIONS.add(new Section("Salud", 1, "http://rss.elconfidencial.com/tags/otros/salud-6110/"));
        SECTIONS.add(new Section("Sexualidad", 1, "http://rss.elconfidencial.com/tags/temas/sexualidad-6986/"));
        SECTIONS.add(new Section("Trabajo", 1, "http://rss.elconfidencial.com/tags/economia/trabajo-5284/"));

        SECTIONS.add(new Section("Cultura", 0, "http://rss.elconfidencial.com/cultura/"));
        SECTIONS.add(new Section("Libros", 1, "http://rss.elconfidencial.com/tags/otros/libros-5344/"));
        SECTIONS.add(new Section("Arte", 1, "http://rss.elconfidencial.com/tags/otros/arte-6092/"));
        SECTIONS.add(new Section("Cine", 1, "http://rss.elconfidencial.com/tags/otros/cine-7354/"));
        SECTIONS.add(new Section("Música", 1, "http://rss.elconfidencial.com/tags/otros/musica-5272/"));

        SECTIONS.add(new Section("Vanitatis", 0, null));
        SECTIONS.add(new Section("Actualidad", 1, "http://rss.vanitatis.elconfidencial.com/noticias/"));
        SECTIONS.add(new Section("Tendencias", 1, "http://rss.vanitatis.elconfidencial.com/estilo/"));
        SECTIONS.add(new Section("Televisión", 1, "http://rss.vanitatis.elconfidencial.com/television/"));
        SECTIONS.add(new Section("Casas Reales", 1, "http://rss.vanitatis.elconfidencial.com/casas-reales/"));
        SECTIONS.add(new Section("Blogs", 1, "http://rss.blogs.vanitatis.elconfidencial.com/"));

    }

    @Override
    protected void readRssPage(Socket handler, String rsslink) {
        org.jsoup.nodes.Document doc;
        try {
            doc = getDocument(rsslink);
        } catch (Exception e) {
            debug("[ERROR No se puede leer el link RSS] link:" + rsslink);
            e.printStackTrace();
            return;
        }

        Elements items = doc.getElementsByTag("entry");
        for (org.jsoup.nodes.Element item : items) {
            String title = "", link = "", description = "", date = "", content = "";
            ArrayList<String> categories = new ArrayList<String>();
            ArrayList<Enclosure> enclosures = new ArrayList<Enclosure>();
            Elements props = item.getAllElements();

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
                    enclosures.add(new Enclosure(prop.attr("url"), prop.attr("type"), "0"));
                    continue;
                }
                if (taghash == EC_HASH_CONTENT) {
                    content = prop.html().replace("&lt;", "<").replace("&gt;", ">").replace("&amp;#039;", "'");
                    org.jsoup.nodes.Element con = org.jsoup.Jsoup.parse(content).select("body").get(0);
                    con.children().last().remove();
                    content = con.html();
                }
            }
            News news = new News(title, link, description, date, new Tags(categories));
            String imgs = "";
            for (Enclosure e : enclosures) {
                imgs += e.html();
            }
            news.content = imgs + content;

            handler.message(TaskMessage.NEWS_READ, news);
        }
    }

    @Override
    public News readNewsContent(News news) {
        return news;
    }

}
