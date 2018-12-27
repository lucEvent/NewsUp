package com.lucevent.newsup.view.adapter.viewholder;

import android.view.View;

import com.lucevent.newsup.data.util.Section;

public class SectionHeaderViewHolder extends SectionViewHolder {

	public SectionHeaderViewHolder(View v)
	{
		super(v);
	}

	@Override
	public void bind(Section section, Object tag, boolean homeEnabled, boolean selected)
	{
		super.bind(section, tag, homeEnabled, selected);
	}

}
