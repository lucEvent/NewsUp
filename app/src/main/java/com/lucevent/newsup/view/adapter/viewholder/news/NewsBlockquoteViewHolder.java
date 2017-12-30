package com.lucevent.newsup.view.adapter.viewholder.news;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.lucevent.newsup.R;
import com.lucevent.newsup.parse.NewsBlockquote;

public class NewsBlockquoteViewHolder extends NewsElementViewHolder {

    private TextView content;

    public NewsBlockquoteViewHolder(View v)
    {
        super(v);
        content = (TextView) v.findViewById(R.id.content);
        content.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void bind()
    {
        content.setText(Html.fromHtml(((NewsBlockquote) elem).getContent()));
    }

    @Override
    public void setTextSize(int font_size)
    {
        content.setTextSize(TypedValue.COMPLEX_UNIT_SP, FONT_SIZE_NORMAL_VALUES[font_size]);
    }

    @Override
    public void setStyle(boolean darkStyle)
    {
        content.setTextColor(darkStyle ? DARK_TEXT_COLOR : LIGHT_TEXT_COLOR);
        content.setBackgroundColor(darkStyle ? DARK_BLOCKQUOTE_BACKGROUND_COLOR : LIGHT_BLOCKQUOTE_BACKGROUND_COLOR);
    }

    @Override
    public void setLinkColor(int linkColor)
    {
        content.setLinkTextColor(linkColor);
    }

}
