package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class YleSections extends Sections {

	public YleSections()
	{
		super();

		add(new Section("P\u00e4\u00e4uutiset", "https://feeds.yle.fi/uutiset/v1/majorHeadlines/YLE_UUTISET.rss", 0));
		add(new Section("Tuoreimmat uutiset", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET", 0));

		add(new Section("Kotimaa", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-34837", 0));
		add(new Section("Ulkomaat", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-34953", 0));
		add(new Section("Talous", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-19274", 0));
		add(new Section("Politiikka", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-38033", 0));
		add(new Section("Kulttuuri", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-150067", 0));
		add(new Section("Viihde", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-36066", 0));
		add(new Section("Tiede", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-819", 0));
		add(new Section("Luonto", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-35354", 0));
		add(new Section("Terveys", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-35138", 0));
		add(new Section("Media", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-35057", 0));
		add(new Section("Liikenne", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-12", 0));
		add(new Section("Ilmi\u00f6t", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-40543", 0));
		add(new Section("Internet", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-37065", 0));
		add(new Section("Pelit", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-40893", 0));
		add(new Section("Blogit", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-35466", 0));
		add(new Section("Ilmasto", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-77773", 0));

		add(new Section("Alueet", null, -1));
		add(new Section("Etel\u00e4-Karjala", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-141372,18-138725,18-141408,18-145125,18-131144,18-140312", 1));
		add(new Section("Etel\u00e4-Savo", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-141852,18-135752,18-128389,18-130792,18-137228,18-125330", 1));
		add(new Section("Helsinki", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-147345,18-150046,18-147340,18-136028,18-134747,18-144282", 1));
		add(new Section("H\u00e4me", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-138727,18-135540,18-126842,18-138395,18-144020,18-126098", 1));
		add(new Section("Kainuu", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-141399,18-130145,18-142169,18-127432,18-149883,18-137572", 1));
		add(new Section("Keski-Pohjanmaa", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-135629,18-132849,18-133352,18-142495,18-149104,18-136959", 1));
		add(new Section("Keski-Suomi", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-148148,18-149340,18-136046,18-127721,18-141069,18-129209", 1));
		add(new Section("Kymenlaakso", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-131408,18-147400,18-130680,18-145649,18-139713,18-133965", 1));
		add(new Section("Lahti", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-141401,18-126860,18-126832,18-132932,18-127816,18-138208", 1));
		add(new Section("Lappi", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-139752,18-139808,18-140691,18-140504,18-133644,18-132902", 1));
		add(new Section("Oulu", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-148154,18-139457,18-133348,18-148431,18-144877,18-129150", 1));
		add(new Section("Per\u00e4meri", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-130713,18-139768,18-139497,18-129655,18-139463,18-125897", 1));
		add(new Section("Pohjanmaa", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-146311,18-148149,18-135574,18-147396,18-140801,18-133447,18-137632", 1));
		add(new Section("Pohjois-Karjala", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-141936,18-137570,18-144499,18-126620,18-126103,18-126878", 1));
		add(new Section("Satakunta", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-139772,18-129057,18-135386,18-149131,18-145760,18-148054", 1));
		add(new Section("Savo", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-141764,18-141405,18-141403,18-130415,18-127015,18-125612", 1));
		add(new Section("Tampere", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-146831,18-141847,18-130545,18-137467,18-132654,18-143939", 1));
		add(new Section("Turku", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_UUTISET&concepts=18-135507,18-150037,18-135495,18-139733,18-137688,18-136031", 1));

		add(new Section("Urheilu", "https://feeds.yle.fi/uutiset/v1/majorHeadlines/YLE_URHEILU.rss", 0));
		add(new Section("Tuoreimmat urheilu-uutiset", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_URHEILU", 1));
		add(new Section("Alppihiihto", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_URHEILU&concepts=18-141666", 1));
		add(new Section("Ampumahiihto", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_URHEILU&concepts=18-38976", 1));
		add(new Section("Formula 1", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_URHEILU&concepts=18-4498", 1));
		add(new Section("Golf", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_URHEILU&concepts=18-37929", 1));
		add(new Section("Hiihto", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_URHEILU&concepts=18-34976", 1));
		add(new Section("Jalkapallo", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_URHEILU&concepts=18-34842", 1));
		add(new Section("J\u00e4\u00e4kiekko", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_URHEILU&concepts=18-34944", 1));
		add(new Section("Koripallo", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_URHEILU&concepts=18-37123", 1));
		add(new Section("Lentopallo", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_URHEILU&concepts=18-36118", 1));
		add(new Section("Lumilautailu", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_URHEILU&concepts=18-35694", 1));
		add(new Section("Moottoripy\u00f6r\u00e4ily", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_URHEILU&concepts=18-40091", 1));
		add(new Section("Nyrkkeily", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_URHEILU&concepts=18-38755", 1));
		add(new Section("Purjehdus", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_URHEILU&concepts=18-40093", 1));
		add(new Section("Ralli", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_URHEILU&concepts=18-133247", 1));
		add(new Section("Ratsastus", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_URHEILU&concepts=18-36955", 1));
		add(new Section("Salibandy", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_URHEILU&concepts=18-38954", 1));
		add(new Section("Tennis", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_URHEILU&concepts=18-36864", 1));

		add(new Section("Selkouutiset", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_SELKOUUTISET", 0));
		add(new Section("News in English", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_NEWS", 0));
		add(new Section("S\u00e1pmi", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_SAPMI", 0));
		add(new Section("Novosti", "https://feeds.yle.fi/uutiset/v1/recent.rss?publisherIds=YLE_NOVOSTI", 0));

	}

}