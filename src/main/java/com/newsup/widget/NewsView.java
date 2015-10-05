package com.newsup.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.newsup.R;
import com.newsup.kernel.News;
import com.newsup.kernel.NewsDataCenter;
import com.newsup.net.State;

public class NewsView {

    private Context context;
    private NewsDataCenter dataCenter;
    private Handler handler;

    private RelativeLayout view;

    private WebView newsView;
    private TextView title;
    private ImageButton bshare, bbookmark, bclose;

    public NewsView(Activity context, NewsDataCenter dataCenter, Handler handler) {
        this.context = context;
        this.dataCenter = dataCenter;
        this.handler = handler;

        view = (RelativeLayout) context.findViewById(R.id.layoutcontent);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        newsView = (WebView) view.findViewById(R.id.content);
        newsView.setOnTouchListener(onNewsViewTouchListener);

        title = (TextView) view.findViewById(R.id.title);

        bshare = (ImageButton) view.findViewById(R.id.button_share);
        bshare.setOnClickListener(onShareListener);

        bbookmark = (ImageButton) view.findViewById(R.id.button_bookmark);
        bbookmark.setOnClickListener(onBookmarkListener);

        bclose = (ImageButton) view.findViewById(R.id.button_close);
        bclose.setOnClickListener(onCloseListener);

    }

    private News currentNews;

    public void displayNews(News news) {
        this.currentNews = news;

        title.setText(news.title);
        newsView.loadData(news.content, "text/html; charset=UTF-8", null);

        setBookmarkButtonImage();

        view.setVisibility(RelativeLayout.VISIBLE);
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

    private View.OnClickListener onBookmarkListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mstime = DEFAULT_WAITING_TIME;

            if (dataCenter.isBookmarked(currentNews)) {
                dataCenter.unBookmarkNews(currentNews);
            } else {
                dataCenter.bookmarkNews(currentNews);
            }
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

    private View.OnClickListener onCloseListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            handler.obtainMessage(State.ACTION_CLOSE_NEWS, null).sendToTarget();
            close();
        }
    };


    private static final int DEFAULT_WAITING_TIME = 3000;
    private int mstime = 0;
    private View.OnTouchListener onNewsViewTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                bshare.setVisibility(ImageButton.VISIBLE);
                bbookmark.setVisibility(ImageButton.VISIBLE);
                bclose.setVisibility(ImageButton.VISIBLE);

                mstime = DEFAULT_WAITING_TIME;
                if (timer.getState() == Thread.State.NEW) {
                    timer.start();
                } else if (timer.getState() == Thread.State.WAITING) {
                    synchronized (timer) {
                        timer.notify();
                    }
                }
            }
            return true;
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
                                bbookmark.setVisibility(ImageButton.INVISIBLE);
                                bshare.setVisibility(ImageButton.INVISIBLE);
                                bclose.setVisibility(ImageButton.INVISIBLE);
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
        Log.d("##NewsView##", text);
    }

}
