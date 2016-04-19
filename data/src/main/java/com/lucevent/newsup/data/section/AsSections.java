package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class AsSections extends Sections {

    public AsSections()
    {
        super();

        add(new Section("Titulares", "http://as.com/rss/diarioas/portada.xml", 0));
        add(new Section("Últimas noticias", "http://as.com/rss/tags/ultimas_noticias.xml", 0));

        add(new Section("Fútbol", "http://futbol.as.com/rss/futbol/portada.xml", 0));
        add(new Section("Liga BBVA", "http://futbol.as.com/rss/futbol/primera.xml", 1));
        add(new Section("Liga Adelante", "http://futbol.as.com/rss/futbol/segunda.xml", 1));
        add(new Section("Copa del rey", "http://futbol.as.com/rss/futbol/copa_del_rey.xml", 1));
        add(new Section("Champions league", "http://futbol.as.com/rss/futbol/champions.xml", 1));
        add(new Section("Europa league", "http://futbol.as.com/rss/futbol/uefa.xml", 1));
        add(new Section("Segunda B", "http://masdeporte.as.com/tag/rss/segunda_division_b/a", 1));
        add(new Section("Mundial", "http://futbol.as.com/rss/futbol/mundial.xml", 1));
        add(new Section("Internacional", "http://futbol.as.com/rss/futbol/internacional.xml", 1));

        add(new Section("Motor", "http://motor.as.com/rss/motor/portada.xml", 0));
        add(new Section("Formula 1", "http://motor.as.com/rss/motor/formula_1.xml", 1));
        add(new Section("Motociclismo", "http://motor.as.com/rss/motor/motociclismo.xml", 1));
        add(new Section("Más motor", "http://motor.as.com/rss/motor/mas_motor.xml", 1));

        add(new Section("Baloncesto", "http://motor.as.com/rss/baloncesto/portada.xml", 0));
        add(new Section("Liga endesa", "http://baloncesto.as.com/rss/baloncesto/acb.xml", 1));
        add(new Section("NBA", "http://baloncesto.as.com/rss/baloncesto/nba.xml", 1));
        add(new Section("Euroliga", "http://as.com/rss/baloncesto/euroliga.xml", 1));
        add(new Section("Copa del rey", "http://as.com/rss/baloncesto/copa_del_rey.xml", 1));
        add(new Section("Eurocup", "http://as.com/rss/baloncesto/eurocup.xml", 1));
        add(new Section("Más baloncesto", "http://as.com/rss/baloncesto/mas_baloncesto.xml", 1));

        add(new Section("Tenis", "http://tenis.as.com/rss/tenis/portada.xml", 0));
        add(new Section("Ciclismo", "http://as.com/rss/ciclismo/portada.xml", 0));
        add(new Section("Atletismo", "http://as.com/rss/masdeporte/atletismo.xml", 0));
        add(new Section("Balonmano", "http://as.com/rss/masdeporte/balonmano.xml", 0));
        add(new Section("Golf", "http://as.com/rss/masdeporte/golf.xml", 0));
        add(new Section("Juegos olimpicos", "http://as.com/rss/masdeporte/juegosolimpicos.xml", 0));
        add(new Section("Polideportivo", "http://as.com/rss/masdeporte/polideportivo.xml", 0));
        add(new Section("Más deportes", "http://as.com/rss/masdeporte/portada.xml", 0));

        add(new Section("Opinión", "http://as.com/rss/opinion/portada.xml", 0));

        add(new Section("Equipos", null, -1));
        add(new Section("Almería", "http://masdeporte.as.com/tag/rss/ud_almeria/a", 1));
        add(new Section("Atlético de madrid", "http://masdeporte.as.com/tag/rss/atletico_madrid/a", 1));
        add(new Section("Athletic de bilbao", "http://masdeporte.as.com/tag/rss/athletic/a", 1));
        add(new Section("Barcelona", "http://masdeporte.as.com/tag/rss/fc_barcelona/a", 1));
        add(new Section("Betis", "http://masdeporte.as.com/tag/rss/real_betis/a", 1));
        add(new Section("Celta", "http://masdeporte.as.com/tag/rss/real_club_celta_de_vigo/a", 1));
        add(new Section("Elche", "http://masdeporte.as.com/tag/rss/elche_cf/a", 1));
        add(new Section("Espanyol", "http://masdeporte.as.com/tag/rss/rcd_espanyol/a", 1));
        add(new Section("Getafe", "http://masdeporte.as.com/tag/rss/getafe_cf/a", 1));
        add(new Section("Granada", "http://masdeporte.as.com/tag/rss/granada_cf/a", 1));
        add(new Section("Levante", "http://masdeporte.as.com/tag/rss/levante_ud/a", 1));
        add(new Section("Málaga", "http://masdeporte.as.com/tag/rss/malaga_cf/a", 1));
        add(new Section("Osasuna", "http://masdeporte.as.com/tag/rss/osasuna/a", 1));
        add(new Section("Rayo Vallecano", "http://masdeporte.as.com/tag/rss/rayo_vallecano/a", 1));
        add(new Section("Real Madrid", "http://masdeporte.as.com/tag/rss/real_madrid/a", 1));
        add(new Section("Real Sociedad", "http://masdeporte.as.com/tag/rss/real_sociedad/a", 1));
        add(new Section("Sevilla", "http://masdeporte.as.com/tag/rss/sevilla_futbol_club/a", 1));
        add(new Section("Valencia", "http://masdeporte.as.com/tag/rss/valencia_cf/a", 1));
        add(new Section("Valladolid", "http://masdeporte.as.com/tag/rss/real_valladolid/a", 1));
        add(new Section("Villarreal", "http://masdeporte.as.com/tag/rss/villarreal_cf/a", 1));

        add(new Section("Autores", null, 1));
        add(new Section("Alfredo Relaño", "http://masdeporte.as.com/autor/rss/alfredo_relano/a", 1));
        add(new Section("Juan Mora", "http://masdeporte.as.com/autor/rss/juan_mora/a", 1));
        add(new Section("Alejandro Elortegui", "http://masdeporte.as.com/autor/rss/alejandro_elortegui/a", 1));
        add(new Section("Juanma Trueba", "http://masdeporte.as.com/autor/rss/juanma_trueba/a", 1));
        add(new Section("Tomás Roncero", "http://masdeporte.as.com/autor/rss/tomas_roncero/a", 1));
        add(new Section("Manolete", "http://masdeporte.as.com/autor/rss/manolete/a", 1));
        add(new Section("Javier Matallanas", "http://masdeporte.as.com/autor/rss/javier_g._matallanas/a", 1));

    }

}
