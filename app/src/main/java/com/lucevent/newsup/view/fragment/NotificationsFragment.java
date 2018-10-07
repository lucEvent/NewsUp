package com.lucevent.newsup.view.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);

		mDataManager = new KernelManager(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (container != null)
			container.removeAllViews();

		if (mMainView == null) {
			Context context = getActivity();

			View view = inflater.inflate(R.layout.f_list, container, false);

			mNoContentMessage = view.findViewById(R.id.no_content);

			ArrayList<DownloadData> notifications = mDataManager.getDatabaseManager().readNotifications();
			if (notifications.isEmpty()) {
				displayNoRecordsMessage();
				return view;
			}

			mAdapter = new NotificationAdapter(context, notifications,
					mOnNotificationClickListener, mOnDeleteClickListener);

			RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
			recyclerView.setHasFixedSize(false);
			recyclerView.setLayoutManager(new LinearLayoutManager(context));
			recyclerView.setAdapter(mAdapter);

			mMainView = view;
		}
		getActivity().setTitle(R.string.notifications);
		return mMainView;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		menu.add(R.string.menu_delete_all)
				.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
				.setIcon(R.drawable.ic_remove_all)
				.setOnMenuItemClickListener(mOnDeleteAllAction);

		super.onCreateOptionsMenu(menu, inflater);
	}

	private View.OnClickListener mOnNotificationClickListener = new View.OnClickListener() {
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

	private View.OnClickListener mOnDeleteClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v)
		{
			DownloadData data = (DownloadData) v.getTag();
			mDataManager.getDatabaseManager().delete(data);
			mAdapter.remove(data);

			if (mAdapter.getItemCount() == 0)
				displayNoRecordsMessage();
		}
	};

	private MenuItem.OnMenuItemClickListener mOnDeleteAllAction = new MenuItem.OnMenuItemClickListener() {
		@Override
		public boolean onMenuItemClick(MenuItem item)
		{
			if (mAdapter.getItemCount() > 0)
				new AlertDialog.Builder(getActivity())
						.setMessage(R.string.msg_confirm_to_clear_notifications)
						.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which)
							{
								mDataManager.getDatabaseManager().clearDownloadData();
								mAdapter.clear();
								displayNoRecordsMessage();
							}
						})
						.setNegativeButton(R.string.cancel, null)
						.show();
			return true;
		}
	};

	private void displayNoRecordsMessage()
	{
		if (mNoContentMessage instanceof ViewStub)
			mNoContentMessage = ((ViewStub) mNoContentMessage).inflate();

		((TextView) mNoContentMessage.findViewById(R.id.message)).setText(R.string.msg_no_history);
	}

}
