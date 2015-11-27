package com.newsup.lister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.newsup.R;
import com.newsup.kernel.Section;

import java.util.ArrayList;

public class SectionPickerLister extends ArrayAdapter<Section> {

    private LayoutInflater inflater;

    public SectionPickerLister(Context context, ArrayList<Section> values) {
        super(context, -1, values);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Section section = getItem(position);
        if (section.level <= 0) {
            if (view == null || !view.hasOnClickListeners() || view.getId() != R.layout.i_section_header) {
                view = inflater.inflate(R.layout.i_section_header, parent, false);
            }
            if (section.level == -1) {
                ((TextView) view).setTextColor(0xff999999);
                view.setOnClickListener(null);
            }
        } else {
            if (view == null || !view.hasOnClickListeners() || view.getId() != R.layout.i_section) {
                view = inflater.inflate(R.layout.i_section, parent, false);
            }
        }

        ((TextView) view).setText(section.name);

        return view;
    }

    private void debug(String text) {
        android.util.Log.d("##SectionLister##", text);
    }

}
