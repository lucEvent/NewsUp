package com.newsup;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.newsup.io.BookmarksManager;
import com.newsup.kernel.News;
import com.newsup.kernel.NewsDataCenter;
import com.newsup.kernel.list.NewsList;
import com.newsup.kernel.list.NewsMap;
import com.newsup.net.State;
import com.newsup.widget.NewsLister;
import com.newsup.widget.NewsView;

public class BookmarksActivity extends ListActivity implements State {

    private NewsLister newslister;
    private NewsView newsView;

    private BookmarksManager manager;
    private boolean displayingNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_bookmarks);

        newslister = new NewsLister(this, new NewsList());
        newslister.setNotifyOnChange(true);
        setListAdapter(newslister);

        newsView = new NewsView(this, new NewsDataCenter(this, null), handler);

        manager = new BookmarksManager(handler);
        manager.getBookmarkedNews();

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (newsView.displayNews((News) getListAdapter().getItem(position))) {
            getActionBar().hide();

            displayingNews = true;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bookmarks, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        if (displayingNews) {
            closeNews(null);
        } else {
            finish();
        }
    }

    public void closeNews(View v) {
        getActionBar().show();

        displayingNews = false;
        newsView.close();
    }

    private final Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case NEWS_READ_BOOKMARKS:
                    newslister.addAll((NewsList) msg.obj);
                    break;
                case ACTION_CLOSE_NEWS:
                    getActionBar().show();
                    displayingNews = false;
                    break;
                case ERROR:
                    debug("Error recibido por el Handler");
                    break;
                default:
                    debug("[][#] OPCION NO CONTEMPLADA");
            }
        }

    };


    private void debug(String text) {
        Log.d("##BOOKMARKSACTIVITY##", text);
    }


}
