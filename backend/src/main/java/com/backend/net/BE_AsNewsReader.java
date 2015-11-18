package com.backend.net;

import com.backend.kernel.BE_Enclosure;
import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_AsNewsReader extends BE_NewsReader {

    public BE_AsNewsReader() {
        super(true);

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Titulares", "http://as.com/rss/diarioas/portada.xml"));
        SECTIONS.add(new BE_Section("Últimas noticias", "http://as.com/rss/tags/ultimas_noticias.xml"));

        SECTIONS.add(new BE_Section("Fútbol", "http://futbol.as.com/rss/futbol/portada.xml"));
        SECTIONS.add(new BE_Section("Liga BBVA", "http://futbol.as.com/rss/futbol/primera.xml"));
        SECTIONS.add(new BE_Section("Liga Adelante", "http://futbol.as.com/rss/futbol/segunda.xml"));
        SECTIONS.add(new BE_Section("Copa del rey", "http://futbol.as.com/rss/futbol/copa_del_rey.xml"));
        SECTIONS.add(new BE_Section("Champions league", "http://futbol.as.com/rss/futbol/champions.xml"));
        SECTIONS.add(new BE_Section("Europa league", "http://futbol.as.com/rss/futbol/uefa.xml"));
        SECTIONS.add(new BE_Section("Segunda B", "http://masdeporte.as.com/tag/rss/segunda_division_b/a"));
        SECTIONS.add(new BE_Section("Mundial", "http://futbol.as.com/rss/futbol/mundial.xml"));
        SECTIONS.add(new BE_Section("Internacional", "http://futbol.as.com/rss/futbol/internacional.xml"));

        SECTIONS.add(new BE_Section("Motor", "http://motor.as.com/rss/motor/portada.xml"));
        SECTIONS.add(new BE_Section("Formula 1", "http://motor.as.com/rss/motor/formula_1.xml"));
        SECTIONS.add(new BE_Section("Motociclismo", "http://motor.as.com/rss/motor/motociclismo.xml"));
        SECTIONS.add(new BE_Section("Más motor", "http://motor.as.com/rss/motor/mas_motor.xml"));

        SECTIONS.add(new BE_Section("Baloncesto", "http://motor.as.com/rss/baloncesto/portada.xml"));
        SECTIONS.add(new BE_Section("Liga endesa", "http://baloncesto.as.com/rss/baloncesto/acb.xml"));
        SECTIONS.add(new BE_Section("NBA", "http://baloncesto.as.com/rss/baloncesto/nba.xml"));
        SECTIONS.add(new BE_Section("Euroliga", "http://as.com/rss/baloncesto/euroliga.xml"));
        SECTIONS.add(new BE_Section("Copa del rey", "http://as.com/rss/baloncesto/copa_del_rey.xml"));
        SECTIONS.add(new BE_Section("Eurocup", "http://as.com/rss/baloncesto/eurocup.xml"));
        SECTIONS.add(new BE_Section("Más baloncesto", "http://as.com/rss/baloncesto/mas_baloncesto.xml"));

        SECTIONS.add(new BE_Section("Tenis", "http://tenis.as.com/rss/tenis/portada.xml"));
        SECTIONS.add(new BE_Section("Ciclismo", "http://as.com/rss/ciclismo/portada.xml"));
        SECTIONS.add(new BE_Section("Atletismo", "http://as.com/rss/masdeporte/atletismo.xml"));
        SECTIONS.add(new BE_Section("Balonmano", "http://as.com/rss/masdeporte/balonmano.xml"));
        SECTIONS.add(new BE_Section("Golf", "http://as.com/rss/masdeporte/golf.xml"));
        SECTIONS.add(new BE_Section("Juegos olimpicos", "http://as.com/rss/masdeporte/juegosolimpicos.xml"));
        SECTIONS.add(new BE_Section("Polideportivo", "http://as.com/rss/masdeporte/polideportivo.xml"));
        SECTIONS.add(new BE_Section("Más deportes", "http://as.com/rss/masdeporte/portada.xml"));

        SECTIONS.add(new BE_Section("Opinión", "http://as.com/rss/opinion/portada.xml"));

        SECTIONS.add(new BE_Section("Equipos", null));
        SECTIONS.add(new BE_Section("Almería", "http://masdeporte.as.com/tag/rss/ud_almeria/a"));
        SECTIONS.add(new BE_Section("Atlético de madrid", "http://masdeporte.as.com/tag/rss/atletico_madrid/a"));
        SECTIONS.add(new BE_Section("Athletic de bilbao", "http://masdeporte.as.com/tag/rss/athletic/a"));
        SECTIONS.add(new BE_Section("Barcelona", "http://masdeporte.as.com/tag/rss/fc_barcelona/a"));
        SECTIONS.add(new BE_Section("Betis", "http://masdeporte.as.com/tag/rss/real_betis/a"));
        SECTIONS.add(new BE_Section("Celta", "http://masdeporte.as.com/tag/rss/real_club_celta_de_vigo/a"));
        SECTIONS.add(new BE_Section("Elche", "http://masdeporte.as.com/tag/rss/elche_cf/a"));
        SECTIONS.add(new BE_Section("Espanyol", "http://masdeporte.as.com/tag/rss/rcd_espanyol/a"));
        SECTIONS.add(new BE_Section("Getafe", "http://masdeporte.as.com/tag/rss/getafe_cf/a"));
        SECTIONS.add(new BE_Section("Granada", "http://masdeporte.as.com/tag/rss/granada_cf/a"));
        SECTIONS.add(new BE_Section("Levante", "http://masdeporte.as.com/tag/rss/levante_ud/a"));
        SECTIONS.add(new BE_Section("Málaga", "http://masdeporte.as.com/tag/rss/malaga_cf/a"));
        SECTIONS.add(new BE_Section("Osasuna", "http://masdeporte.as.com/tag/rss/osasuna/a"));
        SECTIONS.add(new BE_Section("Rayo Vallecano", "http://masdeporte.as.com/tag/rss/rayo_vallecano/a"));
        SECTIONS.add(new BE_Section("Real Madrid", "http://masdeporte.as.com/tag/rss/real_madrid/a"));
        SECTIONS.add(new BE_Section("Real Sociedad", "http://masdeporte.as.com/tag/rss/real_sociedad/a"));
        SECTIONS.add(new BE_Section("Sevilla", "http://masdeporte.as.com/tag/rss/sevilla_futbol_club/a"));
        SECTIONS.add(new BE_Section("Valencia", "http://masdeporte.as.com/tag/rss/valencia_cf/a"));
        SECTIONS.add(new BE_Section("Valladolid", "http://masdeporte.as.com/tag/rss/real_valladolid/a"));
        SECTIONS.add(new BE_Section("Villarreal", "http://masdeporte.as.com/tag/rss/villarreal_cf/a"));

        SECTIONS.add(new BE_Section("Autores", null));
        SECTIONS.add(new BE_Section("Alfredo Relaño", "http://masdeporte.as.com/autor/rss/alfredo_relano/a"));
        SECTIONS.add(new BE_Section("Juan Mora", "http://masdeporte.as.com/autor/rss/juan_mora/a"));
        SECTIONS.add(new BE_Section("Alejandro Elortegui", "http://masdeporte.as.com/autor/rss/alejandro_elortegui/a"));
        SECTIONS.add(new BE_Section("Juanma Trueba", "http://masdeporte.as.com/autor/rss/juanma_trueba/a"));
        SECTIONS.add(new BE_Section("Tomás Roncero", "http://masdeporte.as.com/autor/rss/tomas_roncero/a"));
        SECTIONS.add(new BE_Section("Manolete", "http://masdeporte.as.com/autor/rss/manolete/a"));
        SECTIONS.add(new BE_Section("Javier Matallanas", "http://masdeporte.as.com/autor/rss/javier_g._matallanas/a"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        if (!content.isEmpty()) {
            news.content = getEnclosures(news) + content;
        }
        return news;
    }

    private String getEnclosures(BE_News news) {
        String res = "";
        boolean imgset = false;
        for (BE_Enclosure e : news.enclosures) {
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
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
        org.jsoup.select.Elements e = doc.select("[itemprop=\"articleBody\"");

        if (!e.isEmpty()) {
            news.content = getEnclosures(news) + e.html();
        } else {
            boolean contains = news.link.contains("video");
            boolean thereis = false;
            String videohtml = "";
            for (BE_Enclosure enclosure : news.enclosures) {
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
