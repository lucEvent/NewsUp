package com.newsup.net.dev;

import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.NewsReader;

public class IltaSanomatNewsReader extends NewsReader {

    public IltaSanomatNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Tuoreimmat uutisotsikot", 0, "http://www.iltasanomat.fi/rss/tuoreimmat.xml"));
        SECTIONS.add(new Section("Kotimaa", 0, "http://www.iltasanomat.fi/rss/kotimaa.xml"));
        SECTIONS.add(new Section("Ulkomaat", 0, "http://www.iltasanomat.fi/rss/ulkomaat.xml"));

        SECTIONS.add(new Section("Urheilu", 0, null));
        SECTIONS.add(new Section("Tuoreimmat urheiluotsikot", 1, "http://www.iltasanomat.fi/rss/urheilu.xml"));
        SECTIONS.add(new Section("JÃ¤Ã¤kiekko", 1, "http://www.iltasanomat.fi/rss/jaakiekko.xml"));
        SECTIONS.add(new Section("SM-liiga", 1, "http://www.iltasanomat.fi/rss/sm-liiga.xml"));
        SECTIONS.add(new Section("NHL", 1, "http://www.iltasanomat.fi/rss/nhl.xml"));
        SECTIONS.add(new Section("Elitserien", 1, "http://www.iltasanomat.fi/rss/elitserien.xml"));
        SECTIONS.add(new Section("KHL", 1, "http://www.iltasanomat.fi/rss/khl.xml"));
        SECTIONS.add(new Section("JÃ¤Ã¤kiekon MM-kisat", 1, "http://www.iltasanomat.fi/rss/mmkiekko.xml"));
        SECTIONS.add(new Section("Jalkapallo", 1, "http://www.iltasanomat.fi/rss/jalkapallo.xml"));
        SECTIONS.add(new Section("Mestarien liiga", 1, "http://www.iltasanomat.fi/rss/mestarienliiga.xml"));
        SECTIONS.add(new Section("Veikkausliiga", 1, "http://www.iltasanomat.fi/rss/veikkausliiga.xml"));
        SECTIONS.add(new Section("Valioliiga", 1, "http://www.iltasanomat.fi/rss/valioliiga.xml"));
        SECTIONS.add(new Section("Allsvenskan", 1, "http://www.iltasanomat.fi/rss/allsvenskan.xml"));
        SECTIONS.add(new Section("Huuhkajat", 1, "http://www.iltasanomat.fi/rss/huuhkajat.xml"));
        SECTIONS.add(new Section("Hiihtolajit", 1, "http://www.iltasanomat.fi/rss/hiihtolajit.xml"));
        SECTIONS.add(new Section("MÃ¤kihyppy", 1, "http://www.iltasanomat.fi/rss/makihyppy.xml"));
        SECTIONS.add(new Section("Maastohiihto", 1, "http://www.iltasanomat.fi/rss/maastohiihto.xml"));
        SECTIONS.add(new Section("Yhdistetty", 1, "http://www.iltasanomat.fi/rss/yhdistetty.xml"));
        SECTIONS.add(new Section("Ampumahiihto", 1, "http://www.iltasanomat.fi/rss/ampumahiihto.xml"));
        SECTIONS.add(new Section("Alppihiihto", 1, "http://www.iltasanomat.fi/rss/alppihiihto.xml"));
        SECTIONS.add(new Section("Lumilautailu", 1, "http://www.iltasanomat.fi/rss/lumilautailu.xml"));
        SECTIONS.add(new Section("Freestyle", 1, "http://www.iltasanomat.fi/rss/freestyle.xml"));
        SECTIONS.add(new Section("Taitoluistelu", 1, "http://www.iltasanomat.fi/rss/taitoluistelu.xml"));
        SECTIONS.add(new Section("Formula 1", 1, "http://www.iltasanomat.fi/rss/formula1.xml"));
        SECTIONS.add(new Section("Ralli", 1, "http://www.iltasanomat.fi/rss/ralli.xml"));
        SECTIONS.add(new Section("MoottoripyÃ¶rÃ¤ily", 1, "http://www.iltasanomat.fi/rss/moottoripyoraily.xml"));
        SECTIONS.add(new Section("Yleisurheilu", 1, "http://www.iltasanomat.fi/rss/yleisurheilu.xml"));
        SECTIONS.add(new Section("Uinti", 1, "http://www.iltasanomat.fi/rss/uinti.xml"));
        SECTIONS.add(new Section("Koripallo", 1, "http://www.iltasanomat.fi/rss/koripallo.xml"));
        SECTIONS.add(new Section("Lentopallo", 1, "http://www.iltasanomat.fi/rss/lentopallo.xml"));
        SECTIONS.add(new Section("Salibandy", 1, "http://www.iltasanomat.fi/rss/salibandy.xml"));
        SECTIONS.add(new Section("Tennis", 1, "http://www.iltasanomat.fi/rss/tennis.xml"));
        SECTIONS.add(new Section("PesÃ¤pallo", 1, "http://www.iltasanomat.fi/rss/pesapallo.xml"));
        SECTIONS.add(new Section("Purjehdus", 1, "http://www.iltasanomat.fi/rss/purjehdus.xml"));
        SECTIONS.add(new Section("Nyrkkeily", 1, "http://www.iltasanomat.fi/rss/nyrkkeily.xml"));
        SECTIONS.add(new Section("Golf", 1, "http://www.iltasanomat.fi/rss/golf.xml"));
        SECTIONS.add(new Section("Ravit", 1, "http://www.iltasanomat.fi/rss/ravit.xml"));

        SECTIONS.add(new Section("Viihde", 0, null));
        SECTIONS.add(new Section("Viihdeuutiset", 1, "http://www.iltasanomat.fi/rss/viihde.xml"));
        SECTIONS.add(new Section("Elokuvat", 1, "http://www.iltasanomat.fi/rss/elokuvat.xml"));
        SECTIONS.add(new Section("Kuninkaalliset", 1, "http://www.iltasanomat.fi/rss/kuninkaalliset.xml"));
        SECTIONS.add(new Section("Big Brother", 1, "http://www.iltasanomat.fi/rss/bigbrother.xml"));
        SECTIONS.add(new Section("Miss Suomi", 1, "http://www.iltasanomat.fi/rss/miss-suomi.xml"));
        SECTIONS.add(new Section("Linnan juhlat", 1, "http://www.iltasanomat.fi/rss/linnanjuhlat.xml"));
        SECTIONS.add(new Section("Euroviisut", 1, "http://www.iltasanomat.fi/rss/euroviisut.xml"));
        SECTIONS.add(new Section("Idols", 1, "http://www.iltasanomat.fi/rss/idols.xml"));
        SECTIONS.add(new Section("Tanssii tÃ¤htien kanssa", 1, "http://www.iltasanomat.fi/rss/tanssiitahtienkanssa.xml"));
        SECTIONS.add(new Section("Diili", 1, "http://www.iltasanomat.fi/rss/diili.xml"));

        SECTIONS.add(new Section("Lifestyle", 0, null));
        SECTIONS.add(new Section("HyvÃ¤ olo", 1, "http://www.iltasanomat.fi/rss/hyvaolo.xml"));
        SECTIONS.add(new Section("Muoti & Kauneus", 1, "http://www.iltasanomat.fi/rss/muotikauneus.xml"));
        SECTIONS.add(new Section("Autot", 1, "http://www.iltasanomat.fi/rss/autot.xml"));
        SECTIONS.add(new Section("Matkat", 1, "http://www.iltasanomat.fi/rss/matkat.xml"));
        SECTIONS.add(new Section("Asuminen", 1, "http://www.iltasanomat.fi/rss/asuminen.xml"));
        SECTIONS.add(new Section("Ruokala - uutiset", 1, "http://www.iltasanomat.fi/rss/ruokala/uutiset.xml"));
        SECTIONS.add(new Section("Ruokala - ajankohtaista", 1, "http://www.iltasanomat.fi/rss/ruokala/ajankohtaista.xml"));
        SECTIONS.add(new Section("Ruokala - reseptit", 1, "http://www.iltasanomat.fi/rss/ruokala/resepti.xml"));
        SECTIONS.add(new Section("Lemmikit", 1, "http://www.iltasanomat.fi/rss/lemmikit.xml"));

        SECTIONS.add(new Section("Videot", 0, null));
        SECTIONS.add(new Section("Uutisvideot", 1, "http://www.iltasanomat.fi/rss/videot/uutiset.xml"));
        SECTIONS.add(new Section("Viihdevideot", 1, "http://www.iltasanomat.fi/rss/videot/viihde.xml"));
        SECTIONS.add(new Section("Urheiluvideot", 1, "http://www.iltasanomat.fi/rss/videot/urheilu.xml"));
        SECTIONS.add(new Section("Autovideot", 1, "http://www.iltasanomat.fi/rss/videot/autot.xml"));
        SECTIONS.add(new Section("Asuntovideot", 1, "http://www.iltasanomat.fi/rss/videot/asuminen.xml"));
        SECTIONS.add(new Section("Matkavideot", 1, "http://www.iltasanomat.fi/rss/videot/matkat.xml"));
    }

}