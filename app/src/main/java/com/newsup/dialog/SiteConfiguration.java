package com.newsup.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import com.newsup.R;
import com.newsup.kernel.NewsDataCenter;
import com.newsup.kernel.basic.Site;
import com.newsup.settings.SiteSettings;
import com.newsup.task.Socket;
import com.newsup.task.SocketMessage;

public class SiteConfiguration extends AlertDialog.Builder implements View.OnClickListener, Socket {

    private NewsDataCenter dataCenter;
    private Site site;
    private SiteSettings newSettings;

    private Context context;
    private AlertDialog dialog;

    public SiteConfiguration(Context context, NewsDataCenter dataCenter) {
        super(context);
        this.context = context;
        this.dataCenter = dataCenter;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.d_site_configuration, null);
        view.findViewById(R.id.select_sections_onload).setOnClickListener(this);
        view.findViewById(R.id.select_sections_offline).setOnClickListener(this);
        setView(view);

        setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveChanges();
            }
        });
        setNegativeButton(android.R.string.cancel, null);
        dialog = create();
    }

    private void saveChanges() {

        site.settings = newSettings;

        dataCenter.setSettingsOf(site);

    }


    public void set(Site site) {
        this.site = site;
        this.newSettings = new SiteSettings(site.code);

        SiteSettings settings = dataCenter.getSettingsOf(site);

        newSettings.sectionsOnMain = new boolean[settings.sectionsOnMain.length];
        System.arraycopy(settings.sectionsOnMain, 0, newSettings.sectionsOnMain, 0, settings.sectionsOnMain.length);

        newSettings.sectionsOffline = new boolean[settings.sectionsOffline.length];
        System.arraycopy(settings.sectionsOffline, 0, newSettings.sectionsOffline, 0, settings.sectionsOffline.length);

        dialog.setTitle(site.name);
        dialog.setIcon(site.icon);
        dialog.show();
    }

    private boolean selecting_sections_onload;

    @Override
    public void onClick(View v) {
        boolean[] marks = null;
        if (v.getId() == R.id.select_sections_onload) {
            selecting_sections_onload = true;
            marks = this.newSettings.sectionsOnMain;
        } else if (v.getId() == R.id.select_sections_offline) {
            selecting_sections_onload = false;
            marks = this.newSettings.sectionsOffline;
        }
        new SectionPicker(context, site.getSections(), marks, this);
    }

    @Override
    public void message(int taskMessage, Object dataAttached) {
        if (taskMessage == SocketMessage.SELECTED_SECTIONS) {
            if (selecting_sections_onload) {
                newSettings.sectionsOnMain = (boolean[]) dataAttached;
            } else {
                newSettings.sectionsOffline = (boolean[]) dataAttached;
            }
        }
    }
}
