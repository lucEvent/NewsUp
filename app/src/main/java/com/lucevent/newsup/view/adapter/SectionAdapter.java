package com.lucevent.newsup.view.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.Sections;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.view.adapter.viewholder.SectionViewHolder;

import java.util.Set;

public class SectionAdapter extends RecyclerView.Adapter<SectionViewHolder> implements
		CompoundButton.OnCheckedChangeListener,
		View.OnClickListener {

	private Site mCurrentSite;
	private Sections mDataSet;
	private Set<String> mSectionStates;
	private boolean mDisplayingMainSections = true;
	private int mCurrentSelectedPosition;

	private final View.OnClickListener mOnSectionClickListener;

	private LayoutInflater mInflater;

	public SectionAdapter(Context context, Site site, View.OnClickListener onSectionClickListener)
	{
		mOnSectionClickListener = onSectionClickListener;
		mInflater = LayoutInflater.from(context);

		mSelectedDrawable = context.getResources().getDrawable(R.drawable.ic_main_section_selected).mutate();
		mUnselectedDrawable = context.getResources().getDrawable(R.drawable.ic_main_section_unselected);

		setNewDataSet(site);
	}

	@Override
	public int getItemViewType(int position)
	{
		return mDataSet.get(position).level <= 0 ? 0 : 1;
	}

	@NonNull
	@Override
	public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		int res_id = viewType == 0 ? R.layout.i_section_header : R.layout.i_section;
		View v = mInflater.inflate(res_id, parent, false);
		v.setOnClickListener(this);
		return new SectionViewHolder(v, this);
	}

	private Drawable mSelectedDrawable, mUnselectedDrawable;

	@Override
	public void onBindViewHolder(@NonNull SectionViewHolder holder, int position)
	{
		boolean checked = mSectionStates.contains(Integer.toString(position));
		boolean selected = false;
		if ((mDisplayingMainSections && checked) || (!mDisplayingMainSections && position == mCurrentSelectedPosition))
			selected = true;

		holder.bind(mDataSet.get(position), position, checked, checked ? mSelectedDrawable : mUnselectedDrawable, selected, this);
	}

	@Override
	public int getItemCount()
	{
		return mDataSet.size();
	}

	public void setNewDataSet(Site site)
	{
		mCurrentSite = site;
		if (site != null) {
			int color = site.getDarkColor();
			SectionViewHolder.setSelectedTextColor(color);

			mDataSet = site.getSections();
			mSectionStates = AppSettings.getMainSectionsString(site);
			mDisplayingMainSections = true;
			notifyDataSetChanged();

			DrawableCompat.setTint(mSelectedDrawable, color);
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
	{
		String iSection = Integer.toString((Integer) toggleButton.getTag());

		if (isChecked) {
			mSectionStates.add(iSection);
			toggleButton.setBackground(mSelectedDrawable);
		} else {
			if (mSectionStates.size() == 1) {
				toggleButton.setOnCheckedChangeListener(null);
				toggleButton.setChecked(true);
				toggleButton.setOnCheckedChangeListener(this);
				Toast.makeText(toggleButton.getContext(), R.string.msg_minimum_one_checked, Toast.LENGTH_SHORT).show();
				return;
			} else {
				mSectionStates.remove(iSection);
				toggleButton.setBackground(mUnselectedDrawable);
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
