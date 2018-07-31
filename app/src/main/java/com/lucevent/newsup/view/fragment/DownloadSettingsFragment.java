package com.lucevent.newsup.view.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucevent.newsup.R;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.ScheduleManager;
import com.lucevent.newsup.services.ScheduledDownloadReceiver;
import com.lucevent.newsup.services.util.Download;
import com.lucevent.newsup.view.activity.DownloadEditorActivity;
import com.lucevent.newsup.view.adapter.DownloadScheduleAdapter;

public class DownloadSettingsFragment extends Fragment {

	private ScheduleManager dataManager;
	private DownloadScheduleAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		dataManager = new ScheduleManager(getActivity());
		adapter = new DownloadScheduleAdapter(dataManager.getSchedule(), onModifyAction, onDeleteAction);

	}

	private View noDownloadsScreen;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (container != null)
			container.removeAllViews();

		View view = inflater.inflate(R.layout.f_schedule_download_settings, container, false);

		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setAutoMeasureEnabled(true);

		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
		recyclerView.setNestedScrollingEnabled(false);
		recyclerView.setHasFixedSize(false);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setAdapter(adapter);

		noDownloadsScreen = view.findViewById(R.id.no_downloads);

		if (adapter.getItemCount() == 0)
			noDownloadsScreen.setVisibility(View.VISIBLE);

		view.findViewById(R.id.btn_add).setOnClickListener(onAddAction);

		return view;
	}

	@Override
	public void onResume()
	{
		super.onResume();
		getActivity().setTitle(R.string.pref_schedule_downloads);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			adapter.notifyDataSetChanged();

			ScheduledDownloadReceiver.scheduleDownloads(getActivity(),
					dataManager.getSchedule());

			if (noDownloadsScreen.getVisibility() == View.VISIBLE)
				noDownloadsScreen.setVisibility(View.GONE);
		}
	}

	private View.OnClickListener onAddAction = new View.OnClickListener() {
		@Override
		public void onClick(View v)
		{
			Intent intent = new Intent(getActivity(), DownloadEditorActivity.class);
			intent.putExtra(AppCode.ACTION, DownloadEditorActivity.ACTION_CREATE);
			startActivityForResult(intent, 0);
		}
	};

	private View.OnClickListener onModifyAction = new View.OnClickListener() {
		@Override
		public void onClick(View v)
		{
			Intent intent = new Intent(getActivity(), DownloadEditorActivity.class);
			intent.putExtra(AppCode.SCHEDULE_ID, ((Download) v.getTag()).id);
			intent.putExtra(AppCode.ACTION, DownloadEditorActivity.ACTION_MODIFY);
			startActivityForResult(intent, 0);
		}
	};

	private View.OnClickListener onDeleteAction = new View.OnClickListener() {
		@Override
		public void onClick(final View v)
		{
			new AlertDialog.Builder(getActivity())
					.setTitle(R.string.msg_confirm_to_delete)
					.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which)
						{

							Download schedule = (Download) v.getTag();
							adapter.remove(schedule);
							dataManager.deleteSchedule(schedule);
							ScheduledDownloadReceiver.scheduleDownloads(getActivity(), dataManager.getSchedule());

							if (adapter.getItemCount() == 0)
								noDownloadsScreen.setVisibility(View.VISIBLE);
						}
					})
					.setNegativeButton(R.string.cancel, null)
					.show();
		}
	};

}
