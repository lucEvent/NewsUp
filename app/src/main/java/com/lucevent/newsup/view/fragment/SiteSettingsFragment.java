package com.lucevent.newsup.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.SiteMain;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.io.LogoManager;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.kernel.ScheduleManager;
import com.lucevent.newsup.services.util.Download;
import com.lucevent.newsup.view.activity.DownloadEditorActivity;
import com.lucevent.newsup.view.preference.SectionsMultiSelectPreference;

import java.util.Set;

public class SiteSettingsFragment extends android.preference.PreferenceFragment
		implements SharedPreferences.OnSharedPreferenceChangeListener {

	public static SiteSettingsFragment instanceFor(int site_code)
	{
		Bundle bundle = new Bundle();
		bundle.putInt(AppCode.SITE_CODE, site_code);

		SiteSettingsFragment fragment = new SiteSettingsFragment();
		fragment.setArguments(bundle);
		return fragment;
	}

	private String PREF_SHOW_IN_MAIN_KEY;
	private String PREF_FAVORITE_KEY;
	private String PREF_MAIN_SECTIONS_KEY;
	private String PREF_DOWNLOAD_SECTIONS_KEY;

	private Site currentSite;
	private ScheduleManager scheduleManager;

	@Override
	public void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.site_preferences);

		PREF_SHOW_IN_MAIN_KEY = getString(R.string.pref_show_in_my_feed_key);
		PREF_FAVORITE_KEY = getString(R.string.pref_favorite_key);
		PREF_MAIN_SECTIONS_KEY = getString(R.string.pref_main_sections_key);
		PREF_DOWNLOAD_SECTIONS_KEY = getString(R.string.pref_download_sections_key);

		currentSite = AppData.getSiteByCode(getArguments().getInt(AppCode.SITE_CODE));
		scheduleManager = new ScheduleManager(getActivity());
		getActivity().setTitle(getString(R.string.title_frg_settings_of, currentSite.name));

		boolean inMain = AppSettings.isShownInMain(currentSite);
		boolean favorite = AppSettings.isFavorite(currentSite);

		((SwitchPreference) findPreference(PREF_SHOW_IN_MAIN_KEY)).setChecked(inMain);
		((SwitchPreference) findPreference(PREF_FAVORITE_KEY)).setChecked(favorite);

		((SectionsMultiSelectPreference) findPreference(PREF_MAIN_SECTIONS_KEY)).setSite(currentSite);
		((SectionsMultiSelectPreference) findPreference(PREF_DOWNLOAD_SECTIONS_KEY)).setSite(currentSite);

		setSectionsSummary(AppSettings.getMainSectionsString(currentSite), PREF_MAIN_SECTIONS_KEY);
		setSectionsSummary(AppSettings.getDownloadSectionsString(currentSite), PREF_DOWNLOAD_SECTIONS_KEY);
		setDownloadScheduleSummary();

		findPreference(getString(R.string.pref_shortcut_key)).setOnPreferenceClickListener(onCreateShortcut);
		findPreference(getString(R.string.pref_schedule_one_download_key)).setOnPreferenceClickListener(onScheduleDownload);
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
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onPause()
	{
		super.onPause();
		getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences preferences, String key)
	{
		if (key.equals(PREF_SHOW_IN_MAIN_KEY))
			AppSettings.toggleShownInMain(currentSite);

		else if (key.equals(PREF_FAVORITE_KEY))
			AppSettings.toggleFavorite(currentSite, true);

		else if (key.equals(PREF_MAIN_SECTIONS_KEY)) {
			AppSettings.setMainSections(currentSite,
					preferences.getStringSet(PREF_MAIN_SECTIONS_KEY, null));
			setSectionsSummary(AppSettings.getMainSectionsString(currentSite), PREF_MAIN_SECTIONS_KEY);
		} else if (key.equals(PREF_DOWNLOAD_SECTIONS_KEY)) {
			AppSettings.setDownloadSections(currentSite,
					preferences.getStringSet(PREF_DOWNLOAD_SECTIONS_KEY, null));
			setSectionsSummary(AppSettings.getDownloadSectionsString(currentSite), PREF_DOWNLOAD_SECTIONS_KEY);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		setDownloadScheduleSummary();
	}

	private void setSectionsSummary(Set<String> s, String key)
	{
		boolean _default = s.size() == 1 && s.iterator().next().equals("0");

		findPreference(key).setSummary(
				_default ? getString(R.string.by_default) : getString(R.string.x_section_selected, s.size())
		);
	}

	private void setDownloadScheduleSummary()
	{
		Download s = scheduleManager.getSpecialSchedule(currentSite.code);

		findPreference(getString(R.string.pref_schedule_one_download_key))
				.setSummary(
						s == null ? "" : s.toShortString()
				);
	}

	private Preference.OnPreferenceClickListener onCreateShortcut = new Preference.OnPreferenceClickListener() {
		@Override
		public boolean onPreferenceClick(Preference preference)
		{
			Context context = getActivity();

			Intent shortcutIntent = new Intent(context, SiteMain.class);
			shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
			shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			shortcutIntent.setAction(Intent.ACTION_MAIN);
			shortcutIntent.putExtra(AppCode.SITE_CODE, currentSite.code);

			Intent addIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
			addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
			addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
			addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, currentSite.name);
			addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON, LogoManager.homeScreenIcon(context, currentSite.code));
			context.sendBroadcast(addIntent);

			Toast.makeText(context, getString(R.string.msg_shortcut_created, currentSite.name), Toast.LENGTH_SHORT).show();

			return true;
		}
	};

	private Preference.OnPreferenceClickListener onScheduleDownload = new Preference.OnPreferenceClickListener() {
		@Override
		public boolean onPreferenceClick(Preference preference)
		{
			Download download = scheduleManager.getSpecialSchedule(currentSite.code);

			Intent intent = new Intent(getActivity(), DownloadEditorActivity.class);

			if (download == null)
				intent.putExtra(AppCode.ACTION, DownloadEditorActivity.ACTION_CREATE_ONE);
			else {
				intent.putExtra(AppCode.ACTION, DownloadEditorActivity.ACTION_MODIFY_ONE);
				intent.putExtra(AppCode.SCHEDULE_ID, download.id);
			}
			intent.putExtra(AppCode.SITE_CODE, currentSite.code);
			startActivityForResult(intent, 0);
			return true;
		}
	};


}
