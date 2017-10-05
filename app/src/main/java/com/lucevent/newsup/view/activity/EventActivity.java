package com.lucevent.newsup.view.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.event.Event;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.io.BookmarksManager;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.kernel.KernelManager;
import com.lucevent.newsup.permission.StoragePermissionHandler;
import com.lucevent.newsup.view.adapter.NewsAdapter;
import com.lucevent.newsup.view.util.NewsAdapterList;
import com.lucevent.newsup.view.util.NewsView;

import java.lang.ref.WeakReference;
import java.util.Collection;

public class EventActivity extends AppCompatActivity implements View.OnClickListener {

    private Event mEvent;

    private KernelManager manager;

    private Handler handler;
    private StoragePermissionHandler permissionHandler;

    private NewsAdapter adapter;
    private NewsView newsView;
    private RecyclerView recyclerView;
    private View progressBar;

    private boolean displayingNews = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_event);

        handler = new Handler(this);
        manager = new KernelManager(this);
        permissionHandler = new StoragePermissionHandler(this);

        // Views
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        adapter = new NewsAdapter(this, null, onBookmarkClick, NewsAdapterList.SortBy.byTime);
        adapter.setLoadImages(AppSettings.loadImages());
        adapter.showSiteLogo(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setAutoMeasureEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        newsView = (NewsView) findViewById(R.id.news_view);
        newsView.setFragmentContext(this, null);
        newsView.setBookmarkChangeListener(onBookmarkClick);

        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(ProgressBar.VISIBLE);

        // setup
        Bundle bundle = getIntent().getExtras();
        mEvent = AppData.getEvent(bundle.getInt("e.code"));

        manager.getEvent(mEvent, handler);
        setTitle(mEvent.title);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (displayingNews)
            newsView.resume();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if (displayingNews)
            newsView.pause();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        appmenu = null;
    }

    @Override
    public void onBackPressed()
    {
        if (displayingNews) {
            newsView.hideNews();
            displayingNews = false;
        } else {
            manager.cancelAll();
            super.onBackPressed();
        }
    }

    private Menu appmenu;

    /*
        @Override
        public boolean onCreateOptionsMenu(Menu menu)
        {
            appmenu = menu;
            appmenu.add(R.string.menu_settings)
                    .setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
                    .setIcon(R.drawable.ic_configuration)
                    .setOnMenuItemClickListener(onSiteConfigurationAction);
            return true;
        }
    */
    @Override
    public void onClick(final View v)
    {
        final News news = (News) v.getTag();
        final Context context = this;

        KernelManager.readContentOf(news);

        if (news.content != null && !news.content.isEmpty()) {
            newsView.displayNews(news, v);
            displayingNews = true;
            KernelManager.setNewsRead(news);
            return;
        }
        KernelManager.fetchContentOf(news);

        View view = LayoutInflater.from(context).inflate(R.layout.d_news_not_found, null);

        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(R.string.msg_cant_load_content)
                .setMessage(R.string.msg_news_not_found)
                .setView(view)
                .create();

        view.findViewById(R.id.action_try_again).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2)
            {
                EventActivity.this.onClick(v);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.action_open_in_browser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2)
            {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(news.link));
                context.startActivity(browserIntent);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.action_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2)
            {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private static class Handler extends android.os.Handler {

        private final WeakReference<EventActivity> context;

        Handler(EventActivity context)
        {
            this.context = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg)
        {
            EventActivity service = context.get();
            switch (msg.what) {
                case AppCode.NEWS_COLLECTION:
                    Collection<News> news = (Collection<News>) msg.obj;

                    if (news.isEmpty())
                        return;

                    service.adapter.addAll(news);
                    if (service.adapter.getItemCount() == 0 || service.recyclerView.computeVerticalScrollOffset() == 0)
                        service.recyclerView.smoothScrollToPosition(0);
                    break;
                case AppCode.NEWS_LOADED:
                    service.progressBar.setVisibility(ProgressBar.GONE);
                    break;
                case AppCode.NO_INTERNET:
                    Snackbar snackbar = Snackbar.make(service.recyclerView, R.string.msg_no_internet_connection, Snackbar.LENGTH_LONG);
           /*         if (service.currentSite != null) {
                        int color = service.currentSite.color == 0xffffffff ? 0xffcccccc : service.currentSite.color;
                        snackbar.getView().setBackgroundColor(color);
                    }
            */
                    snackbar.show();
                    break;
                case AppCode.ERROR:
                    AppSettings.printerror("[NLF] Error received by the Handler", null);
                    break;
                default:
                    AppSettings.printerror("[NLF] OPTION UNKNOWN: " + msg.what, null);
            }
        }

    }

    private MenuItem.OnMenuItemClickListener onSiteConfigurationAction = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item)
        {
            //// TODO: 26/09/2017
            return true;
        }
    };

    private View tempBookmarkButton;

    private View.OnClickListener onBookmarkClick = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            if (permissionHandler.checkAndAsk(EventActivity.this)) {
                News news = (News) v.getTag();

                BookmarksManager.toggleBookmark(news);

                newsView.setBookmarkButtonImage(v);
                adapter.update(news);

            } else
                tempBookmarkButton = v;
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
    {
        if (permissionHandler.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            News news = (News) tempBookmarkButton.getTag();
            BookmarksManager.toggleBookmark(news);
            newsView.setBookmarkButtonImage(tempBookmarkButton);
            adapter.update(news);
        }
    }

}