package com.lucevent.newsup.data.event;

import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Site;

import java.util.ArrayList;

public class Category {

	public final int title_res_id;
	public final Category[] subcategories;

	private final String[] sectionTags;

	public Category(int title_res_id, Category[] subcategories, String[] section_tags)
	{
		this.title_res_id = title_res_id;
		this.subcategories = subcategories;
		this.sectionTags = section_tags;
	}

	public ArrayList<Source> getSources(Sites sites)
	{
		ArrayList<Source> sources = new ArrayList<>();
		for (Site site : sites) {
			ArrayList<Integer> sectionCodes = new ArrayList<>();
			for (Section sec : site.getSections())
				for (String tag : sectionTags)
					if (sec.name.equalsIgnoreCase(tag)) {
						sectionCodes.add(sec.code);
						break;
					}

			if (!sectionCodes.isEmpty()) {
				Source source = new Source();
				source.site = site;
				source.sections = new int[sectionCodes.size()];
				for (int i = 0; i < sectionCodes.size(); i++)
					source.sections[i] = sectionCodes.get(i);

				sources.add(source);
			}
		}

		return sources;
	}

}
