package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class Andro4allSections extends Sections {

	public Andro4allSections()
	{
		super();

		add(new Section("Principal", "https://andro4all.com/feed", 0));
		add(new Section("Accesorios para m\u00F3vil", "https://andro4all.com/categoria/accesorios/feed", 0));
		add(new Section("Aplicaciones Android", "https://andro4all.com/categoria/aplicaciones/feed", 0));
		add(new Section("Marcas", "https://andro4all.com/categoria/marca/feed", 0));
		add(new Section("Operadoras", "https://andro4all.com/categoria/operadoras/feed", 0));
		add(new Section("Personalizaci\u00F3n", "https://andro4all.com/categoria/personalizacion/feed", 0));
		add(new Section("Recopilaciones y comparativas", "https://andro4all.com/categoria/recopilaciones/feed", 0));
		add(new Section("Sistema Operativo", "https://andro4all.com/categoria/android-os/feed", 0));
		add(new Section("Tecnolog\u00EDa", "https://andro4all.com/categoria/tecnologia/feed", 0));

	}

}
