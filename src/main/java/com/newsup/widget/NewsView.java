package com.newsup.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.newsup.R;
import com.newsup.kernel.News;
import com.newsup.kernel.NewsDataCenter;
import com.newsup.task.TaskMessage;

public class NewsView {

    private Context context;
    private NewsDataCenter dataCenter;
    private Handler handler;

    private View view;
    private RelativeLayout buttons;

    private WebView newsView;
    private TextView title;
    private ImageButton bshare, bbookmark;

    public NewsView(Activity context, NewsDataCenter dataCenter, Handler handler) {
        this.context = context;
        this.dataCenter = dataCenter;
        this.handler = handler;

        view = context.findViewById(R.id.layoutcontent);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        buttons = (RelativeLayout) context.findViewById(R.id.buttons);

        newsView = (WebView) view.findViewById(R.id.content);
        newsView.setOnTouchListener(onNewsViewTouchListener);
        newsView.getSettings().setJavaScriptEnabled(true);
        newsView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        title = (TextView) view.findViewById(R.id.title);

        bshare = (ImageButton) view.findViewById(R.id.button_share);
        bshare.setOnClickListener(onShareListener);

        bbookmark = (ImageButton) view.findViewById(R.id.button_bookmark);
        bbookmark.setOnClickListener(onBookmarkListener);

    }

    private News currentNews;

    private final String css = "<style>img, iframe { width: 100%; height: auto; }</style>";

    public boolean displayNews(News news) {
        this.currentNews = news;

        if (news.content == null) {
            Toast.makeText(context, "No hay contenido", Toast.LENGTH_SHORT).show();
            return false;
        }

        title.setText(news.title);
        newsView.loadData(css + news.content, "text/html; charset=UTF-8", null);

        setBookmarkButtonImage();

        view.setVisibility(RelativeLayout.VISIBLE);
        return true;
    }

    private void setBookmarkButtonImage() {
        if (dataCenter.isBookmarked(currentNews)) {
            bbookmark.setImageResource(R.drawable.ic_bookmark);
        } else {
            bbookmark.setImageResource(R.drawable.ic_bookmark_border);
        }
    }

    public void close() {
        view.setVisibility(RelativeLayout.GONE);

        newsView.loadUrl("about:blank");
        title.setText("");
    }

    public boolean isShown() {
        return view.isShown();
    }

    private View.OnClickListener onBookmarkListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mstime = DEFAULT_WAITING_TIME;

            if (dataCenter.isBookmarked(currentNews)) {
                dataCenter.unBookmarkNews(currentNews);
            } else {
                dataCenter.bookmarkNews(currentNews);
            }
            handler.obtainMessage(TaskMessage.ACTION_REFRESH_LIST, null).sendToTarget();
            setBookmarkButtonImage();
        }
    };

    private View.OnClickListener onShareListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mstime = DEFAULT_WAITING_TIME;

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, currentNews.title + " " + currentNews.link);
            sendIntent.setType("text/plain");
            context.startActivity(Intent.createChooser(sendIntent, "Share to"));
        }
    };

    private static final int DEFAULT_WAITING_TIME = 3000;
    private int mstime = 0;
    private View.OnTouchListener onNewsViewTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                buttons.setVisibility(View.VISIBLE);

                mstime = DEFAULT_WAITING_TIME;
                if (timer.getState() == Thread.State.NEW) {
                    timer.start();
                } else if (timer.getState() == Thread.State.WAITING) {
                    synchronized (timer) {
                        timer.notify();
                    }
                }
            }
            return false;
        }

        Thread timer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (mstime > 0) {
                        while (mstime > 0) {
                            try {
                                int aux = mstime;
                                mstime = 0;
                                Thread.sleep(aux);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        mainThread.post(new Runnable() {
                            @Override
                            public void run() {
                                buttons.setVisibility(View.GONE);
                            }
                        });
                    }
                    try {
                        synchronized (timer) {
                            timer.wait();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    };

    private Handler mainThread = new Handler();

    private void debug(String text) {
        android.util.Log.d("##NewsView##", text);
    }

}
