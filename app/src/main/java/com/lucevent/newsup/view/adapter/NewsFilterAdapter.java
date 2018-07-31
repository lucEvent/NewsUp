package com.lucevent.newsup.view.adapter;

import android.support.v7.util.SortedList;
import android.view.View;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.view.util.NewsAdapterList;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

public class NewsFilterAdapter extends NewsAdapter {

	private ArrayList<News> mOriginalValues;
	private ArrayList<News> mFilteredValues, mLastQueryValues;

	private TreeSet<Integer> mLastFilterCodes = new TreeSet<>();
	private String mLastFilter = "";

	public NewsFilterAdapter(View.OnClickListener onClick, View.OnClickListener onBookmarkClick, NewsAdapterList.SortBy sortBy)
	{
		super(onClick, onBookmarkClick, sortBy);
	}

	public ArrayList<News> getDataSet()
	{
		init();
		return mOriginalValues;
	}

	private void init()
	{
		if (mOriginalValues == null) {
			SortedList<News> sortedList = mDataSet;
			mOriginalValues = new ArrayList<>(sortedList.size());
			mLastQueryValues = new ArrayList<>(sortedList.size());
			for (int i = 0; i < sortedList.size(); i++)
				mOriginalValues.add(sortedList.get(i));
		}
	}

	private Collection<News> applyFilters(Collection<News> c)
	{
		return applyTextFilter(applyCodeFilter(c));
	}

	private Collection<News> applyCodeFilter(Collection<News> c)
	{
		if (mLastFilterCodes.isEmpty())
			return c;

		ArrayList<News> filteredValues = new ArrayList<>();
		for (News n : c)
			if (mLastFilterCodes.contains(n.site_code))
				filteredValues.add(n);

		return filteredValues;
	}

	private String normalize(String s)
	{
		return Normalizer.normalize(s.toLowerCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	private Collection<News> applyTextFilter(Collection<News> c)
	{
		if (mLastFilter.isEmpty())
			return c;

		String normFilter = normalize(mLastFilter);
		ArrayList<News> filteredValues = new ArrayList<>();
		for (News n : c)
			if (normalize(n.title).contains(normFilter))
				filteredValues.add(n);

		return filteredValues;
	}

	public void filter(String filter)
	{
		init();

		ArrayList<News> searchableValues;
		if (mFilteredValues != null)
			searchableValues = mFilteredValues;
		else
			searchableValues = filter.startsWith(mLastFilter) && !mLastFilter.isEmpty() ? mLastQueryValues : mOriginalValues;

		mLastFilter = filter;
		mLastQueryValues = (ArrayList<News>) applyTextFilter(searchableValues);

		super.replaceAll(mLastQueryValues);
	}

	public void filter(TreeSet<Integer> filter)
	{
		init();

		mLastFilterCodes = filter;
		mFilteredValues = (ArrayList<News>) applyCodeFilter(mOriginalValues);
		mLastQueryValues = (ArrayList<News>) applyTextFilter(mFilteredValues);

		super.replaceAll(mLastQueryValues);
	}

	@Override
	public void setNewDataSet(Collection<News> c)
	{
		mOriginalValues = c instanceof ArrayList ? (ArrayList<News>) c : new ArrayList<>(c);
		super.setNewDataSet(applyFilters(c));
	}

	@Override
	public void addAll(Collection<News> c)
	{
		super.addAll(applyFilters(c));
		if (mOriginalValues != null)
			mOriginalValues.addAll(c);
	}

	@Override
	public void replaceAll(Collection<News> c)
	{
		mOriginalValues = c instanceof ArrayList ? (ArrayList<News>) c : new ArrayList<>(c);
		super.replaceAll(applyFilters(c));
	}

	@Override
	public void clear()
	{
		super.clear();
		mOriginalValues = null;
		mLastQueryValues = null;
		mLastFilter = "";
	}

}
