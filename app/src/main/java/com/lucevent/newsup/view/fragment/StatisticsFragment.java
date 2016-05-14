package com.lucevent.newsup.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lucevent.newsup.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class StatisticsFragment extends android.app.Fragment implements Runnable {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        new Thread(this).start();
    }

    private TextView vContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.f_statistics, container, false);

        vContent = (TextView) view.findViewById(R.id.content_box);

        return view;
    }

    @Override
    public void run()
    {
        String url = "http://newsup-2406.appspot.com/request?stats&options=n";
        String content;
        try {

            URLConnection connection = (new URL(url)).openConnection();
            connection.connect();

            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            char[] buffer = new char[1024];

            StringBuilder html = new StringBuilder();
            for (int read = 0; (read = reader.read(buffer, 0, 1024)) > 0; ) {
                html.append(buffer, 0, read);
            }
            in.close();

            content = html.toString();
        } catch (IOException e) {
            e.printStackTrace();
            content = "Error" + e.toString();
        }

        final String finalContent = content;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run()
            {
                while (vContent == null) ;
                vContent.setText(finalContent);
            }
        });
    }
}
