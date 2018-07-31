package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class ElMundoSections extends Sections {

	public ElMundoSections()
	{
		super();

		add(new Section("Portada", "http://estaticos.elmundo.es/elmundo/rss/portada.xml", 0));
		add(new Section("Espa\u00F1a", "http://estaticos.elmundo.es/elmundo/rss/espana.xml", 0));
		add(new Section("Internacional", "http://estaticos.elmundo.es/elmundo/rss/internacional.xml", 0));
		add(new Section("Econom\u00EDa", "http://estaticos.elmundo.es/elmundo/rss/economia.xml", 0));
		add(new Section("Cultura", "http://estaticos.elmundo.es/elmundo/rss/cultura.xml", 0));

		add(new Section("Ciudades", null, -1));
		add(new Section("Alicante", "http://estaticos.elmundo.es/elmundo/rss/alicante.xml", 1));
		add(new Section("Castell\u00F3n", "http://estaticos.elmundo.es/elmundo/rss/castellon.xml", 1));
		add(new Section("Madrid", "http://estaticos.elmundo.es/elmundo/rss/madrid.xml", 1));
		add(new Section("M\u00E1laga", "http://estaticos.elmundo.es/elmundo/rss/andalucia_malaga.xml", 1));
		add(new Section("Sevilla", "http://estaticos.elmundo.es/elmundo/rss/andalucia_sevilla.xml", 1));
		add(new Section("Valencia", "http://estaticos.elmundo.es/elmundo/rss/valencia.xml", 1));

		add(new Section("Regiones", null, -1));
		add(new Section("Pa\u00EDs Vasco", "http://estaticos.elmundo.es/elmundo/rss/paisvasco.xml", 1));
		add(new Section("Andaluc\u00EDa", "http://estaticos.elmundo.es/elmundo/rss/andalucia.xml", 1));

		add(new Section("Deportes", "http://estaticos.elmundo.es/elmundodeporte/rss/portada.xml", 0));
		add(new Section("F\u00FAtbol", "http://estaticos.elmundo.es/elmundodeporte/rss/futbol.xml", 1));
		add(new Section("Baloncesto", "http://estaticos.elmundo.es/elmundodeporte/rss/baloncesto.xml", 1));
		add(new Section("Ciclismo", "http://estaticos.elmundo.es/elmundodeporte/rss/ciclismo.xml", 1));
		add(new Section("Tenis", "http://estaticos.elmundo.es/elmundodeporte/rss/tenis.xml", 1));

		add(new Section("Comunicaci\u00F3n", "http://estaticos.elmundo.es/elmundo/rss/comunicacion.xml", 0));
		add(new Section("Televisi\u00F3n", "http://estaticos.elmundo.es/elmundo/rss/television.xml", 0));
		add(new Section("Su Vivienda", "http://estaticos.elmundo.es/elmundo/rss/suvivienda.xml", 0));
		add(new Section("Motor", "http://estaticos.elmundo.es/elmundomotor/rss/portada.xml", 0));
		add(new Section("Yo Dona", "http://estaticos.elmundo.es/yodona/rss/portada.xml", 0));

		add(new Section("El Navegante", "http://estaticos.elmundo.es/elmundo/rss/navegante.xml", 0));
		add(new Section("Gadgetoblog", "http://estaticos.elmundo.es/blogs/elmundo/el-gadgetoblog/index.xml", 1));

		add(new Section("Blogs", null, -1));
		add(new Section("Dragolandia", "http://estaticos.elmundo.es/blogs/elmundo/dragolandia/index.xml", 1));
		add(new Section("El blog de Santiago Gonz\u00E1lez", "http://estaticos.elmundo.es/blogs/elmundo/elblogdesantiagogonzalez/index.xml", 1));
		add(new Section("El mundo por dentro y por fuera", "http://estaticos.elmundo.es/blogs/elmundo/elmundopordentro/index.xml", 1));
		add(new Section("Asesino en serie", "http://estaticos.elmundo.es/blogs/elmundo/asesinoenserie/index.xml", 1));
		add(new Section("Coraz\u00F3n de mel\u00F3n", "http://estaticos.elmundo.es/blogs/elmundo/corazondemelon/index.xml", 1));
		add(new Section("Sin noticias de Dior", "http://estaticos.elmundo.es/blogs/elmundo/sinnoticiasdedior/index.xml", 1));
		add(new Section("Tierra", "http://estaticos.elmundo.es/blogs/elmundo/tierra/index.xml", 1));

	}

}

