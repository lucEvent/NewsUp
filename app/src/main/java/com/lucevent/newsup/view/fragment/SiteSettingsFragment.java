package com.lucevent.newsup.view.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.SwitchPreference;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.AppData;
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

}
