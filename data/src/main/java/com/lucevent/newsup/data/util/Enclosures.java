package com.lucevent.newsup.data.util;

import java.util.ArrayList;

public class Enclosures extends ArrayList<Enclosure> {

	public Enclosures()
	{
		super();
	}

	public boolean has(int type)
	{
		for (Enclosure e : this)
			if (e.type == type)
				return true;
		return false;
	}

	public void keepOnlyLargest()
	{
		while (size() > 1) {
			Enclosure e0 = get(0);
			Enclosure e1 = get(1);
			remove(e0.size > e1.size ? 1 : 0);
		}
	}

}
