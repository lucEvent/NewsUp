package com.lucevent.newsup.view.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.SitesMap;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.data.util.SiteCategory;
import com.lucevent.newsup.data.util.SiteCountry;
import com.lucevent.newsup.data.util.SiteLanguage;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.view.adapter.viewholder.CategorizedSiteRowViewHolder;
import com.lucevent.newsup.view.adapter.viewholder.SiteCategoryViewHolder;
import com.lucevent.newsup.view.adapter.viewholder.SiteRowViewHolder;
import com.lucevent.newsup.view.util.SiteScatterMap;

import java.util.ArrayList;
import java.util.TreeSet;

public class CategorizedSiteAdapter extends RecyclerView.Adapter<CategorizedSiteRowViewHolder>
		implements View.OnClickListener {

	public static final int MAX_ROW_ELEMS = 4;

	private static final int TYPE_CATEGORY_TITLE = 0;
	private static final int TYPE_SITE_ROW = 1;

	public enum Order {
		BY_NAME, BY_COUNTRY, BY_LANGUAGE, BY_CATEGORY
	}

	public interface OnSiteClickListener {
		void onSiteSelected(int code);

		void onSiteUnselected(int code);
	}

	private ArrayList<Object> mElems;
	private TreeSet<Integer> mSelectedSiteCodes;
	private final OnSiteClickListener mOnSiteClickListener;

	private final String[] mLanguageTitles, mCountryTitles, mTypeTitles;
	private final int[] mLanguageWeights, mCountryWeights;

	private LayoutInflater mInflater;

	public CategorizedSiteAdapter(Context context, OnSiteClickListener onSiteClickListener)
	{
		Resources res = context.getResources();

		mLanguageTitles = res.getStringArray(R.array.site_languages);
		mCountryTitles = res.getStringArray(R.array.site_countries);
		mTypeTitles = res.getStringArray(R.array.site_categories);
		mLanguageWeights = res.getIntArray(R.array.site_language_weights);
		mCountryWeights = res.getIntArray(R.array.site_country_weights);

		mElems = new ArrayList<>();
		mSelectedSiteCodes = new TreeSet<>();
		mInflater = LayoutInflater.from(context);
		mOnSiteClickListener = onSiteClickListener;
	}

	public void set(Order order, String filter, @Nullable int[] selectedSiteCodes)
	{
		SiteScatterMap map;
		String[] titles;
		int shift;

		switch (order) {
			case BY_NAME:
				titles = null;
				shift = 24;
				map = new SiteScatterMap(AppData.getSites(), filter) {
					@Override
					public Integer comparableValueOf(Site s)
					{
						return 0;
					}
				};
				break;
			default:
			case BY_LANGUAGE:
				titles = mLanguageTitles;
				shift = SiteLanguage.shift;
				map = new SiteScatterMap(AppData.getSites(), filter) {
					@Override
					public Integer comparableValueOf(Site s)
					{
						return mLanguageWeights[s.getLanguage() - 1];
					}
				};
				break;
			case BY_COUNTRY:
				titles = mCountryTitles;
				shift = SiteCountry.shift;
				map = new SiteScatterMap(AppData.getSites(), filter) {
					@Override
					public Integer comparableValueOf(Site s)
					{
						return mCountryWeights[s.getCountry() - 1];
					}
				};
				break;
			case BY_CATEGORY:
				titles = mTypeTitles;
				shift = SiteCategory.shift;
				map = new SiteScatterMap(AppData.getSites(), filter) {
					@Override
					public Integer comparableValueOf(Site s)
					{
						return s.getCategory();
					}
				};
				break;
		}

		mElems.clear();
		for (SitesMap categorySites : map.values()) {
			mElems.add(titles == null ? "" : titles[(((categorySites.first().info >> shift) & 0xFF)) - 1]);
			mElems.addAll(categorySites);
		}

		mSelectedSiteCodes.clear();
		if (selectedSiteCodes != null) {
			for (int c : selectedSiteCodes)
				mSelectedSiteCodes.add(c);
		}

		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public CategorizedSiteRowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		switch (viewType) {
			case TYPE_CATEGORY_TITLE:
				return new SiteCategoryViewHolder(
						mInflater.inflate(R.layout.i_site_group, parent, false)
				);
			case TYPE_SITE_ROW:
				View v = mInflater.inflate(R.layout.i_site, parent, false);
				v.setOnClickListener(this);
				return new SiteRowViewHolder(v);
		}
		return null;    // never reaching this statement
	}

	@Override
	public void onBindViewHolder(@NonNull CategorizedSiteRowViewHolder holder, int position)
	{
		switch (getItemViewType(position)) {
			case TYPE_CATEGORY_TITLE:
				((SiteCategoryViewHolder) holder)
						.bind((String) mElems.get(position));
				break;
			case TYPE_SITE_ROW:
				Site site = (Site) mElems.get(position);
				((SiteRowViewHolder) holder)
						.bind(site, mSelectedSiteCodes.contains(site.code));
		}
	}

	@Override
	public int getItemViewType(int position)
	{
		return mElems.get(position) instanceof String ?
				TYPE_CATEGORY_TITLE :
				TYPE_SITE_ROW;
	}

	@Override
	public int getItemCount()
	{
		return mElems.size();
	}

	public TreeSet<Integer> getSelectedSiteCodes()
	{
		return mSelectedSiteCodes;
	}

	@Override
	public void onClick(View v)
	{
		int code = (int) v.getTag();
		boolean isSelected = !v.isSelected();

		v.setSelected(isSelected);
		if (isSelected) {
			mSelectedSiteCodes.add(code);
			mOnSiteClickListener.onSiteSelected(code);
		} else {
			mSelectedSiteCodes.remove(code);
			mOnSiteClickListener.onSiteUnselected(code);
		}
	}

	public int getSpanSize(int position)
	{
		return getItemViewType(position) == TYPE_CATEGORY_TITLE ? MAX_ROW_ELEMS : 1;
	}

}
