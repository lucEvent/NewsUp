package com.lucevent.newsup.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.SwitchPreference;
import android.widget.Toast;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.SiteMain;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.io.LogoManager;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.view.activity.DownloadScheduleEditorActivity;
import com.lucevent.newsup.view.preference.SectionsMultiSelectPreference;

public class SiteSettingsFragment extends android.preference.PreferenceFragment
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static SiteSettingsFragment instanceFor(int site_code)
    {
        Bundle bundle = new Bundle();
        bundle.putInt(AppCode.SEND_SITE_CODE, site_code);

        SiteSettingsFragment fragment = new SiteSettingsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private String PREF_SHOW_IN_MAIN_KEY;
    private String PREF_FAVORITE_KEY;
    private String PREF_MAIN_SECTIONS_KEY;
    private String PREF_DOWNLOAD_SECTIONS_KEY;

    private Site currentSite;

    @Override
    public void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.site_preferences);

        PREF_SHOW_IN_MAIN_KEY = getString(R.string.pref_show_in_my_feed_key);
        PREF_FAVORITE_KEY = getString(R.string.pref_favorite_key);
        PREF_MAIN_SECTIONS_KEY = getString(R.string.pref_main_sections_key);
        PREF_DOWNLOAD_SECTIONS_KEY = getString(R.string.pref_download_sections_key);

        currentSite = AppData.getSiteByCode(getArguments().getInt(AppCode.SEND_SITE_CODE));
        getActivity().setTitle(getString(R.string.title_frg_settings_of, currentSite.name));

        boolean inMain = AppSettings.isShownInMain(currentSite);
        boolean favorite = AppSettings.isFavorite(currentSite);

        ((SwitchPreference) findPreference(PREF_SHOW_IN_MAIN_KEY)).setChecked(inMain);
        ((SwitchPreference) findPreference(PREF_FAVORITE_KEY)).setChecked(favorite);

        ((SectionsMultiSelectPreference) findPreference(PREF_MAIN_SECTIONS_KEY)).setSite(currentSite);
        ((SectionsMultiSelectPreference) findPreference(PREF_DOWNLOAD_SECTIONS_KEY)).setSite(currentSite);

        findPreference(getString(R.string.pref_shortcut_key)).setOnPreferenceClickListener(onCreateShortcut);
        findPreference(getString(R.string.pref_schedule_one_download_key)).setOnPreferenceClickListener(onScheduleDownload);
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
            AppSettings.toggleFavorite(currentSite);

        else if (key.equals(PREF_MAIN_SECTIONS_KEY))
            AppSettings.setMainSections(currentSite,
                    preferences.getStringSet(PREF_MAIN_SECTIONS_KEY, null));

        else if (key.equals(PREF_DOWNLOAD_SECTIONS_KEY))
            AppSettings.setDownloadSections(currentSite,
                    preferences.getStringSet(PREF_DOWNLOAD_SECTIONS_KEY, null));
    }

    private Preference.OnPreferenceClickListener onCreateShortcut = new Preference.OnPreferenceClickListener() {
        @Override
        public boolean onPreferenceClick(Preference preference)
        {
            Context context = getActivity();

            Intent shortcutIntent = new Intent(context, SiteMain.class);
            shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            shortcutIntent.putExtra(AppCode.SEND_SITE_CODE, currentSite.code);

            Intent addIntent = new Intent();
            addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
            addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, currentSite.name);
            addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON, LogoManager.getBitmap(currentSite.code));
            addIntent.putExtra("duplicate", true);
            addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            context.sendBroadcast(addIntent);

            Toast.makeText(context, getString(R.string.msg_shortcut_created, currentSite.name), Toast.LENGTH_SHORT).show();

            return true;
        }
    };

    private Preference.OnPreferenceClickListener onScheduleDownload = new Preference.OnPreferenceClickListener() {
        @Override
        public boolean onPreferenceClick(Preference preference)
        {
            Intent intent = new Intent(getActivity(), DownloadScheduleEditorActivity.class);
            intent.putExtra(AppCode.ACTION, DownloadScheduleEditorActivity.ACTION_CREATE_ONE);
            intent.putExtra(AppCode.SEND_SITE_CODE, currentSite.code);
//            startActivityForResult(intent, 0);
            startActivity(intent);
            return true;
        }
    };

}
