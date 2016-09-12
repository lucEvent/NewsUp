package com.lucevent.newsup.view.fragment;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

import com.lucevent.newsup.Main;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.SitesMap;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.io.LogoManager;
import com.lucevent.newsup.kernel.AppData;

public class SitesSettingsFragment extends android.preference.PreferenceFragment
        implements Preference.OnPreferenceClickListener {

    @Override
    public void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.sites_list_preferences);

        PreferenceScreen sitesPreferenceScreen = getPreferenceScreen();
        SitesMap sites = new SitesMap(AppData.sites, SitesMap.SITE_COMPARATOR_BY_NAME);
        for (Site site : sites) {
            Preference p = new Preference(getActivity());
            p.setIcon(LogoManager.getLogo(site.code, LogoManager.Size.ACTION_BAR));
            p.setTitle(site.name);
            p.setKey(Integer.toString(site.code));
            p.setOnPreferenceClickListener(this);

            sitesPreferenceScreen.addPreference(p);
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        getActivity().setTitle(R.string.pref_sites_settings);
    }

    @Override
    public boolean onPreferenceClick(Preference preference)
    {
        int code = Integer.parseInt(preference.getKey());
        ((Main) getActivity()).onReplaceFragment(SiteSettingsFragment.instanceFor(code), R.id.nav_settings, true);
        return true;
    }

}
