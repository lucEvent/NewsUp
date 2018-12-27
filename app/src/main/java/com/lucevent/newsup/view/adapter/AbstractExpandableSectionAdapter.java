package com.lucevent.newsup.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;
import com.lucevent.newsup.data.util.Site;

public abstract class AbstractExpandableSectionAdapter<T extends RecyclerView.ViewHolder>
		extends RecyclerView.Adapter<T> {

	protected static final int SECTION_TYPE_HEADER = 0;
	protected static final int SECTION_TYPE_EXPANDABLE_HEADER = 1;
	protected static final int SECTION_TYPE_SUBSECTION = 2;

	protected Site mCurrentSite;
	private Sections mDataSet;
	private boolean[] mExpandedSectionIndexes;
	private int[] mHeaderSectionIndexes, mSubSectionsCounter;
	private int mItemCount;

	protected LayoutInflater mInflater;

	public AbstractExpandableSectionAdapter(Context context, Site site)
	{
		mInflater = LayoutInflater.from(context);

		update(site);
	}

	@Override
	public int getItemViewType(int position)
	{
		for (int i = 0; i < mHeaderSectionIndexes.length; i++) {
			if (position == 0) {
				return mSubSectionsCounter[i] == 0 ?
						SECTION_TYPE_HEADER :
						SECTION_TYPE_EXPANDABLE_HEADER;
			}
			position--;
			if (mExpandedSectionIndexes[i]) {

				if (position < mSubSectionsCounter[i])
					return SECTION_TYPE_SUBSECTION;

				position -= mSubSectionsCounter[i];
			}
		}
		return -1;
	}

	@Override
	public int getItemCount()
	{
		return mItemCount;
	}

	public final Section getSectionAt(int position)
	{
		for (int i = 0; i < mHeaderSectionIndexes.length; i++) {
			if (position == 0)
				return mDataSet.get(mHeaderSectionIndexes[i]);

			if (mExpandedSectionIndexes[i]) {

				if (position - 1 < mSubSectionsCounter[i])
					return mDataSet.get(mHeaderSectionIndexes[i] + position);

				position -= mSubSectionsCounter[i];
			}
			position--;
		}
		return null;
	}

	protected final int getActualIndexOf(Section section)
	{
		return mDataSet.indexOf(section);
	}

	protected final boolean isExpanded(int position)
	{
		for (int i = 0; i < mHeaderSectionIndexes.length; i++) {
			if (position == 0)
				return mExpandedSectionIndexes[i];

			position--;
			if (mExpandedSectionIndexes[i])
				position -= mSubSectionsCounter[i];

		}
		return false;
	}

	public void update(Site site)
	{
		mCurrentSite = site;
		if (site != null) {
			mDataSet = site.getSections();
			//
			mItemCount = 0;
			for (Section s : mDataSet)
				if (s.level < 1)
					mItemCount++;

			mHeaderSectionIndexes = new int[mItemCount];
			mSubSectionsCounter = new int[mItemCount];
			mExpandedSectionIndexes = new boolean[mItemCount];

			int headerCounter = -1;
			for (int i = 0; i < mDataSet.size(); i++) {
				if (mDataSet.get(i).level < 1) {  // Header Section
					headerCounter++;
					mExpandedSectionIndexes[headerCounter] = false;
					mHeaderSectionIndexes[headerCounter] = i;
					mSubSectionsCounter[headerCounter] = 0;
				} else  // SubSection
					mSubSectionsCounter[headerCounter]++;
			}
			//
			notifyDataSetChanged();
		}
	}

	protected final View.OnClickListener mOnExpandCollapseClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v)
		{
			Section section = (Section) v.getTag();
			for (int i = 0, pos = 0; i < mHeaderSectionIndexes.length; i++, pos++) {
				if (section == mDataSet.get(mHeaderSectionIndexes[i])) {
					mExpandedSectionIndexes[i] = !mExpandedSectionIndexes[i];
					v.setRotation(mExpandedSectionIndexes[i] ? 0 : 180);

					if (mExpandedSectionIndexes[i]) {   // Expand
						mItemCount += mSubSectionsCounter[i];
						notifyItemRangeInserted(pos + 1, mSubSectionsCounter[i]);
					} else {                            // Collapse
						mItemCount -= mSubSectionsCounter[i];
						notifyItemRangeRemoved(pos + 1, mSubSectionsCounter[i]);
					}
					return;
				}

				if (mExpandedSectionIndexes[i])
					pos += mSubSectionsCounter[i];
			}
		}
	};

}
