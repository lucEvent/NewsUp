package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class SportsIllustratedSections extends Sections {

	public SportsIllustratedSections()
	{
		super();

		add(new Section("Top stories", "https://www.si.com/rss/si_topstories.rss", 0));
		add(new Section("NFL", "https://www.si.com/rss/si_nfl.rss", 0));
		add(new Section("MLB", "https://www.si.com/rss/si_mlb.rss", 0));
		add(new Section("NBA", "https://www.si.com/rss/si_nba.rss", 0));
		add(new Section("NHL", "https://www.si.com/rss/si_nhl.rss", 0));
		add(new Section("Soccer", "https://www.si.com/rss/si_soccer.rss", 0));
		add(new Section("Tennis", "https://www.si.com/rss/si_tennis.rss", 0));
		add(new Section("MMA", "https://www.si.com/rss/si_mma.rss", 0));
		add(new Section("College football", "https://www.si.com/rss/si_ncaaf.rss", 0));
		add(new Section("College basketball", "https://www.si.com/rss/si_ncaab.rss", 0));
		add(new Section("Extra mustard", "https://www.si.com/rss/si_extra_mustard.rss", 0));
		add(new Section("Edge", "https://www.si.com/rss/si_edge.rss", 0));
		add(new Section("SI writers", "https://www.si.com/rss/si_writers.rss", 0));

	}

}
