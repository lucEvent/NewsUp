package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class SvenskaDagbladetSections extends Sections {

    public SvenskaDagbladetSections()
    {
        super();

        add(new Section("Huvudnyheter", "http://www.svd.se/?service=rss", 0));

        add(new Section("N\u00E4ringsliv", "http://www.svd.se/feed/articles/category/naringsliv.rss", 0));
        add(new Section("B\u00F6rsplus", "http://www.svd.se/feed/articles/category/naringsliv:borsplus.rss", 1));
        add(new Section("Digitalt", "http://www.svd.se/feed/articles/category/naringsliv:digitalt.rss", 1));
        add(new Section("Motor", "http://www.svd.se/feed/articles/category/naringsliv:motor.rss", 1));
        add(new Section("N\u00E4ringsliv Debatt", "http://www.svd.se/feed/articles/category/naringsliv:debatt.rss", 1));

        add(new Section("Kultur", "http://www.svd.se/feed/articles/category/kultur.rss", 0));
        add(new Section("Litteratur", "http://www.svd.se/feed/articles/category/litteratur.rss", 1));
        add(new Section("Under strecket", "http://www.svd.se/feed/articles/category/kultur:under-strecket.rss", 1));
        add(new Section("Scen", "http://www.svd.se/feed/articles/category/kultur:scen.rss", 1));
        add(new Section("Musik", "http://www.svd.se/feed/articles/category/kultur:musik.rss", 1));
        add(new Section("Konst", "http://www.svd.se/feed/articles/category/konst.rss", 1));
        add(new Section("Film", "http://www.svd.se/feed/articles/category/film.rss", 1));
        add(new Section("Konstliv", "http://www.svd.se/feed/articles/category/konstliv.rss", 1));

        add(new Section("Ledare", "http://www.svd.se/feed/articles/category/ledare.rss", 0));
        add(new Section("Debatt", "http://www.svd.se/feed/articles/category/debatt.rss", 0));
        add(new Section("Sverige", "http://www.svd.se/feed/articles/category/sverige.rss", 0));
        add(new Section("V\u00E4rlden", "http://www.svd.se/feed/articles/category/varlden.rss", 0));
        add(new Section("Idagsidan", "http://www.svd.se/feed/articles/category/idagsidan.rss", 0));
        add(new Section("Sport", "http://www.svd.se/feed/articles/category/sport.rss", 0));
        add(new Section("Resor", "http://www.svd.se/feed/articles/category/resor.rss", 0));
        add(new Section("V\u00E4der", "http://www.svd.se/feed/articles/category/vader.rss", 0));
        add(new Section("Vin & mat", "http://www.svd.se/feed/articles/category/vin-och-mat.rss", 0));
        add(new Section("Perfect Guide", "http://www.svd.se/feed/articles/category/perfect-guide.rss", 0));
        add(new Section("Junior", "http://www.svd.se/feed/articles/category/junior.rss", 0));
        add(new Section("Bostad & inredning", "http://www.svd.se/feed/articles/category/bostad-och-inredning.rss", 0));
        add(new Section("L\u00E4s & skriv", "http://www.svd.se/feed/articles/category/las-och-skriv.rss", 0));
        add(new Section("Webb-tv", "http://www.svd.se/feed/articles/tag/webb-tv.rss", 0));

        add(new Section("Artiklar", "http://www.svd.se/feed/articles.rss", 0));
        add(new Section("Jenny Stiernstedt", "http://www.svd.se/feed/articles/author/jenny-stiernstedt.rss", 1));
        add(new Section("TT", "http://www.svd.se/feed/articles/author/tt.rss", 1));
        add(new Section("G\u00F6ran Eriksson", "http://www.svd.se/feed/articles/author/goran-eriksson.rss", 1));
        add(new Section("Tomas Lundin", "http://www.svd.se/feed/articles/author/tomas-lundin.rss", 1));
        add(new Section("Tove Lifvendahl", "http://www.svd.se/feed/articles/author/tove-lifvendahl.rss", 1));
        add(new Section("Lars Hedblad", "http://www.svd.se/feed/articles/author/lars-hedblad.rss", 1));
        add(new Section("Erik Wallrup", "http://www.svd.se/feed/articles/author/erik-wallrup.rss", 1));
        add(new Section("Gunilla von Hall", "http://www.svd.se/feed/articles/author/gunilla-von-hall.rss", 1));
        add(new Section("Louise Andr\u00E9n Meiton", "http://www.svd.se/feed/articles/author/louise-andren-meiton.rss", 1));
        add(new Section("Anders Q Bj\u00F6rkman", "http://www.svd.se/feed/articles/author/anders-q-bjorkman.rss", 1));
        add(new Section("Nordnet", "http://www.svd.se/feed/articles/author/nordnet-sf51.rss", 1));
        add(new Section("Lars Lov\u00E9n", "http://www.svd.se/feed/articles/author/lars-loven.rss", 1));
        add(new Section("Andres Lokko", "http://www.svd.se/feed/articles/author/andres-lokko.rss", 1));
        add(new Section("Stefan Thungren", "http://www.svd.se/feed/articles/author/stefan-thungren.rss", 1));
        add(new Section("Harry Amster", "http://www.svd.se/feed/articles/author/harry-amster.rss", 1));
        add(new Section("Lars Berge", "http://www.svd.se/feed/articles/author/lars-berge.rss", 1));
        add(new Section("Mats Johansson", "http://www.svd.se/feed/articles/author/mats-johansson.rss", 1));
        add(new Section("Andreas Cervenka", "http://www.svd.se/feed/articles/author/andreas-cervenka.rss", 1));
        add(new Section("Anna M\u00F6ller", "http://www.svd.se/feed/articles/author/anna-moller.rss", 1));
        add(new Section("Henrik Ennart", "http://www.svd.se/feed/articles/author/henrik-ennart.rss", 1));
        add(new Section("SEB", "http://www.svd.se/feed/articles/author/seb.rss", 1));
        add(new Section("Erik Bergin", "http://www.svd.se/feed/articles/author/erik-bergin.rss", 1));
        add(new Section("Malin Hoelstad", "http://www.svd.se/feed/articles/author/malin-hoelstad.rss", 1));
        add(new Section("Erik Nilson", "http://www.svd.se/feed/articles/author/erik-nilson.rss", 1));
        add(new Section("Johan Hellekant", "http://www.svd.se/feed/articles/author/johan-hellekant.rss", 1));
        add(new Section("Magnus Helander", "http://www.svd.se/feed/articles/author/magnus-helander.rss", 1));
        add(new Section("Janerik Larsson", "http://www.svd.se/feed/articles/author/janerik-larsson.rss", 1));
        add(new Section("Knut Brunnberg", "http://www.svd.se/feed/articles/author/knut-brunnberg.rss", 1));

        add(new Section("Stories", null, -1));
        add(new Section("Arbogafallet", "http://www.svd.se/feed/articles/story/arbogafallet.rss", 1));
        add(new Section("Attacken i Nice", "http://www.svd.se/feed/articles/story/attacken-i-nice.rss", 1));
        add(new Section("Avtalsr\u00F6relsen 2016", "http://www.svd.se/feed/articles/story/avtalsrorelsen-2016.rss", 1));
        add(new Section("Chefredakt\u00F6rens val", "http://www.svd.se/feed/articles/story/chefredaktorens-val.rss", 1));
        add(new Section("Den hotande bubblan", "http://www.svd.se/feed/articles/story/den-hotande-bubblan.rss", 1));
        add(new Section("Den l\u00F6nsamma asylmarknaden", "http://www.svd.se/feed/articles/story/den-lonsamma-asylmarknaden.rss", 1));
        add(new Section("Det sk\u00E4rpta s\u00E4kerhetsl\u00E4get", "http://www.svd.se/feed/articles/story/det-skarpta-sakerhetslaget.rss", 1));
        add(new Section("Elvakaffet", "http://www.svd.se/feed/articles/story/elvakaffet.rss", 1));
        add(new Section("Ericssons kr\u00E4ftg\u00E5ng", "http://www.svd.se/feed/articles/story/ericssons-kraftgang.rss", 1));
        add(new Section("Flykten till Europa", "http://www.svd.se/feed/articles/story/flykten-till-europa.rss", 1));
        add(new Section("Greklandskrisen", "http://www.svd.se/feed/articles/story/greklandskrisen.rss", 1));
        add(new Section("Gripenaff\u00E4rerna", "http://www.svd.se/feed/articles/story/gripenaffarerna.rss", 1));
        add(new Section("Halvtid f\u00F6r L\u00F6fvens regering", "http://www.svd.se/feed/articles/story/halvtid-for-lofvens-regering.rss", 1));
        add(new Section("Harrisons historia", "http://www.svd.se/feed/articles/story/harrisons-historia.rss", 1));
        add(new Section("Hotet mot Sverige", "http://www.svd.se/feed/articles/story/hotet-mot-sverige.rss", 1));
        add(new Section("Id-kontrollerna", "http://www.svd.se/feed/articles/story/id-kontrollerna.rss", 1));
        add(new Section("Jordb\u00E4vningarna i Italien", "http://www.svd.se/feed/articles/story/jordbavningarna-i-italien.rss", 1));
        add(new Section("Kampen om Vita huset", "http://www.svd.se/feed/articles/story/kampen-om-vita-huset.rss", 1));
        add(new Section("Konstliv", "http://www.svd.se/feed/articles/story/konstliv.rss", 1));
        add(new Section("Larsson l\u00E4ser", "http://www.svd.se/feed/articles/story/larsson-laser.rss", 1));
        add(new Section("Mona Sahlins avg\u00E5ng", "http://www.svd.se/feed/articles/story/mona-sahlins-avgang.rss", 1));
        add(new Section("Musiktj\u00E4nsternas krig", "http://www.svd.se/feed/articles/story/musiktjansternas-krig.rss", 1));
        add(new Section("Panama-dokumenten", "http://www.svd.se/feed/articles/story/panamadokumenten.rss", 1));
        add(new Section("Polisv\u00E5ldet i USA", "http://www.svd.se/feed/articles/story/polisvaldet-i-usa.rss", 1));
        add(new Section("Proteinskiftet", "http://www.svd.se/feed/articles/story/proteinskiftet.rss", 1));
        add(new Section("Regeringsf\u00F6rklaringen 2016", "http://www.svd.se/feed/articles/story/regeringsforklaringen-2016.rss", 1));
        add(new Section("Saab-konkursen", "http://www.svd.se/feed/articles/story/saab-konkursen.rss", 1));
        add(new Section("Scenh\u00F6sten 2016", "http://www.svd.se/feed/articles/story/scenhosten-2016.rss", 1));
        add(new Section("SD:s gala p\u00E5 Grand Hotel", "http://www.svd.se/feed/articles/story/sds-gala-pa-grand-hotel.rss", 1));
        add(new Section("S\u00E4kerhetsr\u00E5det", "http://www.svd.se/feed/articles/story/sakerhetsradet.rss", 1));
        add(new Section("Terrorn i Europa", "http://www.svd.se/feed/articles/story/terrorn-i-europa.rss", 1));
        add(new Section("Tove Lifvendahls val", "http://www.svd.se/feed/articles/story/tove-lifvendahls-val.rss", 1));
        add(new Section("Turbulensen i MP", "http://www.svd.se/feed/articles/story/turbulensen-i-mp.rss", 1));
        add(new Section("Turerna i Fingerprint Cards", "http://www.svd.se/feed/articles/story/turerna-i-fingerprint-cards.rss", 1));
        add(new Section("Turkiet efter kuppf\u00F6rs\u00F6ket", "http://www.svd.se/feed/articles/story/turkiet-efter-kuppforsoket.rss", 1));
        add(new Section("Utsl\u00E4ppsskandalerna i bilindustrin", "http://www.svd.se/feed/articles/story/utslappsskandalerna-i-bilindustrin.rss", 1));
        add(new Section("Vargattacken p\u00E5 Kolm\u00E5rden", "http://www.svd.se/feed/articles/story/vargattacken-pa-kolmarden.rss", 1));

    }

}
