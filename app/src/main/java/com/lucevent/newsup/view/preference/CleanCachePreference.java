package com.lucevent.newsup.view.preference;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.kernel.NewsManager;

public class CleanCachePreference extends android.preference.DialogPreference {

    public CleanCachePreference(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    protected void onPrepareDialogBuilder(AlertDialog.Builder builder)
    {
        builder.setMessage(R.string.msg_confirm_clean_app_data)
                .setCancelable(false)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        NewsManager.cleanCache();
                        AppSettings.setCleanCache();
                    }
                });
    }

}