package com.lucevent.newsup.view.adapter.viewholder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lucevent.newsup.R;
import com.lucevent.newsup.io.LogoManager;
import com.lucevent.newsup.view.util.AppTextView;

public abstract class IconBarViewHolder extends RecyclerView.ViewHolder {

	private LinearLayout mIcons;

	public IconBarViewHolder(View v)
	{
		super(v);
		mIcons = (LinearLayout) v.findViewById(R.id.icons);
	}

	public void bind(int[] site_codes)
	{
		mIcons.removeAllViews();
		Context context = itemView.getContext();
		int dim = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, itemView.getResources().getDisplayMetrics());
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dim, dim);
		lp.setMargins(3, 3, 3, 3);
		if (site_codes.length == 1) {
			Drawable logo = LogoManager.getLogo(site_codes[0], LogoManager.Size.DRAWER);
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
			int len = Math.min(4, site_codes.length);
			for (int i = 0; i < len; i++) {
				Drawable logo = LogoManager.getLogo(site_codes[i], LogoManager.Size.DRAWER);
				ImageView iv = new ImageView(context);
				iv.setLayoutParams(lp);
				iv.setImageDrawable(logo);
				mIcons.addView(iv);
			}
			if (len < site_codes.length) {
				AppTextView tv = new AppTextView(context);
				tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
				tv.setText(context.getString(R.string.plus_x_more, site_codes.length - len));
				mIcons.addView(tv);
			}
		}
	}

}

