package com.lucevent.newsup.view.util;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.io.BookmarksManager;
import com.lucevent.newsup.kernel.AppData;

public class NewsView extends RelativeLayout {

    private News currentNews;

    private Fragment fragmentContext;
    private DrawerLayout drawer;
    private ActionBar actionBar;

    private WebView webView;
    private FloatingActionButton button_bookmark, button_share;

    public NewsView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.v_news, this, true);

        webView = (WebView) findViewById(R.id.webview);
        webView.setOnTouchListener(onNewsContentTouch);
        webView.setPersistentDrawingCache(WebView.PERSISTENT_NO_CACHE);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDisplayZoomControls(false);

        button_bookmark = (FloatingActionButton) findViewById(R.id.button_bookmark);
        button_share = (FloatingActionButton) findViewById(R.id.button_share);
        button_share.setOnClickListener(onShareAction);
    }

    public void setFragmentContext(Fragment context, @Nullable DrawerLayout drawer)
    {
        this.fragmentContext = context;
        this.actionBar = ((AppCompatActivity) context.getActivity()).getSupportActionBar();
        this.drawer = drawer;
    }

    public void setBookmarkChangeListener(View.OnClickListener bookmarkChangeListener)
    {
        button_bookmark.setOnClickListener(bookmarkChangeListener);
    }

    private static final String NEWS_STYLE = "<style>" +
            "body { margin: 20px; font-family: sans-serif-light; font-weight: 300; font-size: 17px; line-height: 1.7; }" +
            "blockquote{margin:10px;padding:5px 10px 5px 10px;background-color:#f2f2f2}" +
            "a{color: #%a_color;}" +
            "</style>";

    private static final String GRAPHYCS_STYLE = "<style>" +
            "iframe, video {width: 100%; margin: 0; padding: 0}" +
            "img, figure {width: 100%; height:auto; margin: 0; padding: 0}" +
            "div > h2 > a > img {width: auto;}" +
            "</style>";

    private static final String GRAPHYCS_STYLE_NO_INTERNET = "<style>" +
            "iframe, video {width: 0; height: 0; margin: 0; padding: 0}" +
            "img, figure {width: 0; height:0; margin: 0; padding: 0}" +
            "div > h2 > a > img {width: 0; height:0;margin: 0; padding: 0}" +
            "</style>";

    private static final String TWITTER_STYLE = "<script type=\"text/javascript\" src=\"https://platform.twitter.com/widgets.js\"></script>";

    public void displayNews(News news)
    {
        currentNews = news;
        button_bookmark.setTag(news);

        //  hiding System UI
        Window w = fragmentContext.getActivity().getWindow();
        w.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        w.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        if (drawer != null)
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        actionBar.hide();

        // displaying current news
        setVisibility(View.VISIBLE);

        Site site = AppData.getSiteByCode(currentNews.site_code);
        int a_color = (site.color == 0xffffffff ? 0xcccccc : site.color & 0xffffff);

        String style = site.getStyle() +
                NEWS_STYLE.replace("%a_color", String.format("%06x", a_color)) +
                (isInternetAvailable() ? GRAPHYCS_STYLE : GRAPHYCS_STYLE_NO_INTERNET) +
                TWITTER_STYLE;

        String webContent = style + "<h2>" + currentNews.title + "</h2>" + currentNews.content;

        webView.loadDataWithBaseURL("file:///android_asset/", webContent, "text/html", "utf-8", null);

        setBookmarkButtonImage(button_bookmark);
    }

    public void resume()
    {
        webView.onResume();
    }

    public void pause()
    {
        webView.onPause();
    }

    public void hideNews()
    {
        // showing System UI
        Window w = fragmentContext.getActivity().getWindow();
        w.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

        if (drawer != null)
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

        // hiding news
        actionBar.show();
        setVisibility(View.GONE);
        webView.loadUrl("about:blank");
    }

    private boolean isInternetAvailable()
    {
        NetworkInfo ni = ((ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo();
        return ni != null && ni.isConnected() && ni.isAvailable();
    }

    public void setBookmarkButtonImage(View view)
    {
        News news = (News) view.getTag();
        if (BookmarksManager.isBookmarked(news))
            ((ImageView) view).setImageResource(R.drawable.ic_bookmark);

        else
            ((ImageView) view).setImageResource(R.drawable.ic_bookmark_border);
    }

    private View.OnClickListener onShareAction = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, currentNews.title + " " + currentNews.link);
            sendIntent.setType("text/plain");
            getContext().startActivity(Intent.createChooser(sendIntent, getContext().getString(R.string.share_with)));
        }
    };

    private final View.OnTouchListener onNewsContentTouch = new View.OnTouchListener() {

        private static final int DEFAULT_WAITING_TIME = 3500;
        private Boolean buttons_visible = true;

        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    synchronized (onNewsContentTouch) {

                        if (!buttons_visible) {
                            buttonsIn();
                            buttons_visible = true;
                        } else {
                            if (actionHide.getState() == Thread.State.RUNNABLE || actionHide.getState() == Thread.State.TIMED_WAITING)
                                actionHide.interrupt();
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    synchronized (onNewsContentTouch) {

                        if (actionHide.getState() == Thread.State.NEW)
                            actionHide.start();
                        else if (actionHide.getState() == Thread.State.WAITING)
                            synchronized (actionHide) {
                                actionHide.notify();
                            }
                    }
                    break;
            }
            return false;
        }

        final Thread actionHide = new Thread(new Runnable() {
            @Override
            public void run()
            {
                //noinspection InfiniteLoopStatement
                while (true)
                    try {
                        Thread.sleep(DEFAULT_WAITING_TIME);
                        synchronized (onNewsContentTouch) {
                            fragmentContext.getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run()
                                {
                                    buttonsOut();
                                }
                            });
                            buttons_visible = false;
                        }
                        synchronized (actionHide) {
                            actionHide.wait();
                        }

                    } catch (Exception e) {
                        try {
                            synchronized (actionHide) {
                                actionHide.wait();
                            }
                        } catch (Exception ignored) {
                        }
                    }
            }
        });
    };

    public void buttonsOut()
    {
        ScaleAnimation animation = new ScaleAnimation(
                1f, 0f, // Start and end values for the X axis scaling
                1f, 0f, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling

        animation.setDuration(500);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation)
            {
            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                button_share.setVisibility(View.GONE);
                button_bookmark.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {
            }
        });

        button_share.startAnimation(animation);
        button_bookmark.startAnimation(animation);
    }

    public void buttonsIn()
    {
        ScaleAnimation animation = new ScaleAnimation(
                0f, 1f, // Start and end values for the X axis scaling
                0f, 1f, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling

        animation.setDuration(500);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation)
            {
                button_share.setVisibility(View.VISIBLE);
                button_bookmark.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {
            }
        });

        button_share.startAnimation(animation);
        button_bookmark.startAnimation(animation);
    }

}