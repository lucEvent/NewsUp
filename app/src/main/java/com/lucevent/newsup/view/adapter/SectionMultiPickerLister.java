package com.lucevent.newsup.view.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;


import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.Section;

import java.util.ArrayList;

public class SectionMultiPickerLister extends ArrayAdapter<Section> implements View.OnClickListener {

    private boolean[] marks;
    private LayoutInflater inflater;

    public SectionMultiPickerLister(Context context, ArrayList<Section> values, boolean[] marks) {
        super(context, -1/*R.layout.i_picker*/, values);
        this.marks = marks;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
 /*       if (view == null) {
            view = inflater.inflate(R.layout.i_picker, parent, false);
        }
        Section section = getItem(position);

        CheckBox checkbox = (CheckBox) view.findViewById(R.id.checkbox);
        checkbox.setOnClickListener(this);
        if (section.level == 0) {
            checkbox.setTypeface(null, android.graphics.Typeface.BOLD);
            checkbox.setPadding((int) (15 * Resources.getSystem().getDisplayMetrics().density), 0, 0, 0);
        } else if (section.level == 1) {
            checkbox.setTypeface(null, android.graphics.Typeface.NORMAL);
            checkbox.setPadding((int) (25 * Resources.getSystem().getDisplayMetrics().density), 0, 0, 0);
        }
        checkbox.setText(getItem(position).name);
        checkbox.setChecked(marks[position]);
        checkbox.setTag(position);
 */       return view;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        marks[position] = !marks[position];
    }
}