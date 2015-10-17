package com.newsup.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import com.newsup.R;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class SectionPickerLister extends ArrayAdapter<Section> implements View.OnClickListener {

    private boolean[] marks;
    private LayoutInflater inflater;

    public SectionPickerLister(Context context, SectionList values, boolean[] marks) {
        super(context, R.layout.i_picker_section, values);
        this.marks = marks;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = inflater.inflate(R.layout.i_picker_section, parent, false);
            view.setOnClickListener(this);
        }
        CheckBox ctview = (CheckBox) view;
        ctview.setTag(position);
        ctview.setTag(position);
        ctview.setText(getItem(position).name);
        ctview.setChecked(marks[position]);

        return ctview;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        marks[position] = !marks[position];
    }
}