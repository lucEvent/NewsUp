package com.lucevent.newsup.view.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
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

public class SectionAdapter extends RecyclerView.Adapter<SectionViewHolder> implements CompoundButton.OnCheckedChangeListener {

	private Site mCurrentSite;
	private Sections mDataSet;
	private Set<String> mSectionStates;
	private final View.OnClickListener mOnItemClickListener;

	private LayoutInflater mInflater;

	public SectionAdapter(Context context, Site site, View.OnClickListener onItemClickListener)
	{
		mOnItemClickListener = onItemClickListener;
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

	@Override
	public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		int res_id = viewType == 0 ? R.layout.i_section_header : R.layout.i_section;
		View v = mInflater.inflate(res_id, parent, false);
		v.setOnClickListener(mOnItemClickListener);
		return new SectionViewHolder(v, this);
	}

	private Drawable mSelectedDrawable, mUnselectedDrawable;

	@Override
	public void onBindViewHolder(SectionViewHolder holder, int position)
	{
		boolean checked = mSectionStates.contains(Integer.toString(position));
		holder.bind(mDataSet.get(position), position, checked, checked ? mSelectedDrawable : mUnselectedDrawable, this);
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
			mDataSet = site.getSections();
			mSectionStates = AppSettings.getMainSectionsString(site);
			notifyDataSetChanged();

			DrawableCompat.setTint(mSelectedDrawable, site.color == 0xffffffff ? 0xff666666 : site.color);
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

}
