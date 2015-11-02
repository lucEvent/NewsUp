package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.util.Enclosure;

public class YleNewsReader extends NewsReader {

    public YleNewsReader() {
        super(true);

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Pääuutiset", 0, "http://yle.fi/uutiset/rss/paauutiset.rss"));
        SECTIONS.add(new Section("Tuoreimmat uutiset", 0, "http://yle.fi/uutiset/rss/uutiset.rss"));
        SECTIONS.add(new Section("Luetuimmat uutiset", 0, "http://yle.fi/uutiset/rss/luetuimmat.rss"));

        SECTIONS.add(new Section("Kotimaa", 0, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=kotimaa"));
        SECTIONS.add(new Section("Ulkomaat", 0, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=ulkomaat"));
        SECTIONS.add(new Section("Talous", 0, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=talous"));
        SECTIONS.add(new Section("Politiikka", 0, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=politiikka"));
        SECTIONS.add(new Section("Kulttuuri", 0, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=kulttuuri"));
        SECTIONS.add(new Section("Viihde", 0, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=viihde"));
        SECTIONS.add(new Section("Tiede", 0, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=tiede"));
        SECTIONS.add(new Section("Luonto", 0, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=luonto"));
        SECTIONS.add(new Section("Terveys", 0, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=terveys"));
        SECTIONS.add(new Section("Media", 0, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=media"));
        SECTIONS.add(new Section("Liikenne", 0, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=liikenne"));
        SECTIONS.add(new Section("Ilmiöt", 0, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=ilmiot"));
        SECTIONS.add(new Section("Internet", 0, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=internet"));
        SECTIONS.add(new Section("Pelit", 0, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=pelit"));
        SECTIONS.add(new Section("Näkökulmat", 0, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=nakokulmat"));
        SECTIONS.add(new Section("Blogit", 0, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=Blogi"));
        SECTIONS.add(new Section("Plus", 0, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=Plus"));
        SECTIONS.add(new Section("Ilmasto", 0, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=ilmasto"));

        SECTIONS.add(new Section("Alueet", 0, null));
        SECTIONS.add(new Section("Etelä-Karjala", 1, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=etela-karjala"));
        SECTIONS.add(new Section("Etelä-Savo", 1, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=etela-savo"));
        SECTIONS.add(new Section("Helsinki", 1, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=yle-helsinki"));
        SECTIONS.add(new Section("Häme", 1, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=hame"));
        SECTIONS.add(new Section("Kainuu", 1, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=kainuu"));
        SECTIONS.add(new Section("Keski-Pohjanmaa", 1, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=keski-pohjanmaa"));
        SECTIONS.add(new Section("Keski-Suomi", 1, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=keski-suomi"));
        SECTIONS.add(new Section("Kymenlaakso", 1, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=kymenlaakso"));
        SECTIONS.add(new Section("Lahti", 1, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=lahti"));
        SECTIONS.add(new Section("Lappi", 1, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=lappi"));
        SECTIONS.add(new Section("Oulu", 1, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=oulu"));
        SECTIONS.add(new Section("Perämeri", 1, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=perameri"));
        SECTIONS.add(new Section("Pohjanmaa", 1, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=pohjanmaa"));
        SECTIONS.add(new Section("Pohjois-Karjala", 1, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=pohjois-karjala"));
        SECTIONS.add(new Section("Satakunta", 1, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=satakunta"));
        SECTIONS.add(new Section("Savo", 1, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=savo"));
        SECTIONS.add(new Section("Tampere", 1, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=tampere"));
        SECTIONS.add(new Section("Turku", 1, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=turku"));

        SECTIONS.add(new Section("Urheilu", 0, null));
        SECTIONS.add(new Section("Pääuutiset", 1, "http://yle.fi/urheilu/rss/paauutiset.rss"));
        SECTIONS.add(new Section("Tuoreimmat uutiset", 1, "http://yle.fi/urheilu/rss/uutiset.rss"));
        SECTIONS.add(new Section("Luetuimmat uutiset", 1, "http://yle.fi/urheilu/rss/luetuimmat.rss"));
        SECTIONS.add(new Section("Alppihiihto", 1, "http://yle.fi/urheilu/rss/uutiset.rss?osasto=alppihiihto"));
        SECTIONS.add(new Section("Ampumahiihto", 1, "http://yle.fi/urheilu/rss/uutiset.rss?osasto=ampumahiihto"));
        SECTIONS.add(new Section("Ampuminen", 1, "http://yle.fi/urheilu/rss/uutiset.rss?osasto=ampuminen"));
        SECTIONS.add(new Section("Formula 1", 1, "http://yle.fi/urheilu/rss/uutiset.rss?osasto=f1"));
        SECTIONS.add(new Section("Golf", 1, "http://yle.fi/urheilu/rss/uutiset.rss?osasto=golf"));
        SECTIONS.add(new Section("Hiihto", 1, "http://yle.fi/urheilu/rss/uutiset.rss?osasto=hiihto"));
        SECTIONS.add(new Section("Jalkapallo", 1, "http://yle.fi/urheilu/rss/uutiset.rss?osasto=jalkapallo"));
        SECTIONS.add(new Section("Jääkiekko", 1, "http://yle.fi/urheilu/rss/uutiset.rss?osasto=jaakiekko"));
        SECTIONS.add(new Section("Koripallo", 1, "http://yle.fi/urheilu/rss/uutiset.rss?osasto=koripallo"));
        SECTIONS.add(new Section("Lentopallo", 1, "http://yle.fi/urheilu/rss/uutiset.rss?osasto=lentopallo"));
        SECTIONS.add(new Section("Lumilautailu", 1, "http://yle.fi/urheilu/rss/uutiset.rss?osasto=lumilautailu"));
        SECTIONS.add(new Section("Moottoripyöräily", 1, "http://yle.fi/urheilu/rss/uutiset.rss?osasto=moottoripyoraily"));
        SECTIONS.add(new Section("Muu palloilu", 1, "http://yle.fi/urheilu/rss/uutiset.rss?osasto=muu_palloilu"));
        SECTIONS.add(new Section("Muu urheilu", 1, "http://yle.fi/urheilu/rss/uutiset.rss?osasto=muut"));
        SECTIONS.add(new Section("Nyrkkeily", 1, "http://yle.fi/urheilu/rss/uutiset.rss?osasto=nyrkkeily"));
        SECTIONS.add(new Section("Purjehdus", 1, "http://yle.fi/urheilu/rss/uutiset.rss?osasto=purjehdus"));
        SECTIONS.add(new Section("Ralli", 1, "http://yle.fi/urheilu/rss/uutiset.rss?osasto=ralli"));
        SECTIONS.add(new Section("Ratsastus", 1, "http://yle.fi/urheilu/rss/uutiset.rss?osasto=ratsastus"));
        SECTIONS.add(new Section("Salibandy", 1, "http://yle.fi/urheilu/rss/uutiset.rss?osasto=salibandy"));
        SECTIONS.add(new Section("Tennis", 1, "http://yle.fi/urheilu/rss/uutiset.rss?osasto=tennis"));
        SECTIONS.add(new Section("Yleisurheilu", 1, "http://yle.fi/urheilu/rss/uutiset.rss?osasto=yleisurheilu"));

        SECTIONS.add(new Section("Selkouutiset", 0, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=selkouutiset"));
        SECTIONS.add(new Section("News in English", 0, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=news"));
        SECTIONS.add(new Section("Sápmi", 0, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=sapmi"));
        SECTIONS.add(new Section("Novosti", 0, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=novosti"));
        SECTIONS.add(new Section("Yle Uudizet karjalakse", 0, "http://yle.fi/uutiset/rss/uutiset.rss?osasto=karjalakse"));

    }

    @Override
    protected News applySpecialCase(News news, String content) {
        if (!content.isEmpty()) {
            if (org.jsoup.Jsoup.parseBodyFragment(content).text().length() != 0) {

                String img = "";
                for (Enclosure e : news.enclosures) {
                    img += e.html();
                }
                news.content = "<meta charset=\"UTF-8\">" + img + content;
            }
        }
        return news;
    }

    @Override
    public News readNewsContent(News news) {
        org.jsoup.nodes.Document doc = getDocument(news.link);
        if (doc == null) return news;

        org.jsoup.select.Elements e = doc.select(".text");

        if (!e.isEmpty()) {
            news.content = e.html();
        } else {
            debug("NO SE HA PODIDO LEER EL CONTENIDO: " + news.link);
        }
        return news;
    }

}