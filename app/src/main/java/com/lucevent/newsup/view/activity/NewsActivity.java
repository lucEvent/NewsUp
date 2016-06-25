package com.lucevent.newsup.view.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.io.BookmarksManager;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.AppData;

public class NewsActivity extends AppCompatActivity {

    private static final String css = "<style>" +
            "body { margin: 20px }" +
            "iframe, video {width: 100%; margin: 0; padding: 0}" +
            "img, figure {width: 100%; height:auto; margin: 0; padding: 0}" +
            "div > h2 > a > img {width: auto;}" +
            "blockquote{margin:10px;padding:5px 10px 5px 10px;background-color:#f2f2f2}" +
            "a{color: #%a_color;}" +
            "</style>";
    private static final String fontcss = "<style>" +
            "@font-face { font-family: customfont; src: url(\"fonts/customfont.woff\"); }" +
            "body { font-family: customfont; font-weight: 300; font-size: 17px; line-height: 1.7; }" +
            "</style>";

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();

    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run()
        {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            newsView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };

    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run()
        {
            hide();
        }
    };

    private News currentNews;
    private BookmarksManager bookmarksManager;

    private WebView newsView;
    private FloatingActionButton button_bookmark, button_share;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_news);

        mVisible = true;

        bookmarksManager = new BookmarksManager(null);
        currentNews = (News) getIntent().getSerializableExtra(AppCode.SEND_NEWS);

        newsView = (WebView) findViewById(R.id.webview);
        newsView.setOnTouchListener(onNewsContentTouch);
        newsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                toggle();
            }
        });
        newsView.setPersistentDrawingCache(WebView.PERSISTENT_NO_CACHE);

        WebSettings webSettings = newsView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDisplayZoomControls(false);

        button_bookmark = (FloatingActionButton) findViewById(R.id.button_bookmark);
        button_bookmark.setOnClickListener(onBookmarkAction);
        button_share = (FloatingActionButton) findViewById(R.id.button_share);
        button_share.setOnClickListener(onShareAction);

        Site site = AppData.getSiteByCode(currentNews.site_code);
        int a_color = (site.color == 0xffffffff ? 0xcccccc : site.color & 0xffffff);

        String siteStyle = site.getStyle() + css.replace("%a_color", String.format("%06x", a_color));
        String webContent = fontcss + siteStyle + "<h2>" + currentNews.title + "</h2>" + currentNews.content;

        newsView.loadDataWithBaseURL("file:///android_asset/", webContent, "text/html", "utf-8", null);

        setBookmarkButtonImage();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !permissionGranted()) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE},
                    AppCode.REQUEST_PERMISSION_WRITE_IN_STORAGE);
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        newsView.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        newsView.onPause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
    {
        switch (requestCode) {
            case AppCode.REQUEST_PERMISSION_WRITE_IN_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the contacts-related task you need to do.
                    bookmarksManager = new BookmarksManager(null);
                    setBookmarkButtonImage();

                } else {

                    // permission denied, boo!
                    Toast.makeText(this, R.string.msg_disk_permission_denied, Toast.LENGTH_LONG).show();

                }
                break;
            }
        }
    }

    private void setBookmarkButtonImage()
    {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M || permissionGranted()) {
            if (bookmarksManager.isBookmarked(currentNews))
                button_bookmark.setImageResource(R.drawable.ic_bookmark);

            else
                button_bookmark.setImageResource(R.drawable.ic_bookmark_border);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        delayedHide(0);
    }

    private void toggle()
    {
        if (mVisible)
            hide();
        else
            show();
    }

    private void hide()
    {
        mVisible = false;
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show()
    {
        mVisible = true;
        mHideHandler.removeCallbacks(mHidePart2Runnable);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis)
    {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    private View.OnClickListener onBookmarkAction = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M || permissionGranted()) {

                if (bookmarksManager.isBookmarked(currentNews))
                    bookmarksManager.unBookmarkNews(currentNews);
                else
                    bookmarksManager.bookmarkNews(currentNews);

                setBookmarkButtonImage();
            } else
                Toast.makeText(NewsActivity.this, R.string.msg_disk_permission_denied, Toast.LENGTH_LONG).show();
        }
    };

    private View.OnClickListener onShareAction = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, currentNews.title + " " + currentNews.link);
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, getString(R.string.share_with)));
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
                while (true)
                    try {
                        Thread.sleep(DEFAULT_WAITING_TIME);
                        synchronized (onNewsContentTouch) {

                            runOnUiThread(new Runnable() {
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

    private boolean permissionGranted()
    {
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

}