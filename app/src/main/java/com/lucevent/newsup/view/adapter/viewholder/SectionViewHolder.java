package com.lucevent.newsup.view.adapter.viewholder;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.Section;

public class SectionViewHolder extends RecyclerView.ViewHolder {

	private ToggleButton mHomeButton;
	private TextView mLabel;

	public SectionViewHolder(View v, CompoundButton.OnCheckedChangeListener sectionStateChangeListener)
	{
		super(v);
		mLabel = (TextView) v.findViewById(R.id.label);
		mHomeButton = (ToggleButton) v.findViewById(R.id.home);
		mHomeButton.setOnCheckedChangeListener(sectionStateChangeListener);
	}

	public void bind(Section section, Object tag, boolean homeEnabled, Drawable homeBg,
	                 CompoundButton.OnCheckedChangeListener sectionStateChangeListener)
	{
		if (section.level >= 0) {
			itemView.setEnabled(true);
			mLabel.setEnabled(true);
			mHomeButton.setVisibility(View.VISIBLE);
			mHomeButton.setBackground(homeBg);
			mHomeButton.setOnCheckedChangeListener(null);
			mHomeButton.setChecked(homeEnabled);
			mHomeButton.setOnCheckedChangeListener(sectionStateChangeListener);
		} else {
			itemView.setEnabled(false);
			mLabel.setEnabled(false);
			mHomeButton.setVisibility(View.INVISIBLE);
		}

		mLabel.setText(section.name);
		itemView.setTag(tag);
		mHomeButton.setTag(tag);
	}

}
