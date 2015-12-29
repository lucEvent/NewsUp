package com.newsup;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.newsup.dialog.ScheduleDownloadDialog;
import com.newsup.kernel.NewsDataCenter;
import com.newsup.lister.DownloadScheduleLister;
import com.newsup.settings.AppSettings;
import com.newsup.settings.DownloadScheduleSetting;
import com.newsup.task.Socket;

public class ScheduleDownloadActivity extends ListActivity implements Socket {

    private NewsDataCenter dataManager;
    private ScheduleDownloadDialog dialog;

    private int editing = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_schedule_download);

        setListAdapter(new DownloadScheduleLister(this, this));

        dialog = new ScheduleDownloadDialog(this, this);
        dataManager = new NewsDataCenter(this);

        if (AppSettings.DL_SCHEDULES.isEmpty()) {
            dialog.showNew();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.scheduledownload, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_clear_all:
                editing = -1;
                dialog.showNew();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        editing = position;
        dialog.showItem((DownloadScheduleSetting) getListAdapter().getItem(position));
    }

    @Override
    public void message(int taskMessage, Object dataAttached) {
        switch (taskMessage) {
            case DOWNLOAD_SCHEDULE:
                if (editing != -1) {
                    dataManager.updateSetting(AppSettings.MOD_DL_SCHEDULE, editing, dataAttached);
                    editing = -1;
                } else {
                    dataManager.setSetting(AppSettings.ADD_DL_SCHEDULE, dataAttached);
                }
                break;
            case REMOVE_SCHEDULE:
                dataManager.setSetting(AppSettings.DEL_DL_SCHEDULE, dataAttached);
                System.out.println("Deleting");
                break;
        }
        ((DownloadScheduleLister) getListAdapter()).notifyDataSetChanged();
    }

    private void debug(String text) {
        Log.d("##ScheduleDLActivity##", text);
    }
}
