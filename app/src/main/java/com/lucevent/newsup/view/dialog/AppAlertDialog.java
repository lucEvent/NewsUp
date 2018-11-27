package com.lucevent.newsup.view.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.alert.Alert;
import com.lucevent.newsup.data.alert.AlertCode;
import com.lucevent.newsup.net.BackendNames;
import com.lucevent.newsup.net.RawContentReader;
import com.lucevent.newsup.view.activity.ContactActivity;

public class AppAlertDialog implements AlertCode, BackendNames {

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

	private View.OnClickListener parseAction(int action)
	{
		switch (action) {
			case ACTION_GOOGLE_PLAY:
				return onGooglePlayAction;
			case ACTION_REPORT:
				return onReportAction;
			case ACTION_DO_NOT_ASK_AGAIN:
				return onDoNotAskAgainAction;
			case ACTION_OPEN_BOOKMARKS:
				return onOpenBookmarks;
			case ACTION_OPEN_HISTORY:
				return onOpenHistory;
			case ACTION_OPEN_EVENTS:
				return onOpenEvents;
			case ACTION_SEND_ANSWER:
				return onSendAnswer;
			case ACTION_OPEN_WEB_PAGE:
				return onOpenWebPage;
			default:
				return onDismissAction;
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
			case BTN_RATE:
				return R.string.rate;
		}
		return R.string.ok;
	}

	private Activity mContext;
	private AlertDialog.Builder mBuilder;
	private AlertDialog mDialog;
	private Alert mAlert;
	private View mView;

	public AppAlertDialog(Activity context)
	{
		mContext = context;
		mBuilder = new AlertDialog.Builder(context);
		mView = LayoutInflater.from(context).inflate(R.layout.d_alert, null, false);
	}

	public AppAlertDialog prepare(Alert alert)
	{
		mAlert = alert;

		TextView msg = (TextView) mView.findViewById(R.id.msg);
		if (alert.message_code == MESSAGE_CUSTOM)
			msg.setText(alert.message);
		else
			msg.setText(parseMessage(alert.message_code));

		Button btn = (Button) mView.findViewById(R.id.btn_1);
		if (alert.btn_start_code != BTN_HIDDEN) {
			btn.setOnClickListener(parseAction(alert.btn_start_action));
			if (alert.btn_start_code == BTN_CUSTOM)
				btn.setText(alert.btn_start_text);
			else
				btn.setText(parseButtonText(alert.btn_start_code));
		} else
			btn.setVisibility(View.GONE);

		btn = (Button) mView.findViewById(R.id.btn_2);
		if (alert.btn_center_code != BTN_HIDDEN) {
			btn.setOnClickListener(parseAction(alert.btn_center_action));
			if (alert.btn_center_code == BTN_CUSTOM)
				btn.setText(alert.btn_center_text);
			else
				btn.setText(parseButtonText(alert.btn_center_code));
		} else
			btn.setVisibility(View.GONE);

		btn = (Button) mView.findViewById(R.id.btn_3);
		if (alert.btn_end_code != BTN_HIDDEN) {
			btn.setOnClickListener(parseAction(alert.btn_end_action));
			if (alert.btn_end_code == BTN_CUSTOM)
				btn.setText(alert.btn_end_text);
			else
				btn.setText(parseButtonText(alert.btn_end_code));
		} else
			btn.setVisibility(View.GONE);

		mBuilder.setView(mView)
				.setCancelable(false);
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
	private View.OnClickListener onGooglePlayAction = new View.OnClickListener() {
		@Override
		public void onClick(View v)
		{
			onDoNotAskAgainAction.onClick(v);
			mContext.startActivity(new Intent(Intent.ACTION_VIEW,
					Uri.parse("market://details?id=" + mContext.getPackageName())));
		}
	};

	private View.OnClickListener onReportAction = new View.OnClickListener() {
		@Override
		public void onClick(View v)
		{
			onDoNotAskAgainAction.onClick(v);
			mContext.startActivity(new Intent(mContext, ContactActivity.class));
		}
	};

	private View.OnClickListener onDoNotAskAgainAction = new View.OnClickListener() {
		@Override
		public void onClick(View v)
		{
			dismiss();
			AppSettings.setAlertAsShown(mAlert.id);
		}
	};

	private View.OnClickListener onDismissAction = new View.OnClickListener() {
		@Override
		public void onClick(View v)
		{
			dismiss();
		}
	};

	private View.OnClickListener onOpenBookmarks = new View.OnClickListener() {
		@Override
		public void onClick(View v)
		{
			// TODO: 08/06/2018
		}
	};

	private View.OnClickListener onOpenHistory = new View.OnClickListener() {
		@Override
		public void onClick(View v)
		{
			// TODO: 08/06/2018
		}
	};

	private View.OnClickListener onOpenEvents = new View.OnClickListener() {
		@Override
		public void onClick(View v)
		{
			// TODO: 08/06/2018
		}
	};

	private View.OnClickListener onSendAnswer = new View.OnClickListener() {
		@Override
		public void onClick(final View v)
		{
			onDoNotAskAgainAction.onClick(v);

			new Thread(new Runnable() {
				@Override
				public void run()
				{
					int answer = -1;
					switch (v.getId()) {
						case R.id.btn_1:
							answer = mAlert.btn_start_code != AlertCode.BTN_CUSTOM ? mAlert.btn_start_code : mAlert.btn_start_text.hashCode();
							break;
						case R.id.btn_2:
							answer = mAlert.btn_center_code != AlertCode.BTN_CUSTOM ? mAlert.btn_center_code : mAlert.btn_center_text.hashCode();
							break;
						case R.id.btn_3:
							answer = mAlert.btn_end_code != AlertCode.BTN_CUSTOM ? mAlert.btn_end_code : mAlert.btn_end_text.hashCode();
							break;
					}

					try {
						RawContentReader.getUrl(MAIN_APP_SERVER + "?poll=" + mAlert.id + "&ans=" + answer);
					} catch (Exception ignored) {
						AppSettings.printerror("[AAD] Couldn't send answer", ignored);
					}
				}
			}).start();
		}
	};

	private View.OnClickListener onOpenWebPage = new View.OnClickListener() {
		@Override
		public void onClick(View v)
		{
			// TODO: 08/06/2018
		}
	};

}
