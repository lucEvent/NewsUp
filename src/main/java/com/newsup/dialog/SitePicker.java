package com.newsup.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.newsup.R;
import com.newsup.kernel.NewsDataCenter;
import com.newsup.kernel.list.SiteList;
import com.newsup.lister.SitePickerLister;
import com.newsup.settings.AppSettings;
import com.newsup.settings.SiteSettings;
import com.newsup.task.Socket;


public class SitePicker extends AlertDialog.Builder implements DialogState {

    private NewsDataCenter dataManager;
    private Socket socket;
    private boolean[] marks;

    public SitePicker(Context context, NewsDataCenter dataManager, Socket socket) {
        super(context);
        this.dataManager = dataManager;
        this.socket = socket;

        SiteList sites = dataManager.getSites();
        AppSettings settings = dataManager.getSettings();
        marks = settings.sitesOnLoadBooleanArray(sites.size());

        setAdapter(new SitePickerLister(context, sites, marks), null);
        setNegativeButton(android.R.string.cancel, null);
        setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveSettings();
            }
        });

        AlertDialog dialog = create();
        dialog.getListView().setDivider(null);
        dialog.show();
    }

    private void saveSettings() {
        int[] main_sites = SiteSettings.toIntegerArray(marks);
        dataManager.setSettingsWith(AppSettings.SET_MAIN_SITE, main_sites);
        socket.message(MAIN_SITES_CHANGED, null);
    }


}
