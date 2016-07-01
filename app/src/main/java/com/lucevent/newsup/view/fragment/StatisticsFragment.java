package com.lucevent.newsup.view.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.stats.Statistics;
import com.lucevent.newsup.net.StatisticsReader;
import com.lucevent.newsup.view.adapter.StatisticsAdapter;

import java.lang.ref.WeakReference;

public class StatisticsFragment extends android.app.Fragment {

    private enum SortOrder {
        SORT_BY_NAME, SORT_BY_NUM_REQUESTS, SORT_BY_TIME
    }

    private Handler handler;

    private StatisticsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        handler = new Handler(this);
        fetchStatistics(SortOrder.SORT_BY_NUM_REQUESTS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.f_statistics, container, false);

        adapter = new StatisticsAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setAutoMeasureEnabled(true);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        View bbyname = view.findViewById(R.id.by_sitename);
        View bbynrequest = view.findViewById(R.id.by_nrequests);
        View bbytime = view.findViewById(R.id.by_time);

        bbyname.setTag(SortOrder.SORT_BY_NAME);
        bbynrequest.setTag(SortOrder.SORT_BY_NUM_REQUESTS);
        bbytime.setTag(SortOrder.SORT_BY_TIME);

        bbyname.setOnClickListener(onOrderChanged);
        bbynrequest.setOnClickListener(onOrderChanged);
        bbytime.setOnClickListener(onOrderChanged);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        menu.add(R.string.reset)
                .setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
                .setIcon(R.drawable.ic_remove_all)
                .setOnMenuItemClickListener(onResetAction);

        super.onCreateOptionsMenu(menu, inflater);
    }

    private MenuItem.OnMenuItemClickListener onResetAction = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item)
        {
            StatisticsReader.fetchStatistics(handler, "http://newsup-2406.appspot.com/app?stats&reset");
            return true;
        }
    };

    private View.OnClickListener onOrderChanged = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            fetchStatistics((SortOrder) v.getTag());
        }
    };

    private void fetchStatistics(SortOrder order)
    {
        String url = "";
        switch (order) {
            case SORT_BY_NAME:
                url = "http://newsup-2406.appspot.com/app?stats&options=s";
                break;
            case SORT_BY_NUM_REQUESTS:
                url = "http://newsup-2406.appspot.com/app?stats&options=n";
                break;
            case SORT_BY_TIME:
                url = "http://newsup-2406.appspot.com/app?stats&options=t";
        }
        StatisticsReader.fetchStatistics(handler, url);
    }

    static class Handler extends android.os.Handler {

        private final WeakReference<StatisticsFragment> context;

        Handler(StatisticsFragment context)
        {
            this.context = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg)
        {
            StatisticsFragment service = context.get();
            switch (msg.what) {
                case AppCode.STATISTICS:
                    service.adapter.setNewDataSet((Statistics) msg.obj);
                    break;
                default:
                    AppSettings.printerror("[SF] OPTION UNKNOWN: " + msg.what);
            }
        }

    }

}
