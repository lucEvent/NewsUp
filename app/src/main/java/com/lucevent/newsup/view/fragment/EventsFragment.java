package com.lucevent.newsup.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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
import android.widget.Toast;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.ProSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.event.Event;
import com.lucevent.newsup.data.event.Events;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.net.EventsManager;
import com.lucevent.newsup.services.StatisticsService;
import com.lucevent.newsup.view.activity.EventActivity;
import com.lucevent.newsup.view.adapter.EventsAdapter;

public class EventsFragment extends android.app.Fragment implements View.OnClickListener, EventsManager.Callback {

	private EventsManager mEventsManager;
	private EventsAdapter mAdapter;

	private View mNoContentMessage;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);

		mAdapter = new EventsAdapter(this);

		mEventsManager = new EventsManager(getActivity());
		mEventsManager.readEvents(getActivity(), AppSettings.getEventsLocaleSetting(), this);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		super.onCreateOptionsMenu(menu, inflater);

		String[] regionsSelected = AppSettings.getEventsLocaleSetting();

		String[] titles = getResources().getStringArray(R.array.event_region_titles);
		String[] codes = getResources().getStringArray(R.array.event_region_codes);

		for (int i = 0; i < titles.length; i++) {
			boolean sel = false;
			for (String r : regionsSelected)
				if (codes[i].equals(r)) {
					sel = true;
					break;
				}

			menu.add(0, i, i, titles[i])
					.setCheckable(true)
					.setChecked(sel);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		super.onOptionsItemSelected(item);
		String region_code = getResources().getStringArray(R.array.event_region_codes)[item.getOrder()];

		if (item.isChecked()) {
			if (!AppSettings.removeEventsLocaleSetting(region_code)) {
				Toast.makeText(getActivity(), R.string.msg_must_select_at_least_one, Toast.LENGTH_SHORT).show();
				return true;
			}
			item.setChecked(false);
		} else {
			item.setChecked(true);
			AppSettings.addEventsLocaleSetting(region_code);
		}
		mEventsManager.readEvents(getActivity(), AppSettings.getEventsLocaleSetting(), this);
		return true;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (container != null)
			container.removeAllViews();

		View view = inflater.inflate(R.layout.f_list, container, false);

		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
		recyclerView.setNestedScrollingEnabled(false);
		recyclerView.setHasFixedSize(false);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.setAdapter(mAdapter);

		mNoContentMessage = view.findViewById(R.id.no_content);

		return view;
	}

	@Override
	public void onEventsRead(Events result)
	{
		mAdapter.setNewsDataSet(result);
	}

	@Override
	public void onNoConnectionAvailable()
	{
		if (mNoContentMessage != null) {
			displayNoContentMessage();
			Snackbar.make(mNoContentMessage, R.string.msg_no_internet_connection, Snackbar.LENGTH_LONG).show();
		}
	}

	@Override
	public void onClick(View v)
	{
		Event e = (Event) v.getTag();

		if (!ProSettings.isDeveloperModeEnabled()) {
			Context c = getActivity();
			c.startService(
					new Intent(c, StatisticsService.class)
							.putExtra(AppCode.REQUEST_CODE, StatisticsService.REQ_EVENT)
							.putExtra(AppCode.EVENT_CODE, e.code)
			);
		}

		Intent intent = new Intent(getActivity(), EventActivity.class);
		intent.putExtra(AppCode.EVENT_CODE, e.code);
		startActivity(intent);
	}

	private void displayNoContentMessage()
	{
		if (mNoContentMessage instanceof ViewStub)
			mNoContentMessage = ((ViewStub) mNoContentMessage).inflate();

		((TextView) mNoContentMessage.findViewById(R.id.message)).setText(R.string.msg_no_events);
	}

}
