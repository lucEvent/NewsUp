package com.lucevent.newsup.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.UserSite;
import com.lucevent.newsup.kernel.KernelManager;
import com.lucevent.newsup.net.RawContentReader;
import com.lucevent.newsup.view.adapter.UserSiteAdapter;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;

public class NewSiteRequestActivity extends AppCompatActivity {

    private static final String request_query = "http://newsup-2406.appspot.com/appv2?request_site=";

    private UserSiteAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_new_site_request);

        findViewById(R.id.request).setOnClickListener(onRequest);

        mAdapter = new UserSiteAdapter(onSiteSelected);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setAutoMeasureEnabled(true);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    private View.OnClickListener onRequest = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            EditText input = (EditText) findViewById(R.id.input);
            final String request = input.getText().toString().trim();
            if (request.isEmpty())
                return;

            // Showing progress bar & message
            findViewById(R.id.layout_wait).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.wait_message)).setText(getString(R.string.searching, request));

            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(input.getWindowToken(), 0);

            new Thread(new Runnable() {
                @Override
                public void run()
                {
                    String response = "";
                    try {
                        response = RawContentReader.getUrl(request_query + URLEncoder.encode(request, "utf-8")).toString();
                    } catch (Exception ignored) {
                        AppSettings.printerror("Error", ignored);
                    }

                    final ArrayList<UserSite> aux = new ArrayList<>();
                    try {
                        JSONObject json = new JSONObject(response);

                        if (json.has("code")) {
                            int code = json.getInt("code");
                            String name = json.getString("name");
                            String url = json.getString("url");
                            String rss = json.getString("rss");
                            String icon = json.getString("icon");
                            int info = json.getInt("info");
                            int color = 0xff000000 | json.getInt("color");

                            aux.add(new UserSite(code, name, color, url, info, rss, icon));
                        }
                    } catch (Exception ignored) {
                        AppSettings.printerror("Error", ignored);
                    }

                    // Hiding progress bar & message
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run()
                        {
                            findViewById(R.id.layout_wait).setVisibility(View.GONE);
                            mAdapter.setNewDataSet(aux);
                        }
                    });
                }
            }).start();

        }
    };

    private View.OnClickListener onSiteSelected = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            UserSite selectedSite = (UserSite) v.getTag();

            new KernelManager(NewSiteRequestActivity.this).addSite(selectedSite);
            setResult(selectedSite.code);
            finish();
        }
    };

}
