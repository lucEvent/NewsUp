package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TopesDeGamaSections extends Sections {

	public TopesDeGamaSections()
	{
		super();

		add(new Section("Principal", "https://topesdegama.com/feed", 0));

		add(new Section("Noticias", "https://topesdegama.com/noticias/feed", 0));
		add(new Section("M\u00F3viles", "https://topesdegama.com/noticias/movil/feed", 0));
		add(new Section("Tablets", "https://topesdegama.com/noticias/tablet/feed", 0));
		add(new Section("Sorteos", "https://topesdegama.com/noticias/sorteos/feed", 0));

		add(new Section("An\u00E1lisis", "https://topesdegama.com/analisis/feed", 0));
		add(new Section("M\u00F3viles (An\u00E1lisis)", "https://topesdegama.com/analisis/moviles/feed", 1));
		add(new Section("Tablets (An\u00E1lisis)", "https://topesdegama.com/analisis/tablets/feed", 1));
		add(new Section("C\u00E1maras", "https://topesdegama.com/analisis/camaras/feed", 1));
		add(new Section("Audio", "https://topesdegama.com/analisis/audio/feed", 1));
		add(new Section("Port\u00E1tiles y convertibles", "https://topesdegama.com/analisis/portatiles-y-convertibles/feed", 1));
		add(new Section("Smart TV", "https://topesdegama.com/analisis/smart-tv/feed", 1));
		add(new Section("Wearables", "https://topesdegama.com/analisis/wearables/feed", 1));
		add(new Section("Gadgets y Perif\u00E9ricos", "https://topesdegama.com/analisis/gadgets-y-perifericos/feed", 1));

	}

}
