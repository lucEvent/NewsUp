package com.lucevent.newsup.view.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.kernel.NewsManager;
import com.lucevent.newsup.kernel.ScheduleManager;
import com.lucevent.newsup.services.util.DownloadSchedule;

import java.text.DecimalFormat;

public class AppSettingsFragment extends PreferenceFragment
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final int PREF_MASK_MAIN_SITES = 0x01;
    private static final int PREF_MASK_SCHEDULE_DOWNLOADS = 0x02;
    private static final int PREF_MASK_CLEAN_CACHE = 0x04;
    private static final int PREF_MASK_DEV_MODE = 0x08;

    @Override
    public void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.app_preferences);

        findPreference(getString(R.string.pref_sites_settings_key)).setOnPreferenceClickListener(onSiteSettingsAction);
        findPreference(AppSettings.PREF_SCHEDULE_DOWNLOADS_KEY).setOnPreferenceClickListener(onScheduleDownloadSettingsAction);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        getActivity().setTitle(R.string.settings);
        setUpPreferenceSummaries(0xff);
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
        if (key.equals(AppSettings.PREF_MAIN_SITES_KEY))
            setUpPreferenceSummaries(PREF_MASK_MAIN_SITES);

        else if (key.equals(AppSettings.PREF_SCHEDULE_DOWNLOADS_KEY))
            setUpPreferenceSummaries(PREF_MASK_SCHEDULE_DOWNLOADS);

        else if (key.equals(AppSettings.PREF_CLEAN_CACHE_KEY))
            setUpPreferenceSummaries(PREF_MASK_CLEAN_CACHE);

        else if (key.equals(AppSettings.PREF_DEV_MODE_KEY))
            setUpPreferenceSummaries(PREF_MASK_DEV_MODE);
    }

    private void setUpPreferenceSummaries(int preferencesMask)
    {
        if ((preferencesMask & PREF_MASK_MAIN_SITES) != 0) {
            int[] codes = AppSettings.getMainSitesCodes();
            StringBuilder sb = new StringBuilder(AppData.getSiteByCode(codes[0]).name);
            for (int c = 1; c < codes.length; ++c)
                sb.append(", ").append(AppData.getSiteByCode(codes[c]).name);

            findPreference(AppSettings.PREF_MAIN_SITES_KEY).setSummary(sb);
        }
        if ((preferencesMask & PREF_MASK_SCHEDULE_DOWNLOADS) != 0) {
            ScheduleManager scheduleManager = new ScheduleManager(getActivity());

            StringBuilder sb = new StringBuilder();
            for (DownloadSchedule schedule : scheduleManager.getDownloadSchedules()) {
                if (sb.length() != 0)
                    sb.append("\n");
                sb.append(schedule.toShortString());
            }
            findPreference(AppSettings.PREF_SCHEDULE_DOWNLOADS_KEY).setSummary(sb);
        }
        if ((preferencesMask & PREF_MASK_CLEAN_CACHE) != 0) {

            String dataSize = new DecimalFormat("#0.00").format(NewsManager.getCacheSize() / 1048576.0);
            findPreference(AppSettings.PREF_CLEAN_CACHE_KEY).setSummary(dataSize + " MB");
        }
        if ((preferencesMask & PREF_MASK_DEV_MODE) != 0) {
            Preference p = findPreference(AppSettings.PREF_DEV_MODE_KEY);
            p.setSummary(AppSettings.isDevModeGranted() ? R.string.enabled : R.string.disabled);
            AppSettings.devModeInvalidated();
        }
    }

    private Preference.OnPreferenceClickListener onSiteSettingsAction = new Preference.OnPreferenceClickListener() {
        @Override
        public boolean onPreferenceClick(Preference preference)
        {
            getActivity().getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_content, new SitesSettingsFragment())
                    .addToBackStack(null)
                    .commit();
            return true;
        }
    };

    private Preference.OnPreferenceClickListener onScheduleDownloadSettingsAction = new Preference.OnPreferenceClickListener() {
        @Override
        public boolean onPreferenceClick(Preference preference)
        {
            getActivity().getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_content, new ScheduleDownloadSettingsFragment())
                    .addToBackStack(null)
                    .commit();
            return true;
        }
    };

}
