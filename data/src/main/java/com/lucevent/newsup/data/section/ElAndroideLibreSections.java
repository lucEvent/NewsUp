package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class ElAndroideLibreSections extends Sections {

	public ElAndroideLibreSections()
	{
		super();

		add(new Section("Principal", "https://elandroidelibre.elespanol.com/feed", 0));
		add(new Section("Aplicaciones", "https://elandroidelibre.elespanol.com/category/aplicaciones/feed", 0));
		add(new Section("Juegos", "https://elandroidelibre.elespanol.com/category/juegos/feed", 0));
		add(new Section("M\u00F3viles", "https://elandroidelibre.elespanol.com/category/moviles/feed", 0));
		add(new Section("Tablets", "https://elandroidelibre.elespanol.com/category/tablets/feed", 0));
		add(new Section("Trucos y curiosidades", "https://elandroidelibre.elespanol.com/category/trucos-y-curiosidades/feed", 0));
		add(new Section("Noticias y novedades", "https://elandroidelibre.elespanol.com/category/noticias-y-novedades/feed", 0));

	}

}
