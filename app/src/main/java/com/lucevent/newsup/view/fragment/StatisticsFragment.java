package com.lucevent.newsup.view.fragment;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.support.design.widget.Snackbar;
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
import com.lucevent.newsup.services.StatisticsService;
import com.lucevent.newsup.view.adapter.StatisticsAdapter;

import java.lang.ref.WeakReference;

public class StatisticsFragment extends android.app.Fragment {

    public enum SortOrder {
        SORT_BY_NAME, SORT_BY_NUM_REQUESTS, SORT_BY_TIME
    }

    public static final SortOrder DEFAULT_ORDER = SortOrder.SORT_BY_NUM_REQUESTS;

    private Handler handler;

    private StatisticsAdapter adapter;

    private View mainView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        handler = new Handler(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mainView = inflater.inflate(R.layout.f_statistics, container, false);

        adapter = new StatisticsAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setAutoMeasureEnabled(true);

        RecyclerView recyclerView = (RecyclerView) mainView.findViewById(R.id.list);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        View bbyname = mainView.findViewById(R.id.by_sitename);
        View bbynrequest = mainView.findViewById(R.id.by_nrequests);
        View bbytime = mainView.findViewById(R.id.by_time);

        bbyname.setTag(SortOrder.SORT_BY_NAME);
        bbynrequest.setTag(SortOrder.SORT_BY_NUM_REQUESTS);
        bbytime.setTag(SortOrder.SORT_BY_TIME);

        bbyname.setOnClickListener(onOrderChanged);
        bbynrequest.setOnClickListener(onOrderChanged);
        bbytime.setOnClickListener(onOrderChanged);

        return mainView;
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
            if (service.isInternetAvailable()) {
                new AlertDialog.Builder(getActivity())
                        .setMessage(R.string.msg_confirm_to_reset_statistics)
                        .setNegativeButton(R.string.cancel, null)
                        .setPositiveButton(R.string.reset, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                service.resetStatistics(handler);
                            }
                        })
                        .show();
            } else
                notifyNoInternet();
            return true;
        }
    };

    private View.OnClickListener onOrderChanged = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            if (service.isInternetAvailable())
                service.getStatistics(handler, (SortOrder) v.getTag());
            else
                notifyNoInternet();
        }
    };

    private void notifyNoInternet()
    {
        Snackbar.make(mainView, R.string.msg_no_internet_connection, Snackbar.LENGTH_LONG).show();
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
                    AppSettings.printerror("[SF] OPTION UNKNOWN: " + msg.what, null);
            }
        }

    }

    //////////////////////////////////////////////
    ///////////////// Service set-up /////////////
    //////////////////////////////////////////////
    private StatisticsService service;
    private boolean serviceBound = false;

    @Override
    public void onStart()
    {
        super.onStart();
        Intent intent = new Intent(getActivity(), StatisticsService.class);
        getActivity().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onStop()
    {
        super.onStop();
        if (serviceBound) {
            getActivity().unbindService(serviceConnection);
            serviceBound = false;
        }
    }

    /**
     * Defines callbacks for service binding, passed to bindService()
     */
    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder ibinder)
        {
            service = ((StatisticsService.Binder) ibinder).getService();
            serviceBound = true;
            if (service.isInternetAvailable())
                service.getStatistics(handler, DEFAULT_ORDER);
            else
                notifyNoInternet();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0)
        {
            serviceBound = false;
        }
    };

}
