package com.lucevent.newsup.view.fragment;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.Main;
import com.lucevent.newsup.OnReplaceFragmentListener;
import com.lucevent.newsup.OnSettingsChangeListener;
import com.lucevent.newsup.ProSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.kernel.KernelManager;
import com.lucevent.newsup.kernel.ScheduleManager;
import com.lucevent.newsup.services.util.Download;
import com.lucevent.newsup.view.activity.SelectSitesActivity;

import java.text.DecimalFormat;
import java.util.Random;

public class AppSettingsFragment extends PreferenceFragment
		implements SharedPreferences.OnSharedPreferenceChangeListener {

	private static final int PREF_MAIN_SITES_MASK = 0x01;
	private static final int PREF_FAVORITES_MASK = 0x02;
	private static final int PREF_SCHEDULE_DOWNLOADS_MASK = 0x04;
	private static final int PREF_CLEAN_CACHE_MASK = 0x08;
	private static final int PREF_KEEP_NEWS_MASK = 0x10;

	private static final int REQUEST_SELECT_MAIN = 0;
	private static final int REQUEST_SELECT_FAVORITE = 1;
	private Preference.OnPreferenceClickListener onSelectMainSites = new Preference.OnPreferenceClickListener() {
		@Override
		public boolean onPreferenceClick(Preference preference)
		{
			Intent intent = new Intent(getActivity(), SelectSitesActivity.class);
			intent.putExtra(AppCode.TARGET, SelectSitesActivity.Target.SELECT_MAIN);
			startActivityForResult(intent, REQUEST_SELECT_MAIN);
			return true;
		}
	};
	private Preference.OnPreferenceClickListener onSelectFavoriteSites = new Preference.OnPreferenceClickListener() {
		@Override
		public boolean onPreferenceClick(Preference preference)
		{
			Intent intent = new Intent(getActivity(), SelectSitesActivity.class);
			intent.putExtra(AppCode.TARGET, SelectSitesActivity.Target.SELECT_FAVORITES);
			startActivityForResult(intent, REQUEST_SELECT_FAVORITE);
			return true;
		}
	};
	private Preference.OnPreferenceClickListener onSiteSettingsAction = new Preference.OnPreferenceClickListener() {
		@Override
		public boolean onPreferenceClick(Preference preference)
		{
			((Main) getActivity()).onReplaceFragment(new SitesSettingsFragment(), R.id.nav_settings, true);
			return true;
		}
	};
	private Preference.OnPreferenceClickListener onScheduleDownloadSettingsAction = new Preference.OnPreferenceClickListener() {
		@Override
		public boolean onPreferenceClick(Preference preference)
		{
			((OnReplaceFragmentListener) getActivity()).onReplaceFragment(new DownloadSettingsFragment(), R.id.nav_settings, true);
			return true;
		}
	};
	private Preference.OnPreferenceChangeListener onNightModeChange = new Preference.OnPreferenceChangeListener() {
		@Override
		public boolean onPreferenceChange(Preference preference, Object newValue)
		{
			AppCompatDelegate.setDefaultNightMode(
					(Boolean) newValue ?
							AppCompatDelegate.MODE_NIGHT_YES :
							AppCompatDelegate.MODE_NIGHT_NO);
			Activity a = getActivity();
			a.startActivity(new Intent(getActivity(), Main.class));
			a.finish();
			return true;
		}
	};

	@Override
	public void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.app_preferences);

		findPreference(AppSettings.PREF_MAIN_SITES_KEY).setOnPreferenceClickListener(onSelectMainSites);
		findPreference(AppSettings.PREF_FAVORITE_SITES_KEY).setOnPreferenceClickListener(onSelectFavoriteSites);
		findPreference(getString(R.string.pref_sites_settings_key)).setOnPreferenceClickListener(onSiteSettingsAction);
		findPreference(getString(R.string.pref_night_mode_key)).setOnPreferenceChangeListener(onNightModeChange);
		findPreference(AppSettings.PREF_SCHEDULE_DOWNLOADS_KEY).setOnPreferenceClickListener(onScheduleDownloadSettingsAction);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		if (container != null)
			container.removeAllViews();

		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onResume()
	{
		super.onResume();
		getActivity().setTitle(R.string.settings);
		setUpPreferenceSummaries(0xfff);
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onPause()
	{
		super.onPause();
		getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
	{
		if (key.equals(AppSettings.PREF_MAIN_SITES_KEY)) {
			setUpPreferenceSummaries(PREF_MAIN_SITES_MASK);
			((OnSettingsChangeListener) getActivity()).onMainPublicationsChange();
		} else if (key.equals(AppSettings.PREF_FAVORITE_SITES_KEY)) {
			((OnSettingsChangeListener) getActivity()).onFavoritesChange();
			setUpPreferenceSummaries(PREF_FAVORITES_MASK);
		} else if (key.equals(AppSettings.PREF_SCHEDULE_DOWNLOADS_KEY))
			setUpPreferenceSummaries(PREF_SCHEDULE_DOWNLOADS_MASK);
		else if (key.equals(AppSettings.PREF_CLEAN_CACHE_KEY))
			setUpPreferenceSummaries(PREF_CLEAN_CACHE_MASK);
		else if (key.equals(AppSettings.PREF_PRO_CODE_KEY)) {

			String code = sharedPreferences.getString(AppSettings.PREF_PRO_CODE_KEY, "");
			if (code.isEmpty()) return;

			int resIdMsg = ProSettings.checkProCode(code);

			AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
			if (resIdMsg == R.string.msg_invalid_code) {
				dialog.setMessage(resIdMsg)
						.setPositiveButton(R.string.ok, null)
						.show();
			} else {
				dialog.setTitle(resIdMsg)
						.setMessage(R.string.msg_app_must_restart_to_apply_changes)
						.setPositiveButton(R.string.restart, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which)
							{
								restartApp();
							}
						})
						.show();
			}
			((EditTextPreference) findPreference(AppSettings.PREF_PRO_CODE_KEY)).setText("");

		} else if (key.equals(AppSettings.PREF_KEEP_NEWS_KEY))
			setUpPreferenceSummaries(PREF_KEEP_NEWS_MASK);
		else if (key.equals(AppSettings.PREF_LOAD_IMAGES_KEY) || key.equals(AppSettings.PREF_COMPACTED_IMAGES_KEY))
			((OnSettingsChangeListener) getActivity()).onLoadImagesPreferenceChanged();
	}

	private void setUpPreferenceSummaries(int preferencesMask)
	{
		if ((preferencesMask & PREF_MAIN_SITES_MASK) != 0) {
			int[] codes = AppSettings.getMainSitesCodes();
			StringBuilder sb = new StringBuilder(AppData.getSiteByCode(codes[0]).name);
			for (int c = 1; c < codes.length; ++c) {
				Site s = AppData.getSiteByCode(codes[c]);
				if (s != null)
					sb.append(", ").append(s.name);
			}

			findPreference(AppSettings.PREF_MAIN_SITES_KEY).setSummary(sb);
		}
		if ((preferencesMask & PREF_FAVORITES_MASK) != 0) {
			findPreference(AppSettings.PREF_FAVORITE_SITES_KEY)
					.setSummary(getString(R.string.x_favorites, AppSettings.getFavoriteSitesCodes().length));
		}
		if ((preferencesMask & PREF_SCHEDULE_DOWNLOADS_MASK) != 0) {
			ScheduleManager scheduleManager = new ScheduleManager(getActivity());

			StringBuilder sb = new StringBuilder();
			for (Download schedule : scheduleManager.getSchedule()) {
				if (sb.length() != 0)
					sb.append("\n");
				sb.append(schedule.toShortString());
			}
			findPreference(AppSettings.PREF_SCHEDULE_DOWNLOADS_KEY).setSummary(sb);
		}
		if ((preferencesMask & PREF_CLEAN_CACHE_MASK) != 0) {

			String dataSize = new DecimalFormat("#0.00").format(KernelManager.getCacheSize() / 1048576.0);
			findPreference(AppSettings.PREF_CLEAN_CACHE_KEY).setSummary(dataSize + " MB");
		}
		if ((preferencesMask & PREF_KEEP_NEWS_MASK) != 0) {
			ListPreference p = (ListPreference) findPreference(AppSettings.PREF_KEEP_NEWS_KEY);

			CharSequence currentValue = p.getValue();
			int currentPosition = 0;
			for (CharSequence v : p.getEntryValues()) {
				if (v.equals(currentValue))
					break;
				currentPosition++;
			}
			p.setSummary(p.getEntries()[currentPosition]);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
			case REQUEST_SELECT_MAIN:
				((OnSettingsChangeListener) getActivity()).onMainPublicationsChange();
				break;
			case REQUEST_SELECT_FAVORITE:
				((OnSettingsChangeListener) getActivity()).onFavoritesChange();
				break;
		}
	}

	private void restartApp()
	{
		Intent intent = new Intent(getActivity(), Main.class);
		intent.putExtra(AppCode.RESTART, AppCode.RESTART);
		int requestCode = new Random().nextInt();
		PendingIntent mPendingIntent = PendingIntent.getActivity(getActivity(), requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		AlarmManager mgr = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
		mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
		System.exit(0);
	}

}
