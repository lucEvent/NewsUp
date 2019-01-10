package com.lucevent.newsup.data.util;

import java.util.ArrayList;
import java.util.Locale;

public class Sections extends ArrayList<Section> {

	public Sections()
	{
		super();
	}

	public boolean areRegional()
	{
		return false;
	}

	public final Section getDefault()
	{
		if (!areRegional())
			return get(0);

		Locale locale = Locale.getDefault();
		return getRegional(locale.getLanguage().toLowerCase(), locale.getCountry().toLowerCase());
	}

	protected Section getRegionalDefault()
	{
		return get(0);
	}

	public int indexOf(int section_code)
	{
		for (int i = 0; i < size(); i++)
			if (get(i).code == section_code)
				return i;

		return -1;
	}

	public Section getSectionByCode(int section_code)
	{
		for (Section s : this)
			if (s.code == section_code)
				return s;

		return null;
	}

	protected final Section getRegional(String lang, String country)
	{
		Section c = null;
		for (Section s : this) {
			RegionalSection rs = (RegionalSection) s;
			if (country.equals(rs.region)) {
				c = s;
				if (lang.equals(rs.language))
					return s;
			}
		}

		if (c != null)    // region matched, language didn't
			return c;

		for (Section s : this) {    // only if region didn't match, we look for language match only
			RegionalSection rs = (RegionalSection) s;
			if (lang.equals(rs.language))
				return s;
		}

		return getRegionalDefault(); // nor language or region matched, return regional default
	}

}
