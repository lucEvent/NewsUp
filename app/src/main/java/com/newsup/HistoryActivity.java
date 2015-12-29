package com.newsup;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.newsup.kernel.HistoryDataCenter;
import com.newsup.kernel.basic.News;
import com.newsup.kernel.set.NewsMap;
import com.newsup.lister.HistoryNewsLister;
import com.newsup.task.SocketMessage;
import com.newsup.widget.NewsView;

public class HistoryActivity extends ListActivity {

    private HistoryNewsLister newslister;
    private NewsView newsView;

    private HistoryDataCenter datamanager;

    private boolean displayingNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_history);

        newslister = new HistoryNewsLister(this);
        setListAdapter(newslister);

        datamanager = new HistoryDataCenter(this, handler);
        datamanager.load_Historial();

        newsView = new NewsView(this, handler);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.historial, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_clear_all:
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        removeHistory();
                    }
                });
                dialog.setNegativeButton(android.R.string.cancel, null);
                dialog.setTitle(R.string.msg_confirm_clear_hist);
                dialog.create().show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void removeHistory() {
        datamanager.clearHistory();
        newslister.clear();
    }

    @Override
    public void onBackPressed() {
        if (displayingNews) {
            closeNews();
        } else {
            finish();
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        News news = newslister.getItem(position);
        if (news.link != null) {
            datamanager.getNewsContent(news);

            displayNews(news);
        }
    }

    private void displayNews(News news) {
        if (newsView.displayNews(news)) {
            getActionBar().hide();
            displayingNews = true;
        }
    }

    private final Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SocketMessage.HISTORY_READ:
                    newslister.addAll((NewsMap) msg.obj);
                    break;
                case SocketMessage.ERROR:
                    debug("Error recibido por el Handler");
                    break;
                default:
                    debug("[###] OPCION NO CONTEMPLADA: " + msg.what);
            }
        }

    };

    public void closeNews() {
        getActionBar().show();
        displayingNews = false;
        newsView.close();
    }

    private static void debug(String text) {
        android.util.Log.d("##HISTORIAL##", text);
    }

}

