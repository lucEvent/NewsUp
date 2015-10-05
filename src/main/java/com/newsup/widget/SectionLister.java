package com.newsup.widget;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.newsup.R;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class SectionLister extends ArrayAdapter<Section> {

    private LayoutInflater inflater;

    public SectionLister(Context context, SectionList values) {
        super(context, R.layout.i_news, values);

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = inflater.inflate(R.layout.i_section, parent, false);
        }
        Section section = getItem(position);

        ((TextView) view).setText(section.name);

        return view;
    }

    private void debug(String text) {
        android.util.Log.d("##SectionLister##", text);
    }

}
