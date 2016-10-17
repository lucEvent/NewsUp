package com.lucevent.newsup.permission;

import android.Manifest;
import android.content.Context;

import com.lucevent.newsup.R;
import com.lucevent.newsup.kernel.AppCode;

public class StoragePermissionHandler extends PermissionHandler {

    public StoragePermissionHandler(Context context)
    {
        super(context,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                AppCode.REQUEST_PERMISSION_WRITE_IN_STORAGE,
                R.string.msg_disk_permission_denied);
    }

}
