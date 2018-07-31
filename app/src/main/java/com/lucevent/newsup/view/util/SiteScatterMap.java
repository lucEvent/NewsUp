package com.lucevent.newsup.view.util;

import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.SitesMap;
import com.lucevent.newsup.data.util.Site;

import java.text.Normalizer;
import java.util.TreeMap;

public abstract class SiteScatterMap extends TreeMap<Integer, SitesMap> {

	public SiteScatterMap(Sites sites, String filter)
	{
		super();

		if (filter == null || filter.isEmpty()) {
			for (Site s : sites)
				add(s);
		} else {
			String normFilter = normalize(filter);
			for (Site s : sites)
				if (normalize(s.name).contains(normFilter))
					add(s);
		}
	}

	private String normalize(String s)
	{
		return Normalizer.normalize(s.toLowerCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	public void add(Site s)
	{
		Integer cvo = comparableValueOf(s);
		SitesMap sm = get(cvo);
		if (sm == null) {
			sm = new SitesMap(SitesMap.SITE_COMPARATOR_BY_NAME);
			put(cvo, sm);
		}
		sm.add(s);
	}

	public abstract Integer comparableValueOf(Site s);

}
