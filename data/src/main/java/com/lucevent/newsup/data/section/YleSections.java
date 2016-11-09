package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class YleSections extends Sections {

    public YleSections()
    {
        super();

        add(new Section("P\u00e4\u00e4uutiset", "http://yle.fi/uutiset/rss/paauutiset.rss", 0));
        add(new Section("Tuoreimmat uutiset", "http://yle.fi/uutiset/rss/uutiset.rss", 0));
        add(new Section("Luetuimmat uutiset", "http://yle.fi/uutiset/rss/luetuimmat.rss", 0));

        add(new Section("Kotimaa", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=kotimaa", 0));
        add(new Section("Ulkomaat", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=ulkomaat", 0));
        add(new Section("Talous", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=talous", 0));
        add(new Section("Politiikka", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=politiikka", 0));
        add(new Section("Kulttuuri", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=kulttuuri", 0));
        add(new Section("Viihde", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=viihde", 0));
        add(new Section("Tiede", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=tiede", 0));
        add(new Section("Luonto", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=luonto", 0));
        add(new Section("Terveys", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=terveys", 0));
        add(new Section("Media", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=media", 0));
        add(new Section("Liikenne", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=liikenne", 0));
        add(new Section("Ilmi\u00f6t", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=ilmiot", 0));
        add(new Section("Internet", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=internet", 0));
        add(new Section("Pelit", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=pelit", 0));
        add(new Section("N\u00e4k\u00f6kulmat", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=nakokulmat", 0));
        add(new Section("Blogit", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=Blogi", 0));
        add(new Section("Ilmasto", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=ilmasto", 0));

        add(new Section("Alueet", null, -1));
        add(new Section("Etel\u00e4-Karjala", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=etela-karjala", 1));
        add(new Section("Etel\u00e4-Savo", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=etela-savo", 1));
        add(new Section("Helsinki", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=yle-helsinki", 1));
        add(new Section("H\u00e4me", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=hame", 1));
        add(new Section("Kainuu", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=kainuu", 1));
        add(new Section("Keski-Pohjanmaa", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=keski-pohjanmaa", 1));
        add(new Section("Keski-Suomi", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=keski-suomi", 1));
        add(new Section("Kymenlaakso", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=kymenlaakso", 1));
        add(new Section("Lahti", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=lahti", 1));
        add(new Section("Lappi", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=lappi", 1));
        add(new Section("Oulu", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=oulu", 1));
        add(new Section("Per\u00e4meri", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=perameri", 1));
        add(new Section("Pohjanmaa", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=pohjanmaa", 1));
        add(new Section("Pohjois-Karjala", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=pohjois-karjala", 1));
        add(new Section("Satakunta", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=satakunta", 1));
        add(new Section("Savo", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=savo", 1));
        add(new Section("Tampere", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=tampere", 1));
        add(new Section("Turku", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=turku", 1));

        add(new Section("Urheilu", null, -1));
        add(new Section("P\u00e4\u00e4uutiset", "http://yle.fi/urheilu/rss/paauutiset.rss", 1));
        add(new Section("Tuoreimmat uutiset", "http://yle.fi/urheilu/rss/uutiset.rss", 1));
        add(new Section("Alppihiihto", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=alppihiihto", 1));
        add(new Section("Ampumahiihto", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=ampumahiihto", 1));
        add(new Section("Formula 1", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=f1", 1));
        add(new Section("Golf", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=golf", 1));
        add(new Section("Hiihto", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=hiihto", 1));
        add(new Section("Jalkapallo", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=jalkapallo", 1));
        add(new Section("J\u00e4\u00e4kiekko", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=jaakiekko", 1));
        add(new Section("Koripallo", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=koripallo", 1));
        add(new Section("Lentopallo", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=lentopallo", 1));
        add(new Section("Lumilautailu", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=lumilautailu", 1));
        add(new Section("Moottoripy\u00f6r\u00e4ily", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=moottoripyoraily", 1));
        add(new Section("Nyrkkeily", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=nyrkkeily", 1));
        add(new Section("Purjehdus", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=purjehdus", 1));
        add(new Section("Ralli", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=ralli", 1));
        add(new Section("Ratsastus", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=ratsastus", 1));
        add(new Section("Salibandy", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=salibandy", 1));
        add(new Section("Tennis", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=tennis", 1));
        add(new Section("Yleisurheilu", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=yleisurheilu", 1));

        add(new Section("Selkouutiset", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=selkouutiset", 0));
        add(new Section("News in English", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=news", 0));
        add(new Section("S\u00e1pmi", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=sapmi", 0));
        add(new Section("Novosti", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=novosti", 0));

    }

}