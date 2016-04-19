package com.lucevent.newsup.view.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.NewsManager;
import com.lucevent.newsup.view.activity.NewsActivity;

public class OnNewsItemClickListener implements View.OnClickListener {

    private Context context;

    public OnNewsItemClickListener(Context context)
    {
        this.context = context;
    }

    @Override
    public void onClick(View v)
    {
        openNews((News) v.getTag());
    }

    private void openNews(final News news)
    {
        NewsManager.getNewsContent(news);

        if (news.content == null) {

            View view = LayoutInflater.from(context).inflate(R.layout.d_news_not_found, null);

            final AlertDialog dialog = new AlertDialog.Builder(context)
                    .setTitle(R.string.msg_cant_load_content)
                    .setMessage(R.string.msg_news_not_found)
                    .setView(view)
                    .create();

            view.findViewById(R.id.action_try_again).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    openNews(news);
                    dialog.dismiss();
                }
            });
            view.findViewById(R.id.action_open_in_browser).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(news.link));
                    context.startActivity(browserIntent);
                    dialog.dismiss();
                }
            });
            view.findViewById(R.id.action_close).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    dialog.dismiss();
                }
            });

            dialog.show();

        } else {
            NewsManager.addToHistory(news);

            Intent intent = new Intent(context, NewsActivity.class);
            intent.putExtra(AppCode.SEND_NEWS, news);
            context.startActivity(intent);
        }
    }

}
