package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class ElPaisSections extends Sections {

	public ElPaisSections()
	{
		super();

		add(new Section("Titulares Espa\u00F1a", "https://ep00.epimg.net/rss/elpais/portada.xml", 0));
		add(new Section("Titulares Catalu\u00F1a", "https://ep00.epimg.net/rss/cat/portada.xml", 0));
		add(new Section("Titulares Am\u00E9rica", "https://ep00.epimg.net/rss/elpais/portada_america.xml", 0));
		add(new Section("Titulares Brasil", "https://ep00.epimg.net/rss/brasil/portada.xml", 0));

		add(new Section("\u00DAltimas not\u00EDcias", "https://ep00.epimg.net/rss/tags/ultimas_noticias.xml", 0));
		add(new Section("M\u00E1s visto", "https://ep00.epimg.net/rss/tags/noticias_mas_vistas.xml", 0));

		add(new Section("Internacional", null, -1));
		add(new Section("Am\u00E9rica latina", "https://elpais.com/tag/rss/latinoamerica/a/", 1));
		add(new Section("M\u00E9xico", "https://elpais.com/tag/rss/mexico/a/", 1));
		add(new Section("Europa", "https://elpais.com/tag/rss/europa/a/", 1));
		add(new Section("Estados Unidos", "https://elpais.com/tag/rss/estados_unidos/a/", 1));
		add(new Section("Oriente pr\u00F3ximo", "https://elpais.com/tag/rss/oriente_proximo/a/", 1));

		add(new Section("Opini\u00F3n", "https://ep00.epimg.net/rss/elpais/opinion.xml", 0));

		add(new Section("Espa\u00F1a", null, -1));
		add(new Section("Andaluc\u00EDa", "https://ep00.epimg.net/rss/ccaa/andalucia.xml", 1));
		add(new Section("Catalunya", "https://ep00.epimg.net/rss/ccaa/catalunya.xml", 1));
		add(new Section("C. Valenciana", "https://ep00.epimg.net/rss/ccaa/valencia.xml", 1));
		add(new Section("Madrid", "https://ep00.epimg.net/rss/ccaa/madrid.xml", 1));
		add(new Section("Pa\u00EDs Vasco", "https://ep00.epimg.net/rss/ccaa/paisvasco.xml", 1));
		add(new Section("Galicia", "https://ep00.epimg.net/rss/ccaa/galicia.xml", 1));

		add(new Section("Pol\u00EDtica", "https://ep00.epimg.net/rss/politica/portada.xml", 0));
		add(new Section("Econom\u00EDa", "https://ep00.epimg.net/rss/economia/portada.xml", 0));
		add(new Section("Ciencia", "https://ep00.epimg.net/rss/elpais/ciencia.xml", 0));
		add(new Section("Tecnolog\u00EDa", "https://ep00.epimg.net/rss/tecnologia/portada.xml", 0));

		add(new Section("Cultura", "https://ep00.epimg.net/rss/cultura/portada.xml", 0));
		add(new Section("Literatura", "https://elpais.com/tag/rss/libros/a/", 1));
		add(new Section("Cine", "https://elpais.com/tag/rss/cine/a/", 1));
		add(new Section("M\u00FAsica", "https://elpais.com/tag/rss/musica/a/", 1));
		add(new Section("Teatro", "https://elpais.com/tag/rss/teatro/a/", 1));
		add(new Section("Danza", "https://elpais.com/tag/rss/danza/a/", 1));
		add(new Section("Arte", "https://elpais.com/tag/rss/arte/a/", 1));
		add(new Section("Arquitectura", "https://elpais.com/tag/rss/arquitectura/a/", 1));

		add(new Section("Estilo", "https://ep00.epimg.net/rss/elpais/estilo.xml", 0));

		add(new Section("Deportes", "https://ep00.epimg.net/rss/deportes/portada.xml", 0));
		add(new Section("F\u00FAtbol", "https://elpais.com/tag/rss/futbol/a/", 1));
		add(new Section("Motor", "https://elpais.com/tag/rss/deportes_motor/a/", 1));
		add(new Section("Baloncesto", "https://elpais.com/tag/rss/baloncesto/a/", 1));
		add(new Section("Ciclismo", "https://elpais.com/tag/rss/ciclismo/a/", 1));
		add(new Section("Tenis", "https://elpais.com/tag/rss/tenis/a/", 1));

		add(new Section("Televisi\u00F3n", "https://ep00.epimg.net/rss/cultura/television.xml", 0));

		add(new Section("Sociedad", null, -1));
		add(new Section("Gente", "https://elpais.com/tag/rss/gente/a/", 1));
		add(new Section("Educaci\u00F3n", "https://elpais.com/tag/rss/educacion/a/", 1));
		add(new Section("Religi\u00F3n", "https://elpais.com/tag/rss/religion/a/", 1));
		add(new Section("Salud", "https://elpais.com/tag/rss/salud/a", 1));

		add(new Section("Blogs", "https://ep01.epimg.net/rss/elpais/blogs.xml", 0));

		add(new Section("Multimedia", null, -1));
		add(new Section("El d\u00EDa en im\u00E1genes", "https://elpais.com/agr/rss/el_dia_en_imagenes/a", 1));
		add(new Section("\u00DAltimas fotos", "https://elpais.com/tag/rss/albumes/a/", 1));
		add(new Section("Vi\u00F1etas", "https://elpais.com/tag/c/rss/ec7a643a2efd84d02c5948f7a9c86aa7", 1));
		add(new Section("Forges", "https://ep00.epimg.net/rss/tags/a_antonio_fraguas_forges_a.xml", 1));

	}

}
