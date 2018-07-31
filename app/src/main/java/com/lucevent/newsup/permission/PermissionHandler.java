package com.lucevent.newsup.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

public class PermissionHandler {

	private final Context mContext;
	private final String[] mPermissions;

	public PermissionHandler(Context context, String[] permissions)
	{
		mContext = context;
		mPermissions = permissions;
	}

	/**
	 * @return true if permissions is granted, false if not
	 */
	public boolean hasPermission()
	{
		return Build.VERSION.SDK_INT < Build.VERSION_CODES.M
				|| ContextCompat.checkSelfPermission(mContext, mPermissions[0]) == PackageManager.PERMISSION_GRANTED;
	}

	@TargetApi(Build.VERSION_CODES.M)
	public void askPermission(Fragment fragment, int requestCode)
	{
		fragment.requestPermissions(mPermissions, requestCode);
	}

	@TargetApi(Build.VERSION_CODES.M)
	public void askPermission(Activity activity, int requestCode)
	{
		activity.requestPermissions(mPermissions, requestCode);
	}

	/**
	 * Same of calling hasPermission, and askPermission if result was false
	 *
	 * @return true if permissions are ok, false if not granted yet
	 */
	public final boolean checkAndAsk(Fragment fragment, int requestCode)
	{
		if (hasPermission())
			return true;

		askPermission(fragment, requestCode);
		return false;
	}

	/**
	 * Same of calling hasPermission, and askPermission if result was false
	 *
	 * @return true if permissions are ok, false if not granted yet
	 */
	public final boolean checkAndAsk(Activity activity, int requestCode)
	{
		if (hasPermission())
			return true;

		askPermission(activity, requestCode);
		return false;
	}

	/**
	 * To be called in the fragment's or activity's <i>onRequestPermissionsResult</i>
	 *
	 * @return true if the permission was granted, false otherwise
	 */
	public boolean isPermissionGranted(@NonNull String permissions[], @NonNull int[] grantResults)
	{
		return grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
	}

}
