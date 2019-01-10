package com.lucevent.newsup.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.view.adapter.viewholder.SectionHeaderViewHolder;
import com.lucevent.newsup.view.adapter.viewholder.SectionViewHolder;
import com.lucevent.newsup.view.adapter.viewholder.news.ExpandableSectionHeaderViewHolder;

import java.util.HashSet;
import java.util.Set;

public class ExpandableSectionAdapter extends AbstractExpandableSectionAdapter<SectionViewHolder>
		implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

	private Set<String> mSectionStates;

	private boolean mDisplayingMainSections = true;
	private int mCurrentSelectedPosition;

	private final View.OnClickListener mOnSectionClickListener;

	public ExpandableSectionAdapter(Context context, Site site, View.OnClickListener onSectionClickListener)
	{
		super(context, site);
		mOnSectionClickListener = onSectionClickListener;

		SectionViewHolder.setOnCheckedChangeListener(this);
	}

	@NonNull
	@Override
	public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		switch (viewType) {
			case SECTION_TYPE_HEADER:
				View v = mInflater.inflate(R.layout.i_section_header, parent, false);
				v.setOnClickListener(this);
				return new SectionHeaderViewHolder(v);
			case SECTION_TYPE_EXPANDABLE_HEADER:
				v = mInflater.inflate(R.layout.i_section_expandable_header, parent, false);
				v.setOnClickListener(this);
				return new ExpandableSectionHeaderViewHolder(v, mOnExpandCollapseClickListener);

			case SECTION_TYPE_SUBSECTION:
				v = mInflater.inflate(R.layout.i_section, parent, false);
				v.setOnClickListener(this);
				return new SectionHeaderViewHolder(v);
		}
		return null;
	}

	@Override
	public void onBindViewHolder(@NonNull SectionViewHolder holder, int visible_position)
	{
		Section section = getSectionAt(visible_position);
		int full_expanded_position = getActualIndexOf(section);

		boolean checked = mSectionStates.contains(Integer.toString(full_expanded_position));
		boolean selected = false;
		if ((mDisplayingMainSections && checked) || (!mDisplayingMainSections && full_expanded_position == mCurrentSelectedPosition))
			selected = true;

		switch (getItemViewType(visible_position)) {
			case SECTION_TYPE_EXPANDABLE_HEADER:
				((ExpandableSectionHeaderViewHolder) holder)
						.bind(section, full_expanded_position, checked, selected, isExpanded(visible_position));
				break;
			case SECTION_TYPE_HEADER:
			case SECTION_TYPE_SUBSECTION:
				holder.bind(section, full_expanded_position, checked, selected);
		}
	}

	public void update(Site site)
	{
		if (site != null) {
			int color = site.getDarkColor();
			SectionViewHolder.setSelectedTextColor(color);

			mSectionStates = AppSettings.getMainSectionsString(site);
			if (mSectionStates == null) {
				Sections sections = site.getSections();
				mSectionStates = new HashSet<>();
				mSectionStates.add(Integer.toString(sections.indexOf(sections.getDefault())));
			}

			mDisplayingMainSections = true;
		}
		super.update(site);
	}

	@Override
	public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
	{
		String iSection = Integer.toString((Integer) toggleButton.getTag());

		if (isChecked) {
			mSectionStates.add(iSection);
			toggleButton.setBackgroundResource(R.drawable.ic_main_section_selected);
		} else {
			if (mSectionStates.size() == 1) {
				toggleButton.setOnCheckedChangeListener(null);
				toggleButton.setChecked(true);
				toggleButton.setOnCheckedChangeListener(this);
				Toast.makeText(toggleButton.getContext(), R.string.msg_minimum_one_checked, Toast.LENGTH_SHORT).show();
				return;
			} else {
				mSectionStates.remove(iSection);
				toggleButton.setBackgroundResource(R.drawable.ic_main_section_unselected);
			}
		}
		AppSettings.setMainSections(mCurrentSite, mSectionStates);
	}

	@Override
	public void onClick(View v)
	{
		mOnSectionClickListener.onClick(v);

		mCurrentSelectedPosition = (int) v.getTag();
		mDisplayingMainSections = false;
		notifyDataSetChanged();
	}

}
