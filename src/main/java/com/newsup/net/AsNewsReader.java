package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.util.Enclosure;

public class AsNewsReader extends NewsReader {

    public AsNewsReader() {
        super(true);

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Titulares", 0, "http://as.com/rss/diarioas/portada.xml"));
        SECTIONS.add(new Section("Últimas noticias", 0, "http://as.com/rss/tags/ultimas_noticias.xml"));

        SECTIONS.add(new Section("Fútbol", 0, "http://futbol.as.com/rss/futbol/portada.xml"));
        SECTIONS.add(new Section("Liga BBVA", 1, "http://futbol.as.com/rss/futbol/primera.xml"));
        SECTIONS.add(new Section("Liga Adelante", 1, "http://futbol.as.com/rss/futbol/segunda.xml"));
        SECTIONS.add(new Section("Copa del rey", 1, "http://futbol.as.com/rss/futbol/copa_del_rey.xml"));
        SECTIONS.add(new Section("Champions league", 1, "http://futbol.as.com/rss/futbol/champions.xml"));
        SECTIONS.add(new Section("Europa league", 1, "http://futbol.as.com/rss/futbol/uefa.xml"));
        SECTIONS.add(new Section("Segunda B", 1, "http://masdeporte.as.com/tag/rss/segunda_division_b/a"));
        SECTIONS.add(new Section("Mundial", 1, "http://futbol.as.com/rss/futbol/mundial.xml"));
        SECTIONS.add(new Section("Internacional", 1, "http://futbol.as.com/rss/futbol/internacional.xml"));

        SECTIONS.add(new Section("Motor", 0, "http://motor.as.com/rss/motor/portada.xml"));
        SECTIONS.add(new Section("Formula 1", 1, "http://motor.as.com/rss/motor/formula_1.xml"));
        SECTIONS.add(new Section("Motociclismo", 1, "http://motor.as.com/rss/motor/motociclismo.xml"));
        SECTIONS.add(new Section("Más motor", 1, "http://motor.as.com/rss/motor/mas_motor.xml"));

        SECTIONS.add(new Section("Baloncesto", 0, "http://motor.as.com/rss/baloncesto/portada.xml"));
        SECTIONS.add(new Section("Liga endesa", 1, "http://baloncesto.as.com/rss/baloncesto/acb.xml"));
        SECTIONS.add(new Section("NBA", 1, "http://baloncesto.as.com/rss/baloncesto/nba.xml"));
        SECTIONS.add(new Section("Euroliga", 1, "http://as.com/rss/baloncesto/euroliga.xml"));
        SECTIONS.add(new Section("Copa del rey", 1, "http://as.com/rss/baloncesto/copa_del_rey.xml"));
        SECTIONS.add(new Section("Eurocup", 1, "http://as.com/rss/baloncesto/eurocup.xml"));
        SECTIONS.add(new Section("Más baloncesto", 1, "http://as.com/rss/baloncesto/mas_baloncesto.xml"));

        SECTIONS.add(new Section("Tenis", 0, "http://tenis.as.com/rss/tenis/portada.xml"));
        SECTIONS.add(new Section("Ciclismo", 0, "http://as.com/rss/ciclismo/portada.xml"));
        SECTIONS.add(new Section("Atletismo", 0, "http://as.com/rss/masdeporte/atletismo.xml"));
        SECTIONS.add(new Section("Balonmano", 0, "http://as.com/rss/masdeporte/balonmano.xml"));
        SECTIONS.add(new Section("Golf", 0, "http://as.com/rss/masdeporte/golf.xml"));
        SECTIONS.add(new Section("Juegos olimpicos", 0, "http://as.com/rss/masdeporte/juegosolimpicos.xml"));
        SECTIONS.add(new Section("Polideportivo", 0, "http://as.com/rss/masdeporte/polideportivo.xml"));
        SECTIONS.add(new Section("Más deportes", 0, "http://as.com/rss/masdeporte/portada.xml"));

        SECTIONS.add(new Section("Opinión", 0, "http://as.com/rss/opinion/portada.xml"));

        SECTIONS.add(new Section("Equipos", 0, null));
        SECTIONS.add(new Section("Almería", 1, "http://masdeporte.as.com/tag/rss/ud_almeria/a"));
        SECTIONS.add(new Section("Atlético de madrid", 1, "http://masdeporte.as.com/tag/rss/atletico_madrid/a"));
        SECTIONS.add(new Section("Athletic de bilbao", 1, "http://masdeporte.as.com/tag/rss/athletic/a"));
        SECTIONS.add(new Section("Barcelona", 1, "http://masdeporte.as.com/tag/rss/fc_barcelona/a"));
        SECTIONS.add(new Section("Betis", 1, "http://masdeporte.as.com/tag/rss/real_betis/a"));
        SECTIONS.add(new Section("Celta", 1, "http://masdeporte.as.com/tag/rss/real_club_celta_de_vigo/a"));
        SECTIONS.add(new Section("Elche", 1, "http://masdeporte.as.com/tag/rss/elche_cf/a"));
        SECTIONS.add(new Section("Espanyol", 1, "http://masdeporte.as.com/tag/rss/rcd_espanyol/a"));
        SECTIONS.add(new Section("Getafe", 1, "http://masdeporte.as.com/tag/rss/getafe_cf/a"));
        SECTIONS.add(new Section("Granada", 1, "http://masdeporte.as.com/tag/rss/granada_cf/a"));
        SECTIONS.add(new Section("Levante", 1, "http://masdeporte.as.com/tag/rss/levante_ud/a"));
        SECTIONS.add(new Section("Málaga", 1, "http://masdeporte.as.com/tag/rss/malaga_cf/a"));
        SECTIONS.add(new Section("Osasuna", 1, "http://masdeporte.as.com/tag/rss/osasuna/a"));
        SECTIONS.add(new Section("Rayo Vallecano", 1, "http://masdeporte.as.com/tag/rss/rayo_vallecano/a"));
        SECTIONS.add(new Section("Real Madrid", 1, "http://masdeporte.as.com/tag/rss/real_madrid/a"));
        SECTIONS.add(new Section("Real Sociedad", 1, "http://masdeporte.as.com/tag/rss/real_sociedad/a"));
        SECTIONS.add(new Section("Sevilla", 1, "http://masdeporte.as.com/tag/rss/sevilla_futbol_club/a"));
        SECTIONS.add(new Section("Valencia", 1, "http://masdeporte.as.com/tag/rss/valencia_cf/a"));
        SECTIONS.add(new Section("Valladolid", 1, "http://masdeporte.as.com/tag/rss/real_valladolid/a"));
        SECTIONS.add(new Section("Villarreal", 1, "http://masdeporte.as.com/tag/rss/villarreal_cf/a"));

        SECTIONS.add(new Section("Autores", 0, null));
        SECTIONS.add(new Section("Alfredo Relaño", 1, "http://masdeporte.as.com/autor/rss/alfredo_relano/a"));
        SECTIONS.add(new Section("Juan Mora", 1, "http://masdeporte.as.com/autor/rss/juan_mora/a"));
        SECTIONS.add(new Section("Alejandro Elortegui", 1, "http://masdeporte.as.com/autor/rss/alejandro_elortegui/a"));
        SECTIONS.add(new Section("Juanma Trueba", 1, "http://masdeporte.as.com/autor/rss/juanma_trueba/a"));
        SECTIONS.add(new Section("Tomás Roncero", 1, "http://masdeporte.as.com/autor/rss/tomas_roncero/a"));
        SECTIONS.add(new Section("Manolete", 1, "http://masdeporte.as.com/autor/rss/manolete/a"));
        SECTIONS.add(new Section("Javier Matallanas", 1, "http://masdeporte.as.com/autor/rss/javier_g._matallanas/a"));

    }

    @Override
    protected News applySpecialCase(News news, String content) {
        if (!content.isEmpty()) {
            news.content = getEnclosures(news) + content;
        }
        return news;
    }

    private String getEnclosures(News news) {
        String res = "";
        boolean imgset = false;
        for (Enclosure e : news.enclosures) {
            if (e.isImage() && !imgset && e.size > 10000) {
                res = e.html();
            } else if (e.isVideo()) {
                res = e.html();
                break;
            }
        }
        return res;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
        org.jsoup.select.Elements e = doc.select("[itemprop=\"articleBody\"");

        if (!e.isEmpty()) {
            news.content = getEnclosures(news) + e.html();
        } else {
            boolean contains = news.link.contains("video");
            boolean thereis = false;
            String videohtml = "";
            for (Enclosure enclosure : news.enclosures) {
                if (enclosure.isVideo()) {
                    thereis = true;
                    videohtml = enclosure.html();
                    break;
                }
            }
            if (contains && thereis) {
                news.content = videohtml + news.description;
            } else {
                e = doc.select("#contenido-interior > p,.entry-content > p,.floatFix > p,.floatFix > figure");
                if (!e.isEmpty()) {
                    news.content = getEnclosures(news) + e.html();
                } else {
                    e = doc.select("#columna2");
                    if (!e.isEmpty()) {
                        e.select(".redes,.menu_post,.archivado,script").remove();
                        news.content = getEnclosures(news) + e.html();
                    } else {
                        e = doc.select(".marcador-generico,.cmt-live");
                        if (!e.isEmpty()) {
                            news.content = getEnclosures(news) + e.html();
                        }
                    }
                }
            }
        }
    }

}
