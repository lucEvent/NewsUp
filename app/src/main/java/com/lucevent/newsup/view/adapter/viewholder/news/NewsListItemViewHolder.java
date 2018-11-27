package com.lucevent.newsup.view.adapter.viewholder.news;

import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.lucevent.newsup.R;
import com.lucevent.newsup.parse.NewsListItem;
import com.lucevent.newsup.view.util.CustomMovementMethod;

public class NewsListItemViewHolder extends NewsElementViewHolder {

	private TextView bullet, content;

	public NewsListItemViewHolder(View v)
	{
		super(v);
		bullet = (TextView) v.findViewById(R.id.bullet);
		content = (TextView) v.findViewById(R.id.content);
		content.setMovementMethod(new CustomMovementMethod());
	}

	@Override
	public void bind(boolean darkStyle)
	{
		content.setText(Html.fromHtml(((NewsListItem) elem).getContent()));
	}

	@Override
	public void setTextSize(int font_size)
	{
		content.setTextSize(TypedValue.COMPLEX_UNIT_SP, FONT_SIZE_NORMAL_VALUES[font_size]);
		bullet.setTextSize(TypedValue.COMPLEX_UNIT_SP, FONT_SIZE_NORMAL_VALUES[font_size]);
	}

	@Override
	public void setStyle(boolean darkStyle)
	{
		bullet.setTextColor(darkStyle ? DARK_TEXT_COLOR : LIGHT_TEXT_COLOR);
		content.setTextColor(darkStyle ? DARK_TEXT_COLOR : LIGHT_TEXT_COLOR);
		itemView.setBackgroundColor(darkStyle ? DARK_BACKGROUND_COLOR : LIGHT_BACKGROUND_COLOR);
	}

	@Override
	public void setLinkColor(int linkColor)
	{
		content.setLinkTextColor(linkColor);
	}

}
