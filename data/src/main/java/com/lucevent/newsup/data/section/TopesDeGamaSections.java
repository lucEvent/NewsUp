package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TopesDeGamaSections extends Sections {

    public TopesDeGamaSections()
    {
        super();

        add(new Section("Principal", "http://topesdegama.com/feed", 0));

        add(new Section("Noticias", "http://topesdegama.com/noticias/feed", 0));
        add(new Section("M\u00F3viles", "http://topesdegama.com/noticias/movil/feed", 1));
        add(new Section("Tablets", "http://topesdegama.com/noticias/tablet/feed", 1));
        add(new Section("Sorteos", "http://topesdegama.com/noticias/sorteos/feed", 1));

        add(new Section("An\u00E1lisis", "http://topesdegama.com/analisis/feed", 0));
        add(new Section("M\u00F3viles", "http://topesdegama.com/analisis/moviles/feed", 1));
        add(new Section("Tablets", "http://topesdegama.com/analisis/tablets/feed", 1));
        add(new Section("C\u00E1maras", "http://topesdegama.com/analisis/camaras/feed", 1));
        add(new Section("Audio", "http://topesdegama.com/analisis/audio/feed", 1));
        add(new Section("Port\u00E1tiles y convertibles", "http://topesdegama.com/analisis/portatiles-y-convertibles/feed", 1));
        add(new Section("Smart TV", "http://topesdegama.com/analisis/smart-tv/feed", 1));
        add(new Section("Wearables", "http://topesdegama.com/analisis/wearables/feed", 1));
        add(new Section("Gadgets y Perif\u00E9ricos", "http://topesdegama.com/analisis/gadgets-y-perifericos/feed", 1));

    }

}
