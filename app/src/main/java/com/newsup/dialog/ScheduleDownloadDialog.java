package com.newsup.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.newsup.R;
import com.newsup.kernel.NewsDataCenter;
import com.newsup.kernel.set.SiteList;
import com.newsup.settings.DownloadScheduleSetting;
import com.newsup.task.Socket;

import java.util.Calendar;

public class ScheduleDownloadDialog extends AlertDialog.Builder implements View.OnClickListener, Socket {

    // Visual elements
    private TimePicker time;
    private ToggleButton[] days;
    private CheckBox repeat, notify;
    private Button show_sites;

    //
    private Socket handler;

    private int[] sites_codes;

    private AlertDialog dialog;

    public ScheduleDownloadDialog(Context context, Socket handler) {
        super(context);
        this.handler = handler;

        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.d_download_schedule, null);

        time = (TimePicker) view.findViewById(R.id.timepicker);
        time.setIs24HourView(true);

        days = new ToggleButton[7];
        days[0] = (ToggleButton) view.findViewById(R.id.monday);
        days[1] = (ToggleButton) view.findViewById(R.id.tuesday);
        days[2] = (ToggleButton) view.findViewById(R.id.wednesday);
        days[3] = (ToggleButton) view.findViewById(R.id.thursday);
        days[4] = (ToggleButton) view.findViewById(R.id.friday);
        days[5] = (ToggleButton) view.findViewById(R.id.saturday);
        days[6] = (ToggleButton) view.findViewById(R.id.sunday);

        repeat = (CheckBox) view.findViewById(R.id.repeat);
        notify = (CheckBox) view.findViewById(R.id.notify);
        show_sites = (Button) view.findViewById(R.id.show_sites);
        show_sites.setOnClickListener(this);

        setView(view);
        setNegativeButton(android.R.string.cancel, null);

        dialog = create();
    }

    public void showNew() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        time.setCurrentHour(calendar.get(Calendar.HOUR));
        time.setCurrentMinute(calendar.get(Calendar.MINUTE));
        System.out.println(calendar.get(Calendar.MINUTE));
        for (ToggleButton day : days) day.setChecked(true);
        repeat.setChecked(true);
        notify.setChecked(true);

        dialog.setButton(AlertDialog.BUTTON_POSITIVE, getContext().getString(R.string.create),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        confirmSchedule();
                    }
                }
        );
        sites_codes = null;

        dialog.show();
    }


    public void showItem(DownloadScheduleSetting schedule) {
        time.setCurrentHour(schedule.hour);
        time.setCurrentMinute(schedule.minute);
        for (int i = 0; i < days.length; i++) days[i].setChecked(schedule.days[i]);
        repeat.setChecked(schedule.repeat);
        notify.setChecked(schedule.notify);

        dialog.setButton(AlertDialog.BUTTON_POSITIVE, getContext().getString(R.string.applychanges),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        confirmSchedule();
                    }
                }
        );
        dialog.show();

        sites_codes = schedule.dl_sites_codes;
    }

    private void confirmSchedule() {
        DownloadScheduleSetting schedule = new DownloadScheduleSetting();
        schedule.hour = time.getCurrentHour();
        schedule.minute = time.getCurrentMinute();

        schedule.repeat = repeat.isChecked();
        schedule.notify = notify.isChecked();

        schedule.days = new boolean[7];
        for (int i = 0; i < schedule.days.length; i++) {
            schedule.days[i] = days[i].isChecked();
        }
        if (sites_codes == null) {
            SiteList sites = NewsDataCenter.getAppSites();
            sites_codes = new int[sites.size()];
            for (int i = 0; i < sites.size(); i++) {
                sites_codes[i] = sites.get(i).code;
            }
        }
        schedule.dl_sites_codes = sites_codes;
        handler.message(Socket.DOWNLOAD_SCHEDULE, schedule);
    }

    @Override
    public void onClick(View v) {
        new SitePicker(getContext(), sites_codes, this);
    }

    @Override
    public void message(int taskMessage, Object dataAttached) {
        sites_codes = (int[]) dataAttached;
    }

}
