package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class AsSections extends Sections {

	public AsSections()
	{
		super();

		add(new Section("Titulares", "https://as.com/rss/diarioas/portada.xml", 0));
		add(new Section("\u00DAltimas noticias", "https://as.com/rss/tags/ultimas_noticias.xml", 0));

		add(new Section("F\u00FAtbol", "https://futbol.as.com/rss/futbol/portada.xml", 0));
		add(new Section("LaLiga Santander", "https://futbol.as.com/rss/futbol/primera.xml", 1));
		add(new Section("LaLiga 1|2|3", "https://futbol.as.com/rss/futbol/segunda.xml", 1));
		add(new Section("Copa del Rey", "https://futbol.as.com/rss/futbol/copa_del_rey.xml", 1));
		add(new Section("Champions league", "https://futbol.as.com/rss/futbol/champions.xml", 1));
		add(new Section("Europa league", "https://futbol.as.com/rss/futbol/uefa.xml", 1));
		add(new Section("Segunda B", "https://as.com/tag/rss/segunda_division_b/a", 1));
		add(new Section("Mundial", "https://futbol.as.com/rss/futbol/mundial.xml", 1));
		add(new Section("Internacional", "https://futbol.as.com/rss/futbol/internacional.xml", 1));

		add(new Section("Motor", "https://motor.as.com/rss/motor/portada.xml", 0));
		add(new Section("Formula 1", "https://motor.as.com/rss/motor/formula_1.xml", 1));
		add(new Section("Motociclismo", "https://motor.as.com/rss/motor/motociclismo.xml", 1));
		add(new Section("M\u00E1s motor", "https://motor.as.com/rss/motor/mas_motor.xml", 1));

		add(new Section("Baloncesto", "https://motor.as.com/rss/baloncesto/portada.xml", 0));
		add(new Section("Liga endesa", "https://baloncesto.as.com/rss/baloncesto/acb.xml", 1));
		add(new Section("NBA", "https://baloncesto.as.com/rss/baloncesto/nba.xml", 1));
		add(new Section("Euroliga", "https://as.com/rss/baloncesto/euroliga.xml", 1));
		add(new Section("Copa del rey", "https://as.com/rss/baloncesto/copa_del_rey.xml", 1));
		add(new Section("Eurocup", "https://as.com/rss/baloncesto/eurocup.xml", 1));
		add(new Section("M\u00E1s baloncesto", "https://as.com/rss/baloncesto/mas_baloncesto.xml", 1));

		add(new Section("Tenis", "https://tenis.as.com/rss/tenis/portada.xml", 0));
		add(new Section("Ciclismo", "https://as.com/rss/ciclismo/portada.xml", 0));
		add(new Section("Atletismo", "https://as.com/rss/masdeporte/atletismo.xml", 0));
		add(new Section("Balonmano", "https://as.com/rss/masdeporte/balonmano.xml", 0));
		add(new Section("Golf", "https://as.com/rss/masdeporte/golf.xml", 0));
		add(new Section("Juegos olimpicos", "https://as.com/rss/masdeporte/juegosolimpicos.xml", 0));
		add(new Section("Polideportivo", "https://as.com/rss/masdeporte/polideportivo.xml", 0));
		add(new Section("M\u00E1s deportes", "https://as.com/rss/masdeporte/portada.xml", 0));

		add(new Section("Opini\u00F3n", "https://as.com/rss/opinion/portada.xml", 0));

		add(new Section("Equipos", null, -1));
		add(new Section("Almer\u00EDa", "https://as.com/tag/rss/ud_almeria/a", 1));
		add(new Section("Atl\u00E9tico de Madrid", "https://as.com/tag/rss/atletico_madrid/a", 1));
		add(new Section("Athletic de Bilbao", "https://as.com/tag/rss/athletic/a", 1));
		add(new Section("Barcelona", "https://as.com/tag/rss/fc_barcelona/a", 1));
		add(new Section("Betis", "https://as.com/tag/rss/real_betis/a", 1));
		add(new Section("Celta", "https://as.com/tag/rss/real_club_celta_de_vigo/a", 1));
		add(new Section("Elche", "https://as.com/tag/rss/elche_cf/a", 1));
		add(new Section("Espanyol", "https://as.com/tag/rss/rcd_espanyol/a", 1));
		add(new Section("Getafe", "https://as.com/tag/rss/getafe_cf/a", 1));
		add(new Section("Granada", "https://as.com/tag/rss/granada_cf/a", 1));
		add(new Section("Levante", "https://as.com/tag/rss/levante_ud/a", 1));
		add(new Section("M\u00E1laga", "https://as.com/tag/rss/malaga_cf/a", 1));
		add(new Section("Osasuna", "https://as.com/tag/rss/osasuna/a", 1));
		add(new Section("Rayo Vallecano", "https://as.com/tag/rss/rayo_vallecano/a", 1));
		add(new Section("Real Madrid", "https://as.com/tag/rss/real_madrid/a", 1));
		add(new Section("Real Sociedad", "https://as.com/tag/rss/real_sociedad/a", 1));
		add(new Section("Sevilla", "https://as.com/tag/rss/sevilla_futbol_club/a", 1));
		add(new Section("Valencia", "https://as.com/tag/rss/valencia_cf/a", 1));
		add(new Section("Valladolid", "https://as.com/tag/rss/real_valladolid/a", 1));
		add(new Section("Villarreal", "https://as.com/tag/rss/villarreal_cf/a", 1));

		add(new Section("Autores", null, -1));
		add(new Section("Alfredo Rela\u00F1o", "https://as.com/autor/rss/alfredo_relano/a", 1));
		add(new Section("Tom\u00E1s Roncero", "https://as.com/autor/rss/tomas_roncero/a", 1));
		add(new Section("Manolete", "https://as.com/autor/rss/manolete/a", 1));

	}

}
