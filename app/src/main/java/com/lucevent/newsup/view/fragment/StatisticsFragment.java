package com.lucevent.newsup.view.fragment;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.stats.Statistics;
import com.lucevent.newsup.services.StatisticsService;
import com.lucevent.newsup.view.adapter.StatisticsAdapter;

import java.lang.ref.WeakReference;

public class StatisticsFragment extends android.app.Fragment {

	public enum SortOrder {
		SORT_BY_NAME, SORT_BY_TOTAL_REQUESTS, SORT_BY_MONTH_REQUESTS, SORT_BY_TIME, SORT_BY_READINGS
	}

	public static final SortOrder DEFAULT_ORDER = SortOrder.SORT_BY_MONTH_REQUESTS;

	private Handler mHandler;

	private StatisticsAdapter mAdapter;

	private View mMainView;
	private View mLastSelectedBtn, mCurrentSelectedBtn;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);

		mHandler = new Handler(this);
	}

	@Override
	public void onAttach(Context context)
	{
		super.onAttach(context);

		Toolbar ab = (Toolbar) getActivity().findViewById(R.id.toolbar);
		ab.setBackgroundResource(R.color.colorPrimaryDark);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (container != null)
			container.removeAllViews();

		if (mMainView == null) {
			mMainView = inflater.inflate(R.layout.f_statistics, container, false);

			mAdapter = new StatisticsAdapter();

			RecyclerView recyclerView = (RecyclerView) mMainView.findViewById(R.id.list);
			recyclerView.setNestedScrollingEnabled(false);
			recyclerView.setHasFixedSize(false);
			recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
			recyclerView.setAdapter(mAdapter);

			mMainView.findViewById(R.id.sort_name).setOnClickListener(onOrderChanged);
			mMainView.findViewById(R.id.sort_total_requests).setOnClickListener(onOrderChanged);
			mMainView.findViewById(R.id.sort_month_requests).setOnClickListener(onOrderChanged);
			mMainView.findViewById(R.id.sort_readings).setOnClickListener(onOrderChanged);
			mMainView.findViewById(R.id.sort_time).setOnClickListener(onOrderChanged);

			mLastSelectedBtn = mCurrentSelectedBtn = mMainView.findViewById(R.id.sort_month_requests);
		}
		return mMainView;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		menu.add(R.string.reset)
				.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
				.setIcon(R.drawable.ic_remove_all)
				.setOnMenuItemClickListener(onResetAction);

		super.onCreateOptionsMenu(menu, inflater);
	}

	private EditText mResetPasswordEditText;

	private MenuItem.OnMenuItemClickListener onResetAction = new MenuItem.OnMenuItemClickListener() {
		@Override
		public boolean onMenuItemClick(MenuItem item)
		{
			if (service.isInternetAvailable()) {
				Context c = getActivity();

				View view = LayoutInflater.from(c).inflate(R.layout.v_dialog_password, null, false);
				mResetPasswordEditText = (EditText) view.findViewById(R.id.input);
				new AlertDialog.Builder(c)
						.setView(view)
						.setNegativeButton(R.string.cancel, null)
						.setPositiveButton(R.string.ok, onResetActionPasswordEntered)
						.show();
			} else
				notifyNoInternet();
			return true;
		}
	};

	private DialogInterface.OnClickListener onResetActionPasswordEntered = new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which)
		{
			new AlertDialog.Builder(getActivity())
					.setMessage(R.string.msg_confirm_to_reset_statistics)
					.setNegativeButton(R.string.cancel, null)
					.setPositiveButton(R.string.reset, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialogInterface, int i)
						{
							service.resetStatistics(mHandler, mResetPasswordEditText.getText().toString());
						}
					})
					.show();
		}
	};

	private View.OnClickListener onOrderChanged = new View.OnClickListener() {
		@Override
		public void onClick(View v)
		{
			mLastSelectedBtn = mCurrentSelectedBtn;
			mCurrentSelectedBtn = v;

			if (service.isInternetAvailable()) {
				SortOrder order = DEFAULT_ORDER;
				switch (v.getId()) {
					case R.id.sort_name:
						order = SortOrder.SORT_BY_NAME;
						break;
					case R.id.sort_total_requests:
						order = SortOrder.SORT_BY_TOTAL_REQUESTS;
						break;
					case R.id.sort_month_requests:
						order = SortOrder.SORT_BY_MONTH_REQUESTS;
						break;
					case R.id.sort_readings:
						order = SortOrder.SORT_BY_READINGS;
						break;
					case R.id.sort_time:
						order = SortOrder.SORT_BY_TIME;
						break;
				}
				service.getStatistics(mHandler, order);
			} else
				notifyNoInternet();
		}
	};

	private void notifyNoInternet()
	{
		Snackbar.make(mMainView, R.string.msg_no_internet_connection, Snackbar.LENGTH_LONG).show();
	}

	static class Handler extends android.os.Handler {

		private final WeakReference<StatisticsFragment> context;

		Handler(StatisticsFragment context)
		{
			this.context = new WeakReference<>(context);
		}

		@Override
		public void handleMessage(Message msg)
		{
			StatisticsFragment service = context.get();
			switch (msg.what) {
				case AppCode.STATISTICS:
					service.mLastSelectedBtn.setEnabled(true);
					service.mAdapter.setNewDataSet((Statistics) msg.obj);
					service.mCurrentSelectedBtn.setEnabled(false);
					break;
				default:
					AppSettings.printerror("[SF] OPTION UNKNOWN: " + msg.what, null);
			}
		}

	}

	//////////////////////////////////////////////
	///////////////// Service set-up /////////////
	//////////////////////////////////////////////
	private StatisticsService service;
	private boolean serviceBound = false;

	@Override
	public void onStart()
	{
		super.onStart();
		Intent intent = new Intent(getActivity(), StatisticsService.class);
		getActivity().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
	}

	@Override
	public void onStop()
	{
		super.onStop();
		if (serviceBound) {
			getActivity().unbindService(serviceConnection);
			serviceBound = false;
		}
	}

	/**
	 * Defines callbacks for service binding, passed to bindService()
	 */
	private ServiceConnection serviceConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName className,
		                               IBinder ibinder)
		{
			service = ((StatisticsService.Binder) ibinder).getService();
			serviceBound = true;
			if (service.isInternetAvailable())
				service.getStatistics(mHandler, DEFAULT_ORDER);
			else
				notifyNoInternet();
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0)
		{
			serviceBound = false;
		}
	};

}
