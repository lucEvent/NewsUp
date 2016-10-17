package com.lucevent.newsup.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

public abstract class PermissionHandler {

    private final Context context;
    private final String[] permissions;
    private final int requestCode;
    private final int messageResId;

    PermissionHandler(Context context, String[] permissions, int requestCode, int messageResId)
    {
        this.context = context;
        this.permissions = permissions;
        this.requestCode = requestCode;
        this.messageResId = messageResId;
    }

    /**
     * @return true if permissions is granted, false if not
     */
    public boolean hasPermission()
    {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                || ContextCompat.checkSelfPermission(context, permissions[0]) == PackageManager.PERMISSION_GRANTED;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void askPermission(Fragment fragment)
    {
        fragment.requestPermissions(permissions, requestCode);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void askPermission(Activity activity)
    {
        activity.requestPermissions(permissions, requestCode);
    }

    /**
     * Same of calling hasPermission, and askPermission if result was false
     *
     * @return true if permissions are ok, false if not granted yet
     */
    public final boolean checkAndAsk(Fragment fragment)
    {
        if (hasPermission())
            return true;

        askPermission(fragment);
        return false;
    }

    /**
     * Same of calling hasPermission, and askPermission if result was false
     *
     * @return true if permissions are ok, false if not granted yet
     */
    public final boolean checkAndAsk(Activity activity)
    {
        if (hasPermission())
            return true;

        askPermission(activity);
        return false;
    }

    /**
     * To be called in the fragment's or activity's <i>onRequestPermissionsResult</i>
     *
     * @return true if the permission was granted, false otherwise
     */
    public boolean onRequestPermissionsResult(int resultRequestCode, @NonNull String permissions[], @NonNull int[] grantResults)
    {
        if (resultRequestCode == requestCode) {

            if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                return true;

            Toast.makeText(context, messageResId, Toast.LENGTH_LONG).show();
        }
        return false;
    }

}
