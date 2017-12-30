package com.lucevent.newsup.view.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.alert.Alert;
import com.lucevent.newsup.data.alert.AlertCode;

public class AppAlertDialog implements AlertCode {

    private int parseMessage(int message)
    {
        switch (message) {
            case MESSAGE_RATE_NOW:
                return R.string.msg_rate;
            case MESSAGE_NEEDS_UPDATE:
                return R.string.msg_needs_update;
            case MESSAGE_REPORT:
                return R.string.msg_report;
        }
        return -1;
    }

    private DialogInterface.OnClickListener parseAction(int action)
    {
        switch (action) {
            case ACTION_GOOGLE_PLAY:
                return onGooglePlayAction;
            case ACTION_REPORT:
                return onReportAction;
            default:
                return null;
        }
    }

    private int parseButtonText(int btn_code)
    {
        switch (btn_code) {
            case BTN_YES:
                return R.string.yes;
            case BTN_NO:
                return R.string.no;
            case BTN_CANCEL:
                return R.string.cancel;
            case BTN_UPDATE:
                return R.string.update;
            case BTN_NOT_NOW:
                return R.string.not_now;
        }
        return R.string.ok;
    }

    private Context mContext;
    private AlertDialog.Builder mBuilder;
    private AlertDialog mDialog;

    public AppAlertDialog(Context context)
    {
        mContext = context;
        mBuilder = new AlertDialog.Builder(context);
    }

    public AppAlertDialog prepare(Alert alert)
    {
        if (alert.message_code == MESSAGE_CUSTOM)
            mBuilder.setMessage(alert.message);
        else
            mBuilder.setMessage(parseMessage(alert.message_code));

        DialogInterface.OnClickListener action;
        if (alert.btn_start_code != BTN_HIDDEN) {
            action = parseAction(alert.btn_start_action);
            if (alert.btn_start_code == BTN_CUSTOM)
                mBuilder.setNegativeButton(alert.btn_start_text, action);
            else
                mBuilder.setNegativeButton(
                        parseButtonText(alert.btn_start_code),
                        action);
        }
        if (alert.btn_center_code != BTN_HIDDEN) {
            action = parseAction(alert.btn_center_action);
            if (alert.btn_center_code == BTN_CUSTOM)
                mBuilder.setNeutralButton(alert.btn_center_text, action);
            else
                mBuilder.setNeutralButton(
                        parseButtonText(alert.btn_center_code),
                        action);
        }
        if (alert.btn_end_code != BTN_HIDDEN) {
            action = parseAction(alert.btn_end_action);
            if (alert.btn_end_code == BTN_CUSTOM)
                mBuilder.setPositiveButton(alert.btn_end_text, action);
            else
                mBuilder.setPositiveButton(
                        parseButtonText(alert.btn_end_code),
                        action);
        }
        return this;
    }

    public AppAlertDialog start()
    {
        mDialog = mBuilder.create();
        mDialog.show();
        return this;
    }

    public void dismiss()
    {
        if (mDialog != null)
            mDialog.dismiss();
    }


    /* ***************** Actions ***************** */
    private DialogInterface.OnClickListener onGooglePlayAction = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            mContext.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + mContext.getPackageName())));
        }
    };

    private DialogInterface.OnClickListener onReportAction = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            Toast.makeText(mContext, "onReportAction", Toast.LENGTH_LONG).show();
        }
    };

}
