package com.newsup.kernel.section;

import com.newsup.kernel.basic.Section;

import java.util.ArrayList;

public class YleSections extends ArrayList<Section> {

    public YleSections() {
        super();

        add(new Section("Pääuutiset", 0));
        add(new Section("Tuoreimmat uutiset", 0));
        add(new Section("Luetuimmat uutiset", 0));

        add(new Section("Kotimaa", 0));
        add(new Section("Ulkomaat", 0));
        add(new Section("Talous", 0));
        add(new Section("Politiikka", 0));
        add(new Section("Kulttuuri", 0));
        add(new Section("Viihde", 0));
        add(new Section("Tiede", 0));
        add(new Section("Luonto", 0));
        add(new Section("Terveys", 0));
        add(new Section("Media", 0));
        add(new Section("Liikenne", 0));
        add(new Section("Ilmiöt", 0));
        add(new Section("Internet", 0));
        add(new Section("Pelit", 0));
        add(new Section("Näkökulmat", 0));
        add(new Section("Blogit", 0));
        add(new Section("Plus", 0));
        add(new Section("Ilmasto", 0));

        add(new Section("Alueet", -1));
        add(new Section("Etelä-Karjala", 1));
        add(new Section("Etelä-Savo", 1));
        add(new Section("Helsinki", 1));
        add(new Section("Häme", 1));
        add(new Section("Kainuu", 1));
        add(new Section("Keski-Pohjanmaa", 1));
        add(new Section("Keski-Suomi", 1));
        add(new Section("Kymenlaakso", 1));
        add(new Section("Lahti", 1));
        add(new Section("Lappi", 1));
        add(new Section("Oulu", 1));
        add(new Section("Perämeri", 1));
        add(new Section("Pohjanmaa", 1));
        add(new Section("Pohjois-Karjala", 1));
        add(new Section("Satakunta", 1));
        add(new Section("Savo", 1));
        add(new Section("Tampere", 1));
        add(new Section("Turku", 1));

        add(new Section("Urheilu", -1));
        add(new Section("Pääuutiset", 1));
        add(new Section("Tuoreimmat uutiset", 1));
        add(new Section("Luetuimmat uutiset", 1));
        add(new Section("Alppihiihto", 1));
        add(new Section("Ampumahiihto", 1));
        add(new Section("Ampuminen", 1));
        add(new Section("Formula 1", 1));
        add(new Section("Golf", 1));
        add(new Section("Hiihto", 1));
        add(new Section("Jalkapallo", 1));
        add(new Section("Jääkiekko", 1));
        add(new Section("Koripallo", 1));
        add(new Section("Lentopallo", 1));
        add(new Section("Lumilautailu", 1));
        add(new Section("Moottoripyöräily", 1));
        add(new Section("Muu palloilu", 1));
        add(new Section("Muu urheilu", 1));
        add(new Section("Nyrkkeily", 1));
        add(new Section("Purjehdus", 1));
        add(new Section("Ralli", 1));
        add(new Section("Ratsastus", 1));
        add(new Section("Salibandy", 1));
        add(new Section("Tennis", 1));
        add(new Section("Yleisurheilu", 1));

        add(new Section("Selkouutiset", 0));
        add(new Section("News in English", 0));
        add(new Section("Sápmi", 0));
        add(new Section("Novosti", 0));
        add(new Section("Yle Uudizet karjalakse", 0));

    }

}