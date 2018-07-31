package com.lucevent.newsup.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

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

	private EventsManager eventsManager;
	private EventsAdapter adapter;

	private View noContentMessage;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//       setHasOptionsMenu(true);

		adapter = new EventsAdapter(this);

		eventsManager = new EventsManager(getActivity());
		eventsManager.readEvents(getActivity(), this);
	}
/*
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        menu.add(R.string.add)
                .setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
                .setIcon(R.drawable.ic_add)
                .setOnMenuItemClickListener(onCreateEvent);

        super.onCreateOptionsMenu(menu, inflater);
    }
*/

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (container != null)
			container.removeAllViews();

		View view = inflater.inflate(R.layout.f_list, container, false);

		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setAutoMeasureEnabled(true);

		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
		recyclerView.setNestedScrollingEnabled(false);
		recyclerView.setHasFixedSize(false);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setAdapter(adapter);

		noContentMessage = view.findViewById(R.id.no_content);

		return view;
	}


	@Override
	public void onEventsRead(Events result)
	{
		adapter.addAll(result);
	}

	@Override
	public void onNoConnectionAvailable()
	{
		if (noContentMessage != null) {
			displayNoContentMessage();
			Snackbar.make(noContentMessage, R.string.msg_no_internet_connection, Snackbar.LENGTH_LONG).show();
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
		if (noContentMessage instanceof ViewStub)
			noContentMessage = ((ViewStub) noContentMessage).inflate();

		((TextView) noContentMessage.findViewById(R.id.message)).setText(R.string.msg_no_events);
	}

}
