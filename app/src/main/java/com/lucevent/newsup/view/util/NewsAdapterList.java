package com.lucevent.newsup.view.util;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;

import com.lucevent.newsup.data.util.News;

public class NewsAdapterList extends SortedList<News> {

	public NewsAdapterList(final RecyclerView.Adapter adapter, SortBy sortBy)
	{
		super(News.class, new CallBack(adapter, sortBy));
	}

	public enum SortBy {
		byTime, byReadOn
	}

	private static class CallBack extends SortedList.Callback<News> {

		private final RecyclerView.Adapter adapter;
		private final SortBy sortBy;

		CallBack(RecyclerView.Adapter adapter, SortBy sortBy)
		{
			this.adapter = adapter;
			this.sortBy = sortBy;
		}

		@Override
		public int compare(News o1, News o2)
		{
			switch (sortBy) {
				case byTime:
					int comparison = Long.compare(o1.date, o2.date);
					return comparison != 0 ? -comparison : Integer.compare(o1.id, o2.id);

				case byReadOn:
					return -Long.compare(o1.readOn, o2.readOn);
			}
			return o1.compareTo(o2);
		}

		@Override
		public void onInserted(int position, int count)
		{
			adapter.notifyItemRangeInserted(position, count);
		}

		@Override
		public void onRemoved(int position, int count)
		{
			adapter.notifyItemRangeRemoved(position, count);
		}

		@Override
		public void onMoved(int fromPosition, int toPosition)
		{
			adapter.notifyItemMoved(fromPosition, toPosition);
		}

		@Override
		public void onChanged(int position, int count)
		{
			adapter.notifyItemRangeChanged(position, count);
		}

		@Override
		public boolean areContentsTheSame(News oldItem, News newItem)
		{
			return oldItem.id == newItem.id;
		}

		@Override
		public boolean areItemsTheSame(News item1, News item2)
		{
			return item1.id == item2.id;
		}

	}

}
