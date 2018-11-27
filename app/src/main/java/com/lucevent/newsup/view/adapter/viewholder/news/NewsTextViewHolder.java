package com.lucevent.newsup.view.adapter.viewholder.news;

import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.lucevent.newsup.parse.NewsElement;
import com.lucevent.newsup.view.util.CustomMovementMethod;

public class NewsTextViewHolder extends NewsElementViewHolder {

	public static final int TEXT_SIZE_TITLE = 0;
	public static final int TEXT_SIZE_LARGE = 1;
	public static final int TEXT_SIZE_NORMAL = 2;
	public static final int TEXT_SIZE_SMALL = 3;

	private final int[] mTextSizeValues;

	public NewsTextViewHolder(View v, int textSize)
	{
		super(v);
		switch (textSize) {
			case TEXT_SIZE_TITLE:
				mTextSizeValues = FONT_SIZE_TITLE_VALUES;
				break;
			case TEXT_SIZE_LARGE:
				mTextSizeValues = FONT_SIZE_LARGE_VALUES;
				break;
			default:
			case TEXT_SIZE_NORMAL:
				mTextSizeValues = FONT_SIZE_NORMAL_VALUES;
				break;
			case TEXT_SIZE_SMALL:
				mTextSizeValues = FONT_SIZE_SMALL_VALUES;
				break;
		}
		((TextView) itemView).setMovementMethod(new CustomMovementMethod());
	}

	@Override
	public void bind(boolean darkStyle)
	{
		String content = ((NewsElement<String>) elem).getContent();
		((TextView) itemView).setText(Html.fromHtml(content));
	}

	@Override
	public void setTextSize(int font_size)
	{
		((TextView) itemView).setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSizeValues[font_size]);
	}

	@Override
	public void setStyle(boolean darkStyle)
	{
		((TextView) itemView).setTextColor(darkStyle ? DARK_TEXT_COLOR : LIGHT_TEXT_COLOR);
	}

	@Override
	public void setLinkColor(int linkColor)
	{
		((TextView) itemView).setLinkTextColor(linkColor);
	}

}
