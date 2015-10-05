package com.newsup.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.util.Log;

import com.newsup.kernel.list.SiteList;
import com.newsup.widget.SitePickerLister;


public class SitePicker extends AlertDialog.Builder implements DialogInterface.OnClickListener, DialogState {

    private Handler handler;
    private AlertDialog dialog;

    public SitePicker(Context context, SiteList sites, Handler handler) {
        super(context);
        this.handler = handler;

        this.setAdapter(new SitePickerLister(context, sites), this);
        this.dialog = create();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int which) {
        handler.obtainMessage(SITE_PICKED, which).sendToTarget();
    }

    @Override
    public AlertDialog show() {
        dialog.show();
        return dialog;
    }
}
