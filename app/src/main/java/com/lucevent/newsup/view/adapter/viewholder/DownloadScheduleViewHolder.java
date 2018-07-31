package com.lucevent.newsup.view.adapter.viewholder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lucevent.newsup.R;
import com.lucevent.newsup.services.util.Download;

public class DownloadScheduleViewHolder extends IconBarViewHolder {

	private TextView mTVSchedule;
	private ImageView mVRepeat, mVNotify;
	private ImageButton mBDelete;

	public DownloadScheduleViewHolder(View v)
	{
		super(v);

		mTVSchedule = (TextView) v.findViewById(R.id.schedule);

		mVRepeat = (ImageView) v.findViewById(R.id.repeat);
		mVNotify = (ImageView) v.findViewById(R.id.notify);

		mBDelete = (ImageButton) v.findViewById(R.id.action_delete);
	}

	public void bind(Download schedule, View.OnClickListener deleteListener)
	{
		super.bind(schedule.sites_codes);

		mTVSchedule.setText(schedule.toShortString());

		mVRepeat.setVisibility(schedule.repeat ? View.VISIBLE : View.GONE);
		mVNotify.setImageResource(schedule.notify ? R.drawable.ic_notification_on : R.drawable.ic_notification_off);

		mBDelete.setTag(schedule);
		mBDelete.setOnClickListener(deleteListener);

		itemView.setTag(schedule);
	}

}