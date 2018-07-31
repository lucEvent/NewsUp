package com.lucevent.newsup.view.adapter.viewholder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.Date;
import com.lucevent.newsup.services.util.DownloadData;

public class NotificationViewHolder extends IconBarViewHolder {

	private TextView mTVDate;
	private ImageButton mBDelete;

	public NotificationViewHolder(View v)
	{
		super(v);

		mTVDate = (TextView) v.findViewById(R.id.date);
		mBDelete = (ImageButton) v.findViewById(R.id.delete);
	}

	public void bind(DownloadData downloadData, View.OnClickListener deleteListener)
	{
		super.bind(downloadData.site_codes);

		mTVDate.setText(Date.getAge(downloadData.time));

		mBDelete.setTag(downloadData);
		mBDelete.setOnClickListener(deleteListener);

		itemView.setTag(downloadData);
	}

}