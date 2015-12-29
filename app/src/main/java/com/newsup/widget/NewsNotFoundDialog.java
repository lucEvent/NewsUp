package com.newsup.widget;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.newsup.R;
import com.newsup.kernel.basic.News;
import com.newsup.task.SocketMessage;

public class NewsNotFoundDialog implements View.OnClickListener {

    private Context context;
    private Handler handler;
    private News news;

    private AlertDialog dialog;

    public NewsNotFoundDialog(Context context, Handler handler, News news) {
        this.context = context;
        this.handler = handler;
        this.news = news;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();

        View view = inflater.inflate(R.layout.d_newsnotfound, null);

        view.findViewById(R.id.tryagain).setOnClickListener(this);
        view.findViewById(R.id.openinbrowser).setOnClickListener(this);
        view.findViewById(R.id.close).setOnClickListener(this);

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(R.string.title_newsnotfound);
        dialog.setMessage(R.string.message_newsnotfound);

        dialog.setView(view);
        this.dialog = dialog.create();
    }

    public void show() {
        dialog.show();
        ((TextView) this.dialog.findViewById(android.R.id.message)).setGravity(Gravity.CENTER);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tryagain:
                handler.obtainMessage(SocketMessage.ACTION_OPEN_NEWS, news).sendToTarget();
                break;
            case R.id.openinbrowser:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(news.link));
                context.startActivity(browserIntent);
        }
        dialog.hide();
    }
}
