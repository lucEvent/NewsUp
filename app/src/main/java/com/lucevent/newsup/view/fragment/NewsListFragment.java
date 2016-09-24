package com.lucevent.newsup.view.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.Main;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.NewsMap;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.kernel.NewsManager;
import com.lucevent.newsup.services.StatisticsService;
import com.lucevent.newsup.view.adapter.NewsAdapter;
import com.lucevent.newsup.view.dialog.SectionsDialog;
import com.lucevent.newsup.view.util.ContentLoader;
import com.lucevent.newsup.view.util.NewsView;
import com.lucevent.newsup.view.util.OnBackPressedListener;

import java.lang.ref.WeakReference;

public class NewsListFragment extends android.app.Fragment implements View.OnClickListener,
        OnBackPressedListener {

    public static NewsListFragment instanceFor(int site_code)
    {
        Bundle bundle = new Bundle();
        bundle.putInt(AppCode.SEND_SITE_CODE, site_code);

        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static NewsListFragment instanceForNotification(int[] news_ids)
    {
        Bundle bundle = new Bundle();
        bundle.putInt(AppCode.SEND_SITE_CODE, -2);
        bundle.putIntArray(AppCode.SEND_NEWS_IDS, news_ids);

        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public int currentSiteCode, lastLoadedSiteCode;
    private Site currentSite;
    private SectionsDialog sectionsDialog;

    public void setSite(int site_code)
    {
        currentSiteCode = site_code;
        if (site_code > 0)
            currentSite = AppData.getSiteByCode(site_code);
        else
            currentSite = null;

        Sections sections = currentSite != null ? currentSite.sections : new Sections();
        sectionsDialog = new SectionsDialog(getActivity(), sections, onSectionSelected);
    }

    public void setUp()
    {
        if (currentSiteCode == lastLoadedSiteCode)
            return;

        adapter.clear();
        progressBar.setVisibility(ProgressBar.VISIBLE);
        switch (currentSiteCode) {
            case -2:

                int[] news_ids = getArguments().getIntArray(AppCode.SEND_NEWS_IDS);
                assert news_ids != null : "Arguments can't be recovered";

                NewsMap newsMap = new NewsMap();

                for (int news_id : news_ids)
                    if (news_id > 0)
                        newsMap.add(dataManager.getNewsById(news_id));

                getActivity().setTitle(R.string.app_name);
                handler.obtainMessage(AppCode.NEWS_MAP_READ, newsMap).sendToTarget();
                handler.obtainMessage(AppCode.NEWS_LOADED).sendToTarget();
                break;

            case -1:
                dataManager.getMainNews();
                getActivity().setTitle(R.string.my_news);
                break;

            default:
                dataManager.getNewsOf(currentSite, null);
                getActivity().setTitle(currentSite.name);
        }
        lastLoadedSiteCode = currentSiteCode;
        setUpColors();
    }

    private NewsManager dataManager;
    private NewsAdapter adapter;
    private Handler handler;

    private View mainView;
    private NewsView newsView;
    private RecyclerView recyclerView;
    private FloatingActionButton btn_sections;
    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        handler = new Handler(this);
        dataManager = new NewsManager(getActivity(), handler);

        lastLoadedSiteCode = -9;
        setSite(getArguments().getInt(AppCode.SEND_SITE_CODE));

        getActivity().startService(
                new Intent(getActivity(), StatisticsService.class)
                        .putExtra(AppCode.SEND_REQUEST_CODE, StatisticsService.REQ_SEND)
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if (adapter == null) {
            mainView = inflater.inflate(R.layout.f_news_list, container, false);

            adapter = new NewsAdapter(new NewsArray(), this);
            adapter.showSiteLogo(currentSite == null);

            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setAutoMeasureEnabled(true);

            recyclerView = (RecyclerView) mainView.findViewById(R.id.list);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setHasFixedSize(false);
            recyclerView.addOnScrollListener(new ContentLoader(layoutManager) {
                @Override
                public void onLoadMore()
                {
                    adapter.loadMoreData();
                }
            });
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

            newsView = (NewsView) mainView.findViewById(R.id.news_view);
            newsView.setFragmentContext(this, ((Main) getActivity()).drawer);

            btn_sections = (FloatingActionButton) mainView.findViewById(R.id.button_sections);
            btn_sections.setOnClickListener(onSectionsAction);

            progressBar = (ProgressBar) mainView.findViewById(R.id.progress_bar);
        }
        setUp();
        return mainView;
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

    private Menu appmenu;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        appmenu = menu;
        appmenu.add(R.string.menu_favorite)
                .setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
                .setIcon(R.drawable.ic_favorite)
                .setOnMenuItemClickListener(onFavoriteToggleAction);
        appmenu.add(R.string.menu_settings)
                .setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
                .setIcon(R.drawable.ic_configuration)
                .setOnMenuItemClickListener(onSiteConfigurationAction);

        super.onCreateOptionsMenu(appmenu, inflater);

        setUpColors();
    }

    private boolean displayingNews = false;

    @Override
    public boolean onBackPressed()
    {
        if (displayingNews) {
            if (currentSite != null)
                btn_sections.setVisibility(View.VISIBLE);
            newsView.hideNews();
            displayingNews = false;
            return true;
        }
        return false;
    }

    @Override
    public void onClick(final View v)
    {
        final News news = (News) v.getTag();
        final Context context = getActivity();

        NewsManager.getNewsContent(news);

        if (news.content != null) {
            newsView.displayNews(news);
            btn_sections.setVisibility(View.GONE);
            displayingNews = true;
            NewsManager.addToHistory(news);
            return;
        }

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
                NewsListFragment.this.onClick(v);
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

    static class Handler extends android.os.Handler {

        private final WeakReference<NewsListFragment> context;

        Handler(NewsListFragment context)
        {
            this.context = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg)
        {
            NewsListFragment service = context.get();
            switch (msg.what) {
                case AppCode.NEWS_MAP_READ:
                case AppCode.NEWS_MAP_FRAGMENT_READ:
                    service.adapter.addAll((NewsMap) msg.obj);
                    service.recyclerView.smoothScrollToPosition(0);
                    break;
                case AppCode.NEWS_LOADED:
                    service.progressBar.setVisibility(ProgressBar.GONE);
                    break;
                case AppCode.NO_INTERNET:
                    Snackbar snackbar = Snackbar.make(service.mainView, R.string.msg_no_internet_connection, Snackbar.LENGTH_LONG);
                    if (service.currentSite != null) {
                        int color = service.currentSite.color == 0xffffffff ? 0xffcccccc : service.currentSite.color;
                        snackbar.getView().setBackgroundColor(color);
                    }
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

    @SuppressWarnings("ConstantConditions")
    public void setUpColors()
    {
        if (appmenu == null || appmenu.size() < 2) return;

        Toolbar ab = (Toolbar) getActivity().findViewById(R.id.toolbar);
        if (currentSite == null) {

            ab.setTitle(R.string.my_news);

            btn_sections.setVisibility(ImageButton.GONE);
            appmenu.getItem(0).setVisible(false);
            appmenu.getItem(1).setVisible(false);

        } else {

            ab.setTitle(currentSite.name);

            btn_sections.setVisibility(ImageButton.VISIBLE);
            btn_sections.setBackgroundTintList(ColorStateList.valueOf(currentSite.color));

            setFavoriteIcon();
            Drawable icon_conf = appmenu.getItem(1).setVisible(true).getIcon();

            if (currentSite.hasDarkColor()) {
                btn_sections.clearColorFilter();
                icon_conf.clearColorFilter();

            } else {
                btn_sections.setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
                icon_conf.setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
            }
        }
    }

    @SuppressWarnings("ConstantConditions")
    private void setFavoriteIcon()
    {
        Drawable icon_fav = ContextCompat.getDrawable(getActivity(),
                AppSettings.isFavorite(currentSite) ? R.drawable.ic_favorite : R.drawable.ic_favorite_border);

        appmenu.getItem(0).setVisible(true).setIcon(icon_fav);

        if (currentSite.hasDarkColor())
            icon_fav.clearColorFilter();
        else
            icon_fav.setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
    }

    private View.OnClickListener onSectionsAction = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            sectionsDialog.show();
        }
    };

    private MenuItem.OnMenuItemClickListener onFavoriteToggleAction = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item)
        {
            AppSettings.toggleFavorite(currentSite);
            setFavoriteIcon();
            return true;
        }
    };

    private MenuItem.OnMenuItemClickListener onSiteConfigurationAction = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item)
        {
            ((Main) getActivity()).onReplaceFragment(SiteSettingsFragment.instanceFor(currentSite.code), R.id.nav_settings, true);
            return true;
        }
    };

    private View.OnClickListener onSectionSelected = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            adapter.clear();
            Section section = (Section) v.getTag();
            dataManager.getNewsOf(currentSite, new int[]{currentSite.sections.indexOf(section)});
            sectionsDialog.dismiss();
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
    {
        newsView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
