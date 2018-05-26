package com.lucevent.newsup.view.adapter.viewholder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lucevent.newsup.R;
import com.lucevent.newsup.io.LogoManager;
import com.lucevent.newsup.services.util.Download;
import com.lucevent.newsup.view.util.AppTextView;

public class DownloadScheduleViewHolder extends RecyclerView.ViewHolder {

    private LinearLayout mIcons;
    private TextView mTVSchedule;
    private ImageView mVRepeat, mVNotify;
    private ImageButton mBDelete;

    public DownloadScheduleViewHolder(View v)
    {
        super(v);

        mIcons = (LinearLayout) v.findViewById(R.id.icons);
        mTVSchedule = (TextView) v.findViewById(R.id.schedule);

        mVRepeat = (ImageView) v.findViewById(R.id.repeat);
        mVNotify = (ImageView) v.findViewById(R.id.notify);

        mBDelete = (ImageButton) v.findViewById(R.id.action_delete);
    }

    public void bind(Download schedule, View.OnClickListener deleteListener)
    {
        mIcons.removeAllViews();
        Context context = itemView.getContext();
        int dim = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, itemView.getResources().getDisplayMetrics());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dim, dim);
        lp.setMargins(3, 3, 3, 3);
        if (schedule.sites_codes.length == 1) {
            Drawable logo = LogoManager.getLogo(schedule.sites_codes[0], LogoManager.Size.DRAWER);
            if (logo != null) {
                ImageView iv = new ImageView(context);
                iv.setLayoutParams(lp);
                iv.setImageDrawable(logo);
                mIcons.addView(iv);
            } else {
                AppTextView tv = new AppTextView(context);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
                tv.setText(R.string.event);
                mIcons.addView(tv);
            }
        } else {
            int len = Math.min(4, schedule.sites_codes.length);
            for (int i = 0; i < len; i++) {
                Drawable logo = LogoManager.getLogo(schedule.sites_codes[i], LogoManager.Size.DRAWER);
                ImageView iv = new ImageView(context);
                iv.setLayoutParams(lp);
                iv.setImageDrawable(logo);
                mIcons.addView(iv);
            }
            if (len < schedule.sites_codes.length) {
                AppTextView tv = new AppTextView(context);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
                tv.setText(context.getString(R.string.plus_x_more, schedule.sites_codes.length - len));
                mIcons.addView(tv);
            }
        }

        mTVSchedule.setText(schedule.toShortString());

        mVRepeat.setVisibility(schedule.repeat ? View.VISIBLE : View.GONE);
        mVNotify.setImageResource(schedule.notify ? R.drawable.ic_notification : R.drawable.ic_notification_off);

        mBDelete.setTag(schedule);
        mBDelete.setOnClickListener(deleteListener);

        itemView.setTag(schedule);
    }

}