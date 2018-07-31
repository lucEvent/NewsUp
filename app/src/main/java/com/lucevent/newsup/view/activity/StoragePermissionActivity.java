package com.lucevent.newsup.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.lucevent.newsup.R;
import com.lucevent.newsup.io.SDManager;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.permission.PermissionHandler;

public abstract class StoragePermissionActivity extends AppCompatActivity {

	private PermissionHandler mPermissionHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		mPermissionHandler = new PermissionHandler(this, AppData.STORAGE_PERMISSIONS);
	}

	private View mTempData;

	protected View.OnClickListener onBookmarkClick = new View.OnClickListener() {
		@Override
		public void onClick(View v)
		{
			if (mPermissionHandler.checkAndAsk(StoragePermissionActivity.this, AppCode.REQUEST_PERMISSION_BOOKMARK))
				onBookmarkStateChanged(v);
			else
				mTempData = v;
		}
	};

	protected View.OnLongClickListener onImageLongClick = new View.OnLongClickListener() {

		@Override
		public boolean onLongClick(View v)
		{
			if (mPermissionHandler.checkAndAsk(StoragePermissionActivity.this, AppCode.REQUEST_PERMISSION_SAVE_IMAGE))
				onSaveImage(v);
			else
				mTempData = v;
			return true;
		}
	};

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
	{
		if (mPermissionHandler.isPermissionGranted(permissions, grantResults)) {
			if (requestCode == AppCode.REQUEST_PERMISSION_BOOKMARK)
				onBookmarkStateChanged(mTempData);
			else if (requestCode == AppCode.REQUEST_PERMISSION_SAVE_IMAGE)
				onSaveImage(mTempData);
		} else {
			if (requestCode == AppCode.REQUEST_PERMISSION_BOOKMARK)
				Toast.makeText(this, R.string.msg_permission_bookmark_denied, Toast.LENGTH_LONG).show();
			else if (requestCode == AppCode.REQUEST_PERMISSION_SAVE_IMAGE)
				Toast.makeText(this, R.string.msg_permission_save_image_denied, Toast.LENGTH_LONG).show();
		}
	}

	protected abstract void onBookmarkStateChanged(View btn);

	private void onSaveImage(View img)
	{
		SDManager.downloadFile(this, (String) img.getTag(AppCode.TAG_IMAGE));
	}

}
