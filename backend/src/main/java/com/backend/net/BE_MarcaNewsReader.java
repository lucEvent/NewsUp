package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_MarcaNewsReader extends BE_NewsReader {

    public BE_MarcaNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Notícias principales", "http://marca.feedsportal.com/rss/portada.xml"));

        SECTIONS.add(new BE_Section("Fútbol", null));
        SECTIONS.add(new BE_Section("1ª División", "http://marca.feedsportal.com/rss/futbol_1adivision.xml"));
        SECTIONS.add(new BE_Section("2ª División", "http://marca.feedsportal.com/rss/futbol_2adivision.xml"));
        SECTIONS.add(new BE_Section("Más fútbol", "http://marca.feedsportal.com/rss/futbol_mas_futbol.xml"));
        SECTIONS.add(new BE_Section("Fútbol internacional", "http://marca.feedsportal.com/rss/futbol_futbol_internacional.xml"));
        SECTIONS.add(new BE_Section("Liga de campeones", "http://marca.feedsportal.com/rss/futbol_liga_campeones.xml"));
        SECTIONS.add(new BE_Section("Copa del Rey", "http://marca.feedsportal.com/rss/futbol_copa_rey.xml"));
        SECTIONS.add(new BE_Section("Selección", "http://marca.feedsportal.com/rss/futbol_mundial.xml"));

        SECTIONS.add(new BE_Section("Equipos", null));
        SECTIONS.add(new BE_Section("Athletic", "http://marca.feedsportal.com/rss/futbol_equipos_athletic.xml"));
        SECTIONS.add(new BE_Section("Atlético", "http://marca.feedsportal.com/rss/futbol_equipos_atletico.xml"));
        SECTIONS.add(new BE_Section("Barcelona", "http://marca.feedsportal.com/rss/futbol_equipos_barcelona.xml"));
        SECTIONS.add(new BE_Section("Betis", "http://marca.feedsportal.com/rss/futbol_equipos_betis.xml"));
        SECTIONS.add(new BE_Section("Celta", "http://marca.feedsportal.com/rss/futbol_equipos_celta.xml"));
        SECTIONS.add(new BE_Section("Deportivo", "http://marca.feedsportal.com/rss/futbol_equipos_deportivo.xml"));
        SECTIONS.add(new BE_Section("Espanyol", "http://marca.feedsportal.com/rss/futbol_equipos_espanyol.xml"));
        SECTIONS.add(new BE_Section("Getafe", "http://marca.feedsportal.com/rss/futbol_equipos_getafe.xml"));
        SECTIONS.add(new BE_Section("Granada", "http://marca.feedsportal.com/rss/futbol_equipos_granada.xml"));
        SECTIONS.add(new BE_Section("Levante", "http://marca.feedsportal.com/rss/futbol_equipos_levante.xml"));
        SECTIONS.add(new BE_Section("Málaga", "http://marca.feedsportal.com/rss/futbol_equipos_malaga.xml"));
        SECTIONS.add(new BE_Section("Mallorca", "http://marca.feedsportal.com/rss/futbol_equipos_mallorca.xml"));
        SECTIONS.add(new BE_Section("Osasuna", "http://marca.feedsportal.com/rss/futbol_equipos_osasuna.xml"));
        SECTIONS.add(new BE_Section("Rayo Vallecano", "http://marca.feedsportal.com/rss/futbol_equipos_rayo.xml"));
        SECTIONS.add(new BE_Section("Real Madrid", "http://marca.feedsportal.com/rss/futbol_equipos_real_madrid.xml"));
        SECTIONS.add(new BE_Section("Real Sociedad", "http://marca.feedsportal.com/rss/futbol_equipos_real_sociedad.xml"));
        SECTIONS.add(new BE_Section("Sevilla", "http://marca.feedsportal.com/rss/futbol_equipos_sevilla.xml"));
        SECTIONS.add(new BE_Section("Valencia", "http://marca.feedsportal.com/rss/futbol_equipos_valencia.xml"));
        SECTIONS.add(new BE_Section("Valladolid", "http://marca.feedsportal.com/rss/futbol_equipos_valladolid.xml"));
        SECTIONS.add(new BE_Section("Zaragoza", "http://marca.feedsportal.com/rss/futbol_equipos_zaragoza.xml"));

        SECTIONS.add(new BE_Section("Baloncesto", "http://marca.feedsportal.com/rss/baloncesto.xml"));
        SECTIONS.add(new BE_Section("ACB", "http://marca.feedsportal.com/rss/baloncesto_acb.xml"));
        SECTIONS.add(new BE_Section("NBA", "http://marca.feedsportal.com/rss/baloncesto_nba.xml"));
        SECTIONS.add(new BE_Section("Euroliga", "http://marca.feedsportal.com/rss/baloncesto_euroliga.xml"));
        SECTIONS.add(new BE_Section("Basket FEB", "http://marca.feedsportal.com/rss/baloncesto_basketfeb.xml"));
        SECTIONS.add(new BE_Section("Eurocup", "http://marca.feedsportal.com/rss/baloncesto_eurocup.xml"));
        SECTIONS.add(new BE_Section("Selección", "http://marca.feedsportal.com/rss/baloncesto_seleccion.xml"));

        SECTIONS.add(new BE_Section("Motor", "http://marca.feedsportal.com/rss/motor.xml"));
        SECTIONS.add(new BE_Section("Formula 1", "http://marca.feedsportal.com/rss/motor_formula1.xml"));
        SECTIONS.add(new BE_Section("Mundial de motos", "http://marca.feedsportal.com/rss/motor_mundial_motos.xml"));
        SECTIONS.add(new BE_Section("Rallies", "http://marca.feedsportal.com/rss/motor_rallies.xml"));
        SECTIONS.add(new BE_Section("Gp2", "http://marca.feedsportal.com/rss/motor_gp2.xml"));
        SECTIONS.add(new BE_Section("Superbikes", "http://marca.feedsportal.com/rss/motor_superbikes.xml"));
        SECTIONS.add(new BE_Section("Trial", "http://marca.feedsportal.com/rss/motor_trial.xml"));
        SECTIONS.add(new BE_Section("Más motor", "http://marca.feedsportal.com/rss/motor_mas_motor.xml"));

        SECTIONS.add(new BE_Section("Fútbol sala", "http://marca.feedsportal.com/rss/futbol_futbol_sala.xml"));
        SECTIONS.add(new BE_Section("Tenis", "http://marca.feedsportal.com/rss/tenis.xml"));
        SECTIONS.add(new BE_Section("Ciclismo", "http://marca.feedsportal.com/rss/ciclismo.xml"));
        SECTIONS.add(new BE_Section("Golf", "http://marca.feedsportal.com/rss/golf.xml"));
        SECTIONS.add(new BE_Section("Atletismo", "http://marca.feedsportal.com/rss/atletismo.xml"));
        SECTIONS.add(new BE_Section("Balonmano", "http://marca.feedsportal.com/rss/balonmano.xml"));
        SECTIONS.add(new BE_Section("Vela", "http://marca.feedsportal.com/rss/mas_deportes_vela.xml"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text();
        news.description = news.description.replace("Leer", "");
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
        doc.select("script").remove();

        org.jsoup.select.Elements ee = doc.select(".news-item");

        if (ee.isEmpty()) {

            ee = doc.select("#contenido-noticia");

            if (!ee.isEmpty()) {

                org.jsoup.select.Elements img = doc.select(".cubrereproductor noscript");
                org.jsoup.select.Elements content = doc.select(".cuerpo_articulo > p");

                if (content.isEmpty()) {
                    news.content = ee.select(".bloque-foto img").outerHtml();
                } else {
                    news.content = img.html() + content.outerHtml();
                }
            }
        } else {

            org.jsoup.select.Elements img = doc.select("figure");
            org.jsoup.select.Elements content = doc.select("[itemprop=\"articleBody\"] > p");

            if (!content.isEmpty()) {

                String simage = img.html().replace("\"//", "\"http://").replace("noscript", "p");
                news.content = simage + content.outerHtml();

            }
        }
        news.content = news.content.replace("style=\"", "none=\"");
    }

}
