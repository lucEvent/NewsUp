package com.lucevent.newsup.net;

import android.app.Activity;
import android.content.Context;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.event.Event;
import com.lucevent.newsup.data.event.Events;
import com.lucevent.newsup.data.event.Source;
import com.lucevent.newsup.kernel.AppData;

import java.util.ArrayList;
import java.util.Locale;

public final class EventsManager {

	public interface Callback {
		void onEventsRead(Events result);

		void onNoConnectionAvailable();
	}

	private static final String query_all_events = "http://newsup-2406.appspot.com/appv2?events&lang=%s";
	private final String version;
	private final String device_language;

	private static ConnectivityManager connectivityManager;

	public EventsManager(Context context)
	{
		this.version = context.getString(R.string.app_version);
		this.device_language = Locale.getDefault().getLanguage();
		connectivityManager = new ConnectivityManager(context);
	}

	public void readEvents(final Activity context, final Callback onResult)
	{
		new Thread(new Runnable() {
			@Override
			public void run()
			{
				fetchEvents(context, onResult);
			}
		}).start();
	}

	private void fetchEvents(Activity context, final Callback onResult)
	{
		if (!connectivityManager.isInternetAvailable()) {
			context.runOnUiThread(new Runnable() {
				@Override
				public void run()
				{
					onResult.onNoConnectionAvailable();
				}
			});
			return;
		}

		String eventsQuery = String.format(query_all_events, device_language);
		AppSettings.printlog("Query: " + eventsQuery);

		org.jsoup.nodes.Document doc = getDocument(eventsQuery);
		if (doc == null) return;

		final Events res = new Events();

		for (org.jsoup.nodes.Element jEvent : doc.select("event")) {
			Event e = new Event();
			e.code = Integer.parseInt(jEvent.attr("code"));
			e.title = jEvent.attr("title");
			e.topic = jEvent.attr("topic");
			e.imgSrc = jEvent.attr("imgsrc");
			e.keyWords = jEvent.attr("tags").split(",");
			e.sources = new ArrayList<>();

			String[] sources = jEvent.attr("sources").split(";");
			for (String s : sources) {
				String[] parts = s.split(",");
				Source source = new Source();
				source.site = AppData.getSiteByCode(Integer.parseInt(parts[0]));

				if (source.site == null)
					continue;

				source.sections = new int[parts.length - 1];
				for (int i = 0; i < source.sections.length; i++)
					source.sections[i] = Integer.parseInt(parts[i + 1]);

				e.sources.add(source);
			}

			res.add(e);
		}
		AppData.setEvents(res);

		context.runOnUiThread(new Runnable() {
			@Override
			public void run()
			{
				onResult.onEventsRead(res);
			}
		});
	}

	/**
	 * This call must be called in a thread different from the Master/UI thread
	 *
	 * @param eventCode
	 * @return the Event with code given, if any exists
	 */
	public Event fetchEvent(int eventCode)
	{
		if (!connectivityManager.isInternetAvailable())
			return null;

		String eventsQuery = String.format(query_all_events, device_language);
		AppSettings.printlog("Query: " + eventsQuery);

		org.jsoup.nodes.Document doc = getDocument(eventsQuery);
		if (doc == null) return null;

		for (org.jsoup.nodes.Element jEvent : doc.select("event")) {
			int code = Integer.parseInt(jEvent.attr("code"));
			if (code != eventCode)
				continue;

			Event e = new Event();
			e.code = code;
			e.title = jEvent.attr("title");
			e.topic = jEvent.attr("topic");
			e.imgSrc = jEvent.attr("imgsrc");
			e.keyWords = jEvent.attr("tags").split(",");
			e.sources = new ArrayList<>();

			String[] sources = jEvent.attr("sources").split(";");
			for (String s : sources) {
				String[] parts = s.split(",");
				Source source = new Source();
				source.site = AppData.getSiteByCode(Integer.parseInt(parts[0]));

				if (source.site == null)
					continue;

				source.sections = new int[parts.length - 1];
				for (int i = 0; i < source.sections.length; i++)
					source.sections[i] = Integer.parseInt(parts[i + 1]);

				e.sources.add(source);
			}

			return e;
		}

		return null;
	}

	private org.jsoup.nodes.Document getDocument(String url)
	{
		try {
			return org.jsoup.Jsoup.connect(url).parser(org.jsoup.parser.Parser.xmlParser()).timeout(10000).get();
		} catch (Exception e) {
			AppSettings.printerror("[NR] Can't read url: " + url, e);
		}
		return null;
	}

}
