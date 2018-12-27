package com.lucevent.newsup.view.adapter.viewholder.news;

import android.view.View;
import android.widget.ImageView;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.view.adapter.viewholder.SectionHeaderViewHolder;

public class ExpandableSectionHeaderViewHolder extends SectionHeaderViewHolder {

	private ImageView mSwitch;

	public ExpandableSectionHeaderViewHolder(View v, View.OnClickListener onExpandCollapseClickListener)
	{
		super(v);

		mSwitch = v.findViewById(R.id._switch);
		mSwitch.setOnClickListener(onExpandCollapseClickListener);
	}

	public void bind(Section section, Object tag, boolean homeEnabled, boolean selected, boolean expanded)
	{
		super.bind(section, tag, homeEnabled, selected);

		if (section.url == null) {
			itemView.setEnabled(false);
			mLabel.setEnabled(false);
			mLabel.setTextColor(TEXT_COLOR_DISABLED);
			mHomeButton.setVisibility(View.INVISIBLE);
		} else {
			itemView.setEnabled(true);
			mLabel.setEnabled(true);
			mHomeButton.setVisibility(View.VISIBLE);
		}

		mSwitch.setRotation(expanded ? 0 : 180);
		mSwitch.setTag(section);
	}

}
