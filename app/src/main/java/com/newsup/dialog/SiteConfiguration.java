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

import java.util.Arrays;

public class SiteConfiguration extends AlertDialog.Builder implements View.OnClickListener, Socket {

    private NewsDataCenter dataCenter;
    private Site site;
    private SiteSettings newSettings;

    private Context context;
    private AlertDialog dialog;

    public SiteConfiguration(Context context, final NewsDataCenter dataCenter) {
        super(context);
        this.context = context;
        this.dataCenter = dataCenter;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.f_conf_site, null);
        view.findViewById(R.id.select_sections_onload).setOnClickListener(this);
        view.findViewById(R.id.select_sections_tosave).setOnClickListener(this);
        setView(view);

        setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean changes = false;
                if (newSettings.sectionsOnMain != null) {
                    changes = true;
                    site.settings.sectionsOnMain = newSettings.sectionsOnMain;
                }
                if (newSettings.sectionsToSave != null) {
                    changes = true;
                    site.settings.sectionsToSave = newSettings.sectionsToSave;
                }
                if (changes) dataCenter.setSettingsOf(site);
            }
        });
        setNegativeButton(android.R.string.cancel, null);
        dialog = create();
    }


    public void set(Site site) {
        this.site = site;
        this.newSettings = new SiteSettings(site.code);

        dialog.setTitle(site.name);
        dialog.setIcon(site.icon);
        dialog.show();
    }

    private boolean selecting_sections_onload;

    @Override
    public void onClick(View v) {
        SiteSettings settings = dataCenter.getSettingsOf(site);
        boolean[] marks = null;
        if (v.getId() == R.id.select_sections_tosave) {
            selecting_sections_onload = false;
            marks = Arrays.copyOfRange(settings.sectionsToSave, 0, settings.sectionsToSave.length);
        } else if (v.getId() == R.id.select_sections_onload) {
            selecting_sections_onload = true;
            marks = Arrays.copyOfRange(settings.sectionsOnMain, 0, settings.sectionsOnMain.length);
        }
        new SectionPicker(context, site.getSections(), marks, this);
    }

    @Override
    public void message(int taskMessage, Object dataAttached) {
        if (taskMessage == DialogState.SECTIONS_CHANGED) {
            if (selecting_sections_onload) {
                newSettings.sectionsOnMain = (boolean[]) dataAttached;
            } else {
                newSettings.sectionsToSave = (boolean[]) dataAttached;
            }
        }
    }
}
