package com.lucevent.newsup.view.fragment;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
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

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.Main;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.NewsMap;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.kernel.NewsManager;
import com.lucevent.newsup.view.adapter.NewsAdapter;
import com.lucevent.newsup.view.dialog.SectionsDialog;
import com.lucevent.newsup.view.util.ContentLoader;
import com.lucevent.newsup.view.util.OnNewsItemClickListener;

import java.lang.ref.WeakReference;

public class NewsListFragment extends android.app.Fragment {

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

    public int currentSiteCode;
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
        switch (currentSiteCode) {
            case -2:

                NewsArray newsArray = new NewsArray();
                int[] news_ids = getArguments().getIntArray(AppCode.SEND_NEWS_IDS);
                assert news_ids != null : "Arguments can't be recovered";
                for (int news_id : news_ids)
                    newsArray.add(dataManager.getNewsById(news_id));
                adapter.setNewDataSet(newsArray);
                getActivity().setTitle(R.string.app_name);

                break;

            case -1:
                dataManager.getMainNews();
                getActivity().setTitle(R.string.my_news);
                break;

            default:
                dataManager.getNewsOf(currentSite, null);
                getActivity().setTitle(currentSite.name);

        }
        adapter.clear();
        setUpColors();
    }

    private NewsManager dataManager;
    private NewsAdapter adapter;

    private View mainView;
    private RecyclerView recyclerView;
    private FloatingActionButton btn_sections;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        Handler handler = new Handler(this);
        dataManager = new NewsManager(getActivity(), handler);

        setSite(getArguments().getInt(AppCode.SEND_SITE_CODE));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if (adapter == null) {
            mainView = inflater.inflate(R.layout.f_news_list, container, false);

            adapter = new NewsAdapter(new NewsArray(), new OnNewsItemClickListener(getActivity()));
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

            btn_sections = (FloatingActionButton) mainView.findViewById(R.id.button_sections);
            btn_sections.setOnClickListener(onSectionsAction);


        }
        setUp();
        return mainView;
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
                case AppCode.NO_INTERNET:
                    Snackbar.make(service.mainView, R.string.msg_no_internet_connection, Snackbar.LENGTH_LONG).show();
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
            ((Main) getActivity()).getMainFragmentManager()
                    .replaceFragment(SiteSettingsFragment.instanceFor(currentSite.code), currentSite.code, true);
            return true;
        }
    };

    private View.OnClickListener onSectionSelected = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            Section section = (Section) v.getTag();
            dataManager.getNewsOf(currentSite, new int[]{currentSite.sections.indexOf(section)});
            adapter.clear();
            sectionsDialog.dismiss();
        }
    };

}
