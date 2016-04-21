package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class MarcaSections extends Sections {

    public MarcaSections()
    {
        super();

        add(new Section("Notícias principales", "http://estaticos.marca.com/rss/portada.xml", 0));

        add(new Section("Fútbol", null, -1));
        add(new Section("1ª División", "http://estaticos.marca.com/rss/futbol_1adivision.xml", 1));
        add(new Section("2ª División", "http://estaticos.marca.com/rss/futbol_2adivision.xml", 1));
        add(new Section("Más fútbol", "http://estaticos.marca.com/rss/futbol_mas_futbol.xml", 1));
        add(new Section("Fútbol internacional", "http://estaticos.marca.com/rss/futbol_futbol_internacional.xml", 1));
        add(new Section("Liga de campeones", "http://estaticos.marca.com/rss/futbol_liga_campeones.xml", 1));
        add(new Section("Copa del Rey", "http://estaticos.marca.com/rss/futbol_copa_rey.xml", 1));
        add(new Section("Selección", "http://estaticos.marca.com/rss/futbol_mundial.xml", 1));

        add(new Section("Equipos", null, -1));
        add(new Section("Athletic", "http://estaticos.marca.com/rss/futbol_equipos_athletic.xml", 1));
        add(new Section("Atlético", "http://estaticos.marca.com/rss/futbol_equipos_atletico.xml", 1));
        add(new Section("Barcelona", "http://estaticos.marca.com/rss/futbol_equipos_barcelona.xml", 1));
        add(new Section("Betis", "http://estaticos.marca.com/rss/futbol_equipos_betis.xml", 1));
        add(new Section("Celta", "http://estaticos.marca.com/rss/futbol_equipos_celta.xml", 1));
        add(new Section("Deportivo", "http://estaticos.marca.com/rss/futbol_equipos_deportivo.xml", 1));
        add(new Section("Espanyol", "http://estaticos.marca.com/rss/futbol_equipos_espanyol.xml", 1));
        add(new Section("Getafe", "http://estaticos.marca.com/rss/futbol_equipos_getafe.xml", 1));
        add(new Section("Granada", "http://estaticos.marca.com/rss/futbol_equipos_granada.xml", 1));
        add(new Section("Levante", "http://estaticos.marca.com/rss/futbol_equipos_levante.xml", 1));
        add(new Section("Málaga", "http://estaticos.marca.com/rss/futbol_equipos_malaga.xml", 1));
        add(new Section("Mallorca", "http://estaticos.marca.com/rss/futbol_equipos_mallorca.xml", 1));
        add(new Section("Osasuna", "http://estaticos.marca.com/rss/futbol_equipos_osasuna.xml", 1));
        add(new Section("Rayo Vallecano", "http://estaticos.marca.com/rss/futbol_equipos_rayo.xml", 1));
        add(new Section("Real Madrid", "http://estaticos.marca.com/rss/futbol_equipos_real_madrid.xml", 1));
        add(new Section("Real Sociedad", "http://estaticos.marca.com/rss/futbol_equipos_real_sociedad.xml", 1));
        add(new Section("Sevilla", "http://estaticos.marca.com/rss/futbol_equipos_sevilla.xml", 1));
        add(new Section("Valencia", "http://estaticos.marca.com/rss/futbol_equipos_valencia.xml", 1));
        add(new Section("Valladolid", "http://estaticos.marca.com/rss/futbol_equipos_valladolid.xml", 1));
        add(new Section("Zaragoza", "http://estaticos.marca.com/rss/futbol_equipos_zaragoza.xml", 1));

        add(new Section("Baloncesto", "http://estaticos.marca.com/rss/baloncesto.xml", 0));
        add(new Section("ACB", "http://estaticos.marca.com/rss/baloncesto_acb.xml", 1));
        add(new Section("NBA", "http://estaticos.marca.com/rss/baloncesto_nba.xml", 1));
        add(new Section("Euroliga", "http://estaticos.marca.com/rss/baloncesto_euroliga.xml", 1));
        add(new Section("Basket FEB", "http://estaticos.marca.com/rss/baloncesto_basketfeb.xml", 1));
        add(new Section("Eurocup", "http://estaticos.marca.com/rss/baloncesto_eurocup.xml", 1));
        add(new Section("Selección", "http://estaticos.marca.com/rss/baloncesto_seleccion.xml", 1));

        add(new Section("Motor", "http://estaticos.marca.com/rss/motor.xml", 0));
        add(new Section("Formula 1", "http://estaticos.marca.com/rss/motor_formula1.xml", 1));
        add(new Section("Mundial de motos", "http://estaticos.marca.com/rss/motor_mundial_motos.xml", 1));
        add(new Section("Rallies", "http://estaticos.marca.com/rss/motor_rallies.xml", 1));
        add(new Section("Gp2", "http://estaticos.marca.com/rss/motor_gp2.xml", 1));
        add(new Section("Superbikes", "http://estaticos.marca.com/rss/motor_superbikes.xml", 1));
        add(new Section("Trial", "http://estaticos.marca.com/rss/motor_trial.xml", 1));
        add(new Section("Más motor", "http://estaticos.marca.com/rss/motor_mas_motor.xml", 1));

        add(new Section("Fútbol sala", "http://estaticos.marca.com/rss/futbol_futbol_sala.xml", 0));
        add(new Section("Tenis", "http://estaticos.marca.com/rss/tenis.xml", 0));
        add(new Section("Ciclismo", "http://estaticos.marca.com/rss/ciclismo.xml", 0));
        add(new Section("Golf", "http://estaticos.marca.com/rss/golf.xml", 0));
        add(new Section("Atletismo", "http://estaticos.marca.com/rss/atletismo.xml", 0));
        add(new Section("Balonmano", "http://estaticos.marca.com/rss/balonmano.xml", 0));
        add(new Section("Vela", "http://estaticos.marca.com/rss/mas_deportes_vela.xml", 0));

    }

}
