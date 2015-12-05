package com.newsup.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.newsup.R;
import com.newsup.kernel.NewsDataCenter;
import com.newsup.kernel.basic.Site;
import com.newsup.kernel.set.SiteList;
import com.newsup.lister.SitePickerLister;
import com.newsup.settings.AppSettings;
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

        marks = new boolean[sites.size()];
        for (int i = 0; i < marks.length; ++i) marks[i] = false;
        for (int site_code : AppSettings.MAIN_CODES) {
            Site site = sites.getSiteByCode(site_code);
            marks[sites.indexOf(site)] = true;
        }

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
        int counter = 0;
        for (boolean b : marks) if (b) counter++;

        if (counter > 0) {
            SiteList sites = dataManager.getSites();

            int[] main_codes = new int[counter];
            int index = 0;
            for (int i = 0; i < marks.length; ++i) {
                if (marks[i]) {
                    main_codes[index] = sites.get(i).code;
                    index++;
                }
            }

            dataManager.setSettingsWith(AppSettings.SET_MAIN_CODES, main_codes);
            socket.message(MAIN_SITES_CHANGED, null);

        } else {
            Toast.makeText(getContext(), R.string.msg_no_site_picked, Toast.LENGTH_SHORT).show();
        }
    }


}
