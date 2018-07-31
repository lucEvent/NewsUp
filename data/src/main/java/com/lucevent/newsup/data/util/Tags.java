package com.lucevent.newsup.data.util;

import java.util.ArrayList;
import java.util.Collection;

public class Tags extends ArrayList<String> {

	public Tags()
	{
		super();
	}

	public Tags(Collection<String> c)
	{
		super(c);
	}

	public Tags(String compact)
	{
		super();
		String[] tags = compact.replace("[", "").replace("]", "").split(", ");
		for (String tag : tags)
			add(tag);
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < this.size(); ++i) {
			if (i != 0)
				sb.append(", ");
			sb.append(get(i));
		}
		return sb.toString() + "]";
	}

}
