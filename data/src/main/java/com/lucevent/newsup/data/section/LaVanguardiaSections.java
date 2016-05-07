package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class LaVanguardiaSections extends Sections {

    public LaVanguardiaSections() {
        super();

        add(new Section("Principal", "http://www.lavanguardia.com/mvc/feed/rss/home", 0));
        add(new Section("Internacional", "http://www.lavanguardia.com/mvc/feed/rss/internacional", 0));
        add(new Section("Política", "http://www.lavanguardia.com/mvc/feed/rss/politica", 0));
        add(new Section("Vida", "http://www.lavanguardia.com/mvc/feed/rss/vida", 0));
        add(new Section("Deportes", "http://www.lavanguardia.com/mvc/feed/rss/deportes", 0));
        add(new Section("Economía", "http://www.lavanguardia.com/mvc/feed/rss/economia", 0));
        add(new Section("Opinión", "http://www.lavanguardia.com/mvc/feed/rss/opinion", 0));
        add(new Section("Cultura", "http://www.lavanguardia.com/mvc/feed/rss/cultura", 0));
        add(new Section("Gente", "http://www.lavanguardia.com/mvc/feed/rss/gente", 0));
        add(new Section("Participación", "http://www.lavanguardia.com/mvc/feed/rss/participacion", 0));
        add(new Section("Vídeos", "http://www.lavanguardia.com/mvc/feed/rss/videos", 0));
        add(new Section("Temas", "http://www.lavanguardia.com/mvc/feed/rss/temas", 0));

        add(new Section("Canales", null, -1));
        add(new Section("VangData", "http://www.lavanguardia.com/mvc/feed/rss/vangdata", 1));
        add(new Section("Natural", "http://www.lavanguardia.com/mvc/feed/rss/vida/natural", 1));
        add(new Section("Big Vang", "http://www.lavanguardia.com/mvc/feed/rss/ciencia", 1));
        add(new Section("Salud", "http://www.lavanguardia.com/mvc/feed/rss/vida/salud", 1));
        add(new Section("Tecnología", "http://www.lavanguardia.com/mvc/feed/rss/tecnologia", 1));
        add(new Section("Televisión", "http://www.lavanguardia.com/mvc/feed/rss/ocio/television", 1));
        add(new Section("Series", "http://www.lavanguardia.com/mvc/feed/rss/ocio/series", 1));
        add(new Section("Flípalo", "http://www.lavanguardia.com/mvc/feed/rss/gente/fans", 1));
        add(new Section("Motor", "http://www.lavanguardia.com/mvc/feed/rss/ocio/motor", 1));
        add(new Section("De Moda", "http://www.lavanguardia.com/mvc/feed/rss/de-moda", 1));
        add(new Section("Vivo", "http://www.lavanguardia.com/mvc/feed/rss/vivo", 1));

        add(new Section("Ediciones Locales", null, -1));
        add(new Section("Barcelona", "http://www.lavanguardia.com/mvc/feed/rss/local/barcelona", 1));
        add(new Section("Tarragona", "http://www.lavanguardia.com/mvc/feed/rss/local/tarragona", 1));
        add(new Section("Lleida", "http://www.lavanguardia.com/mvc/feed/rss/local/lleida", 1));
        add(new Section("Girona", "http://www.lavanguardia.com/mvc/feed/rss/local/girona", 1));
        add(new Section("Madrid", "http://www.lavanguardia.com/mvc/feed/rss/local/madrid", 1));
        add(new Section("Andalucía", "http://www.lavanguardia.com/mvc/feed/rss/local/sevilla", 1));
        add(new Section("Comunidad Valenciana", "http://www.lavanguardia.com/mvc/feed/rss/local/valencia", 1));
        add(new Section("País Vasco", "http://www.lavanguardia.com/mvc/feed/rss/local/paisvasco", 1));

        add(new Section("Corresponsales", null, -1));
        add(new Section("Beirut: Tomás Alcoverro", "http://feeds.feedburner.com/lavanguardia/diariodebeirut", 1));
        add(new Section("India: Jordi Joan Baños", "http://feeds.feedburner.com/lavanguardia/diariodeindia", 1));
        add(new Section("Diario itinerante: Andy Robinson", "http://feeds.feedburner.com/lavanguardia/diarioitinerante", 1));

        add(new Section("Blogs", null, -1));
        add(new Section("Ailof", "http://feeds.feedburner.com/lavanguardia/ailof", 1));
        add(new Section("Desorientado en Oriente", "http://feeds.feedburner.com/lavanguardia/desorientadoenoriente", 1));
        add(new Section("El arquero", "http://feeds.feedburner.com/lavanguardia/elarquero", 1));
        add(new Section("El Dardo", "http://feeds.feedburner.com/lavanguardia/eldardo", 1));
        add(new Section("El otro escaño", "http://feeds.feedburner.com/lavanguardia/elotroescano", 1));
        add(new Section("Metamorfosis", "http://feeds.feedburner.com/lavanguardia/metamorfosis", 1));
        add(new Section("No digas que se te mueren las plantas", "http://feeds.feedburner.com/lavanguardia/plantas", 1));
        add(new Section("Pasos perdidos", "http://feeds.feedburner.com/lavanguardia/pasosperdidos", 1));
        add(new Section("Teclado Móvil", "http://feeds.feedburner.com/lavanguardia/tecladomovil", 1));
        add(new Section("The Fourth Bit", "http://feeds.feedburner.com/lavanguardia/thefourthbit", 1));
        add(new Section("Diario de Valencia", "http://feeds.feedburner.com/lavanguardia/diariodevalencia", 1));

    }

}
