package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class MundoDeportivoSections extends Sections {

	public MundoDeportivoSections()
	{
		super();

		add(new Section("Portada", "http://www.mundodeportivo.com/mvc/feed/rss/home", 0));

		add(new Section("F\u00FAtbol", "http://www.mundodeportivo.com/feed/rss/futbol", 0));
		add(new Section("Fichajes", "http://www.mundodeportivo.com/feed/rss/futbol/fichajes", 0));

		add(new Section("Equipos", null, -1));
		add(new Section("Athletic", "http://www.mundodeportivo.com/feed/rss/futbol/athletic-bilbao", 1));
		add(new Section("Atl\u00E9tico de Madrid", "http://www.mundodeportivo.com/feed/rss/futbol/atletico-madrid", 1));
		add(new Section("Celta", "http://www.mundodeportivo.com/feed/rss/futbol/celta-vigo", 1));
		add(new Section("C\u00F3rdoba", "http://www.mundodeportivo.com/feed/rss/futbol/cordoba", 1));
		add(new Section("Deportivo", "http://www.mundodeportivo.com/feed/rss/futbol/deportivo-coruna", 1));
		add(new Section("Eibar", "http://www.mundodeportivo.com/feed/rss/futbol/eibar", 1));
		add(new Section("Elche", "http://www.mundodeportivo.com/feed/rss/futbol/elche-cf", 1));
		add(new Section("Espanyol", "http://www.mundodeportivo.com/feed/rss/futbol/rcd-espanyol", 1));
		add(new Section("F.C.Barcelona", "http://www.mundodeportivo.com/feed/rss/futbol/fc-barcelona", 1));
		add(new Section("Getafe", "http://www.mundodeportivo.com/feed/rss/futbol/getafe", 1));
		add(new Section("Granada", "http://www.mundodeportivo.com/feed/rss/futbol/granada", 1));
		add(new Section("Levante", "http://www.mundodeportivo.com/feed/rss/futbol/levante", 1));
		add(new Section("M\u00E1laga", "http://www.mundodeportivo.com/feed/rss/futbol/malaga", 1));
		add(new Section("Rayo Vallecano", "http://www.mundodeportivo.com/feed/rss/futbol/rayo-vallecano", 1));
		add(new Section("Real Madrid", "http://www.mundodeportivo.com/feed/rss/futbol/real-madrid", 1));
		add(new Section("Real Sociedad", "http://www.mundodeportivo.com/feed/rss/futbol/real-sociedad", 1));
		add(new Section("Sevilla", "http://www.mundodeportivo.com/feed/rss/futbol/sevilla", 1));
		add(new Section("Valencia", "http://www.mundodeportivo.com/feed/rss/futbol/valencia", 1));
		add(new Section("Villarreal", "http://www.mundodeportivo.com/feed/rss/futbol/villarreal", 1));

		add(new Section("Baloncesto", "http://www.mundodeportivo.com/feed/rss/baloncesto", 0));
		add(new Section("ACB", "http://www.mundodeportivo.com/feed/rss/baloncesto/acb", 1));
		add(new Section("Euroliga", "http://www.mundodeportivo.com/feed/rss/baloncesto/euroliga", 1));
		add(new Section("NBA", "http://www.mundodeportivo.com/feed/rss/baloncesto/nba", 1));

		add(new Section("Motor", "http://www.mundodeportivo.com/feed/rss/motor", 0));
		add(new Section("F\u00F3rmula 1", "http://www.mundodeportivo.com/feed/rss/motor/f1", 1));
		add(new Section("Motociclismo", "http://www.mundodeportivo.com/feed/rss/motor/motociclismo", 1));
		add(new Section("Rallies", "http://www.mundodeportivo.com/feed/rss/motor/rallies", 1));
		add(new Section("Dakar", "http://www.mundodeportivo.com/feed/rss/motor/rally-dakar", 1));

		add(new Section("Tenis", "http://www.mundodeportivo.com/feed/rss/tenis", 0));
		add(new Section("M\u00E1s deporte", "http://www.mundodeportivo.com/feed/rss/mas-deporte", 0));

		add(new Section("Opini\u00F3n", "http://www.mundodeportivo.com/feed/rss/opinion", 0));
		add(new Section("\u00A1Vaya Mundo!", "http://www.mundodeportivo.com/feed/rss/vaya-mundo", 0));
		add(new Section("Ocio", "http://www.mundodeportivo.com/feed/rss/ocio", 0));
		add(new Section("Hemeroteca", "http://www.mundodeportivo.com/feed/rss/hemeroteca", 0));

	}

}
