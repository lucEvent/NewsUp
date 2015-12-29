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
import com.newsup.task.Socket;
import com.newsup.task.SocketMessage;

public class SitePicker extends AlertDialog.Builder {

    private Socket socket;
    private boolean[] marks;

    public SitePicker(Context context, int[] site_codes, Socket socket) {
        super(context);
        this.socket = socket;

        SiteList sites = NewsDataCenter.getAppSites();

        marks = new boolean[sites.size()];
        for (int i = 0; i < marks.length; ++i) marks[i] = false;

        if (site_codes != null) {
            for (int site_code : site_codes) {
                Site site = sites.getSiteByCode(site_code);
                marks[sites.indexOf(site)] = true;
            }
        }

        setAdapter(new SitePickerLister(context, sites, marks), null);
        setNegativeButton(android.R.string.cancel, null);
        setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                done();
            }
        });

        AlertDialog dialog = create();
        dialog.getListView().setDivider(null);
        dialog.show();
    }

    private void done() {
        int counter = 0;
        for (boolean b : marks) if (b) counter++;

        if (counter > 0) {
            SiteList sites = NewsDataCenter.getAppSites();

            int[] site_codes = new int[counter];
            int index = 0;
            for (int i = 0; i < marks.length; ++i) {
                if (marks[i]) {
                    site_codes[index] = sites.get(i).code;
                    index++;
                }
            }
            socket.message(SocketMessage.SELECTED_SITE_CODES, site_codes);

        } else {
            Toast.makeText(getContext(), R.string.msg_no_site_picked, Toast.LENGTH_SHORT).show();
        }
    }

}
