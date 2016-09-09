package com.lucevent.newsup.view.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lucevent.newsup.R;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.ScheduleManager;
import com.lucevent.newsup.services.ScheduledDownloadReceiver;
import com.lucevent.newsup.services.util.DownloadSchedule;
import com.lucevent.newsup.view.activity.DownloadScheduleEditorActivity;
import com.lucevent.newsup.view.adapter.DownloadScheduleAdapter;

public class ScheduleDownloadSettingsFragment extends Fragment {

    private ScheduleManager dataManager;
    private DownloadScheduleAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        dataManager = new ScheduleManager(getActivity());
        adapter = new DownloadScheduleAdapter(dataManager.getDownloadSchedules(), onModifyAction, onDeleteAction);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},
                    AppCode.REQUEST_PERMISSION_READ_PHONE_STATE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.f_schedule_download_settings, container, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setAutoMeasureEnabled(true);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        view.findViewById(R.id.button_sections).setOnClickListener(onAddAction);

        return view;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        getActivity().setTitle(R.string.pref_schedule_downloads);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            adapter.notifyDataSetChanged();

            ScheduledDownloadReceiver.scheduleDownloads(getActivity(),
                    dataManager.getDownloadSchedules());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
    {
        switch (requestCode) {
            case AppCode.REQUEST_PERMISSION_READ_PHONE_STATE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the contacts-related task you need to do.

                } else {

                    // permission denied, boo!
                    Toast.makeText(getActivity(), R.string.msg_read_phone_state_permission_denied, Toast.LENGTH_SHORT).show();

                }
                break;
            }
        }
    }

    private View.OnClickListener onAddAction = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            Intent intent = new Intent(getActivity(), DownloadScheduleEditorActivity.class);
            intent.putExtra(AppCode.ACTION, DownloadScheduleEditorActivity.ACTION_CREATE);
            startActivityForResult(intent, 0);
        }
    };

    private View.OnClickListener onModifyAction = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            Intent intent = new Intent(getActivity(), DownloadScheduleEditorActivity.class);
            intent.putExtra(AppCode.SEND_DOWNLOAD_SCHEDULE, (DownloadSchedule) v.getTag());
            intent.putExtra(AppCode.ACTION, DownloadScheduleEditorActivity.ACTION_MODIFY);
            startActivityForResult(intent, 0);
        }
    };

    private View.OnClickListener onDeleteAction = new View.OnClickListener() {
        @Override
        public void onClick(final View v)
        {
            new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.msg_confirm_to_delete)
                    .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            DownloadSchedule schedule = (DownloadSchedule) v.getTag();
                            adapter.remove(schedule);
                            dataManager.deleteDownloadSchedule(schedule);
                            ScheduledDownloadReceiver.scheduleDownloads(getActivity(),
                                    dataManager.getDownloadSchedules());
                        }
                    })
                    .setNegativeButton(R.string.cancel, null)
                    .show();
        }
    };

}
