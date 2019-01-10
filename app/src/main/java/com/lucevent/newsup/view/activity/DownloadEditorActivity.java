package com.lucevent.newsup.view.activity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.event.Event;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.kernel.ScheduleManager;
import com.lucevent.newsup.services.util.Download;

import java.util.Arrays;

public class DownloadEditorActivity extends AppCompatActivity {

	public static final int ACTION_CREATE = 0;
	public static final int ACTION_MODIFY = 1;
	public static final int ACTION_CREATE_ONE = 2;
	public static final int ACTION_MODIFY_ONE = 3;
	public static final int ACTION_CREATE_EVENT = 4;
	public static final int ACTION_MODIFY_EVENT = 5;

	private int action;
	private Download originalSchedule;
	private ScheduleManager dataManager;

	private TextView text_time;
	private ToggleButton[] btn_days;
	private CheckBox repeat, notify;
	private int hour = 0, minute = 0;

	private int[] selected_sites = new int[0];

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_download_schedule_editor);

		dataManager = new ScheduleManager(this);

		text_time = (TextView) findViewById(R.id.text_time);

		int[] btn_ids = new int[]{R.id.monday, R.id.tuesday, R.id.wednesday, R.id.thursday,
				R.id.friday, R.id.saturday, R.id.sunday};
		btn_days = new ToggleButton[7];
		for (int i = 0; i < btn_days.length; i++)
			btn_days[i] = (ToggleButton) findViewById(btn_ids[i]);

		repeat = (CheckBox) findViewById(R.id.checkbox_repeat);
		findViewById(R.id.layout_repeat).setTag(repeat);

		notify = (CheckBox) findViewById(R.id.checkbox_notify);
		findViewById(R.id.layout_notify).setTag(notify);

		Bundle extras = getIntent().getExtras();
		action = extras.getInt(AppCode.ACTION);
		switch (action) {
			case ACTION_MODIFY:
				setOriginalSchedule(extras.getInt(AppCode.SCHEDULE_ID));

				if (originalSchedule.id < 0) {
					Site s = AppData.getSiteByCode(-originalSchedule.id);
					disableSectionsButton(s != null ? s.name : getString(R.string.event));
				}
				break;
			case ACTION_MODIFY_ONE:
				setOriginalSchedule(extras.getInt(AppCode.SCHEDULE_ID));
			case ACTION_CREATE_ONE:
				int site_code = (int) extras.get(AppCode.SITE_CODE);
				selected_sites = new int[]{site_code};
				disableSectionsButton(AppData.getSiteByCode(site_code).name);
				break;
			case ACTION_MODIFY_EVENT:
				setOriginalSchedule(extras.getInt(AppCode.SCHEDULE_ID));
			case ACTION_CREATE_EVENT:
				Event event = AppData.getEvent(extras.getInt(AppCode.EVENT_CODE));
				selected_sites = new int[]{event.code};
				disableSectionsButton(event.title);
				break;
		}
	}

	@Override
	public void onBackPressed()
	{
		actionCancel(null);
	}

	private void disableSectionsButton(String title)
	{
		Button ss = (Button) findViewById(R.id.select_sites);
		ss.setText(title);
		ss.setEnabled(false);
	}

	private void setOriginalSchedule(int download_id)
	{
		originalSchedule = (Download) dataManager.getSchedule(download_id);

		hour = originalSchedule.hour;
		minute = originalSchedule.minute;
		text_time.setText(originalSchedule.timeString());
		for (int i = 0; i < btn_days.length; i++)
			if (!originalSchedule.days[i]) {
				btn_days[i].setChecked(false);
				onDayStateChange(btn_days[i]);
			}

		repeat.setChecked(originalSchedule.repeat);
		notify.setChecked(originalSchedule.notify);

		selected_sites = Arrays.copyOfRange(originalSchedule.sites_codes, 0, originalSchedule.sites_codes.length);
	}

	public void onTimePickerAction(View v)
	{
		TimePickerDialog dialog;
		dialog = new TimePickerDialog(new ContextThemeWrapper(DownloadEditorActivity.this, R.style.themeDialog),
				new TimePickerDialog.OnTimeSetListener() {
					@Override
					public void onTimeSet(TimePicker timePicker, int hour, int minute)
					{
						DownloadEditorActivity.this.hour = hour;
						DownloadEditorActivity.this.minute = minute;
						String time = (hour < 10 ? "0" : "") + hour + ":" + (minute < 10 ? "0" : "") + minute;
						text_time.setText(time);
					}
				}, hour, minute, true);
		dialog.setTitle(R.string.select_time);
		dialog.show();
	}

	public void onDayStateChange(View v)
	{
		ToggleButton tb = (ToggleButton) v;
		if (tb.isChecked()) {
			tb.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
		} else {
			tb.setTextColor(ContextCompat.getColor(this, R.color.disabled));
		}
	}

	public void onCheckboxStateChange(View v)
	{
		CheckBox checkbox = (CheckBox) v.getTag();
		checkbox.toggle();
	}

	public void onSelectSitesAction(View v)
	{
		Intent intent = new Intent(this, SelectSitesActivity.class);
		intent.putExtra(AppCode.TARGET, SelectSitesActivity.Target.SELECT_DOWNLOAD);
		intent.putExtra(AppCode.SELECTED, selected_sites);
		startActivityForResult(intent, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			selected_sites = (int[]) data.getExtras().get(AppCode.SELECTED);
		}
	}

	public void actionSave(View view)
	{
		if (selected_sites.length == 0) {
			Toast.makeText(this, R.string.msg_must_select_at_least_one_site, Toast.LENGTH_SHORT).show();
			return;
		}
		boolean daySelected = false;
		boolean[] days = new boolean[7];
		for (int i = 0; i < days.length; i++) {
			days[i] = btn_days[i].isChecked();
			daySelected |= days[i];
		}
		if (!daySelected) {
			Toast.makeText(this, R.string.msg_must_select_at_least_one_day, Toast.LENGTH_SHORT).show();
			return;
		}

		boolean notify = this.notify.isChecked();
		boolean repeat = this.repeat.isChecked();

		switch (action) {
			case ACTION_MODIFY:
			case ACTION_MODIFY_ONE:
			case ACTION_MODIFY_EVENT:
				originalSchedule.hour = hour;
				originalSchedule.minute = minute;
				originalSchedule.notify = notify;
				originalSchedule.repeat = repeat;
				originalSchedule.days = days;
				originalSchedule.sites_codes = selected_sites;

				dataManager.updateSchedule(originalSchedule);
				break;
			case ACTION_CREATE_ONE:
				dataManager.createSpecialSchedule(getIntent().getExtras().getInt(AppCode.SITE_CODE), hour, minute, notify, repeat, days, selected_sites);
				break;
			case ACTION_CREATE_EVENT:
				dataManager.createSpecialSchedule(getIntent().getExtras().getInt(AppCode.EVENT_CODE), hour, minute, notify, repeat, days, selected_sites);
				break;
			default:
				dataManager.createSchedule(hour, minute, notify, repeat, days, selected_sites);
		}

		Toast.makeText(this, R.string.msg_download_set_successfully, Toast.LENGTH_SHORT).show();
		setResult(RESULT_OK);
		finish();
	}

	public void actionCancel(View view)
	{
		new AlertDialog.Builder(this)
				.setMessage(R.string.msg_confirm_to_go_back)
				.setNegativeButton(R.string.go_back, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						finish();
					}
				})
				.setPositiveButton(R.string.continue_, null)
				.show();
	}

}
