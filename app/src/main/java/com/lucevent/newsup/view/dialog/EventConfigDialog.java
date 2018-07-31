package com.lucevent.newsup.view.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.event.Event;
import com.lucevent.newsup.data.event.Source;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.ScheduleManager;
import com.lucevent.newsup.services.util.Download;
import com.lucevent.newsup.view.activity.DownloadEditorActivity;
import com.lucevent.newsup.view.util.FilterCheckBox;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

public class EventConfigDialog implements DialogInterface.OnClickListener {

	public interface Callback {
		void onFilter(TreeSet<Integer> f);
	}

	private Activity mContext;
	private EventConfigDialog.Callback mCallback;
	private ScheduleManager mScheduleManager;
	private Event mEvent;
	private TextView mDSSummary;

	public EventConfigDialog(Activity context)
	{
		mContext = context;
		mScheduleManager = new ScheduleManager(context);
	}

	public EventConfigDialog event(Event e)
	{
		mEvent = e;
		return this;
	}

	public EventConfigDialog listener(EventConfigDialog.Callback callback)
	{
		mCallback = callback;
		return this;
	}

	private ArrayList<FilterCheckBox> mCheckBoxes;
	private AlertDialog mDialog;

	public void show()
	{
		if (mDialog == null)
			create();

		Download ds = mScheduleManager.getSpecialSchedule(mEvent.code);
		if (ds == null)
			mDSSummary.setVisibility(View.GONE);
		else {
			mDSSummary.setVisibility(View.VISIBLE);
			mDSSummary.setText(ds.toShortString());
		}

		mDialog.show();
	}

	private void create()
	{
		Resources r = mContext.getResources();
		String[] titles_languages = r.getStringArray(R.array.site_languages);
		int[] language_weights = r.getIntArray(R.array.site_language_weights);
		TreeSet<Integer> filterCodes = AppSettings.getEventFilter(mEvent.code);

		TreeMap<Integer, Site> languageCodes = new TreeMap<>();

		for (Source s : mEvent.sources)
			languageCodes.put(language_weights[s.site.getLanguage() - 1], s.site);

		LayoutInflater inflater = LayoutInflater.from(mContext);
		View view = inflater.inflate(R.layout.d_event_config, null);
		view.findViewById(R.id.btn_schedule_download).setOnClickListener(onScheduleDownload);
		mDSSummary = (TextView) view.findViewById(R.id.summary_schedule_download);

		ViewGroup vgLanguages = (ViewGroup) view.findViewById(R.id.l_languages);
		mCheckBoxes = new ArrayList<>();
		for (Site s : languageCodes.values()) {
			FilterCheckBox fcb = new FilterCheckBox(mContext, null);
			fcb.setText(titles_languages[s.getLanguage() - 1]);
			fcb.setTag(s.getLanguage());
			fcb.setChecked(filterCodes == null || filterCodes.contains(s.code));
			vgLanguages.addView(fcb);
			mCheckBoxes.add(fcb);
		}

		mDialog = new AlertDialog.Builder(mContext)
				.setView(view)
				.setPositiveButton(R.string.done, this)
				.create();
	}

	private View.OnClickListener onScheduleDownload = new View.OnClickListener() {
		@Override
		public void onClick(View v)
		{
			mDialog.dismiss();

			Download download = mScheduleManager.getSpecialSchedule(mEvent.code);

			Intent intent = new Intent(mContext, DownloadEditorActivity.class);
			if (download == null)
				intent.putExtra(AppCode.ACTION, DownloadEditorActivity.ACTION_CREATE_EVENT);
			else {
				intent.putExtra(AppCode.ACTION, DownloadEditorActivity.ACTION_MODIFY_EVENT);
				intent.putExtra(AppCode.SCHEDULE_ID, download.id);
			}
			intent.putExtra(AppCode.EVENT_CODE, mEvent.code);

			mContext.startActivity(intent);
		}
	};

	@Override
	public void onClick(DialogInterface dialog, int which)
	{
		TreeSet<Integer> filter = new TreeSet<>();

		for (FilterCheckBox cb : mCheckBoxes)
			if (cb.isChecked()) {
				int lang = (int) cb.getTag();
				for (Source s : mEvent.sources)
					if (s.site.getLanguage() == lang)
						filter.add(s.site.code);
			}

		if (filter.isEmpty()) {
			Toast.makeText(mContext, R.string.msg_cant_apply_filter, Toast.LENGTH_SHORT).show();
			return;
		}

		if (filter.size() == mCheckBoxes.size())
			filter.clear();

		mCallback.onFilter(filter);
		AppSettings.setEventFilter(mEvent.code, filter.isEmpty() ? null : filter);
	}

}
