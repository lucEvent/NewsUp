package com.lucevent.newsup.view.preference;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.view.adapter.SiteMultiSelectAdapter;

import java.util.HashSet;

public class SiteMultiSelectPreference extends android.preference.MultiSelectListPreference {

    private Sites sites;

    private HashSet<String> selected_preferences;

    public SiteMultiSelectPreference(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        sites = new Sites(AppData.sitesOrderedByName);
    }

    public void setSelectedPreferences()
    {
        selected_preferences = new HashSet<>(AppSettings.getMainSitesCodesString());
    }

    @Override
    protected void onPrepareDialogBuilder(Builder builder)
    {
        setSelectedPreferences();

        SiteMultiSelectAdapter adapter = new SiteMultiSelectAdapter(getContext(), sites, selected_preferences);

        builder.setAdapter(adapter, null)
                .setCancelable(false)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                        if (selected_preferences.size() > 0)
                            AppSettings.setMainSitesCodes(selected_preferences);

                        else {
                            new AlertDialog.Builder(getContext())
                                    .setMessage(R.string.msg_must_select_at_least_one)
                                    .setNegativeButton(R.string.dismiss, null)
                                    .show();
                        }
                    }
                });
    }

}