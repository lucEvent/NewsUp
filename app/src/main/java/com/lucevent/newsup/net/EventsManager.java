package com.lucevent.newsup.net;

import android.app.Activity;
import android.content.Context;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.event.Event;
import com.lucevent.newsup.data.event.Events;
import com.lucevent.newsup.data.event.Source;
import com.lucevent.newsup.kernel.AppData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public final class EventsManager implements BackendNames {

	public interface Callback {
		void onEventsRead(Events result);

		void onNoConnectionAvailable();
	}

	private final String mAppVersion;

	private static ConnectivityManager mConnectivityManager;

	public EventsManager(Context context)
	{
		mAppVersion = context.getString(R.string.app_version);
		mConnectivityManager = new ConnectivityManager(context);
	}

	public void readEvents(final Activity context, final String[] regionCodes, final Callback onResult)
	{
		new Thread(new Runnable() {
			@Override
			public void run()
			{
				fetchEvents(context, regionCodes, onResult);
			}
		}).start();
	}

	private void fetchEvents(Activity context, String[] regionCodes, final Callback onResult)
	{
		if (!mConnectivityManager.isInternetAvailable()) {
			context.runOnUiThread(new Runnable() {
				@Override
				public void run()
				{
					onResult.onNoConnectionAvailable();
				}
			});
			return;
		}

		StringBuilder sb = new StringBuilder(regionCodes[0]);
		for (int i = 1; i < regionCodes.length; i++)
			sb.append(",").append(regionCodes[i]);

		try {
			sb = RawContentReader.getUrl(
					MAIN_APP_SERVER + "?events=" + sb.toString() + "&v=" + mAppVersion
			);

			final Events res = new Events();

			JSONArray json = new JSONArray(sb.toString());
			for (int i = 0; i < json.length(); i++)
				res.add(parseEvent((JSONObject) json.get(i)));

			AppData.setEvents(res);

			context.runOnUiThread(new Runnable() {
				@Override
				public void run()
				{
					onResult.onEventsRead(res);
				}
			});
		} catch (Exception e) {
			AppSettings.printerror("[EM] Error on Events Manager", e);
		}
	}

	private Event parseEvent(JSONObject jEvent) throws JSONException
	{
		Event e = new Event();
		e.code = jEvent.getInt("code");
		e.title = jEvent.getString("title");
		e.imgSrc = jEvent.getString("imgsrc");
		e.keyWords = new ArrayList<>(Arrays.asList(jEvent.getString("tags").split(",")));
		e.sources = new ArrayList<>();

		JSONArray jSources = jEvent.getJSONArray("sources");
		for (int j = 0; j < jSources.length(); j++) {
			Source source = new Source();
			JSONObject jSource = (JSONObject) jSources.get(j);
			source.site = AppData.getSiteByCode(jSource.getInt("si_c"));

			if (source.site == null)
				continue;

			JSONArray jSectionCodes = jSource.getJSONArray("se_c");
			source.sections = new int[jSectionCodes.length()];
			for (int k = 0; k < jSectionCodes.length(); k++)
				source.sections[k] = jSectionCodes.getInt(k);

			e.sources.add(source);
		}
		return e;
	}

}
