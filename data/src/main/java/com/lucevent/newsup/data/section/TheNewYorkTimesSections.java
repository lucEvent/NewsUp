package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class TheNewYorkTimesSections extends Sections {

	public TheNewYorkTimesSections()
	{
		super();

		add(new Section("Home Page", "http://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml", 0));
		add(new Section("N.Y. Region", "http://rss.nytimes.com/services/xml/rss/nyt/NYRegion.xml", 0));

		add(new Section("World", "http://rss.nytimes.com/services/xml/rss/nyt/World.xml", 0));
		add(new Section("Africa", "http://rss.nytimes.com/services/xml/rss/nyt/Africa.xml", 1));
		add(new Section("Americas", "http://rss.nytimes.com/services/xml/rss/nyt/Americas.xml", 1));
		add(new Section("Asia Pacific", "http://rss.nytimes.com/services/xml/rss/nyt/AsiaPacific.xml", 1));
		add(new Section("Europe", "http://rss.nytimes.com/services/xml/rss/nyt/Europe.xml", 1));
		add(new Section("Middle East", "http://rss.nytimes.com/services/xml/rss/nyt/MiddleEast.xml", 1));

		add(new Section("U.S.", "http://rss.nytimes.com/services/xml/rss/nyt/US.xml", 0));
		add(new Section("Education", "http://rss.nytimes.com/services/xml/rss/nyt/Education.xml", 1));
		add(new Section("Politics", "http://rss.nytimes.com/services/xml/rss/nyt/Politics.xml", 1));
		add(new Section("The Upshot", "http://rss.nytimes.com/services/xml/rss/nyt/Upshot.xml", 1));

		add(new Section("Business", "http://rss.nytimes.com/services/xml/rss/nyt/Business.xml", 0));
		add(new Section("Economy", "http://rss.nytimes.com/services/xml/rss/nyt/Economy.xml", 1));
		add(new Section("DealBook", "https://rss.nytimes.com/services/xml/rss/nyt/Dealbook.xml", 1));
		add(new Section("Media & Advertising", "http://rss.nytimes.com/services/xml/rss/nyt/MediaandAdvertising.xml", 1));
		add(new Section("Your Money", "http://rss.nytimes.com/services/xml/rss/nyt/YourMoney.xml", 1));

		add(new Section("Technology", "http://rss.nytimes.com/services/xml/rss/nyt/Technology.xml", 0));
		add(new Section("Personal Tech", "http://rss.nytimes.com/services/xml/rss/nyt/PersonalTech.xml", 0));

		add(new Section("Sports", "http://rss.nytimes.com/services/xml/rss/nyt/Sports.xml", 0));
		add(new Section("Baseball", "http://rss.nytimes.com/services/xml/rss/nyt/Baseball.xml", 1));
		add(new Section("College Basketball", "http://rss.nytimes.com/services/xml/rss/nyt/CollegeBasketball.xml", 1));
		add(new Section("College Football", "https://www.nytimes.com/svc/collections/v1/publish/https://www.nytimes.com/section/sports/ncaafootball/rss.xml", 1));
		add(new Section("Golf", "http://rss.nytimes.com/services/xml/rss/nyt/Golf.xml", 1));
		add(new Section("Hockey", "http://rss.nytimes.com/services/xml/rss/nyt/Hockey.xml", 1));
		add(new Section("Pro-Basketball", "http://rss.nytimes.com/services/xml/rss/nyt/ProBasketball.xml", 1));
		add(new Section("Pro-Football", "http://rss.nytimes.com/services/xml/rss/nyt/ProFootball.xml", 1));
		add(new Section("Soccer", "http://rss.nytimes.com/services/xml/rss/nyt/Soccer.xml", 1));

		add(new Section("Science", "http://rss.nytimes.com/services/xml/rss/nyt/Science.xml", 0));
		add(new Section("Space & Cosmos", "http://rss.nytimes.com/services/xml/rss/nyt/Space.xml", 1));

		add(new Section("Health", "http://rss.nytimes.com/services/xml/rss/nyt/Health.xml", 0));
		add(new Section("Well Blog", "http://www.nytimes.com/svc/collections/v1/publish/www.nytimes.com/section/well/rss.xml", 1));

		add(new Section("Arts", "http://rss.nytimes.com/services/xml/rss/nyt/Arts.xml", 0));
		add(new Section("Art & Design", "http://rss.nytimes.com/services/xml/rss/nyt/ArtandDesign.xml", 1));
		add(new Section("Books", "http://rss.nytimes.com/services/xml/rss/nyt/Books.xml", 1));
		add(new Section("Sunday Book Review", "http://rss.nytimes.com/services/xml/rss/nyt/SundayBookReview.xml", 1));
		add(new Section("Dance", "http://rss.nytimes.com/services/xml/rss/nyt/Dance.xml", 1));
		add(new Section("Movies", "http://rss.nytimes.com/services/xml/rss/nyt/Movies.xml", 1));
		add(new Section("Music", "http://rss.nytimes.com/services/xml/rss/nyt/Music.xml", 1));
		add(new Section("Television", "http://rss.nytimes.com/services/xml/rss/nyt/Television.xml", 1));
		add(new Section("Theater", "http://rss.nytimes.com/services/xml/rss/nyt/Theater.xml", 1));

		add(new Section("Fashion & Style", "http://rss.nytimes.com/services/xml/rss/nyt/FashionandStyle.xml", 0));
		add(new Section("Dining & Wine", "http://rss.nytimes.com/services/xml/rss/nyt/DiningandWine.xml", 1));
		add(new Section("Weddings/Celebrations", "http://rss.nytimes.com/services/xml/rss/nyt/Weddings.xml", 1));
		add(new Section("T Magazine", "http://rss.nytimes.com/services/xml/rss/nyt/tmagazine.xml", 1));

		add(new Section("Travel", "http://rss.nytimes.com/services/xml/rss/nyt/Travel.xml", 0));
		add(new Section("Real Estate", "http://rss.nytimes.com/services/xml/rss/nyt/RealEstate.xml", 0));
		add(new Section("Autos", "http://rss.nytimes.com/services/xml/rss/nyt/Automobiles.xml", 0));
		add(new Section("Lens Blog", "https://lens.blogs.nytimes.com/feed/", 0));

		add(new Section("Other Blog", null, -1));
		add(new Section("Obituaries", "http://rss.nytimes.com/services/xml/rss/nyt/Obituaries.xml", 1));
		add(new Section("Times Wire", "https://content.api.nytimes.com/svc/news/v3/all/recent.rss", 1));
		add(new Section("Most Shared", "http://rss.nytimes.com/services/xml/rss/nyt/MostShared.xml", 1));
		add(new Section("Most Viewed", "http://rss.nytimes.com/services/xml/rss/nyt/MostViewed.xml", 1));

		add(new Section("Columnists", null, -1));
		add(new Section("Charles M. Blow", "http://www.nytimes.com/svc/collections/v1/publish/www.nytimes.com/column/charles-m-blow/rss.xml", 1));
		add(new Section("David Brooks", "http://www.nytimes.com/svc/collections/v1/publish/www.nytimes.com/column/david-brooks/rss.xml", 1));
		add(new Section("Frank Bruni", "http://www.nytimes.com/svc/collections/v1/publish/www.nytimes.com/column/frank-bruni/rss.xml", 1));
		add(new Section("Roger Cohen", "http://www.nytimes.com/svc/collections/v1/publish/www.nytimes.com/column/roger-cohen/rss.xml", 1));
		add(new Section("Gail Collins", "http://www.nytimes.com/svc/collections/v1/publish/www.nytimes.com/column/gail-collins/rss.xml", 1));
		add(new Section("Ross Douthat", "http://www.nytimes.com/svc/collections/v1/publish/www.nytimes.com/column/ross-douthat/rss.xml", 1));
		add(new Section("Maureen Dowd", "http://www.nytimes.com/svc/collections/v1/publish/www.nytimes.com/column/maureen-dowd/rss.xml", 1));
		add(new Section("Thomas L. Friedman", "http://www.nytimes.com/svc/collections/v1/publish/www.nytimes.com/column/thomas-l-friedman/rss.xml", 1));
		add(new Section("Nicholas D. Kristof", "http://www.nytimes.com/svc/collections/v1/publish/www.nytimes.com/column/nicholas-kristof/rss.xml", 1));
		add(new Section("Paul Krugman", "http://www.nytimes.com/svc/collections/v1/publish/www.nytimes.com/column/paul-krugman/rss.xml", 1));

		add(new Section("Opinionator", null, -1));
		add(new Section("Fixes", "http://www.nytimes.com/svc/collections/v1/publish/www.nytimes.com/column/fixes/rss.xml", 1));
		add(new Section("The Stone", "http://www.nytimes.com/svc/collections/v1/publish/www.nytimes.com/column/the-stone/rss.xml", 1));

		add(new Section("Sunday Review", "http://rss.nytimes.com/services/xml/rss/nyt/sunday-review.xml", 0));

	}

}
