package com.newsup;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DebugActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.a_debug);


        String data = getIntent().getExtras().getString("debug");


        TextView view = new TextView(this);

        view.setText(data);

        setContentView(view);
    }


    private void debug(String text) {
        Log.d("##DEBUG##", text);
    }

}
