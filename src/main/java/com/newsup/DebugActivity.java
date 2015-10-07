package com.newsup;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.newsup.kernel.News;

public class DebugActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_debug);


        view = (RelativeLayout) findViewById(R.id.layoutcontent);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        newsView = (WebView) view.findViewById(R.id.content);

        title = (TextView) view.findViewById(R.id.title);

News news = null;
//        News news = NewsView.debug;
/*

        Document d = Jsoup.parse(news.content);
        Elements es = d.getElementsByTag("img");
        for (Element e : es) {
            e.attr("width", "100%");
            e.attr("height", "");
        }
*/
        title.setText(news.title);
        newsView.loadData(news.content, "text/html; charset=UTF-8", null);

        view.setVisibility(RelativeLayout.VISIBLE);


        debug("##################################################");
        debug(news.content);
        debug("##################################################");

    }


    private RelativeLayout view;

    private WebView newsView;
    private TextView title;

    private void debug(String text) {
        Log.d("##DEBUG##", text);
    }

}
