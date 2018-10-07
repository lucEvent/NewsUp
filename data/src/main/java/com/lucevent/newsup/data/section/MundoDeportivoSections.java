package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class MundoDeportivoSections extends Sections {

	public MundoDeportivoSections()
	{
		super();

		add(new Section("Portada", "https://www.mundodeportivo.com/mvc/feed/rss/home", 0));

		add(new Section("F\u00FAtbol", "https://www.mundodeportivo.com/feed/rss/futbol", 0));
		add(new Section("Fichajes", "https://www.mundodeportivo.com/feed/rss/futbol/fichajes", 0));

		add(new Section("Equipos", null, -1));
		add(new Section("Athletic", "https://www.mundodeportivo.com/feed/rss/futbol/athletic-bilbao", 1));
		add(new Section("Atl\u00E9tico de Madrid", "https://www.mundodeportivo.com/feed/rss/futbol/atletico-madrid", 1));
		add(new Section("Celta", "https://www.mundodeportivo.com/feed/rss/futbol/celta-vigo", 1));
		add(new Section("C\u00F3rdoba", "https://www.mundodeportivo.com/feed/rss/futbol/cordoba", 1));
		add(new Section("Deportivo", "https://www.mundodeportivo.com/feed/rss/futbol/deportivo-coruna", 1));
		add(new Section("Eibar", "https://www.mundodeportivo.com/feed/rss/futbol/eibar", 1));
		add(new Section("Elche", "https://www.mundodeportivo.com/feed/rss/futbol/elche-cf", 1));
		add(new Section("Espanyol", "https://www.mundodeportivo.com/feed/rss/futbol/rcd-espanyol", 1));
		add(new Section("F.C.Barcelona", "https://www.mundodeportivo.com/feed/rss/futbol/fc-barcelona", 1));
		add(new Section("Getafe", "https://www.mundodeportivo.com/feed/rss/futbol/getafe", 1));
		add(new Section("Granada", "https://www.mundodeportivo.com/feed/rss/futbol/granada", 1));
		add(new Section("Levante", "https://www.mundodeportivo.com/feed/rss/futbol/levante", 1));
		add(new Section("M\u00E1laga", "https://www.mundodeportivo.com/feed/rss/futbol/malaga", 1));
		add(new Section("Rayo Vallecano", "https://www.mundodeportivo.com/feed/rss/futbol/rayo-vallecano", 1));
		add(new Section("Real Madrid", "https://www.mundodeportivo.com/feed/rss/futbol/real-madrid", 1));
		add(new Section("Real Sociedad", "https://www.mundodeportivo.com/feed/rss/futbol/real-sociedad", 1));
		add(new Section("Sevilla", "https://www.mundodeportivo.com/feed/rss/futbol/sevilla", 1));
		add(new Section("Valencia", "https://www.mundodeportivo.com/feed/rss/futbol/valencia", 1));
		add(new Section("Villarreal", "https://www.mundodeportivo.com/feed/rss/futbol/villarreal", 1));

		add(new Section("Baloncesto", "https://www.mundodeportivo.com/feed/rss/baloncesto", 0));
		add(new Section("ACB", "https://www.mundodeportivo.com/feed/rss/baloncesto/acb", 1));
		add(new Section("Euroliga", "https://www.mundodeportivo.com/feed/rss/baloncesto/euroliga", 1));
		add(new Section("NBA", "https://www.mundodeportivo.com/feed/rss/baloncesto/nba", 1));

		add(new Section("Motor", "https://www.mundodeportivo.com/feed/rss/motor", 0));
		add(new Section("F\u00F3rmula 1", "https://www.mundodeportivo.com/feed/rss/motor/f1", 1));
		add(new Section("Motociclismo", "https://www.mundodeportivo.com/feed/rss/motor/motociclismo", 1));
		add(new Section("Rallies", "https://www.mundodeportivo.com/feed/rss/motor/rallies", 1));
		add(new Section("Dakar", "https://www.mundodeportivo.com/feed/rss/motor/rally-dakar", 1));

		add(new Section("Tenis", "https://www.mundodeportivo.com/feed/rss/tenis", 0));
		add(new Section("M\u00E1s deporte", "https://www.mundodeportivo.com/feed/rss/mas-deporte", 0));

		add(new Section("Opini\u00F3n", "https://www.mundodeportivo.com/feed/rss/opinion", 0));
		add(new Section("\u00A1Vaya Mundo!", "https://www.mundodeportivo.com/feed/rss/vaya-mundo", 0));
		add(new Section("Ocio", "https://www.mundodeportivo.com/feed/rss/ocio", 0));
		add(new Section("Hemeroteca", "https://www.mundodeportivo.com/feed/rss/hemeroteca", 0));

	}

}
