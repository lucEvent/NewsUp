package com.backend.net;

import com.backend.kernel.BE_Enclosure;
import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_YleNewsReader extends BE_NewsReader {

    public BE_YleNewsReader() {
        super(true);

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Pääuutiset", "http://yle.fi/uutiset/rss/paauutiset.rss"));
        SECTIONS.add(new BE_Section("Tuoreimmat uutiset", "http://yle.fi/uutiset/rss/uutiset.rss"));
        SECTIONS.add(new BE_Section("Luetuimmat uutiset", "http://yle.fi/uutiset/rss/luetuimmat.rss"));

        SECTIONS.add(new BE_Section("Kotimaa", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=kotimaa"));
        SECTIONS.add(new BE_Section("Ulkomaat", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=ulkomaat"));
        SECTIONS.add(new BE_Section("Talous", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=talous"));
        SECTIONS.add(new BE_Section("Politiikka", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=politiikka"));
        SECTIONS.add(new BE_Section("Kulttuuri", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=kulttuuri"));
        SECTIONS.add(new BE_Section("Viihde", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=viihde"));
        SECTIONS.add(new BE_Section("Tiede", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=tiede"));
        SECTIONS.add(new BE_Section("Luonto", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=luonto"));
        SECTIONS.add(new BE_Section("Terveys", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=terveys"));
        SECTIONS.add(new BE_Section("Media", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=media"));
        SECTIONS.add(new BE_Section("Liikenne", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=liikenne"));
        SECTIONS.add(new BE_Section("Ilmiöt", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=ilmiot"));
        SECTIONS.add(new BE_Section("Internet", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=internet"));
        SECTIONS.add(new BE_Section("Pelit", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=pelit"));
        SECTIONS.add(new BE_Section("Näkökulmat", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=nakokulmat"));
        SECTIONS.add(new BE_Section("Blogit", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=Blogi"));
        SECTIONS.add(new BE_Section("Plus", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=Plus"));
        SECTIONS.add(new BE_Section("Ilmasto", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=ilmasto"));

        SECTIONS.add(new BE_Section("Alueet", null));
        SECTIONS.add(new BE_Section("Etelä-Karjala", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=etela-karjala"));
        SECTIONS.add(new BE_Section("Etelä-Savo", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=etela-savo"));
        SECTIONS.add(new BE_Section("Helsinki", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=yle-helsinki"));
        SECTIONS.add(new BE_Section("Häme", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=hame"));
        SECTIONS.add(new BE_Section("Kainuu", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=kainuu"));
        SECTIONS.add(new BE_Section("Keski-Pohjanmaa", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=keski-pohjanmaa"));
        SECTIONS.add(new BE_Section("Keski-Suomi", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=keski-suomi"));
        SECTIONS.add(new BE_Section("Kymenlaakso", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=kymenlaakso"));
        SECTIONS.add(new BE_Section("Lahti", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=lahti"));
        SECTIONS.add(new BE_Section("Lappi", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=lappi"));
        SECTIONS.add(new BE_Section("Oulu", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=oulu"));
        SECTIONS.add(new BE_Section("Perämeri", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=perameri"));
        SECTIONS.add(new BE_Section("Pohjanmaa", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=pohjanmaa"));
        SECTIONS.add(new BE_Section("Pohjois-Karjala", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=pohjois-karjala"));
        SECTIONS.add(new BE_Section("Satakunta", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=satakunta"));
        SECTIONS.add(new BE_Section("Savo", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=savo"));
        SECTIONS.add(new BE_Section("Tampere", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=tampere"));
        SECTIONS.add(new BE_Section("Turku", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=turku"));

        SECTIONS.add(new BE_Section("Urheilu", null));
        SECTIONS.add(new BE_Section("Pääuutiset", "http://yle.fi/urheilu/rss/paauutiset.rss"));
        SECTIONS.add(new BE_Section("Tuoreimmat uutiset", "http://yle.fi/urheilu/rss/uutiset.rss"));
        SECTIONS.add(new BE_Section("Luetuimmat uutiset", "http://yle.fi/urheilu/rss/luetuimmat.rss"));
        SECTIONS.add(new BE_Section("Alppihiihto", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=alppihiihto"));
        SECTIONS.add(new BE_Section("Ampumahiihto", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=ampumahiihto"));
        SECTIONS.add(new BE_Section("Ampuminen", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=ampuminen"));
        SECTIONS.add(new BE_Section("Formula 1", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=f1"));
        SECTIONS.add(new BE_Section("Golf", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=golf"));
        SECTIONS.add(new BE_Section("Hiihto", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=hiihto"));
        SECTIONS.add(new BE_Section("Jalkapallo", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=jalkapallo"));
        SECTIONS.add(new BE_Section("Jääkiekko", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=jaakiekko"));
        SECTIONS.add(new BE_Section("Koripallo", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=koripallo"));
        SECTIONS.add(new BE_Section("Lentopallo", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=lentopallo"));
        SECTIONS.add(new BE_Section("Lumilautailu", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=lumilautailu"));
        SECTIONS.add(new BE_Section("Moottoripyöräily", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=moottoripyoraily"));
        SECTIONS.add(new BE_Section("Muu palloilu", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=muu_palloilu"));
        SECTIONS.add(new BE_Section("Muu urheilu", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=muut"));
        SECTIONS.add(new BE_Section("Nyrkkeily", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=nyrkkeily"));
        SECTIONS.add(new BE_Section("Purjehdus", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=purjehdus"));
        SECTIONS.add(new BE_Section("Ralli", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=ralli"));
        SECTIONS.add(new BE_Section("Ratsastus", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=ratsastus"));
        SECTIONS.add(new BE_Section("Salibandy", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=salibandy"));
        SECTIONS.add(new BE_Section("Tennis", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=tennis"));
        SECTIONS.add(new BE_Section("Yleisurheilu", "http://yle.fi/urheilu/rss/uutiset.rss?osasto=yleisurheilu"));

        SECTIONS.add(new BE_Section("Selkouutiset", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=selkouutiset"));
        SECTIONS.add(new BE_Section("News in English", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=news"));
        SECTIONS.add(new BE_Section("Sápmi", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=sapmi"));
        SECTIONS.add(new BE_Section("Novosti", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=novosti"));
        SECTIONS.add(new BE_Section("Yle Uudizet karjalakse", "http://yle.fi/uutiset/rss/uutiset.rss?osasto=karjalakse"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        if (!content.isEmpty()) {
            if (org.jsoup.Jsoup.parseBodyFragment(content).text().length() != 0) {

                String img = "";
                for (BE_Enclosure e : news.enclosures) {
                    img += e.html();
                }
                news.content = "<meta charset=\"UTF-8\">" + img + content;
            }
        }
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
        org.jsoup.select.Elements e = doc.select(".text");

        if (!e.isEmpty()) {
            news.content = e.html();
        }
    }

}