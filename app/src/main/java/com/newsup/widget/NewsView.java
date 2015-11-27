package com.newsup.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.newsup.R;
import com.newsup.io.BookmarksManager;
import com.newsup.kernel.News;
import com.newsup.kernel.NewsDataCenter;
import com.newsup.task.TaskMessage;

public class NewsView {

    private Context context;
    private BookmarksManager bmManager;
    private Handler handler;

    private View view;
    private RelativeLayout buttons;

    private WebView newsView;
    private ImageButton bbookmark;

    public NewsView(Activity context, NewsDataCenter dataCenter, Handler handler) {
        this.context = context;
        this.handler = handler;
        this.bmManager = new BookmarksManager(dataCenter, null);

        view = context.findViewById(R.id.layoutcontent);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        buttons = (RelativeLayout) context.findViewById(R.id.buttons);

        newsView = (WebView) view.findViewById(R.id.content);
        newsView.setOnTouchListener(onNewsViewTouchListener);
        newsView.setPersistentDrawingCache(WebView.PERSISTENT_NO_CACHE);
        WebSettings webSettings = newsView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        view.findViewById(R.id.button_share).setOnClickListener(onShareListener);

        bbookmark = (ImageButton) view.findViewById(R.id.button_bookmark);
        bbookmark.setOnClickListener(onBookmarkListener);

    }

    private News currentNews;

    private final String css = "<style>img, iframe, video,figure {width: 100%; height: auto; margin: 0; padding: 0} div > h2 > a > img {width: auto;}</style>";
    private final String fontcss = "<style>" +
            "@font-face { font-family: customfont; src: url(\"fonts/customfont.woff\"); }" +
            "body { font-family: customfont; font-weight: 300; font-size: 16px; line-height: 1.67; }" +
            "</style>";


    public boolean displayNews(News news) {
        this.currentNews = news;

        if (news.content == null) return false;

        String title = "<h2>" + news.title + "</h2>";

        newsView.loadDataWithBaseURL("file:///android_asset/", css + fontcss + title + news.content, "text/html", "utf-8", null);

        setBookmarkButtonImage();

        view.setVisibility(RelativeLayout.VISIBLE);
        return true;
    }

    private void setBookmarkButtonImage() {
        if (bmManager.isBookmarked(currentNews)) {
            bbookmark.setImageResource(R.drawable.ic_bookmark);
        } else {
            bbookmark.setImageResource(R.drawable.ic_bookmark_border);
        }
    }

    public void close() {
        view.setVisibility(RelativeLayout.GONE);

        newsView.loadUrl("about:blank");
        newsView.clearHistory();
        newsView.clearCache(true);
    }

    private View.OnClickListener onBookmarkListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mstime = DEFAULT_WAITING_TIME;

            if (bmManager.isBookmarked(currentNews)) {
                bmManager.unBookmarkNews(currentNews);
            } else {
                bmManager.bookmarkNews(currentNews);
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
            context.startActivity(Intent.createChooser(sendIntent, context.getString(R.string.sharewith)));
        }
    };

    private static final int DEFAULT_WAITING_TIME = 3000;
    private int mstime = 0;
    //TODO To Improve
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
