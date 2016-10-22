package com.lucevent.newsup.permission;

import android.Manifest;
import android.content.Context;

import com.lucevent.newsup.kernel.AppCode;

public class PhoneStatePermissionHandler extends PermissionHandler {

    public PhoneStatePermissionHandler(Context context)
    {
        super(context,
                new String[]{Manifest.permission.READ_PHONE_STATE},
                AppCode.REQUEST_PERMISSION_READ_PHONE_STATE,
                -1);// "Without this permission, we CANNOT re-schedule the downloads after a phone restart");
    }

}
