package com.lucevent.newsup.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import com.lucevent.newsup.Main;
import com.lucevent.newsup.R;
import com.lucevent.newsup.kernel.KernelManager;
import com.lucevent.newsup.services.util.DownloadData;
import com.lucevent.newsup.view.adapter.NotificationAdapter;

import java.util.ArrayList;

public class NotificationsFragment extends android.app.Fragment {

	private NotificationAdapter mAdapter;
	private KernelManager mDataManager;

	private View mMainView, mNoContentMessage;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (container != null)
			container.removeAllViews();

		if (mMainView == null) {
			Context context = getActivity();

			View view = inflater.inflate(R.layout.f_list, container, false);

			mNoContentMessage = view.findViewById(R.id.no_content);

			mDataManager = new KernelManager(context);
			ArrayList<DownloadData> notifications = mDataManager.getDatabaseManager().readNotifications();

			if (notifications.isEmpty()) {
				displayNoRecordsMessage();
				return view;
			}

			mAdapter = new NotificationAdapter(context, notifications,
					onNotificationClickListener, onDeleteClickListener);

			LinearLayoutManager layoutManager = new LinearLayoutManager(context);
			layoutManager.setAutoMeasureEnabled(true);

			RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
			recyclerView.setHasFixedSize(false);
			recyclerView.setLayoutManager(layoutManager);
			recyclerView.setAdapter(mAdapter);

			mMainView = view;
		}
		getActivity().setTitle(R.string.notifications);
		return mMainView;
	}

	private View.OnClickListener onNotificationClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v)
		{
			NewsListFragment newsFragment = NewsListFragment.instanceFor((DownloadData) v.getTag());

			((Main) getActivity()).onReplaceFragment(
					newsFragment,
					R.id.nav_notifications,
					true);
		}
	};

	private View.OnClickListener onDeleteClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v)
		{
			DownloadData data = (DownloadData) v.getTag();
			mDataManager.delete(data);
			mAdapter.remove(data);

			if (mAdapter.getItemCount() == 0)
				displayNoRecordsMessage();
		}
	};

	private void displayNoRecordsMessage()
	{
		if (mNoContentMessage instanceof ViewStub)
			mNoContentMessage = ((ViewStub) mNoContentMessage).inflate();

		((TextView) mNoContentMessage.findViewById(R.id.message)).setText(R.string.msg_no_history);
	}

}
