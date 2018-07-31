package com.lucevent.newsup.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.Date;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.io.LogoManager;

public class NewsCompactViewHolder extends RecyclerView.ViewHolder {

	private static final RequestOptions OPTIONS = new RequestOptions().centerCrop();

	private View mLogo;
	private TextView mTitle, mDate;
	private ImageButton mBookmark;
	private ImageView mPicture;

	public NewsCompactViewHolder(View v, View.OnClickListener onBookmark)
	{
		super(v);

		mTitle = (TextView) v.findViewById(R.id.title);
		mDate = (TextView) v.findViewById(R.id.date);
		mLogo = v.findViewById(R.id.logo);
		mBookmark = (ImageButton) v.findViewById(R.id.button_bookmark);
		mBookmark.setOnClickListener(onBookmark);
		mPicture = (ImageView) v.findViewById(R.id.picture);
	}

	public void bind(News news, boolean showSiteLogo, boolean bookmarked)
	{
		if (showSiteLogo) {
			mLogo.setVisibility(View.VISIBLE);
			mLogo.setBackground(LogoManager.getLogo(news.site_code, LogoManager.Size.I_ITEM));
		} else
			mLogo.setVisibility(View.GONE);

		// img
		mPicture.setVisibility(View.VISIBLE);
		mPicture.setImageDrawable(null);

		Glide.with(mPicture.getContext())
				.applyDefaultRequestOptions(OPTIONS)
				.load(news.imgSrc)
				.into(mPicture);
		// end img

		mTitle.setText(Html.fromHtml(news.title));
		mDate.setText(Date.getAge(news.date));
		mBookmark.setSelected(bookmarked);

		mBookmark.setTag(news);
		itemView.setTag(news);
	}

}
