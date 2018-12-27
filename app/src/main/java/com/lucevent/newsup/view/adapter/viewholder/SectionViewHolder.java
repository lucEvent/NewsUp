package com.lucevent.newsup.view.adapter.viewholder;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.Section;

public class SectionViewHolder extends RecyclerView.ViewHolder {

	protected static CompoundButton.OnCheckedChangeListener mOnSectionStateChangeListener;

	protected static int TEXT_COLOR_DISABLED = -1;
	protected static int TEXT_COLOR_UNSELECTED;
	protected static int TEXT_COLOR_SELECTED;

	protected ToggleButton mHomeButton;
	protected TextView mLabel;

	public SectionViewHolder(View v)
	{
		super(v);

		if (TEXT_COLOR_DISABLED == -1) {
			Resources r = v.getResources();

			TEXT_COLOR_DISABLED = r.getColor(R.color.disabled_section_text_color);
			TEXT_COLOR_UNSELECTED = r.getColor(R.color.enabled_section_text_color);
		}

		mLabel = (TextView) v.findViewById(R.id.label);
		mHomeButton = (ToggleButton) v.findViewById(R.id.home);
		mHomeButton.setOnCheckedChangeListener(mOnSectionStateChangeListener);
	}

	public void bind(Section section, Object tag, boolean homeEnabled, boolean selected)
	{
		mLabel.setText(section.name);
		mLabel.setTextColor(selected ? TEXT_COLOR_SELECTED : TEXT_COLOR_UNSELECTED);

		mHomeButton.setBackgroundResource(homeEnabled ? R.drawable.ic_main_section_selected : R.drawable.ic_main_section_unselected);
		ViewCompat.setBackgroundTintList(mHomeButton, ColorStateList.valueOf(TEXT_COLOR_SELECTED));
		mHomeButton.setOnCheckedChangeListener(null);
		mHomeButton.setChecked(homeEnabled);
		mHomeButton.setOnCheckedChangeListener(mOnSectionStateChangeListener);
		mHomeButton.setTag(tag);

		itemView.setTag(tag);
	}

	public static void setSelectedTextColor(int color)
	{
		TEXT_COLOR_SELECTED = color;
	}

	public static void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener)
	{
		mOnSectionStateChangeListener = onCheckedChangeListener;
	}

}
