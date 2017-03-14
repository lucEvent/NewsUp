package com.lucevent.newsup.view.util;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.io.BookmarksManager;
import com.lucevent.newsup.io.SDManager;
import com.lucevent.newsup.kernel.AppData;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.TreeSet;

public class NewsView extends RelativeLayout {

    private News currentNews;

    private Fragment fragmentContext;
    private DrawerLayout drawer;
    private ActionBar actionBar;

    private WebView webView;
    private FloatingActionButton button_bookmark;
    private NewsSideToolbar sideToolbar;
    private boolean nightMode;

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

        sideToolbar = (NewsSideToolbar) findViewById(R.id.side_toolbar);

        button_bookmark = (FloatingActionButton) findViewById(R.id.button_bookmark);
        findViewById(R.id.button_share).setOnClickListener(onShareAction);
        findViewById(R.id.button_night).setOnClickListener(onNightModeSelected);

        nightMode = AppSettings.getNightModeStatus();

        SCRIPTS = "<script async defer>" + SDManager.readRaw(context, R.raw.instagram) + "</script>" +
                "<script type='text/javascript'>" + SDManager.readRaw(context, R.raw.twitter) + "</script>";

        //     gd = new GestureDetectorCompat(context, onGestureListener);
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);

        if (viewHeight != -2) {
            int aux = viewWidth;
            viewWidth = viewHeight;
            viewHeight = aux;
        }

        if (getVisibility() == View.VISIBLE) {

            ViewGroup.LayoutParams layoutParams = getLayoutParams();

            layoutParams.width = -1;
            layoutParams.height = -1;

            requestLayout();
        }
    }

    //  GestureDetectorCompat gd;

    public void setFragmentContext(Fragment context, @Nullable DrawerLayout drawer)
    {
        this.fragmentContext = context;
        this.actionBar = ((AppCompatActivity) context.getActivity()).getSupportActionBar();
        this.actionBar.setShowHideAnimationEnabled(false);
        this.drawer = drawer;
    }

    public void setBookmarkChangeListener(View.OnClickListener bookmarkChangeListener)
    {
        button_bookmark.setOnClickListener(bookmarkChangeListener);
    }

    private static final String NEWS_STYLE_DAY = "<style>" +
            "body{margin:20px;font-family:sans-serif-light;font-weight:300;font-size:17px;line-height:1.7;background-color:#fff;color:#000;}" +
            "blockquote{margin:10px;padding:5px 10px 5px 10px;background-color:#eee}" +
            "a{color:#%a_c;}" +
            "</style>";

    private static final String NEWS_STYLE_NIGHT = "<style>" +
            "body{margin:20px;font-family:sans-serif-light;font-weight:300;font-size:17px;line-height:1.7;background-color:#000;color:#fff;}" +
            "blockquote{margin:10px;padding:5px 10px 5px 10px;background-color:#111}" +
            "a{color:#%a_c;}" +
            "</style>";

    private static final String GRAPHYCS_STYLE = "<style>" +
            "iframe,video{width:100%;margin:0;padding:0;min-height:250px}" +
            "img,figure{width:100%;height:auto;margin:0;padding:0}" +
            "div > h2 > a > img {width:auto;}" +
            "</style>";

    private static final String GRAPHYCS_STYLE_NO_INTERNET = "<style>" +
            "iframe,video{width:0;height:0;margin:0;padding:0}" +
            "img,figure{width:0;height:0;margin:0;padding:0}" +
            "div > h2 > a > img{width:0;height:0;margin:0;padding:0}" +
            "</style>";

    private String computeStyle(Site site)
    {
        int a_color;
        if (nightMode) {
            if (site.getColorDarkness() > 0.95)
                a_color = 0x555555;
            else if (site.getColorDarkness() > 0.7)
                a_color = Utils.brighter(site.color, 1.2 - site.getColorDarkness()) & 0xffffff;
            else
                a_color = site.color & 0xffffff;
        } else
            a_color = (site.getColorDarkness() < 0.3 ? Utils.darker(site.color, 0.6) : site.color) & 0xffffff;


        String news_style = nightMode ? NEWS_STYLE_NIGHT : NEWS_STYLE_DAY;

        return site.getStyle() +
                news_style.replace("%a_c", String.format("%06x", a_color)) +
                (isInternetAvailable() ? GRAPHYCS_STYLE : GRAPHYCS_STYLE_NO_INTERNET) +
                SCRIPTS;
    }

    private static String SCRIPTS = "";

    private int viewWidth = -2;
    private int viewHeight = -2;
    private View newsItemView;

    public void displayNews(News news, final View from)
    {
        currentNews = news;
        button_bookmark.setTag(news);
        newsItemView = from;

        updateNewsView(0);

        setBookmarkButtonImage(button_bookmark);

        if (drawer != null)
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        actionBar.hide();

        setVisibility(View.VISIBLE);

        if (viewHeight == -2) {
            Rect r = new Rect();
            fragmentContext.getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
            viewHeight = ((ViewGroup) getParent()).getHeight() + actionBar.getHeight() + r.top;
            viewWidth = getWidth();
        }

        AppAnimator.expandMoving(this, from, viewHeight, new AppAnimator.AppAnimatorListener() {
            @Override
            public void onAnimationEnd(Animation animation)
            {
                //  hiding System UI
                Window w = fragmentContext.getActivity().getWindow();
                w.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                w.getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LOW_PROFILE
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            }
        });
    }

    private void updateNewsView(int scroll)
    {
        Site site = AppData.getSiteByCode(currentNews.site_code);

        String webContent = computeStyle(site) + "<h3>" + currentNews.title + "</h3>" + currentNews.content;

        webView.loadDataWithBaseURL("", webContent, "text/html", "utf-8", null);
        if (scroll > 0)
            webView.setScrollY(scroll);
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

        actionBar.show();

        webView.setScrollY(0);
        AppAnimator.collapseMoving(this, newsItemView, viewHeight, new AppAnimator.AppAnimatorListener() {
            @Override
            public void onAnimationEnd(Animation animation)
            {
                if (drawer != null)
                    drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

                webView.loadUrl("about:blank");
                setVisibility(View.GONE);
            }
        });

        if (!sideToolbar.closed) {
            sideToolbar.close();
        }
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

    private final View.OnClickListener onShareAction = new View.OnClickListener() {
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

    private final View.OnClickListener onNightModeSelected = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            nightMode = !nightMode;
            updateNewsView(webView.getScrollY());
            AppSettings.setNightModeStatus(nightMode);
        }
    };

    private final View.OnTouchListener onNewsContentTouch = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            //        gd.onTouchEvent(event);

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:

                    break;
                case MotionEvent.ACTION_UP:

                    if (swipingDown) {
                        swipingDown = false;

                        TranslateAnimation swipeAnimation = new TranslateAnimation(0, 0, swipedDown, 0);
                        swipeAnimation.setDuration((long) (swipedDown / 2));
                        swipeAnimation.setFillAfter(true);

                        webView.setAnimation(swipeAnimation);
                    }
                    break;
            /*    case MotionEvent.ACTION_MOVE:
                    if (swipingDown) {
                  //      webView.setTranslationY(previousY-event.getY());
                    }
                    if (webView.getScrollY() == 0) {
                        if (start){
                            start=false;
                        }
                        else{
                            swipingDown = true;
                            previousY = event.getY();
                        }
                    }
                    break;
                default:
                    System.out.println("Other... " + event.getAction());*/
            }
            return false;
        }

    };

    private boolean swipingDown = false;
    private float swipedDown = 0;
    private GestureDetector.OnGestureListener onGestureListener = new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e)
        {
            System.out.println("onDown");
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e)
        {
            System.out.println("onShowPress");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e)
        {
            System.out.println("onSingleTapUp");
            return false;
        }

        private boolean nocount = false;

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
        {
            System.out.println("onScroll");
            if (swipingDown) {

                if (nocount) {

                    float from = swipedDown;
                    float to = swipedDown - (3 * distanceY);
                    swipedDown = to;

                    if (swipedDown < 0) {
                        to = 0;
                        swipingDown = false;
                    }

                    TranslateAnimation swipeAnimation = new TranslateAnimation(0, 0, from, to);
                    swipeAnimation.setDuration(100);
                    swipeAnimation.setFillAfter(true);

                    webView.startAnimation(swipeAnimation);
                    webView.setScrollY(0);
                }
                nocount = !nocount;
            } else if (distanceY < 0) {//scrolling down

                if (webView.getScrollY() == 0) {
                    //closing news
                    swipingDown = true;
                    nocount = true;
                    swipedDown = 0;
                    return true;
                }

            }
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e)
        {
            System.out.println("onLongPress");
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
        {
            System.out.println("onFling");
            return false;
        }
    };

}