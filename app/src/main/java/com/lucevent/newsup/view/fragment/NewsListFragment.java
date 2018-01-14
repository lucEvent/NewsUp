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
import android.util.Pair;
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
import com.lucevent.newsup.data.event.Event;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.io.BookmarksManager;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.kernel.KernelManager;
import com.lucevent.newsup.net.MainChangeListener;
import com.lucevent.newsup.permission.StoragePermissionHandler;
import com.lucevent.newsup.services.StatisticsService;
import com.lucevent.newsup.services.util.DownloadResponse;
import com.lucevent.newsup.view.adapter.NewsAdapter;
import com.lucevent.newsup.view.dialog.SectionsDialog;
import com.lucevent.newsup.view.util.NewsAdapterList;
import com.lucevent.newsup.view.util.NewsView;
import com.lucevent.newsup.view.util.OnBackPressedListener;
import com.lucevent.newsup.view.util.OnMoreSectionsClickListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class NewsListFragment extends android.app.Fragment implements View.OnClickListener,
        View.OnLongClickListener, OnBackPressedListener {

    public static NewsListFragment instanceFor(int site_code)
    {
        Bundle bundle = new Bundle();
        bundle.putInt(AppCode.SITE_CODE, site_code);

        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static NewsListFragment instanceFor(Bundle extras)
    {
        extras.putInt(AppCode.SITE_CODE, -2);

        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    public int currentSiteCode, lastLoadedSiteCode;
    private boolean showSectionsButton;
    private Site currentSite;
    private SectionsDialog sectionsDialog;

    public void setSite(int site_code)
    {
        currentSiteCode = site_code;
        if (site_code > 0)
            currentSite = AppData.getSiteByCode(site_code);
        else
            currentSite = null;

        if (sectionsDialog == null)
            sectionsDialog = new SectionsDialog(getActivity(), currentSite, onSectionSelected);
        else
            sectionsDialog.setSections(currentSite);
    }

    public void setUp()
    {
        if (currentSiteCode == lastLoadedSiteCode) {
            adapter.notifyDataSetChanged();
            return;
        }

        adapter.showSiteIcon(currentSite == null);
        adapter.clear();
        progressBar.setVisibility(ProgressBar.VISIBLE);
        switch (currentSiteCode) {
            case -2:    // Load from notification
                getActivity().setTitle(R.string.app_name);
                adapter.setOnMoreSectionsClick(null);
                showSectionsButton = false;

                Event event = null;
                if (getArguments().containsKey(AppCode.STRING_FILTERS)) {
                    event = new Event();
                    event.keyWords = getArguments().getStringArray(AppCode.STRING_FILTERS);
                }
                ArrayList<DownloadResponse.Source> sources = (ArrayList<DownloadResponse.Source>) getArguments().getSerializable(AppCode.SOURCES);
                assert sources != null : "Arguments can't be recovered";

                for (DownloadResponse.Source s : sources) {
                    Collection<News> c = dataManager.getSavedNewsOf(s);
                    if (event != null)
                        c = event.filter(c);

                    handler.obtainMessage(AppCode.NEWS_COLLECTION, c).sendToTarget();
                }

                handler.obtainMessage(AppCode.NEWS_LOADED).sendToTarget();
                break;

            case -1:    // Main sites
                showSectionsButton = false;
                adapter.setOnMoreSectionsClick(null);
                dataManager.getMainNews(handler);
                getActivity().setTitle(R.string.my_news);
                break;

            default:
                showSectionsButton = currentSite.getSections().size() > 1;
                adapter.setOnMoreSectionsClick(showSectionsButton ? onMoreSectionsClick : null);
                dataManager.getNewsOf(currentSite, null, handler);
                getActivity().setTitle(currentSite.name);
        }
        lastLoadedSiteCode = currentSiteCode;
        setUpColors();
    }

    public void onLoadImagesPreferenceChanged()
    {
        adapter.setUserPreferences();
    }

    private KernelManager dataManager;
    private NewsAdapter adapter;
    private Handler handler;
    private StoragePermissionHandler permissionHandler;

    private View mainView;
    private NewsView newsView;
    private RecyclerView recyclerView;
    private FloatingActionButton btn_sections;
    private View progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        Context context = getActivity();

        handler = new Handler(this);
        dataManager = new KernelManager(context);
        permissionHandler = new StoragePermissionHandler(context);

        lastLoadedSiteCode = -9;
        setSite(getArguments().getInt(AppCode.SITE_CODE));

        if (!AppSettings.DEBUG)
            context.startService(
                    new Intent(context, StatisticsService.class)
                            .putExtra(AppCode.REQUEST_CODE, StatisticsService.REQ_SEND)
            );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if (adapter == null) {
            mainView = inflater.inflate(R.layout.f_news_list, container, false);

            adapter = new NewsAdapter(this, this, onBookmarkClick, NewsAdapterList.SortBy.byTime);
            adapter.setUserPreferences();

            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setAutoMeasureEnabled(true);

            recyclerView = (RecyclerView) mainView.findViewById(R.id.list);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setHasFixedSize(false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

            newsView = (NewsView) mainView.findViewById(R.id.news_view);
            newsView.setFragmentContext(this, getActivity() instanceof Main ? ((Main) getActivity()).drawer : null);
            newsView.setBookmarkStateChangeListener(onBookmarkClick);

            btn_sections = (FloatingActionButton) mainView.findViewById(R.id.button_sections);
            btn_sections.setOnClickListener(onSectionsAction);

            progressBar = mainView.findViewById(R.id.progress_bar);
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

        sectionsDialog.setSections(currentSite);
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
            if (showSectionsButton)
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

        KernelManager.readContentOf(news);

        if (news.content != null && !news.content.isEmpty()) {
            newsView.displayNews(news);
            btn_sections.setVisibility(View.GONE);
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

    @Override
    public boolean onLongClick(View v)
    {
        //// TODO: 14/10/2016  
        return false;
    }

    private static class Handler extends android.os.Handler {

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
                case AppCode.NEWS_COLLECTION:
                    Collection<News> news = (Collection<News>) msg.obj;

                    if (news.isEmpty())
                        return;

                    if (service.currentSiteCode > 0 &&
                            news.iterator().next().site_code != service.currentSiteCode)
                        return;

                    service.adapter.addAll(news);
                    if (service.adapter.getItemCount() == 0 || service.recyclerView.computeVerticalScrollOffset() == 0)
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

            if (showSectionsButton) {
                btn_sections.setVisibility(ImageButton.VISIBLE);
                btn_sections.setBackgroundTintList(ColorStateList.valueOf(currentSite.color == 0xffffffff ? 0xff666666 : currentSite.color));
            } else
                btn_sections.setVisibility(ImageButton.GONE);

            setFavoriteIcon();
            Drawable icon_conf = appmenu.getItem(1).setVisible(true).getIcon();

            if (currentSite.needsBrightColors()) {
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

        if (currentSite.needsBrightColors())
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
            ((MainChangeListener) getActivity()).onReplaceFragment(SiteSettingsFragment.instanceFor(currentSite.code), R.id.nav_settings, true);
            return true;
        }
    };

    private View.OnClickListener onSectionSelected = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            adapter.clear();
            progressBar.setVisibility(ProgressBar.VISIBLE);
            int iSection = (int) v.getTag();
            dataManager.getNewsOf(currentSite, new int[]{iSection}, handler);
            sectionsDialog.dismiss();
        }
    };

    private View tempBookmarkButton;

    private View.OnClickListener onBookmarkClick = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            if (permissionHandler.checkAndAsk(NewsListFragment.this))
                bookmarkStateChanged(v);
            else
                tempBookmarkButton = v;
        }
    };

    private OnMoreSectionsClickListener onMoreSectionsClick = new OnMoreSectionsClickListener() {
        @Override
        public Set<Pair<Integer, Section>> sections()
        {
            Set<Pair<Integer, Section>> res;
            Sections s = currentSite.getSections();
            if (s.size() <= 9) {
                res = new HashSet<>(s.size());
                for (int i = 0; i < s.size(); i++)
                    res.add(new Pair<>(i, s.get(i)));

            } else {
                res = new TreeSet<>(new Comparator<Pair<Integer, Section>>() {
                    @Override
                    public int compare(Pair<Integer, Section> s1, Pair<Integer, Section> s2)
                    {
                        return s1.second.name.compareTo(s2.second.name);
                    }
                });
                Random r = new Random();
                while (res.size() < 9) {
                    int pos;
                    do {
                        pos = r.nextInt(s.size());
                    } while (s.get(pos).level == -1);
                    res.add(new Pair<>(pos, s.get(pos)));
                }
            }
            return res;
        }

        @Override
        public void onClick(View v)
        {
            onSectionSelected.onClick(v);
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
    {
        if (permissionHandler.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            bookmarkStateChanged(tempBookmarkButton);
        }
    }

    private void bookmarkStateChanged(View btn)
    {
        News news = (News) btn.getTag();

        btn.setSelected(
                BookmarksManager.toggleBookmark(news)
        );

        if (btn instanceof FloatingActionButton)
            adapter.update(news);
    }

}
