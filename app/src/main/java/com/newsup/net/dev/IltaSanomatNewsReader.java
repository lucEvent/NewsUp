package com.newsup.net.dev;

import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.NewsReader;

public class IltaSanomatNewsReader extends NewsReader {

    public IltaSanomatNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new SectionDeprecated("Tuoreimmat uutisotsikot", 0, "http://www.iltasanomat.fi/rss/tuoreimmat.xml"));
        SECTIONS.add(new SectionDeprecated("Kotimaa", 0, "http://www.iltasanomat.fi/rss/kotimaa.xml"));
        SECTIONS.add(new SectionDeprecated("Ulkomaat", 0, "http://www.iltasanomat.fi/rss/ulkomaat.xml"));

        SECTIONS.add(new SectionDeprecated("Urheilu", 0, null));
        SECTIONS.add(new SectionDeprecated("Tuoreimmat urheiluotsikot", 1, "http://www.iltasanomat.fi/rss/urheilu.xml"));
        SECTIONS.add(new SectionDeprecated("JÃ¤Ã¤kiekko", 1, "http://www.iltasanomat.fi/rss/jaakiekko.xml"));
        SECTIONS.add(new SectionDeprecated("SM-liiga", 1, "http://www.iltasanomat.fi/rss/sm-liiga.xml"));
        SECTIONS.add(new SectionDeprecated("NHL", 1, "http://www.iltasanomat.fi/rss/nhl.xml"));
        SECTIONS.add(new SectionDeprecated("Elitserien", 1, "http://www.iltasanomat.fi/rss/elitserien.xml"));
        SECTIONS.add(new SectionDeprecated("KHL", 1, "http://www.iltasanomat.fi/rss/khl.xml"));
        SECTIONS.add(new SectionDeprecated("JÃ¤Ã¤kiekon MM-kisat", 1, "http://www.iltasanomat.fi/rss/mmkiekko.xml"));
        SECTIONS.add(new SectionDeprecated("Jalkapallo", 1, "http://www.iltasanomat.fi/rss/jalkapallo.xml"));
        SECTIONS.add(new SectionDeprecated("Mestarien liiga", 1, "http://www.iltasanomat.fi/rss/mestarienliiga.xml"));
        SECTIONS.add(new SectionDeprecated("Veikkausliiga", 1, "http://www.iltasanomat.fi/rss/veikkausliiga.xml"));
        SECTIONS.add(new SectionDeprecated("Valioliiga", 1, "http://www.iltasanomat.fi/rss/valioliiga.xml"));
        SECTIONS.add(new SectionDeprecated("Allsvenskan", 1, "http://www.iltasanomat.fi/rss/allsvenskan.xml"));
        SECTIONS.add(new SectionDeprecated("Huuhkajat", 1, "http://www.iltasanomat.fi/rss/huuhkajat.xml"));
        SECTIONS.add(new SectionDeprecated("Hiihtolajit", 1, "http://www.iltasanomat.fi/rss/hiihtolajit.xml"));
        SECTIONS.add(new SectionDeprecated("MÃ¤kihyppy", 1, "http://www.iltasanomat.fi/rss/makihyppy.xml"));
        SECTIONS.add(new SectionDeprecated("Maastohiihto", 1, "http://www.iltasanomat.fi/rss/maastohiihto.xml"));
        SECTIONS.add(new SectionDeprecated("Yhdistetty", 1, "http://www.iltasanomat.fi/rss/yhdistetty.xml"));
        SECTIONS.add(new SectionDeprecated("Ampumahiihto", 1, "http://www.iltasanomat.fi/rss/ampumahiihto.xml"));
        SECTIONS.add(new SectionDeprecated("Alppihiihto", 1, "http://www.iltasanomat.fi/rss/alppihiihto.xml"));
        SECTIONS.add(new SectionDeprecated("Lumilautailu", 1, "http://www.iltasanomat.fi/rss/lumilautailu.xml"));
        SECTIONS.add(new SectionDeprecated("Freestyle", 1, "http://www.iltasanomat.fi/rss/freestyle.xml"));
        SECTIONS.add(new SectionDeprecated("Taitoluistelu", 1, "http://www.iltasanomat.fi/rss/taitoluistelu.xml"));
        SECTIONS.add(new SectionDeprecated("Formula 1", 1, "http://www.iltasanomat.fi/rss/formula1.xml"));
        SECTIONS.add(new SectionDeprecated("Ralli", 1, "http://www.iltasanomat.fi/rss/ralli.xml"));
        SECTIONS.add(new SectionDeprecated("MoottoripyÃ¶rÃ¤ily", 1, "http://www.iltasanomat.fi/rss/moottoripyoraily.xml"));
        SECTIONS.add(new SectionDeprecated("Yleisurheilu", 1, "http://www.iltasanomat.fi/rss/yleisurheilu.xml"));
        SECTIONS.add(new SectionDeprecated("Uinti", 1, "http://www.iltasanomat.fi/rss/uinti.xml"));
        SECTIONS.add(new SectionDeprecated("Koripallo", 1, "http://www.iltasanomat.fi/rss/koripallo.xml"));
        SECTIONS.add(new SectionDeprecated("Lentopallo", 1, "http://www.iltasanomat.fi/rss/lentopallo.xml"));
        SECTIONS.add(new SectionDeprecated("Salibandy", 1, "http://www.iltasanomat.fi/rss/salibandy.xml"));
        SECTIONS.add(new SectionDeprecated("Tennis", 1, "http://www.iltasanomat.fi/rss/tennis.xml"));
        SECTIONS.add(new SectionDeprecated("PesÃ¤pallo", 1, "http://www.iltasanomat.fi/rss/pesapallo.xml"));
        SECTIONS.add(new SectionDeprecated("Purjehdus", 1, "http://www.iltasanomat.fi/rss/purjehdus.xml"));
        SECTIONS.add(new SectionDeprecated("Nyrkkeily", 1, "http://www.iltasanomat.fi/rss/nyrkkeily.xml"));
        SECTIONS.add(new SectionDeprecated("Golf", 1, "http://www.iltasanomat.fi/rss/golf.xml"));
        SECTIONS.add(new SectionDeprecated("Ravit", 1, "http://www.iltasanomat.fi/rss/ravit.xml"));

        SECTIONS.add(new SectionDeprecated("Viihde", 0, null));
        SECTIONS.add(new SectionDeprecated("Viihdeuutiset", 1, "http://www.iltasanomat.fi/rss/viihde.xml"));
        SECTIONS.add(new SectionDeprecated("Elokuvat", 1, "http://www.iltasanomat.fi/rss/elokuvat.xml"));
        SECTIONS.add(new SectionDeprecated("Kuninkaalliset", 1, "http://www.iltasanomat.fi/rss/kuninkaalliset.xml"));
        SECTIONS.add(new SectionDeprecated("Big Brother", 1, "http://www.iltasanomat.fi/rss/bigbrother.xml"));
        SECTIONS.add(new SectionDeprecated("Miss Suomi", 1, "http://www.iltasanomat.fi/rss/miss-suomi.xml"));
        SECTIONS.add(new SectionDeprecated("Linnan juhlat", 1, "http://www.iltasanomat.fi/rss/linnanjuhlat.xml"));
        SECTIONS.add(new SectionDeprecated("Euroviisut", 1, "http://www.iltasanomat.fi/rss/euroviisut.xml"));
        SECTIONS.add(new SectionDeprecated("Idols", 1, "http://www.iltasanomat.fi/rss/idols.xml"));
        SECTIONS.add(new SectionDeprecated("Tanssii tÃ¤htien kanssa", 1, "http://www.iltasanomat.fi/rss/tanssiitahtienkanssa.xml"));
        SECTIONS.add(new SectionDeprecated("Diili", 1, "http://www.iltasanomat.fi/rss/diili.xml"));

        SECTIONS.add(new SectionDeprecated("Lifestyle", 0, null));
        SECTIONS.add(new SectionDeprecated("HyvÃ¤ olo", 1, "http://www.iltasanomat.fi/rss/hyvaolo.xml"));
        SECTIONS.add(new SectionDeprecated("Muoti & Kauneus", 1, "http://www.iltasanomat.fi/rss/muotikauneus.xml"));
        SECTIONS.add(new SectionDeprecated("Autot", 1, "http://www.iltasanomat.fi/rss/autot.xml"));
        SECTIONS.add(new SectionDeprecated("Matkat", 1, "http://www.iltasanomat.fi/rss/matkat.xml"));
        SECTIONS.add(new SectionDeprecated("Asuminen", 1, "http://www.iltasanomat.fi/rss/asuminen.xml"));
        SECTIONS.add(new SectionDeprecated("Ruokala - uutiset", 1, "http://www.iltasanomat.fi/rss/ruokala/uutiset.xml"));
        SECTIONS.add(new SectionDeprecated("Ruokala - ajankohtaista", 1, "http://www.iltasanomat.fi/rss/ruokala/ajankohtaista.xml"));
        SECTIONS.add(new SectionDeprecated("Ruokala - reseptit", 1, "http://www.iltasanomat.fi/rss/ruokala/resepti.xml"));
        SECTIONS.add(new SectionDeprecated("Lemmikit", 1, "http://www.iltasanomat.fi/rss/lemmikit.xml"));

        SECTIONS.add(new SectionDeprecated("Videot", 0, null));
        SECTIONS.add(new SectionDeprecated("Uutisvideot", 1, "http://www.iltasanomat.fi/rss/videot/uutiset.xml"));
        SECTIONS.add(new SectionDeprecated("Viihdevideot", 1, "http://www.iltasanomat.fi/rss/videot/viihde.xml"));
        SECTIONS.add(new SectionDeprecated("Urheiluvideot", 1, "http://www.iltasanomat.fi/rss/videot/urheilu.xml"));
        SECTIONS.add(new SectionDeprecated("Autovideot", 1, "http://www.iltasanomat.fi/rss/videot/autot.xml"));
        SECTIONS.add(new SectionDeprecated("Asuntovideot", 1, "http://www.iltasanomat.fi/rss/videot/asuminen.xml"));
        SECTIONS.add(new SectionDeprecated("Matkavideot", 1, "http://www.iltasanomat.fi/rss/videot/matkat.xml"));
    }

}