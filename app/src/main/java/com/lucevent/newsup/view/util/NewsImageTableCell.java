package com.lucevent.newsup.view.util;


import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lucevent.newsup.R;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NewsImageTableCell extends LinearLayout {

	private static RequestOptions glideOptions = new RequestOptions().fitCenter();

	private TextView mTextView;

	public NewsImageTableCell(Context context, String content)
	{
		super(context);

		((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.v_image_table_cell, this, true);

		setOrientation(LinearLayout.VERTICAL);

		Document doc = org.jsoup.Jsoup.parse(content);
		Elements imgs = doc.select("img");
		if (!imgs.isEmpty()) {
			Element img = imgs.first();
			ImageView imageView = (ImageView) findViewById(R.id.image);

			try {
				int width = Integer.parseInt(img.attr("width"));
				int height = Integer.parseInt(img.attr("height"));
				Resources r = getResources();

				imageView.getLayoutParams().width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width, r.getDisplayMetrics());
				imageView.getLayoutParams().height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height, r.getDisplayMetrics());
			} catch (Exception ignored) {
			}

			Glide.with(context)
					.load(img.attr("src"))
					.apply(glideOptions)
					.into(imageView);
			imgs.remove();
		}

		mTextView = (TextView) findViewById(R.id.text);
		mTextView.setText(Html.fromHtml(doc.body().html()));
	}

	public void setTextSize(float fontSize)
	{
		mTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
	}

	public void setTextColor(int textColor)
	{
		mTextView.setTextColor(textColor);
	}

	public void setLinkTextColor(int linkColor)
	{
		mTextView.setLinkTextColor(linkColor);
	}

}
