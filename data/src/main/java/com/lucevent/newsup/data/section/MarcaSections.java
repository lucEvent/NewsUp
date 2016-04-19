package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class MarcaSections extends Sections {

    public MarcaSections()
    {
        super();

        add(new Section("Notícias principales", "http://marca.feedsportal.com/rss/portada.xml", 0));

        add(new Section("Fútbol", null, -1));
        add(new Section("1ª División", "http://marca.feedsportal.com/rss/futbol_1adivision.xml", 1));
        add(new Section("2ª División", "http://marca.feedsportal.com/rss/futbol_2adivision.xml", 1));
        add(new Section("Más fútbol", "http://marca.feedsportal.com/rss/futbol_mas_futbol.xml", 1));
        add(new Section("Fútbol internacional", "http://marca.feedsportal.com/rss/futbol_futbol_internacional.xml", 1));
        add(new Section("Liga de campeones", "http://marca.feedsportal.com/rss/futbol_liga_campeones.xml", 1));
        add(new Section("Copa del Rey", "http://marca.feedsportal.com/rss/futbol_copa_rey.xml", 1));
        add(new Section("Selección", "http://marca.feedsportal.com/rss/futbol_mundial.xml", 1));

        add(new Section("Equipos", null, -1));
        add(new Section("Athletic", "http://marca.feedsportal.com/rss/futbol_equipos_athletic.xml", 1));
        add(new Section("Atlético", "http://marca.feedsportal.com/rss/futbol_equipos_atletico.xml", 1));
        add(new Section("Barcelona", "http://marca.feedsportal.com/rss/futbol_equipos_barcelona.xml", 1));
        add(new Section("Betis", "http://marca.feedsportal.com/rss/futbol_equipos_betis.xml", 1));
        add(new Section("Celta", "http://marca.feedsportal.com/rss/futbol_equipos_celta.xml", 1));
        add(new Section("Deportivo", "http://marca.feedsportal.com/rss/futbol_equipos_deportivo.xml", 1));
        add(new Section("Espanyol", "http://marca.feedsportal.com/rss/futbol_equipos_espanyol.xml", 1));
        add(new Section("Getafe", "http://marca.feedsportal.com/rss/futbol_equipos_getafe.xml", 1));
        add(new Section("Granada", "http://marca.feedsportal.com/rss/futbol_equipos_granada.xml", 1));
        add(new Section("Levante", "http://marca.feedsportal.com/rss/futbol_equipos_levante.xml", 1));
        add(new Section("Málaga", "http://marca.feedsportal.com/rss/futbol_equipos_malaga.xml", 1));
        add(new Section("Mallorca", "http://marca.feedsportal.com/rss/futbol_equipos_mallorca.xml", 1));
        add(new Section("Osasuna", "http://marca.feedsportal.com/rss/futbol_equipos_osasuna.xml", 1));
        add(new Section("Rayo Vallecano", "http://marca.feedsportal.com/rss/futbol_equipos_rayo.xml", 1));
        add(new Section("Real Madrid", "http://marca.feedsportal.com/rss/futbol_equipos_real_madrid.xml", 1));
        add(new Section("Real Sociedad", "http://marca.feedsportal.com/rss/futbol_equipos_real_sociedad.xml", 1));
        add(new Section("Sevilla", "http://marca.feedsportal.com/rss/futbol_equipos_sevilla.xml", 1));
        add(new Section("Valencia", "http://marca.feedsportal.com/rss/futbol_equipos_valencia.xml", 1));
        add(new Section("Valladolid", "http://marca.feedsportal.com/rss/futbol_equipos_valladolid.xml", 1));
        add(new Section("Zaragoza", "http://marca.feedsportal.com/rss/futbol_equipos_zaragoza.xml", 1));

        add(new Section("Baloncesto", "http://marca.feedsportal.com/rss/baloncesto.xml", 0));
        add(new Section("ACB", "http://marca.feedsportal.com/rss/baloncesto_acb.xml", 1));
        add(new Section("NBA", "http://marca.feedsportal.com/rss/baloncesto_nba.xml", 1));
        add(new Section("Euroliga", "http://marca.feedsportal.com/rss/baloncesto_euroliga.xml", 1));
        add(new Section("Basket FEB", "http://marca.feedsportal.com/rss/baloncesto_basketfeb.xml", 1));
        add(new Section("Eurocup", "http://marca.feedsportal.com/rss/baloncesto_eurocup.xml", 1));
        add(new Section("Selección", "http://marca.feedsportal.com/rss/baloncesto_seleccion.xml", 1));

        add(new Section("Motor", "http://marca.feedsportal.com/rss/motor.xml", 0));
        add(new Section("Formula 1", "http://marca.feedsportal.com/rss/motor_formula1.xml", 1));
        add(new Section("Mundial de motos", "http://marca.feedsportal.com/rss/motor_mundial_motos.xml", 1));
        add(new Section("Rallies", "http://marca.feedsportal.com/rss/motor_rallies.xml", 1));
        add(new Section("Gp2", "http://marca.feedsportal.com/rss/motor_gp2.xml", 1));
        add(new Section("Superbikes", "http://marca.feedsportal.com/rss/motor_superbikes.xml", 1));
        add(new Section("Trial", "http://marca.feedsportal.com/rss/motor_trial.xml", 1));
        add(new Section("Más motor", "http://marca.feedsportal.com/rss/motor_mas_motor.xml", 1));

        add(new Section("Fútbol sala", "http://marca.feedsportal.com/rss/futbol_futbol_sala.xml", 0));
        add(new Section("Tenis", "http://marca.feedsportal.com/rss/tenis.xml", 0));
        add(new Section("Ciclismo", "http://marca.feedsportal.com/rss/ciclismo.xml", 0));
        add(new Section("Golf", "http://marca.feedsportal.com/rss/golf.xml", 0));
        add(new Section("Atletismo", "http://marca.feedsportal.com/rss/atletismo.xml", 0));
        add(new Section("Balonmano", "http://marca.feedsportal.com/rss/balonmano.xml", 0));
        add(new Section("Vela", "http://marca.feedsportal.com/rss/mas_deportes_vela.xml", 0));

    }

}
