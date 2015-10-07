package com.newsup.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;

import com.newsup.kernel.list.SiteList;
import com.newsup.widget.SiteLister;


public class SitePicker extends AlertDialog.Builder implements DialogInterface.OnClickListener, DialogState {

    private Handler handler;
    private AlertDialog dialog;

    public SitePicker(Context context, SiteList sites, Handler handler) {
        super(context);
        this.handler = handler;

        this.setAdapter(new SiteLister(context, sites), this);
        this.dialog = create();
        dialog.getListView().setDivider(null);
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
