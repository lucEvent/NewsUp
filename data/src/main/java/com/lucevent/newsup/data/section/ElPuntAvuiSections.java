package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class ElPuntAvuiSections extends Sections {

	public ElPuntAvuiSections()
	{
		super();

		add(new Section("Portada", "http://www.elpuntavui.cat/barcelona.feed?type=rss", 0));
		add(new Section("Territori", "http://www.elpuntavui.cat/territori.feed?type=rss", 0));
		add(new Section("Societat", "http://www.elpuntavui.cat/societat.feed?type=rss", 0));
		add(new Section("Punt divers", "http://www.elpuntavui.cat/punt-divers.feed?type=rss", 0));
		add(new Section("Pol\u00EDtica", "http://www.elpuntavui.cat/politica.feed?type=rss", 0));
		add(new Section("Economia", "http://www.elpuntavui.cat/economia.feed?type=rss", 0));
		add(new Section("Cultura", "http://www.elpuntavui.cat/cultura.feed?type=rss", 0));
		add(new Section("Comunicaci\u00F3", "http://www.elpuntavui.cat/comunicacio.feed?type=rss", 0));
		add(new Section("Opini\u00F3", "http://www.elpuntavui.cat/opinio.feed?type=rss", 0));
		add(new Section("Esports", "http://www.elpuntavui.cat/esports.feed?type=rss", 0));

		add(new Section("Local", null, -1));
		add(new Section("Barcelona", "http://www.elpuntavui.cat/barcelona.feed?type=rss", 1));
		add(new Section("Girona", "http://www.elpuntavui.cat/girona.feed?type=rss", 1));
		add(new Section("Lleida", "http://www.elpuntavui.cat/lleida.feed?type=rss", 1));
		add(new Section("Tarragona", "http://www.elpuntavui.cat/tarragona.feed?type=rss", 1));
		add(new Section("Reus", "http://www.elpuntavui.cat/reus.feed?type=rss", 1));
		add(new Section("Vic", "http://www.elpuntavui.cat/vic.feed?type=rss", 1));

		add(new Section("In English", "http://www.elpuntavui.cat/canals/generes/catalonia-today.feed?type=rss", 0));

	}

}
