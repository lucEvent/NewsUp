package com.lucevent.newsup.view.fragment;

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

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.Main;
import com.lucevent.newsup.ProSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.kernel.NewsManager;
import com.lucevent.newsup.kernel.ScheduleManager;
import com.lucevent.newsup.net.MainChangeListener;
import com.lucevent.newsup.services.util.DownloadSchedule;

import java.text.DecimalFormat;
import java.util.Random;

public class AppSettingsFragment extends PreferenceFragment
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final int PREF_MASK_MAIN_SITES = 0x01;
    private static final int PREF_MASK_SCHEDULE_DOWNLOADS = 0x02;
    private static final int PREF_MASK_CLEAN_CACHE = 0x04;
    private static final int PREF_MASK_KEEP_NEWS = 0x10;

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
            setUpPreferenceSummaries(PREF_MASK_MAIN_SITES);
            ((Main) getActivity()).onMainistsChange();
        } else if (key.equals(AppSettings.PREF_SCHEDULE_DOWNLOADS_KEY))
            setUpPreferenceSummaries(PREF_MASK_SCHEDULE_DOWNLOADS);
        else if (key.equals(AppSettings.PREF_CLEAN_CACHE_KEY))
            setUpPreferenceSummaries(PREF_MASK_CLEAN_CACHE);
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
            setUpPreferenceSummaries(PREF_MASK_KEEP_NEWS);
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
        if ((preferencesMask & PREF_MASK_KEEP_NEWS) != 0) {
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
            ((MainChangeListener) getActivity()).onReplaceFragment(new ScheduleDownloadSettingsFragment(), R.id.nav_settings, true);
            return true;
        }
    };

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
