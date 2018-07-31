package com.lucevent.newsup.data.util;

import java.util.ArrayList;

public class Sections extends ArrayList<Section> {

	public Sections()
	{
		super();
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

}
