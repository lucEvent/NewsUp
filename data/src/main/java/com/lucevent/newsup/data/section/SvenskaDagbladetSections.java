package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class SvenskaDagbladetSections extends Sections {

	public SvenskaDagbladetSections()
	{
		super();

		add(new Section("Huvudnyheter", "https://www.svd.se/?service=rss", 0));

		add(new Section("N\u00E4ringsliv", "https://www.svd.se/feed/articles/category/naringsliv.rss", 0));
		add(new Section("B\u00F6rsplus", "https://www.svd.se/feed/articles/category/naringsliv:borsplus.rss", 1));
		add(new Section("Digitalt", "https://www.svd.se/feed/articles/category/naringsliv:digitalt.rss", 1));
		add(new Section("Motor", "https://www.svd.se/feed/articles/category/naringsliv:motor.rss", 1));
		add(new Section("N\u00E4ringsliv Debatt", "https://www.svd.se/feed/articles/category/naringsliv:debatt.rss", 1));

		add(new Section("Kultur", "https://www.svd.se/feed/articles/category/kultur.rss", 0));
		add(new Section("Litteratur", "https://www.svd.se/feed/articles/category/litteratur.rss", 1));
		add(new Section("Under strecket", "https://www.svd.se/feed/articles/category/kultur:under-strecket.rss", 1));
		add(new Section("Scen", "https://www.svd.se/feed/articles/category/kultur:scen.rss", 1));
		add(new Section("Musik", "https://www.svd.se/feed/articles/category/kultur:musik.rss", 1));
		add(new Section("Konst", "https://www.svd.se/feed/articles/category/konst.rss", 1));
		add(new Section("Film", "https://www.svd.se/feed/articles/category/film.rss", 1));

		add(new Section("Ledare", "https://www.svd.se/feed/articles/category/ledare.rss", 0));
		add(new Section("Debatt", "https://www.svd.se/feed/articles/category/debatt.rss", 0));
		add(new Section("Sverige", "https://www.svd.se/feed/articles/category/sverige.rss", 0));
		add(new Section("V\u00E4rlden", "https://www.svd.se/feed/articles/category/varlden.rss", 0));
		add(new Section("Idagsidan", "https://www.svd.se/feed/articles/category/idagsidan.rss", 0));
		add(new Section("Sport", "https://www.svd.se/feed/articles/category/sport.rss", 0));
		add(new Section("V\u00E4der", "https://www.svd.se/feed/articles/category/vader.rss", 0));
		add(new Section("Vin & mat", "https://www.svd.se/feed/articles/category/vin-och-mat.rss", 0));
		add(new Section("Perfect Guide", "https://www.svd.se/feed/articles/category/perfect-guide.rss", 0));
		add(new Section("Junior", "https://www.svd.se/feed/articles/category/junior.rss", 0));
		add(new Section("L\u00E4s & skriv", "https://www.svd.se/feed/articles/category/las-och-skriv.rss", 0));
		add(new Section("Webb-tv", "https://www.svd.se/feed/articles/tag/webb-tv.rss", 0));

		add(new Section("Artiklar", "https://www.svd.se/feed/articles.rss", 0));
		add(new Section("Harry Amster", "https://www.svd.se/feed/articles/author/harry-amster.rss", 1));
		add(new Section("Lars Berge", "https://www.svd.se/feed/articles/author/lars-berge.rss", 1));
		add(new Section("Erik Bergin", "https://www.svd.se/feed/articles/author/erik-bergin.rss", 1));
		add(new Section("Anders Q Bj\u00F6rkman", "https://www.svd.se/feed/articles/author/anders-q-bjorkman.rss", 1));
		add(new Section("Knut Brunnberg", "https://www.svd.se/feed/articles/author/knut-brunnberg.rss", 1));
		add(new Section("Henrik Ennart", "https://www.svd.se/feed/articles/author/henrik-ennart.rss", 1));
		add(new Section("G\u00F6ran Eriksson", "https://www.svd.se/feed/articles/author/goran-eriksson.rss", 1));
		add(new Section("Gunilla von Hall", "https://www.svd.se/feed/articles/author/gunilla-von-hall.rss", 1));
		add(new Section("Lars Hedblad", "https://www.svd.se/feed/articles/author/lars-hedblad.rss", 1));
		add(new Section("Magnus Helander", "https://www.svd.se/feed/articles/author/magnus-helander.rss", 1));
		add(new Section("Johan Hellekant", "https://www.svd.se/feed/articles/author/johan-hellekant.rss", 1));
		add(new Section("Malin Hoelstad", "https://www.svd.se/feed/articles/author/malin-hoelstad.rss", 1));
		add(new Section("Janerik Larsson", "https://www.svd.se/feed/articles/author/janerik-larsson.rss", 1));
		add(new Section("Tove Lifvendahl", "https://www.svd.se/feed/articles/author/tove-lifvendahl.rss", 1));
		add(new Section("Andres Lokko", "https://www.svd.se/feed/articles/author/andres-lokko.rss", 1));
		add(new Section("Lars Lov\u00E9n", "https://www.svd.se/feed/articles/author/lars-loven.rss", 1));
		add(new Section("Tomas Lundin", "https://www.svd.se/feed/articles/author/tomas-lundin.rss", 1));
		add(new Section("Louise Andr\u00E9n Meiton", "https://www.svd.se/feed/articles/author/louise-andren-meiton.rss", 1));
		add(new Section("Nordnet", "https://www.svd.se/feed/articles/author/nordnet-sf51.rss", 1));
		add(new Section("Jenny Stiernstedt", "https://www.svd.se/feed/articles/author/jenny-stiernstedt.rss", 1));
		add(new Section("Stefan Thungren", "https://www.svd.se/feed/articles/author/stefan-thungren.rss", 1));
		add(new Section("TT", "https://www.svd.se/feed/articles/author/tt.rss", 1));
		add(new Section("Erik Wallrup", "https://www.svd.se/feed/articles/author/erik-wallrup.rss", 1));

		add(new Section("Stories", null, -1));
		add(new Section("Arbogafallet", "https://www.svd.se/feed/articles/story/arbogafallet.rss", 1));
		add(new Section("Chefredakt\u00F6rens val", "https://www.svd.se/feed/articles/story/chefredaktorens-val.rss", 1));
		add(new Section("Den hotande bubblan", "https://www.svd.se/feed/articles/story/den-hotande-bubblan.rss", 1));
		add(new Section("Det sk\u00E4rpta s\u00E4kerhetsl\u00E4get", "https://www.svd.se/feed/articles/story/det-skarpta-sakerhetslaget.rss", 1));
		add(new Section("Elvakaffet", "https://www.svd.se/feed/articles/story/elvakaffet.rss", 1));
		add(new Section("Ericssons kr\u00E4ftg\u00E5ng", "https://www.svd.se/feed/articles/story/ericssons-kraftgang.rss", 1));
		add(new Section("Flykten till Europa", "https://www.svd.se/feed/articles/story/flykten-till-europa.rss", 1));
		add(new Section("Greklandskrisen", "https://www.svd.se/feed/articles/story/greklandskrisen.rss", 1));
		add(new Section("Gripenaff\u00E4rerna", "https://www.svd.se/feed/articles/story/gripenaffarerna.rss", 1));
		add(new Section("Harrisons historia", "https://www.svd.se/feed/articles/story/harrisons-historia.rss", 1));
		add(new Section("Hotet mot Sverige", "https://www.svd.se/feed/articles/story/hotet-mot-sverige.rss", 1));
		add(new Section("Id-kontrollerna", "https://www.svd.se/feed/articles/story/id-kontrollerna.rss", 1));
		add(new Section("Panama-dokumenten", "https://www.svd.se/feed/articles/story/panamadokumenten.rss", 1));
		add(new Section("Saab-konkursen", "https://www.svd.se/feed/articles/story/saab-konkursen.rss", 1));
		add(new Section("S\u00E4kerhetsr\u00E5det", "https://www.svd.se/feed/articles/story/sakerhetsradet.rss", 1));
		add(new Section("Terrorn i Europa", "https://www.svd.se/feed/articles/story/terrorn-i-europa.rss", 1));
		add(new Section("Tove Lifvendahls val", "https://www.svd.se/feed/articles/story/tove-lifvendahls-val.rss", 1));
		add(new Section("Turerna i Fingerprint Cards", "https://www.svd.se/feed/articles/story/turerna-i-fingerprint-cards.rss", 1));
		add(new Section("Turkiet efter kuppf\u00F6rs\u00F6ket", "https://www.svd.se/feed/articles/story/turkiet-efter-kuppforsoket.rss", 1));
		add(new Section("Utsl\u00E4ppsskandalerna i bilindustrin", "https://www.svd.se/feed/articles/story/utslappsskandalerna-i-bilindustrin.rss", 1));

	}

}
