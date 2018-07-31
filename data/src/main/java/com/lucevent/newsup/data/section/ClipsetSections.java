package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class ClipsetSections extends Sections {

	public ClipsetSections()
	{
		super();

		add(new Section("Principal", "https://clipset.20minutos.es/feed/", 0));

		add(new Section("Actualidad", "https://clipset.20minutos.es/categoria/actualidad/feed/", 0));
		add(new Section("Video", "https://clipset.20minutos.es/categoria/video/feed/", 0));

		add(new Section("Tecnolog\u00EDa", null, -1));
		add(new Section("Gadgets", "https://clipset.20minutos.es/categoria/tecnologia/gadgets/feed/", 1));
		add(new Section("Futuro", "https://clipset.20minutos.es/categoria/tecnologia/futuro-general/feed/", 1));
		add(new Section("Internet", "https://clipset.20minutos.es/categoria/tecnologia/internet/feed/", 1));
		add(new Section("M\u00F3viles", "https://clipset.20minutos.es/categoria/tecnologia/moviles-general-2/feed/", 1));
		add(new Section("Imagen", "https://clipset.20minutos.es/categoria/tecnologia/audiovideo/feed/", 1));

		add(new Section("Inclasificable", "https://clipset.20minutos.es/categoria/inclasificable/feed/", 0));

		add(new Section("Ocio", "https://clipset.20minutos.es/categoria/ocio/feed/", 0));
		add(new Section("Pel\u00EDculas", "https://clipset.20minutos.es/categoria/ocio/peliculas/feed/", 1));
		add(new Section("Videojuegos", "https://clipset.20minutos.es/categoria/ocio/videojuegos/feed/", 1));

	}

}
