package com.lucevent.newsup.view.activity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.SitesMap;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.kernel.ScheduleManager;
import com.lucevent.newsup.services.util.DownloadSchedule;
import com.lucevent.newsup.view.adapter.SiteMultiSelectAdapter;

import java.util.HashSet;
import java.util.Set;

public class DownloadScheduleEditorActivity extends AppCompatActivity {

    public static final int ACTION_CREATE = 0;
    public static final int ACTION_MODIFY = 1;

    private int action;
    private DownloadSchedule originalSchedule;

    private TextView text_time;
    private ToggleButton[] btn_days;
    private CheckBox repeat, notify;
    private int hour = 0, minute = 0;

    private Set<String> selected_sites = new HashSet<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_download_schedule_editor);

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

        action = getIntent().getExtras().getInt(AppCode.ACTION);
        if (action == ACTION_MODIFY) {
            originalSchedule = (DownloadSchedule) getIntent().getExtras().get(AppCode.SEND_DOWNLOAD_SCHEDULE);

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

            selected_sites = new HashSet<>(originalSchedule.sites_codes.length);
            for (int code : originalSchedule.sites_codes)
                selected_sites.add(Integer.toString(code));
        }
    }

    @Override
    public void onBackPressed()
    {
        actionCancel(null);
    }

    public void onTimePickerAction(View v)
    {
        TimePickerDialog dialog;
        dialog = new TimePickerDialog(new ContextThemeWrapper(DownloadScheduleEditorActivity.this, R.style.themeDialog),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute)
                    {
                        DownloadScheduleEditorActivity.this.hour = hour;
                        DownloadScheduleEditorActivity.this.minute = minute;
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

    private Sites sites;

    public void onSelectSitesAction(View v)
    {
        if (sites == null)
            sites = new Sites(new SitesMap(AppData.sites, SitesMap.SITE_COMPARATOR_BY_NAME));

        SiteMultiSelectAdapter adapter = new SiteMultiSelectAdapter(DownloadScheduleEditorActivity.this,
                sites, selected_sites);

        new AlertDialog.Builder(DownloadScheduleEditorActivity.this)
                .setAdapter(adapter, null)
                .setCancelable(false)
                .setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        if (selected_sites.size() == 0)
                            new AlertDialog.Builder(DownloadScheduleEditorActivity.this)
                                    .setMessage(R.string.msg_must_select_at_least_one)
                                    .setNegativeButton(R.string.dismiss, null)
                                    .show();
                    }
                })
                .show();
    }

    public void actionSave(View view)
    {
        if (selected_sites.isEmpty()) {
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

        int[] sites_codes = new int[selected_sites.size()];
        int index = 0;
        for (String scode : selected_sites)
            sites_codes[index++] = Integer.parseInt(scode);

        ScheduleManager dataManager = new ScheduleManager(this);
        if (action == ACTION_MODIFY) {
            originalSchedule.hour = hour;
            originalSchedule.minute = minute;
            originalSchedule.notify = notify;
            originalSchedule.repeat = repeat;
            originalSchedule.days = days;
            originalSchedule.sites_codes = sites_codes;

            dataManager.updateDownloadSchedule(originalSchedule);
        } else
            dataManager.createDownloadSchedule(hour, minute, notify, repeat, days, sites_codes);

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
