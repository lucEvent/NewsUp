package com.lucevent.newsup.view.preference;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;
import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.kernel.KernelManager;

import java.io.File;

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
						new CleanCacheTask(getContext()).execute();
					}
				});
	}


	static class CleanCacheTask extends AsyncTask<Void, Integer, Void> {

		private ProgressDialog mDialog;
		private File glideCache;

		CleanCacheTask(Context c)
		{
			mDialog = new ProgressDialog(c);
			mDialog.setCancelable(false);

			glideCache = Glide.getPhotoCacheDir(c);
		}


		protected void onPreExecute()
		{
			mDialog.setMessage(mDialog.getContext().getString(R.string.msg_clean_cache_start));
			mDialog.show();
		}

		protected Void doInBackground(Void... args)
		{
			publishProgress(R.string.msg_clean_cache_remove_news);
			KernelManager.cleanCache();

			publishProgress(R.string.msg_clean_cache_remove_images);
			KernelManager.cleanGlideCache();

			publishProgress(R.string.done);
			AppSettings.setCleanCache();

			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values)
		{
			super.onProgressUpdate(values);

			if (values != null && values.length > 0)
				mDialog.setMessage(mDialog.getContext().getString(values[0]));
		}

		@Override
		protected void onPostExecute(Void result)
		{
			mDialog.dismiss();
		}

	}

}