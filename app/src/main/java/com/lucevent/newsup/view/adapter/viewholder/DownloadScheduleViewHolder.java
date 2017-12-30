package com.lucevent.newsup.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lucevent.newsup.R;
import com.lucevent.newsup.services.util.DownloadSchedule;

public class DownloadScheduleViewHolder extends RecyclerView.ViewHolder {

    private TextView view_time, view_days;
    private View view_repeat, view_notify;
    private ImageButton button_delete;

    public DownloadScheduleViewHolder(View v)
    {
        super(v);

        view_time = (TextView) v.findViewById(R.id.time);
        view_days = (TextView) v.findViewById(R.id.days);

        view_repeat = v.findViewById(R.id.repeat);
        view_notify = v.findViewById(R.id.notify);

        button_delete = (ImageButton) v.findViewById(R.id.action_delete);
    }

    public void bind(DownloadSchedule schedule, View.OnClickListener deleteListener)
    {
        view_time.setText(schedule.timeString());
        view_days.setText(schedule.daysString());

        view_repeat.setVisibility(schedule.repeat ? View.VISIBLE : View.GONE);
        view_notify.setVisibility(schedule.notify ? View.VISIBLE : View.GONE);

        button_delete.setTag(schedule);
        button_delete.setOnClickListener(deleteListener);

        itemView.setTag(schedule);
    }

}