package com.newsup.lister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.newsup.R;
import com.newsup.settings.AppSettings;
import com.newsup.settings.DownloadScheduleSetting;
import com.newsup.task.Socket;

public class DownloadScheduleLister extends ArrayAdapter<DownloadScheduleSetting> implements View.OnClickListener {

    private LayoutInflater inflater;
    private Socket socket;

    public DownloadScheduleLister(Context context, Socket socket) {
        super(context, R.layout.i_download_schedule, AppSettings.DL_SCHEDULES);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.socket = socket;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = inflater.inflate(R.layout.i_download_schedule, parent, false);
            view.findViewById(R.id.action_delete).setOnClickListener(this);
        }
        DownloadScheduleSetting schedule = getItem(position);

        String time = schedule.toTimeString();
        ((TextView) view.findViewById(R.id.time)).setText(time);

        String days = schedule.toDaysString();
        ((TextView) view.findViewById(R.id.days)).setText(days);

        View rep_icon = view.findViewById(R.id.repeat_icon);
        View repeat = view.findViewById(R.id.repeat);
        if (schedule.repeat) {
            repeat.setVisibility(View.VISIBLE);
            rep_icon.setVisibility(View.VISIBLE);
        } else {
            repeat.setVisibility(View.INVISIBLE);
            rep_icon.setVisibility(View.INVISIBLE);
        }

        View not_icon = view.findViewById(R.id.notification_icon);
        View notification = view.findViewById(R.id.notify);
        if (schedule.notify) {
            not_icon.setVisibility(View.VISIBLE);
            notification.setVisibility(View.VISIBLE);
        } else {
            not_icon.setVisibility(View.INVISIBLE);
            notification.setVisibility(View.INVISIBLE);
        }
        view.findViewById(R.id.action_delete).setTag(position);
        return view;
    }

    @Override
    public void onClick(View v) {
        socket.message(Socket.REMOVE_SCHEDULE, v.getTag());
    }
}